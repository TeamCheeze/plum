plugins {
    kotlin("jvm") version "1.5.21"
    id("org.jetbrains.dokka") version "1.5.0"
    `maven-publish`
    signing
}

group = "io.github.teamcheeze"
version = "0.0.3"

repositories {
    mavenCentral()
}
subprojects {
    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    repositories {
        mavenCentral()
    }
    dependencies {
        compileOnly(kotlin("stdlib"))
        compileOnly("io.github.dolphin2410:jaw:1.0.0")
    }
    tasks {
        register<Jar>("javadocJar") {
            dependsOn("dokkaHtml")
            from("$buildDir/dokka/html/")
            include("**")
        }
    }
}
tasks {
    jar {
        subprojects.onEach { from(it.sourceSets["main"].output) }.forEach {
            from(it.sourceSets["main"].allSource)
        }
    }
    register<Jar>("javadocJar") {
        archiveClassifier.set("javadoc")
        subprojects.forEach {
            from((it.tasks["javadocJar"] as Jar).source)
        }
    }
    register<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        subprojects.forEach {
            from(it.sourceSets["main"].allSource)
            from(it.sourceSets["main"].output)
        }
    }
}

fun isSnapshot(ver: String): Boolean {
    return ver.matches("[\\.-](beta|snapshot|Beta|SNAPSHOT)$".toRegex())
}

publishing {
    publications {
        create<MavenPublication>("apiPub") {
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
                    url = if (isSnapshot(version)) {
                        uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                    } else {
                        uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                    }
                }
            }
            pom {
                name.set("plum-api")
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
    sign(tasks["javadocJar"], tasks["sourcesJar"])
    sign(publishing.publications["apiPub"])
}
