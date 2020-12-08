package day8

val instructRegex = Regex("(\\w{3}) (\\+\\d+|\\-\\d+)")

fun main() {
    val fileContent: String = {}::class.java.getResource("/day8.txt").readText().trim()

    println("Puzzle 1 : " + run(fileContent, instructRegex).first)
    println("Puzzle 2 : " + fix(fileContent, instructRegex))
}

fun run(input: String, regex: Regex): Pair<Int,Int> {
    val instructSeq = input.lineSequence()
    val executedLines = HashSet<Int>()
    var curLine = 0
    var accumulator = 0
    while(!executedLines.contains(curLine) && instructSeq.elementAtOrNull(curLine) != null) {
        executedLines.add(curLine)
        var delta = 1
        val regexRes = regex.find(instructSeq.elementAt(curLine))
        if ((regexRes != null)) {
            val (instruction, value) = regexRes.destructured
            when(instruction) {
                "acc" -> accumulator += value.toInt()
                "jmp" -> delta = value.toInt()
            }
        }
        curLine += delta
    }
    return Pair(accumulator, curLine)
}

fun fix(input: String, regex: Regex): Int {
    return input.lines().mapIndexed { idx, line ->
        val fileCopy = input.lines().toMutableList()
        when {
            line.contains("nop") -> {
                fileCopy[idx] = fileCopy[idx].replace("nop", "jmp")
                run(fileCopy.reduce {s1,s2 ->s1+"\n"+s2 }, regex)
            }
            line.contains("jmp") -> {
                fileCopy[idx] = fileCopy[idx].replace("jmp", "nop")
                run(fileCopy.reduce {s1,s2 ->s1+"\n"+s2 }, regex)
            }
            else -> Pair(-1,-1)
        }
    }.find { it.second==input.lines().size }!!.first
}