scalaVersion := "2.12.0"

enablePlugins(org.nlogo.build.NetLogoExtension, org.nlogo.build.ExtensionDocumentationPlugin)

name := "sound"

version := "1.1.0"

netLogoClassManager := "org.nlogo.extensions.sound.SoundExtension"

netLogoTarget :=
  org.nlogo.build.NetLogoExtension.directoryTarget(baseDirectory.value)

javaSource in Compile := baseDirectory.value / "src" / "main" / "java"

javacOptions ++= Seq("-g", "-Xlint:deprecation", "-Xlint:all", "-Xlint:-serial", "-Xlint:-path",
  "-encoding", "us-ascii")

netLogoVersion := "6.0.4-9328ba6"
