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
    File("src/main/resources/input/$year/$day.txt").writeText(input)
}

fun getInput(year: Int, day: Int): List<String> {
    if (!File("src/main/resources/input/$year/$day.txt").exists()) fetchInput(year, day)

    val puzzleInputFile = File("src/main/resources/input/$year/$day.txt")
    return puzzleInputFile.readLines()
}

val dotEnv = dotenv()