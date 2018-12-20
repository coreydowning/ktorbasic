package com.coreydowning.ktorbasic

import com.coreydowning.ktorbasic.vehicles.Vehicle
import com.coreydowning.ktorbasic.vehicles.VehicleService
import com.coreydowning.ktorbasic.vehicles.vehicles
import com.coreydowning.ktorbasic.vehicles.vehiclesModule
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Locations
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.util.KtorExperimentalAPI
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
@KtorExperimentalLocationsAPI
@Suppress("unused") // Referenced in application.conf
fun Application.defaultApp() = app(Kodein {
    import(vehiclesModule)
})

@KtorExperimentalLocationsAPI
@KtorExperimentalAPI
fun Application.app(kodein: Kodein) {
    val vehicleService: VehicleService by kodein.instance()
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        gson {}
    }
    install(Locations)
    install(StatusPages) {
        exception<NoSuchElementException> { cause ->
            call.respondText(cause.localizedMessage, status = HttpStatusCode.NotFound)
        }
    }
    install(Routing) {
        get("/") {
            call.respondText(
                "Corey drives a ${environment.config.property("coreydowning.vehicle").getString()}",
                ContentType.Text.Plain
            )
        }
        route("/v1") {
            route("/vehicles") {
                get {
                    call.respond(peopleAndVehicles)
                }
                post {
                    val vehicle = call.receive<Vehicle>()
                    peopleAndVehicles.add(vehicle)
                    call.respond(peopleAndVehicles)
                }
                get("/{owner}") {
                    val vehicle = peopleAndVehicles.find { vehicle -> vehicle.owner == call.parameters["owner"] }
                    when (vehicle) {
                        null -> {
                            call.respond(HttpStatusCode.NotFound, {})
                        }
                        else -> {
                            call.respond(vehicle)
                        }
                    }
                }
            }
        }
        route("/v2") {
            vehicles(vehicleService)
        }
    }
}

val peopleAndVehicles = mutableListOf<Vehicle>()