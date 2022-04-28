import org.nlogo.build.{ NetLogoExtension, ExtensionDocumentationPlugin }

enablePlugins(NetLogoExtension, ExtensionDocumentationPlugin)

name := "sound"
version := "1.1.1"
isSnapshot := true

netLogoVersion := "6.2.2"
netLogoClassManager := "org.nlogo.extensions.sound.SoundExtension"

javaSource in Compile := baseDirectory.value / "src" / "main" / "java"
javacOptions ++= Seq("-g", "-Xlint:deprecation", "-Xlint:all", "-Xlint:-serial", "-Xlint:-path", "-encoding", "us-ascii")
