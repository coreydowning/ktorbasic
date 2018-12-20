import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kodein_version: String by project
val junit_jupiter_version: String by project

plugins {
    application
    kotlin("jvm") version "1.3.10"
}

group = "ktorbasic"
version = "0.0.1"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    compile("io.ktor:ktor-server-netty:$ktor_version")
    compile("io.ktor:ktor-gson:$ktor_version")
    compile("io.ktor:ktor-locations:$ktor_version")
    compile("org.kodein.di:kodein-di-generic-jvm:$kodein_version")
    compile("ch.qos.logback:logback-classic:$logback_version")

    testCompile("org.junit.jupiter:junit-jupiter-api:$junit_jupiter_version")
    testCompile("org.junit.jupiter:junit-jupiter-params:$junit_jupiter_version")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:$junit_jupiter_version")
    testCompile("io.ktor:ktor-server-tests:$ktor_version")
    testCompile("org.assertj:assertj-core:3.11.1")
    testImplementation("io.mockk:mockk:1.8.13.kotlin13")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}