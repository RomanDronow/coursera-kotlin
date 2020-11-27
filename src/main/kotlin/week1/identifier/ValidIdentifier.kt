package week1.identifier

fun isValidIdentifier(s: String): Boolean {
    if(s == "") return false
    if(s[0] != '_' && s[0] !in 'a'..'z') return false
    for (ch in s.substring(1 until s.length)) {
        when(ch){
            '_' -> {}
            in 'a'..'z' -> {}
            in '0'..'9' -> {}
            else -> return false
        }
    }
    return true
}