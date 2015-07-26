#ifndef TOOLS_H_INCLUDED
#define TOOLS_H_INCLUDED

void split_slash(char *chaine, char *tab[100]);
char *stringFromCommande (char *atts[100], int nbAtts);
char *stringFromCommandeChoixNom(char *nomCom, char *atts[100], int nbAtts);
int* compteNbLigne(char *texte, int *nb);
int contientMot (const char *phrase, const char *mot);
#endif // TOOLS_H_INCLUDED
