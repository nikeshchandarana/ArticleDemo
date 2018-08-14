package com.demo.articles.model.api;

import com.google.gson.GsonBuilder;

import com.demo.articles.model.utilities.Constants;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class ApiManager {

    private ArticleApi mArticleApi;

    public ArticleApi getArticles() {

        if (mArticleApi == null) {
            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(String.class, new StringDesirializer());

            mArticleApi = new RestAdapter.Builder()
                    .setEndpoint(Constants.BASE_URL)
                    .setConverter(new GsonConverter(gson.create()))
                    .build()
                    .create(ArticleApi.class);
        }
        return mArticleApi;
    }

}
