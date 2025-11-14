package com.giancarlo.cementostest.data.repository

import com.giancarlo.cementostest.data.MovieApi
import com.giancarlo.cementostest.data.MovieDao
import com.giancarlo.cementostest.domain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remote: MovieApi,
    private val local: MovieDao
) : MovieRepository {

    override fun getMovies(): Flow<List<Movie>> = flow {
        val localData = local.getAllMovies().firstOrNull().orEmpty()
        //Si no hay datos, descargamos
        if (localData.isEmpty()) {
            val result = remote.getMovies().results
            local.upsertAll(result.map { it.toEntity() })
        }
        emitAll(
            local.getAllMovies().map { list ->
                list.map { Movie(it.imdbID, it.year, it.type, it.released, it.title, it.plot, it.poster, it.actors) }
            }
        )
    }

    suspend fun refreshMovies(apiKey: String) {
        val remote = remote.getMovies(apiKey).results
        local.upsertAll(remote.map { it.toEntity() })
    }
}