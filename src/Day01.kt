fun main() {
    fun part1(input: List<String>): Int {
        val numbers = input.map { it.toInt() }
        var count = 0
        for(index in 1 until numbers.size){
            if(numbers[index]  > numbers[index - 1]) count++
        }
        return count
    }

    fun part2(input: List<String>): Int {
        val numbers = input.map { it.toInt() }
        val groups = mutableListOf<Int>()
        for(index in 2 until numbers.size){
            groups.add(numbers[index - 2] + numbers[index - 1] + numbers[index])
        }
        var count = 0
        for(index in 1 until groups.size){
            if(groups[index]  > groups[index - 1]) count++
        }
        return count
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
