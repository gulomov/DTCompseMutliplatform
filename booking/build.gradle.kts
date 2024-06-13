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
            baseName = "booking"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.ktor.client.android)
        }
        commonMain.dependencies {
            implementation(projects.domain)
            implementation(projects.design)
            implementation(projects.database)
            implementation(projects.common)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.koin.compose)
            implementation(libs.koin.core)
            implementation(libs.viewmode.compose)
            implementation(libs.coil)
            implementation(libs.coil.compose)
            implementation(libs.coil.ktor)
            implementation(libs.compose.material3.multiplatform)
            implementation(libs.androidx.navigation)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.datetime)
            implementation(libs.cupertino)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.dtcm.work.booking"
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
        packageOfResClass = "org.dtcm.work.booking"
        generateResClass = always
    }
}
