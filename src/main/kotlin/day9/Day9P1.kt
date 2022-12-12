package day9

import file.FileReader
import kotlin.math.*

fun main() {

    val fileContent = FileReader.readContent("/day9/input.txt")
    val lines = fileContent.split("\n").filter { it.isNotBlank() }
    val pairs = lines.map {
        val l = it.split(" ")
        val dir = l[0].trim()
        val num = l[1].trim().toInt()
        dir to num
    }

    data class Point(val x: Int, val y: Int)
    val visited = HashSet<Point>()
    visited.add(Point(0, 0))
    val knots = 2
    val x = IntArray(knots)
    val y = IntArray(knots)
    for (pair in pairs) {
        repeat(pair.second) {
            when (pair.first[0]) {
                'U' -> y[0]--
                'D' -> y[0]++
                'R' -> x[0]++
                'L' -> x[0]--
                else -> error(pair.first)
            }
            for (i in 1 until knots) {
                val dx = x[i - 1] - x[i]
                val dy = y[i - 1] - y[i]
                if (abs(dx) > 1 || abs(dy) > 1) {
                    x[i] += dx.sign
                    y[i] += dy.sign
                }
            }
            visited.add(Point(x[knots - 1], y[knots - 1]))
        }
    }
    println(visited.size)

}
