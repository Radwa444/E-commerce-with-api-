// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    kotlin("plugin.serialization") version "2.0.21" apply false
    id("com.google.dagger.hilt.android") version "2.56.2" apply false



}
buildscript {

    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")

    }
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.56.2")
        val nav_version = "2.9.0"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }

}
