package com.pariz.task.domain.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("Data")
    val movies: List<Movies>,
    val metadata: Metadata
)
