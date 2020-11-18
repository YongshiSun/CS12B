#------------------------
# Yongshi Sun ID:1619410
# makefile for Queens.java
# --------------------------

Queens: Queens.class
	echo Main-class: Queens > Manifest
	jar cvfm Queens Manifest Queens.class
	rm Manifest
	chmod +x Queens

Queens.class: Queens.java
	javac -Xlint Queens.java

clean:
	rm -f Queens Queens.class

submit: Makefile Queens.java
	submit cmps012b-pt.w19 pa2 Makefile Queens.java