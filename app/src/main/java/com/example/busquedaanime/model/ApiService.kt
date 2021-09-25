package com.example.busquedaanime.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getAnimeList(@Url url: String) : Response<ResponseAnime>
}