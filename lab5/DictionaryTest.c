//-----------------------------------------------------------------------------
// DictionaryTest.c
// c. Yongshi Sun ID:1619410
// a Linked List implementation of the Dictionary.c
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
    //tesing isEmpty()
    Dictionary A = newDictionary();
    printf("%s\n", (isEmpty(A)?"true":"false"));

    //testing insert() and print()
    insert(A,"1","a");
    //insert(A,"1","a"); //checking for error message
    printDictionary(stdout, A);
    printf("%d\n", size(A));

    //testing delete() and size()
    insert(A,"2","b");
    printDictionary(stdout, A);
    //delete(A,"100"); //checking for error message
    delete(A,"1");
    printDictionary(stdout, A);
    printf("%d\n", size(A));

    //testing lookup()
    char* v;
    
    v = lookup(A, "2");
    printf("key=\"%s\" %s\"%s\"\n", "2", (v==NULL?"not found ":"value="), v);
    

    //testing makeEmpty()
    makeEmpty(A);
    printf("%s\n", (isEmpty(A)?"true":"false"));




}