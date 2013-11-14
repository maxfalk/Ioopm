#include <stdio.h>
#include <string.h>
#include "musiclibdir.h"
#include "ID3Info.h"

void sortmymusicfile(char*,char*,char*,char*,char*);
void addmp3ending(char*);
//-----------------------------------------------------
int main(int argc, char *argv[]){


  if(argc != 3){
    puts("Need arg for paths!");
    return -1;
  }

  char *path = argv[1];
  printf("Move From:%s\n",path);
  char files[100][50];
  
  
  listdir(path,files);//get paths 
  
  char *movetodir = argv[2];
  printf("Move To:%s\n",movetodir);
  for(int i = 0;strcmp(files[i],"\0") != 0 && i < 100;i++){
    //printf("Path: %s\n",files[i]);
    ID3 myinfo = readid3info(files[i]);
    char *artist = getinfo(myinfo,'H');
    char *album = getinfo(myinfo,'A');
	char *title = getinfo(myinfo,'L');
    //printf("Artist:%s, Album: %s\n",artist,album);
    sortmymusicfile(files[i],movetodir,artist,album,title); 
    destroyid3(myinfo);
  }
 
  
  return 0;
}
//-----------------------------------------------------
//FIlename = name of the file, workingdir = dir were fils should be stored,
// movetodir= dir the file wants to be
void sortmymusicfile(char *filepath,char *movetodir,char *artist, char * album, char *title){
  
  char filename[100];
  char newdir[100];
  int error = 0;
  strcpy(filename,title);
  addmp3ending(filename);
  //getfilename(filename);
  
 // printf("filename:%s\n",filename); 

  //artist dir
  strcpy(newdir,movetodir);
  dirconcat(newdir,artist);
  //printf("newartistdir:%s\n",newdir);  
	makefolder(newdir);

	//albumdir
	dirconcat(newdir,album);
	//printf("newalbumdir:%s\n",newdir);
	makefolder(newdir);
	//move file
	dirconcat(newdir,filename);
	error = movefile(filepath,newdir);
	if(error != -1){
		printf("Moved file to:%s\n",newdir);  
	}else{
		printf("Couldn't move file:%s \n", filepath);
	}


  


 
}
void addmp3ending(char *filename){
	strcat(filename,".mp3");

}
