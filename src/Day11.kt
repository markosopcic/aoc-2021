import java.math.BigInteger

fun getNeighboringIndexes(x: Int, y: Int, octopi: List<List<Int>>): List<Pair<Int, Int>> {
    val neighbors = mutableListOf<Pair<Int, Int>>()
    if (y > 0) {
        neighbors.add(Pair(y - 1, x))
    }
    if (y < octopi.size - 1) {
        neighbors.add(Pair(y + 1, x))
    }
    if (x > 0) {
        neighbors.add(Pair(y, x - 1))
    }
    if (x < octopi[y].size - 1) {
        neighbors.add(Pair(y, x + 1))
    }
    if (y < octopi.size - 1 && x < octopi[y].size - 1) {
        neighbors.add(Pair(y + 1, x + 1))
    }
    if (y > 0 && x < octopi[y].size - 1) {
        neighbors.add(Pair(y - 1, x + 1))
    }
    if (y > 0 && x > 0) {
        neighbors.add(Pair(y - 1, x - 1))
    }
    if (y < octopi.size - 1 && x > 0) {
        neighbors.add(Pair(y + 1, x - 1))
    }
    return neighbors
}


fun printMatrix(matrix: List<List<Int>>) {
    for (row in matrix) {
        println(row.joinToString(separator = ""))
    }

    println()
}

class OctopusBoard(val octopi: MutableList<MutableList<Int>>) {

    var flashed = 0

    fun step1() {
        for (y in octopi.indices) {
            for (x in octopi[y].indices) {
                octopi[y][x] += 1
            }
        }
    }

    fun step2(): Set<Pair<Int, Int>> {

        fun getFlashingOctopuses(octopi: List<List<Int>>): Set<Pair<Int, Int>> {
            val result = mutableSetOf<Pair<Int, Int>>()
            for (y in octopi.indices) {
                for (x in octopi[y].indices) {
                    if (octopi[y][x] > 9) {
                        result.add(Pair(y, x))
                    }
                }
            }
            return result
        }

        val flashed = mutableSetOf<Pair<Int, Int>>()

        var flashingOctopi = getFlashingOctopuses(octopi)

        while (flashingOctopi.any()) {
            this.flashed += flashingOctopi.filterNot { flashed.contains(it) }.size
            for (octopus in flashingOctopi) {
                flashed.add(octopus)
                val neighbors = getNeighboringIndexes(octopus.second, octopus.first, octopi)
                for (neighbour in neighbors) {
                    octopi[neighbour.first][neighbour.second] += 1
                }
            }

            flashingOctopi = getFlashingOctopuses(octopi).filterNot { flashed.contains(it) }.toSet()
        }

        return flashed
    }

    fun step3(allFlashes: Set<Pair<Int, Int>>) {
        for (octopus in allFlashes) {
            octopi[octopus.first][octopus.second] = 0
        }
    }

    fun work2(): Long {
        var step = 0L
        while(true) {

            printMatrix(octopi)
            step1()

            printMatrix(octopi)

            val allFlashed = step2()
            printMatrix(octopi)

            step3(allFlashed)
            printMatrix(octopi)

            step++

            if(allFlashed.size == octopi.size * octopi[0].size){
                return step
            }
        }
    }

    fun work() {

        for (i in 0 until 100) {
            printMatrix(octopi)
            step1()

            printMatrix(octopi)

            val allFlashed = step2()
            printMatrix(octopi)

            step3(allFlashed)
            printMatrix(octopi)
        }
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val octopi = input.map { it.trim().toCharArray().map { it.toString().toInt() }.toMutableList() }.toMutableList()
        val octopusBoard = OctopusBoard(octopi)

        octopusBoard.work()
        return octopusBoard.flashed
    }


    fun part2(input: List<String>): Long {
        val octopi = input.map { it.trim().toCharArray().map { it.toString().toInt() }.toMutableList() }.toMutableList()
        val octopusBoard = OctopusBoard(octopi)

        return octopusBoard.work2()
    }

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}
