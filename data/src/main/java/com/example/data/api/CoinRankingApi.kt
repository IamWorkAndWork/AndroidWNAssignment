package com.example.data.api

import com.example.data.model.response.CoinResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinRankingApi {

    @GET("v1/public/coins")
    suspend fun getCoins(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): CoinResponse

    @GET("v1/public/coins")
    suspend fun getCoinsByPrefix(@Query("prefix") prefix: String): CoinResponse

    @GET("v1/public/coins")
    suspend fun getCoinsBySymbols(@Query("symbols") symbols: String): CoinResponse

    @GET("v1/public/coins")
    suspend fun getCoinsBySlugs(@Query("slugs") slugs: String): CoinResponse

    @GET("v1/public/coins")
    suspend fun getCoinsByIds(@Query("ids") ids: String): CoinResponse

}