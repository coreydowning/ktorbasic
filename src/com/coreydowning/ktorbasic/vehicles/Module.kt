package com.coreydowning.ktorbasic.vehicles

import io.ktor.locations.KtorExperimentalLocationsAPI
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

@KtorExperimentalLocationsAPI
val vehiclesModule = Kodein.Module(name = "vehicles") {
    bind<VehicleService>() with singleton {
        VehicleService(
            instance()
        )
    }
    bind<VehicleRepository>() with singleton { VehicleRepository() }
}


