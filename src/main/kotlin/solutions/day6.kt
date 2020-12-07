package solutions

import inputpath
import java.io.File

fun parseInput6 (fileName: String): List<List<String>> {
    var group = 0
    val input: MutableList<MutableList<String>> = mutableListOf(mutableListOf())
    File(fileName).forEachLine {
        if(it.isEmpty()){
            group++
            input.add(mutableListOf())
        }
        else {
            input[group].add(it)
        }
    }
    return input
}

fun d6P1(input: List<List<String>>): Int =
    input.map { it.joinToString("").toHashSet().size }.sum()

fun d6P2(input: List<List<String>>): Int =
    input.sumBy { group -> group.reduce{acc, s -> acc.toSet().intersect(s.toSet()).joinToString("")}.length }


fun main() {
    val input = parseInput6("$inputpath/day6.in")
    //println(input)

    val p1 = d6P1(input)
    println(p1)

    val p2 = d6P2(input)
    println(p2)

}