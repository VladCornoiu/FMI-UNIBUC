#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>

int main(char argc, char **argv)
{
	int i = 0;
	if (argc < 2)
	{
		printf("Argument number Error\n");
		exit(1);
	}
	if (!strcmp(argv[1], "0"))
	{
		printf("0 is not a prime number\n");
		return 0;
	}
	int number = atoi(argv[1]);
	if (number == 0)
	{
		printf("Argument error\n");
		exit(2);
	}
	if (number == 1)
	{
		printf("%d" is not a prime number\n", number);
		return 0;
	}
	for (i = 2 i <= sqrt(number); ++i)
	{
		if (number % i == 0)
		{
			printf("%d" is not a prime number\n", number);
			return 0;
		}
	}
	printf("%d is a prime number\n", number);
	return 0;
}