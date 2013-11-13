#ifndef DATABASE_H // check if file is already loaded(defined)
#define DATATBASE_H // define file

//defines a binary tree with two branches, keys with lower/higher value than the current node is placed to the left/right (if there is an NULL branch there)



typedef struct bitree *db;

//list our publicly accessible functions
db createdb();
db destroy(db);
db querydb(db,char*,char*,int*);
db updatedb(db,char*,char*,int*);
db insertdb(db,char*,char*,int*);
db deletedb(db,char*,int*,char*);
db printdb(db);

#endif
