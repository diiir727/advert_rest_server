package ru.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "sum")
    private float sum;

    @Column(name = "desc")
    private String desc;

    @Column(name = "date")
    private  Timestamp dateTime;

    @Column(name = "img")
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
