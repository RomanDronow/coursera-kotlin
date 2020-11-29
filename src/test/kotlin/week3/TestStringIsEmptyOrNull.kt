package week3

import isEmptyOrNull

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TestStringIsEmptyOrNull {
    @Test
    fun test01() {
        val s: String? = null
        assertTrue(s.isEmptyOrNull())
    }

    @Test
    fun test02() {
        val s: String? = ""
        assertTrue(s.isEmptyOrNull())
    }

    @Test
    fun test03() {
        val s: String? = "   "
        assertFalse(s.isEmptyOrNull())
    }
}