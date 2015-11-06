SBT JMH (seed)
==============

[OpenJDK JMH](http://openjdk.java.net/projects/code-tools/jmh/) is "the definitive" Java benchmarking tool.
Using the sbt-jmh plugin you can write and run these benchmark seamlessly within SBT.

This template simply includes the [sbt-jhm plugin](https://github.com/ktoso/sbt-jmh), which does all the work to make JMH work with SBT.

Setup
-----

1. [Download Typesafe Activator](http://typesafe.com/platform/getstarted) (or copy it over from a USB)
2. Extract the zip and run the `activator` or `activator.bat` script from a non-interactive shell
3. Your browser should open to the Activator UI: [http://localhost:8888](http://localhost:8888)

Check the example benchmarks
----------------------------

This template includes one example benchmark, but in order to fully understand JMH it's recomended to check out the OpenJDK repository with [all JMH samples](http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/).

Open in an IDE
--------------

If you want to use an IDE (Eclipse or IntelliJ), click on *Code*, select *Open*, and then select your IDE.  This will walk you through the steps to generate the project files and open the project.  Alternatively you can edit files in the Activator UI.


Update Dependencies
-------------------

The latest version of sbt-jmh can be found on: [https://github.com/ktoso/sbt-jmh](https://github.com/ktoso/sbt-jmh).
