package week3

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TestInterchangeablePredicates {
    private val list1 = listOf(1, 2, 3)
    private val list2 = listOf(0, 1, 2)

    @Test
    fun test01() {
        assertTrue(list1.allNonZero())
    }

    @Test
    fun test02() {
        assertTrue(list1.allNonZero1())
    }

    @Test
    fun test03() {
        assertTrue(list1.allNonZero2())
    }

    @Test
    fun test04() {
        assertFalse(list1.containsZero())
    }

    @Test
    fun test05() {
        assertFalse(list1.containsZero1())
    }

    @Test
    fun test06() {
        assertFalse(list1.containsZero2())
    }

    @Test
    fun test07() {
        assertFalse(list2.allNonZero())
    }

    @Test
    fun test08() {
        assertFalse(list2.allNonZero1())
    }

    @Test
    fun test09() {
        assertFalse(list2.allNonZero2())
    }

    @Test
    fun test10() {
        assertTrue(list2.containsZero())
    }

    @Test
    fun test11() {
        assertTrue(list2.containsZero1())
    }

    @Test
    fun test12() {
        assertTrue(list2.containsZero2())
    }
}