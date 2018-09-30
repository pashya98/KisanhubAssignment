package com.kisanhub.assignment.remote;

import com.kisanhub.assignment.model.ArticlesInfo;
import com.kisanhub.assignment.model.PolygonInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("farm/farms.json")
    Call<List<PolygonInfo>> getPolygonInfoService();

    @GET("articles/articles.json")
    Call<ArticlesInfo> getArticlesInfoService();
}
