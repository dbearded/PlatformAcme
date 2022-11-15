package com.derekbearded.platformacme.extensions

import org.junit.Assert.assertEquals
import org.junit.Test

class _StringTest {

    /**
     * Several cases condensed into a single test for brevity
     */
    @Test
    fun numberOfVowels() {
        assertEquals(5, "aeiou".numVowels())
        assertEquals(5, "aeiouy".numVowels())
        assertEquals(2, "aa".numVowels())
        assertEquals(0, "s".numVowels())
        assertEquals(0, ",".numVowels())
    }

    /**
     * Several cases condensed into a single test for brevity
     */
    @Test
    fun numberOfConsonants() {
        assertEquals(3, "bcd".numConsonants())
        assertEquals(3, "abcd".numConsonants())
        assertEquals(2, "bb".numConsonants())
        assertEquals(0, "a".numConsonants())
        assertEquals(0, ",".numConsonants())
    }

    /**
     * Several cases condensed into a single test for brevity
     */
    @Test
    fun streetName() {
        assertEquals("Osinski Manors", "215 Osinski Manors".streetName())
        assertEquals("Marvin Stravenue", "9856 Marvin Stravenue".streetName())
        assertEquals("Volkman Garden", "63187 Volkman Garden Suite 447".streetName())
        assertEquals("Adolf Island", "1797 Adolf Island Apt. 744".streetName())
    }
}