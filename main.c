#include <stdio.h>
#include <stdlib.h>
#include "file.h"

int main(int argc, char *argv[]){

  if(argc != 2){
    perror("No arg");
  }
  char *file = argv[1];
  FILE *stream = fopen(file, "r");
  if(stream == NULL) perror("File not open");
  printf("Using File:%s\n",file);
  char **mystrings = Filereadline(stream);
  
  for(int i=0; *(mystrings+i) != NULL &&  i <49; i++){  
    printf("String:%s\n",*(mystrings+i));
    //free(mystrings[i]);
  }


  fclose(stream);
  return 0;
}
