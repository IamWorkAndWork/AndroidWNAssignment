package com.example.data.di

import com.example.data.mapper.CoinResponseToModelMapper
import com.example.data.mapper.CoinResponseToModelMapperImpl
import com.example.data.repository.CoinRemoteDataSourceImpl
import com.example.data.repository.CoinRepositoryImpl
import com.example.domain.repository.CoinRemoteDataSource
import com.example.domain.repository.CoinRepository
import org.koin.dsl.module

val RepositoryModule = module {

    factory<CoinResponseToModelMapper> {
        CoinResponseToModelMapperImpl()
    }

    factory<CoinRemoteDataSource> {
        CoinRemoteDataSourceImpl(
            coinRankingApi = get(),
            coinResponseToModelMapper = get()
        )
    }

    factory<CoinRepository> {
        CoinRepositoryImpl(
            coinRemoteDataSource = get()
        )
    }

}
