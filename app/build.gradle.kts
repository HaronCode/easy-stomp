plugins {
    id("com.android.library")
    kotlin("android")
    id("ktlint-plugin")
}


android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        minSdkVersion(21)
    }


    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

}