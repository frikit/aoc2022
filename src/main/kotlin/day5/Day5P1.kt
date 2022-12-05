package day5

import file.FileReader

fun main() {
    fun getColumnValue(line: String, idx: Int): String =
        line.windowed(3, 4)
            .getOrElse(idx - 1) { "" }

    val file1Content = FileReader.readContent("/day5/input1.txt").split("\n").filter { it.isNotBlank() }
    val columnsIdx = file1Content.first { !it.contains("[") }
        .split(" ")
        .filter { it.isNotBlank() }
        .map { it.toInt() }
    val valueLines = file1Content.filter { it.contains("[") && it.isNotBlank() }
    val matrix = columnsIdx.map { idx -> idx to valueLines.map { getColumnValue(it, idx) } }
        .associate { it.first to it.second.filter { v -> v.isNotBlank() }.reversed().toMutableList() }
        .toMutableMap()

    matrix.entries.map { println(it) }

    val file2Content = FileReader.readContent("/day5/input2.txt").split("\n").filter { it.isNotBlank() }
    val operations = file2Content.map {
        val split = it.split(" ")
        //move 2 from 2 to 7
        Triple(split[1].toInt(), split[3].toInt(), split[5].toInt())
    }

    println(operations.take(10))

    operations.forEach { op ->
        val count = op.first
        val from = op.second
        val to = op.third

        val elements = matrix[from]!!.takeLast(count)
        matrix[to]!!.addAll(elements.reversed())
        matrix[from] = matrix[from]!!.dropLast(count).toMutableList()
    }

    matrix.entries.map { println(it) }

    val finalValues = matrix.values.joinToString(separator = "") { it.last() }
        .replace("[", "")
        .replace("]", "")

    println(finalValues)
}
