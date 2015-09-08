# NetLogo sound extension

This package contains the NetLogo sound extension.

## Using

The sound extension is pre-installed in NetLogo. For instructions on using sound in your model, or for more information about NetLogo extensions, see the NetLogo User Manual.

For models that use this extension, see Code Examples/Sound in the models library.

## Building

Use the netlogo.jar.url environment variable to tell sbt which NetLogo.jar to compile against (defaults to NetLogo 5.3). For example:

    sbt -Dnetlogo.jar.url=file:///path/to/NetLogo/target/NetLogo.jar

If compilation succeeds, `sound.jar` will be created.

## Terms of Use

[![CC0](http://i.creativecommons.org/p/zero/1.0/88x31.png)](http://creativecommons.org/publicdomain/zero/1.0/)

The NetLogo sound extension is in the public domain.  To the extent possible under law, Uri Wilensky has waived all copyright and related or neighboring rights.
