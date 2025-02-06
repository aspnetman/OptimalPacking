object Versions {
    const val lombok = "1.18.36"
    const val mapstruct = "1.6.3"
    const val pipelinr = "0.9"
    const val gson = "2.11.0"
    const val commonsCli = "1.9.0"
    const val telegramBots = "6.9.7.1"
    const val slf4jApi = "2.0.7"
    const val logbackCore = "1.5.13"
    const val logbackClassic = "1.5.13"
    const val jUnitJupiterApi = "5.11.4"
    const val assertjCore = "3.27.2"
    const val junitBom = "5.10.0"
    const val junitJupiter = "5.10.0"
    const val springdocOpenApiVersion = "2.8.3"
    const val springCloudStarterStreamKafka = "4.2.0"
    const val shedlock = "5.16.0"
}

plugins {
    id("java")
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "ru.hoff"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val mockitoAgent = configurations.create("mockitoAgent")

dependencies {
    compileOnly("org.projectlombok:lombok:${Versions.lombok}")
    annotationProcessor("org.projectlombok:lombok:${Versions.lombok}")
    annotationProcessor("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    implementation("org.mapstruct:mapstruct:${Versions.mapstruct}")
    implementation("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
    implementation("net.sizovs:pipelinr:${Versions.pipelinr}")
    implementation("com.google.code.gson:gson:${Versions.gson}")
    implementation("commons-cli:commons-cli:${Versions.commonsCli}")
    implementation("org.telegram:telegrambots:${Versions.telegramBots}")
    implementation("org.slf4j:slf4j-api:${Versions.slf4jApi}")
    implementation("ch.qos.logback:logback-core:${Versions.logbackCore}")
    implementation("ch.qos.logback:logback-classic:${Versions.logbackClassic}")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${Versions.springdocOpenApiVersion}")
    implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka:${Versions.springCloudStarterStreamKafka}")
    implementation("net.javacrumbs.shedlock:shedlock-provider-jdbc-template:${Versions.shedlock}")
    implementation("net.javacrumbs.shedlock:shedlock-spring:${Versions.shedlock}")


    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.jUnitJupiterApi}")
    testImplementation("org.assertj:assertj-core:${Versions.assertjCore}")
    testImplementation(platform("org.junit:junit-bom:${Versions.junitBom}"))
    testImplementation("org.junit.jupiter:junit-jupiter:${Versions.junitJupiter}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-junit-jupiter")

    testAnnotationProcessor("org.projectlombok:lombok:${Versions.lombok}")
    testAnnotationProcessor("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
}

tasks {
    withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.displayName()
    }

    withType<Javadoc> {
        options.encoding = Charsets.UTF_8.displayName()
    }

    test {
        useJUnitPlatform()
    }
}
