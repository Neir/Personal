#ifndef CLIENT_H
#define CLIENT_H

#include "server.h"

typedef struct
{
   SOCKET sock;
   char name[BUF_SIZE];
}Client;

typedef struct{
    struct Client *listClient;
    int indCliCourant;
    char *buffer;
    int *actual;
}ArgThread;

#endif /* guard */
