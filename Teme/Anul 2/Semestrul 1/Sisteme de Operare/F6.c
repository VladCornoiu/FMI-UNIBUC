#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <stdbool.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <errno.h>
#include <ctype.h>

int main (int argc, char** argv)
{
	if (argc != 3)
	{
		printf("Parameters error\n");
		exit(1);
	}
	
	FILE *txtFile;
	txtFile = fopen(argv[1], "r");
	
	FILE *binFile;
	binFile = fopen(argv[2], "wb");
	
	if (txtFile == NULL)
	{
		fprintf(stderr, "Can't open text File\n");
		exit(1);
	}
	
	if (binFile == NULL)
	{
		fprintf(stderr, "Can't open binary File\n");
		exit(2);
	}
	
	fseek(txtFile, 0, SEEK_END);
	int length = ftell(txtFile);
	fseek(txtFile, 0, SEEK_SET);
	length--;
	int count = 0;
	
	while(ftell(txtFile) != length)
	{
		int nr;
		fscanf(txtFile, "%d", &nr);
		count++;
		fwrite(&nr, sizeof(int), 1, binFile);
	}
	
	fseek(binFile, 0, SEEK_SET);
	FILE* verFile;
	verFile = fopen("verifier.txt", "w+");
	
	int i = 0;
	int buffer[4096];
	for (i = 0; i < count; ++i)
	{
		fread(&buffer[i], sizeof(int), 1, binFile);
		fwrite(&buffer[i], sizeof(int), 1, verFile);
	}
	
	fclose(txtFile);
	fclose(binFile);
	fclose(verFile);
}