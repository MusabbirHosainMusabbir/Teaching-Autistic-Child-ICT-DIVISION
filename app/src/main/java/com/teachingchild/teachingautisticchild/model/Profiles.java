package com.teachingchild.teachingautisticchild.model;

public class Profiles {
    int serial;
    String image,name,numbers;

    public Profiles(int serial, String image, String name, String numbers) {
        this.serial = serial;
        this.image = image;
        this.name = name;
        this.numbers = numbers;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }
}
