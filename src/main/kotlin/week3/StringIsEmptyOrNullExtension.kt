fun String?.isEmptyOrNull(): Boolean{
    return this == null || this == ""
}

fun main(args: Array<String>) {
    val s1: String? = null
    val s2: String? = ""
    val s3 = "   "
    if (s1.isEmptyOrNull()) println(true) else println(false) // should be true
    if (s2.isEmptyOrNull()) println(true) else println(false) // should be true
    if (s3.isEmptyOrNull()) println(true) else println(false) // should be false
}