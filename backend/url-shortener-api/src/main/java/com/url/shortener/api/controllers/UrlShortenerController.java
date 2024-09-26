package com.url.shortener.api.controllers;

import com.url.shortener.api.constants.AppConstants;
import com.url.shortener.api.models.*;
import com.url.shortener.api.services.UrlShortenerService;
import com.url.shortener.api.entities.UrlShortenerEntity;
import com.url.shortener.api.models.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/{controllerName}")
@CrossOrigin(origins = {"*"})
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/getShortUrl")
    public ResponseEntity<?> getShortUrl(@RequestBody UrlShortenerShortInputModel inputModel){
        try{
            // validate
            if(inputModel == null) {
                throw new Exception("invalid input");
            }
            if(inputModel.longUrl == null || inputModel.longUrl.isEmpty()) {
                throw new Exception("invalid input");
            }

            // process
            UrlShortenerEntity result =  urlShortenerService.createShortUrl(inputModel);

            // return
            UrlShortenerShortOutputModel model = new UrlShortenerShortOutputModel();
            model.isSuccess = true;
            model.responseStatus = com.url.shortener.api.models.ResponseStatus.SUCCESS;
            model.statusCode = HttpStatus.OK.value();
            model.message = "Successfully created short url";
            model.id = result.getId();
            model.shortUrl = result.getShortUrl();
            model.longUrl = result.getLongUrl();
            model.dateCreated = result.getDateCreated();
            model.count = result.getCount();
            return ResponseEntity.ok(model);
        }catch (Exception ex){
            // error
            UrlShortenerShortOutputModel model = new UrlShortenerShortOutputModel();
            model.isSuccess = false;
            model.responseStatus = com.url.shortener.api.models.ResponseStatus.FAILED;
            model.statusCode = HttpStatus.BAD_REQUEST.value();
            model.message = ex.getMessage();
            return ResponseEntity.badRequest().body(model);
        }
    }

    @PostMapping("/getLongUrl")
    public ResponseEntity<?> getLongUrl(@RequestBody UrlShortenerLongInputModel inputModel){
        try{
            //validate
            if(inputModel == null) {
                throw new Exception("invalid input");
            }
            if(inputModel.shortUrl == null || inputModel.shortUrl.isEmpty()) {
                throw new Exception("invalid input");
            }

            //process
            UrlShortenerEntity result = urlShortenerService.getLongUrlByShortUrl(inputModel);

            // return
            if(result == null){
                throw new Exception("Long Url not found for short url:" + inputModel.shortUrl);
            }
            UrlShortenerLongOutputModel model = new UrlShortenerLongOutputModel();
            model.isSuccess = true;
            model.responseStatus = com.url.shortener.api.models.ResponseStatus.SUCCESS;
            model.statusCode = HttpStatus.OK.value();
            model.message = "Successfully get long url";
            model.id = result.getId();
            model.shortUrl = result.getShortUrl();
            model.longUrl = result.getLongUrl();
            model.dateCreated = result.getDateCreated();
            model.count = result.getCount();
            return ResponseEntity.ok(model);
        }catch (Exception ex){
            // error
            UrlShortenerLongOutputModel model = new UrlShortenerLongOutputModel();
            model.isSuccess = false;
            model.responseStatus = ResponseStatus.FAILED;
            model.statusCode = HttpStatus.BAD_REQUEST.value();
            model.message = ex.getMessage();
            return ResponseEntity.badRequest().body(model);
        }
    }

}
