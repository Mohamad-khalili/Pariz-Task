package com.pariz.task.data.remote

import com.pariz.task.core.utils.ApiConstants
import com.pariz.task.domain.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    @GET(ApiConstants.MOVIES_LIST)
    suspend fun getMoviesList(@Query("page") page: Int) : Response<MoviesResponse>
}