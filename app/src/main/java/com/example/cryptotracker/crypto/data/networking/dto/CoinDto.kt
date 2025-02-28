package com.example.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id : String,
    val rank : Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: Double,
    val priceUsd: Double,
    val changePercent24Hr: Double
)


// DTO - Data Transfer Object
/*

Technically, we could make the 'Coin' class as Serializable and use it as a DTO, but this
breaks our architecture, because,
1) In real life projects, data types from the DTOs and the domain's objects may differ.
   For instance, it might be easier to use a LocalDate object in the domain's object, but,
   such objects are hard to serialize, thus generally APIs use time in milliseconds or
   string timestamp instead of our type of objects.
2) We couple our domain layer to implementation details layer by doing so.

*/