package day1

import file.FileReader

fun main() {
    val fileContent = FileReader.readContent("/day1/input.txt")

    val top = fileContent.split("\n\n")
        .map { it.split("\n").filter { it.isNotBlank() } }
        .map { it.map { n -> n.toInt() }.sum() }
        .sortedDescending()
        .take(3)
        .sum()
    println(top)
}
