package com.hemant.graphql.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemant.graphql.dal.ArticleDAL;
import com.hemant.graphql.model.Article;
import com.hemant.graphql.model.PageRequest;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDAL articleDAL;

	private static final Logger LOG = LoggerFactory.getLogger(ArticleServiceImpl.class);

	@Override
	public List<Article> getAllArticles(PageRequest pageRequest) {
		String validationErrors = PageRequest.validatePaginationParams(pageRequest);
		if(StringUtils.isNotBlank(validationErrors)) {
			throw new IllegalArgumentException(validationErrors);
		}
		
		return articleDAL.getAllArticles(pageRequest);
	}
	
}
