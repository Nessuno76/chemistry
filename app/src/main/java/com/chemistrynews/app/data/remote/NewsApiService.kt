package com.chemistrynews.app.data.remote

import com.chemistrynews.app.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String,
        @Query("language") language: String = "en",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("pageSize") pageSize: Int = 50
    ): NewsResponse

    companion object {
        const val BASE_URL = "https://newsapi.org/"
        // IMPORTANTE: Sostituire con la propria API key da https://newsapi.org/
        const val API_KEY = "YOUR_API_KEY_HERE"
    }
}
