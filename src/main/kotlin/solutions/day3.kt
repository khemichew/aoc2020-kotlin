package solutions

import timer.timer
import inputpath
import java.io.File

fun parseInput3 (fileName: String): List<String> {
    val arr: MutableList<String> = mutableListOf()
    File(fileName).forEachLine { arr.add(it) }
    return arr
}

// open square = (.), tree = (#)
fun p1 (input: List<String>): Int {
    var trees = 0
    val length = input[1].length //11
    for(i in 1 until input.size){
        if(input[i][(3 * i) % length] == '#') trees++
        //println("1st pos : ${3*i % length}")
    }
    return trees
}

fun trees (input: List<String>, right: Int, down: Int): Int {
    var trees = 0
    val length = input[1].length
    for(i in down until input.size step down){
        if(input[i][(right * i)/down % length] == '#') trees++
        //println("2nd pos at line $i: ${(right * i/down) % length}")
    }
    return trees
}

fun p2 (input: List<String>): Long {
    val pair = listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
    return pair.fold(1){acc, (right, down) -> acc * trees(input, right, down)}
}

fun main() {
    val input = parseInput3("$inputpath/day3.in")
    val p1 = p1(input)
    println(p1)
    timer(::p1, input, 1000)

    val p2 = p2(input)
    println(p2)
    timer(::p2, input, 1000)
}