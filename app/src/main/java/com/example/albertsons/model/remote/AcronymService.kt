package com.example.albertsons.model.remote

import com.example.albertsons.model.models.AcronymItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymService {

    companion object {
        private const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/dictionary.py/"

        private val interceptor = HttpLoggingInterceptor()
            .apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

        private val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        fun getInstance(): AcronymService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @GET(".")
    suspend fun getAcronymSearchResults(@Query("sf") search: String): Response<List<AcronymItem>>
}
