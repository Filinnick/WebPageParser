package ru.simbirsoft.sbs_webpageparser.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.simbirsoft.sbs_webpageparser.model.PageStatistic;

public interface PageRepository extends MongoRepository<PageStatistic, String> {//String: Type of PageStat ID.
    PageStatistic findByPageUrl(String pageUrl);
}
