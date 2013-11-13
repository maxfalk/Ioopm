#include "database.h"
#include <stdio.h>
#include <string.h>

int main(int argc,int *argv[]){
  tree db = createdb();
  
  db = insert(db,"1","a");
  db = insert(db,"2","b");  
  db = insert(db,"3","c");
  db = insert(db,"4","d");
  db = insert(db,"5","e");
  db = insert(db,"6","f");  
  char result[128];
  query(db,"1",result);
  printf("Result: %s\n",result);
 *result = '\n';
  query(db,"2",result);
  printf("Result: %s\n",result);
  db = delete(db,"2");
  *result = '\0';
  query(db,"2",result);  
  printf("Result: %s\n",result);
  db = update(db,"3","F");
  *result = '\0';
  query(db,"3",result);  
  printf("Result: %s\n",result);
  return 0;
}
