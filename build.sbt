lazy val root = project.in( file( "." ) )
    .settings( Settings.common )
    .settings(
        name := "viewvalue",
        publish := (),
        publishLocal := (),
        publishArtifact := false
    )
    .aggregate( core, base, design )

lazy val core = project
    .enablePlugins( AndroidLib )
    .settings( Settings.common ++ Settings.android )
    .settings(
        minSdkVersion := "1"
    )

lazy val base = project
    .enablePlugins( AndroidLib )
    .settings( Settings.common ++ Settings.android )
    .dependsOn( core )

lazy val design = project
    .enablePlugins( AndroidLib )
    .settings( Settings.common ++ Settings.android )
    .settings(
        libraryDependencies ++=
            "com.android.support" % "design" % "24.2.0" ::
            Nil,
        minSdkVersion := "9"
    )
    .dependsOn( base )