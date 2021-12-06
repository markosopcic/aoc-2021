import java.io.File

fun main() {
    fun part1(input: List<String>): Int {
        val bingoNums = input.first().split(",").map { it.toInt() }

        val boards = mutableListOf<BingoBoard>()
        var bingoBoard = mutableListOf<List<Int>>()

        for(line in input.subList(2, input.size)){

            if(line.isEmpty()){
                boards.add(BingoBoard(bingoBoard))
                bingoBoard = mutableListOf()
                continue
            }

            bingoBoard.add(line.trim().split("\\s+".toRegex()).map { it.toInt() })
        }

        for(num in bingoNums){
            boards.forEach {
                it.hitNumber(num)
                if(it.hasWon()){
                    return it.calculateScore(num)
                }
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val bingoNums = input.first().split(",").map { it.toInt() }

        var boards = mutableListOf<BingoBoard>()
        var bingoBoard = mutableListOf<List<Int>>()

        for(line in input.subList(2, input.size)){

            if(line.isEmpty()){
                boards.add(BingoBoard(bingoBoard))
                bingoBoard = mutableListOf()
                continue
            }

            bingoBoard.add(line.trim().split("\\s+".toRegex()).map { it.toInt() })
        }

        var lastWon: BingoBoard? = null
        var lastWonNum = 1
        for(num in bingoNums){
            val newBoards = mutableListOf<BingoBoard>()
            for(board in boards.toList()){
                board.hitNumber(num)
                if(board.hasWon()){
                    lastWon = board
                    lastWonNum = num
                }else{
                    newBoards.add(board)
                }
            }
            boards = newBoards
        }

        return lastWon!!.calculateScore(lastWonNum)
    }


    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}


class BingoBoard(val board: List<List<Int>>){

    private val hit: MutableList<MutableList<Boolean>> =
        MutableList(size = board.size, init = { MutableList(board.size, init = { false })})

    fun calculateScore(lastNum: Int): Int{
        var sum = 0
        for(y in board.indices){
            for(x in board[y].indices){
                if(!hit[y][x]){
                    sum+=board[y][x]
                }
            }
        }
        return sum*lastNum
    }

    fun hitNumber(num: Int){
        for(y in board.indices){
            for(x in board[y].indices){
                if(board[y][x] == num){
                    hit[y][x] = true
                }
            }
        }
    }

    fun hasWon(): Boolean{
        for(y in hit.indices){
            var won = true
            for(x in hit[y].indices){
                if(hit[y][x] == false){
                    won = false
                    break
                }
            }
            if(won) return true
        }


        for(y in hit.indices){
            var won = true
            for(x in hit[y].indices){
                if(hit[x][y] == false){
                    won = false
                    break
                }
            }
            if(won) return true
        }

        return false
    }
}