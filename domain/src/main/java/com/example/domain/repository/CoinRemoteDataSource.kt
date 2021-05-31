package com.example.domain.repository

import com.example.domain.entity.CoinEntity

interface CoinRemoteDataSource {
    suspend fun getCoins(
        offset: Int,
        limit: Int
    ): List<CoinEntity>

    suspend fun getCoinsByPrefix(prefix: String): List<CoinEntity>
    suspend fun getCoinsBySymbols(symbols: String): List<CoinEntity>
    suspend fun getCoinsBySlugs(slugs: String): List<CoinEntity>
    suspend fun getCoinsByIds(ids: String): List<CoinEntity>
}
