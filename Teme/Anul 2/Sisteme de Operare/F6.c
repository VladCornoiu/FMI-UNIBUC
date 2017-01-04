#include <stdlib.h>
#include <stdio.h>

int readInt(int file, int *number)
{
	ssize_t binaryFileContent;
	char character;
	int sign;
	bool ok = false;
	*number = 0;
	
	while(true)
	{
		binaryFileContent = read(file, &character, sizeof(char));
		
		if (binaryFileContent == -1)
		{
			printf("Error while reading from file: %s\n", strerror(errno));
			exit(1);
		}
		
		if (binaryFileContent == 0) 
		{
			break;
		}
		
		if (character == '-')
		{
			sign = -1;
			continue;
		}
		
		if (isDigit(character))
		{
			ok = true;
			(*number) *= 10;
			(*number) += (character - '0');
			continue;
		}
		
		break;
	}
	
	(*number) *= sign;
	return ok;
}

int main (int argc, char** argv)
{
	if (argc != 3)
	{
		printf("Parameters error");
		exit(1);
	}
	
	int textFile = open(argv[1], O_RDONLY);
	int binaryFile = open(argv[2], O_RDWR | O_CREAT, S_IWRITE | S_IREAD);
		
	if (textFile == -1)
	{
		printf("Text file cannot be opened");
		exit(1);
	}
	else if (binaryFile == -1)
	{
		printf("Binary File cannot be opened");
		exit(1);
	}

	int number;
	while(readInt(textFile, &number) > 0) 
	{
		int binaryContent = write(binaryFile, &number, sizeof(nr));
		if (binaryContent == -1)
		{
			printf("Error while writing in file: %s\n", strerror(errno));
			exit(1);
		}
	}
	
	close(textFile);
	close(binaryFile);
}