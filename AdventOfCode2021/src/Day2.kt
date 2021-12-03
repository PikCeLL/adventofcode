fun main() {
    Day2().printResults()
}

class Day2 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day2.txt"
    }

    override fun getResultPuzzle1(input: String): Int {
        return input.lineSequence().fold(Pair(0, 0)) { coord, line ->
            val displacementValue = line.split(" ")[1].toInt()
            when {
                line.startsWith("forward") -> Pair(coord.first + displacementValue, coord.second)
                line.startsWith("down") -> Pair(coord.first, coord.second + displacementValue)
                line.startsWith("up") -> Pair(coord.first, coord.second - displacementValue)
                else -> coord
            }
        }.let { it.first * it.second }
    }

    override fun getResultPuzzle2(input: String): Int {
        return input.lineSequence().fold(Triple(0, 0, 0)) { coord, line ->
            val displacementValue = line.split(" ")[1].toInt()
            when {
                line.startsWith("forward") -> Triple(
                    coord.first + displacementValue,
                    coord.second + (coord.third * displacementValue),
                    coord.third
                )
                line.startsWith("down") -> Triple(coord.first, coord.second, coord.third + displacementValue)
                line.startsWith("up") -> Triple(coord.first, coord.second, coord.third - displacementValue)
                else -> coord
            }
        }.let { it.first * it.second }
    }
}

