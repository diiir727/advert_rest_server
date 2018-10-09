package ru.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Advert {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    private Integer id;

    @Column(name = "c_title")
    private String title;

    @Column(name = "c_url")
    private String url;

    @Column(name = "c_sum")
    private float sum;

    @Column(name = "c_desc")
    private String desc;

    @Column(name = "c_date")
    private  Timestamp dateTime;

    @Column(name = "c_img")
    private String img;


    public Advert() {
    }

    public Advert(Integer id, String title, String url, float sum, String desc, Timestamp dateTime, String img) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.sum = sum;
        this.desc = desc;
        this.dateTime = dateTime;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public double getSum() {
        return sum;
    }

    public String getDesc() {
        return desc;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public String getImg() {
        return img;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
