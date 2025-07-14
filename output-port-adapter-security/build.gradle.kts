dependencies {
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-autoconfigure")
    implementation("org.springframework:spring-context")
    implementation("org.springframework.security:spring-security-crypto")
    implementation("jakarta.annotation:jakarta.annotation-api")

    implementation("io.jsonwebtoken:jjwt-api")

    runtimeOnly("io.jsonwebtoken:jjwt-jackson")
    runtimeOnly("io.jsonwebtoken:jjwt-impl")
}