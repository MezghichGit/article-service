package com.sip.ams.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sip.ams.entities.Article;
import com.sip.ams.models.ArticleProvider;
import com.sip.ams.models.Provider;
import com.sip.ams.repositories.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	RestTemplate restTemplate;

	public Article saveArticle(Article article) {
		return articleRepository.save(article);
	}

	public ArticleProvider getArticleWithProvider(Long articleId) {
		ArticleProvider article_provider = new ArticleProvider();
		
		log.info("Get article avec son provider succès depuis Article Service");
		
		Article article = articleRepository.findArticleById(articleId);
		
		Provider provider = restTemplate.getForObject("http://PROVIDER-SERVICE/providers/" + article.getProviderId(),
				Provider.class);
		
		article_provider.setArticle(article);
		article_provider.setProvider(provider);
		
		return article_provider;
	}
	
	public List<Article> getAllArticles()
	{
		return  articleRepository.findAll();
	}
	
	public List<ArticleProvider> getAlllArticlesWithProviders() {
		
		List<ArticleProvider> mesArticles = new ArrayList<>();
		
		List<Article> articlesSansProviders = getAllArticles();
		
		for(Article article : articlesSansProviders)
		{
			Provider provider = restTemplate.getForObject("http://PROVIDER-SERVICE/providers/" + article.getProviderId(),
					Provider.class);
			ArticleProvider article_provider = new ArticleProvider();
			article_provider.setArticle(article);
			article_provider.setProvider(provider);
			mesArticles.add(article_provider);	
		}
		log.info("Récupération des articles avec providers avec succès");
		return mesArticles;
	}
}
