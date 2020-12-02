package util

infix fun Boolean.eq(value: Boolean): Unit = if (this == value) println(value) else println(!value)
