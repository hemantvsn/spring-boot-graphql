package com.hemant.graphql.config;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class MongoConfig {

	private static final Logger LOG = LoggerFactory.getLogger(MongoConfig.class);

	@Value("${app.mongodb.host}")
	private String host;
	@Value("${app.mongodb.database}")
	private String db;
	@Value("${app.mongodb.authdb}")
	private String authDb;
	@Value("${app.mongodb.username}")
	private String username;
	@Value("${app.mongodb.password}")
	private String pwd;
	@Value("${app.mongodb.port}")
	private int port;

	@Bean
	public MongoTemplate getMongoTemplate() {
		MongoCredential creds = MongoCredential.createCredential(username, authDb, pwd.toCharArray());
		List<MongoCredential> credentialsList = Arrays.asList(creds);
		ServerAddress serverAddress = new ServerAddress(host, port);
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new MongoClient(serverAddress, credentialsList), db);
		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		LOG.info("************MONGO_TEMPLATE configured successfully :{}************", template);
		return template;
	}

}