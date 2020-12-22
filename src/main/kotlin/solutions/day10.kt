package solutions

import inputpath
import java.io.File

fun parseInput10(fileName: String): List<Int>{
    val intBuilder = mutableListOf<Int>()
    File(fileName).forEachLine {
        if(it.isNotEmpty()){
            intBuilder.add(it.toInt())
        }
    }
    return intBuilder
}

fun d10P1 (input: List<Int>): Int{
    val dist = MutableList(4){0}
    val jolts = input.toMutableList()
    jolts.add(0)
    jolts.add(input.maxOrNull()!! + 3)
    jolts.sort()
    for(i in 0 until jolts.size - 1){
        dist[jolts[i+1] - jolts[i]]++
    }
    return dist[1] * dist[3]
}

fun d10P2(input: List<Int>): Long {
    val jolts = input.toMutableList()
    jolts.sort()
    //println(jolts)
    val ways: MutableList<Long> = MutableList(jolts.last() + 1){0}
    ways[0] = 1
    if(jolts.contains(1)){
        ways[1] = ways[0]
    }
    if(jolts.contains(2)){
        ways[2] = ways[0] + ways[1]
    }

    for(i in 3 until ways.size){
        if(jolts.contains(i)){
            ways[i] = ways[i-1] + ways[i-2] + ways[i-3]
            //println("${ways[i]} at $i")
        }
    }
    return ways[ways.size-1]
}


fun main() {
    val input = parseInput10("$inputpath/day10.in")
    val p1 = d10P1(input)
    println(p1)

    val p2 = d10P2(input)
    println(p2)
}

