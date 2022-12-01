package day1

import file.FileReader

fun main(args: Array<String>) {
    val fileContent = FileReader.readContent("/day1/input.txt")

    val top = fileContent.split("\n\n")
        .map { it.split("\n").filter { it.isNotBlank() } }
        .map { it.map { n -> n.toInt() }.sum() }
        .sorted()
        .reversed()
        .first()
    println(top)
}
