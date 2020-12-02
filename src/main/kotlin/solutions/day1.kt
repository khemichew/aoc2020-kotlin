package solutions

import inputpath
import timer.timer
import java.io.File
import java.util.*

fun p1v1(input: List<Int>): Int?{
    val entries = HashSet(input)
    val exist = BooleanArray(2020){false}
    for(entry in entries){
        if(exist[2020 - entry]) return (entry * (2020 - entry))
        exist[entry] = true
    }
    return null
}

fun p1v2 (input: List<Int>): Int? {
    val entries = HashSet(input)
    for(entry in entries){
        if(entries.contains(2020 - entry)) return (entry * (2020 - entry))
    }
    return null
}

fun p2v1 (input: List<Int>): Int? {
    val entries = HashSet(input)
    val exist = BooleanArray(2020){false}
    for(i in entries){
        for(j in entries){
            if( (2020 - i - j) > 0 && exist[2020 - i - j]) return i * j * (2020 - i - j)
            exist[j] = true
        }
        exist[i] = true
    }
    return null
}

fun p2v2 (input: List<Int>): Int? {
    val entries = HashSet(input)
    for(i in entries){
        for(j in entries){
            if(entries.contains(2020 - i - j)) return i * j * (2020 - i - j)
        }
    }
    return null
}

fun parseInput1(fileName: String): List<Int> {
    val nums = mutableListOf<Int>()
    File(fileName).forEachLine { nums.add(it.toInt()) }
    return nums
}


fun main() {
    val input = parseInput1("$inputpath/day1.in")

    val p1v1 = p1v1(input)
    val p1v2 = p1v2(input)

    val p2v1 = p2v1(input)
    val p2v2 = p2v2(input)

    println(p1v1)
    timer(::p1v1, input, 1000)
    println(p1v2)
    timer(::p1v2, input, 1000)

    println(p2v1)
    timer(::p2v1, input, 1000)
    println(p2v2)
    timer(::p2v2, input, 1000)

}