package com.example.cryptotracker.crypto.presentation.coin_list

import com.example.cryptotracker.crypto.presentation.CoinUI

/*
Why sealed class?
- It ensures type safety → Only predefined actions are allowed.
- It makes handling them in the ViewModel easier with when.
*/
sealed interface CoinListAction {
    data class OnCoinClicked(val coinUI: CoinUI): CoinListAction
}