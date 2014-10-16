ifeq ($(origin JAVA_HOME), undefined)
  JAVA_HOME=/usr
endif

ifneq (,$(findstring CYGWIN,$(shell uname -s)))
  JAVA_HOME := `cygpath -up "$(JAVA_HOME)"`
endif

JAVAC=$(JAVA_HOME)/bin/javac
SRCS=$(wildcard src/*.java)

sound.jar sound.jar.pack.gz: $(SRCS) manifest.txt NetLogoHeadless.jar Makefile
	mkdir -p classes
	$(JAVAC) -g -deprecation -Xlint:all -Xlint:-serial -Xlint:-path -encoding us-ascii -source 1.7 -target 1.7 -classpath NetLogoHeadless.jar -d classes $(SRCS)
	jar cmf manifest.txt sound.jar -C classes .
	pack200 --modification-time=latest --effort=9 --strip-debug --no-keep-file-order --unknown-attribute=strip sound.jar.pack.gz sound.jar

NetLogoHeadless.jar:
	curl -f -s -S 'http://ccl.northwestern.edu/devel/6.0-M1/NetLogoHeadless.jar' -o NetLogoHeadless.jar

sound.zip: sound.jar
	rm -rf sound
	mkdir sound
	cp -rp sound.jar sound.jar.pack.gz README.md Makefile src manifest.txt soundbank-min.gm sound
	zip -rv sound.zip sound
	rm -rf sound
