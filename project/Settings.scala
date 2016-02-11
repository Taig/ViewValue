import android.Keys._
import io.taig.sbt.sonatype.Plugin.autoImport._
import sbt.Keys._

object Settings {
    val common = Seq(
        organization := "io.taig.android.viewvalue",
        scalaVersion := "2.11.7",
        githubProject := "viewvalue"
    )
    
    val android = Seq(
        platformTarget := "android-23",
        typedResources := false
    )
}