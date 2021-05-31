package com.example.presentation.ui.coinranking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.databinding.ItemSeperatorBinding
import com.example.presentation.model.CoinUIModel

class CoinItemSeperatorViewHolder(private val binding: ItemSeperatorBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): CoinItemSeperatorViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_seperator, parent, false)
            val binding = ItemSeperatorBinding.bind(view)
            return CoinItemSeperatorViewHolder(binding)
        }
    }

    fun bind(model: CoinUIModel.SeperatorItem) {

    }

}