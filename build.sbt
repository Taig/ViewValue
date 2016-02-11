lazy val root = project.in( file( "." ) )
    .settings( Settings.common )
    .settings(
        name := "ViewValue",
        normalizedName := "view-value",
        publish := (),
        publishLocal := (),
        publishArtifact := false
    )
    .aggregate( core, design )

lazy val core = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )
    .settings(
        minSdkVersion := "1",
        packageForR := s"${organization.value}.resource"
    )

lazy val design = project
    .androidBuildWith( core )
    .settings( Settings.common ++ Settings.android )
    .settings(
        libraryDependencies ++=
            "com.android.support" % "design" % "23.1.1" ::
            Nil,
        minSdkVersion := "7",
        packageForR := s"${organization.value}.design.resource"
    )
    .dependsOn( core )