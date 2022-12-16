package day10

import file.FileReader

fun main() {

    val fileContent = FileReader.readContent("/day10/input.txt")
    val lines = fileContent.split("\n").filter { it.isNotBlank() }
    var i = 1
    var x = 1
    val a = Array(6) { CharArray(40) { '.' } }
    fun draw() {
        val pos = (i - 1) % 40
        if (x in pos - 1 .. pos + 1) {
            a[(i - 1) / 40][pos] = '#'
        }
    }
    for (line in lines) {
        val elems = line.split(" ")
        var il = 0
        var inc = 0
        when(elems[0]) {
            "noop" -> il = 1
            "addx" -> {
                il = 2
                inc = elems[1].toInt()
            }
        }
        repeat(il) {
            draw()
            i++
        }
        x += inc
    }
    a.forEach { println(it.concatToString()) }

}
