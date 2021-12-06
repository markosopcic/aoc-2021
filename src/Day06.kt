import java.io.File
import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): Int {
        val values = input.map { it.split(",").map { it.toInt() } }.flatten()

        var fish = values.toMutableList()

        for (i in 0 until 80) {
            val fishToAdd = fish.count { it == 0}
            fish = fish.map {
                when (it) {
                    0 -> 6
                    else -> it - 1
                }
            }.toMutableList()

            for(add in 0 until fishToAdd){
                fish.add(8)
            }
        }

        return fish.size
    }

    fun part2(input: List<String>): BigInteger {
        val values = input.map { it.split(",").map { it.toInt() } }.flatten()

        var willGenerate = mutableMapOf<Int,Long>()
        values.forEach {
            willGenerate[it] = willGenerate.getOrDefault(it, 0) + 1
        }
        for(i in 0 until 256){
            val newWillGenerate = mutableMapOf<Int,Long>()
            for((days, fish) in willGenerate){
                when(days){
                    0 ->{
                        newWillGenerate[8] = newWillGenerate.getOrDefault(8,0) + fish
                        newWillGenerate[6] = newWillGenerate.getOrDefault(6,0) + fish
                    }
                    else ->{
                        newWillGenerate[days - 1] = newWillGenerate.getOrDefault(days - 1, 0) + fish
                    }
                }
            }
            willGenerate = newWillGenerate
        }

        return willGenerate.values.map { it.toBigInteger() }.reduce { acc, bigInteger -> acc + bigInteger }
    }

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
