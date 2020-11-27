package week1.mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun charMap(str: String): HashMap<Char, Int> {
    val map = HashMap<Char, Int>()
    str.forEach { char: Char ->
        if (!map.containsKey(char)) {
            map[char] = 1
        } else {
            val prev = map[char]!!
            map[char] = prev + 1
        }
    }
    return map
}

fun HashMap<Char, Int>.changeCount(char: Char) {
    val prev = this[char]!!
    this[char] = prev - 1
    if(this[char]!! < 0) throw Exception("Size is negative, boi")
}

fun HashMap<Char,Int>.checkLetterAvailable(char: Char): Boolean {
    return this[char]!! > 0
}

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var right = 0
    var wrong = 0
    val secretMap = charMap(secret)
    guess.forEachIndexed { index, char ->
        if (char == secret[index]) {
            right++
            secretMap.changeCount(char)
        }
        if (secret.contains(char) && secret[index] != char && secretMap.checkLetterAvailable(char)) {
            wrong++
            secretMap.changeCount(char)
        }
    }
    return Evaluation(right, wrong)
}