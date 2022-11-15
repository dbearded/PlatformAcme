package com.derekbearded.platformacme.data

import com.derekbearded.platformacme.model.Driver
import com.derekbearded.platformacme.model.Shipment

/**
 * Provides the Shipments data as Typed models
 */
class ShipmentsProvider(private val source: ShipmentsSource) {

    // Not yet injecting because there isn't another variant of the transformer
    // pulled this functionality out for single responsibility
    private val transformer = ShipmentsTransformer()

    /**
     * Loads Shipment and Driver data from assets folder.
     *
     * @return Pair of List of List of Shipments and List of Drivers
     */
    fun fetchShipmentsData(): Pair<List<Shipment>, List<Driver>>? {
        val input = source.getShipmentsRawData()
        return transformer.transform(input)
    }
}