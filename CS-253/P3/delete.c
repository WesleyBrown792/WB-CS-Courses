#include "delete.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int doDelete(char *line1, char *line2){
int currline = 1;
char array[1025];
long startbound = strtol(line1,NULL,10);
long endbound = strtol(line2,NULL,10); 
if(startbound>endbound){
return 1;
}
if(startbound<1){
return 1;
}

  while(fgets(array,sizeof(array),stdin) != NULL){
      if(strlen(array)>=1024){
        return 1;
      }
      if(currline<startbound || currline>endbound){
       fputs(array,stdout);
      }
      currline++;
    }
  
  return 0;
}