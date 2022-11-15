package com.derekbearded.platformacme.data

import com.derekbearded.platformacme.model.Driver
import com.derekbearded.platformacme.model.Shipment
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.UnsupportedEncodingException

/**
 * Class that transforms the raw Shipments string to a Typed model
 */
class ShipmentsTransformer {

    fun transform(raw: String): Pair<List<Shipment>, List<Driver>>? {
        return try {
            val shipmentOrderData = JSONObject(raw)
            val shipmentArray = shipmentOrderData.getJSONArray("shipments")
            val shipmentList = mutableListOf<Shipment>()
            for (i in 0 until shipmentArray.length()) {
                val shipmentJson = shipmentArray[i] as String
                shipmentList.add(Shipment(address = shipmentJson))
            }

            val driverArray = shipmentOrderData.getJSONArray("drivers")
            val driverList = mutableListOf<Driver>()
            for (i in 0 until driverArray.length()) {
                val driverJson = driverArray[i] as String
                driverList.add(Driver(name = driverJson))
            }

            shipmentList to driverList
        } catch (e: JSONException) {
            null
        } catch (e: UnsupportedEncodingException) {
            null
        } catch (e: IOException) {
            null
        }
    }
}