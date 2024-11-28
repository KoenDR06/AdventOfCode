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

    val response = client.send(request, HttpResponse.BodyHandlers.ofString());
    val input = response.body()

    File("src/main/resources/input/$year").mkdirs()
    File("src/main/resources/input/$year/$day.txt").writeText(input)
}

fun getInput(year: Int, day: Int): List<String> {
    if (!File("src/main/resources/input/$year/$day.txt").exists()) fetchInput(year, day)

    val puzzleInputFile = File("src/main/resources/input/$year/$day.txt")
    return puzzleInputFile.readLines()
}

fun <T> T.println(): T {
    println(this)
    return this
}

fun findLCM(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}

fun findLCM(numbers: MutableList<Long>): Long {
    var result = numbers[0]
    for (i in 1..<numbers.size) {
        result = findLCM(result, numbers[i])
    }
    return result
}