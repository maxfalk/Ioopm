#include <stdio.h>
#include <string.h>
#include "CUnit/Basic.h"
#include "musiclibdir.h"
#include <dirent.h>
#include <sys/stat.h>
#include <sys/types.h>
/*
run test:
gcc -Wall -I$HOME/local/include databasetest.c database.c database.h -L$HOME/local/lib -lcunit -o myprog

*/
static FILE* tempfile = NULL;

/* The suite initialization function.
 * Make files and folders we need for testing
 * 
 */
int init_suite1(void)
{
	if(makefolder("H:/musictest1") != 0  || (tempfile = fopen("H:/musictest1/temp.txt","w+")) == NULL){
		return -1;
	}else{
		return 0;
	}
}

/* The suite cleanup function.
 * Remove folders and files we created for the tests.
 * 
 */
int clean_suite1(void)
{
	if (0 != fclose(tempfile)) {
      return -1;
   }
   else {
    tempfile = NULL;
	rmdir("H:/musictest1/musictest2");
	remove("H:/musictest1/movetest.txt");
	rmdir("H:/musictest1");
      return 0;
   }

}

/*
* Test listdir
*
*
*/
void test_listdir(void){
	char pathlist[100][50];
	listdir("H:/musictest1",pathlist);
	CU_ASSERT(strcmp(pathlist[0],"H:/musictest1/temp.txt") == 0);  
}
/*
*  Test movefile
*
*
 */
void test_movefile(){
	char pathlist[100][50];
	movefile("H:/musictest1/temp.txt","H:/musictest1/movetest.txt");
	listdir("H:/musictest1",pathlist);
	CU_ASSERT(strcmp(pathlist[0],"H:/musictest1/movetest.txt") == 0);  
}
/*
*  Test makefolder
*
*
 */
void test_makefolder(){
	makefolder("H:/musictest1/musictest2");
	CU_ASSERT(checkfordir("H:/musictest1","musictest2")  == 1); 
}
/*
* Test checkfordir
*
*
 */
void test_checkfordir(){
	CU_ASSERT(checkfordir("H:/musictest1","musictest2")  == 1); 
}
/*
* Test changedirdown
*
*
 */
void test_changedirdown(){
	char dir[100] = "C:/folder/fakefolder/file.txt";
	changedirdown(dir);
	CU_ASSERT(strcmp(dir,"C:/folder/fakefolder/") == 0);


}
/*
* Test dirconcat
*
*
 */
void test_dirconcat(){
	char first[50] = "C:/folder1";
	char last[50] = "folder2";
	dirconcat(first,last);
	CU_ASSERT(strcmp(first,"C:/folder1/folder2") == 0);

}
/*
* Test getfilename
*
*
 */
void test_getfilename(){
	char path[100] = "C:/folder/fakefolder/file.txt";
	getfilename(path);
	CU_ASSERT(strcmp(path,"file.txt") == 0);

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
   if ((NULL == CU_add_test(pSuite, "test of test_listdir()", test_listdir)) ||
       (NULL == CU_add_test(pSuite, "test of test_movefile()", test_movefile)) ||
	   (NULL == CU_add_test(pSuite, "test of test_makefolder()", test_makefolder)) ||
	   (NULL == CU_add_test(pSuite, "test of test_checkfordir()", test_checkfordir)) ||
	   (NULL == CU_add_test(pSuite, "test of test_changedirdown()", test_changedirdown)) ||
	   (NULL == CU_add_test(pSuite, "test of test_dirconcat()", test_dirconcat)) ||
	   (NULL == CU_add_test(pSuite, "test of test_getfilename()", test_getfilename)))
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