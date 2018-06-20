package com.hemant.graphql.resolvers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hemant.graphql.model.Article;
import com.hemant.graphql.model.Feedback;
import com.hemant.graphql.model.pagination.PageRequest;
import com.hemant.graphql.model.pagination.SortOrder;
import com.hemant.graphql.service.ArticleService;

@Component
public class Query implements GraphQLQueryResolver {
	
	@Autowired
	private ArticleService articleService;

	public List<Article> getAllArticles(int pageNumber, int pageSize, SortOrder sortOrder, String sortBy) {
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize, sortOrder, sortBy);
		return articleService.getAllArticles(pageRequest);
	}
	
	public List<Feedback> getFeedBacksForArticle(String articleId) {
		return articleService.getFeedbacksForArticle(articleId);
	}
}
