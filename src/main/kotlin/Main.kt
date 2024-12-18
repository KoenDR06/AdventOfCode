package me.koendev

import io.github.cdimascio.dotenv.dotenv
import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

fun fetchInput(year: Int, day: Int) {
    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://adventofcode.com/$year/day/$day/input"))
        .header("Cookie", "session=${dotEnv["SESSION"]}")
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    val input = response.body()

    File("src/main/resources/input/$year").mkdirs()
    File("src/main/resources/input/$year/${day.toString().padStart(2, '0')}.txt").writeText(input)
}

fun getInput(year: Int, day: Int): List<String> {
    if (!File("src/main/resources/input/$year/${day.toString().padStart(2, '0')}.txt").exists()) fetchInput(year, day)

    val puzzleInputFile = File("src/main/resources/input/$year/${day.toString().padStart(2, '0')}.txt")
    return puzzleInputFile.readLines()
}

fun solve(year: Int, day: Int, part1: (input: List<String>) -> Any, part2: (input: List<String>) -> Any) {
    val input = getInput(year, day)

    var start = System.nanoTime()
    println("Part 1 solution is `${part1(input)}` and took: ${(System.nanoTime() - start) / 1000000.0} ms")

    start = System.nanoTime()
    println("Part 2 solution is `${part2(input)}` and took: ${(System.nanoTime() - start) / 1000000.0} ms")
}

val dotEnv = dotenv()