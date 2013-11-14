/*! \mainpage musiclibrary
 *
 * \section intro_sec Introduction
 * Functions for a simple id3tag reader and files system interaction.
 * 
 *
*/


/** @file musiclibdir.h
* @brief Function for interacting with the filesystem.
*
* Function to look at a location on the harddrive and make files, move files etc..
* 
*
* @authers Max Falk Nilsson
*/
#ifndef DIR_H
#define DIR_H


/**
*Lists all files in the current path.
*@param path The path of which to list all of it's files.
*@param pathlist Were the result of the files found in path will be stored.
*/
void listdir(char *path,char pathlist[][50]);
/**
* Moves a file form on location to another.
*@param filepath The fullpath to the file to be moved.
*@param newfilepath The fullpath to were the file is to be moved.
*@return -1 if there was an error else 0.
*/
int movefile(char *filepath,char *newfilepath);
/**
*Makes a new directory.
*@param path The fullpath to the new directory to be created.
*@return -1 if there was an error else 0.
*/
int makefolder(char *path);
/**
*Checks it a folder already exists
*@param dirname The directory the be search
*@param lkdir The directory to search for.
*@return 1 if the directory was found else 0.
*/
int checkfordir(char *dirname,char *lkdir);
/**
*Changes a path a dir down(cd ..).
*@param dir The dir to go one step down from.
*/
void changedirdown(char *dir);
/**
*Concatenat to a path with a file. 
*@param first The path to be concatenated.
*@param last The file to be concatenated to the path.
*/
void dirconcat(char *first,char *last);
/**
*Takes a fullpath and returns the file which it leads to.
*@param fullpath The path to get the filename from.
*/
void getfilename(char *fullpath);



#endif
