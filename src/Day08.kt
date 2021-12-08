infix fun <E> Collection<E>.symmetricDifference(other: Collection<E>): Set<E> {
    val left = this subtract other
    val right = other subtract this
    return left union right
}

fun String.calculateDifferentChars(other: String): Int =
    (this.toSortedSet() symmetricDifference other.toSortedSet()).size


fun main() {

    fun part1(input: List<String>): Int {
        return input.map { it.split("|")[1] }
            .map { it.split("\\s+".toRegex()) }
            .map { it.count { it.length == 7 || it.length == 4 || it.length == 2 || it.length == 3 } }
            .sum()
    }


    fun Pair<List<String>, List<String>>.toOutputValue(): Int {
        val one = first.first { it.length == 2 }
        val four = first.first { it.length == 4 }
        val seven = first.first { it.length == 3 }
        val eight = first.first { it.length == 7 }
        val twoThreeFive = first.filter { it.length == 5 }
        val zeroSixNine = first.filter { it.length == 6 }
        val two = twoThreeFive.first { it.calculateDifferentChars(four) == 5 }
        val threeFive = twoThreeFive.filterNot { it == two }
        val three = threeFive.first { it.calculateDifferentChars(two) == 2 }
        val five = threeFive.filterNot { it == three }.first()
        val zero = zeroSixNine.first { it.calculateDifferentChars(five) == 3 }
        val sixNine = zeroSixNine.filterNot { it == zero }
        val nine = sixNine.first{it.calculateDifferentChars(three) == 1}
        val six = sixNine.filterNot { it == nine }.first()

        return second.joinToString(separator = "") {
            when (it) {
                one -> "1"
                two -> "2"
                three -> "3"
                four -> "4"
                five -> "5"
                six -> "6"
                seven -> "7"
                eight -> "8"
                nine -> "9"
                zero -> "0"
                else -> throw Exception("NO")
            }
        }.toInt()
    }

    fun part2(input: List<String>): Int {
        return input.map { it.split("|") }
            .map {
                Pair(
                    it[0].split("\\s+".toRegex()).map { it.toSortedSet().joinToString(separator = "") },
                    it[1].split("\\s+".toRegex()).filter { it.isNotEmpty() }.map { it.toSortedSet().joinToString(separator = "") })
            }
            .map { it.toOutputValue() }
            .sum()
    }


    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
