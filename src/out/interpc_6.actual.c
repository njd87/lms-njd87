/*****************************************
Emitting C Generated Code
*******************************************/
#include <stdlib.h>
#include "state.h"
#include <stdio.h>
#include <stdint.h>
#include <stdbool.h>
/**************** Snippet ****************/
bool Snippet(bool* x0) {
  bool x1 = x0[0];
  bool x2 = false;
  x0[1] = true;
  if (x1) {} else x2 = true;
  if (x2) x0[2] = true;
  else x0[2] = false;
  return x0[2];
}
/*****************************************
End of C Generated Code
*******************************************/
int main(int argc, char *argv[]) {
  if (argc != 2) {
    printf("usage: %s <arg>\n", argv[0]);
    return 0;
  }
  Snippet();
  return 0;
}
