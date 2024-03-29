import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val minecraft_version: String by project
val yarn_mappings: String by project
val loader_version: String by project
val mod_version: String by project
val maven_group: String by project
val fabric_version: String by project


plugins {
    id("maven-publish")
    id("fabric-loom") version "1.4-SNAPSHOT"
    kotlin("jvm") version "1.9.10"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

version = mod_version
group = maven_group

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        name = "Aliyun"
        url = uri("https://maven.aliyun.com/repository/public")
    }
    maven {
        name = "Fabric"
        url = uri("https://maven.fabricmc.net/")
    }
}

val libImpl by configurations.creating

dependencies {
    minecraft("com.mojang:minecraft:${minecraft_version}")
    mappings("net.fabricmc:yarn:${yarn_mappings}:v2")

    modImplementation("net.fabricmc:fabric-loader:${loader_version}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${fabric_version}")

    libImpl(kotlin("stdlib"))
    
    libImpl.dependencies.forEach {
        implementation(it)
    }

}

loom {
    accessWidenerPath.assign(file("src/main/resources/mycape.accesswidener"))
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand(mutableMapOf("version" to project.version))
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(17)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

java {
    withSourcesJar()
}

tasks.jar {
    from("LICENSE")
    from(libImpl.map { if (it.isDirectory) it else zipTree(it) })
}