package com.applycreditcard.coinedone.Modal;

import java.util.ArrayList;

public class QuestionRVModal {
    private String question;
    private String time;
    private String bigPic;
    private ArrayList<String> images;
    private String concerned;
    private String awareness;
    private String canDo;
    private String shareLink;
    private ArrayList<String> tags;

    public QuestionRVModal(String question, String time, String bigPic, ArrayList<String> images, String concerned, String awareness, String canDo, String shareLink, ArrayList<String> tags) {
        this.question = question;
        this.time = time;
        this.bigPic = bigPic;
        this.images = images;
        this.concerned = concerned;
        this.awareness = awareness;
        this.canDo = canDo;
        this.shareLink = shareLink;
        this.tags = tags;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getConcerned() {
        return concerned;
    }

    public void setConcerned(String concerned) {
        this.concerned = concerned;
    }

    public String getAwareness() {
        return awareness;
    }

    public void setAwareness(String awareness) {
        this.awareness = awareness;
    }

    public String getCanDo() {
        return canDo;
    }

    public void setCanDo(String canDo) {
        this.canDo = canDo;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

}
