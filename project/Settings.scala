import android.Keys._
import io.taig.sbt.sonatype.SonatypeHouserulePlugin.autoImport._
import sbt._
import sbt.Keys._

object Settings {
    val common = Def.settings(
        githubProject := "viewvalue",
        javacOptions ++=
            "-source" :: "1.7" ::
            "-target" :: "1.7" ::
            Nil,
        name := s"viewvalue-${name.value}",
        organization := "io.taig.android",
        platformTarget := "android-24",
        scalaVersion := "2.11.8",
        scalacOptions ++=
            "-feature" ::
            "-deprecation" ::
            Nil,
        typedResources := false
    )
}