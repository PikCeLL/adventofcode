package day5

val planeLog2 = Pair(7,3)

fun main() {
    val fileContent: String = {}::class.java.getResource("/day5.txt").readText().trim()

    println("Puzzle 1 : " + fileContent.lineSequence().maxOf { getSeatId(getSeat(it, planeLog2)) })
    println("Puzzle 2 : " + findMySeat(fileContent.lineSequence().map { getSeatId(getSeat(it, planeLog2)) }.sorted()))
}

fun findMySeat(usedIds: Sequence<Int>): Int {
    val iter = usedIds.iterator()
    var curId = iter.next()
    while (iter.hasNext()) {
        val tmp = iter.next()
        if(curId+2==tmp) return curId+1 else curId = tmp
    }
    return -1
}

fun getSeat(line: String, plane: Pair<Int,Int>): Pair<Int,Int> {
    val binaryLine = line.replace('F', '0').replace('B','1').replace('L','0').replace('R','1')
    return Pair(Integer.parseInt(binaryLine.substring(0, plane.first),2),Integer.parseInt(binaryLine.substring(plane.first),2))
}

fun getSeatId(seat: Pair<Int,Int>):Int {
    return seat.first*8+seat.second
}