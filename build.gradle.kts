import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.3.BUILD-SNAPSHOT"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.4.0"
	kotlin("plugin.spring") version "1.4.0"
	kotlin("plugin.jpa") version "1.4.0"
	kotlin("plugin.serialization") version "1.4.0"
}

group = "spring.kotlin"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

repositories {
	mavenCentral()
	jcenter()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-batch")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.github.java-json-tools:json-patch:1.13")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.11.2")
	implementation("com.fasterxml.jackson.core:jackson-annotations:2.11.2")
	implementation("com.fasterxml.jackson.core:jackson-core:2.11.2")
	implementation("com.soywiz.korlibs.klock:klock:1.8.4")
	implementation ("com.soywiz.korlibs.klock:klock-jvm:1.8.4")
	implementation(kotlin("stdlib", org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION))
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC")
	compileOnly("org.projectlombok:lombok")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.batch:spring-batch-test")
	testImplementation("io.kotest:kotest-runner-junit5:4.2.0.RC1")
	testImplementation("io.kotest:kotest-extensions-spring:4.2.0.RC1")
}

tasks.withType<Test> {
	useJUnitPlatform();
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}
