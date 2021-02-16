name := "functional-examples"

version := "1.0"

scalaVersion := "2.12.8"

assemblyJarName in assembly := "hello-scala.jar"

// resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % Test,
  "org.mockito" % "mockito-core" % "1.10.19" % Test,
  "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0" % Test,
  "org.scalatest" %% "scalatest" % "3.0.1" % Test
)

// https://github.com/aws/aws-lambda-java-libs

scalacOptions ++= Seq(
  "-deprecation",         // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",   // Specify character encoding used by source files.
  "-explaintypes",        // Explain type errors in more detail.
  "-feature",             // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked",           // Enable additional warnings where generated code depends on assumptions.
  "-Xcheckinit",          // Wrap field accessors to throw an exception on uninitialized access.
  "-Xfatal-warnings"      // Fail the compilation if there are any warnings.
)




