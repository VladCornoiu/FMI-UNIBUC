#include <stdio.h>
#include <string.h>

int main ()
{
	char cwd[256], ncwd[256], t[256], var[256];
	getcwd(cwd, sizeof(cwd));
	printf("Valoarea returnata de getcwd este: %s\n", cwd);
	printf("PWD=%s\n", (char *)getenv("PWD"));
	
	printf("Valoarea cu care se inlocuieste PWD: ");
	fgets(t, 255, stdin);
	strcpy(var, "PWD=");
	strcpy(var, t);
	putenv(var);
	
	printf("PWD=%s\n", (char *)getenv("PWD"));
	getcwd(ncwd, sizeof(ncwd));
	printf("Valoarea returnata de getcwd dupa modificarea PWD este: %s\n", ncwd);
	
	if (!strcmp(ncwd, cwd)) 
		printf("Valoarea returnata de getcwd nu se modifica \n");
	else 
		printf("Valoarea returnata de getcwd se modifica\n");
	
	return 0;
	
}