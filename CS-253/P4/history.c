#define _GNU_SOURCE
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include "history.h"

extern int count;
struct Cmd* holder[MAXHISTORY];
struct Cmd{
  char* cmd;
  int exitStatus;
};

void init_history(void){
  for(int i=0;i<MAXHISTORY;i++){
    holder[i]= malloc(sizeof(struct Cmd));
  }
}
void add_history(char *input, int exitStatus){
  //This will add a new struct into the array with the given *cmd and exitStatus
  if(count>=MAXHISTORY){
    free(holder[0]->cmd);
    for(int i=0;i<9;i++){
      holder[i] -> cmd = holder[i+1] ->cmd;
      holder[i] -> exitStatus = holder[i+1] -> exitStatus;
    }
    holder[9] -> cmd = strndup(input, strlen(input));
    holder[9] -> exitStatus = exitStatus;
  }else{
    holder[count] -> cmd = strndup(input, strlen(input));
    holder[count] -> exitStatus = exitStatus;
  }
    count++;
}
void clear_history(void){
  for(int i=0;i<MAXHISTORY;i++){
  if(holder[i]->cmd != NULL){
  free(holder[i]->cmd);
  }
    free(holder[i]);
  }
}
void print_history(int firstSequenceNumber){
  //this does a print to fputs which will output the number in the array, the [exitstatus], and the name as *cmd
  int A = firstSequenceNumber;
  int current=0;
  if(A>10){
    current=10;
    A=A-10+1;
  }else{
    current=A;
    A=1;
  }
  for(int i=0;i<current;i++){
    printf("%d [%d] %s \n",(int)A,holder[i]->exitStatus,holder[i]->cmd);
    A++;
  }
}
