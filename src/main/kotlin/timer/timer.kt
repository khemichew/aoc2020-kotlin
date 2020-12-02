package timer

import kotlin.system.measureNanoTime

fun <T, R> timer(func: (T) -> R, input: T, runs: Int){
    var totalTime: Long = 0
    for(i in 1..runs){
        totalTime = totalTime.plus(measureNanoTime { func(input) })
    }
    val average = totalTime.toDouble()/runs
    println("average time taken for $runs runs is ${average / 1000} μs")
}