#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

#include <pthread.h>
#include <fthread.h>

#include "server.h"
#include "client.h"
#include "tools.h"
#include "draw.h"

void gestionCommande(char *commande, Client *clients, Client *sender, int actual, char from_server);
void lecture(void *arg);

static void init(void)
{
#ifdef WIN32
   WSADATA wsa;
   int err = WSAStartup(MAKEWORD(2, 2), &wsa);
   if(err < 0)
   {
      puts("WSAStartup failed !");
      exit(EXIT_FAILURE);
   }
#endif
}

static void end(void)
{
#ifdef WIN32
   WSACleanup();
#endif
}

static void app(void)
{
   SOCKET sock = init_connection();
   char buffer[BUF_SIZE];
   /* the index for the array */
   int actual = 0;
   int max = sock;
   /* an array for all clients */
   Client clients[MAX_CLIENTS];

   fd_set rdfs;

   int tid[2];
//   while(1)
//   {
      int i = 0;
      FD_ZERO(&rdfs);

      /* add the connection socket */
      FD_SET(sock, &rdfs);

      /* add socket of each client */
      for(i = 0; i < actual; i++)
      {
         FD_SET(clients[i].sock, &rdfs);
         printf("for add socket of each client (10)\n");
      }

      #ifdef defined (linux)
      /* add STDIN_FILENO */

      FD_SET(STDIN_FILENO, &rdfs);

      if(select(max + 1, &rdfs, NULL, NULL, NULL) == -1)
      {
         perror("select()");
         exit(errno);
      }

      /* something from standard input : i.e keyboard */
      if(FD_ISSET(STDIN_FILENO, &rdfs))
      {
         /* stop process when type on keyboard */
         break;
      }
      else
      #endif
        printf("voici le buffer : %s \n\n", buffer);

//        if(FD_ISSET(sock, &rdfs))
//      {
         /* On attend que tous les clients soient connectés */
         while(actual < MAX_CLIENTS){
         SOCKADDR_IN csin = { 0 };
         size_t sinsize = sizeof csin;
printf("passage 24\n");
         int csock = accept(sock, (SOCKADDR *)&csin, &sinsize);
printf("passage 26\n");
         if(csock == SOCKET_ERROR)
         {
            perror("accept()");
            continue;
         }
printf("passage 28\n");
         /* after connecting the client sends its name */
//         if(read_client(csock, buffer) == -1)
//         { printf("erreur du reader (20)\n");
//            /* disconnected */
//            continue;
//         }

         /* what is the new maximum fd ? */
         max = csock > max ? csock : max;

         FD_SET(csock, &rdfs);
printf("passage (30)\n");
         Client c = { csock };
         //strncpy(c.name, buffer, BUF_SIZE - 1);
         clients[actual] = c;
         actual++;
         }
         printf("Tous les clients sont connectés (35)\n");
//      }
//      else
//      {
//         int i = 0;
//ft_scheduler_t sched = ft_scheduler_create();

         for(i = 0; i < actual; i++)
         { printf("dans le dernier for all client (50)\n");
            /* a client is talking */
            if(FD_ISSET(clients[i].sock, &rdfs))
            {
               Client client = clients[i];
    ArgThread *arg = (ArgThread *) malloc(sizeof(ArgThread));
    arg->buffer = buffer;
    arg->indCliCourant = i;
    arg->listClient = clients;
    arg->actual = &actual;
	pthread_create(&tid[i], 0, lecture, arg);
	/*
               int c = read_client(clients[i].sock, buffer);
printf("dans if(FD_ISSET(clients[i].sock, &rdfs)) (53)\n");
               // client disconnected
               if(c == 0)
               {printf("client deconnecte (55)\n");
                  closesocket(clients[i].sock);
                  remove_client(clients, i, &actual);
                  strncpy(buffer, client.name, BUF_SIZE - 1);
                  strncat(buffer, " disconnected !", BUF_SIZE - strlen(buffer) - 1);
                  send_message_to_all_clients(clients, client, actual, buffer, 1);
               }
               else
               {printf("client connecté (56)\n");
                  //send_message_to_all_clients(clients, client, actual, buffer, 0);
                  gestionCommande(buffer, clients, client, actual, 0);
               }

               break;
            }*/
         }
         //ft_scheduler_start(sched);
//        ft_exit();
}
//      }
//   }
while(1);
   printf("fin de app (60)\n");
   clear_clients(clients, actual);
   end_connection(sock);
}

void lecture(void *arg){
    ArgThread *a = (ArgThread*) arg;
	int i = a->indCliCourant;
	Client *clients = a->listClient;
	char *buffer = a->buffer;
	int *actual = a->actual;

while(1){
    int c = read_client(clients[i].sock, buffer);
    printf("dans lecture ! ! (53)\n");
    /* client disconnected */
    if(c == 0)
    {printf("client deconnecte (55)\n");
        closesocket(clients[i].sock);
        remove_client(clients, i, &actual);
        strncpy(buffer, clients[i].name, BUF_SIZE - 1);
        strncat(buffer, " disconnected !", BUF_SIZE - strlen(buffer) - 1);
        send_message_to_all_clients(clients, clients[i], *actual, buffer, 1);
    }
    else
    {printf("client connecté (56)\n");
        //send_message_to_all_clients(clients, client, actual, buffer, 0);
        //while(strcmp(*buffer,"")!=0&&strcmp(*buffer,"\n")!=0)

        printf("voici le buffer : %s \n\n", buffer);
        int j;
        int nbCom = 0;
        int *indDebLigne = compteNbLigne(buffer, &nbCom);
        printf("Nombre de ligne du buffer : %d\n", nbCom);
        for(j=0;j<nbCom; j++)
            gestionCommande(&(buffer[indDebLigne[j]]), clients, &(clients[i]), *actual, 0);
    }
}
    //ft_thread_cooperate();
}

static void clear_clients(Client *clients, int actual)
{
   int i = 0;
   for(i = 0; i < actual; i++)
   {
      closesocket(clients[i].sock);
   }
}

static void remove_client(Client *clients, int to_remove, int *actual)
{
   /* we remove the client in the array */
   memmove(clients + to_remove, clients + to_remove + 1, (*actual - to_remove - 1) * sizeof(Client));
   /* number client - 1 */
   (*actual)--;
}

static void send_message_to_all_clients(Client *clients, Client sender, int actual, const char *buffer, char from_server)
{
   int i = 0;
   char message[BUF_SIZE];
   message[0] = 0;
   for(i = 0; i < actual; i++)
   {printf("envoie du message au client numero %d / %d\n", i, actual);
      /* we don't send message to the sender */
      if(sender.sock != clients[i].sock)
      {
         if(from_server == 0)
         {
            strncpy(message, sender.name, BUF_SIZE - 1);
            strncat(message, " : ", sizeof message - strlen(message) - 1);
         }
         strncat(message, buffer, sizeof message - strlen(message) - 1); printf("message : %s\n", message);

         write_client(clients[i].sock, message);
      }
   }
}

static int init_connection(void)
{
   SOCKET sock = socket(AF_INET, SOCK_STREAM, 0);
   SOCKADDR_IN sin = { 0 };

   if(sock == INVALID_SOCKET)
   {
      perror("socket()");
      exit(errno);
   }
//   sin.sin_addr.s_addr = htonl(inet_addr("127.0.0.1"));
   sin.sin_addr.s_addr = htonl(INADDR_ANY);
   sin.sin_port = htons(PORT);
   sin.sin_family = AF_INET;

   if(bind(sock,(SOCKADDR *) &sin, sizeof sin) == SOCKET_ERROR)
   {
      perror("bind()");
      exit(errno);
   }

   if(listen(sock, MAX_CLIENTS) == SOCKET_ERROR)
   {
      perror("listen()");
      exit(errno);
   }

   return sock;
}

static void end_connection(int sock)
{
   closesocket(sock);
}

static int read_client(SOCKET sock, char *buffer)
{
   int n = 0;

   if((n = recv(sock, buffer, BUF_SIZE - 1, 0)) < 0)
   {
      perror("recv()");
      /* if recv error we disonnect the client */
      n = 0;
   }
printf("Commande(s) recue(s) : %s\n", buffer);
   buffer[n] = 0;

   return n;
}

static void write_client(SOCKET sock, const char *buffer)
{
   if(send(sock, buffer, strlen(buffer), 0) < 0)
   {
      perror("send()");
      exit(errno);
   }
}

LigneStr *ligne = NULL;

void gestionCommande(char *ligneCommande, Client *clients, Client *sender, int actual, char from_server){
    char *tab[100];// = (char*)calloc(200,sizeof(char));
    split_slash(ligneCommande, tab);
    char *nomCom = tab[0];
    char *commande;
    printf("Le nom de ma commande : %s \n\n", nomCom);

//    LigneStr *ligne = NULL;// = init_ligneStr();
    // Ajouter ligne dans les paramètre de la fonction
    // et Créer une ligne en variable globale.


    if(strcmp(nomCom, "EXIT") == 0){
       // voir lignes 124 et 125

       // Rajouter fermeture de la socket et du client !
       char *atts[1] = { tab[1] };
       commande = stringFromCommandeChoixNom("EXITED", atts , 1);
       printf("Envoie de commande : %s\n", commande);
       send_message_to_all_clients(clients, *sender, actual, commande, 1);
    } else
    if(strcmp(nomCom, "CONNECT") == 0){
       // voir lignes 83 - 93 -> 108
       strcpy(sender->name, tab[1]);
       char *atts[1] = { tab[1] };printf("atts = %s\n", sender->name);
       commande = stringFromCommandeChoixNom("CONNECTED", atts , 1);
       printf("Envoie de commande : %s\n", commande);
       send_message_to_all_clients(clients, *sender, actual, commande, 1);
    } else
    if(strcmp(nomCom, "GUESS") == 0){
       char *atts[2] = {tab[1], sender->name}; // sender.name
       commande = stringFromCommandeChoixNom("GUESSED", atts , 2);
       printf("Envoie de commande : %s\n", commande);
       send_message_to_all_clients(clients, *sender, actual, commande, 1);
    } else
    if(strcmp(nomCom, "SET_COLOR") == 0){
       ligne = init_ligneStr();
       ligne->r = tab[1];
       ligne->g = tab[2];
       ligne->b = tab[3];
       //gestionCommande(ligneCommande, clients, sender, actual, from_server);
    } else
    if(strcmp(nomCom, "SET_SIZE") == 0){
       ligne->epaisseur = tab[1];
       //gestionCommande(ligneCommande, clients, sender, actual, from_server);
    } else
    if(strcmp(nomCom, "SET_LINE") == 0){
       ligne->x1 = tab[1];
       ligne->y1 = tab[2];
       ligne->x2 = tab[3];
       ligne->y2 = tab[4];
       char *atts[8] = {
           tab[1], tab[2], tab[3], tab[4], // coordonnées x1, y1, x2, y2
           ligne->r, ligne->g, ligne->b, // couleurs RGB
           ligne->epaisseur // epaisseur du trait
       };
       commande = stringFromCommandeChoixNom("LINE", atts , 8);
       printf("Envoie de commande : %s\n", commande);
       send_message_to_all_clients(clients, *sender, actual, commande, 1);
       free_ligneStr(ligne);
    } else {
       printf("Commande rentree non reconnue !\n\n");
    }
}

void new_round(){
    commande = stringFromCommandeChoixNom("NEW_ROUND", atts , 2);
       printf("Envoie de commande : %s\n", commande);
       // pour chaque client
       // attribuer un devineur à tous sauf 1
       //
       send_message_to_all_clients(clients, *sender, actual, commande, 1);
}

int main(int argc, char **argv)
{
   init();

   app();

   end();

   return EXIT_SUCCESS;
}

// Code basé sur le tutoriel des sockets sur developpez.com
