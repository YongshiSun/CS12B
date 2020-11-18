#------------------------------------------------------------------------------
# Yongshi Sun ID:yosun date:4.12.19
# Makefile for Subset.java
#------------------------------------------------------------------------------
JAVASRC = Subset.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = Subset
CLASSES = Subset.class
JARFILE = Subset
SUBMIT = submit cmps012b-pt.s19 pa1

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

try:
	java $(JARFILE) 4 2

clean:
	rm $(CLASSES) $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)