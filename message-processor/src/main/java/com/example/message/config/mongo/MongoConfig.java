package com.example.message.config.mongo;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages = "com.example.common")
@EnableMongoRepositories(basePackages = "com.example.common")
public class MongoConfig {
}
