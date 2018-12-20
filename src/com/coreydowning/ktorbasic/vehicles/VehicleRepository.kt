package com.coreydowning.ktorbasic.vehicles

class VehicleRepository {
    private val vehicles = mutableListOf<Vehicle>()

    fun all(): List<Vehicle> = vehicles

    fun create(vehicle: Vehicle) {
        vehicles.add(vehicle)
    }

    fun findByOwnerName(owner: String): Vehicle {
        return vehicles.first { it.owner == owner }
    }

    fun count(): Int = vehicles.size
}