#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "database.h"

void readline(char *dest, int n, FILE *source);
db createdbfromfile(db list, FILE *database);
int query(db list);
void update(db list);
db insert(db list);
db delete(db list);

// laser forsta ordet kvarvarande filen fran strommen och satter den i *dest
void readline(char *dest, int n, FILE *source){
  fgets(dest, n, source);
  int len = strlen(dest);
  if(dest[len-1] == '\n'){
    dest[len-1] = '\0';
  }
}

// satter in varderna ur en fil i en databas list.
db createdbfromfile(db list, FILE *database){
  char bufferkey[128];
  char buffervalue[128];
  int status;
  while(!(feof(database))){ //tills man nar slutet av filen.
    readline(buffervalue, 128, database);
    readline(bufferkey, 128, database);
   list = insertdb(list,bufferkey,buffervalue,&status); //satt in nytt varde i databas list.
  }
  return list;
 
}

//letar efter ett varde med hjalp av dennes nyckel i databas list.  
int query(db list){
  char buffer[128];
  char value[128];
  int status = 1;
  printf("Enter key: ");
  readline(buffer, 128, stdin); //laser fran terminalen for att fa nyckeln.
  puts("Searching database...\n");
  querydb(list,buffer,value,&status); //anropar querydb for att leta i databas list efter noden med nyckeln.

  if(status == 0){ //om status ar 0 har querydb hittat nyckeln och vardet skrivs ut.
      puts("Found entry:");
      printf("key: %s\nvalue: %s\n", buffer, value);
      return 0;
  }else{ //om status ar inte 0 har querydb inte hittat nyckeln.
      puts("Key not found!");
      return 1;
  }

 
}
//Uppdaterar databas list med ett nytt varde for en nyckel.
void update(db list){

  char key[128];
  char value[128];
  int status = 1;
  printf("Enter key: ");
  readline(key, 128, stdin); //laser in nyckel.
  
  printf("Enter new value: "); //om den finns satt in ett nytt varde
  readline(value, 128, stdin);  
  puts("Searching database...\n");  
  updatedb(list,key,value,&status);
  
  if(status == 0){ //om querydb hittade inte nyckeln sa avslutas update.
    puts("Could not find an entry matching key!\n");
  }else{
     //satter in det nya vardet.
    puts("Value inserted successfully!");
  }


}
//Satter in ny nod med nyckel och varde i databasen list.
db insert(db list) {

  char key[128];
  char value[128];
  int status = 1;
  printf("Enter key: ");
  readline(key, 128, stdin);//Laser in nyckel.
  printf("Enter value: ");
  readline(value, 128, stdin);//Laser in varde.
  puts("Searching database...\n");
  list = insertdb(list,key,value,&status);//satter in lista och varde.  


  if(status == 1){ //Satter in ny nod pa ratt plats.
    puts("Key is unique!\n");
    puts("");
    puts("Entry inserted successfully:");
    printf("key: %s\nvalue: %s\n", key, value);
  }else{ //En annan nyckel med samma namn finns redan.
    puts("Key is not unique!\n");
  }

  return list;

}
//Tar bort nod och reparerar.
db delete(db list) {

  char key[128];
  char result[128];
  int status = 1;
  printf("Enter key: ");
  readline(key, 128, stdin); //Laser nyckel.
  puts("Searching database...\n");
  list = deletedb(list,key,&status,result); //Tar bort noden med databasen. 

  if(status == 1){ //Nyckeln finns.  
    printf("Deleted the following entry:\nkey: %s\nvalue: %s\n", key, result); //Printar vilken nyckel och varde som tas bort.
  }
  else{ //Nyckeln hittas inte.
    printf("Could not find an entry matching key \"%s\"!\n", key);
  }
 
  return list;

}


int main(int argc, char *argv[]){
  if (argc < 2){
    puts("Usage: db [FILE]");
    return -1;
    //Main funktionen. Om tillrackliga argument inte ges returneras instruktioner. 
  }
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
  char *filename = argv[1];// ta in forsta argumentet.
  printf("Loading database \"%s\"...\n\n", filename);
  FILE *database = fopen(filename, "r"); // oppna en stom mot filename.
  db list = createdb(); //skapar databas list med createdb.
  list = createdbfromfile(list,database); //satter in datan fran strommen i list.
  fclose(database);
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
    // fragar efter input. Det har filblocket ar anvandarens primara interface med funktionen.

    switch(choice){
    case 1:
      // Query
      query(list);      
      break;
    case 2:
      // Update
      update(list);
      break;
    case 3:
      // Insert 
      list = insert(list);    
	break;
    case 4:
      // Delete
      list = delete(list);
      break;
    case 5:
      // Print database
      printdb(list);
      break;
    case 0:
      // Exit
	  destroy(list);
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
