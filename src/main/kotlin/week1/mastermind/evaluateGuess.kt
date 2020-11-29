package week1.mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)


fun evaluateGuess(secret: String, guess: String): Evaluation {
    val rightPosition = countRightPosition(secret, guess)
    val wrongPosition = countWrongPosition(secret, guess)
    return Evaluation(rightPosition, wrongPosition)
}

private fun countRightPosition(secret: String, guess: String): Int {
    var rightPosition = 0
    for (i in secret.indices) {
        if (secret[i] == guess[i]) {
            rightPosition++
        }
    }
    return rightPosition
}

private fun countWrongPosition(secret: String, guess: String): Int {
    var wrongPosition = 0

    val onlyWrongSecret = mutableListOf<Char>()
    val onlyWrongGuess = mutableListOf<Char>()

    for (i in secret.indices) {
        if (secret[i] != guess[i]) {
            onlyWrongSecret.add(secret[i])
            onlyWrongGuess.add(guess[i])
        }
    }

    val evaluatedChars = mutableListOf<Char>()
    for (letter in guess) {
        if (!evaluatedChars.contains(letter)) {
            val letterInWrongSecret = countLetterEntry(onlyWrongSecret, letter)
            val letterInWrongGuess = countLetterEntry(onlyWrongGuess, letter)
            if (letterInWrongSecret < letterInWrongGuess) {
                wrongPosition += letterInWrongSecret
            } else {
                wrongPosition += letterInWrongGuess
            }
            evaluatedChars.add(letter)
        }
    }
    return wrongPosition
}


private fun countLetterEntry(letters: MutableList<Char>, letter: Char): Int {
    var count = 0
    for (char in letters) {
        if (char == letter) count++
    }
    return count
}