package identifier

import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse

class TestIdentifier {
    @Test
    fun test01() = assertTrue(isValidIdentifier("name"))

    @Test
    fun test02() = assertTrue(isValidIdentifier("_name"))

    @Test
    fun test03() = assertTrue(isValidIdentifier("_12"))

    @Test
    fun test04() = assertFalse(isValidIdentifier(""))

    @Test
    fun test05() = assertFalse(isValidIdentifier("012"))

    @Test
    fun test06() = assertFalse(isValidIdentifier("no$"))
}

