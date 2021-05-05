package com.example.geradorboletos.database.di

import android.content.Context
import androidx.room.Room
import com.example.geradorboletos.database.GeradorBoletosDatabase
import com.example.geradorboletos.database.GeradorBoletosDatabaseConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabaseInstance(context: Context): GeradorBoletosDatabase {
        return Room.databaseBuilder(context, GeradorBoletosDatabase::class.java, GeradorBoletosDatabaseConstants.DB_NAME)
            .build()
    }
}