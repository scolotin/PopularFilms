package com.scolotin.popularfilms.model

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("overview")
    val overview: String
 )

data class Films(
    @SerializedName("results")
    val results: List<Film>
)
