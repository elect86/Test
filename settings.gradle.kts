rootProject.name = "test"



dependencyResolutionManagement {
    repositories {
        maven("https://maven.scijava.org/content/groups/public")
    }
    versionCatalogs {
        create("libs") {
            from("org.scijava:pom-scijava:38.0.0-SNAPSHOT")
        }
    }
}

