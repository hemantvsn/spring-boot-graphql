package com.hemant.graphql.model.resolvers;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hemant.graphql.model.Article;

@Service
public class Query implements GraphQLQueryResolver {

	public List<Article> getAllArticles(String id) {
		return Collections.emptyList();
	}
}
