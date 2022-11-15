package com.derekbearded.platformacme.model

/**
 * Simplified model of a Driver with a simple name.
 * In a production environment, there should be a separate [Name]
 * data structure that properly accounts for all the components of a name:
 * first, middle, last, suffixes, double-barreled, etc. Or at least an
 * "accepted" type name per the account holder
 *
 * I thought about using (firstName: String, lastName: String) here to fit
 * the test data but it seemed sub-optimal since it's not complex enough to
 * handle all types of names (discussed above) and also, for this problem,
 * I'm only actually interested in the total length and individual characters
 * (not first name or last name).
 */
data class Driver(val name: String)