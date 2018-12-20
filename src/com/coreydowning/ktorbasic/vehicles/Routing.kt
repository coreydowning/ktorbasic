package com.coreydowning.ktorbasic.vehicles

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route


@KtorExperimentalLocationsAPI
fun Route.vehicles(service: VehicleService) {
    get<Vehicles.Index> { index ->
        call.respond(service.index(index))
    }
    post<Vehicles.Create> {
        val vehicle = call.receive<Vehicle>()
        service.create(vehicle)
        call.respond(HttpStatusCode.Created, vehicle)
    }
    get<Vehicles.ByOwner> { byOwner ->
        call.respond(service.getByOwner(byOwner))
    }
}

@KtorExperimentalLocationsAPI
@Location("/vehicles")
class Vehicles {
    @Location("/")
    data class Index(val page: Int = 1, val count: Int = 10)

    @Location("/")
    class Create

    @Location("/{owner}")
    data class ByOwner(val owner: String)
}
