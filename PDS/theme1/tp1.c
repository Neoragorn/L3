#include "mfile.h"

#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>


char Texte1[] = "Le texte" ;
char Texte2[] = " () en question" ;
char Texte3[4096] ;

/* C'est l'equivalent de FILE */
struct FileDataStructure
{
 int fd ; 
 char tab[2096] ;
 int current ;

} ;

typedef struct FileDataStructure MFILE ; 

MFILE *mfopen(char *filename)
{
 MFILE *res  ;

res = (MFILE *) malloc(sizeof(MFILE)); 
res->fd =open(filename,O_RDWR|O_CREAT,S_IRWXU) ;
res->current = 0 ;
if(res->fd==-1)
	 exit(1) ;

 return res ;
}

void mfwrite(MFILE *mfile, const char *text)
{
 int i ;

 for(i=0;text[i];i++)
 {
	 mfile->tab[mfile->current++]=text[i] ;
	 if (mfile->current==2095)
		 {
		 write(mfile->fd,mfile->tab,mfile->current) ;
		 mfile->current=0 ;
		 }
	 }
}

void mfclose(MFILE *mfile) 
{
 if(mfile->current!=0)
	 write(mfile->fd,mfile->tab,mfile->current) ;
 close(mfile->fd) ;
}

int main(void)
{
 MFILE *file ;
 int i ;

 for(i=0;i<4096;i++)
  Texte3[i]='1' ;

 file=mfopen("nombre");
 mfwrite(file,Texte1) ;
 mfwrite(file,Texte2) ;
 mfwrite(file,Texte3) ;
 mfclose(file) ; 

 return 0 ;
}