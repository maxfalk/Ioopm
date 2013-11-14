
/** @file ID3Info.h
* @brief Function for reading and getting information for ID3tags.
*
* Read and get functions for ID3tags.
* 
*
* @authers Max Falk Nilsson
*/

#ifndef MUSICLIBDIR_H
#define MUSICLIBDIR_H

/**
*typedef for the id3 struct
*/
typedef struct id3 *ID3;

//Read a mp3file for it's ID3 info
/**
*Read the ID3info of the file at the fullpath given.
*@param filename The fullpath to the file to read from.
*@return The information stored in the files ID3tags.
*/
ID3 readid3info(char *filename);
/**
*Get information from a generated ID3tag.
*@param mymfile the ID3tag to look in.
*@param value The code for what information to get.
*@return the infromation stored at the given code in the given ID3tag.
*/
char *getinfo(ID3 mymfile,char value);
/**
*Destroy the ID3tag 
*@param mymfile the ID#tag to destroy.
*/
void destroyid3(ID3 mymfile);


#endif
