package com.example.presentation.ui.coinranking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemCoinRightBinding
import com.example.presentation.model.CoinUIModel

class CoinItemRightViewHolder(private val binding: ItemCoinRightBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): CoinItemRightViewHolder {
            val binding =
                ItemCoinRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CoinItemRightViewHolder(binding)
        }
    }

    fun bind(model: CoinUIModel.RightItem, clickedItemListener: (CoinUIModel) -> Unit) =
        with(binding) {
            uiModel = model
            executePendingBindings()

            parentItem.setOnClickListener {
                clickedItemListener.invoke(model)
            }
        }

}