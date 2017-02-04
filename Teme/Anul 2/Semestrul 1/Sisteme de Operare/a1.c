#include <stdio.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>

int destination;

void ParseArgument(const char arg[])
{
	ssize_t nread;
	int argLength = strlen(arg);
	char file[argLength + 1];
	char buffer[4096];
	
	int i = 0;
	for (i = 0; i < argLength && arg[i] != '+'; ++i);
	strncpy(file, arg, i);
	file[i] = 0;
	int toAppend = open(file, O_RDONLY);
	errno = 0;
	
	if (toAppend == -1)
	{
		printf("Could not open %s: %s\n", file, strerror(errno));
		exit(1);
	}
	
	nread = read(toAppend, buffer, sizeof(buffer));
	while(nread > 0)
	{
		write(destination, buffer, nread - 1);
		nread = read(toAppend, buffer, sizeof(buffer));
	}
	
	if (i != argLength)
		ParseArgument(arg + i + 1);
}

int main (int argc, char **argv)
{
	if (argc != 3)
	{
		printf("Argument number error\n");
		exit(1);
	}
	
	destination = open(argv[2], O_RDWR | O_CREAT | O_TRUNC, 0666);
	if (destination == -1)
	{
		printf("Could not open %s: %s\n", argv[2], strerror(errno));
		exit(1);
	}
	ParseArgument(argv[1]);
}