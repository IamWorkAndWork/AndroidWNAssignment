package com.example.presentation.mapper

import com.example.domain.entity.CoinEntity
import com.example.presentation.R
import com.example.presentation.model.CoinUIModel

interface CoinUIModelMapper {
    fun transform(coinEntity: CoinEntity, index: Int): CoinUIModel
}

class CoinUIModelMapperImpl : CoinUIModelMapper {

    override fun transform(coinEntity: CoinEntity, index: Int): CoinUIModel {
        return if (index % 5 == 0) {
            CoinUIModel.RightItem(
                id = coinEntity.id,
                name = coinEntity.name,
                description = coinEntity.description,
                iconDrawableRes = R.drawable.ic_bitcoin_btc
            )
        } else {
            CoinUIModel.DefaultItem(
                id = coinEntity.id,
                name = coinEntity.name,
                description = coinEntity.description,
                iconDrawableRes = R.drawable.ic_bitcoin_btc
            )
        }
    }

}