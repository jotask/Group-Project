JFLAGS = -g
JC = javac
JVM = java

# set a --classpath

%.class: %.java
	$(JC) $(JFLAGS) $<

#SRCS := $(wildcard src/com/github/jotask/groupproject/*.java)
#SRCS += $(wildcard src/com/github/jotask/groupproject/util/*.java)
#SRCS += $(wildcard src/com/github/jotask/groupproject/model/*.java)
#SRCS += $(wildcard src/com/github/jotask/groupproject/gui/*.java)
#SRCS += $(wildcard src/com/github/jotask/groupproject/connection/*.java)
#SRCS += $(wildcard src/com/github/jotask/groupproject/connection/dao/*.java)

SRCS := $(wildcard src/com/github/jotask/groupproject/util/MD5.java)
SRCS += $(wildcard src/com/github/jotask/groupproject/util/Util.java)
SRCS += $(wildcard src/com/github/jotask/groupproject/model/Element.java)
SRCS += $(wildcard src/com/github/jotask/groupproject/model/Team.java)
SRCS += $(wildcard src/com/github/jotask/groupproject/model/Task.java)
#SRCS += $(wildcard src/com/github/jotask/groupproject/connection/*.java)
#SRCS += $(wildcard src/com/github/jotask/groupproject/connection/dao/*.java)
#SRCS += $(wildcard src/com/github/jotask/groupproject/gui/*.java)
#SRCS += $(wildcard src/com/github/jotask/groupproject/*.java)

CLASSES := $(SRCS:.java=.class)

MAIN=Application

.PHONY: run
run: $(CLASSES)
	$(JVM) $(MAIN)

.PHONY: clean
clean:
	$(RM) $(CLASSES)

.PHONY: classes
classes: $(CLASSES)