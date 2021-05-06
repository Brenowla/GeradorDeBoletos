package com.example.geradorboletos.database.di

import android.content.Context
import androidx.room.Room
import com.example.geradorboletos.database.GeradorBoletosDatabase
import com.example.geradorboletos.database.GeradorBoletosDatabaseConstants
import com.example.geradorboletos.database.dao.ItemDAO
import com.example.geradorboletos.database.dao.PersonDAO
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

    @Singleton
    @Provides
    fun getPersonDAO(geradorBoletosDatabase: GeradorBoletosDatabase): PersonDAO {
        return geradorBoletosDatabase.personDAO
    }

    @Singleton
    @Provides
    fun getItemDAO(geradorBoletosDatabase: GeradorBoletosDatabase): ItemDAO {
        return geradorBoletosDatabase.itemDAO
    }
}