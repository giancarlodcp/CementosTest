package com.giancarlo.cementostest.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {

    @GET("/")
    suspend fun getMovies(
        @Query("s") search:String = "Super",
        @Query("apikey") apiKey: String = "fdbdfe3a"
    ): MoviesResponse
}

@Serializable
data class MoviesResponse(
    @SerialName("Search") val results: List<MovieDto> = emptyList(),
    @SerialName("totalResults") val totalResults: String = "0",
    @SerialName("Response") val response: String = "False",
)