plugins {
	id("mod-platform")
	id("net.minecraftforge.gradle")
}

fun prop(key: String) = project.property(key) as String

platform {
	loader = "forge"
	dependencies {
		required("minecraft") {
			forgeVersionRange = "[${prop("deps.minecraft")}]"
		}
		required("forge") {
			forgeVersionRange = "[1,)"
		}
	}
}

minecraft {
	mappings("official", prop("deps.minecraft"))

	val atFile = rootProject.file("src/main/resources/aw/${stonecutter.current.version}.cfg")
	if (atFile.exists()) {
		accessTransformers = files(atFile)
	}

	runs {
		configureEach {
			workingDir.convention(layout.projectDirectory.dir("run"))
			systemProperty("forge.logging.console.level", "debug")
			args("--mixin.config=${prop("mod.id")}.mixins.json")
		}
		register("client") {
			args("--username", "Player")
		}
		register("server") {
			args("--nogui")
		}
	}
}

sourceSets.configureEach {
	val dir = layout.buildDirectory.dir("sourcesSets/$name")
	output.setResourcesDir(dir)
	java.destinationDirectory.set(dir)
}

repositories {
	minecraft.mavenizer(this)
	maven(fg.forgeMaven)
	maven(fg.minecraftLibsMaven)
	strictMaven("https://api.modrinth.com/maven", "maven.modrinth") { name = "Modrinth" }
	mavenCentral()
}

// Forge jarJar doesn't work correctly
val jarJarLibs = configurations.create("jarJarLibs") {
	isTransitive = false
	isCanBeConsumed = false
}

val jarJarOut = layout.buildDirectory.dir("generated/jarjar")

val generateJarJar = tasks.register("generateJarJar") {
	inputs.files(jarJarLibs)
	outputs.dir(jarJarOut)
	doLast {
		val root = jarJarOut.get().asFile
		root.deleteRecursively()
		val dir = File(root, "META-INF/jarjar").also { it.mkdirs() }
		val entries = jarJarLibs.resolvedConfiguration.resolvedArtifacts.map { a ->
			val id = a.moduleVersion.id
			a.file.copyTo(File(dir, a.file.name), overwrite = true)
			"""{"identifier":{"group":"${id.group}","artifact":"${id.name}"},""" +
					""""version":{"range":"[${id.version},)","artifactVersion":"${id.version}"},""" +
					""""path":"META-INF/jarjar/${a.file.name}","isObfuscated":false}"""
		}
		File(dir, "metadata.json").writeText("""{"jars":[${entries.joinToString(",")}]}""")
	}
}

tasks.named<Jar>("jar") {
	dependsOn(generateJarJar)
	from(jarJarOut)
}

dependencies {
	implementation(minecraft.dependency("net.minecraftforge:forge:${prop("deps.forge")}"))
	annotationProcessor("org.spongepowered:mixin:${libs.versions.mixin.get()}:processor")
	annotationProcessor("io.github.llamalad7:mixinextras-common:${libs.versions.mixinextras.get()}")

	if (stonecutter.eval(stonecutter.current.version, "< 1.21.9")) {
		compileOnly("io.github.llamalad7:mixinextras-common:${libs.versions.mixinextras.get()}")
		implementation("io.github.llamalad7:mixinextras-forge:${libs.versions.mixinextras.get()}")
		jarJarLibs("io.github.llamalad7:mixinextras-forge:${libs.versions.mixinextras.get()}")

		jarJarLibs(libs.moulberry.mixinconstraints)
	}
	implementation(libs.moulberry.mixinconstraints)

}

tasks.withType<Jar>().matching { it.name == "jar" || it.name == "jarJar" }.configureEach {
	manifest {
		attributes["MixinConfigs"] = "${prop("mod.id")}.mixins.json"
	}

	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

sourceSets {
	main {
		resources.srcDir(
			"${rootDir}/versions/datagen/${stonecutter.current.version.split("-")[0]}/src/main/generated"
		)
	}
}

stonecutter {
	replacements.string(current.parsed >= "1.21.11" && current.parsed > "1.21.8") {
		replace("ResourceLocation", "Identifier")
		replace("location()", "identifier()")
	}
}