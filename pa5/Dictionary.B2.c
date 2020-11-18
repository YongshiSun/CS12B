//--------------------------------------------------------------------------
// Yongshi Sun ID:1619410
// Dictionary.c
// The hash table implementation of Dictionary ADT
//--------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

const int tableSize=101;

// private types ------------------------------------------------
//NodeObj
typedef struct NodeObj{
    char* key;
    char* value;
    struct NodeObj* next;
} NodeObj;

//Node
typedef NodeObj* Node;

// newNode()
// constructor for private Node type
Node newNode(char* k, char* v) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = k;
   N->value = v;
   N->next= NULL;
   return(N);
}

// freeNode()
// destructor for private Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// DictionaryObj
typedef struct DictionaryObj{
   //List* table[tableSize];
   Node head[tableSize]; 
   int numItems;
} DictionaryObj;
/*
//ListObj
typedef struct ListObj{
    Node head;
    int numPairs;
}ListObj;

//List
typedef ListObj* List;

//newList()
List newList(void) {
   List N = malloc(sizeof(ListObj));
   assert(N!=NULL);
   N->head = NULL;
   N->numPairs = 0;
   return(N);
}

//freeList()
void freeList(List* pL){
   if( pL!=NULL && *pL!=NULL ){
      free(*pL);
      *pL = NULL;
   }
}
*/
// private functions ------------------------------------------------

// findKey()
// returns the Node containing key k in the hash table, or returns
// NULL if no such Node exists
Node findKey(Node R, char* k){
   if(R==NULL || strcmp(k, R->key)==0) 
      return R;
   else 
      return findKey(R->next, k);
}

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
 int sizeInBits = 8*sizeof(unsigned int);
 shift = shift & (sizeInBits - 1);
 if ( shift == 0 )
 return value;
 return (value << shift) | (value >> (sizeInBits - shift));
}
// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
 unsigned int result = 0xBAE86554;
 while (*input) {
 result ^= *input++;
 result = rotate_left(result, 5);
 }
 return result;
}
// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
 return pre_hash(key)%tableSize;
}

// printInOrder() WORKED
// prints the (key, value) pairs belonging to the hash table rooted at R in order
// of increasing keys to file pointed to by out.
void printInOrder(FILE* out, Node R){
   if( R!=NULL ){
      fprintf(out, "%s %s\n", R->key, R->value);
      R = R->next;
   }
}


// deleteAll()
// reset L to the empty state
void deleteAll(Dictionary L){
   Node N =NULL;
   if( L==NULL ){
      fprintf(stderr, 
         "List Error: calling deleteAll() on NULL List reference\n");
      exit(EXIT_FAILURE);
    }
        for( int i = 0; i<tableSize; i++){
        N = L->head[i];
        N = NULL;
        freeNode(&N);
        }
           

 
}

// public ADT operations ------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(){
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D!=NULL);
    for(int i = 0; i<tableSize;i++){
        D->head[i] = NULL;
    }
    D->numItems = 0;
    return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   if( pD!=NULL && *pD!=NULL ){
      makeEmpty(*pD);
      free(*pD);
      *pD = NULL;
   }
}

// isEmpty() WORKED
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numItems==0);
}

// size() WORKED
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling size() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numItems);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
   Node N;
   int index = hash(k);
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   N = findKey(D->head[index], k);
   return ( N==NULL ? NULL : N->value );
}

// insert() WORKED
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
   Node N, A, B;
   int index = hash(k);
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling insert() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( lookup(D, k)!=NULL ){
      fprintf(stderr, 
         "Dictionary Error: cannot insert() duplicate key: \"%s\"\n", k);
      exit(EXIT_FAILURE);
   }
   N = newNode(k, v);
   B = NULL;
   A = D->head[index];
   while( A!=NULL ){
      B = A;
        A = A->next;
   }
   if( B==NULL ) D->head[index] = N;
   else B->next = N;
   D->numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
   Node N, P, S;
   int index = hash(k);
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling delete() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   N = lookup(D, k);
   if( N==NULL ){
      fprintf(stderr, 
         "Dictionary Error: cannot delete() non-existent key: \"%s\"\n", k);
      exit(EXIT_FAILURE);
   }
if(N->next == NULL){
    if(N==D->head[index]){
        D->head[index] = NULL;
        freeNode(&N);
    }else{
        P = D->head[index-1];
        P->next = NULL;
        freeNode(&N); 
    }
}if(N->next !=NULL){
    S = D->head[index-1];
    S->next = N->next;
    freeNode(&N);  
}
}



// makeEmpty()  WORKED
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
   deleteAll(D);
   for(int i = 0; i< tableSize;i++){
        D->head[i] = NULL;
   }
   D->numItems = 0;
}


// printDictionary() WORKED
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling printDictionary() on NULL"
         " Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   for(int i = 0; i< tableSize; i++){
    printInOrder(out, D->head[i]);
   }
}
