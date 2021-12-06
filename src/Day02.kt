fun main() {
    fun part1(input: List<String>): Int {
        val forward = input.filter { it.startsWith("forward") }.map { it.split(" ")[1].toInt() }.sum()
        val depth = input.filter { !it.startsWith("forward") }.map { if(it.startsWith("up")) it.split(" ")[1].toInt().times(-1) else it.split(" ")[1].toInt() }.sum()
        return forward * depth
    }

    fun part2(input: List<String>): Int {

        var forward = 0
        var depth = 0
        var aim = 0

        for(line in input){
            val parts = line.split(" ")
            val num = parts[1].toInt()
            when(parts[0]){
                "forward" -> {
                    forward+=num
                    depth += aim * num
                }
                "up" -> {
                    aim-=num
                }
                "down" -> {
                    aim +=num
                }
            }
        }
        return forward*depth
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
