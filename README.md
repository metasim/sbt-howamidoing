# SBT HowAmIDoing Plugin

This is an SBT 0.13.x plugin for recording over time in simple CSV format statistics on test results.

## Building 

You can build and publish the plugin in the normal way to your local Ivy repository:

```
sbt publish-local
```

## Installation

Check out the project and build as detailed above. Then in `project/plugins.sbt` add:

```
addSbtPlugin("eri" % "sbt-howamidoing" % "1.0.0-SNAPSHOT")
```

In `build.sbt`:

```
val myProject = (project in file(".")).enablePlugins(SbtHowAmIDoing)
```

## Usage

**TODO**
