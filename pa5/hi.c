#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
    char s1[4] = {1,2,3};
    char s2[4] = {4,5,6};
    char s3[20];
    cat(s1,s2);

    printf("%c\n", s3[0]);
    printf("%c\n", s3[1]);
    printf("%c\n", s3[2]);
    printf("%c\n", s3[3]);
}

char* cat(char* s1, char* s2){
   //char i;
   char* s3 = (char*)malloc(sizeof(char*)*20);

   int lengths1=0;
   int lengths2=0;
   int notnull1=0;
   int notnull2=0;

   for( int i = 0; notnull1 ==0; i++){
      if(s1[i]!='\0'){
         lengths1++;
      }else{
         notnull1++;
      }
   }

   for (int j =0;notnull2 ==0; j++){
     if(s2[j]!='\0'){
         lengths2++;
      }else{
         notnull2++;
      } 
   
   }
   for(char k= 0; k<lengths1;k++){
      s3[k]=s1[k];
   }

   for(char h = 0; h<lengths2;h++){
      s3[lengths1+h]=s2[h];
   }

   s3[lengths1+lengths2]='\0';
   

   return s3;
}