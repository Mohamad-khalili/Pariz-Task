package com.pariz.task.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pariz.task.domain.interactor.MoviesInteractor
import com.pariz.task.domain.model.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    interactor: MoviesInteractor
): ViewModel() {



    val movies: Flow<PagingData<Movies>> =
        interactor.getMoviesList()
            .cachedIn(viewModelScope)
}