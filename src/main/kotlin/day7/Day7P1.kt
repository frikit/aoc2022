package day7

import file.FileReader

fun main() {

    fun getTotalDirectorySize(paths: MutableMap<String, List<Pair<String, String>>>, path: String): Long {
        val currDirSize = paths[path]!!.map { it.first }.filter { it != "dir" }.sumOf { it.toLong() }
        val restSizes = paths.entries.filter { it.key != path && it.key.contains(path) }
            .sumOf { it.value.filter { it.first != "dir" }.sumOf { it.first.toLong() } }

        return currDirSize + restSizes
    }

    fun MutableCollection<String>.toPath() =
        this.joinToString(separator = "/").replace("/+".toRegex(), "/")

    val fileContent = FileReader.readContent("/day7/input.txt").split("\n").filter { it.isNotBlank() }

    var currentPath = mutableListOf<String>()
    val paths = mutableMapOf<String, List<Pair<String, String>>>()
    val currentListOfFiles = mutableListOf<String>()
    var currCmd: String

    fileContent.forEach { line ->
        if (line.startsWith("$")) {
            currCmd = line.split(" ")[1].trim()
            val args = line.split(" ").getOrElse(2) { "" }.trim()

            if (currCmd == "cd" && currentListOfFiles.isNotEmpty()) {
                val pairs = currentListOfFiles.map { it.split(" ")[0] to it.split(" ")[1] }
                paths[currentPath.toPath()] = pairs
                currentListOfFiles.removeAll { true }
            }

            if (currCmd == "cd" && args == "/") {
                currentPath.removeAll { true }
                currentPath.add("/")
            } else if (currCmd == "cd" && args == "..") {
                currentPath = currentPath.dropLast(1).toMutableList()
            } else if (currCmd == "cd" && args.isNotBlank()) {
                currentPath.add(args)
            }
        } else {
            currentListOfFiles.add(line)
        }
    }

    if (currentListOfFiles.isNotEmpty()) {
        val pairs = currentListOfFiles.map { it.split(" ")[0] to it.split(" ")[1] }
        paths[currentPath.toPath()] = pairs
        currentListOfFiles.removeAll { true }
    }

    val result = paths.keys.associateWith { getTotalDirectorySize(paths, it) }

    println(result.values.filter { it <= 100000 }.sum())
}
