package solutions

import java.io.File
import inputpath

fun parseInput9(fileName: String): List<Long> {
    val intBuilder = mutableListOf<Long>()
    File(fileName).forEachLine {
        if(it.isNotEmpty()){
            intBuilder.add(it.toLong())
        }
    }
    return intBuilder
}

fun d9P1(input: List<Long>): Long {
    for(i in 25 until input.size){
        val prev25 = input.slice(i-25 until i)
        val findNum = checkSumTo(input[i], prev25)
        if(findNum.isEmpty()){
            return input[i]
        }
    }
    return -1
}

fun checkSumTo(total: Long, nums: List<Long>): List<Long> =
    nums.filter { nums.contains(total - it) }

fun d9P2 (input: List<Long>, target: Long): Long {
    var fst = 0
    var lst = 1
    var sum = input[0] + input[1]

    // in fst == lst case, sum > target is implied
    while(sum != target){
        if(sum < target || fst == lst){
            lst++
            sum += input[lst]
        }else {
            sum -= input[fst]
            fst++
        }
    }
    val nums = input.slice(fst..lst)
    return nums.maxOrNull()!! + nums.minOrNull()!!
}

fun main() {
    val input = parseInput9("$inputpath/day9.in")
    val p1 = d9P1(input)
    println(p1)

    val p2 = d9P2(input, p1)
    println(p2)
}