plugins {
    alias(libs.plugins.android.application)
    kotlin("plugin.serialization")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs")

}

android {
    namespace = "com.example.e_commercewithapi"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.e_commercewithapi"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        //noinspection DataBindingWithoutKapt
        dataBinding = true
        viewBinding = true
    }

}

dependencies {
    implementation(libs.room.rxjava2)
    val nav_version = "2.9.0"


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.core:core-splashscreen:1.0.1")

    //dependencies packages
    implementation ("com.github.pwittchen:reactivenetwork-rx2:3.0.8")

    // Jetpack Compose integration
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // Views/Fragments integration
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")


    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

    // JSON serialization library, works with the Kotlin serialization plugin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    // أو آخر إصدار


    //implementation ("com.google.android.gms:play-services-ads:24.3.0")
    implementation ("androidx.viewpager2:viewpager2:1.1.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.tbuonomo:dotsindicator:4.3")

    //dataStore
    implementation("androidx.datastore:datastore-core:1.1.6")
    implementation("androidx.datastore:datastore-rxjava3:1.1.6")
    implementation("androidx.datastore:datastore-rxjava2:1.1.6")

    //Dependency injection
    implementation("com.google.dagger:hilt-android:2.56.2")
    annotationProcessor("com.google.dagger:hilt-android-compiler:2.56.2")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

    // RxJava2
    implementation ("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")

    //room database
    implementation("androidx.room:room-runtime:2.7.2")
    annotationProcessor("androidx.room:room-compiler:2.7.2")
    implementation("androidx.room:room-rxjava2:2.7.2")
    implementation ("it.xabaras.android:recyclerview-swipedecorator:1.2")
    implementation("com.github.zerobranch:SwipeLayout:1.3.1")


}