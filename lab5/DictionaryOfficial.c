//-----------------------------------------------------------------------------
//   Dictionary.c
//   Implementation file for Dictionary ADT
//-----------------------------------------------------------------------------

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <string.h>
#include "Dictionary.h"

// private types --------------------------------------------------------------

// PairObj
typedef struct PairObj{
  char* key;
  char* value;
}PairObj;


// Pair
typedef PairObj* Pair;


// newPair()
// constructor for the pair type
Pair newPair(char* k, char *v){
  
  // allocate memory in heap for new Pair
  Pair P = malloc(sizeof(PairObj));
  
  // check that memory is allocated
  assert( P != NULL );
  
  // allocate memory for key and value
  P->key = calloc(strlen(k)+1, sizeof(char));
  P->value = calloc(strlen(v)+1, sizeof(char));
  
  // init Pair with k and v
  strcpy(P->key, k);
  strcpy(P->value, v);
  
  // return the pointer to pair
  return (P);
}


// freePair()
// destructor for the pair type
void freePair(Pair* pP){
  if( pP != NULL && *pP != NULL){
    
    // free memory allocated for key and value
    free((*pP)->key);
    free((*pP)->value);
    
    // free memory allocated for PairObj
    free(*pP);
    *pP=NULL;
  }
}


// NodeObj
typedef struct NodeObj{
  Pair pair;
  struct NodeObj* next;
}NodeObj;


// Node
typedef NodeObj* Node;


// newNode()
// constructor for the Node type
Node newNode(char* k, char* v){

  // allocate memory in heap for new node and corresponding pair
  Node N = malloc(sizeof(NodeObj));
  Pair P = newPair(k, v);

  // make sure that memory is allocated
  assert( N != NULL && P != NULL);

  // initialize new node fields
  N->pair = P;
  N->next = NULL;

  // return pointer to NodeObj
  return (N);
}


// freeNode()
// destructor for the Node type
void freeNode(Node* pN){

  // first free memory allocated for pair of Node
  freePair(&(*pN)->pair);

  // Now free memory allocated for NodeObj
  if( pN != NULL && *pN != NULL){
    free(*pN);
    *pN=NULL;
  }
}


// DictionaryObj
typedef struct DictionaryObj{
  Node head;
  Node tail;
  int numItems;
}DictionaryObj;


// public functions -----------------------------------------------------------


// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){

  // allocate memory in heap for new Dictionary
  Dictionary D = malloc(sizeof(DictionaryObj));

  // make sure memory is allocated
  assert( D != NULL );

  // initialize new Dictionary fields
  D->head = NULL;
  D->tail = NULL;
  D->numItems = 0;

  // return pointer to DictionaryObj
  return (D);
}


// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
  
  // free memory allocated for DictionaryObj
  if( pD != NULL && *pD != NULL){
    free(*pD);
    *pD = NULL;
  }
}


// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){

  // check if referencing a NULL Dictionary
  if ( D == NULL ){
    fprintf(stderr, 
	    "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }
  
  // return true if empty
  return (D->numItems==0);
}


// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
  
  // check if referencing a NULL Dictionary
  if( D == NULL){
    fprintf(stderr, 
	    "Dictionary Error: calling size() on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }

  // return numItems
  return (D->numItems);
}


// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){

  // check if referencing a NULL Dictionary
  if ( D == NULL ){
    fprintf(stderr,
	    "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }


  // set N to point to begining of list
  Node N = D->head;
  // set F (the found Node) to be List head too
  Node F = N;
  // set found to be false
  int found = 0;  

  // search list for matching key
  while( N != NULL && !found ){

    // if key is found
    if( !strcmp(N->pair->key, k) ){
      // set F to found node N
      F = N;
      // set found to true
      found = 1;
    }
    // if not found, move on to next node
    N = N->next;
  } 
  
  // if found, return corresponding value
  if (found){
    return(F->pair->value); 
  }
  else{
    return NULL;
  }
   
}


// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){

  // check if key already exists
  if( lookup(D, k) != NULL ){
    fprintf(stderr, 
	    "Dictionary Error: calling insert() with a duplicate key: %s\n", k);
  }
  else{
    // create a new node with k and v as newPair                                               
    Node N = newNode(k, v);

    // if list is empty                                                                         
    if(isEmpty(D)){
      D->head = N;
      D->tail = N;
    }
    else{
      // add new node to end of list                                                            
      D->tail->next = N;
      D->tail = D->tail->next;
    }
    
    // increment number of items                                                              
    D->numItems++;
  }
}


// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){

  // if key does not exist
  if( lookup(D, k) == NULL ) {
    fprintf(stderr,
	    "Dictionary Error: calling delete() with a non-existing key: %s\n", k);
  }
  else{
  Node current  = D->head;
  Node previous = D->head;
  int found     = 0;

  // if dictionary has only only one node
  if( D->numItems == 1 ){
    freeNode(&D->head);
    D->head = NULL;
    D->tail = NULL;
  }
  else{
    // loop until key is found
    while( !found ){

      // if key is found
      if( !strcmp(current->pair->key, k) ){
	// set found to true
	found = 1;
	
	// if key is head
	if( current == D->head ){
	  D->head = D->head->next;
	  current->next = NULL;
	  freeNode(&current);
	}
	// if key is tail
	else if( current == D->tail){
	  D->tail = previous;
	  previous->next = NULL;
	  freeNode(&current);
	}
	// if key is a middle node
	else{
	  previous->next = current->next;
	  current->next = NULL;
	  freeNode(&current);
	}
      }
      // if key is not yet found
      else{
	// if still at beginning
	if( current == D->head){
	  // advance current only
	  current = current->next;
	}
	else{
	  // advance both previous and current
	  current = current->next;
	  previous = previous->next;
	}
      }
    }
  }
  // decrement numItems after removing node
  D->numItems--;
  }
}


// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
  
  // check if NULL referencing D
  if( D == NULL ){
    fprintf(stderr, "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }

  Node current = D->head;
  Node delete  = D->head;

  while( current != NULL ){
    // move current to next    
    current = current->next;
    // delete node
    freeNode(&delete);
    // move delete to be current
    delete = current;
  }
  // init Dictionary
  D->numItems = 0;
  D->head     = NULL;
  D->tail     = NULL;
}


// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){

  // check if NULL referencing D                                                            
  if( D == NULL ){
    fprintf(stderr, 
	    "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }

  // start at head
  Node N = D->head;

  // for each node, print key and value
  for(; N != NULL; N = N->next){
    fprintf(out, "%s %s\n", N->pair->key, N->pair->value);
  }

}