package solutions

import inputpath
import timer.timer
import java.io.File

typealias Input = Triple<List<Pair<Int, Int>>, CharArray, List<String>>

//every line will have 4 items, returns list with multiple of 4
fun parseInput2(fileName: String): Input {
    val range = mutableListOf<Pair<Int, Int>>()
    val chars = mutableListOf<Char>()
    val pw = mutableListOf<String>()
    File(fileName).forEachLine {
        s -> val ls = s.split(" ", "-", ":").filterNot { it == "" }
        range.add(ls[0].toInt() to ls[1].toInt())
        chars.add(ls[2].single())
        pw.add(ls[3])
    }
    return Triple(range, chars.toCharArray(), pw)
}

fun p1(input: Input): Int {
    var valid = 0
    val (range, chars, pw) = input
    for(i in chars.indices){
        val count = pw[i].filter{it == chars[i]}.length
        if (count in range[i].first..range[i].second) valid++
    }
    return valid
}

fun p2(input: Input): Int {
    var valid = 0
    val (range, chars, pw) = input
    for(i in chars.indices){
        valid += xor(range[i], chars[i], pw[i])
    }
    return valid
}

fun xor(range: Pair<Int, Int>, char: Char, pw: String): Int{
    if(char == pw[range.first-1] && char != pw[range.second-1] ||
        char != pw[range.first-1] && char == pw[range.second-1]) return 1
    return 0
}

fun main() {
    val input = parseInput2("$inputpath/day2.in")
    val p1 = p1(input)
    println(p1)
    timer(::p1, input, 1000)

    val p2 = p2(input)
    println(p2)
    timer(::p2, input, 1000)
}