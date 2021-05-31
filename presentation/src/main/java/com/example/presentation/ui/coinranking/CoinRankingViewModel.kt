package com.example.presentation.ui.coinranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.domain.usecase.GetCoinsPagingDataUseCase
import com.example.presentation.mapper.CoinUIModelMapper
import com.example.presentation.model.CoinUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoinRankingViewModel(
    private val getCoinsPagingDataUseCase: GetCoinsPagingDataUseCase,
    private val coinUIModelMapper: CoinUIModelMapper
) : ViewModel() {

    private var currentSearchResult: Flow<PagingData<CoinUIModel>>? = null
    private var currentQueryValue: String? = null

    fun searchCoin(query: String): Flow<PagingData<CoinUIModel>> {
        val lastResult = currentSearchResult

        if (query == currentQueryValue && lastResult != null) {
            return lastResult
        }

        currentQueryValue = query

        val result = getCoinsPagingDataUseCase.execute(query = query).map { pagingData ->
            var index = 0
            pagingData.map { coin ->
                index++
                coinUIModelMapper.transform(coinEntity = coin, index = index)
            }
        }.cachedIn(viewModelScope)

        currentSearchResult = result

        return result

    }

}