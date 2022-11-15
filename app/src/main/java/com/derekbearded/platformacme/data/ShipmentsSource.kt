package com.derekbearded.platformacme.data

import android.content.Context
import java.io.BufferedReader

/**
 * Specification for the source of the Shipments information.
 *
 * This abstraction is helpful for injecting a fake in the test directory as well as
 * single responsibility principle
 */
interface ShipmentsSource {
    fun getShipmentsRawData(): String
}

private const val JSON_FILE = "shipment_driver_order.json"

class ShipmentsSourceLocal(private val context: Context) : ShipmentsSource {
    override fun getShipmentsRawData(): String {
        val inputStream = context.assets.open(JSON_FILE)
        return inputStream.bufferedReader().use(BufferedReader::readText)
    }
}