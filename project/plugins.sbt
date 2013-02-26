resolvers += "Templemore Repository" at "http://templemore.co.uk/repo"

addSbtPlugin("templemore" % "sbt-cucumber-plugin" % "0.7.2")

//Plugin to automatically restart the server whenever code changes

addSbtPlugin("io.spray" % "sbt-revolver" % "0.6.2")