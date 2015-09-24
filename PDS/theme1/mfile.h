#define BUFFSIZE 1024

#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

typedef struct s_mFile
{
	FILE *fd;
	char buff[BUFFSIZE];
	int pos;
}t_mFile;