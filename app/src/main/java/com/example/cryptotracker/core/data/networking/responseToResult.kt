package com.example.cryptotracker.core.data.networking

import com.example.cryptotracker.core.domain.util.NetworkError
import com.example.cryptotracker.core.domain.util.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, NetworkError> {
    /*
    reified:
    Normally, generic types are erased at runtime (Type Erasure), but using reified inside an
    inline function allows access to T at runtime.
    'reified' keeps T available at runtime, allowing us to use it in reflection (T::class).

    Why in inline though?
    - inline copies the function’s bytecode directly into the caller's place.
    - Since the function is no longer a separate entity, the actual type is substituted before erasure happens.
    - This makes T available at runtime.
    */


    return when (response.status.value) {
        in 200..299 -> {
            try {
                // This is deserializing the response, if error occurs, it is caught
                // Also, the below line is a suspending function, that's why our function is suspend
                Result.Success(response.body<T>())
            }
            catch (e: NoTransformationFoundException) {
                Result.Error(NetworkError.SERIALIZATION_ERROR)
            }
        }
        408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
        else -> Result.Error(NetworkError.UNKNOWN)


    }
}