#ifndef DIR_H
#define DIR_H


//list all files in a dir
void listdir(char *path,char pathlist[][50]);
//movefile
void movefile(char*,char*);
//make a folder
void makefolder(char*);
//check path for a dir
int checkfordir(char*,char*);
//remove the highest folder
void changedirdown(char*);
//concatenat names with / between
void dirconcat(char*,char*);
//Take a fill path and "cut away" all the folders
void getfilename(char*);



#endif
