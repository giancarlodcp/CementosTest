package com.giancarlo.cementostest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    var imdbID: String = "",
    var year: String = "",
    var type: String = "",
    var released: String = "",
    var title: String = "",
    var plot: String = "",
    var poster: String = "",
    var actors: String = "",
)