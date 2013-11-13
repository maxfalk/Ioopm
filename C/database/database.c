#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "database.h"



struct bitree{
  char *key;
  char *value;
  struct bitree *left;
  struct bitree *right;
};
//Satt in data i nod.

void destroynode(db node);

//----------------------------------------------------------------------------------------------
void insertdata(char *value, char *key, db newNode){

  if(newNode->key != NULL){
    free(newNode->key);
  }

  newNode->key = malloc(strlen(key) + 1);//skapa plats pa heapen.

  if(newNode->value != NULL){
    free(newNode->value);
  }

  newNode->value = malloc(strlen(value) + 1);//skapa plats pa heapen.

  if(value != NULL){
    strcpy(newNode->value, value);//kopiera varden till noden.
  }

  if(key != NULL){
    strcpy(newNode->key, key);//kopiera till noden.
  }
}
//Skapa tom db.
//----------------------------------------------------------------------------------------------
db createdb(){
  return NULL;
}
//Skapa en ny nod som ar tom.
//----------------------------------------------------------------------------------------------
db emptynode(){

  db newNode = malloc(sizeof(struct bitree)); 
  newNode->left = NULL;//Satt grenarna till NULL, for att hjalpa sokningen genom databasen.
  newNode->right = NULL;
  newNode->key = NULL;
  newNode->value = NULL;
  return newNode;

}
//Jamfor en nyckel i en nod med en annan nyckel.
//----------------------------------------------------------------------------------------------
int comparekey(char *key,db node){
 
    return strcmp(key, node->key);

}
//Satter in vardet i noden till result.
//----------------------------------------------------------------------------------------------
void copy(char *result,db node){

  char *nodeValue = node->value;
  strcpy(result,nodeValue);

}
//Soker efter en nyckel i en nod, satt resultatet till dess varde, annars NULL.
//----------------------------------------------------------------------------------------------
db querydb(db node,char *key,char *result,int *status){

  int direction;
  *status = 0;
  if(node == NULL){
    *status = 1; //Om nyckeln inte hittas innan man nar NULL satts status till 1. 
  }
  else if(node != NULL && (direction = comparekey(key,node)) == 0){
    copy(result,node); //Om noden inte ar NULL och nyckeln ar detsamma som nyckeln i den nuvarnade noden satt in vardet fran den nyckeln i result.
    // Uppdaterar ocksa direction.
  }
  else if(direction < 0){ //byter nod och fortsatter leta vanster i tradet (nyckeln man undersokte var "storre" an den nyckeln man letar efter).
    node->left = querydb(node->left, key,result,status);  
  }
  else{ //byter nod och fortsatter leta hoger i tradet (nyckeln man undersokte var "storre" an den nyckeln man letar efter).
    node->right = querydb(node->right, key,result,status);
  }

  return node;
}
//Insatter nyckel och varde till en nod, inga kopior av nyckeln ar tillatna.
//----------------------------------------------------------------------------------------------
db insertdb(db node,char *key,char * value, int *status) { 
  
  int direction;
  *status = 0;
  if(node == NULL){//Nyckeln inte hittad.
    //Satt in nyckel och varde i ratt position.
    node = emptynode();//Skapa ny nod.
    insertdata(value,key,node);//Satt in nyckel och varde i den noden.
    *status = 1;
  } 
  else if((direction = comparekey(key,node)) == 0){
    //Nycklen finns redan.
  
  }
  else if(direction < 0){//Ga till vanster gren.
    node->left = insertdb(node->left, key,value,status);  
  }
  else{//Ga till hoger gren.
    node->right = insertdb(node->right, key,value,status);
  }

  return node;

}
//Updaterar noden om den hittas.
//----------------------------------------------------------------------------------------------
db updatedb(db node, char *key, char *value, int *status){

  int direction;
  *status = 0;
  if(node == NULL){
    //nod/nyckel hittas inte.
  }
  else if(node != NULL && (direction = comparekey(key,node)) == 0){
    //nod hittas updatera varde.
    strcpy(node->value,value);
    *status = 1;
  }
  else if(direction < 0){//vanster gren.
    node->left = updatedb(node->left, key,value,status);  
  }
  else{ //hoger gren.
    node->right = updatedb(node->right, key,value,status);
  }

  return node;
}
//Ga igenom databasen alltid till den hogra grenen.
//----------------------------------------------------------------------------------------------
db treverseright(db node,db newNode){
 
  if(node->right != NULL){  
    node->right = treverseright(node->right,newNode);
  }
  else{
    //hittade ratt node
    db tmpnode = node;
    insertdata(node->value,node->key,newNode);
    node = node->left;
    destroynode(tmpnode);
  }
  return node;
}
//Tar bort noden med nyckel.
//----------------------------------------------------------------------------------------------
db deletedb(db node,char *key, int *status,char *result){

  int direction;
  *status = 0;

  if(node == NULL){
    //Hittade den inte.
  } 
  else if((direction = comparekey(key,node)) == 0){
    //Har noden som ska tas bord.
    *status = 1;
    strcpy(result,node->value);
    //Undersoker grenarna for att bestamma nasta handling.
    if(node->left == NULL && node->right != NULL){
      //Hoger gren.
      //Ta bort nod ochsatter med hogernod.
      db tmpnode = node;
      node = node->right;
      destroynode(tmpnode);
    }
    else if(node->left != NULL && node->right == NULL){
      //Vanster gren.
      db tmpnode = node;
      node = node->left;
      destroynode(tmpnode);
    }
    else  if(node->left != NULL && node->right != NULL){
      //Tva existerande grenar.
      db newNode = emptynode();
      node->left = treverseright(node->left,newNode);
      db tmpnode = node;
      newNode->left = node->left;
      newNode->right = node->right;
      node = newNode;
      
      destroynode(tmpnode);
      //puts(node->key);
    }
    else{
      //Inga grenar.
      destroynode(node);
      node = NULL;
    }
   
  }
  else if(direction < 0){//Vanster gren.
    node->left = deletedb(node->left, key,status,result);  
  }
  else{//Hoger gren.
    node->right = deletedb(node->right, key,status,result);
  }

  return node;

}
//-----------------------------------------------------
void destroynode(db node){
 
  if(node->key != NULL)
    free(node->key);
  if(node->value != NULL)
    free(node->value);

  free(node);

}
//----------------------------------------------------------------------------------------------
db destroy(db node){

  if(node != NULL){
    
    destroy(node->left);
    destroy(node->right);
    destroynode(node);
  
  
  }

  return node;

}
//Skriver ut hela databasen.
//----------------------------------------------------------------------------------------------
db printdb(db node){

  if(node != NULL){
    printf("Key:%s, Value: %s\n",node->key,node->value);    
    printdb(node->left);
    printdb(node->right);
  }
  return node;

}
