package ru.simbirsoft.sbs_webpageparser.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simbirsoft.sbs_webpageparser.model.PageStatistic;
import ru.simbirsoft.sbs_webpageparser.repositories.PageRepository;
import java.util.*;

@Service
public class WordProcessor {

    @Autowired
    private PageRepository pageRepository;

    private List<Map.Entry<String, Integer>> wordStat = new ArrayList();
    private String html;

    public void stringSplitter(String text){
        String[] words = text.split("[^а-яА-ЯёЁa-zA-Z0-9]+");
        wordCounter(words);
    }

    public void wordCounter(String[] words){
        List<String> wordList = Arrays.asList(words);
        Map<String, Integer> wordMap = new HashMap<>();
        for(String word : wordList){
            if(wordMap.containsKey(word)){
                wordMap.put(word, wordMap.get(word) + 1);
            } else {
                wordMap.put(word, 1);
            }
        }
        wordStatSorter(wordMap);
    }

    public void wordStatSorter(Map<String, Integer> wordMap){
        List<Map.Entry<String, Integer>> sortedWordList = new ArrayList<>( wordMap.entrySet() );
        Collections.sort(sortedWordList, new Comparator<Map.Entry<String, Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2){
                return o2.getValue() - o1.getValue();
            }
        });
        wordStat = sortedWordList;
        addToMongoCollection();
    }

    public void addToMongoCollection() {
        PageStatistic pageStatistic = new PageStatistic();
        pageStatistic.setPageUrl(this.html);
        pageStatistic.setWordStat(this.wordStat);
        this.pageRepository.insert(pageStatistic);
    }

    public List<Map.Entry<String, Integer>> getWordStat() {
        return wordStat;
    }

    public void setWordStat(List<Map.Entry<String, Integer>> wordStat) {
        this.wordStat = wordStat;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
