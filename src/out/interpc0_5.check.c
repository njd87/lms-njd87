/*****************************************
Emitting C Generated Code
*******************************************/
#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>
#include <stdbool.h>
/**************** Snippet ****************/
int* Snippet(int* x0) {
  x0[0] = 1;
  x0[1] = x0[1] + x0[1];
  int x1 = x0[0];
  if (x1 == 0) {
    x0[0] = 1;
    x0[1] = x0[1] + x0[1];
  }
  if (x1 == 1) {
    x0[0] = 2;
    if (x0[1] == 0) x0[0] = 0;
  }
  int x2 = x0[0];
  if (x2 == 0) {
    x0[0] = 1;
    x0[1] = x0[1] + x0[1];
  }
  if (x2 == 1) {
    x0[0] = 2;
    if (x0[1] == 0) x0[0] = 0;
  }
  int x3 = x0[0];
  if (x3 == 0) {
    x0[0] = 1;
    x0[1] = x0[1] + x0[1];
  }
  if (x3 == 1) {
    x0[0] = 2;
    if (x0[1] == 0) x0[0] = 0;
  }
  return x0;
}
/*****************************************
End of C Generated Code
*******************************************/
int main(int argc, char *argv[]) {
  if (argc != 2) {
    printf("usage: %s <arg>\n", argv[0]);
    return 0;
  }

  int n[2] ;
  n[0] = 0 ;
  n[1] = 0 ;
  int* result = Snippet(n);

  for (int i = 0; i < 2; i++)
  {
    printf("%i", result[0]);
    printf("\n");
  }

  return 0;
}