dependencies {
    implementation(project(":domain"))

    implementation("org.springframework:spring-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.liquibase:liquibase-core")
    runtimeOnly("org.postgresql:postgresql")
}