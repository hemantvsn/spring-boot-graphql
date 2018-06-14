package com.hemant.graphql.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hemant.graphql.model.Article;
import com.hemant.graphql.model.Feedback;
import com.hemant.graphql.service.ArticleService;

@Component
public class Mutation implements GraphQLMutationResolver {
	
	@Autowired
	private ArticleService articleService;
	
	public Article createArticle(String name, String createdByUserId) {
		return articleService.createArticle(name, createdByUserId);
	}
	
	public Feedback createNewFeedback(String feedbackText, String articleId,String createdByUserId) {
		return articleService.createFeedback(feedbackText, articleId, createdByUserId);
	}

}
