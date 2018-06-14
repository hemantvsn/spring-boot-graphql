package com.hemant.graphql.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hemant.graphql.model.Article;
import com.hemant.graphql.model.Feedback;
import com.hemant.graphql.model.pagination.PageRequest;

@Repository
public class ArticleDALImpl implements ArticleDAL {

	@Autowired
	private MongoTemplate mongo;

	@Override
	public List<Article> getAllArticles(PageRequest pageRequest) {
		Query query = PageRequest.getPaginationQueryForMongo(pageRequest);
		return mongo.find(query, Article.class);
	}

	@Override
	public void saveArticle(Article art) {
		mongo.save(art);
	}

	@Override
	public List<Feedback> getFeedbacksForArticle(String articleId, PageRequest pageRequest) {
		Query query = PageRequest.getPaginationQueryForMongo(pageRequest);
		query.addCriteria(Criteria.where("articleId").is(articleId));
		return mongo.find(query, Feedback.class);
	}

	@Override
	public Article getArticleById(String articleId) {
		return mongo.findById(articleId, Article.class);
	}

	@Override
	public void saveFeedback(Feedback fb) {
		mongo.save(fb);
	}

}
