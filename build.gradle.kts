plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version("8.1.1")
}

group = "dev.jsinco.canvord"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
}

dependencies {
    // Still need JDA
    implementation("net.dv8tion:JDA:5.0.0-beta.24") {
        exclude("org.slf4j", "slf4j-api")
    }
    implementation("club.minnced:discord-webhooks:0.8.4") {
        exclude("org.slf4j", "slf4j-api")
    }


    // Google guava/gson
    implementation("com.google.guava:guava:33.3.1-jre")
    implementation("com.google.code.gson:gson:2.10.1")

    // Annotations
    implementation("org.jetbrains:annotations:25.0.0")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    // Framework
    //implementation("com.github.Jsinco:jda-framework:1.2")
    implementation(files("lib/jda-framework-1.3.jar"))

    // Canvas integration
    implementation("com.github.Jsinco:canvas-api:2.0.2")

    // HTML to markdown
    implementation("io.github.furstenheim:copy_down:1.0")

    // Config
    implementation("eu.okaeri:okaeri-configs-yaml-snakeyaml:5.0.5")
    implementation("eu.okaeri:okaeri-configs-json-gson:5.0.5")
}

tasks {
    jar {
        enabled = false // ShadowJar will handle this
    }

    // gradlew build
    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        manifest {
            attributes("Main-Class" to "dev.jsinco.canvord.Main")
        }
        dependencies {
            // Need to include all transitive dependencies
        }

        archiveBaseName.set(project.rootProject.name)
        archiveClassifier.set("")
    }
}
