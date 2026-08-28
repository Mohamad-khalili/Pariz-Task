package com.pariz.task.data.repositoryImpl

import com.pariz.task.data.remote.MoviesApiService
import com.pariz.task.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    api: MoviesApiService
) : MoviesRepository {
}