package com.example.cryptotracker.di

import com.example.cryptotracker.core.data.networking.HttpClientFactory
import com.example.cryptotracker.crypto.data.networking.RemoteCoinDataSource
import com.example.cryptotracker.crypto.domain.CoinDataSource
import com.example.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) } // Singleton
//    single { RemoteCoinDataSource(get()) } // get() looks through the app module to automatically
//    resolve
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>() // More or less the same as above line

    viewModelOf(::CoinListViewModel)
}