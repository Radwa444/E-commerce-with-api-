// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    kotlin("plugin.serialization") version "2.0.21" apply false
    id("com.google.dagger.hilt.android") version "2.56.2" apply false


}
buildscript {
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.56.2") // أو أحدث نسخة
    }
}
