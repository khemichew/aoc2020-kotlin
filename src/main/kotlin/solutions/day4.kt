package solutions

import inputpath
import timer.timer
import java.io.File

fun parseInput4 (fileName: String): List<Map<String,String>> {
    var uid = 0
    val input: MutableList<MutableMap<String,String>> = mutableListOf(mutableMapOf())
    File(fileName).forEachLine {
        if(it.isEmpty()) {
            uid++
            input.add(mutableMapOf())
        }
        else {
            val data = it.split(":", " ")
            for(i in data.indices step 2){
                input[uid][data[i]] = data[i+1]
            }
        }
    }
    return input
}

fun p1 (input: List<Map<String, String>>): Int =
    input.filter{ map -> map.size == 8 || map.size == 7 && map["cid"].isNullOrEmpty() }.size

fun p2 (input: List<Map<String, String>>): Int =
    input.filter{ map -> rule(map) && (map.size == 8  || map.size == 7 && map["cid"].isNullOrEmpty()) }.size


fun rule(map: Map<String, String>): Boolean =
    byr(map["byr"]) && iyr(map["iyr"]) && eyr(map["eyr"]) && hcl(map["hcl"]) &&
    ecl(map["ecl"]) && pid(map["pid"]) && hgt(map["hgt"])

fun byr(s: String?): Boolean =
    (1920..2002).contains(s?.toInt())

fun iyr(s: String?): Boolean =
    (2010..2020).contains(s?.toInt())

fun eyr(s: String?): Boolean =
    (2020..2030).contains(s?.toInt())

fun hcl(s: String?): Boolean =
    !s.isNullOrEmpty()
    && s.startsWith('#')
    && s.drop(1).all{it - '0' in 0..9 || it in 'a'..'f'}

fun ecl(s: String?): Boolean =
    listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(s)

fun pid(s: String?): Boolean =
    s?.length == 9 && s.toIntOrNull() != null

fun hgt(s: String?): Boolean =
    when {
        s.isNullOrEmpty() -> false
        s.endsWith("cm")-> (150..193).contains(s.substring(0, 3).toIntOrNull())
        s.endsWith("in") -> (59..76).contains(s.substring(0, 2).toIntOrNull())
        else -> false
    }

fun main() {
    val input = parseInput4("$inputpath/day4.in")
    val p1 = p1(input)
    println(p1)
    timer(::p1, input, 1000)

    val p2 = p2(input)
    println(p2)
    timer(::p2, input, 1000)
//    for(map in input){
//        println(byr(map["byr"]))
//        println(iyr(map["iyr"]))
//        println(eyr(map["eyr"]))
//        println(hcl(map["hcl"]))
//        println(ecl(map["ecl"]))
//        println(pid(map["pid"]))
//        println(hgt(map["hgt"]))
//        println()
//    }
}
