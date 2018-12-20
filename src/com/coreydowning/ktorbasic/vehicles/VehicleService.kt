package com.coreydowning.ktorbasic.vehicles

import io.ktor.locations.KtorExperimentalLocationsAPI
import kotlin.math.min

@KtorExperimentalLocationsAPI
class VehicleService(private val repository: VehicleRepository) {
    fun index(index: Vehicles.Index): List<Vehicle> {
        val pageZeroIndexed = index.page - 1
        val startIndex = pageZeroIndexed * index.count
        val stopIndex = min(startIndex + index.count, repository.count())
        return repository.all().subList(startIndex, stopIndex)
    }

    fun create(vehicle: Vehicle) = repository.create(vehicle)

    fun getByOwner(byOwner: Vehicles.ByOwner) = repository.findByOwnerName(byOwner.owner)
}