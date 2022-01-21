package ru.simbirsoft.sbs_webpageparser.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

@Document(collection = "PageStatistic")
public class PageStatistic {

    @Id
    @Field(value = "PageURL")
    private String pageUrl;

    @Field(value = "wordStat")
    private List<Map.Entry<String, Integer>> wordStat;

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String url) {
        pageUrl = url;
    }

    public List<Map.Entry<String, Integer>> getWordStat() {
        return wordStat;
    }

    public void setWordStat(List<Map.Entry<String, Integer>> wordStat) {
        this.wordStat = wordStat;
    }
}
