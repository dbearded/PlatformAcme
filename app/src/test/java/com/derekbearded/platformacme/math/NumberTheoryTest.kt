package com.derekbearded.platformacme.math

import org.junit.Assert.assertEquals
import org.junit.Test

class NumberTheoryTest {

    /**
     * Several cases condensed into a single test for brevity
     */
    @Test
    fun greatestCommonDenominator() {
        assertEquals(1, 3.gcd(7))
        assertEquals(2, 2.gcd(2))
        assertEquals(3, 3.gcd(6))
        assertEquals(9, 81.gcd(153))
    }
}