#include <stdio.h>
#include <string.h>
#include <dirent.h>
#include <unistd.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/stat.h>

void RecursiveDelete(const char path[])
{
	DIR* crtDir = opendir(path);
	struct dirent* ent;
	size_t pathSize = strlen(path);
	
	if (crtDir == NULL)
	{
		printf("Error opening directory %s: %s\n", path, strerror(errno));
		exit(2);
	}
	
	while(1)
	{
		errno = 0;
		ent = readdir(crtDir);
		if(ent == NULL)
		{
			if (errno != 0)
			{
				printf("Error reading from directory %s: %s\n", path, strerror(errno));
				exit(2);
			}
			else
				break;
		}
		
		if (!strcmp(ent->d_name, ".") || !strcmp(ent->d_name, ".."))
			continue;
		
		size_t dirSize = strlen(ent->d_name);
		char newPath[pathSize + dirSize + 2];
		
		strncpy(newPath, path, pathSize);
		strncpy(newPath + pathSize, ent->d_name, dirSize);
		
		if (ent->d_type == DT_DIR)
		{
			newPath[pathSize + dirSize] = '/';
			newPath[pathSize + dirSize + 1] = 0;
			
			RecursiveDelete(newPath);
		}
		else if (ent->d_type == DT_REG)
		{
			newPath[pathSize + dirSize] = 0;
			int len = strlen(ent->d_name);
			if ((len >= 4) && strcmp(&(ent->d_name[len - 4]), ".out") == 0)
				remove(newPath);
		}
	}
}

int main(int argc, char** argv)
{
	if (argc != 2)
	{
		printf("Argument number error\n");
		exit(1);
	}
	
	size_t argSize = strlen(argv[1]);
	
	char argDir[argSize + 2];
	strncpy(argDir, argv[1], argSize);
	
	if (argDir[argSize - 1] != '/')
	{
		argDir[argSize] = '/';
		++argSize;
	}
	
	argDir[argSize] = 0;
	
	RecursiveDelete(argDir);
	
	return 0;
}