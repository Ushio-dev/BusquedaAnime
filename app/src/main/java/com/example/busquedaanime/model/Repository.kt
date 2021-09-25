package com.example.busquedaanime.model

import retrofit2.Response

class Repository {

    suspend fun getAnimeList(url: String) : Response<ResponseAnime> {
        return RetrofirInstance.api.getAnimeList(url)
    }
}