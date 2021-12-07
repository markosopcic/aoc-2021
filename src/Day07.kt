import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val crabs = input.first().split(",").map { it.toInt() }
        val fuels = mutableMapOf<Int,Int>()
        for(align in crabs.minOrNull()!!..crabs.maxOrNull()!!){
            fuels[align] = crabs.map { abs(it - align) }.sum()
        }
        return fuels.values.minOrNull()!!
    }

    fun calculateSumFrom1To(n: Int): Int{
        var sum = 0
        for(i in 1..n){
            sum+=i
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val crabs = input.first().split(",").map { it.toInt() }
        val fuels = mutableMapOf<Int,Int>()
        for(align in crabs.minOrNull()!!..crabs.maxOrNull()!!){
            fuels[align] = crabs.map { calculateSumFrom1To(abs(it - align)) }.sum()
        }
        return fuels.values.minOrNull()!!
    }

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
