package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.domain.entity.CoinEntity
import com.example.domain.repository.CoinRemoteDataSource
import com.example.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

class CoinRepositoryImpl(
    private val coinRemoteDataSource: CoinRemoteDataSource
) : CoinRepository {

    companion object {
        const val NETWORK_LIMIT = 10
    }

    override fun getSearchResultStream(query: String): Flow<PagingData<CoinEntity>> {

        val pagingConfig = PagingConfig(
            pageSize = NETWORK_LIMIT,
            enablePlaceholders = false,
        )

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                CoinPagingSource(
                    query = query,
                    coinRemoteDataSource = coinRemoteDataSource
                )
            }
        ).flow

    }

}