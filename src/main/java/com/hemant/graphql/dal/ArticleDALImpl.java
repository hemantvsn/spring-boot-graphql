package com.hemant.graphql.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hemant.graphql.model.Article;
import com.hemant.graphql.model.PageRequest;

@Repository
public class ArticleDALImpl implements ArticleDAL {

	@Autowired
	private MongoTemplate mongo;

	@Override
	public List<Article> getAllArticles(PageRequest pageRequest) {
		Query query = PageRequest.getPaginationQueryForMongo(pageRequest);
		return mongo.find(query, Article.class);
	}
	
	
}
