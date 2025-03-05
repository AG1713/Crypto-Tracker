package com.example.cryptotracker.crypto.presentation

import androidx.annotation.DrawableRes
import com.example.cryptotracker.crypto.domain.Coin
import com.example.cryptotracker.core.presentation.util.getDrawableIdForCoin
import com.example.cryptotracker.crypto.presentation.coin_details.DataPoint
import java.text.NumberFormat
import java.util.Locale

data class CoinUI(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int,
    val coinPriceHistory: List<DataPoint> = emptyList()
) {


    data class DisplayableNumber (
        val value: Double,
        val formatted: String
    )

}

fun Coin.toCoinUi(): CoinUI {
    return CoinUI(
            id = id,
            name = name,
            symbol = symbol,
            rank = rank,
            priceUsd = priceUsd.toDisplayableNumber(),
            marketCapUsd = marketCapUsd.toDisplayableNumber(),
            changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
            iconRes = getDrawableIdForCoin(symbol = symbol)
        )
}

fun Double.toDisplayableNumber(): CoinUI.DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    return CoinUI.DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}