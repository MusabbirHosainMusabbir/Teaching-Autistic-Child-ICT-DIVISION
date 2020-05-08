package com.teachingchild.teachingautisticchild.model;

public class Item {
    String image,id;
    String topic_id;
    int percentage,number,totalnumber;
    String title,subtitle;
    int icon;

    public Item(String topic_id, String image, int percentage, String title, String subtitle, int number, int totalnumber) {
        this.topic_id = topic_id;
        this.image = image;
        this.percentage = percentage;
        this.title = title;
        this.subtitle = subtitle;
        this.number = number;
        this.totalnumber = totalnumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item(String image, String title){
        this.id = id;
        this.image = image;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Item(String id,int icon, String title){
        this.id = id;
        this.icon = icon;
        this.title = title;
    }
    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }



    public int getTotalnumber() {
        return totalnumber;
    }

    public void setTotalnumber(int totalnumber) {
        this.totalnumber = totalnumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
