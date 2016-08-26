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
        minSdkVersion := "1",
        name := "viewvalue-core",
        packageForR := s"${organization.value}.resource"
    )

lazy val design = project
    .androidBuildWith( core )
    .settings( Settings.common ++ Settings.android )
    .settings(
        libraryDependencies ++=
            "com.android.support" % "design" % "24.2.0" ::
            Nil,
        minSdkVersion := "9",
        name := "viewvalue-design",
        packageForR := s"${organization.value}.design.resource"
    )