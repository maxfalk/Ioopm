#include <stdio.h>
#include "convert_content.h"
#include "string.h"
#include "ID3Info.h"

#define MAXFRAMES 5000

/**
*@struct header ID3Info.h ID3Info.h 
*@brief Saves the header of a ID3tag.
*/
struct header{
  char *ID3v2identifier; /**< "ID3" */
  int ID3v2version;     /**< Version of ID3*/
  int ID3v2falgs;       /**< Flags*/
  int ID3v2size; /**< size of ID3 tag**/

};
/**
*@struct frameinfo ID3Info.h ID3Info.h 
*@brief Saves the information and the sign type of a frame.

*/
struct frameinfo{
  int type; /**< 0x0 ISO-8859-1, 0x01 USC-2.*/
  char *info; /**< Information saved (ex artist, album,songname)*/

};
/**
*@struct frame ID3Info.h ID3Info.h 
*@brief The frame information.
*/
struct frame{
  char *FrameID; /**< The type of info given */
  int Size; /**<  The size of post to come, not including this header */
  int Flags; /**< Flags */
  struct frameinfo *Frameinfo; /**< Frame inforamtion */
};

/**
*@struct id3 ID3Info.h ID3Info.h 
*@brief Saves all frames of the ID3tag and the ID3s header.
*/
struct id3{
  struct header *Header; /**< Header of the ID3tag*/
  struct frame *Frame[MAXFRAMES]; /**< Frames in the ID3tag*/
};
 
/**
*Tags = Låttitel, huvudartist, album,spårnummer, Genre, inspelningsår
*/
char *tags[] = {"TIT2","TPE1","TALB","TRCK","TCON","TYER"};

void destroyframe(struct frame *fr);
void readfile(FILE*,int,char*);
void readheader(FILE*,ID3);
int readsize(FILE*,int);
int readframeheader(FILE*,ID3,int);
int readframeinfo(FILE*,ID3 ,int);
void readframes(FILE*, ID3);
void destroyheader(struct header *hr);
void destroyframeinfo(struct frameinfo *frami);


//deallocate data in the header struct
void destroyheader(struct header *hr){
  free(hr->ID3v2identifier);
  free(hr);
}
//deallocate data in the framinfo struct
void destroyframeinfo(struct frameinfo *frami){
  free(frami->info);
  free(frami);
}
//deallocate data in the fram struct
void destroyframe(struct frame *fr){
  free(fr->FrameID);
  free(fr);
}
//deallocate all the data in the ID3 struct
void destroyid3(ID3 mymfile){
  //deallocate header
  destroyheader(mymfile->Header); 
 
  for(int i = 0;i <5000; i++){
    if(mymfile->Frame[i] != NULL){//release frames and all the data in them.
      destroyframeinfo(mymfile->Frame[i]->Frameinfo);
      destroyframe(mymfile->Frame[i]);
    }else
      break;// end of frames to release
  }
  //deallocate ID3
  free(mymfile);

}
//--------------------------------------------------------------------
ID3 readid3info(char* filename){

  //allocate room for the ID3 info
  ID3 mymfile = malloc(sizeof(struct id3));
  //open file
  FILE *stream = fopen(filename,"r");
  //read header in the file
  readheader(stream,mymfile);
  //printf("stuffhere: %s\n",mymfile->Header->ID3v2identifier);
  //read frames in the file
  readframes(stream,mymfile);
  //printf("more more outside info: %s, num:%d\n",mymfile->Frame[0]->Frameinfo->info,0);
  //printf("tag: %s\n",getinfo(mymfile,'H'));
  //close file
  fclose(stream);

  return mymfile;
}

//-------------------------------------------------------------
char *getinfo(ID3 mymfile, char value){
//Tags = Låttitel, huvudartist, album,spårnummer, Genre, inspelningsår

 char *searchvalue;
  int valid = 1;
  //Choose what info to get from the ID3 info read.
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
    //Search in the ID3 struct for the info
    while(mymfile->Frame[i] != NULL && valid == 1){
	if(strcmp(mymfile->Frame[i]->FrameID,searchvalue) == 0){ // check for given value
	result = mymfile->Frame[i]->Frameinfo->info;
	//printf("%s\n",mymfile->Frame[i]->Frameinfo->info);    
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
    mymfile->Frame[framenum] = NULL; //mark end of frames 
    //printf("End at:%d\n", framenum);
    return 1; // no data
  }
}
//-------------------------------------------------
int readframeinfo(FILE *stream, ID3 mymfile,int framenum){
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

  return framesize;
}
//--------------------------------------------------
void readframes(FILE*stream, ID3 mymfile){
  
  int ID3v2size = mymfile->Header->ID3v2size;
  //printf("sizeid3:%d\n.",ID3v2size);
  int endoffile = 0;
  int bytesread = 0;


  for(int i = 0; bytesread < ID3v2size && endoffile == 0 && i < MAXFRAMES; i++){
    if(readframeheader(stream,mymfile,i) == 0){
      bytesread += 10; // 10 bytes is for the frame header.
      bytesread += readframeinfo(stream,mymfile,i);// count bytes read
    }else{
      endoffile = 1; //terminate, we have no more data to read.
    }
   
  }
 


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
