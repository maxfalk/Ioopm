#include <stdio.h>
#include <string.h>
void print(int);

int main(int argc, char *argv[]){
  if(argc != 2)
    return 1;

  int n = atoi(argv[1]);
  print(n);

  return 0;
}
void print(int n){
  for(int i = 0;i <=n;i++){
    printf("%d\n",i);
    printf("%d\n",i);
   }

}
