package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.api.CoinRankingApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val NetworkModule = module {

    single<HttpLoggingInterceptor> {
        provideLoggingInterceptor()
    }

    single<OkHttpClient> {
        provideOkHttpClient(
            interceptor = get()
        )
    }

    single<Retrofit> {
        provideRetrofit(
            okHttpClient = get(),
            gsonConverterFactory = get()
        )
    }

    single<GsonConverterFactory> {
        provideGsonConverterFactory()
    }

    factory<CoinRankingApi> {
        provideCoinRankingService(retrofit = get())
    }

}

fun provideCoinRankingService(retrofit: Retrofit): CoinRankingApi {
    return retrofit.create(CoinRankingApi::class.java)
}

fun provideGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create()
}

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_SERVER_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}
