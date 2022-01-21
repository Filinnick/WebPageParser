package ru.simbirsoft.sbs_webpageparser.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class HtmlParser {

    public String parseSite(String html) throws IOException {

        Document doc = Jsoup.connect(html).get();
        Elements elements = doc.select("p, span, a, h1, h2, h3, h4, h5, h6");
        StringBuilder sb = new StringBuilder();
        String text = "";
        ArrayList<String> texts = new ArrayList<>();

        for(Element element : elements) {
            texts.add(element.text());
            sb.append(element.text());
            sb.append(" ");
        }
        text = sb.toString();

        return text;
    }

}
