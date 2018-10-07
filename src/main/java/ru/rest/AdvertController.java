package ru.rest;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvertController {

    @RequestMapping("/advert")
    public Advert advert() {
        Advert obj = new Advert("hellow world", 1);
        return obj;
    }
}
