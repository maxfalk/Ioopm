#ifndef MUSICLIBDIR_H
#define MUSICLIBDIR_H


typedef struct id3 *ID3;

//Read a mp3file for it's ID3 info
ID3 readid3info(char*);
//Get specific info.
char *getinfo(ID3,char);
//release memory on heap for the ID3 info
void destroyid3(ID3 mymfile);


#endif
