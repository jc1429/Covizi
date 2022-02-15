package com.example.covizi;

public class FAQ {
    private String title, content;
    private boolean expandable;

    public FAQ() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public FAQ(String title, String content) {
        this.title = title;
        this.content = content;
        this.expandable = false;
    }
}
