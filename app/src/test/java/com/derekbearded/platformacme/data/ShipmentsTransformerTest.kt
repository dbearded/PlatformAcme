package com.derekbearded.platformacme.data

import com.derekbearded.platformacme.shipmentsDataTest
import org.junit.Test

class ShipmentsTransformerTest {

    @Test
    fun testTransformer() {
        val transformer = ShipmentsTransformer()
        val result = transformer.transform(shipmentsDataTest)!!

        val shipments = result.first
        val drivers = result.second

        assert(shipments.isNotEmpty())
        assert(drivers.isNotEmpty())
    }
}