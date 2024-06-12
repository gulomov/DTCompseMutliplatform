rootProject.name = "DTCompseMutliplatform"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":database")
include(":design")
include(":navigationroute")
include(":navigationcomposables")
include(":home")
include(":repository")
include(":domain")
include(":common")
include(":gallery")
include(":favorites")
include(":productdetail")
include(":booking")
include(":datastore")
include(":introduction")
