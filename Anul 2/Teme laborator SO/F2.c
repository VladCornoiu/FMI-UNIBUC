#include <stdio.h>
#include <math.h>

int main(int argc, int **argv)
{
	int i = 0;
	if (argc < 2) printf("Argument Error");
	else
	{
		int number = atoi(argv[1]);
		if (number <= 1)
		{
			printf("%d is not a prime number\n", number);
			return 0;
		}
		for (i = 2; i <= sqrt(number); ++i)
		{
			if (number % i == 0)
			{
				printf("%d is not a prime number\n", number);
				return 0;
			}
		}
		printf("%d is a prime number\n", number);
		return 0;
	}
}

F(6), F(11), F(2), A(1), A(17), D(6)