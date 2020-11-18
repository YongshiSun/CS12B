//-----------------------------------------------------------------------------
// DictionaryTest.c
// Test client for the Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
    Dictionary A = newDictionary();
    char* k;
    char* v;

    printf("%s\n", (isEmpty(A)?"true":"false"));
    insert(A,"one", "hi");
    insert(A,"two", "bro");
    //insert(A,"two", "bro");
    printf("%s\n", (isEmpty(A)?"true":"false"));
    printf("%d\n", (size(A)));
    printDictionary(stdout, A);
    
    delete(A, "two" );
    printf("%d\n", (size(A)));

    printDictionary(stdout, A);
    k = "one";
    v = lookup(A, k);
    printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);

    makeEmpty(A);
    printf("%s\n", (isEmpty(A)?"true":"false")); 


}