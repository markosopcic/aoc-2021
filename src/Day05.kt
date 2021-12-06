import java.io.File
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {

        val knots = mutableMapOf<Pair<Int, Int>, Int>()

        for (line in input) {
            val parts = line.split("->").map { it.trim() }
            val (fromX, fromY) = parts[0].split(",").map { it.toInt() }
            val (toX, toY) = parts[1].split(",").map { it.toInt() }

            if (fromX == toX) {
                for (y in if (fromY <= toY) fromY..toY else toY..fromY) {
                    val pair = Pair(fromX, y)
                    knots[pair] = knots.getOrDefault(pair, 0) + 1
                }
            } else if (fromY == toY) {
                for (x in if (fromX <= toX) fromX..toX else toX..fromX) {
                    val pair = Pair(x, fromY)
                    knots[pair] = knots.getOrDefault(pair, 0) + 1
                }
            }

        }
        return knots.values.count { it >= 2 }
    }

    fun part2(input: List<String>): Int {
        val knots = mutableMapOf<Pair<Int, Int>, Int>()

        for (line in input) {
            val parts = line.split("->").map { it.trim() }
            val (fromX, fromY) = parts[0].split(",").map { it.toInt() }
            val (toX, toY) = parts[1].split(",").map { it.toInt() }

            if (fromX == toX) {
                for (y in if (fromY <= toY) fromY..toY else toY..fromY) {
                    val pair = Pair(fromX, y)
                    knots[pair] = knots.getOrDefault(pair, 0) + 1
                }
            } else if (fromY == toY) {
                for (x in if (fromX <= toX) fromX..toX else toX..fromX) {
                    val pair = Pair(x, fromY)
                    knots[pair] = knots.getOrDefault(pair, 0) + 1
                }
            } else {
                //for diagonal lines
                var counter = abs(fromX - toX)
                while(counter >= 0){
                    val nextX = if(fromX >= toX) fromX - counter else fromX + counter
                    val nextY = if(fromY >= toY) fromY - counter else fromY + counter
                    val pair = Pair(nextX, nextY)
                    knots[pair] = knots.getOrDefault(pair, 0) + 1
                    counter--
                }
            }

        }
        return knots.values.count { it >= 2 }
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
