import org.jetbrains.kotlin.gradle.internal.ensureParentDirsCreated
import java.time.LocalDate
import java.time.ZoneId

plugins {
    kotlin("jvm") version "2.0.21"
}

group = "me.koendev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.2")
    implementation("org.json:json:20240303")
}

tasks.test {
    useJUnitPlatform()
}

task("solve-all") {
    group = "solutions"
    description = "Run every single day of AoC"
}

val basePath = "src/main/kotlin/"
File(basePath).listFiles()!!.forEach outer@ { file ->
    if (file.name == "utils" || !file.isDirectory) return@outer

    file.listFiles()!!.forEach inner@ { solutionFile ->
        val splits = solutionFile.toString().split("/")
        val year = splits[3].split("year")[1]
        val day = splits[4].split("Day",".kt")[1].padStart(2, '0')
        val taskName = "$year-$day"
        task<JavaExec>(taskName) {
            group = "solutions"
            description = "Run $year day $day"
            mainClass.set("me.koendev.year$year.Day${day}Kt")
            classpath = sourceSets.main.get().runtimeClasspath
        }
        tasks.named("solve-all") { dependsOn(taskName) }
    }
}

fun write(day: Int, year: Int) {
    val outputFile = File("src/main/kotlin/year${year}/Day${day.toString().padStart(2, '0')}.kt")
    if (outputFile.exists()) return else outputFile.ensureParentDirsCreated()
    outputFile.writeText("""
            package me.koendev.year${year}
    
            import me.koendev.utils.*
            import me.koendev.*
    
            fun main() {
                solve(
                    ${year},
                    ${day},
                    ::part1,
                    ::part2
                )
            }
    
            private fun part1(input: List<String>): Int {
    
    
                return 0
            }
    
    
            private fun part2(input: List<String>): Int {
    
    
                return 0
            }
        """.trimIndent())
}

task("init-day") {
    group = "solutions"
    description = "Initialize today's solution file."

    doFirst {
        val splits = project.property("day").toString().split("-").map { it.toIntOrNull() }.filterNotNull()
        if (splits.size != 2) return@doFirst

        write(splits[1], splits[0])
    }
}

task("init-today") {
    group = "solutions"
    description = "Initialize today's solution file."

    doFirst {
        val now = LocalDate.now(ZoneId.of("UTC-5"))

        val outputFile = File("src/main/kotlin/year${now.year}/Day${now.dayOfMonth.toString().padStart(2, '0')}.kt")
        if (outputFile.exists()) return@doFirst
        outputFile.writeText(
            """
            package me.koendev.year${now.year}
    
            import me.koendev.utils.*
            import me.koendev.*
    
            fun main() {
                solve(
                    ${now.year},
                    ${now.dayOfMonth},
                    ::part1,
                    ::part2
                )
            }
    
            private fun part1(input: List<String>): Int {
    
    
                return 0
            }
    
    
            private fun part2(input: List<String>): Int {
    
    
                return 0
            }
        """.trimIndent()
        )
    }
}