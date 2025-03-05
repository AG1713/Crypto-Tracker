package com.example.cryptotracker.crypto.domain

import com.example.cryptotracker.core.domain.util.NetworkError
import com.example.cryptotracker.core.domain.util.Result
import java.time.ZonedDateTime

interface CoinDataSource {
    // This is in place f a repository
    // This specifies 'what' a coin data source should do that's why it is in domain

    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError>
}