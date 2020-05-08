package com.teachingchild.teachingautisticchild.model;

import com.baoyachi.stepview.bean.StepBean;

import java.util.List;

public class CourseAdd {
    private List<StepBean> addList = null;

    public List<StepBean> getResponse() {
        return addList;
    }

    public void setResponse(List<StepBean> response) {
        this.addList = addList;
    }
}
