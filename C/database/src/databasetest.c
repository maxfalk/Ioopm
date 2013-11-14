#include "CUnit/Headers/CUnit.h"
#include "database.h"

void test_createdb(){
	CU_ASSERT(createdb() == NULL);

}