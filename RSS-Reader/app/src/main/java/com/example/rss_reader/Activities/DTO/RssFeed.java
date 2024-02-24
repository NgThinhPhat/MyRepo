package com.example.rss_reader.Activities.DTO;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import java.util.List;

@Root(name = "rss", strict = false)
public class RssFeed {

    @ElementList(inline = true, name = "channel")
    private List<RssChannel> channels;

    public List<RssChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<RssChannel> channels) {
        this.channels = channels;
    }

}