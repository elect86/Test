plugins {
    `java-library`
    kotlin("jvm") version "1.5.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(files("opengl.jar"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf(
        "--enable-native-access=ALL-UNNAMED",
        "--add-modules",
        "jdk.incubator.foreign",
        "-Djava.library.path=.:/usr/lib/x86_64-linux-gnu"))
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))