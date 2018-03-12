# Project Creation Notes

## Configure SBT

### Determine Requirements

The SBT version must be 0.13.5 or higher in order to support the use of the SBT
shell for IntelliJ build and import functionality. As of March 4, 2018, the
current version of SBT is 1.1.1.

### Check SBT Version

The `sbt` command creates a `target` directory every time it is run, even for
simple things like checking the SBT version; this can create litter in your
working directory:

https://github.com/sbt/sbt/issues/2835

To avoid this, change to a temporary directory before running `sbt sbtVersion`
to check the SBT version; for example:

```console
$ cd /tmp
$ sbt sbtVersion
[warn] No sbt.version set in project/build.properties, base directory: /private/tmp
[info] Loading settings from idea.sbt ...
[info] Loading global plugins from /Users/michael/.sbt/1.0/plugins
[info] Updating ProjectRef(uri("file:/Users/michael/.sbt/1.0/plugins/"), "global-plugins")...
[info] Done updating.
[info] Set current project to tmp (in build file:/private/tmp/)
[info] 1.1.1
```

### Restrict SBT to Java 8

The Play Framework does not work properly on Java 9, as documented here:

https://github.com/playframework/playframework/issues/7879

Although this bug specifically mentions Play 2.6.5, the problem persists in
Play 2.6.12, the current version as of March 4, 2018.

In order to ensure viable Play project creation, restrict SBT to Java 8 by
setting the `-java-home` parameter in `/etc/sbt/sbtopts` (Linux) or
`/usr/local/etc/sbtopts` (macOS); for example:

```console
$ diff /usr/local/etc/sbtopts.2018-03-03 /usr/local/etc/sbtopts
48c48
< #-java-home <path>
---
> -java-home /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home
```

## Create New Project

### Use `SBT new`

Creation of a Play 2.6.12 project in IntelliJ 2017.3.4 does not seem to work
properly; the only reliable method seems to be to use `sbt new` on the command
line; for example:

```console
$ sbt new playframework/play-java-seed.g8
[info] Loading settings from idea.sbt ...
[info] Loading global plugins from /Users/michael/.sbt/1.0/plugins
[info] Updating ProjectRef(uri("file:/Users/michael/.sbt/1.0/plugins/"), "global-plugins")...
[info] Done updating.
[info] Set current project to play-framework (in build file:/Users/michael/git/play-framework/)

This template generates a Play Java project 

name [play-java-seed]: helloworld
organization [com.example]: net.avax
scala_version [2.12.4]: 
play_version [2.6.12]: 
sbt_version [1.0.4]: 

Template applied in ./helloworld

```

Note that the suggested SBT version (1.0.4 above) may be lower than the
installed version (1.1.1 in this case).

### Clean Up `target` Directory

As mentioned above, the `sbt` command creates litter in the current directory;
remember to clean up the unneeded `target` directory after running `sbt new`:

```console
$ rm -Rf target
```

### Set Up GNU Privacy Guard (GPG) Before Committing

If you would like your GitHub commits to appear as “verified,” then you must set
up GNU Privacy Guard (GPG), as described in the file `GPG.md` in this directory.

### Commit Before Importing into IntelliJ

Be sure to commit the project immediately after creation in case the attempt to
import it into IntelliJ breaks the build configuration:

```console
$ git add helloworld
$ git commit
[master b4fb925] Create helloworld from play-java-seed
 25 files changed, 600 insertions(+)
 create mode 100644 helloworld/.g8/form/app/controllers/$model__Camel$Controller.java
 create mode 100644 helloworld/.g8/form/app/controllers/$model__Camel$Data.java
 create mode 100644 helloworld/.g8/form/app/views/$model__camel$/form.scala.html
 create mode 100644 helloworld/.g8/form/default.properties
 create mode 100644 helloworld/.g8/form/generated-test/README.md
 create mode 100644 helloworld/.g8/form/generated-test/controllers/$model__Camel$ControllerTest.java
 create mode 100644 helloworld/app/controllers/HomeController.java
 create mode 100644 helloworld/app/views/index.scala.html
 create mode 100644 helloworld/app/views/main.scala.html
 create mode 100644 helloworld/build.gradle
 create mode 100644 helloworld/build.sbt
 create mode 100644 helloworld/conf/application.conf
 create mode 100644 helloworld/conf/logback.xml
 create mode 100644 helloworld/conf/routes
 create mode 100644 helloworld/gradle/wrapper/gradle-wrapper.jar
 create mode 100644 helloworld/gradle/wrapper/gradle-wrapper.properties
 create mode 100755 helloworld/gradlew
 create mode 100644 helloworld/gradlew.bat
 create mode 100644 helloworld/project/build.properties
 create mode 100644 helloworld/project/plugins.sbt
 create mode 100644 helloworld/project/scaffold.sbt
 create mode 100644 helloworld/public/images/favicon.png
 create mode 100644 helloworld/public/javascripts/main.js
 create mode 100644 helloworld/public/stylesheets/main.css
 create mode 100644 helloworld/test/controllers/HomeControllerTest.java
```

### Add `.gitignore` Before Importing into IntelliJ

To ensure that transient Play and IntelliJ files are not committed to Git, add
a `.gitignore` file consisting of the recommended IntelliJ and Play defaults to
the Play project directory after the initial commit but prior to the IntelliJ
import:

```console
$ cat helloworld/.gitignore
# JetBrains .gitignore:
#
# https://raw.githubusercontent.com/github/gitignore/master/Global/JetBrains.gitignore

# Covers JetBrains IDEs: IntelliJ, RubyMine, PhpStorm, AppCode, PyCharm, CLion, Android Studio and Webstorm
# Reference: https://intellij-support.jetbrains.com/hc/en-us/articles/206544839

# User-specific stuff:
.idea/**/workspace.xml
.idea/**/tasks.xml
.idea/dictionaries

# Sensitive or high-churn files:
.idea/**/dataSources/
.idea/**/dataSources.ids
.idea/**/dataSources.xml
.idea/**/dataSources.local.xml
.idea/**/sqlDataSources.xml
.idea/**/dynamic.xml
.idea/**/uiDesigner.xml

# Gradle:
.idea/**/gradle.xml
.idea/**/libraries

# CMake
cmake-build-debug/

# Mongo Explorer plugin:
.idea/**/mongoSettings.xml

## File-based project format:
*.iws

## Plugin-specific files:

# IntelliJ
out/

# mpeltonen/sbt-idea plugin
.idea_modules/

# JIRA plugin
atlassian-ide-plugin.xml

# Cursive Clojure plugin
.idea/replstate.xml

# Crashlytics plugin (for Android Studio and IntelliJ)
com_crashlytics_export_strings.xml
crashlytics.properties
crashlytics-build.properties
fabric.properties

# Play Framework .gitignore:
#
# https://www.playframework.com/documentation/2.6.x/Anatomy#Typical-.gitignore-file

logs
project/project
project/target
target
tmp
dist
.cache
```

### Import into IntelliJ

1.  Use the __Import Project__ option.
1.  Browse to the `build.sbt` file and press __Open__.
1.  Choose the following options and press __OK__:
    * Group modules: ___Using explicit module groups___
    * Download
      * Library sources: ___checked___
      * Sbt sources: ___unchecked___
    * Use sbt shell for build and import (requires sbt 0.13.5+): ___checked___
    * Project JDK: __1.8__
    * Project format: __.idea (directory based)__
    * Global sbt settings
      * JVM
        * From project JDK: ___checked___
        * Custom: ___unchecked___
      * JVM Options
        * Maximum heap size, MB: __768__
        * VM parameters: ___\[leave blank\]___
      * Launcher (sbt-launch.jar)
        * Bundled: ___checked___
        * Custom: ___unchecked___
1.  If you see the message __Unregistered VCS root detected__, press
    __Add root__.
1.  If you see the mesage __Unlinked Gradle project?__, do not choose the
    __Import Gradle project__ option; use the __press here__ option to suppress
    the message for the project.
1.  In the __Project__ pane, right-click __build.sbt__ and select __Run Play 2
    App__.
1.  In the __Play 2__ drop-down in the toolbar, select __Edit
    Configurations&hellip;__, choose the following options, and press __OK__:
    *   Name: __Play 2__
    *   Share: ___unchecked___
    *   Single instance only: ___unchecked___
    *   Open in browser
        * Open in browser after compilation: ___checked___
        * Url to Open: __http\://localhost:9000__
    *   JVM Options: __-Xms512M -Xmx1024M -Xss1M -XX:+CMSClassUnloadingEnabled__
        ___\[remove -XX:MaxPermSize=256M\]___
    *   Environment variables: ___\[leave blank\]___
    *   Use non-default Play 2 install dir: ___unchecked___
    *   Debug port: __9999__
    *   Enable auto-reload: ___unchecked___
    *   Play2 Module: __root__
    *   Before launch: Build, Activate tool window: __Build__
    *   Show this page: ___unchecked___
    *   Activate tool window: ___checked___
1.  In the __Play 2__ drop-down in the toolbar, select __Save 'Play 2'
    Configuration__.
1.  Close the project.

### Commit Again After Importing into IntelliJ

Be sure to commit the project immediately after importing it into IntelliJ in
order to provide a known good baseline to which the project can be
compared&mdash;and, if necessary, reverted&mdash;later:

```console
$ git add helloworld
$ git commit
[master 0afaf76] Import helloworld with sbt shell for build
 11 files changed, 568 insertions(+)
 create mode 100644 helloworld/.gitignore
 create mode 100644 helloworld/.idea/compiler.xml
 create mode 100644 helloworld/.idea/misc.xml
 create mode 100644 helloworld/.idea/modules.xml
 create mode 100644 helloworld/.idea/modules/root-build.iml
 create mode 100644 helloworld/.idea/modules/root.iml
 create mode 100644 helloworld/.idea/play2_project_settings.xml
 create mode 100644 helloworld/.idea/play2_settings.xml
 create mode 100644 helloworld/.idea/sbt.xml
 create mode 100644 helloworld/.idea/scala_compiler.xml
 create mode 100644 helloworld/.idea/vcs.xml
```
