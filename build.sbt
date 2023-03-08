import org.nlogo.build.{ NetLogoExtension, ExtensionDocumentationPlugin }

enablePlugins(NetLogoExtension, ExtensionDocumentationPlugin)

name := "sound"
version := "1.1.1"
isSnapshot := true

netLogoVersion := "6.3.0"
netLogoClassManager := "org.nlogo.extensions.sound.SoundExtension"

Compile / javaSource := baseDirectory.value / "src" / "main" / "java"
javacOptions ++= Seq("-g", "-Xlint:deprecation", "-Xlint:all", "-Xlint:-serial", "-Xlint:-path", "-encoding", "us-ascii", "--release", "11")
