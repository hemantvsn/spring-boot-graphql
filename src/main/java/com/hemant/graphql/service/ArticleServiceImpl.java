package com.hemant.graphql.service;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemant.graphql.dal.ArticleDAL;
import com.hemant.graphql.model.Article;
import com.hemant.graphql.model.Feedback;
import com.hemant.graphql.model.pagination.PageRequest;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDAL articleDAL;

	private static final Logger LOG = LoggerFactory.getLogger(ArticleServiceImpl.class);

	@Override
	public List<Article> getAllArticles(PageRequest pageRequest) {
		String validationErrors = PageRequest.validatePaginationParams(pageRequest);
		if(isNotBlank(validationErrors)) {
			throw new IllegalArgumentException(validationErrors);
		}
		
		return articleDAL.getAllArticles(pageRequest);
	}
	
	@Override
	public Article createArticle(String name, String createdByUserId) {
		if(isBlank(name)) {
			throw new IllegalArgumentException("Article name cannot be blank");
		}
		
		if(isBlank(createdByUserId)) {
			throw new IllegalArgumentException("CreatedByUserId cannot be blank");
		}
		
		String id = new ObjectId().toString();
		Article art = new Article();
		art.setId(id);
		art.setCreatedByUserId(createdByUserId);
		art.setName(name);
		art.setCreatedOn(new Date());
		art.setLastUpdatedOn(new Date());
		
		articleDAL.saveArticle(art);
		LOG.info("Article created successfully :{}", art);
		return art;
		
	}

	@Override
	public List<Feedback> getFeedbacksForArticle(String articleId) {
		return articleDAL.getFeedbacksForArticle(articleId);
	}
	
	@Override
	public Feedback createFeedback(String feedbackText, String articleId, String createdByUserId) {
		
		if(isBlank(feedbackText)) {
			throw new IllegalArgumentException("FeedbackText name cannot be blank");
		}
		
		if(isBlank(createdByUserId)) {
			throw new IllegalArgumentException("CreatedByUserId cannot be blank");
		}
		
		Article article = articleDAL.getArticleById(articleId);
		if(null == article) {
			LOG.error("No article exists for articleId :{}", articleId);
			throw new IllegalArgumentException("No article exists for articleId :" + articleId);
		}
		
		Feedback feedback = new Feedback();
		feedback.setArticleId(articleId);
		feedback.setCreatedByUserId(createdByUserId);
		feedback.setFeedbackText(feedbackText);
		feedback.setCreatedOn(new Date());
		feedback.setLastUpdatedOn(new Date());
		
		articleDAL.saveFeedback(feedback);
		LOG.info("Feedback created as :{} for article :{}", feedback, article);
		return feedback;
	}
	
}
