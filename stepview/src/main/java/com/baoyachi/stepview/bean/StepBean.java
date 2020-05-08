package com.baoyachi.stepview.bean;

/**
 * 日期：16/9/3 00:36
 * <p/>
 * 描述：
 */
public class StepBean
{
    public static final int STEP_UNDO = -1;//未完成  undo step
    public static final int STEP_CURRENT = 0;//正在进行 current step
    public static final int STEP_COMPLETED = 1;//已完成 completed step

    private String topicname;
    private String name;
    private int state;
    private double percentage;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public StepBean()
    {
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public StepBean(String topicname, String name, int state, double percentage)
    {
        this.topicname = topicname;
        this.name = name;
        this.state = state;
        this.percentage = percentage;
    }
}
