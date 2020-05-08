package com.teachingchild.teachingautisticchild.model;

public class DoctorAdvice {
    String steps,title,subtitle,subtopicid;
    String image;
    int status;

    public DoctorAdvice(String steps, String title, String subtitle, String image, int status) {
        this.steps = steps;
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.status = status;
    }

    public DoctorAdvice(String subtopic_id, String steps, String title, String subtitle, String image, int status) {
        this.subtopicid = subtopic_id;
        this.steps = steps;
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.status = status;
    }


    public String getSubtopicid() {
        return subtopicid;
    }

    public void setSubtopicid(String subtopicid) {
        this.subtopicid = subtopicid;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
