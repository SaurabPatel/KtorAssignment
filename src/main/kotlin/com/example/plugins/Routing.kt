package com.example.plugins

import com.data.Main
import com.data.Train
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import java.util.UUID

fun Application.configureRouting() {

    val main = Main()
    // Starting point for a Ktor app:
    routing {

        get("/") {
            val count = main.trainData.trainSources.count().toString()
            call.respondText(count)
        }

        get("/train") {
            var train = main.trainData.trainSources
            val trainName = train.map { it.name }
            call.respondText{ trainName.toString() }
        }

        post("/train/insert") {
            val request = call.receive<Parameters>()

            val name =  request["name"]
            val source = request["source"]
            val destination = request["destination"]
            val depatureTime = request["depatureTime"]

            if(name != null && source != null && destination != null && depatureTime != null) {
                var id = UUID.randomUUID().toString()
                val train = Train(id = id, name = name, source = source, destination = destination, depatureTime = depatureTime)
                main.trainData.insert(train)
                call.respondText { id }
            }
        }

        post("/train/filter") {
            val request = call.receive<Parameters>()

            //by Name
            val name =  request["name"]
            if(name != null) {
                val train = main.trainData.filterByName(name)
                if(train != null) {
                    call.respondText { main.trainData.printDetails(train) }
                    return@post
                }else {
                    call.respondText { "No Train Found" }
                    return@post
                }
            }

            //by source
            val source = request["source"]
            if(source != null) {
                val train = main.trainData.filterBySource(source)
                if(train.isNotEmpty()) {
                    call.respondText { train.map { main.trainData.printDetails(it) }.joinToString("\n\n")}
                    return@post
                }else {
                    call.respondText { "No Train Found" }
                    return@post
                }
            }

            //by desination
            val destination = request["destination"]
            if(destination != null) {
                val train = main.trainData.filterByDestination(destination)
                if(train.isNotEmpty()) {
                    call.respondText { train.map { main.trainData.printDetails(it) }.joinToString { "\n\n" } }
                    return@post
                }else {
                    call.respondText { "No Train Found" }
                    return@post
                }
            }

            //by depatureTime
            val depatureTime = request["depatureTime"]
            if(depatureTime != null) {
                val train = main.trainData.filterByDestination(depatureTime)
                if(train.isNotEmpty()) {
                    call.respondText { train.map { main.trainData.printDetails(it) }.joinToString { "\n\n" } }
                    return@post
                }else {
                    call.respondText { "No Train Found" }
                    return@post
                }
            }

        }
    }

}
