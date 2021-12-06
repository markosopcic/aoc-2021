import java.io.File

fun main() {
    fun part1(input: List<String>): Int {
        val threshold = input.size.toFloat() / 2
        val mostCommonBits = input.map {
            it.chunked(1).withIndex()
        }.flatten()
            .groupBy {
                it.index
            }
            .map {
                it.value.map { it.value }
            }
            .map {
                if (it.count { it.toInt() == 1 } >= threshold) "1" else "0"
            }.joinToString(separator = "")

        val gamma = mostCommonBits.toInt(radix = 2)
        val epsilon = mostCommonBits.map { if (it == '1') '0' else '1' }.joinToString(separator = "").toInt(radix = 2)

        return gamma * epsilon
    }

    fun List<String>.countOneInColumn(index: Int) = map { it[index] == '1' }.count{ it }

    fun List<String>.hasMoreOnesInColumn(index: Int) = countOneInColumn(index) >= size.toFloat() / 2

    fun MutableList<String>.removeNumberWithBitOnIndex(bit: Boolean, index: Int) =
        removeIf { (bit && it[index] == '1') || (!bit && it[index] == '0') }

    fun part2(input: List<String>): Int {

            val oxygenList = input.toMutableList()
            val co2List = input.toMutableList()

            for (index in oxygenList[0].indices) {
                if (oxygenList.hasMoreOnesInColumn(index)) {
                    oxygenList.removeNumberWithBitOnIndex(false, index)
                } else {
                    oxygenList.removeNumberWithBitOnIndex(true, index)
                }

                if(oxygenList.size == 1) break
            }

            for (index in co2List[0].indices) {
                if (!co2List.hasMoreOnesInColumn(index)) {
                    co2List.removeNumberWithBitOnIndex(false, index)
                } else {
                    co2List.removeNumberWithBitOnIndex(true, index)
                }

                if(co2List.size == 1) break
            }

            return co2List.first().toInt(radix = 2) * oxygenList.first().toInt(radix = 2)
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
