enablePlugins( AndroidLib )

githubProject := "viewvalue"

javacOptions ++=
    "-source" :: "1.7" ::
    "-target" :: "1.7" ::
    Nil

libraryDependencies ++=
    "com.android.support" % "design" % "25.1.0" ::
    Nil

minSdkVersion := "7"

name := "viewvalue"

organization := "io.taig.android"

platformTarget := "android-24"

scalaVersion := "2.11.8"

scalacOptions ++=
    "-feature" ::
    "-deprecation" ::
    Nil

typedResources := false