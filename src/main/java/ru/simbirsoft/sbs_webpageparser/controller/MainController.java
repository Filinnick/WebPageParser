package ru.simbirsoft.sbs_webpageparser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.simbirsoft.sbs_webpageparser.model.PageStatistic;
import ru.simbirsoft.sbs_webpageparser.repositories.PageRepository;
import ru.simbirsoft.sbs_webpageparser.services.HtmlParser;
import ru.simbirsoft.sbs_webpageparser.services.WordProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private WordProcessor wordProcessor;

    @Autowired
    private HtmlParser htmlParser;

    @GetMapping("/main")
    public String mainPage() {return "main_page";}

    @PostMapping("/parse")
    public String parsePage(Model model, @RequestParam String html, @RequestParam int number) {
        List<Map<String, Integer>> words = new ArrayList<>();
        try{
            PageStatistic pageStat = pageRepository.findByPageUrl(html);
            if(pageStat == null) {
                wordProcessor.setHtml(html);
                wordProcessor.stringSplitter(htmlParser.parseSite(html));
                pageStat = pageRepository.findByPageUrl(html);
            }
            for(int i = 0; i < number; i++){
                Map<String, Integer> word = new HashMap<String, Integer>();
                word.put(pageStat.getWordStat().get(i).getKey(),pageStat.getWordStat().get(i).getValue());
                words.add(word);
            }
            model.addAttribute("words",words);
            model.addAttribute("page",html);
        } catch (Exception e) {
            LOGGER.error("Encountered an error - {}", e.toString());
        }
        return "main_page";
    }
}
