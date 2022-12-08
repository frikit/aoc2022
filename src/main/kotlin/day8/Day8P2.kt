package day8

import file.FileReader

fun main() {

    fun takeWhile(list: List<Int>, curr: Int): List<Int> {
        val res = mutableListOf<Int>()
        list.forEach {
            res.add(it)
            if (it >= curr) {
                return res
            }
        }

        return res
    }

    val fileContent = FileReader.readContent("/day8/input.txt")
    val grid = fileContent.split("\n").filter { it.isNotBlank() }
        .map { it.toCharArray().toList().map { it.toString().toInt() } }

    val count = grid.mapIndexed { rIdx, r ->
        r.mapIndexed { cIdx, c ->
            val sameRowLeft = (0 until cIdx).toList().map { grid[rIdx][it] }.reversed()
            val sameRowRight = (cIdx + 1..grid[rIdx].lastIndex).toList().map { grid[rIdx][it] }
            val sameColumnUp = (0 until rIdx).toList().map { grid[it][cIdx] }.reversed()
            val sameColumnDown = (rIdx + 1..grid.lastIndex).toList().map { grid[it][cIdx] }

            val left = takeWhile(sameRowLeft, c)
            val right = takeWhile(sameRowRight, c)
            val up = takeWhile(sameColumnUp, c)
            val down = takeWhile(sameColumnDown, c)

            left.size * right.size * up.size * down.size
        }
    }.flatten().max()

    println(count) //wrong 2322000 4757904

}
