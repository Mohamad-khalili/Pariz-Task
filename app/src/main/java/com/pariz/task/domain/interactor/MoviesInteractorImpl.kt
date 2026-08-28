package com.pariz.task.domain.interactor

import androidx.paging.PagingData
import com.pariz.task.domain.model.Movies
import com.pariz.task.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesInteractorImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : MoviesInteractor{
    override  fun getMoviesList(): Flow<PagingData<Movies>> {
        return moviesRepository.getMoviesList()
    }
}