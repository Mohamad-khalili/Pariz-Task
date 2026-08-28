package com.pariz.task.domain.interactor

import androidx.paging.PagingData
import com.pariz.task.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MoviesInteractor {

     fun getMoviesList(): Flow<PagingData<Movies>>
}