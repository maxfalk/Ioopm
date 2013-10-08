#include <stdio.h>
#include "convert_content.h"
#include "string.h"
struct header{
  char *ID3v2identifier; //"ID3"
  int ID3v2version;     //2 bytes version
  int ID3v2falgs;       //falg 1 byte
  int ID3v2size; //size of ID3 tag. 4 bytes not including header

};

struct frameinfo{
  int type; // 0x0 ISO-8859-1, 0x01 USC-2. One byte.
  char *info;

};

struct frame{
  char *FrameID; // 4bytes, type of info given
  int Size; // 4 bytes, size of post to come, not including this header
  int Flags; // 2 bytes, flags
  struct frameinfo *Frameinfo;
};


typedef struct id3{
  struct header *Header;
  struct frame *Frame[500]; 
}*ID3;
 
//Tags = Låttitel, huvudartist, album,spårnummer, Genre, inspelningsår
char *tags[] = {"TIT2","TPE1","TALB","TRCK","TCON","TYER"};
#define NUMOFTAGS 6

void readfile(FILE*,int,char*);
void readheader(FILE*,ID3);
int readsize(FILE*,int);
int readframeheader(FILE*,ID3,int);
void readframeinfo(FILE*,ID3 ,int);
void readframes(FILE*, ID3);
char *getinfo(ID3,char);

//-----------------------------------------------------
int main(int argc, char *argv[]){
  
  
  ID3 mymfile = malloc(sizeof(struct id3));

  FILE *stream = fopen("music/music39.mp3","r");
  readheader(stream,mymfile);
  //printf("stuffhere: %s\n",mymfile->Header->ID3v2identifier);
  readframes(stream,mymfile);
  //printf("more more outside info: %s, num:%d\n",mymfile->Frame[0]->Frameinfo->info,0);
  printf("tag: %s\n",getinfo(mymfile,'H'));
  fclose(stream);
  return 0;
}
//-----------------------------------------------------
char *getinfo(ID3 mymfile, char value){
//Tags = Låttitel, huvudartist, album,spårnummer, Genre, inspelningsår

 char *searchvalue;
  int valid = 1;
  //inte klart här
  switch(value){
  case 'L':
    searchvalue = "TIT2";
    break;
  case 'H':
    searchvalue = "TPE1";
    break;
  case 'A':
    searchvalue = "TALB";
    break;
  case 'S':
    searchvalue = "TRCK";
    break;
  case 'G':
    searchvalue = "TCON";
    break;
  case 'I':
    searchvalue = "TYER";
    break;
  default:
    valid = 0;
    break;
}

  
    char *result = "\0";
    int i = 0;

    while(mymfile->Frame[i] != NULL && valid == 1){
	if(strcmp(mymfile->Frame[i]->FrameID,searchvalue) == 0){ // check for given value
	result = mymfile->Frame[i]->Frameinfo->info;
	//	printf("%s\n",mymfile->Frame[i]->Frameinfo->info);    
	valid = 0;
      }
      i++;
    }


  return result;
}
//-------------------------------------------------
int readframeheader(FILE* stream, ID3 mymfile, int framenum){
  
  char result[128];
  readfile(stream,4,result); // read identifier

  if(*result != NULL){
    mymfile->Frame[framenum] = malloc(sizeof(struct frame));
    mymfile->Frame[framenum]->FrameID = malloc(strlen(result)+1);
    strcpy(mymfile->Frame[framenum]->FrameID,result);  

    mymfile->Frame[framenum]->Size = readsize(stream,4);// read size
    //printf("frameSize1: %d",  mymfile->Frame[framenum]->Size);			      
    readfile(stream,2,result); // read version
    mymfile->Frame[framenum]->Flags = atoi(result);
    return 0; // read data, header found
  }else{
    //  mymfile->Frame[framenum] = NULL; //mark end of frames   
    return 1; // no data
  }
}
//-------------------------------------------------
void readframeinfo(FILE *stream, ID3 mymfile,int framenum){
  int framesize = mymfile->Frame[framenum]->Size;
  //printf("framesize2: %d\n", framesize);
  char result[128];
  mymfile->Frame[framenum]->Frameinfo = malloc(sizeof(struct frameinfo));
  readfile(stream,1,result);//read text type
  mymfile->Frame[framenum]->Frameinfo->type = atoi(result);     
  readfile(stream,framesize-1,result);//read framedata
  mymfile->Frame[framenum]->Frameinfo->info = malloc(strlen(result)+1);
  strcpy(mymfile->Frame[framenum]->Frameinfo->info,result);
  //printf("info: %s, num:%d\n",mymfile->Frame[framenum]->Frameinfo->info,framenum);

}
//--------------------------------------------------
void readframes(FILE*stream, ID3 mymfile){
  
  int ID3v2size = mymfile->Header->ID3v2size;
  int endoffile = 0;

  for(int i = 0; i < ID3v2size && endoffile == 0; i++){
    if(readframeheader(stream,mymfile,i) == 0){
      readframeinfo(stream,mymfile,i);
      //      printf("outside info: %s, num:%d\n",mymfile->Frame[i]->Frameinfo->info,i);

    }else{
      endoffile = 1; //terminate, we have no more data to read.
    }
   
  }
  //printf("moremore outside info: %s, num:%d\n",mymfile->Frame[1]->Frameinfo->info,1);


}
//---------------------------------------------------
void readheader(FILE *stream,ID3 mymfile){

  char result[128];
  mymfile->Header = malloc(sizeof(struct header));

  readfile(stream,3,result); // read identifier
  mymfile->Header->ID3v2identifier = malloc(strlen(result)+1);
  strcpy(mymfile->Header->ID3v2identifier,result);
  
  readfile(stream,2,result); // read version
  mymfile->Header->ID3v2version = atoi(result);
  
  readfile(stream,1,result); // read flags
  mymfile->Header->ID3v2falgs = atoi(result);
  
  mymfile->Header->ID3v2size = readsize(stream,4);// read size
  

}
//------------------------------------------
int readsize(FILE *stream, int n){

  int result = 0;
  int accum = 0;

  for(int i = 0; i<n;i++){
    result = fgetc(stream);
    //printf("result:%d\n",result);
   
    accum = accum << 7;
    result = result & 127;
    //printf("accum:%d\n",accum);
    accum = result | accum;
    //printf("accumR:%d\n",accum);

}
  //printf("accumT:%d\n",accum);
  return accum;

}
//------------------------------------------
void readfile(FILE *stream, int n, char *result){

  int numreads = 0;
  //read one byte per loop
  while((result[numreads] = fgetc(stream)) != EOF && numreads < n){
    // printf("tag: %d\n",result[numreads]); 
  numreads++;
  }
  ungetc(result[numreads],stream); // put last char back
  result[numreads] = '\0';
}
