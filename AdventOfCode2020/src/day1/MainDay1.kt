package day1

fun main() {
    print("Target?")
    val sum: Int = Integer.decode(readLine())
    print(result(sum))
}

fun result(sum: Int): Int {
    val result: Int
    val fileContent: String = {}::class.java.getResource("/day1.txt").readText()
    val expenses: List<Int> = fileContent.lineSequence().map { line -> Integer.decode(line) }.toList()

    expenses.forEach {
        expense -> expenses.forEach {
            expense2 -> expenses.forEach {
                when(expense + expense2 + it) {
                    sum -> return expense * expense2 * it
                }
            }
        }
    }
    return -1
}