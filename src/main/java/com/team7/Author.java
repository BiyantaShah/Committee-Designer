package com.team7;

public class Author {

 private  String name;
 private  Journal journal;
 private  Article article;
 private String email;

 	public Author(String name, Journal journal, Article article) {
	// TODO Auto-generated constructor stub
    	this.name = name;
    	this.journal = journal;
	   	this.article = article;
 	}

 	public Author(String name, Article article, Journal journal) {
 		// TODO Auto-generated constructor stub
 		this.name = name;
 		this.journal = journal;
 		this.article = article;
 	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
