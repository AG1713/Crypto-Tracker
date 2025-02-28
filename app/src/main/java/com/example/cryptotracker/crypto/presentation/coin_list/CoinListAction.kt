package com.example.cryptotracker.crypto.presentation.coin_list

import com.example.cryptotracker.crypto.presentation.CoinUI

sealed interface CoinListAction {
    data class OnCoinClicked(val coinUI: CoinUI): CoinListAction
}