package com.example.cryptotracker.crypto.domain

import com.example.cryptotracker.core.domain.util.NetworkError
import com.example.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    // This is in place f a repository
    // This specifies 'what' a coin data source should do that's why it is in domain

    suspend fun getCoins(): Result<List<Coin>, NetworkError>



}