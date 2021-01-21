#define MAXLINE 4096
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "smash.h"
#include "history.h"

int main(){
  char bfr[MAXLINE];
  init_history();
  fputs("$ ",stderr);
  
  while(fgets(bfr, MAXLINE, stdin) != NULL){
    bfr[strlen(bfr)-1] ='\0';
    executeCommand(bfr);
    fputs("$ ",stderr);
  }
  return 0;
}