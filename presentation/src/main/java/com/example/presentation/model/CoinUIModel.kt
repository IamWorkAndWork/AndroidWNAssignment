package com.example.presentation.model

sealed class CoinUIModel {

    abstract var id: Int
    abstract var name: String
    abstract var description: String
    abstract var iconDrawableRes: Int

    data class DefaultItem(
        override var id: Int = 0,
        override var name: String = "",
        override var description: String = "",
        override var iconDrawableRes: Int
    ) : CoinUIModel()

    data class RightItem(
        override var id: Int = 0,
        override var name: String = "",
        override var description: String = "",
        override var iconDrawableRes: Int
    ) : CoinUIModel()

    data class SeperatorItem(
        override var id: Int = -1,
        override var name: String = "",
        override var description: String = "",
        override var iconDrawableRes: Int = 0
    ) : CoinUIModel()

}