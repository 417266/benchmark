import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetBrains.kotlin.plugin.compose)
}

android {
    namespace = "com.example.benchmark"
    compileSdk = 36
    defaultConfig {
        applicationId = "com.example.benchmark"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes { release { isMinifyEnabled = false } }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin { compilerOptions { jvmTarget = JvmTarget.JVM_11 } }
    buildFeatures { compose = true }
}

dependencies {
    implementation(libs.androidX.activity.compose)
    implementation(platform(libs.androidX.compose.bom))
    implementation(libs.androidX.compose.ui)
    implementation(libs.androidX.compose.material3)
    implementation(libs.coilKt.coil3.compose)
    implementation(libs.coilKt.coil3.networkKtor3)
    implementation(libs.ktor.clientAndroid)
}
