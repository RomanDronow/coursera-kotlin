package rationals

import java.math.BigInteger

data class Rational(var numerator: BigInteger, var denominator: BigInteger) : Comparable<Rational> {
    init {
        if (denominator.equals(0)) {
            throw IllegalArgumentException("Denominator cannot be equals to zero")
        }

        if (denominator.compareTo(BigInteger.ZERO) == -1) {
            numerator *= BigInteger.valueOf(-1)
            denominator *= BigInteger.valueOf(-1)
        }
        val (num, den) = normalize(numerator, denominator)
        this.numerator = num
        this.denominator = den
    }

    constructor() : this(0, 1)

    constructor(numerator: Long, denominator: Long) : this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator))

    operator fun plus(other: Rational): Rational {
        val numerator = this.numerator * other.denominator + other.numerator * denominator
        val denominator = this.denominator * other.denominator
        return Rational(numerator, denominator)

    }

    operator fun minus(other: Rational): Rational {
        val numerator = this.numerator * other.denominator - other.numerator * denominator
        val denominator = this.denominator * other.denominator
        return Rational(numerator, denominator)

    }

    operator fun plusAssign(other: Rational) {
        this.numerator = this.numerator * other.denominator + other.numerator * denominator
        this.denominator = this.denominator * other.denominator
    }

    operator fun minusAssign(other: Rational) {
        this.numerator = this.numerator * other.denominator - other.numerator * denominator
        this.denominator = this.denominator * other.denominator
    }

    operator fun times(other: Rational): Rational {
        return Rational(this.numerator * other.numerator, this.denominator * other.denominator)
    }

    operator fun div(other: Rational): Rational {
        return this * Rational(other.denominator, other.numerator)
    }

    operator fun timesAssign(other: Rational) {
        this.numerator = this.numerator * other.numerator
        this.denominator = this.denominator * other.denominator
    }

    operator fun divAssign(other: Rational) {
        this.numerator = this.numerator * other.denominator
        this.denominator = this.denominator * other.numerator
    }

    operator fun unaryMinus(): Rational {
        return Rational(this.numerator * BigInteger.valueOf(-1), this.denominator)
    }

    override fun toString(): String {
        if (denominator.equals(BigInteger.ONE)) return "$numerator"
        return "$numerator/$denominator"
    }

    override operator fun compareTo(other: Rational): Int {
        val left = this.numerator * other.denominator
        val right = other.numerator * denominator
        if (left > right) return 1
        if (right > left) return -1
        return 0
    }

    operator fun rangeTo(other: Rational): ClosedRange<Rational> {
        return object : ClosedRange<Rational> {
            override val endInclusive: Rational = other
            override val start: Rational = this@Rational
        }
    }
}

private fun normalize(one: BigInteger, another: BigInteger): Pair<BigInteger, BigInteger> {
    fun euclid(a: BigInteger, b: BigInteger): BigInteger {
        var copyA = a
        var copyB = b
        while (copyB != BigInteger.ZERO) {
            val tmp = copyA % copyB
            copyA = copyB
            copyB = tmp
        }
        return copyA
    }

    val absOne = bigIntAbs(one)
    val absAnother = bigIntAbs(another)

    val gcd = if (absOne > absAnother) euclid(absOne, absAnother) else euclid(absAnother, absOne)
    return Pair(one / gcd, another / gcd)
}

infix fun Number.divBy(other: Number): Rational {
    if (!checkNumber(this) && !checkNumber(other)) {
        throw Exception("Floating points values are forbidden")
    }
    return Rational(this.toLong(), other.toLong())
}

fun String.toRational(): Rational {
    if (!this.contains('/')) return Rational(this.toBigInteger(), BigInteger.ONE)
    val (numerator, denominator) = this.split('/')
    return Rational(numerator.toBigInteger(), denominator.toBigInteger())
}

fun bigIntAbs(value: BigInteger): BigInteger {
    return if (value.compareTo(BigInteger.ZERO) == -1) value * BigInteger.valueOf(-1) else value
}

private fun checkNumber(number: Number): Boolean {
    return when (number) {
        is Double -> false
        is Float -> false
        is Long -> true
        is Int -> true
        is BigInteger -> true
        else -> false
    }
}