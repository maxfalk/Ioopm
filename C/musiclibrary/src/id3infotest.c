#include <stdio.h>
#include <string.h>
#include "CUnit/Basic.h"
#include "ID3Info.h"
/*
run test:
gcc -Wall -std=c99 -I$HOME/local/include id3infotest.c ID3Info.c ID3Info.h -L$HOME/local/lib -lcunit -o id3test

*/
ID3 id3tag = NULL; /* ID3tag to use for tests*/
char *filename = "H:/music0.mp3"; //path to a mp3 file.
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
* Test readid3info
* Test the reading of id3tags from a file. Must be and file with id3tags.
*
 */
void test_readid3info(void){
 CU_ASSERT((id3tag = readid3info(filename)) != NULL);  
}
/*
* Test getinfo
* Test the function for getting information from the id3 tag,
* this will be dependent of your test file. Change the varibles according to the test file.
*
 */
void test_getinfo(){
	char *title = "Apostlarna";
	char *artist = "Tolvan Tolvansson";
	char *album = "Best of Tolvan";
	char *year = "1912"; 
	CU_ASSERT(strcmp(getinfo(id3tag,'L'),title) == 0);
	CU_ASSERT(strcmp(getinfo(id3tag,'H'),artist) == 0);
	CU_ASSERT(strcmp(getinfo(id3tag,'A'),album) == 0);
	CU_ASSERT(strcmp(getinfo(id3tag,'I'),year) == 0);
	

}
/*
* Test destroyid3
*
*
 */
void test_destroyid3(){
	destroyid3(id3tag);

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
   if ((NULL == CU_add_test(pSuite, "test of test_readid3info()", test_readid3info)) ||
       (NULL == CU_add_test(pSuite, "test of test_getinfo()", test_getinfo)) ||
	   (NULL == CU_add_test(pSuite, "test of test_destroyid3()", test_destroyid3))){
      CU_cleanup_registry();
      return CU_get_error();
   }

   /* Run all tests using the CUnit Basic interface */
   CU_basic_set_mode(CU_BRM_VERBOSE);
   CU_basic_run_tests();
   CU_cleanup_registry();
   return CU_get_error();
}