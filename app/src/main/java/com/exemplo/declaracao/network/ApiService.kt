package com.exemplo.declaracao.network

import com.exemplo.declaracao.data.Address
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepService {
    @GET("ws/{cep}/json/")
    suspend fun getAddress(@Path("cep") cep: String): Address
}

object RetrofitInstance {
    private const val BASE_URL = "https://viacep.com.br/"

    val viaCepApi: ViaCepService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ViaCepService::class.java)
    }
}
