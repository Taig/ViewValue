lazy val root = project.in( file( "." ) )
    .settings( Settings.common )
    .settings(
        name := "viewvalue",
        publish := (),
        publishLocal := (),
        publishArtifact := false
    )
    .aggregate( core, design )

lazy val core = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )
    .settings(
        minSdkVersion := "1"
    )

lazy val design = project
    .androidBuildWith( core )
    .settings( Settings.common ++ Settings.android )
    .settings(
        libraryDependencies ++=
            "com.android.support" % "design" % "24.2.0" ::
            Nil,
        minSdkVersion := "9"
    )