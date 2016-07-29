import com.typesafe.sbt.packager.archetypes.ServerLoader

name := """hashworkapi"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala,DebianPlugin,JavaServerAppPackaging)

scalaVersion := "2.11.8"

val PhantomVersion = "1.27.0"

maintainer := "Boniface Kabaso <boniface@kabaso.com>"
packageSummary in Linux := "Hashwork REST API"
packageDescription :=  "Hashwork API Backend "
serverLoading in Debian := ServerLoader.SystemV

bashScriptExtraDefines ++= Seq(
  """addJava "-Xms1024m"""",
  """addJava "-Xmx2048m""""
)

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "com.websudos"  % "phantom-dsl_2.11"              % PhantomVersion,
  "com.websudos" % "phantom-reactivestreams_2.11"   % PhantomVersion,
  "org.scalatest" % "scalatest_2.11"                % "2.2.6",
  "org.apache.logging.log4j" % "log4j-slf4j-impl"   % "2.4.1",
  "org.apache.logging.log4j" % "log4j-api"          % "2.4.1",
  "org.apache.logging.log4j" % "log4j-core"         % "2.4.1",
  "com.chuusai" %% "shapeless"                      % "2.2.5"
)
// https://mvnrepository.com/artifact/io.getclump/clump_2.11
libraryDependencies += "io.getclump" % "clump_2.11" % "0.0.11"
libraryDependencies += "io.monix" %% "monix" % "2.0-RC7"
libraryDependencies += "me.lessis" %% "courier" % "0.1.3"
libraryDependencies += "com.jason-goodwin" % "authentikat-jwt_2.11" % "0.4.1"
libraryDependencies += "me.lessis" % "base64_2.11" % "0.2.0"
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "org.apache.tika" % "tika-core" % "1.11"
libraryDependencies += "com.github.t3hnar" % "scala-bcrypt_2.11" % "2.5"
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.4"
libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.4"
libraryDependencies += "org.apache.tika" % "tika" % "1.11"
libraryDependencies += "com.sksamuel.scrimage" % "scrimage-core_2.11" % "2.1.1"
libraryDependencies += "org.imgscalr" % "imgscalr-lib" % "4.2"
// redis-server cache
libraryDependencies += "com.github.karelcemus" %% "play-redis" % "1.2.0"
libraryDependencies += "com.github.romix.akka" %% "akka-kryo-serialization" % "0.4.1"
// https://mvnrepository.com/artifact/com.esotericsoftware.kryo/kryo
libraryDependencies += "com.esotericsoftware.kryo" % "kryo" % "2.24.0"


// repository with the Brando connector
resolvers += "Brando Repository" at "http://chrisdinn.github.io/releases/"
resolvers += "softprops-maven" at "http://dl.bintray.com/content/softprops/maven"
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
libraryDependencies += filters

resolvers ++= Seq(
  "Typesafe repository snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype repo"                    at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Sonatype releases"                at "https://oss.sonatype.org/content/repositories/releases",
  "Sonatype snapshots"               at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype staging"                 at "http://oss.sonatype.org/content/repositories/staging",
  "Java.net Maven2 Repository"       at "http://download.java.net/maven/2/",
  "Twitter Repository"               at "http://maven.twttr.com",
  "Websudos releases"                at "https://dl.bintray.com/websudos/oss-releases/"
)
resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)
