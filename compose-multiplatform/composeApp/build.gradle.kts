import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.jetBrains.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.jetBrains.compose)
    alias(libs.plugins.jetBrains.kotlin.plugin.compose)
}

kotlin {
    androidLibrary {
        namespace = "com.example.benchmark.composeapp"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        compilerOptions { jvmTarget.set(JvmTarget.JVM_11) }
    }
    listOf(iosArm64(), iosSimulatorArm64()).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    sourceSets {
        androidMain.dependencies { implementation(libs.ktor.clientAndroid) }
        iosMain.dependencies { implementation(libs.ktor.clientDarwin) }
        commonMain.dependencies {
            implementation(libs.jetBrains.compose.runtime)
            implementation(libs.jetBrains.compose.foundation)
            implementation(libs.jetBrains.compose.material3)
            implementation(libs.jetBrains.compose.ui)
            implementation(libs.coilKt.coil3.compose)
            implementation(libs.coilKt.coil3.networkKtor3)
        }
    }
}
