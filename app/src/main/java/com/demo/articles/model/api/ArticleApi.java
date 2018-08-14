package com.demo.articles.model.api;

import retrofit.Callback;
import retrofit.http.GET;

public interface ArticleApi {

    @GET("/all-sections/7.json?api-key=94b8671b2ca04fa881d5062e405452b3")
    void getArticles(Callback<String> articles);
}
