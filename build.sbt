lazy val root = project.in( file( "." ) )
    .settings( Settings.common )
    .settings(
        name := "viewvalue"
    )
    .aggregate( core, base, design )
    .dependsOn( core, base, design )

lazy val core = project
    .enablePlugins( AndroidJar )
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
            "com.android.support" % "design" % "25.0.1" ::
            Nil,
        minSdkVersion := "9"
    )
    .dependsOn( base )