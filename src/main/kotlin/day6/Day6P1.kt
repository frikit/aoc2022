package day6

import file.FileReader
import kotlin.system.exitProcess

fun main() {
    val fileContent = FileReader.readContent("/day6/input.txt")
        .replace("\n", "")
        .replace(" ", "")

    fileContent.forEachIndexed { index, _ ->
        val seq = fileContent.substring(index, index + 4).toCharArray().toList()
        val set = seq.toHashSet()

        if (seq.size == set.size) {
            println(index + 4)
            exitProcess(0)
        }
    }
}
