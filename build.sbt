lazy val root = project.in( file( "." ) )
    .enablePlugins( AndroidLib )
    .settings( Settings.common )
    .settings(
        name := "viewvalue"
    )
    .aggregate( core, base, design )
    .dependsOn( core, base, design )

lazy val core = project
    .enablePlugins( AndroidJar )
    .settings( Settings.common )
    .settings(
        minSdkVersion := "1"
    )

lazy val base = project
    .enablePlugins( AndroidLib )
    .settings( Settings.common )
    .dependsOn( core )

lazy val design = project
    .enablePlugins( AndroidLib )
    .settings( Settings.common )
    .settings(
        libraryDependencies ++=
            "com.android.support" % "design" % "25.0.1" ::
            Nil,
        minSdkVersion := "9"
    )
    .dependsOn( base )