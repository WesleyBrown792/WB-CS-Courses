//Include definitions for C Runtime Library functions used in this program
#include <stdio.h>				//The standard I/O functions

//-------------------------------------------------------------------------------
//This is the main function, invoked by the C Runtime (CRT) to start the program
//-------------------------------------------------------------------------------
int main(void) {

    int upper,lower,vowels,consonants,digits,total,t1,t2,t3;
    upper=0;
    lower=0;
    vowels=0;
    consonants=0;
    digits=0;
    total=0;
    t1=0;
    t2=0;
    t3=0;
    
    int c;
    
    while((c = getchar()) != EOF){
      if(c == 'E'||c == 'A' ||c == 'I'||c == 'O'||c == 'U'||c == 'Y'||c == 'e'||c == 'a' ||c == 'i'||c == 'o'||c == 'u'||c == 'y'){
        vowels++;
        t3++;
      }else{
        if(isdigit(c)){
          digits++;
          t2++;
        }else{
          if(c == '?'){
           total++;
          }else{
          consonants++;
          t1++;
          }
        }
      }
      if(islower(c)){
        lower++;
      }else{
        if(isupper(c)){
          upper++;
        }
      }
      putchar(c);
    }
    total+= t1 + t2 + t3 +1;
    printf("upper-case: %i ", upper);
    printf("lower-case: %i ", lower);
    printf("vowels: %i ", vowels);
    printf("consonants: %i ", consonants);
    printf("digits: %i ", digits);
    printf("total: %i ", total);

}
	
