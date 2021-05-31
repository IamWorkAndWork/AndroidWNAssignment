package com.example.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.entity.CoinEntity
import com.example.domain.repository.CoinRemoteDataSource
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CoinPagingSource(
    private val query: String,
    private val coinRemoteDataSource: CoinRemoteDataSource,
) : PagingSource<Int, CoinEntity>() {

    override fun getRefreshKey(state: PagingState<Int, CoinEntity>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinEntity> {

        return try {

            when (query.isEmpty()) {
                true -> getCoinsByDefault(params = params)
                false -> getCoinsByFilter(query = query)
            }

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    private suspend fun getCoinsByFilter(
        query: String
    ): LoadResult.Page<Int, CoinEntity> {

        val coinList = mutableListOf<CoinEntity>()

        val coinsByPrefix = coinRemoteDataSource.getCoinsByPrefix(prefix = query)
        val coinsBySymbols = coinRemoteDataSource.getCoinsBySymbols(symbols = query)
        val coinsBySlugs = coinRemoteDataSource.getCoinsBySlugs(slugs = query)
        val coinsByIds = coinRemoteDataSource.getCoinsByIds(ids = query)

        coinList.addAll(coinsByIds)
        coinList.addAll(coinsBySlugs)
        coinList.addAll(coinsByPrefix)
        coinList.addAll(coinsBySymbols)

        return LoadResult.Page(
            data = coinList,
            prevKey = null,
            nextKey = null
        )

    }

    private suspend fun getCoinsByDefault(
        params: LoadParams<Int>
    ): LoadResult<Int, CoinEntity> {

        return try {

            val offset = params.key ?: 0

            val responseList =
                coinRemoteDataSource.getCoins(offset = offset, limit = params.loadSize)

            val nextKey = if (responseList.isNullOrEmpty()) {
                null
            } else {
                offset + params.loadSize
            }

            LoadResult.Page(
                data = responseList,
                prevKey = if (offset == 0) null else offset - 1,
                nextKey = nextKey
            )

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }

    }
}