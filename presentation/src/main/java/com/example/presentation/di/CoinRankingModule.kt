package com.example.presentation.di

import com.example.presentation.mapper.CoinUIModelMapper
import com.example.presentation.mapper.CoinUIModelMapperImpl
import com.example.presentation.ui.coinranking.CoinRankingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coinRankingModule = module {

    factory<CoinUIModelMapper> {
        CoinUIModelMapperImpl()
    }

    viewModel<CoinRankingViewModel> {
        CoinRankingViewModel(
            getCoinsPagingDataUseCase = get(),
            coinUIModelMapper = get()
        )
    }

}