package com.pariz.task.domain.interactor

import com.pariz.task.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesInteractorImpl @Inject constructor(
    moviesRepository: MoviesRepository
) : MoviesInteractor{
}