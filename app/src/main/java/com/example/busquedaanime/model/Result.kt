package com.example.busquedaanime.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val airing: Boolean,
    val end_date: String,
    val episodes: Int,
    val image_url: String,
    val mal_id: Int,
    val members: Int,
    val rated: String,
    val score: Double,
    val start_date: String,
    val synopsis: String,
    val title: String,
    val type: String,
    val url: String
) : Parcelable