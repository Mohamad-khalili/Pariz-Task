package com.pariz.task.domain.repository

import androidx.paging.PagingData
import com.pariz.task.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

     fun getMoviesList(): Flow<PagingData<Movies>>
}