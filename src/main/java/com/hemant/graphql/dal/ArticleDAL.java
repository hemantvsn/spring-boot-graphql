package com.hemant.graphql.dal;

import java.util.List;

import com.hemant.graphql.model.Article;
import com.hemant.graphql.model.Feedback;
import com.hemant.graphql.model.pagination.PageRequest;

public interface ArticleDAL {
	
	List<Article> getAllArticles(PageRequest pageRequest);

	void saveArticle(Article art);

	List<Feedback> getFeedbacksForArticle(String articleId);

	Article getArticleById(String articleId);

	void saveFeedback(Feedback feedback);

}
