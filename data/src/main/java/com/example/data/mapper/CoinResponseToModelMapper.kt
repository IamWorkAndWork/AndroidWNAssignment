package com.example.data.mapper

import com.example.data.model.response.Coin
import com.example.data.model.response.CoinResponse
import com.example.domain.entity.CoinEntity

interface CoinResponseToModelMapper {
    suspend fun transform(coin: CoinResponse): List<CoinEntity>
}

class CoinResponseToModelMapperImpl : CoinResponseToModelMapper {

    override suspend fun transform(coin: CoinResponse): List<CoinEntity> {
        return coin.data?.coins?.map(::mapToCoinModel) ?: emptyList()
    }

    fun mapToCoinModel(coin: Coin): CoinEntity {
        return CoinEntity(
            id = coin.id ?: 0,
            uuid = coin.uuid.orEmpty(),
            name = coin.name.orEmpty(),
            description = coin.description.orEmpty(),
            iconUrl = coin.iconUrl.orEmpty()
        )
    }

}