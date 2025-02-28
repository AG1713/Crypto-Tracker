package com.example.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.core.domain.util.onError
import com.example.cryptotracker.core.domain.util.onSuccess
import com.example.cryptotracker.crypto.domain.CoinDataSource
import com.example.cryptotracker.crypto.presentation.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
    /*
    Another benefit of using a abstraction (here, the implementation) instead of concrete
    implementation is that, we cannot pass a different instance of the datasource during testing
    or for any reason
    */
): ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    // private because only viewmodel should alter this StateFlow

    val state = _state
        .onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )
    // This prevents accidental changes to state

    // Channels are similar to SharedFlow, where the value are emitted specifically, and the value
    // is not cached like a StateFlow
    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()


    fun onAction(action: CoinListAction) {
        when (action){
            is CoinListAction.OnCoinClicked -> {

            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update {
                // Function that updates the value of the stateflow in thread-safe manner,
                // thus race conditions can happen with update

                it.copy(
                    isLoading = true
                )
            }

            coinDataSource.getCoins()
                .onSuccess {
                    coins ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = coins.map { it.toCoinUi() }
                        )
                    }
                }
                .onError {
                    error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(CoinListEvent.Error(error))

                }
        }
    }


}