package com.example.cryptotracker.crypto.presentation.coin_list

import com.example.cryptotracker.core.domain.util.NetworkError

/*
Why sealed class for events?
- Ensures all events are handled properly.
- Avoids multiple triggers for the same event (e.g., a button click sending multiple navigation events).
*/
sealed interface CoinListEvent {
    // Events are one-time UI effects (like showing a toast or navigation).
    // They flow from ViewModel to UI (opposite of Actions).

    data class Error(val error: NetworkError): CoinListEvent

}