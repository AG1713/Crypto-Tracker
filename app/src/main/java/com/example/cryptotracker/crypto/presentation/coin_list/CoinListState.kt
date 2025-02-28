package com.example.cryptotracker.crypto.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.example.cryptotracker.crypto.presentation.CoinUI


@Immutable
// This tells that the class will never change
data class CoinListState (
    val isLoading: Boolean = false,
    val coins: List<CoinUI> = emptyList(), // This is not stable for compose
    // Thus it will recompose more often than necessary
    // The immutable says that when this class change, the whole instances will be changed,
    // otherwise no need to recompose
    val selectedCoin: CoinUI? = null
)