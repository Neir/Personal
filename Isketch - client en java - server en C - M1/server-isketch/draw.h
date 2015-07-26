#ifndef DRAW_H_INCLUDED
#define DRAW_H_INCLUDED

typedef struct
{
    int red;
    int green;
    int blue;
} Couleur;

typedef struct
{
    int x;
    int y;
} Point;

/*
typedef struct
{
    int epaisseur;
    struct Couleur *couleur;
    struct Point *p1;
    struct Point *p2;
} Ligne;
*/

typedef struct
{
    char *epaisseur;
    char *x1, *y1, *x2, *y2;
    char *r, *g, *b;
} LigneStr;

// Eventuellement...
typedef struct
{
    int epaisseur;
    struct Couleur *couleur;
    struct Point *centre;
    int rayon;
} Cercle;

LigneStr *init_ligneStr();
void free_ligneStr(LigneStr *l);

#endif // DRAW_H_INCLUDED
