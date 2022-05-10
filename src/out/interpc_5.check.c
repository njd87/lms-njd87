/*****************************************
Emitting C Generated Code
*******************************************/
#include <stdlib.h>
#include "state.h"
#include <stdio.h>
#include <stdint.h>
#include <stdbool.h>
/**************** Snippet ****************/
interpctest.type#lms.koika.interpctest$statet Snippet(interpctest.type#lms.koika.interpctest$statet x0) {
  set_state_pc(x0, 0);
  int x1 = state_pc(x0);
  if (x1 == 0) {
    set_state_pc(x0, 1);
    set_state_reg(x0, 0, state_reg(x0, 0) + state_reg(x0, 0));
  }
  if (x1 == 1) {
    set_state_pc(x0, 2);
    if (state_reg(x0, 0) == 0) set_state_pc(x0, 0);
  }
  int x2 = state_pc(x0);
  if (x2 == 0) {
    set_state_pc(x0, 1);
    set_state_reg(x0, 0, state_reg(x0, 0) + state_reg(x0, 0));
  }
  if (x2 == 1) {
    set_state_pc(x0, 2);
    if (state_reg(x0, 0) == 0) set_state_pc(x0, 0);
  }
  int x3 = state_pc(x0);
  if (x3 == 0) {
    set_state_pc(x0, 1);
    set_state_reg(x0, 0, state_reg(x0, 0) + state_reg(x0, 0));
  }
  if (x3 == 1) {
    set_state_pc(x0, 2);
    if (state_reg(x0, 0) == 0) set_state_pc(x0, 0);
  }
  int x4 = state_pc(x0);
  if (x4 == 0) {
    set_state_pc(x0, 1);
    set_state_reg(x0, 0, state_reg(x0, 0) + state_reg(x0, 0));
  }
  if (x4 == 1) {
    set_state_pc(x0, 2);
    if (state_reg(x0, 0) == 0) set_state_pc(x0, 0);
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
  Snippet();
  return 0;
}
