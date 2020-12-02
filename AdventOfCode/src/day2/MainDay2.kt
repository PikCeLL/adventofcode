package day2

val lineRegex = Regex("([0-9]*)-([0-9]*) ([a-z]): ([A-z]*)", RegexOption.MULTILINE)

fun main() {
    val fileContent: String = {}::class.java.getResource("/day2.txt").readText()
    println("Rule 1 : " + countOkPwd(fileContent) { isPwdOk1(it) })
    println("Rule 2 : " + countOkPwd(fileContent) { isPwdOk2(it) })
}

fun countOkPwd(list: String, rule: (MatchResult) -> Boolean): Int {
    return lineRegex.findAll(list).count { rule(it) }
}

fun isPwdOk1(lineMatchResult: MatchResult): Boolean {
    val (min, max, ruleLetter, pwd) = lineMatchResult.destructured
    val countRange = IntRange(min.toInt(), max.toInt())
    val letter = ruleLetter.single()

    return countRange.contains(pwd.count { it == letter })
}

fun isPwdOk2(lineMatchResult: MatchResult): Boolean {
    val (int1, int2, ruleLetter, pwd) = lineMatchResult.destructured
    val pos1 = int1.toInt()
    val pos2 = int2.toInt()
    val letter = ruleLetter.single()

    return (pwd[pos1-1] == letter) xor (pwd[pos2-1] == letter)
}