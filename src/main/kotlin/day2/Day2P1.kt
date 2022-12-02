package day2

import file.FileReader

fun main() {

    fun isMeWinner(opp: String, me: String) =
        when {
            opp == "P" && me == "S" -> true
            opp == "R" && me == "P" -> true
            opp == "S" && me == "R" -> true
            else -> false
        }

    fun isDraw(opp: String, me: String) = opp == me

    fun scoreWinnerMapper(enemy: String, me: String): Int {
        return when {
            isMeWinner(enemy, me) -> 6
            !isMeWinner(enemy, me) && isDraw(enemy, me) -> 3
            else -> 0
        }
    }


    fun scoreInputMapper(me: String) =
        when (me) {
            "R" -> 1
            "P" -> 2
            else -> 3
        }


    val mapper = mapOf(
        //enemy
        "A" to "R",
        "B" to "P",
        "C" to "S",
        //me
        "Y" to "P",
        "X" to "R",
        "Z" to "S",
    )

    val fileContent = FileReader.readContent("/day2/input.txt")
    val games = fileContent.split("\n")
        .filter { it.isNotBlank() }
        .map { line ->
            val comb = line.split(" ")
            val e = comb[0].trim().uppercase()
            val m = comb[1].trim().uppercase()
            e to m
        }

    val score = games.map { game ->
        val enemy = mapper[game.first]!!
        val me = mapper[game.second]!!

        val inputScore = scoreInputMapper(me)
        val scoreWinner = scoreWinnerMapper(enemy, me)

        inputScore + scoreWinner
    }.sum()

    println(score)
}
