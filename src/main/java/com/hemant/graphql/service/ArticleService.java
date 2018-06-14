package com.hemant.graphql.service;

import java.util.List;

import com.hemant.graphql.model.Article;
import com.hemant.graphql.model.Feedback;
import com.hemant.graphql.model.pagination.PageRequest;

public interface ArticleService {

	List<Article> getAllArticles(PageRequest pageRequest);

	Article createArticle(String name, String createdByUserId);

	List<Feedback> getFeedbacksForArticle(String articleId, PageRequest pageRequest);

	Feedback createFeedback(String feedbackText, String articleId, String createdByUserId);

}
