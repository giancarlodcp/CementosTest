package com.giancarlo.cementostest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giancarlo.cementostest.data.repository.MovieRepositoryImpl
import com.giancarlo.cementostest.domain.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    getMoviesUseCase: GetMoviesUseCase,
    private val repoImpl: MovieRepositoryImpl
) : ViewModel() {

    val movies = getMoviesUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    /*fun refresh(apiKey: String) {
        viewModelScope.launch {
            repoImpl.refreshMovies(apiKey)
        }
    }*/
}