plugins {
    id("java")
}

group = "braydo.link-tree"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("com.titusfortner:selenium-logger:2.4.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.47.0")
}

tasks.test {
    useJUnitPlatform()
}