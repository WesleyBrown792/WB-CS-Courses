//-----------------------------------------------------------------------------
//
// NAME
//  sed253 -- Simplified editor
//
// SYNOPSIS
//  sed253
//  sed253 -s pattern string
//  sed253 -d line1 line2
//
// DESCRIPTION
//  Simplified editor.  Copies lines read from stdin to stdout.  Options:
//
//  -s Substitute every occurrence of pattern with string
//  -d Delete line1 through line2 inclusive
//
// ERRORS
//  Prints usage message and exits abnormally for invalid commands.  Prints an
//  error message and exits abnormally for other issues.
//
// LIMITATIONS
//  Lines of text are limited to a maximum of 1023 chars.
//
// AUTHORS
//  Epoch...................................................................jrc
//
//-----------------------------------------------------------------------------

//Bring in the definitions for our helper functions
#include "copy.h"
#include "substitute.h"
#include "delete.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
//-----------------------------------------------------------------------------
// main -- the main function
//-----------------------------------------------------------------------------
int main(int argc, char **argv) {
  if(argc > 4){
    printf("Too many arguments in the command line please have usage:  sed253 [-s pattern string] [-d line1 line2]");
    return 1;
  }else{
    if(strcmp(argv[1],"-d") == 0){
    return doDelete(argv[2], argv[3]);
    }
    if(strcmp(argv[1],"-s") == 0){
    return doSubstitute(argv[2], argv[3]);
    }
    if(argc==1){
    return doCopy();
    }
  }
  return 0;
}

