package com.example.presentation.ui.coinranking

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.model.CoinUIModel

class CoinRankingAdapter(private val clickedItemListener: (CoinUIModel) -> Unit) :
    PagingDataAdapter<CoinUIModel, RecyclerView.ViewHolder>(DiffItem) {

    companion object {
        private val DiffItem = object : DiffUtil.ItemCallback<CoinUIModel>() {
            override fun areItemsTheSame(oldItem: CoinUIModel, newItem: CoinUIModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CoinUIModel, newItem: CoinUIModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CoinUIModel.DefaultItem -> {
                R.layout.item_coin_default
            }
            is CoinUIModel.RightItem -> {
                R.layout.item_coin_right
            }
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_coin_default -> {
                CoinItemDefaultViewHolder.create(parent = parent)
            }
            else -> {
                CoinItemRightViewHolder.create(parent = parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uiModel = getItem(position)

        uiModel?.let { model ->

            when (model) {
                is CoinUIModel.DefaultItem -> (holder as CoinItemDefaultViewHolder).bind(
                    model,
                    clickedItemListener
                )
                is CoinUIModel.RightItem -> (holder as CoinItemRightViewHolder).bind(
                    model,
                    clickedItemListener
                )
            }

        }

    }


}