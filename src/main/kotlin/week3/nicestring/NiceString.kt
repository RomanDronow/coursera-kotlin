package nicestring

fun String.isNice(): Boolean {
    var niceCount = 0
    if (checkRule1(this)) niceCount++
    if (checkRule2(this)) niceCount++
    if (checkRule3(this)) niceCount++
    return niceCount >= 2
}

private fun checkRule1(string: String): Boolean {
    val pairList = string.zipWithNext()
    return !pairList.contains('b' to 'u') && !pairList.contains('b' to 'a') && !pairList.contains('b' to 'e')
}

private fun checkRule2(string: String): Boolean {
    val vowelSet = setOf('a', 'e', 'i', 'o', 'u')
    var vowelCount = 0
    for (letter in string) {
        if (vowelSet.contains(letter)) vowelCount++
    }
    return vowelCount >= 3
}

private fun checkRule3(string: String): Boolean {
    val pairList = string.zipWithNext()
    for (pair in pairList) {
        if (pair.first == pair.second) return true
    }
    return false
}