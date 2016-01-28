JFLAGS = -g
JC = javac
JVM = java

%.class: %.java
    $(JC) $(JFLAGS) $<

SRCS := $(wildcard src/com/github/jotask/groupproject/*.java)
SRCS += $(wildcard src/com/github/jotask/groupproject/util/*.java)
SRCS += $(wildcard src/com/github/jotask/groupproject/model/*.java)
SRCS += $(wildcard src/com/github/jotask/groupproject/gui/*.java)
SRCS += $(wildcard src/com/github/jotask/groupproject/connection/*.java)
SRCS += $(wildcard src/com/github/jotask/groupproject/connection/dao/*.java)

CLASSES := $(SRCS:.java=.class)

MAIN = Application

.PHONY:
    run

run:$(CLASSES)
    $(JVM) $(MAIN)

.PHONY:
    clean

clean:
    $(RM) $(CLASSES)

.PHONY:
    classes

classes:
    $(CLASSES)