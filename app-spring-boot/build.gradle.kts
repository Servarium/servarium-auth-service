import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":input-port-adapter-rest-api"))
    implementation(project(":output-port-adapter-persistence"))
    implementation(project(":output-port-adapter-security"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks {
    named<BootJar>("bootJar") {
        layered {
            enabled = true
        }
        archiveFileName.set("servarium.jar")
        mainClass.set("app.servarium.ServariumApplication")
    }

    named<Jar>("jar") {
        enabled = false
    }

    named("build") {
        dependsOn("bootJar")
    }
}