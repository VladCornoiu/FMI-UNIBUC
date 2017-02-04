#include <stdio.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdint.h>

int main (int argc, char ** argv)
{
	if (argc != 3)
	{
		printf("Parameters error");
		exit(1);
	}
	
	FILE *fFile;
	fFile = fopen(argv[1], "r");
	
	FILE *sFile;
	sFile = fopen(argv[2], "r");
	
	int firstFile = open(argv[1], O_RDONLY);
	int secondFile = open(argv[2], O_RDONLY);
	
	if (firstFile == -1)
	{
		printf("First file cannot be opened");
		exit(2);
	}
	if (secondFile == -1)
	{
		printf("Second File cannot be opened");
		exit(3);
	}
	
	fseek(fFile, 0, SEEK_END);
	int lent1 = ftell(fFile);
	fseek(fFile, 0, SEEK_SET);
	len1--;
	
	fseek(sFile, 0, SEEK_END);
	int len2 = ftell(sFile);
	fseek(sFile, 0, SEEK_SET);
	len2--;
	
	int i = 0;
	char c1, c2;
	while(i < len1 && i < len2)
	{
		fread(&c1, sizeof(char), 1, fFile);
		fread(&c2, sizeof(char), 1, sFile);
		
		if (c1 > c2)
		{
			printf("1 > 2\n");
			return 0;
		}
		else if (c1 < c2)
		{
			printf("1 < 2\n");
			return 0;
		}
	}
	
	if (len1 < len2)
	{
		printf("1 < 2\n");
		return 0;
	}
	else if (len1 > len2)
	{
		printf("1 > 2\n");
		return 0;
	}
	else
	{
		printf("1 = 2\n");
		return 0;
	}
	
}