package com.giancarlo.cementostest.data.repository

import com.giancarlo.cementostest.domain.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>
}