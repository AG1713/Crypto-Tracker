package com.example.cryptotracker.core.data.networking

import com.example.cryptotracker.core.domain.util.NetworkError
import com.example.cryptotracker.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall (
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    }
    // The below catch exceptions before we get anything, and,
    catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    }
    catch (e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION_ERROR)
    }
    catch (e: Exception) {
//        if (e is CancellationException) throw e
        coroutineContext.ensureActive() // Somewhat equivalent to the above statement
        return Result.Error(NetworkError.UNKNOWN)
    }

    // this catches exceptions after getting the response
    return responseToResult(response)
}