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

tasks.register("solve-all") {
    group = "solutions"
    description = "Run every single day of AoC"
}

val basePath = "src/main/kotlin/"
File(basePath).listFiles()!!.forEach { file ->
    if (!file.isDirectory) return@forEach

    file.listFiles()!!.forEach { solutionFile ->
        val splits = solutionFile.toString().split("/")
        val year = splits[3].split("year")[1]
        val day = splits[4].split("Day",".kt")[1].padStart(2, '0')
        val taskName = "$year-$day"
        tasks.register<JavaExec>(taskName) {
            group = "solutions"
            description = "Run $year day $day"
            mainClass.set("me.koendev.year$year.Day${day}Kt")
            classpath = sourceSets.main.get().runtimeClasspath
        }
        tasks.named("solve-all") { dependsOn(taskName) }
    }
}