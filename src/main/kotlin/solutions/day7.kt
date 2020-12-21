package solutions

import inputpath
import java.io.File

//Pre: the number is a single digit
fun parseInput7 (fileName: String): Map<String, List<Pair<Int, String>>> {
    val input: MutableMap<String, MutableList<Pair<Int, String>>> = mutableMapOf()
    File(fileName).forEachLine { raw ->
        val temp = raw.split(" bags contain no other bags.", " bags contain", " bags,", " bags.", " bag,", " bag.")
                      .filterNot { it == "" }
        input[temp[0]] = mutableListOf()
        for(i in 1 until temp.size){
            input[temp[0]]!!.add(Pair(Character.getNumericValue(temp[i][1]), temp[i].drop(3)))
        }
    }
    return input
}

fun d7P1 (input: Map<String, List<Pair<Int, String>>>): Int {
    var ans = 0

    fun dfsD7P1 (key: String): Int {
        var a = 0
        for(v in input[key] ?: emptyList()){
            if (v.second == "shiny gold"){
                a++
            }else {
                a += dfsD7P1(v.second)
            }
        }

//        if(key == "shiny gold"){
//            a++
//        }else {
//            input[key]?.forEach{a += dfsD7P1(it.second)}
//        }
        return a
    }

    input.forEach{(k, _) -> if (dfsD7P1(k) > 0) ans++ }

    return ans
}

fun d7P2 (input: Map<String, List<Pair<Int, String>>>): Int {
    fun dfsD7P2 (key: String): Int {
        var a = 0
        if(!input[key].isNullOrEmpty()){
            input[key]!!.forEach { (k,v) -> a += k * (1 + dfsD7P2(v)) }
        }
        //println("$key : cur value : $a")
        return a
    }
    return dfsD7P2("shiny gold")
}

fun main() {
    val input = parseInput7("$inputpath/day7.in")

    val p1 = d7P1(input)
    println(p1)

    val p2 = d7P2(input)
    println(p2)

}