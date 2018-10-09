package ru.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class AdvertController {

    @RequestMapping("/advert")
    public Advert advert() {
        Advert obj = new Advert(1, "title", "url", 2005, "description", new Timestamp(1), "1.jpg");
        return obj;
    }
}
