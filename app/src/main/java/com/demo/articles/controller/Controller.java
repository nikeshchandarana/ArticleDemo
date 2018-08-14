package com.demo.articles.controller;

import android.util.Log;

import com.demo.articles.model.api.ApiManager;
import com.demo.articles.model.pojo.Articles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Controller {

    private static final String TAG = Controller.class.getSimpleName();
    private ArticleCallbackListener mListener;
    private ApiManager mApiManager;

    public Controller(ArticleCallbackListener listener) {
        mListener = listener;
        mApiManager = new ApiManager();
    }

    public void startFetching() {
        mApiManager.getArticles().getArticles(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, "JSON :: " + s);

                try {

                    JSONObject mainObject = new JSONObject(s);

                    JSONArray array = mainObject.getJSONArray("results");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        Articles articles = new Articles.Builder()
                                .setArticleBy(object.getString("byline"))
                                .setArticleSource(object.getString("source"))
                                .setArticleDate(object.getString("published_date"))
                                .setPhoto(getPhotoUrl(object))
                                .setName(object.getString("title"))
                                .setProductId(object.getString("id"))
                                .setArticleMainLink(object.getString("url"))
                                .build();

                        mListener.onFetchProgress(articles);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    mListener.onFetchFailed();
                }


                mListener.onFetchComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :: " + error.getMessage());
                mListener.onFetchComplete();
            }
        });
    }

    private String getPhotoUrl(JSONObject object) {
        String url = "";
        try {
            JSONArray array = object.getJSONArray("media");
            for (int i = 0; i < array.length(); i++) {
                JSONObject subObject = array.getJSONObject(i);
                JSONArray subArray = subObject.getJSONArray("media-metadata");

                for (int k = 0; k < subArray.length(); k++) {
                    JSONObject urlObject = subArray.getJSONObject(k);
                    if (urlObject.getString("format").equalsIgnoreCase("Standard Thumbnail")) {
                        url = urlObject.getString("url");
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return url;
    }

    public interface ArticleCallbackListener {

        void onFetchStart();

        void onFetchProgress(Articles articles);

        void onFetchProgress(List<Articles> articlesList);

        void onFetchComplete();

        void onFetchFailed();
    }
}
