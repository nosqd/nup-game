plugins {
    id("java")
    kotlin("jvm")
}

group = "ru.nosqd.nup-game"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.purpurmc.org/snapshots")
}

dependencies {
    compileOnly("org.purpurmc.purpur", "purpur-api", "1.20.4-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib-jdk8"))
}
//kotlin {
//    jvmToolchain(17)
//}