package com.example.cryptotracker.crypto.presentation.coin_list

import com.example.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    // This is used for one time events

    data class Error(val error: NetworkError): CoinListEvent

}