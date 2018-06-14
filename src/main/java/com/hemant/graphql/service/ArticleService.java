package com.hemant.graphql.service;

import java.util.List;

import com.hemant.graphql.model.Article;
import com.hemant.graphql.model.PageRequest;

public interface ArticleService {

	List<Article> getAllArticles(PageRequest pageRequest);

}
