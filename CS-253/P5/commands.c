#define _GNU_SOURCE
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include "smash.h"
#include "history.h"
#include <sys/wait.h>
int count=0;

int executeExternalCommand(char *args[]){
  pid_t pid = fork();
  
  int status;
  //this is for the parent
  if(pid>0){
    wait(&status);
  }else if(pid==0){
    execvp(args[0], args);
    fputs("smash: -Command Not Found",stdout);
    fputs("\n",stdout);
    exit(127);
  }else{
    fputs("Fork Failed",stdout);
    fputs("\n",stdout);
    exit(127);
  }
  
  
  return WEXITSTATUS(status);
}


void executeCommand(char *str){
  char *copy = strndup(str, strlen(str));
  char *args[100];
  char *token;
  int counter = 0;
  token=strtok(str, " ");
  while(token != NULL){
    args[counter] = token;
    counter++;
    token = strtok(NULL, " ");
  }
  args[counter]= NULL;
  if(counter==0){
  return;
  }
  if(strncmp("exit",args[0],4)==0){
    clear_history();
    printf("\n");
    free(copy);
    exit(0);
  }else{
    if(strncmp("cd",args[0],2)==0){
      int dir = chdir(args[1]);
      if(dir != 0){
        perror(args[1]);
        add_history(copy,1);
      }else{
        fputs(copy,stderr);
        add_history(copy,0);
        fputs("\n",stdout);
      }
    }else{
      if(strncmp("history",args[0],7)==0){
        add_history(copy,0);
        print_history(count);
      }else{
        add_history(copy,executeExternalCommand(args));
      }
    }
  }
  free(copy);
}


