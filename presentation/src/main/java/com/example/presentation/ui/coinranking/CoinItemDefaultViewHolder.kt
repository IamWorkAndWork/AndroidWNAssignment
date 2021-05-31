package com.example.presentation.ui.coinranking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemCoinDefaultBinding
import com.example.presentation.model.CoinUIModel

class CoinItemDefaultViewHolder(private val binding: ItemCoinDefaultBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): CoinItemDefaultViewHolder {
            val binding =
                ItemCoinDefaultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CoinItemDefaultViewHolder(binding)
        }
    }

    fun bind(model: CoinUIModel.DefaultItem, clickedItemListener: (CoinUIModel) -> Unit) =
        with(binding) {
            uiModel = model
            executePendingBindings()

            parentItem.setOnClickListener {
                clickedItemListener.invoke(model)
            }
        }

}