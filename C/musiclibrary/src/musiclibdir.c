#include <stdio.h>
#include <dirent.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <stddef.h>
#include <string.h>
#include "musiclibdir.h"

/*

Module to handle the moving and indexing of file by input params, 
moving files to specific root folder and making a fir tree there
 */

//-------------------------------------------------------------
void listdir(char *path,char pathlist[][50]){
 
  struct dirent *ep;
  DIR *dr;
  dr = opendir(path);
  int fi = 0;
 
  if(dr != NULL){
   
   while((ep = readdir(dr)) != NULL){
     if(strcmp(ep->d_name,"..") != 0 && strcmp(ep->d_name,".") != 0){
       strcpy(pathlist[fi],path);
       dirconcat(pathlist[fi],ep->d_name);
       //printf("File1:%s\n",ep->d_name);
       //printf("File2:%s\n",pathlist[fi]);
       fi++;    
     }
    }
   strcpy(pathlist[fi],"\0");
    closedir(dr);
  }else
    perror("Couldn't open the dir\n");

  //puts("done");
}


//------------------------------------------------------------------
int movefile(char* filepath, char* newfilepath){
  
    return rename(filepath,newfilepath);
}
//--------------------------------------------------------------------
//makefolder
int makefolder(char *path){
   

   return mkdir(path,0777);

}
//-------------------------------------------------------------------
//Check folder for existing folder
int checkfordir(char *dirname, char *lkdir){

  int found = 0;
  struct dirent *ep;
  DIR *dr;

  dr = opendir(dirname);

  if(dr != NULL){
    while((ep = readdir(dr))){
  
      if(strcmp(ep->d_name,lkdir) == 0){
	found = 1;
      } 
    
    }
    closedir(dr);
  }else
    perror("Couldn't open the dir\n");


  return found;

}
//-----------------------------------------------------------------
void changedirdown(char *dir){
  
  int len = strlen(dir);
  dir[len] = '\0'; //remove first sign probably a /.
  len--;
  while(dir[len] != '/' && len >= 0){
    dir[len] = '\0';
    len--;
  }

}
//-------------------------------------------------
void getfilename(char *fullpath){

  char rest[100];
  strcpy(rest,fullpath);
  changedirdown(rest);
  int start = strlen(rest);
  int end = strlen(fullpath);
  int i = 0;
  for(i = 0;start <= end;i++){
    fullpath[i] = fullpath[start];
    start++;
  }
  fullpath[i] = '\0';


}

//------------------------------------------------------
void dirconcat(char *first,char *last){

  if(first[strlen(first)] != '/')
    strcat(first,"/");

  strcat(first,last);

}


