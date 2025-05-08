import org.nlogo.build.{ NetLogoExtension, ExtensionDocumentationPlugin }

enablePlugins(NetLogoExtension, ExtensionDocumentationPlugin)

name := "sound"
version := "1.1.1"
isSnapshot := true

netLogoVersion := "7.0.0-beta1"
netLogoClassManager := "org.nlogo.extensions.sound.SoundExtension"

Compile / javaSource := baseDirectory.value / "src" / "main" / "java"
javacOptions ++= Seq("-g", "-Xlint:deprecation", "-Xlint:all", "-Xlint:-serial", "-Xlint:-path", "-encoding", "us-ascii", "--release", "11")
