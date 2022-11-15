package com.derekbearded.platformacme.extensions

/**
 * For simplicity, I am assuming "Y" is not a vowel; however, this is technically incorrect since
 * "Y" is often used as a vowel under specific circumstances as discussed on [Merriam-Webster][https://www.merriam-webster.com/words-at-play/why-y-is-sometimes-a-vowel-usage#:~:text=Y%20is%20considered%20to%20be,a%20syllable%3A%20system%2C%20borborygmus.]
 * Proper consideration of "Y's" vowel-ness would likely require a English Lexical plugin
 */
fun String.numVowels(): Int = this.lowercase().fold(0) { acc, c ->
    when (c) {
        'a', 'e', 'i', 'o', 'u' -> acc + 1
        else -> acc
    }
}

/**
 * Checking against the actual consonants. This may be more expensive than other
 * ways (this.removeWhitespace().length - this.removeWhitespace().numVowels()) but
 * it prevents false positives with punctuation or other non-consonant and non-vowel
 * characters
 */
fun String.numConsonants(): Int = this.lowercase().fold(0) { acc, c ->
    when (c) {
        'b', 'c', 'd', 'f', 'g', 'h',
        'j', 'k', 'l', 'm', 'n', 'p',
        'q', 'r', 's', 't', 'v', 'w',
        'x', 'y', 'z',
        -> acc + 1
        else -> acc
    }
}

/**
 * Unsafe implementation that attempts to extract the street name from an incomplete Address.
 * This is a hack to extract the data needed for this project, but wouldn't be an acceptable
 * approach in production.
 *
 * In a production environment, the addresses should be validated (backend) and provided to
 * mobile as such.
 *
 * Using the address description from [ArcGIS][https://desktop.arcgis.com/en/arcmap/latest/manage-data/geocoding/what-is-an-address.htm]
 * to extract just the street name from one of the addresses in the test data set. e.g.
 * "Aufderhar River" from "8725 Aufderhar River Suite 859".
 *
 * This approach assumes all addresses in test data set follows this pattern
 * (house number)(Street Name)(optional: sub-unit)
 * e.g.
 * (79035)(Shanna Light)(Apt. 322)
 */
fun String.streetName(): String {
    var subunitIndex = this.length // safe max value
    return this.trim().split("\\s".toRegex()).foldIndexed("") { index, acc, s ->
        if (index == 0) { // skip house number
            acc
        } else if (index >= subunitIndex) { // ignore anything sub-unit and after
            acc
        } else if (s.lowercase() == "apt." || s.lowercase() == "suite") { // id the sub-unit
            subunitIndex = index
            acc
        } else { // everything else accounted for, so this is the street name
            "$acc $s"
        }
    }.trim()
}