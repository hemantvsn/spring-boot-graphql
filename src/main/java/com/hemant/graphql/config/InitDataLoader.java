package com.hemant.graphql.config;

import java.util.Collections;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.hemant.graphql.model.Article;
import com.hemant.graphql.model.Feedback;

@Component
public class InitDataLoader {
	
	private static final Logger LOG = LoggerFactory.getLogger(InitDataLoader.class);
	
	@Autowired
	private MongoTemplate mongo;
	
	@PostConstruct
	public void createInitialData() {
		LOG.info("************STARTED INIT DATA LOADER************");
		clearAllData();
		
		for(int i=1; i<=5; i++) {
			String id = new ObjectId().toString();
			Article art = new Article();
			art.setId(id);
			art.setCreatedByUserId("User"+ i);
			art.setName(RandomStringUtils.randomAlphabetic(10));
			art.setCreatedOn(new Date());
			art.setLastUpdatedOn(new Date());
			mongo.save(art);
			
			LOG.info("Saved article : {}", art);
			
		}
		LOG.info("************END INIT DATA LOADER************");
		
	}

	private void clearAllData() {
		mongo.remove(new Query(), Article.class);
		mongo.remove(new Query(), Feedback.class);
		LOG.info("1. All existing data cleared");
	}
	
}
