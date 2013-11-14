/*! \mainpage Database
 *
 * \section intro_sec Introduction
 *
 * An implementation of a simple database.
 *
*/


/** @file database.h
* @brief An impementation for a simple database.
*
* This module conatians functions for working with a simple database and
* for sotring and getting information saved in it.
*
* @auther Max Falk Nilsson
*/


#ifndef DATABASE_H // check if file is already loaded(defined)
#define DATATBASE_H // define file

/**
*typedef for the struct bitree 
*/
typedef struct bitree *db;


/**
*Creates a new empty database.
*@return The new empty database.
*/
db createdb();
/**
*Destroys the given database and frees all it's memory.
*@param database The database to destry.
*@return db A empty database .
*/
db destroy(db database);
/**
*Query the database.
*@param database The database to look query.
*@param key The key to lookup.
*@param result The value corresponding to the given key.
*@param status Takes either the value 1 or 0, 1 if the key wasn't found. 0 if it did.
*@return the given database.
*/
db querydb(db database,char *key,char *result,int *status);
/**
*Update a entry in the database
*@param database The database to look in.
*@param key The key of the value to update.
*@param value The new value to update the entry with the given key.
*@param status Indicates if the update was successful with a 0, and unsuccessful with a 1.
*@return The updated database
*/
db updatedb(db database,char *key,char *value,int *status);
/**
*Inserts a new entry in the database.
*@param database The database to enter the new entry.
*@param key The key of the new entry.
*@param value The value of the new entry.
*@param status Indicates if the insert was successful with a 0, and unsuccessful with a 1.
*@return The database with the new entry inserted.
*/
db insertdb(db database,char *key,char *value,int *status);
/**
*Delete a entry in the database.
*@param database The database the remove the enrty in.
*@param key The key of the entry to remove.
*@param status Indicates if the insert was successful with a 0, and unsuccessful with a 1.
*@param result The value which was removed.
*@return The database without the removed entry.
*/
db deletedb(db database,char *key,int *status,char *result);
/**
*Prints the database to stdout.
*@param database The database to be printed.
*/
db printdb(db database);

#endif
