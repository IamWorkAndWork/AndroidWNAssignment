package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.entity.CoinEntity
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    fun getSearchResultStream(
        query: String,
    ): Flow<PagingData<CoinEntity>>
}