package com.url.shortener.api.services;

import com.url.shortener.api.entities.UrlShortenerEntity;
import com.url.shortener.api.models.UrlShortenerLongInputModel;
import com.url.shortener.api.models.UrlShortenerShortInputModel;

public interface IUrlShortenerService {
    boolean isShortUrlExists(String shortUrl);
    UrlShortenerEntity createShortUrl(UrlShortenerShortInputModel inputModel);
    UrlShortenerEntity getLongUrlByShortUrl(UrlShortenerLongInputModel inputModel);
}
