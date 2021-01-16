buildscript {

    val kotlin_version by extra("1.4.21")
    repositories {
        mavenCentral()
        google()
        jcenter()
    }

    dependencies {
        classpath(Deps.kotlinGradlePlugin)
        classpath(Deps.androidGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        jcenter()
    }
}


object ProjectSettings{
    const val minSdk = 21
    const val compileSdk = 30
    const val targetSdk = 30
    const val buildTools = "30.0.3"
}
