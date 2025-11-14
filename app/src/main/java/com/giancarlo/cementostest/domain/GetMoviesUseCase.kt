package com.giancarlo.cementostest.domain

import com.giancarlo.cementostest.data.repository.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke() = repository.getMovies()
}