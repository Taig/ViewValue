import android.Keys._
import io.taig.sbt.sonatype.SonatypeHouserulePlugin.autoImport._
import sbt._
import sbt.Keys._

object Settings {
    val common = Def.settings(
        javacOptions ++=
            "-source" :: "1.7" ::
            "-target" :: "1.7" ::
            Nil,
        organization := "io.taig.android",
        scalaVersion := "2.11.8",
        scalacOptions ++=
            "-feature" ::
            "-deprecation" ::
            Nil,
        githubProject := "viewvalue"
    )
    
    val android = Def.settings(
        platformTarget := "android-23",
        typedResources := false
    )
}