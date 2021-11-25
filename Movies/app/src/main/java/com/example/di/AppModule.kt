package com.example.di

import android.app.Application
import androidx.room.Room
import com.example.api.MoviesApi
import com.example.api.MoviesApiInterface
import com.example.localdatabase.AllMoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit =
        Retrofit.Builder().baseUrl(MoviesApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit): MoviesApiInterface =
        retrofit.create(MoviesApiInterface::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app : Application):AllMoviesDatabase =
        Room.databaseBuilder(app,AllMoviesDatabase::class.java,"movies_database")
            .fallbackToDestructiveMigration().build()
















}