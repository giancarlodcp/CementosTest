package com.giancarlo.cementostest.data

import com.giancarlo.cementostest.domain.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val imdbID: String,
    @SerialName("Year") val Year: String,
    @SerialName("Type") val Type: String,
   // @SerialName("Released" )val released: String,
    @SerialName("Title") val Title: String,
    //@SerialName("Plot") val plot: String,
    @SerialName("Poster") val Poster: String,
    //@SerialName("Actors") val actors: String,
) {
    fun toDomain() = Movie(
        imdbID = imdbID,
        title = Title,
        year = Year,
        poster = Poster,
        actors = "actors",
        plot = "plot",
        type = Type,
        released = "released"
    )

    fun toEntity() = MovieEntity(
        imdbID = imdbID,
        title = Title,
        year = Year,
        poster = Poster,
        actors = "actors",
        plot = "plot",
        type = Type,
        released = "released"
    )
}