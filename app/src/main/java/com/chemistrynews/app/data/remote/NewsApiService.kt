package com.chemistrynews.app.data.remote

import com.chemistrynews.app.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("language") language: String = "en",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("pageSize") pageSize: Int = 100,
        @Query("apiKey") apiKey: String
    ): NewsResponse

    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = "7fb1869164e042c18de7cad045211427"
    }
}
