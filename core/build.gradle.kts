plugins {
    kotlin("jvm")
    id("ktlint-plugin")
    id("publish-plugin")
}

val libraryGroup = findProperty("group") as String
val libraryVersion = findProperty("version") as String

version = libraryGroup
group = libraryVersion

dependencies {

    implementation("com.squareup.okhttp3:okhttp:4.9.0")
}
