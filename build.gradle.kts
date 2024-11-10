plugins {
  kotlin("jvm") version "2.0.21"
  id("co.uzzu.dotenv.gradle") version "4.0.0"
}

group = "technology.idlab"

version = "0.0.1"

kotlin { jvmToolchain(22) }

repositories {
  mavenCentral()

  maven {
    url = uri("https://maven.pkg.github.com/rdf-connect/orchestrator")
    credentials {
      username = env.fetchOrNull("GITHUB_ACTOR") ?: System.getenv("GITHUB_ACTOR")
      password = env.fetchOrNull("GITHUB_TOKEN") ?: System.getenv("GITHUB_TOKEN")
    }
  }
}

dependencies {
  // RDFC SDK
  implementation("technology.idlab:rdfc-core:0.0.1")
  implementation("technology.idlab:rdfc-processor:0.0.1")

  // Kotlin extensions.
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

  // HTTP dependencies
  implementation("io.ktor:ktor-client-core:2.3.10")
  implementation("io.ktor:ktor-client-cio:2.3.10")
  implementation("io.ktor:ktor-server-core:2.3.10")
  implementation("io.ktor:ktor-server-netty:2.3.10")
  testImplementation("io.ktor:ktor-client-mock:2.3.10")

  // Initialize testing.
  testImplementation("org.jetbrains.kotlin:kotlin-test")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
}

tasks.test {
  useJUnitPlatform()

  testLogging { showStandardStreams = true }
}
