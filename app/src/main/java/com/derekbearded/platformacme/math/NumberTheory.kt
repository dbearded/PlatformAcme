package com.derekbearded.platformacme.math

/**
 * Using Greatest Common Divisor for finding common factors between length of street name
 * and driver's name. This approach exits as soon as there's a factor, so it's fairly quick
 * and it also has small space usage.
 *
 * This will return 1 if no other common divisor and requires both numbers to be greater than 0.
 */
fun Int.gcd(other: Int): Int {
    assert(this > 0)
    assert(other > 0)
    var first = this
    var second = other
    while (first != second) {
        if (first > second) {
            first -= second
        } else {
            second -= first
        }
    }

    return first
}