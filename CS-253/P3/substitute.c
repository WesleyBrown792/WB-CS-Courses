#include "substitute.h"
#include <stdio.h>
#include <string.h>

int doSubstitute(char *pattern, char *string){
char array[1025]; 
char final[1025];
char* holder;
char* start;
  while(fgets(array,sizeof(array),stdin) != NULL){
  if(strlen(array)>=1024){
  return 1;
  }
  start=array;
  if((holder=strstr(start,pattern))!=NULL){
  strncat(final, start, strlen(start)-strlen(holder));
  strncat(final, string,strlen(string));
  start=holder+strlen(pattern);
  
   while((holder = strstr(start,pattern)) != NULL){
     strncat(final, start,strlen(start)-strlen(holder) );
     strncat(final, string,strlen(string));
     start=holder+strlen(pattern);
   }
   
  strncat(final, start, strlen(start));
   fputs(final,stdout);
  }else{
  fputs(array,stdout);
  }
  
  
  
  }
  return 0;
}