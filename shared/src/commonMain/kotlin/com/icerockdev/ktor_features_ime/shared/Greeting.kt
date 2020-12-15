package com.icerockdev.ktor_features_ime.shared

import io.ktor.client.HttpClient
import io.ktor.client.features.cookies.AcceptAllCookiesStorage
import io.ktor.client.features.cookies.HttpCookies
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.Cookie
import io.ktor.http.Url


class Greeting {
    private val cookieStorage = AcceptAllCookiesStorage()
    private val httpClient: HttpClient = HttpClient {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
            level = LogLevel.HEADERS
        }
        // if we comment this feature - InvalidMutabilityException fixed.
        install(HttpCookies) {
            storage = cookieStorage
        }
    }

    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }

    suspend fun addSomeCookie() {
        cookieStorage.addCookie(
            requestUrl = Url("icerockdev.com"),
            cookie = Cookie("test", value = "test value")
        )
    }

    suspend fun sendRequest() {
        val content: String = httpClient.get("https://youtrack.jetbrains.com/issue/KTOR-1087")
        println(content)
    }
}
