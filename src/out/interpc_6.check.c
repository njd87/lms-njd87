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
  if (state_f2d_valid(x0) == 0) {
    set_state_f2d_valid(x0, 1);
    set_state_f2d_epoch(x0, state_epoch(x0));
    int x1 = state_btb(x0);
    set_state_f2d_pc(x0, state_pc(x0));
    set_state_f2d_ppc(x0, x1);
    set_state_pc(x0, x1);
  }
  if (state_f2d_valid(x0) == 1) if (state_f2d_epoch(x0) == state_epoch(x0)) {
    int x2 = state_f2d_pc(x0);
    int x3 = state_f2d_ppc(x0);
    set_state_f2d_valid(x0, 0);
    if (x2 == 0) {
      set_state_reg(x0, 0, state_reg(x0, 0) + state_reg(x0, 0));
      if (x3 != 1) {
        set_state_pc(x0, 1);
        set_state_epoch(x0, ~(state_epoch(x0)));
      }
    }
    if (x2 == 1) {
      set_state_btb(x0, state_reg(x0, 0), 1);
      if (state_reg(x0, 0) == 0) if (x3 != 0) {
        set_state_pc(x0, 0);
        set_state_epoch(x0, ~(state_epoch(x0)));
      }
      else if (x3 != 2) {
        set_state_pc(x0, 2);
        set_state_epoch(x0, ~(state_epoch(x0)));
      }
    }
  } else set_state_f2d_valid(x0, 0);
  if (state_f2d_valid(x0) == 0) {
    set_state_f2d_valid(x0, 1);
    set_state_f2d_epoch(x0, state_epoch(x0));
    int x4 = state_btb(x0);
    set_state_f2d_pc(x0, state_pc(x0));
    set_state_f2d_ppc(x0, x4);
    set_state_pc(x0, x4);
  }
  if (state_f2d_valid(x0) == 1) if (state_f2d_epoch(x0) == state_epoch(x0)) {
    int x5 = state_f2d_pc(x0);
    int x6 = state_f2d_ppc(x0);
    set_state_f2d_valid(x0, 0);
    if (x5 == 0) {
      set_state_reg(x0, 0, state_reg(x0, 0) + state_reg(x0, 0));
      if (x6 != 1) {
        set_state_pc(x0, 1);
        set_state_epoch(x0, ~(state_epoch(x0)));
      }
    }
    if (x5 == 1) {
      set_state_btb(x0, state_reg(x0, 0), 1);
      if (state_reg(x0, 0) == 0) if (x6 != 0) {
        set_state_pc(x0, 0);
        set_state_epoch(x0, ~(state_epoch(x0)));
      }
      else if (x6 != 2) {
        set_state_pc(x0, 2);
        set_state_epoch(x0, ~(state_epoch(x0)));
      }
    }
  } else set_state_f2d_valid(x0, 0);
  if (state_f2d_valid(x0) == 0) {
    set_state_f2d_valid(x0, 1);
    set_state_f2d_epoch(x0, state_epoch(x0));
    int x7 = state_btb(x0);
    set_state_f2d_pc(x0, state_pc(x0));
    set_state_f2d_ppc(x0, x7);
    set_state_pc(x0, x7);
  }
  if (state_f2d_valid(x0) == 1) if (state_f2d_epoch(x0) == state_epoch(x0)) {
    int x8 = state_f2d_pc(x0);
    int x9 = state_f2d_ppc(x0);
    set_state_f2d_valid(x0, 0);
    if (x8 == 0) {
      set_state_reg(x0, 0, state_reg(x0, 0) + state_reg(x0, 0));
      if (x9 != 1) {
        set_state_pc(x0, 1);
        set_state_epoch(x0, ~(state_epoch(x0)));
      }
    }
    if (x8 == 1) {
      set_state_btb(x0, state_reg(x0, 0), 1);
      if (state_reg(x0, 0) == 0) if (x9 != 0) {
        set_state_pc(x0, 0);
        set_state_epoch(x0, ~(state_epoch(x0)));
      }
      else if (x9 != 2) {
        set_state_pc(x0, 2);
        set_state_epoch(x0, ~(state_epoch(x0)));
      }
    }
  } else set_state_f2d_valid(x0, 0);
  if (state_f2d_valid(x0) == 0) {
    set_state_f2d_valid(x0, 1);
    set_state_f2d_epoch(x0, state_epoch(x0));
    int x10 = state_btb(x0);
    set_state_f2d_pc(x0, state_pc(x0));
    set_state_f2d_ppc(x0, x10);
    set_state_pc(x0, x10);
  }
  if (state_f2d_valid(x0) == 1) if (state_f2d_epoch(x0) == state_epoch(x0)) {
    int x11 = state_f2d_pc(x0);
    int x12 = state_f2d_ppc(x0);
    set_state_f2d_valid(x0, 0);
    if (x11 == 0) {
      set_state_reg(x0, 0, state_reg(x0, 0) + state_reg(x0, 0));
      if (x12 != 1) {
        set_state_pc(x0, 1);
        set_state_epoch(x0, ~(state_epoch(x0)));
      }
    }
    if (x11 == 1) {
      set_state_btb(x0, state_reg(x0, 0), 1);
      if (state_reg(x0, 0) == 0) if (x12 != 0) {
        set_state_pc(x0, 0);
        set_state_epoch(x0, ~(state_epoch(x0)));
      }
      else if (x12 != 2) {
        set_state_pc(x0, 2);
        set_state_epoch(x0, ~(state_epoch(x0)));
      }
    }
  } else set_state_f2d_valid(x0, 0);
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
