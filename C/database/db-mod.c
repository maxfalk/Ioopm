#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//länkade struktur Key, value
//typedefad till Node
typedef struct node{
  char *key;
  char *value;
  struct node *next;
} *Node;

void query(char *buffer,Node list);
void update(char* buffer,Node list);
void delete(char* buffer,Node list);
Node insert(char* buffer,Node list);
Node readfile(FILE *, char *, Node);
void print(Node list);


//-------------------------------------------------------------------
int main(int argc, char *argv[]){
  //LÄngre än 2 tecken för inpara
  if (argc < 2){
    puts("Usage: db [FILE]");
    return -1;
  }
  //Skit?
  puts("Welcome to");
  puts(" ____    ____       ");
  puts("/\\  _`\\ /\\  _`\\     ");
  puts("\\ \\ \\/\\ \\ \\ \\L\\ \\   ");
  puts(" \\ \\ \\ \\ \\ \\  _ <\\ ");
  puts("  \\ \\ \\_\\ \\ \\ \\L\\ \\ ");
  puts("   \\ \\____/\\ \\____/ ");
  puts("    \\/___/  \\/___/  ");
  puts("");
  // Read the input file
  char *filename = argv[1]; // läs första inparametern
  printf("Loading database \"%s\"...\n\n", filename); //skriv ut filnamnet
  FILE *database = fopen(filename, "r"); // öppna filen
  char buffer[128]; // skapa array 
 Node list = NULL; //Skapa en tom struct list
 
  list = readfile(database,buffer,list);

  // Main loop
  int choice = -1;
  while(choice != 0){
    puts("Please choose an operation");
    puts("1. Query a key");
    puts("2. Update an entry");
    puts("3. New entry");
    puts("4. Remove entry");
    puts("5. Print database");
    puts("0. Exit database");
    printf("? ");
    scanf("%d", &choice);
    while(getchar() != '\n'); // Clear stdin
    switch(choice){
    case 1:
      // Query
      query(buffer,list);
      break;
    case 2:
      // Update
      update(buffer,list);      
      break;
    case 3:
      // Insert
     list = insert(buffer,list);
      break;
    case 4:
      // Delete
      delete(buffer,list);
      break;
    case 5:
      // Print database
      print(list);
      break;
    case 0:
      // Exit
      puts("Good bye!");
      break;
    default:
      // Please try again
      puts("Could not parse choice! Please try again");
    }
    puts("");
  }
  return 0;
}
//-------------------------------------------------------------------------
//länk en hel rad, tabort \n, läs från source, till dest, längst n tecken.
void readline(char *dest, int n, FILE *source){
  fgets(dest, n, source);
  int len = strlen(dest);
  if(dest[len-1] == '\n')
    dest[len-1] = '\0';

}
//---------------------------------------------------------------
Node readfile(FILE * database,char *buffer, Node list){

  //läs tills filen är slut
  while(!(feof(database))){
    Node newNode = malloc(sizeof(struct node)); // allokera plats på heap
    readline(buffer, 128, database);// läs in rader från filen i buffer
      if((feof(database))) break;
    newNode->key = malloc(strlen(buffer) + 1);// skapa plats på heapen för variabeln buffer
    strcpy(newNode->key, buffer); //kopiera innehåller av buffer till newnodes key 
    printf("Read value:%s, feof:%d\n",newNode->key,feof(database));
    readline(buffer, 128, database); //läs nästa rad
    newNode->value = malloc(strlen(buffer) + 1);//allokera på heapen för value
    strcpy(newNode->value, buffer);//läs in den nya raden i value
    printf("Read value:%s, feof:%d\n",newNode->value,feof(database));
    newNode->next = list; // sätt nästa länkade "structur" till list dvs en tom Node
    list = newNode;
 
 }
  
return list;

}
//------------------------------------------------------------
void query(char *buffer,Node list){

  printf("Enter key: ");
  readline(buffer, 128, stdin);
  puts("Searching database...\n");
  int found = 0;
  Node cursor = list;
  while(!found && cursor != NULL){
    if(strcmp(buffer, cursor->key) == 0){
      puts("Found entry:");
      printf("key: %s\nvalue: %s\n", cursor->key, cursor->value);
      found = 1;
    }else{
      cursor = cursor->next;
    }
  }
  if(!found){
    printf("Could not find an entry matching key \"%s\"!\n", buffer);
  }
  

}
//-----------------------------------------------------------
void update(char *buffer,Node list){
  printf("Enter key: ");
  readline(buffer, 128, stdin);
  puts("Searching database...\n");
  int found = 0;
  Node cursor = list;
  while(!found && cursor != NULL){
    if(strcmp(buffer, cursor->key) == 0){
      puts("Matching entry found:");
      printf("key: %s\nvalue: %s\n\n", cursor->key, cursor->value);
      found = 1;
    }else{
      cursor = cursor->next;
    }
  }
  if(!found){
    printf("Could not find an entry matching key \"%s\"!\n", buffer);
  }else{
    printf("Enter new value: ");
    readline(buffer, 128, stdin);
    free(cursor->value);
    cursor->value = malloc(strlen(buffer) + 1);
    strcpy(cursor->value, buffer);
    puts("Value inserted successfully!");
  }
  
}
//----------------------------------------------------
Node insert(char *buffer,Node list){

      printf("Enter key: ");
      readline(buffer, 128, stdin);
      puts("Searching database for duplicate keys...");
      int found = 0;
      Node cursor = list;
      while(!found && cursor != NULL){
        if(strcmp(buffer, cursor->key) == 0){
          printf("key \"%s\" already exists!\n", cursor->key);
          found = 1;
        }else{
          cursor = cursor->next;
        }
      }
      if(!found){ // Insert new node to the front of the list
        puts("Key is unique!\n");
        Node newNode = malloc(sizeof(struct node));
        newNode->key = malloc(strlen(buffer) + 1);
        strcpy(newNode->key, buffer);
        printf("Enter value: ");
        readline(buffer, 128, stdin);
        newNode->value = malloc(strlen(buffer) + 1);
        strcpy(newNode->value, buffer);
	//list->next =newNode;
	newNode->next = list;
        list = newNode;
        puts("");
        puts("Entry inserted successfully:");
        printf("key: %s\nvalue: %s\n", list->key, list->value);
      }

      return list;

}
//-------------------------------------
void delete(char *buffer,Node list){
  printf("Enter key: ");
  readline(buffer, 128, stdin);
  puts("Searching database...\n");
  int found = 0;
  Node cursor = list;
  Node prev = NULL;
  while(!found && cursor != NULL){
    if(strcmp(buffer, cursor->key) == 0){
      if(prev == NULL){ // Delete first node
	list = cursor->next;
      }else{
	prev->next = cursor->next;
      }
      found = 1;
      printf("Deleted the following entry:\nkey: %s\nvalue: %s\n", cursor->key, cursor->value);
    }else{
      prev = cursor;
      cursor = cursor->next;
    }
  }
  if(!found){
    printf("Could not find an entry matching key \"%s\"!\n", buffer);
  }
  
}
//---------------------------------------------
void print(Node list){

  Node cursor = list;
  while(cursor != NULL){
    puts(cursor->key);
    puts(cursor->value);
    cursor = cursor->next;
  }
}
