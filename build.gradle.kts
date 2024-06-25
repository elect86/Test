plugins {
    embeddedKotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.scijava.org/content/groups/public")
}

dependencies {
    implementation(platform("org.scijava:pom-scijava:38.0.0-SNAPSHOT"))

    annotationProcessor(libs.scijava.common)

    implementation(kotlin("reflect"))
    implementation(libs.org.jetbrains.kotlinx.kotlinxCoroutinesCore)

    implementation(libs.org.slf4j.slf4jApi)
    implementation(libs.org.joml.joml)
    implementation(libs.org.jocl.jocl)
    implementation(libs.scijava.common)
    implementation(libs.scijava.scriptEditor)
    implementation(libs.scijava.uiBehaviour)
    implementation(libs.scijava.scriptingJython)
    implementation(libs.net.java.dev.jna.jnaPlatform)
}
