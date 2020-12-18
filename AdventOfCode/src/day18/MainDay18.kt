package day18

val isANumberRegex = Regex("\\d+")

enum class Operation {
    SUM, PRODUCT
}

fun main() {
    val fileContent: String = {}::class.java.getResource("/day18.txt").readText().trim()

    val start = System.currentTimeMillis()
    println("Puzzle 1 : " + doHomeworks(fileContent).sum())
    println("Puzzle 2 : " + doAdvancedHomeworks(fileContent).sum())
    println("ExecTime : " + (System.currentTimeMillis() - start) / 1000.0)
}

fun doHomeworks(input: String): Sequence<Long> {
    return input.lineSequence().map { getResult(it.replace("\\s".toRegex(), "")) }
}

fun doAdvancedHomeworks(input: String): Sequence<Long> {
    return input.lineSequence().map { getResult(parenthesisSums(it.replace("\\s".toRegex(), ""))) }
}

fun parenthesisSums(expression: String): String {
    val listExpr = expression.toMutableList()
    var i = 0
    while (i <= listExpr.lastIndex) {
        if (listExpr[i] == '+') {
            var op = 0
            for (j in i - 1 downTo 0 step 1) {
                when {
                    listExpr[j] == ')' -> ++op
                    listExpr[j] == '(' -> --op
                }
                if (op == 0) {
                    listExpr.add(j, '(')
                    break
                }
            }
            ++i
            op = 0
            for (j in (i + 1)..listExpr.lastIndex) {
                when {
                    listExpr[j] == '(' -> ++op
                    listExpr[j] == ')' -> --op
                }
                if (op == 0) {
                    listExpr.add(j + 1, ')')
                    break
                }
            }
        }
        ++i
    }
    return listExpr.joinToString("")
}

fun getResult(expression: String): Long {
    var res = 0L
    var lastOp = Operation.SUM
    var i = 0

    while (i <= expression.lastIndex) {
        when {
            expression[i] == '(' -> {
                val endParIdx = findMatchingParIdx(expression, i)
                res = applyLastOp(lastOp, res, getResult(expression.substring(i + 1, endParIdx)))
                i = endParIdx
            }
            expression[i].isDigit() -> res = applyLastOp(lastOp, res, Character.getNumericValue(expression[i]).toLong())
            expression[i] == '+' -> lastOp = Operation.SUM
            expression[i] == '*' -> lastOp = Operation.PRODUCT
        }
        ++i
    }
    return res
}

fun findMatchingParIdx(condensedExpr: String, parIdx: Int): Int {
    var op = 1
    for (i in parIdx + 1..condensedExpr.lastIndex) {
        when (condensedExpr[i]) {
            '(' -> ++op
            ')' -> --op
        }
        if (op == 0) {
            return i
        }
    }
    throw UnsupportedOperationException("PAS DE PARENTHESE FERMANTE, FUCK")
}

fun applyLastOp(lastOp: Operation, left: Long, right: Long): Long {
    return if (lastOp == Operation.SUM) left + right else left * right
}