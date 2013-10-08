#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "file.h"

char **Filesplitoncomma(char*);

char **Filereadline(FILE *stream){
 
  char string[50] = {};
  char **stringcon = NULL;
  if(fgets(string,100,stream) != NULL)
    {  
      stringcon = Filesplitoncomma(string);
    }

  for(int i = 0; stringcon[i] != NULL; i++)
    printf("CS:%s\n",stringcon[i]);
  
  return stringcon;

}
//---------------------------------------------------------
#define MAX_NUMBER_SPLITS 50 
#define MAX_STRING_LEN 50
char **Filesplitoncomma(char *string){

  char **stringcon = malloc(sizeof(char*)*MAX_NUMBER_SPLITS);  
  int pos = 0;
  char *pch = NULL;
  pch = strtok(string,",");
  
  if(pch != NULL){
    char *stringheap = malloc(sizeof(char)*(strlen(pch)+1));
    strncpy(stringheap,pch,strlen(pch)+1);
    stringcon[pos] = stringheap;
    pos++;   
   
    while((pch = strtok(NULL,",")) != NULL && pos < MAX_NUMBER_SPLITS-1){
      pos++;       
      stringheap = malloc(sizeof(char)*(strlen(pch)+1));
      strncpy(stringheap,pch,strlen(pch)+1);
      stringcon[pos] = stringheap;
      
    }
    pos++;
    stringcon[pos] = NULL; //terminate array of pointers with a null pointer
  }

  return stringcon;

}
