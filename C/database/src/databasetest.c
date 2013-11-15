#include <stdio.h>
#include <string.h>
#include "CUnit/Basic.h"
#include "database.h"
/*
run test:
gcc -Wall -I$HOME/local/include databasetest.c database.c database.h -L$HOME/local/lib -lcunit -o myprog

*/
/* Database to use for tests*/
db database = NULL;

/* The suite initialization function.
 * Not used as of now.
 * 
 */
int init_suite1(void)
{
	return 0;
}

/* The suite cleanup function.
 * Not used as of now.
 * 
 */
int clean_suite1(void)
{
	return 0;
}

/*
* Test the createdb function.
*
*
 */
void test_createdb(void)
{
 CU_ASSERT(createdb() == NULL);  
}

/* 
* Test insertdb
*  test_createdb must have been run before this.
*			
*		
 */
void test_insertdb(void)
{
  int status = 0;
	CU_ASSERT((database = insertdb(database,"e","test_e",&status)) != NULL);
	CU_ASSERT((database = insertdb(database,"b","test_b",&status)) != NULL);
	CU_ASSERT((database = insertdb(database,"f","test_f",&status)) != NULL);
	CU_ASSERT((database = insertdb(database,"a","test_a",&status)) != NULL);
	CU_ASSERT((database = insertdb(database,"c","test_c",&status)) != NULL);
	CU_ASSERT((database = insertdb(database,"g","test_g",&status)) != NULL);
}
/*
* Test querydb.
* test_insertdb must have been run before this test.
*
*/
void test_query(void){

	char result[50]= {""};
	int status = 0;
	CU_ASSERT(querydb(database,"e",result,&status) != NULL);
	CU_ASSERT(strcmp(result,"test_e") == 0);
	CU_ASSERT(querydb(database,"g",result,&status) != NULL);
	CU_ASSERT(strcmp(result,"test_g") == 0);
}
/*
* Test updatedb
* test_insertdb must have been run before this.
*
*/
void test_updatedb(void){
	int status = 0;
	char result[50]= {""};
	CU_ASSERT(updatedb(database,"e","updt_test_e",&status) != NULL);
	CU_ASSERT(querydb(database,"e",result,&status) != NULL)
	CU_ASSERT(strcmp(result,"updt_test_e") == 0);


}
/*
* Test deletedb.
* test_insertdb must have been run before this.
*/
void test_deletedb(){
	int status = 0;
	char result[50]= {""};
	CU_ASSERT((database = deletedb(database,"f",&status, result)) != NULL);
	CU_ASSERT(strcmp(result,"test_f") == 0);
	CU_ASSERT((database = deletedb(database,"b",&status, result)) != NULL);
	CU_ASSERT(strcmp(result,"test_b") == 0);
	CU_ASSERT((database = deletedb(database,"c",&status, result)) != NULL);
	CU_ASSERT(strcmp(result,"test_c") == 0);
	CU_ASSERT((database = deletedb(database,"a",&status, result)) != NULL);
	CU_ASSERT(strcmp(result,"test_a") == 0);
	CU_ASSERT((database = deletedb(database,"e",&status, result)) != NULL);
	CU_ASSERT(strcmp(result,"updt_test_e") == 0);
	CU_ASSERT((database = deletedb(database,"g",&status, result)) == NULL);
	CU_ASSERT(strcmp(result,"test_g") == 0);

}
/*
* Test test_printdb.
* test_deletedb must have been run before this.
*/
void test_printdb(void){
	int status = 0;
	CU_ASSERT(printdb(database) == NULL);
	database = insertdb(database,"e","test_e",&status);
	CU_ASSERT(printdb(database) != NULL);
}
/*
* Test test_destroy.
* 
*/
void test_destroy(void){

	CU_ASSERT(destroy(database) == NULL);
}
/* The main() function for setting up and running the tests.
 * Returns a CUE_SUCCESS on successful running, another
 * CUnit error code on failure.
 */
int main()
{
   CU_pSuite pSuite = NULL;

   /* initialize the CUnit test registry */
   if (CUE_SUCCESS != CU_initialize_registry())
      return CU_get_error();

   /* add a suite to the registry */
   pSuite = CU_add_suite("Suite_1", init_suite1, clean_suite1);
   if (NULL == pSuite) {
      CU_cleanup_registry();
      return CU_get_error();
   }

   /* add the tests to the suite */
   /* NOTE - ORDER IS IMPORTANT*/
   if ((NULL == CU_add_test(pSuite, "test of test_createdb()", test_createdb)) ||
       (NULL == CU_add_test(pSuite, "test of test_insertdb()", test_insertdb)) ||
	   (NULL == CU_add_test(pSuite, "test of test_query()", test_query)) ||
	   (NULL == CU_add_test(pSuite, "test of test_updatedb()", test_updatedb)) ||
	   (NULL == CU_add_test(pSuite, "test of test_deletedb()", test_deletedb)) ||
	   (NULL == CU_add_test(pSuite, "test of test_printdb()", test_printdb)) ||
	   (NULL == CU_add_test(pSuite, "test of test_destroy()", test_destroy)))
   {
      CU_cleanup_registry();
      return CU_get_error();
   }

   /* Run all tests using the CUnit Basic interface */
   CU_basic_set_mode(CU_BRM_VERBOSE);
   CU_basic_run_tests();
   CU_cleanup_registry();
   return CU_get_error();
}