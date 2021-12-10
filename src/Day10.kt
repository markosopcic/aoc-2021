import java.util.*

fun Char.isOpening() = this in listOf('(', '{', '[', '<')
fun Char.isClosing() = this in listOf(')', '}', ']', '>')

fun Char.toClosing() = when (this) {
    '(' -> ')'
    '{' -> '}'
    '<' -> '>'
    '[' -> ']'
    else -> throw Exception("")
}

fun checkLine(line: String): Char? {
    val closingStack = Stack<Char>()
    for (c in line) {
        when {
            c.isOpening() -> {
                closingStack.push(c.toClosing())
            }
            c != closingStack.peek() -> {
                println("On line $line expected ${closingStack.peek()} but got $c")
                return c
            }
            else -> {
                closingStack.pop()
            }
        }
    }
    return null
}

fun toCompletionString(line: String): String {
    val closingStack = Stack<Char>()
    for (c in line) {
        when {
            c.isOpening() -> {
                closingStack.push(c.toClosing())
            }
            c != closingStack.peek() -> {
                println("On line $line expected ${closingStack.peek()} but got $c")
                return ""
            }
            else -> {
                closingStack.pop()
            }
        }
    }
    return closingStack.joinToString(separator = "")
}

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val char = checkLine(line)
            sum += when (char) {
                ')' -> 3
                ']' -> 57
                '}' -> 1197
                '>' -> 25137
                else -> 0
            }
        }
        return sum
    }

    fun Char.toScore() = when(this){
        ')' -> 1
        ']' -> 2
        '}' -> 3
        '>' -> 4
        else -> 0
    }


    fun part2(input: List<String>): Long {
        val results = input.map { toCompletionString(it) }.filter { it.isNotBlank() }
            .map {
                var score = 0L
                for(c in it.reversed()){
                    score *= 5
                    score += c.toScore()
                }
                score
            }.sorted()
        return results[results.size/2]
    }

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
