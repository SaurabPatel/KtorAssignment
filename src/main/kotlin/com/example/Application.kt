package com.example

import com.data.trainModule
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import org.koin.core.context.startKoin

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        connectDatabase()
        configureRouting()
    }.start(wait = true)
}

fun connectDatabase() {
    startKoin{
        modules(trainModule)
    }
}
