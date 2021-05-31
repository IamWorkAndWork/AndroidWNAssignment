package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.entity.CoinEntity
import com.example.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

interface GetCoinsPagingDataUseCase {
    fun execute(query: String): Flow<PagingData<CoinEntity>>
}

class GetCoinsPagingDataUseCaseImpl(private val coinRepository: CoinRepository) :
    GetCoinsPagingDataUseCase {

    override fun execute(query: String): Flow<PagingData<CoinEntity>> {
        return coinRepository.getSearchResultStream(query = query)
    }

}