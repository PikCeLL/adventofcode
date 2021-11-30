package utils

abstract class PuzzleUtils {
    companion object {
        fun readFileFromSrc(fileName: String): String {
            return {}::class.java.getResource(fileName).readText()
        }
    }
}