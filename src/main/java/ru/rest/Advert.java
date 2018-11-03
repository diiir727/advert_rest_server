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

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "c_title")
    private String title;

    @Column(name = "c_url")
    private String url;

    @Column(name = "c_sum")
    private float sum;

    @Column(name = "c_desc")
    private String desc;

    @Column(name = "c_date")
    private  Timestamp date;

    @Column(name = "c_img")
    private String img;


    public Advert() {
    }

    public Advert(Integer id, Integer projectId, String title, String url, float sum, String desc, Timestamp date, String img) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.sum = sum;
        this.desc = desc;
        this.date = date;
        this.img = img;
        this.projectId = projectId;
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

    public Timestamp getDate() {
        return date;
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

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "title='" + title + '\'' +
                ", id=" + id +
                '}';
    }

    public void update(Advert advert) {
        this.desc = advert.desc;
        this.title = advert.title;
        this.date = advert.date;
        this.img = advert.img;
        this.url = advert.url;
        this.sum = advert.sum;
    }
}
