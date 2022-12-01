package file

object FileReader {

    fun readContent(path: String): String =
        FileReader::class.java.getResource(path)!!.readText()

}
