package com.derekbearded.platformacme.math

import blogspot.software_and_algorithms.stern_library.optimization.HungarianAlgorithm
import com.derekbearded.platformacme.data.ShipmentsProvider
import com.derekbearded.platformacme.data.ShipmentsSource
import com.derekbearded.platformacme.shipmentsDataTest
import com.derekbearded.platformacme.topsecret.suitabilityScore
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

class HungarianAlgorithmTest {

    @Test
    fun testHungarianAlgorithmLibrary() {
        val matrix = arrayOf(
            doubleArrayOf(70.0, 40.0, 20.0, 55.0),
            doubleArrayOf(65.0, 60.0, 45.0, 90.0),
            doubleArrayOf(30.0, 45.0, 50.0, 75.0),
            doubleArrayOf(25.0, 30.0, 55.0, 40.0),)
        val hg = HungarianAlgorithm(matrix)
        val match = hg.execute();
        assertTrue(Arrays.equals(intArrayOf(2, 1, 0, 3), match))
        assertEquals(150.0, computeCost(matrix, match), 0.0000001)
    }

    /**
     * The Hungarian Algorithm looks for minimum be default. For maximum cost, there are 2 general ways
     * 1. Negate the cost matrix (if the algorithm supports this)
     * 2. Adjust the cost matrix by subtracting every value from the max cost ([i][j] = max - [i][j]
     *
     * This test checks the library to verify that either approach works. Negating the cost is easier
     */
    @Test
    fun testMaxHungarianAlgorithmLibrary() {
        val matrixNeg = arrayOf(
            doubleArrayOf(-70.0, -40.0, -20.0, -55.0),
            doubleArrayOf(-65.0, -60.0, -45.0, -90.0),
            doubleArrayOf(-30.0, -45.0, -50.0, -75.0),
            doubleArrayOf(-25.0, -30.0, -55.0, -40.0),)
        val hgNeg = HungarianAlgorithm(matrixNeg)
        val matchNeg = hgNeg.execute();
        assertTrue(Arrays.equals(intArrayOf(0, 3, 1, 2), matchNeg))
        assertEquals(-260.0, computeCost(matrixNeg, matchNeg), 0.0000001)

        val matrixSub = arrayOf(
            doubleArrayOf(20.0, 50.0, 70.0, 35.0),
            doubleArrayOf(25.0, 30.0, 45.0, 0.0),
            doubleArrayOf(60.0, 45.0, 40.0, 15.0),
            doubleArrayOf(65.0, 60.0, 35.0, 50.0),)
        val hgSub = HungarianAlgorithm(matrixSub)
        val matchSub = hgSub.execute();
        assertTrue(Arrays.equals(intArrayOf(0, 3, 1, 2), matchSub))
        assertEquals(100.0, computeCost(matrixSub, matchSub), 0.0000001)

        assertTrue(Arrays.equals(matchNeg, matchSub))
    }

    companion object {
        private fun computeCost(matrix: Array<DoubleArray>, match: IntArray): Double {
            var result = 0.0
            val visited = hashSetOf<Int>()
            for (i in matrix.indices) {
                if (match[i] == -1) {
                    continue
                }
                if (!visited.add(match[i])) {
                    Assert.fail()
                }
                result += matrix[i][match[i]]
            }
            return result
        }
    }
}