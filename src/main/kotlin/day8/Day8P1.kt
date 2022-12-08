package day8

import file.FileReader

fun main() {

    val fileContent = FileReader.readContent("/day8/input.txt")
    val grid = fileContent.split("\n").filter { it.isNotBlank() }
        .map { it.toCharArray().toList().map { it.toString().toInt() } }

    val count = grid.mapIndexed { rIdx, r ->
        r.mapIndexed { cIdx, c ->
            val sameRowLeft = (0 until cIdx).toList().map { grid[rIdx][it] }
            val sameRowRight = (cIdx + 1..grid[rIdx].lastIndex).toList().map { grid[rIdx][it] }
            val sameColumnUp = (0 until rIdx).toList().map { grid[it][cIdx] }
            val sameColumnDown = (rIdx + 1..grid.lastIndex).toList().map { grid[it][cIdx] }

            val left = sameRowLeft.all { it < c }
            val right = sameRowRight.all { it < c }
            val up = sameColumnUp.all { it < c }
            val down = sameColumnDown.all { it < c }

            if (left || right || up || down) {
                1
            }else {
                0
            }
        }
    }.flatten().sum()

    println(count)

}
