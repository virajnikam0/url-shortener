package com.url.shortener.api.repositories;

import com.url.shortener.api.entities.UrlShortenerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IUrlShortenerRepository extends JpaRepository<UrlShortenerEntity,Integer> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM url_shortener WHERE short_url = :shortUrl)", nativeQuery = true)
    int isShortUrlExists(String shortUrl);

    @Query(value = "SELECT id,count,date_created,long_url,short_url FROM url_shortener WHERE short_url = :shortUrl", nativeQuery = true)
    UrlShortenerEntity longUrlFromShortUrl(String shortUrl);

    @Transactional
    @Modifying
    @Query(value = "UPDATE url_shortener SET count = :count WHERE id = :id", nativeQuery = true)
    int updateVisitCount(@Param("count") int count, @Param("id") int id);



}
