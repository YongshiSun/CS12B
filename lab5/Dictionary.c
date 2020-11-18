//-----------------------------------------------------------------------------
// Dictionary.c
// c. Yongshi Sun ID:1619410
// implementation file for the Dictionary ADT
//-----------------------------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Dictionary.h"

// private types-----------------------------------------------------------------

//NodeObj
typedef struct NodeObj{
    char* key;
    char* value;
    struct NodeObj* next;
} NodeObj;

//Node
typedef NodeObj* Node;

//newNode()
//constructor of the Node type
Node newNode(char* k, char* v){
    Node N = malloc(sizeof(NodeObj));
    assert(N!=NULL);
    N->key = calloc(strlen(k)+1, sizeof(char));
    N->value = calloc(strlen(k)+1, sizeof(char));
    strcpy(N->key, k);
    strcpy(N->value,v);
    N->next = NULL;
    return(N);
}

//freeNode()
//destructor for the Node type
void freeNode(Node* pN){
    if(pN!=NULL && *pN!=NULL){
        free((*pN)->key);
        free((*pN)->value); 
        free(*pN);
        *pN = NULL;
    }
}

//DictionaryObj
typedef struct DictionaryObj{
    Node head;
    Node tail;
    int numItems;
} DictionaryObj;




// public functions--------------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D!=NULL);
    D->head = NULL;
    D->tail = NULL;
    D->numItems = 0;
    return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
    if(pD!=NULL && *pD!=NULL){
        //if(!isEmpty(*pD)) makeEmpty(*pD);
        free(*pD);
        *pD = NULL;
    }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
    if( D==NULL){
        fprintf(stderr,"Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return(D->numItems==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
    return (D->numItems);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
    //when Dictionary is NULL
    if(D == NULL){
       fprintf(stderr,"Dictionary Error: calling lookup() on NULL Dictionary reference \n");
       exit(EXIT_FAILURE); 
    }
    
    //set N to point to head
    Node N = D->head;
    // set F (Found Node) to head
    Node F = N;
    // set found to be false
    int found = 0;

    //scan for matching key
    while(N != NULL && !found){
        //if found
        if(!strcmp(N->key,k)){
            F = N;
            found = 1;
        }
        //not found, go to next node
        N = N->next;
    }
    if(found){
        return(F->value);
    }else{
        return NULL;
    }
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
    if(lookup(D,k)!= NULL){
        fprintf(stderr,"Dictionary Error: calling insert() on a pair that already exist in Dictionary reference \n");
        exit(EXIT_FAILURE);
    }else{
        Node N = newNode(k,v);
        //base case (list is empty)
        if(isEmpty(D)){
            D->head = N;
            D->tail = N;
        }else{//list not empty, just add to end of list
            D->tail->next = N;
            D->tail = D->tail->next;
        }
        D->numItems++;
    }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
    if(lookup(D,k)==NULL){
        fprintf(stderr,"Dictionary Error: calling delete() on a pair that does not exist in Dictionary reference \n");
        exit(EXIT_FAILURE);
    }else{
        Node now = D->head;
        Node back = D->head;
        int found = 0;

        //base case (only 1 node in list)
        if(D->numItems == 1){
            freeNode(&D->head);
            D->head = NULL;
            D->tail = NULL;
        }else{
            //scan for key
            while(!found){
                //if found
                if(!strcmp(now->key,k)){
                    found =1;

                    //key is head
                    if( now == D->head ){
	                    D->head = D->head->next;
	                    now->next = NULL;
	                    freeNode(&now);
	                }

                    //key is tail
                    else if(now == D->tail){
                        D->tail = back;
                        back->next = NULL;
                        freeNode(&now);    
                    }

                    //key is middle
                    else{
                        back->next = now->next;
                        now->next = NULL;
                        freeNode(&now);
                    }
                }

                //not found
                else{
                    //at begining
                    if(now ==D->head){
                        //move to next node
                        now = now->next;
                    }else{
                        //move both Nodenow and Nodeback
                        now = now->next;
                        back = back->next;
                    }
                }
            }
        }
        D->numItems--;
    }
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
    //set Node to head so you can delete from head to tail
    Node now = D->head;
    Node advance = D->head; 
    while(now != NULL){
        //advance Node advance
        advance = advance->next; 
        //deleting Node now
        freeNode(&now);
        //advance Node now
        now = advance;
    }
    //reset head and tail back to when list was empty
    D->head = NULL;
    D->tail = NULL;
    D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
    Node N;
    if(D==NULL){
        fprintf(stderr,"Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    for(N=D->head; N!=NULL; N=N->next){
        fprintf(out,"%s %s",N->key, N->value);
        fprintf(out,"\n");
    }
}