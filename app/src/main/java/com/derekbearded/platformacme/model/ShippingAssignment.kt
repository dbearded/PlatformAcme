package com.derekbearded.platformacme.model

/**
 * A pairing between a Shipment and a Driver
 */
data class ShippingAssignment(val shipment: Shipment, val driver: Driver)