package day3

import file.FileReader

fun main() {
    fun toPriority(c: Char): Int =
        when (c) {
            in 'a'..'z' -> c.code - 'a'.code + 1
            in 'A'..'Z' -> c.code - 'A'.code + 27
            else -> throw IllegalStateException("unexpected input $c")
        }

    fun threeRucksacksToPriority(threeLines: List<String>): Int {
        val c = threeLines[0].filter { letter -> letter in threeLines[1] && letter in threeLines[2] }
        return toPriority(c.first())
    }

    val fileContent = FileReader.readContent("/day3/input.txt")
    val rucksacks = fileContent.split("\n").filter { it.isNotBlank() }

    val res = rucksacks
        .windowed(3, 3)
        .map(::threeRucksacksToPriority)
        .sum()

    println(res)
}
