package com.compose.androidremind.advance.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

interface Repository {
    fun getD(): String
}

class RepositoryImpl @Inject constructor() : Repository {
    override fun getD(): String {
        return "provide data"
    }
}

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Provides
    fun provideRepository(): Repository = RepositoryImpl()
}