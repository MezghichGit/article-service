package com.sip.ams.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.ams.entities.Article;
import com.sip.ams.models.ArticleProvider;
import com.sip.ams.services.ArticleService;

@RestController
@RequestMapping("/articles")
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	@PostMapping("/")
	public Article addArticle(@RequestBody Article article)
	{
		return articleService.saveArticle(article);
		
	}
	
	@GetMapping("/{id}")
	public ArticleProvider getArticleById(@PathVariable("id")Long id)
	{
		return articleService.getArticleWithProvider(id);
		
	}
	
	//@GetMapping("/")
	/*public List<Article> getAllArticles()
	{
		return articleService.getAllArticles();
		
	}*/
	@GetMapping("/")
	public List<ArticleProvider> getAlllArticlesWithProviders(){
		return articleService.getAlllArticlesWithProviders();
	}



}
