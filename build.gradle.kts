plugins {
    kotlin("jvm") version "2.0.0"
}

allprojects {
    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }

    kotlin {
        jvmToolchain(21)
    }
}