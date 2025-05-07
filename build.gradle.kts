plugins {
    java
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    // If using Java preview features
    options.compilerArgs.add("--enable-preview")
}

tasks.withType<JavaExec> {
    // If using Java preview features
    jvmArgs("--enable-preview")
}

group = "com.acey.adstext"
version = "1.1.1"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.essentialsx.net/snapshots/")
    maven("https://maven.enginehub.org/repo/")  // WorldGuard & WorldEdit
    maven("https://repo.codemc.io/repository/maven-public/")  // LuckPerms & HolographicDisplays
    maven("https://repo.onarandombox.com/releases/")  // Multiverse-Core
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.dmulloy2.net/repository/public/")  // Vault repository
    maven("https://jitpack.io")  // Add JitPack repository for VaultAPI
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7") {
        exclude(group = "org.bukkit", module = "bukkit")
    }
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.13")
    compileOnly("com.sk89q.worldedit:worldedit-bukkit:7.3.10")
    compileOnly("net.essentialsx:EssentialsX:2.21.0-SNAPSHOT")
}

configurations.all {
    resolutionStrategy.capabilitiesResolution.withCapability("org.spigotmc:spigot-api") {
        select("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    }
}
