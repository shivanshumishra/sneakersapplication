package com.example.sneakersapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.sneakersapp.data.data_source.SneakerDatabase
import com.example.sneakersapp.data.repository.SneakerRepositoryImpl
import com.example.sneakersapp.domain.repository.Repository
import com.example.sneakersapp.domain.usecases.AddSneakerToCartUseCase
import com.example.sneakersapp.domain.usecases.DeleteSneakerFromCartUseCase
import com.example.sneakersapp.domain.usecases.GetSneakerByIdUseCase
import com.example.sneakersapp.domain.usecases.GetSneakersInCartUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSneakerRepository(@ApplicationContext appContext: Context, db:SneakerDatabase) : Repository {
        return SneakerRepositoryImpl(appContext,db.sneakersDao)
    }

    @Provides
    @Singleton
    fun providesSneakerDatabase(app:Application) : SneakerDatabase {
        return Room.databaseBuilder(
            app,
            SneakerDatabase::class.java,
            SneakerDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesGetSneakerUsecase(repository: Repository) : GetSneakersInCartUseCase {
        return GetSneakersInCartUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetSneakerByIdUseCase(repository: Repository) : GetSneakerByIdUseCase {
        return GetSneakerByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesDeleteSneakerUsecase(repository: Repository) : DeleteSneakerFromCartUseCase {
        return DeleteSneakerFromCartUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesAddSneakerToCartUsecase(repository: Repository) : AddSneakerToCartUseCase {
        return AddSneakerToCartUseCase(repository)
    }

}