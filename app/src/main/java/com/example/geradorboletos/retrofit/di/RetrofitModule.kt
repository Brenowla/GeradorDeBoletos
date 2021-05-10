package com.example.geradorboletos.retrofit.di

import com.example.geradorboletos.retrofit.RetrofitConstants
import com.example.geradorboletos.retrofit.service.AutenticateService
import com.example.geradorboletos.retrofit.service.ChargeService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    // Inicialização
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
//    fun provideOkHttpClient(logging: HttpLoggingInterceptor, authInterceptor: AuthInterceptor): OkHttpClient {
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
//            .addInterceptor(authInterceptor)

    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(RetrofitConstants.URL.BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    // Services
    @Singleton
    @Provides
    fun provideAuthenticationService(retrofit: Retrofit): AutenticateService {
        return retrofit.create(AutenticateService::class.java)
    }

    @Singleton
    @Provides
    fun provideChargeService(retrofit: Retrofit): ChargeService {
        return retrofit.create(ChargeService::class.java)
    }

}