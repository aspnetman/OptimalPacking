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
}

plugins {
    java
}

group = "ru.hoff"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

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

    testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.jUnitJupiterApi}")
    testImplementation("org.assertj:assertj-core:${Versions.assertjCore}")

    testImplementation(platform("org.junit:junit-bom:${Versions.junitBom}"))
    testImplementation("org.junit.jupiter:junit-jupiter:${Versions.junitJupiter}")
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
