#------------------------------------------------------------------------------
# Makefile for DictionaryClient.c with macros
#------------------------------------------------------------------------------

ADT     = hi
##EXEBIN  = $(ADT)Client
SOURCES = $(ADT).c 
OBJECTS = $(ADT).o 
HEADERS = $(ADT).h

all: $(ADT)

$(OBJECTS) : $(SOURCES) 
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(ADT) $(OBJECTS)

check:
	valgrind --leak-check=full $(ADT) 

try:
	./$(ADT)