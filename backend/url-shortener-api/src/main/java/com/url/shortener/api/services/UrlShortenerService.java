package com.url.shortener.api.services;

import com.url.shortener.api.constants.AppConstants;
import com.url.shortener.api.models.UrlShortenerLongInputModel;
import com.url.shortener.api.entities.UrlShortenerEntity;
import com.url.shortener.api.models.UrlShortenerShortInputModel;
import com.url.shortener.api.repositories.IUrlShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;

@Service
public class UrlShortenerService implements IUrlShortenerService {

    @Autowired
    private IUrlShortenerRepository urlShortenerRepository;

    @Override
    public boolean isShortUrlExists(String shortUrl) {
        return urlShortenerRepository.isShortUrlExists(shortUrl) >= 1 ? true : false;
    }

    @Override
    public UrlShortenerEntity createShortUrl(UrlShortenerShortInputModel inputModel) {
        // create short url
        String shortUrl = getUniqueShortUrl();
        // create entity
        UrlShortenerEntity entity = new UrlShortenerEntity();
        entity.setLongUrl(inputModel.longUrl);
        entity.setDateCreated(Date.from(Instant.now()));
        entity.setCount(0);
        entity.setShortUrl(shortUrl);
        // save db
        urlShortenerRepository.save(entity);
        return entity;
    }

    @Override
    public UrlShortenerEntity getLongUrlByShortUrl(UrlShortenerLongInputModel inputModel) {
        // get url entity by short url from db
        UrlShortenerEntity entity = urlShortenerRepository.longUrlFromShortUrl(inputModel.shortUrl);
        if (entity == null) {
            return null;
        }

        // increment visit count
        entity.setCount(entity.getCount() + 1);
        int affectedRows = urlShortenerRepository.updateVisitCount(entity.getCount(), entity.getId());

        // return
        if (affectedRows > 0) {
            return entity;
        }
        return entity;
    }


    private String getUniqueShortUrl() {

        int retryCount = 0;
        String url = "";
        boolean isInvalid = true;
        while (isInvalid && retryCount < AppConstants.MAX_RETRY_COUNT) {
            retryCount++;
            url = buildUniqueShortUrl();
            isInvalid = isShortUrlExists(url);
        }
        if (isInvalid || retryCount > AppConstants.MAX_RETRY_COUNT) {
            url = "";
        }
        return url;
    }

    private String buildUniqueShortUrl() {
        final SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        int length = AppConstants.UNIQUE_URL_INPUT.length();
        for (int i = 0; i < AppConstants.SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(length);
            stringBuilder.append(AppConstants.UNIQUE_URL_INPUT.charAt(index));
        }
        return stringBuilder.toString();
    }
}
