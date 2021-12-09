fun main() {

    fun isLowPoint(x: Int, y: Int, points: List<List<Int>>): Boolean {
        val pointsToBeHigher = when {
            y == 0 && x == 0 -> listOf(points[y][x + 1], points[y + 1][x])
            y == 0 && (x == points[y].size - 1) -> listOf(points[y][x - 1], points[y + 1][x])
            (y == points.size - 1) && x == 0 -> listOf(points[y - 1][x], points[y][x + 1])
            (y == points.size - 1) && (x == points[y].size - 1) -> listOf(points[y][x - 1], points[y - 1][x])
            y == 0 -> listOf(points[y][x - 1], points[y][x + 1], points[y + 1][x])
            x == 0 -> listOf(points[y - 1][x], points[y + 1][x], points[y][x + 1])
            y == points.size - 1 -> listOf(points[y][x - 1], points[y][x + 1], points[y - 1][x])
            x == points[y].size - 1 -> listOf(points[y - 1][x], points[y + 1][x], points[y][x - 1])
            else -> listOf(points[y - 1][x], points[y][x - 1], points[y + 1][x], points[y][x + 1])
        }

        return pointsToBeHigher.all { it > points[y][x] }
    }

    fun part1(input: List<String>): Int {
        val points = input.map { it.trim().toCharArray().map { it.toString().toInt() } }
        var sum = 0
        var count = 0
        for (y in points.indices) {
            for (x in points[y].indices) {
                if (isLowPoint(x, y, points)) {
                    count++
                    sum += 1 + points[y][x]
                }
            }
        }
        return sum
    }

    fun surroundingPoints(x: Int, y: Int, points: List<List<Int>>): List<Pair<Int, Int>> {
        return when {
            y == 0 && x == 0 -> listOf(Pair(y, x + 1), Pair(y + 1, x))
            y == 0 && (x == points[y].size - 1) -> listOf(Pair(y, x - 1), Pair(y + 1, x))
            (y == points.size - 1) && x == 0 -> listOf(Pair(y - 1, x), Pair(y, x + 1))
            (y == points.size - 1) && (x == points[y].size - 1) -> listOf(Pair(y, x - 1), Pair(y - 1, x))
            y == 0 -> listOf(Pair(y, x - 1), Pair(y, x + 1), Pair(y + 1, x))
            x == 0 -> listOf(Pair(y - 1, x), Pair(y + 1, x), Pair(y, x + 1))
            y == points.size - 1 -> listOf(Pair(y, x - 1), Pair(y, x + 1), Pair(y - 1, x))
            x == points[y].size - 1 -> listOf(Pair(y - 1, x), Pair(y + 1, x), Pair(y, x - 1))
            else -> listOf(Pair(y - 1, x), Pair(y, x - 1), Pair(y + 1, x), Pair(y, x + 1))
        }
    }

    fun findBasin(
        x: Int,
        y: Int,
        points: List<List<Int>>,
        basin: MutableSet<Pair<Int, Int>>
    ): MutableSet<Pair<Int, Int>> {

        basin.add(Pair(y, x))

        val surroundingPoints = surroundingPoints(x, y, points)
        for (point in surroundingPoints.filterNot { basin.contains(it) }) {
            if (points[point.first][point.second] > points[y][x] && points[point.first][point.second] != 9) {
                findBasin(point.second, point.first, points, basin)
            }
        }

        return basin
    }


    fun part2(input: List<String>): Int {
        val points = input.map { it.trim().toCharArray().map { it.toString().toInt() } }
        val basins = mutableListOf<Set<Pair<Int, Int>>>()
        for (y in points.indices) {
            for (x in points[y].indices) {
                if (isLowPoint(x, y, points)) {
                    basins.add(findBasin(x, y, points, mutableSetOf()))
                }
            }
        }

        var mul = 1
        for(num in  basins.map { it.size }.sortedDescending().take(3)) mul*= num
        return mul
    }

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
