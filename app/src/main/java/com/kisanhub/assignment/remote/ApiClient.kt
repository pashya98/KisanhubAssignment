package com.kisanhub.assignment.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object Factory {
        var retrofit: Retrofit? = null
        var BASE_URL:String = "https://s3.eu-west-2.amazonaws.com/interview-question-data/"
        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
    }
}