package com.example.data.repository

import com.example.data.api.CoinRankingApi
import com.example.data.mapper.CoinResponseToModelMapper
import com.example.domain.entity.CoinEntity
import com.example.domain.repository.CoinRemoteDataSource

class CoinRemoteDataSourceImpl(
    private val coinRankingApi: CoinRankingApi,
    private val coinResponseToModelMapper: CoinResponseToModelMapper
) : CoinRemoteDataSource {

    override suspend fun getCoins(offset: Int, limit: Int): List<CoinEntity> {
        return try {
            val response = coinRankingApi.getCoins(offset = offset, limit = limit)
            coinResponseToModelMapper.transform(response)
        } catch (error: Exception) {
            emptyList()
        }
    }

    override suspend fun getCoinsByPrefix(prefix: String): List<CoinEntity> {
        return try {
            val response = coinRankingApi.getCoinsByPrefix(prefix = prefix)
            coinResponseToModelMapper.transform(response)
        } catch (error: Exception) {
            emptyList()
        }
    }

    override suspend fun getCoinsBySymbols(symbols: String): List<CoinEntity> {
        return try {
            val response = coinRankingApi.getCoinsBySymbols(symbols = symbols)
            coinResponseToModelMapper.transform(response)
        } catch (error: Exception) {
            emptyList()
        }
    }

    override suspend fun getCoinsBySlugs(slugs: String): List<CoinEntity> {
        return try {
            val response = coinRankingApi.getCoinsBySlugs(slugs = slugs)
            coinResponseToModelMapper.transform(response)
        } catch (error: Exception) {
            emptyList()
        }
    }

    override suspend fun getCoinsByIds(ids: String): List<CoinEntity> {
        return try {
            val response = coinRankingApi.getCoinsByIds(ids = ids)
            coinResponseToModelMapper.transform(response)
        } catch (error: Exception) {
            emptyList()
        }
    }

}
