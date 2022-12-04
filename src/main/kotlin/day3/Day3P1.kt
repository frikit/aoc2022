package day3

import file.FileReader

fun main() {
    fun toPriority(c: Char): Int =
        when (c) {
            in 'a'..'z' -> c.code - 'a'.code + 1
            in 'A'..'Z' -> c.code - 'A'.code + 27
            else -> throw IllegalStateException("unexpected input $c")
        }

    val fileContent = FileReader.readContent("/day3/input.txt")
    val rucksacks = fileContent.split("\n").filter { it.isNotBlank() }
    val splitInCompartments = rucksacks.map {
        val fComp = it.substring(0, it.length / 2)
        val sComp = it.substring(it.length / 2)
        val c = fComp.filter { letter -> letter in sComp }

        toPriority(c.first())
    }

    println(splitInCompartments.sum())
}
