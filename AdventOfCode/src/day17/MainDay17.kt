package day17

import java.lang.Integer.min
import kotlin.math.abs
import kotlin.math.max

data class Coord(val x: Int, val y: Int, val z: Int) {
    infix fun isAdjacent(coord: Coord): Boolean {
        return this != coord && abs(this.x - coord.x) <= 1 &&
                abs(this.y - coord.y) <= 1 &&
                abs(this.z - coord.z) <= 1
    }
}

data class HyperCoord(val x: Int, val y: Int, val z: Int, val w: Int) {
    infix fun isAdjacent(coord: HyperCoord): Boolean {
        return this != coord && abs(this.x - coord.x) <= 1 &&
                abs(this.y - coord.y) <= 1 &&
                abs(this.z - coord.z) <= 1 &&
                abs(this.w - coord.w) <= 1
    }
}

fun main() {
    val fileContent: String = {}::class.java.getResource("/day17.txt").readText().trim()

    val start = System.currentTimeMillis()
    println("Puzzle 1 : " + getActivatedCubes(fileContent, 6).size)
    println("Puzzle 2 : " + getActivatedHyperCubes(fileContent, 6).size)
    println("ExecTime : " + (System.currentTimeMillis() - start) / 1000.0)
}

fun getActivatedCubes(input: String, iterations: Int): Set<Coord> {
    var activeCubes = input
            .lineSequence()
            .foldIndexed(setOf<Coord>()) { idy, cubes, line -> cubes.plus(line.mapIndexed { idx, c -> if (c == '#') Coord(idx, idy, 0) else null }.filterNotNull()) }

    for (i in 1..iterations) {
        val dimension = activeCubes.fold(Pair(Coord(0, 0, 0), Coord(0, 0, 0))) { acc, cube ->
            Pair(Coord(min(acc.first.x, cube.x),
                    min(acc.first.y, cube.y),
                    min(acc.first.z, cube.z)),
                    Coord(max(acc.second.x, cube.x),
                            max(acc.second.y, cube.y),
                            max(acc.second.z, cube.z)))
        }

        val bigDim = Pair(Coord(dimension.first.x - 1, dimension.first.y - 1, dimension.first.z - 1),
                Coord(dimension.second.x + 1, dimension.second.y + 1, dimension.second.z + 1))

        activeCubes = (bigDim.first.x..bigDim.second.x).flatMap { x ->
            (bigDim.first.y..bigDim.second.y).flatMap { y ->
                (bigDim.first.z..bigDim.second.z).mapNotNull { z ->
                    val cube = Coord(x, y, z)
                    val adjacentActiveCubes = activeCubes.filter { cube isAdjacent it }.count()
                    if ((adjacentActiveCubes == 3) || (adjacentActiveCubes == 2 && activeCubes.contains(cube))) {
                        cube
                    } else {
                        null
                    }
                }
            }
        }.toSet()
    }
    return activeCubes
}

// Ctrl-C Ctrl-V o/
fun getActivatedHyperCubes(input: String, iterations: Int): Set<HyperCoord> {
    var activeCubes = input
            .lineSequence()
            .foldIndexed(setOf<HyperCoord>()) { idy, cubes, line -> cubes.plus(line.mapIndexed { idx, c -> if (c == '#') HyperCoord(idx, idy, 0, 0) else null }.filterNotNull()) }

    for (i in 1..iterations) {
        val dimension = activeCubes.fold(Pair(HyperCoord(0, 0, 0, 0), HyperCoord(0, 0, 0, 0))) { acc, cube ->
            Pair(HyperCoord(min(acc.first.x, cube.x),
                    min(acc.first.y, cube.y),
                    min(acc.first.z, cube.z),
                    min(acc.first.w, cube.w)),
                    HyperCoord(max(acc.second.x, cube.x),
                            max(acc.second.y, cube.y),
                            max(acc.second.z, cube.z),
                            max(acc.second.w, cube.w)))
        }

        val bigDim = Pair(HyperCoord(dimension.first.x - 1, dimension.first.y - 1, dimension.first.z - 1, dimension.first.w - 1),
                HyperCoord(dimension.second.x + 1, dimension.second.y + 1, dimension.second.z + 1, dimension.second.w + 1))

        activeCubes = (bigDim.first.x..bigDim.second.x).flatMap { x ->
            (bigDim.first.y..bigDim.second.y).flatMap { y ->
                (bigDim.first.z..bigDim.second.z).flatMap { z ->
                    (bigDim.first.w..bigDim.second.w).mapNotNull { w ->
                        val cube = HyperCoord(x, y, z, w)
                        val adjacentActiveCubes = activeCubes.filter { cube isAdjacent it }.count()
                        if ((adjacentActiveCubes == 3) || (adjacentActiveCubes == 2 && activeCubes.contains(cube))) {
                            cube
                        } else {
                            null
                        }
                    }
                }
            }
        }.toSet()
    }
    return activeCubes
}
