#include <stdio.h>
#include <string.h>
#include "musiclibdir.h"
#include "ID3Info.h"

void sortmymusicfile(char*,char*,char*,char*);

//-----------------------------------------------------
int main(int argc, char *argv[]){


  if(argc != 3){
    puts("Need arg for paths!");
    return -1;
  }

  char *path = argv[1];
  printf("First path:%s\n",path);
  char files[100][50];
  
  
  listdir(path,files);//get paths 
  
  char *movetodir = argv[2];
  printf("MoveTo:%s\n",movetodir);
  for(int i = 0;strcmp(files[i],"\0") != 0 && i < 100;i++){
    printf("Path: %s\n",files[i]);
    ID3 myinfo = readid3info(files[i]);
    char *artist = getinfo(myinfo,'H');
    char *album = getinfo(myinfo,'A');
    printf("Artist:%s, Album: %s\n",artist,album);
    sortmymusicfile(files[i],movetodir,artist,album); 
    destroyid3(myinfo);
  }
 
  
  return 0;
}
//-----------------------------------------------------
//FIlename = name of the file, workingdir = dir were fils should be stored,
// movetodir= dir the file wants to be
void sortmymusicfile(char *filepath,char *movetodir,char *artist, char * album){
  
  char filename[100];
  char newdir[100];
  strcpy(filename,filepath);
  getfilename(filename);
  printf("filename:%s\n",filename); 

  //artist dir
  strcpy(newdir,movetodir);
  dirconcat(newdir,artist);
  printf("newartistdir:%s\n",newdir);  
  makefolder(newdir);

  //albumdir
  dirconcat(newdir,album);
  printf("newalbumdir:%s\n",newdir);
  makefolder(newdir);
  
  //move file
  dirconcat(newdir,filename);
  printf("newfilepath:%s\n",newdir);  
  movefile(filepath,newdir);



 
}
