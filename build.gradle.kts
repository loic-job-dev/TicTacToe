plugins {
    id("java")
}

group = "fr.campus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Jar>("fatJar") {
    group = "build"
    archiveBaseName.set("TicTacToe")
    archiveVersion.set(version.toString())
    manifest {
        attributes["Main-Class"] = "fr.campus.loic.squaregames.Main"
    }

    // Inclure toutes les classes du projet
    from(sourceSets.main.get().output)

    // Inclure les dépendances
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}