#include "copy.h"
#include <stdio.h>

int doCopy(void){
char array[1023];
while(fgets(array,sizeof(array),stdin) != NULL){
      fputs(array, stdout);
         
    }
return 0;
}