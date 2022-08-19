#ifndef state
#define state


#include <stdio.h>
#include <string.h>

struct state_t
{ 
    int pc;
    int* reg;
    int epoch;

};


int state_pc (struct state_t mys)
{
    return mys.pc;
}

void set_state_pc (struct state_t mys, int pc)
{
    mys.pc = pc;
}

int state_reg (struct state_t mys, int i)
{
    return mys.reg[i];
}

void set_state_reg (struct state_t mys, int i, int v)
{
    mys.reg[i] = v;
}

int state_epoch (struct state_t mys)
{
    return mys.epoch;
}

void set_state_epoch (struct state_t mys, int v)
{
    mys.epoch = v;
}

#endif