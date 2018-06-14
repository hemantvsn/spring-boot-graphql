package com.hemant.graphql.dal;

import java.util.List;

import com.hemant.graphql.model.Article;
import com.hemant.graphql.model.PageRequest;

public interface ArticleDAL {
	
	List<Article> getAllArticles(PageRequest pageRequest);

}
