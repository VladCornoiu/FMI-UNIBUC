#include <stdio.h>
#include <string.h>
#include <dirent.h>

int listdir (const char *path, int depth)
{
	struct dirent *entry;
	DIR *dp;
	
}

int main (int argc, char **argv)
{
	if (argc == 1)
		listdir(".", 0);
	else
		listdir(argv[1], 0);
}