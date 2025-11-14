package com.giancarlo.cementostest.di

import android.content.Context
import androidx.room.Room
import com.giancarlo.cementostest.data.MovieApi
import com.giancarlo.cementostest.data.MoviesDatabase
import com.giancarlo.cementostest.data.repository.MovieRepository
import com.giancarlo.cementostest.data.repository.MovieRepositoryImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        impl: MovieRepositoryImpl
    ): MovieRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext app: Context)
    : MoviesDatabase =
        Room.databaseBuilder(app, MoviesDatabase::class.java, "movies.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMovieDao(db: MoviesDatabase) = db.movieDao()

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com")
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
            .build()
            .create(MovieApi::class.java)
    }
}