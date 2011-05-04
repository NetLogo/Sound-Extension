.DELETE_ON_ERROR:

# NETLOGO is the directory containing the NetLogo install we are
# building against.  if not specified, it defaults to two dirs up from
# this directory
ifeq ($(origin NETLOGO), undefined)
     NETLOGO =../..
endif

# JAVA_HOME is the base of the java installation we will build with
ifeq ($(origin JAVA_HOME), undefined)
  $(error JAVA_HOME must be defined.)
endif

ifeq ($(origin SCALA_JAR), undefined)
    SCALA_JAR = $(NETLOGO)/lib/scala-library.jar
endif


JAVA = $(JAVA_HOME)/bin/java
JAVAC = $(JAVA_HOME)/bin/javac
JAVACARGS=-g -deprecation -Xlint:all -Xlint:-serial -Xlint:-fallthrough -Xlint:-path -encoding us-ascii -source 1.5 -target 1.5
JAR = $(JAVA_HOME)/bin/jar
SLASH = /
COLON = :


OS = $(shell uname)

ifneq (,$(findstring CYGWIN,$(OS)))
SLASH = \\
COLON = \;
endif

SRCDIR = src
CLASSDIR = classes
LIBDIR = lib
DOCDIR = doc

# the name of the extension
EXTENSION=sound

# a list of external jars the extension needs to build and ship with
EXTERNAL_JARS=

# a space separated list of directories to include in the tarball
DISTDIRS=

# a space separated list of files to include in the tarball
DISTFILES=soundbank-min.gm

JAVAFILES = $(wildcard $(SRCDIR)/*.java)
CLASSFILES = $(patsubst $(SRCDIR)/%.java,$(CLASSDIR)/%.class,$(JAVAFILES))

empty=
space:=$(empty) $(empty)
NLJAR=$(NETLOGO)$(SLASH)NetLogo.jar
CLASSPATH=$(NLJAR)$(COLON)$(subst $(space),$(COLON),$(EXTERNAL_JARS))$(COLON)$(USERCLASSPATH)

BUILDPREFIX=build
BUILDDIR=$(BUILDPREFIX)/$(EXTENSION)
INSTALL=install


$(EXTENSION).jar: $(JAVAFILES) $(NLJAR) $(EXTERNAL_JARS) manifest.txt Makefile
	mkdir -p $(CLASSDIR)
	$(JAVAC) $(JAVACARGS) -classpath $(CLASSPATH)$(COLON)$(SCALA_JAR) -d $(CLASSDIR) $(JAVAFILES)
	$(JAR) cmf manifest.txt $(EXTENSION).jar -C $(CLASSDIR) .

.PHONY: clean
clean:
	rm -rf $(CLASSDIR)
	rm -f $(EXTENSION).jar
	rm -rf $(BUILDPREFIX)
	rm -f $(EXTENSION).tgz

.PHONY: prerelease
prerelease: $(EXTENSION).jar docs lib
	mkdir -p $(BUILDDIR)
	$(foreach jar, $(EXTERNAL_JARS), cp $(jar) $(BUILDDIR);)
	$(foreach distfile, $(DISTFILES), cp $(distfile) $(BUILDDIR);)
	$(foreach distdir, $(DISTDIRS), cp -r $(distdir) $(BUILDDIR);)
	if [ -d $(DOCDIR) ]; then cp -r $(DOCDIR) $(BUILDDIR); fi
	if [ -d $(LIBDIR) ]; then cp -r $(LIBDIR) $(BUILDDIR); fi
	if [ -d $(SRCDIR) ]; then cp -r $(SRCDIR) $(BUILDDIR); fi
	if [ -f README.txt ]; then cp -r README.txt $(BUILDDIR); fi
	cp $(EXTENSION).jar $(BUILDDIR)
	cp Makefile config.mk manifest.txt $(BUILDDIR)
	if test -n "$$(find . -maxdepth 1 -name '*.nlogo' -print -quit)" ; then cp *.nlogo $(BUILDDIR); fi
	if test -n "$$(find . -maxdepth 1 -name '*.nlogo3d' -print -quit)" ; then cp *.nlogo3d $(BUILDDIR); fi
	(cd $(BUILDPREFIX); /usr/bin/find . -name CVS -print0 | xargs -0 rm -rf)
	(cd $(BUILDPREFIX); /usr/bin/find . -name .svn -print0 | xargs -0 rm -rf)

$(EXTENSION).tgz: prerelease
	tar czfC $@ $(BUILDPREFIX) $(EXTENSION)

$(EXTENSION).zip: prerelease
	cd $(BUILDPREFIX); zip -r ../$(EXTENSION).zip $(EXTENSION)
