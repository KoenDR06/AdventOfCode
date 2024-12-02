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