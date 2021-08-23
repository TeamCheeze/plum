plugins {
    kotlin("jvm") version "1.5.21"
    id("org.jetbrains.dokka") version "1.5.0"
    `maven-publish`
    signing
}

group = "io.github.teamcheeze"
version = "0.0.11"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://libraries.minecraft.net/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
    compileOnly("com.mojang:authlib:1.5.21")
    compileOnly("io.github.teamcheeze:jaw:1.0.2")
}

tasks {
    jar {
        from(project.sourceSets["main"].output)
    }
    register<Jar>("javadocJar") {
        archiveClassifier.set("javadoc")
            dependsOn("dokkaHtml")
            from("$buildDir/dokka/html/")
            include("**")
    }
    register<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(project.sourceSets["main"].allSource)
    }
}

publishing {
    publications {
        create<MavenPublication>("plumPublication") {
            artifactId = "plum"
            from(components["java"])
            artifact(tasks["javadocJar"])
            artifact(tasks["sourcesJar"])
            repositories {
                mavenLocal()
                maven {
                    name = "sonatype"
                    credentials.runCatching {
                        val nexusUsername: String by project
                        val nexusPassword: String by project
                        username = nexusUsername
                        password = nexusPassword
                    }.onFailure {
                        print("Credentials error")
                    }
                    url = if (version.endsWith("-SNAPSHOT"))
                            uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                        else
                            uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                }
            }
            pom {
                name.set("plum")
                description.set("A spigot library")
                url.set("https://github.com/TeamCheeze/plum")
                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("dolphin2410")
                        name.set("dolphin2410")
                        email.set("dolphin2410@outlook.kr")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/TeamCheeze/plum.git")
                    developerConnection.set("scm:git:ssh://github.com/TeamCheeze/plum.git")
                    url.set("https://github.com/TeamCheeze/plum")
                }
            }
        }
    }
}
signing {
    isRequired = true
    sign(tasks["javadocJar"], tasks["sourcesJar"], tasks.jar.get())
    sign(publishing.publications["plumPublication"])
}
