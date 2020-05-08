package com.teachingchild.teachingautisticchild.model;

public class Badges {
    int image;
    String name,subtitles;

    public Badges(int image, String name, String subtitles) {
        this.image = image;
        this.name = name;
        this.subtitles = subtitles;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }
}
