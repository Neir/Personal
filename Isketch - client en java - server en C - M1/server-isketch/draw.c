#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

#include "server.h"
#include "client.h"
#include "draw.h"

// Obsolète, aucune utilité d'utiliser autres types que char*.
/*
Ligne *init_ligne(){

    Couleur *couleur = (Couleur *) malloc (sizeof(Couleur));
    couleur->red = 0;
    couleur->green = 0;
    couleur->blue = 0;

    Point *p1 = (Point *) malloc (sizeof(Point));
    p1->x = 0;
    p1->y = 0;
    Point *p2 = (Point *) malloc (sizeof(Point));
    p2->x = 0;
    p2->y = 0;

    int epaisseur = 10;

    Ligne *ligne = (Ligne *) malloc(sizeof(Ligne));
    ligne->couleur = couleur;
    ligne->p1 = p1;
    ligne->p2 = p2;
    ligne->epaisseur = epaisseur;

    return ligne;
}
*/

LigneStr *init_ligneStr(){
    LigneStr *ligne = (LigneStr *) malloc(sizeof(LigneStr));

    ligne->epaisseur = (char *)malloc(3*sizeof(char));

    ligne->x1 = (char *)malloc(3*sizeof(char));
    ligne->y1 = (char *)malloc(3*sizeof(char));
    ligne->x2 = (char *)malloc(3*sizeof(char));
    ligne->y2 = (char *)malloc(3*sizeof(char));

    ligne->r = (char *)malloc(3*sizeof(char));
    ligne->g = (char *)malloc(3*sizeof(char));
    ligne->b = (char *)malloc(3*sizeof(char));

    ligne->epaisseur = "10";
    ligne->x1 = "0";
    ligne->y1 = "0";
    ligne->x2 = "0";
    ligne->y2 = "0";
    ligne->r = "0";
    ligne->g = "0";
    ligne->b = "0";

    return ligne;
}

void free_ligneStr(LigneStr *l){
    free(l->epaisseur);
    free(l->x1);
    free(l->y1);
    free(l->x2);
    free(l->y2);
    free(l->r);
    free(l->g);
    free(l->b);
    free(l);
}
