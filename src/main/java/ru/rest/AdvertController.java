package ru.rest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdvertController {

    @GetMapping("/advert")
    public String getAdvert(@RequestParam(value = "id", required = false, defaultValue = "0") int id) {
        JSONArray res = new JSONArray();
        if (id <= 0) {
            Application.dao.getAllAdverts().stream()
                    .map(this::advertToJson)
                    .forEach(res::put);
            return res.toString();
        }
        res.put(advertToJson(Application.dao.getAdvert(id)));
        return res.toString();
    }

    private JSONObject advertToJson(Advert adv){
        JSONObject obj = new JSONObject();
        String date = adv.getDateTime().toLocalDateTime().format(DateTimeFormatter.ofPattern("Y-MM-dd h:mm"));
        obj.put("id", adv.getId());
        obj.put("desc", adv.getDesc());
        obj.put("dateTime", date);
        obj.put("sum", adv.getSum());
        obj.put("title", adv.getTitle());
        obj.put("img", adv.getImg());
        obj.put("url", adv.getUrl());
        return obj;
    }

    @PutMapping("/advert")
    public Advert putAdvert(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "title", required = false, defaultValue = "") String title,
            @RequestParam(value = "dateTime", required = false, defaultValue = "") String timestamp,
            @RequestParam(value = "sum", required = false, defaultValue = "-1") float sum,
            @RequestParam(value = "desc", required = false, defaultValue = "") String desc,
            @RequestParam(value = "img", required = false, defaultValue = "") String img,
            @RequestParam(value = "url", required = false, defaultValue = "") String url
    ) {
        if(id > 0) {
            Advert advert = Application.dao.getAdvert(id);

            if(!timestamp.isEmpty()) {
                try {
                    Timestamp timestamp1 = Timestamp.valueOf(timestamp);
                    advert.setDateTime(timestamp1);
                }catch (Exception e) {
                    throw new IllegalArgumentException("not valid timestamp");
                }
            }

            if(!title.isEmpty())
                advert.setTitle(title);

            if(sum >= 0)
                advert.setSum(sum);

            if(!desc.isEmpty())
                advert.setDesc(desc);

            if(!img.isEmpty())
                advert.setImg(img);

            if (!url.isEmpty())
                advert.setUrl(url);

            return Application.dao.updateAdvert(advert);
        }
        throw new IllegalArgumentException("not valid args");
    }

    @PostMapping("/advert")
    public Advert postAdvert(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "sum") float sum,
            @RequestParam(value = "desc") String desc,
            @RequestParam(value = "img") String img,
            @RequestParam(value = "url") String url
    ) {


        if(title.isEmpty() || sum <= 0 || timestamp.isEmpty() || desc.isEmpty() || img.isEmpty() || url.isEmpty())
            throw new IllegalArgumentException("not valid args");

        Timestamp timestamp1;
        try {
            timestamp1 = Timestamp.valueOf(timestamp);
        }catch (Exception e) {
            throw new IllegalArgumentException("not valid timestamp");
        }
        Advert adv = new Advert();
        adv.setDateTime(timestamp1);
        adv.setTitle(title);
        adv.setSum(sum);
        adv.setDesc(desc);
        adv.setImg(img);
        adv.setUrl(url);

        return Application.dao.createAdvert(adv);
    }

    @DeleteMapping("/advert")
    public void deleteAdvert(@RequestParam(value = "id") int id) {
        Application.dao.delete(id);
    }

}
