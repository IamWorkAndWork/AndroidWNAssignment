package com.example.presentation.ui.coinranking

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.databinding.CoinRankingFragmentBinding
import com.example.presentation.model.CoinUIModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinRankingFragment : Fragment() {

    private val coinRankingViewModel: CoinRankingViewModel by viewModel()
    private var _binding: CoinRankingFragmentBinding? = null
    private val binding get() = _binding!!

    private var searchJob: Job? = null
    private var isPullRoRefresh = false

    private val coinRankingAdapter by lazy {
        CoinRankingAdapter(clickedItemListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CoinRankingFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this@CoinRankingFragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWidget()
        initListener()
        hideShimmerLoading()
        searchCoin(query = "")
    }

    @OptIn(InternalCoroutinesApi::class)
    private fun initListener() {
        binding.queryEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(text: Editable?) {
                searchCoin(query = text.toString())
            }
        })

        coinRankingAdapter.addLoadStateListener { loadState ->
            val isListEmpty =
                loadState.refresh is LoadState.NotLoading && coinRankingAdapter.itemCount == 0
            showEmptyList(isListEmpty)

            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
//            binding.shimmerRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading

            val isLoading = loadState.source.refresh is LoadState.Loading
            showSwipeRefreshLoading(isLoading)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshSearchQuery()
        }

    }

    private fun refreshSearchQuery() {
        binding.queryEditText.setText("")
        searchCoin(query = "")
        isPullRoRefresh = true
        coinRankingAdapter.refresh()
    }

    private fun showSwipeRefreshLoading(loading: Boolean) {
        if (isPullRoRefresh && loading) {
            binding.swipeRefreshLayout.isRefreshing = true
        } else {
            binding.swipeRefreshLayout.isRefreshing = false
            isPullRoRefresh = false
        }
    }

    private fun showEmptyList(show: Boolean) {
        when (show) {
            true -> {
                binding.noDataTextView.visibility = View.VISIBLE
                binding.shimmerRecyclerView.visibility = View.GONE
            }
            false -> {
                binding.noDataTextView.visibility = View.GONE
                binding.shimmerRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    private fun initWidget() {
        val lm = LinearLayoutManager(context)
        val divider = DividerItemDecoration(context, lm.orientation)

        binding.shimmerRecyclerView.apply {
            layoutManager = lm
            adapter = coinRankingAdapter.withLoadStateFooter(
                footer = CoinLoadStateAdapter()
            )
//            addItemDecoration(divider)
            showShimmer()
        }
    }

    private fun searchCoin(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            coinRankingViewModel.searchCoin(query = query).collectLatest { pagingData ->
                coinRankingAdapter.submitData(pagingData)
            }
        }
    }

    private fun hideShimmerLoading() {
        lifecycleScope.launch {
            delay(1200L)
            binding.shimmerRecyclerView.hideShimmer()
        }
    }

    private val clickedItemListener: ((CoinUIModel) -> Unit) = { model ->
        when (model) {
            is CoinUIModel.DefaultItem -> {
                Toast.makeText(
                    requireContext(),
                    String.format(getString(R.string.text_toast_clicked), model.name),
                    Toast.LENGTH_SHORT
                ).show()
            }
            is CoinUIModel.RightItem -> {
                Toast.makeText(
                    requireContext(),
                    String.format(getString(R.string.text_toast_clicked), model.name),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}