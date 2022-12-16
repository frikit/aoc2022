package day10

import file.FileReader

fun main() {

    val fileContent = FileReader.readContent("/day10/input.txt")
    val lines = fileContent.split("\n").filter { it.isNotBlank() }
    var ans = 0
    var i = 1
    var x = 1
    fun check() {
        if (i in listOf(20, 60, 100, 140, 180, 220)) {
            ans += x * i
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
            check()
            i++
        }
        x += inc
    }
    println(ans)

}
