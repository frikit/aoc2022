package day4

import file.FileReader

fun main() {

    val fileContent = FileReader.readContent("/day4/input.txt")
    val pairLines = fileContent.split("\n").filter { it.isNotBlank() }
    val counts = pairLines.map {
        val ranges = it.split(",")
        val firstStartRange = ranges[0].split("-")[0].toInt()
        val firstEndRange = ranges[0].split("-")[1].toInt()
        val secondStartRange = ranges[1].split("-")[0].toInt()
        val secondEndRange = ranges[1].split("-")[1].toInt()

        if (firstStartRange in secondStartRange..secondEndRange
            || firstEndRange in secondStartRange..secondEndRange
        ) {
            1
        } else if (secondStartRange in firstStartRange..firstEndRange
            || secondEndRange in firstStartRange..firstEndRange
        ) {
            1
        } else {
            0
        }
    }.sum()

    println(counts)

}
