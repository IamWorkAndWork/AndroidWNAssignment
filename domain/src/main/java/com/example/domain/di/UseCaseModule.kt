package com.example.domain.di

import com.example.domain.usecase.GetCoinsPagingDataUseCase
import com.example.domain.usecase.GetCoinsPagingDataUseCaseImpl
import org.koin.dsl.module

val UseCaseModule = module {

    factory<GetCoinsPagingDataUseCase> {
        GetCoinsPagingDataUseCaseImpl(
            coinRepository = get()
        )
    }

}