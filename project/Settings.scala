import android.Keys._
import io.taig.sbt.sonatype.Plugin.autoImport._
import sbt.Keys._

object Settings {
    val common = Seq(
        javacOptions ++=
            "-source" :: "1.7" ::
            "-target" :: "1.7" ::
            Nil,
        organization := "io.taig.android.viewvalue",
        scalaVersion := "2.11.7",
        scalacOptions ++=
            "-feature" ::
            "-deprecation" ::
            Nil,
        githubProject := "viewvalue"
    )
    
    val android = Seq(
        platformTarget := "android-23",
        typedResources := false
    )
}