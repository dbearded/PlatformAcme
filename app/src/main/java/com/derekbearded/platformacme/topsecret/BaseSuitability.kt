package com.derekbearded.platformacme.topsecret

import com.derekbearded.platformacme.extensions.numConsonants
import com.derekbearded.platformacme.extensions.numVowels
import com.derekbearded.platformacme.extensions.streetName
import com.derekbearded.platformacme.math.gcd
import com.derekbearded.platformacme.model.Driver
import com.derekbearded.platformacme.model.Shipment

/**
 * Being a little silly with the package name
 */

/**
 * Determine a Driver's suitability for a Shipment
 */
fun Shipment.suitabilityScore(driver: Driver): Double {
    val streetName = this.address.streetName()
    if (streetName == "") return 0.0
    val driverName = driver.name.trim()
    if (driverName == "") return 0.0

    var score: Double
    if (streetName.length % 2 == 0) {
        score = 1.5 * driverName.numVowels().toDouble()
    } else {
        score = 1.0 * driverName.numConsonants().toDouble()
    }

    if (streetName.length.gcd(driverName.length) > 1) {
        score *= 1.5
    }

    return score
}