package solutions

import inputpath
import java.io.File
import java.util.*
import timer.timer

fun parseInput8 (fileName: String) : List<Pair<String, Int>> {
    val input = mutableListOf<Pair<String, Int>>()
    File(fileName).forEachLine {
        input.add(Pair(it.take(3), it.drop(4).toInt()))
    }
    return input
}

fun d8 (input: List<Pair<String, Int>>): Pair<Int, Boolean> {
    var acc = 0
    val vis = BooleanArray(input.size) {false}
    var it = 0
    while (!vis[it]){
        vis[it] = true
        when (input[it].first) {
            "nop" -> {
                it++
            }
            "acc" -> {
                acc += input[it].second
                it++
            }
            else -> {    //jmp
                it += input[it].second
            }
        }
        if(it >= input.size){
            break
        }
    }
    return Pair(acc, vis.last())
}

fun swapPrefix(p :Pair<String, Int>): Pair<String, Int> =
    when (p.first){
        "nop" -> {
            Pair("jmp", p.second)
        }
        "jmp" -> {
            Pair("nop", p.second)
        }
        else -> {
            p
        }
    }

fun d8P2 (input: List <Pair<String, Int>>): Int? {
    val instr = input.toMutableList()
    for(i in instr.indices) {
        instr[i] = swapPrefix(instr[i])
        val curr = d8(instr)
        if(curr.second){
            return curr.first
        }
        instr[i] = swapPrefix(instr[i])
    }
    return null
}

fun main() {
    val input = parseInput8("$inputpath/day8.in")
    val p1 = d8(input)
    println(p1.first)
    timer(::d8, input, 1000)

    val p2 = d8P2(input)
    println(p2)
    timer(::d8P2, input, 1000)

}