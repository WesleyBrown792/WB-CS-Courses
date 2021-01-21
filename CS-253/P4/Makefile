#-------Define names of all the object files in this project
	OBJS = smash.o history.o commands.o 

#-------Define the name of the resulting released product
	EXE = smash

#-------Define options passed by make to the compiler
	CFLAGS = -Wall -std=c99
  CC = gcc

#-------Define target "all" for building the executable(s)
all:	$(EXE) rules.d 

rules.d: $(wildcard *.c) $(wildcard *.h)
	gcc -MM $(wildcard *.c) >rules.d

#-------Include the rules for rebuilding each *.o file
-include rules.d

#-------Rule for linking the executable product
$(EXE): $(OBJS)
	$(CC) $(CFLAGS) $^ -o $@

#-------Rule for cleaning build and emacs artifacts
clean:
	rm -f $(OBJS) $(EXE) *.d  *~
 
 #-------Rule for smash to be made

debug: CFLAGS += -g -O0
debug: all