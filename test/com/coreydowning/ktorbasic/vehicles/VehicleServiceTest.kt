package com.coreydowning.ktorbasic.vehicles

import io.ktor.locations.KtorExperimentalLocationsAPI
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@KtorExperimentalLocationsAPI
class VehicleServiceTest {
    val mockVehicleRepository = mockk<VehicleRepository>()
    val subject = VehicleService(mockVehicleRepository)

    @Nested
    inner class Index {
        @BeforeEach
        fun beforeEach() {
            every { mockVehicleRepository.count() }.returns(3)
            every { mockVehicleRepository.all() }.returns(listOf(
                Vehicle("o1", "v1"),
                Vehicle("o2", "v2"),
                Vehicle("o3", "v3")
            ))
        }

        @Test
        fun `returns maximum of count vehicles`() {
            val result = subject.index(Vehicles.Index(count = 2))

            assertThat(result)
                .containsExactly(Vehicle("o1", "v1"), Vehicle("o2", "v2"))
        }

        @Test
        fun `returns less than count when fewer vehicles present`() {
            val result = subject.index(Vehicles.Index(count = 4))

            assertThat(result)
                .containsExactly(
                    Vehicle("o1", "v1"),
                    Vehicle("o2", "v2"),
                    Vehicle("o3", "v3")
                )
        }

        @Test
        fun `uses page to offset start of results`() {
            val result = subject.index(Vehicles.Index(page = 2, count = 1))

            assertThat(result)
                .containsExactly(Vehicle("o2", "v2"))
        }
    }
}