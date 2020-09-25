enablePlugins(org.nlogo.build.NetLogoExtension, org.nlogo.build.ExtensionDocumentationPlugin)

name := "sound"

version := "1.1.1"

netLogoClassManager := "org.nlogo.extensions.sound.SoundExtension"

netLogoTarget :=
  org.nlogo.build.NetLogoExtension.directoryTarget(baseDirectory.value)

javaSource in Compile := baseDirectory.value / "src" / "main" / "java"

javacOptions ++= Seq("-g", "-Xlint:deprecation", "-Xlint:all", "-Xlint:-serial", "-Xlint:-path",
  "-encoding", "us-ascii")

netLogoVersion := "6.1.1-c82c397"
