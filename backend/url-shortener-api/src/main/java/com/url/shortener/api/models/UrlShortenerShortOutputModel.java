package com.url.shortener.api.models;

import java.util.Date;

public class UrlShortenerShortOutputModel extends CommonModel{
    public int id;
    public String longUrl;
    public String shortUrl;
    public Date dateCreated;
    public int count;
}
