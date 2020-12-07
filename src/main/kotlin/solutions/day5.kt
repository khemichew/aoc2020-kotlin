package solutions

import inputpath

// use parseInput3

// pre: input is 'F', 'B', 'L', 'R'
fun d5Binary(range: Int, string: String): Int {
    var lowerBound = 0
    var upperBound = range - 1
    string.forEach { c ->
        when (c) {
            'F' -> upperBound -= (upperBound - lowerBound + 1) / 2  //total rows left / 2
            'B' -> lowerBound += (upperBound - lowerBound + 1) / 2
            'L' -> upperBound -= (upperBound - lowerBound + 1) / 2
            'R' -> lowerBound += (upperBound - lowerBound + 1) / 2
        }
        //println("$lowerBound, $upperBound")
    }
    return lowerBound
}

fun d5Row(string: String): Int =
    d5Binary(128, string.substring(0, 7))

fun d5Col(string: String): Int =
    d5Binary(8, string.substring(7, 10))

fun d5Seat(string: String): Int =
    8 * d5Row(string) + d5Col(string)

//max seat id
fun d5P1(input: List<String>): Int =
    input.map { d5Seat(it) }.maxOrNull() ?: -100

// try filtering consecutive numbers from the list of integers
fun d5P2(input: List<String>): List<Int> {
    return (0..d5P1(input))
        .filterNot { int -> int in input.map{ d5Seat(it) }}
}


fun main() {
    //val bp = listOf("BFFFBBFRRR")
    val input = parseInput3("$inputpath/day5.in")
    val p1 = d5P1(input)
    println(p1)
    val p2 = d5P2(input)
    println(p2)
}