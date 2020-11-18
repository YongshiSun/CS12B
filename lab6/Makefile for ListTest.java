#------------------------------------------------------------------------------
#   Makefile for List ADT
#------------------------------------------------------------------------------

# Variables
JARFILE    = ListTest
MAINCLASS  = ListTest
SOURCES    = ListInterface.java ListIndexOutOfBoundsException.java List.java \
             ListTest.java
CLASSES    = ListInterface.class ListIndexOutOfBoundsException.class \
             List.class List\$$Node.class ListTest.class

# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm -f Manifest $(CLASSES)
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint $(SOURCES)

# Phony Targets
clean:
	rm -f $(JARFILE)

run:
	java -jar $(JARFILE) > my-out

diff:
	diff my-out model-out