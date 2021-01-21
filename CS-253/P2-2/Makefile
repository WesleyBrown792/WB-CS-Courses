# Makefile
# Usage
#	make all - builds and outputs the 
#	make clean	Remove build artifacts

#-------Define names of all the object files in this project
	OBJS = match.o

#-------Define the name of the resulting released product
	EXE = match

#-------Define options passed by make to the compiler
	CFLAGS = -Wall

#-------Define the name of the compiler to be used
	CC = gcc

#-------Define target "all" for building the executable(s)
all:	$(EXE)

#-------Rule for linking the executable product
$(EXE): $(OBJS)
	$(CC) $(CFLAGS) $^ -o $@

clean:
	rm -f $(OBJS) $(EXE) *~
