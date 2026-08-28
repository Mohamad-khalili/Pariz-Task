package com.pariz.task.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pariz.task.core.paging.MoviesPagingSource
import com.pariz.task.core.utils.PagerConfig
import com.pariz.task.data.remote.MoviesApiService
import com.pariz.task.domain.model.Movies
import com.pariz.task.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MoviesApiService
) : MoviesRepository {
    override fun getMoviesList(): Flow<PagingData<Movies>> {
        return getMoviesListFlow()
    }


    private fun getMoviesListFlow(pagingConfig: PagingConfig = PagerConfig.getDefaultPageConfig()): Flow<PagingData<Movies>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                MoviesPagingSource(
                    api,
                    Dispatchers.IO
                )
            }
        ).flow
    }
}