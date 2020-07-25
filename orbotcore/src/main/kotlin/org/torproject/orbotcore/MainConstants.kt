package org.torproject.orbotcore

// todo this was a carry over from java, currently this porting of this file is not very idiomatic
interface MainConstants {
    companion object {
        //EXIT COUNTRY CODES
        @JvmField
        val COUNTRY_CODES = arrayOf("DE", "AT", "SE", "CH", "IS", "CA", "US", "ES", "FR", "BG", "PL", "AU", "BR", "CZ", "DK", "FI", "GB", "HU", "NL", "JP", "RO", "RU", "SG", "SK")

        //path to check Tor against
        @JvmField
        val URL_TOR_CHECK = "https://check.torproject.org"

        @JvmField
        val URL_TOR_BRIDGES = "https://bridges.torproject.org/bridges"

        @JvmField
        val EMAIL_TOR_BRIDGES = "bridges@torproject.org"

        @JvmField
        val RESULT_CLOSE_ALL = 0
    }
}