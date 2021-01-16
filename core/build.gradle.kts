plugins {
    kotlin("jvm")
    id("ktlint-plugin")
    id("publish-plugin")
}

val libraryGroup = findProperty("group") as String
val libraryVersion = findProperty("version") as String

version = libraryGroup
group = libraryVersion

kotlin {
    explicitApi()
}

dependencies {

}
