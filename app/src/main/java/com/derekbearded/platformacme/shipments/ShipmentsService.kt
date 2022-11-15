package com.derekbearded.platformacme.shipments

import blogspot.software_and_algorithms.stern_library.optimization.HungarianAlgorithm
import com.derekbearded.platformacme.data.ShipmentsProvider
import com.derekbearded.platformacme.model.Shipment
import com.derekbearded.platformacme.model.ShippingAssignment
import com.derekbearded.platformacme.topsecret.suitabilityScore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Computes the Shipping Assignments given the list of Addresses and Drivers
 */
class ShipmentsService(private val provider: ShipmentsProvider) {

    suspend fun fetchAssignments(): List<ShippingAssignment> = withContext(Dispatchers.IO) {
        val shipmentsData = provider.fetchShipmentsData() ?: return@withContext listOf()
        val shipments = shipmentsData.first
        val drivers = shipmentsData.second
        val costMatrix: Array<DoubleArray> = Array(drivers.size) { DoubleArray(shipments.size) }
        drivers.forEachIndexed { i, driver ->
            shipments.forEachIndexed { j, shipment ->
                /**
                 * The Hungarian Algorithm looks for minimum be default. For maximum cost, there are 2 general ways
                 * 1. Negate the cost matrix (if the algorithm supports this)
                 * 2. Adjust the cost matrix by subtracting every value from the max cost ([i][j] = max - [i][j]
                 *
                 * Use 1. here since it is easier. Verified in Unit Tests
                 */
                costMatrix[i][j] = -1.0 * shipment.suitabilityScore(driver)
            }
        }

        val hungarianAlgorithm = HungarianAlgorithm(costMatrix)
        val matches = hungarianAlgorithm.execute()

        val assignments = mutableListOf<ShippingAssignment>()
        matches.forEachIndexed { driverIdx, shipmentIdx ->
            val shipment = if (shipmentIdx == -1) {
                Shipment(address = "Check back again later.")
            } else {
                shipments[shipmentIdx]
            }
            assignments += ShippingAssignment(
                shipment = shipment,
                driver = drivers[driverIdx]
            )
        }

        assignments
    }
}