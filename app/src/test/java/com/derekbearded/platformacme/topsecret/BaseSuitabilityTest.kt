package com.derekbearded.platformacme.topsecret

import com.derekbearded.platformacme.model.Driver
import com.derekbearded.platformacme.model.Shipment
import org.junit.Assert.assertEquals
import org.junit.Test

const val DELTA = 0.00001

class BaseSuitabilityTest {

    /**
     * Several cases condensed into a single test for brevity
     */
    @Test
    fun baseSuitabilityTest() {
        // Null cases
        assertEquals(
            0.0,
            Shipment(address = "").suitabilityScore(Driver(name = "Everardo Welch")),
            DELTA
        )
        assertEquals(
            0.0,
            Shipment(address = "215 Osinski Manors").suitabilityScore(Driver(name = "")),
            DELTA
        )

        // Even street name length with common factor
        assertEquals(
            11.25,
            Shipment(address = "215 Osinski Manors").suitabilityScore(Driver(name = "Everardo Welch")),
            DELTA
        )

        // Even street name length without common factor
        assertEquals(
            3.0,
            Shipment(address = "215 Osinski Manors").suitabilityScore(Driver(name = "Kai")),
            DELTA
        )

        // Odd street name length with common factor
        assertEquals(
            6.0,
            Shipment(address = "7127 Kathlynnn Ferry").suitabilityScore(Driver(name = "Welch")),
            DELTA
        )

        // Odd street name length without common factor
        assertEquals(
            1.0,
            Shipment(address = "7127 Kathlyn Ferry").suitabilityScore(Driver(name = "Kai")),
            DELTA
        )
    }
}