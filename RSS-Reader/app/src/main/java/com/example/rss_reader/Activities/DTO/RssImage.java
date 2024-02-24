package com.example.rss_reader.Activities.DTO;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "image", strict = false)
public class RssImage {
    @Element
    private String url;
    @Element
    private String title;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "RssImage{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
