import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    `java-library`
    kotlin("jvm") version "1.5.10"
    application
    id("elect86.jextract") //version "0.1.6"
//    id("io.github.krakowski.jextract") version "0.1.6"
    idea
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
//    implementation(files("opengl.jar"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
    mainClass.set("Teapot")
    applicationDefaultJvmArgs += "-Djava.library.path=.:/usr/lib/x86_64-linux-gnu"
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

tasks.withType<JavaExec>().configureEach {
    javaLauncher.set(javaToolchains.launcherFor(java.toolchain))
}

tasks {

    withType<JavaCompile>().configureEach {
        options.isIncremental = false
    }

    jextract {

        jdk {

        }



        header("/usr/include/GL/glut.h") {
            // The library name
            libraries.addAll("glut", "GLU", "GL")

            // The package under which all source files will be generated
            targetPackage.set("opengl")

            includes.add("/usr/lib/x86_64-linux-gnu")

            sourceMode.set(true)
            // The generated class name
//            className = 'Linux'
        }
    }

    register<ShadowJar>("testJar") {
        archiveClassifier.set("tests")
        from(sourceSets.test.get().output)
        configurations.add(project.configurations.testImplementation.get())
    }
}
//println(project.properties["org.gradle.java.installations.paths"])

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        verbose = true
//        freeCompilerArgs = listOf("--add-modules", "jdk.incubator.foreign")
    }
}