#ifndef state
#define state


#include <stdio.h>
#include <string.h>

typedef struct state_t
{ 
    int pc;
    int* reg;
    int epoch;
    int f2d_valid;
    int f2d_pc;
    int f2d_ppc;
    int f2d_epoch;
    int btb;

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

int state_f2d_valid (struct state_t mys)
{
    return mys.f2d_valid;
}

void set_state_f2d_valid (struct state_t mys, int v)
{
    mys.f2d_valid = v;
}

int state_f2d_pc (struct state_t mys)
{
    return mys.f2d_pc;
}

void set_state_f2d_pc (struct state_t mys, int v)
{
    mys.f2d_pc = v;
}

int state_f2d_ppc (struct state_t mys)
{
    return mys.f2d_ppc;
}

void set_state_f2d_ppc (struct state_t mys, int v)
{
    mys.f2d_ppc = v;
}

int state_f2d_epoch (struct state_t mys)
{
    return mys.f2d_epoch;
}

void set_state_f2d_epoch (struct state_t mys, int v)
{
    mys.f2d_epoch = v;
}

int state_btb (struct state_t mys)
{
    return mys.btb;
}

void set_state_btb (struct state_t mys, int v)
{
    mys.btb = v;
}

#endif