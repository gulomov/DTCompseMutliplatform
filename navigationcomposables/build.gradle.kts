plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "navigationcomposables"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.home)
            implementation(projects.introduction)
            implementation(projects.gallery)
            implementation(projects.favorites)
            implementation(projects.productdetail)
            implementation(projects.splashscreen)
            implementation(projects.common)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.androidx.navigation)
            implementation(libs.compose.material3.multiplatform)
            implementation(libs.cupertino)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.dtcm.work.navigationcomposables"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    compose.resources {
        publicResClass = true
        packageOfResClass = "org.dtcm.work.navigationcomposables"
        generateResClass = always
    }

}
