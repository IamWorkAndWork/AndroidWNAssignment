package com.example.presentation.ui.coinranking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemLoadStateFooterViewBinding

class CoinLoadStateAdapter : LoadStateAdapter<CoinLoadStateAdapter.CoinLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: CoinLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CoinLoadStateViewHolder {
        val binding = ItemLoadStateFooterViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinLoadStateViewHolder(binding)
    }

    inner class CoinLoadStateViewHolder(private val binding: ItemLoadStateFooterViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }

    }

}

