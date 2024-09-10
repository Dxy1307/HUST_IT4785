fun main() {
    val pets = arrayOf("dog", "cat", "canary")
    for((index, element) in pets.withIndex())
        println("Item at $index is $element")
}

