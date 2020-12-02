package week3

fun List<Int>.allNonZero() = all { it != 0 }
fun List<Int>.allNonZero1() = none { it == 0 }
fun List<Int>.allNonZero2() = !any { it == 0 }

fun List<Int>.containsZero() =  any { it == 0}
fun List<Int>.containsZero1() =  !all { it != 0 }
fun List<Int>.containsZero2() =  !none { it == 0 }
