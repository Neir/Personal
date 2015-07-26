#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "tools.h"

void split_slash(char *chaine, char *tab[100])
{
    // i offset de toute la commande
    // j index de tab
    // k offset d'un mot de la commande
    int i, j = 0, k;
    int len = strlen(chaine);
    char *mot = (char*)calloc(200,sizeof(char)); //pour stocker un mot parsé

    for (i=0; i<len; i++)
    {
        if (chaine[i] == '\n') return;

        if (chaine[i] == '/' && chaine[i+1])
        {
            k = i+1;
            do{
                i++;
                if(chaine[i]!='/')
                    mot[i-k] = chaine[i];
            }while(chaine[i]!='/'&&i<len);

            mot[i-k] = '\0';
            tab[j] = mot;
            mot = (char*)calloc(200,sizeof(char));
            j++;
            i--;
        }
        if (j==100) return;
    }
}

char *stringFromCommande(char *atts[100], int nbString){
    char *ligneCom = (char *)calloc(300,sizeof(char));
    int offset = 0;
//    ligneCom[offset++] = '/';
//    strcpy(&ligneCom[offset], nomCom);
//    offset += strlen(nomCom);
    ligneCom[offset++] = '/';

    int i;
    for(i = 0 ; i < nbString+1 ; i++){
        if(offset>300){
            printf("Attributs trop grands !");
            return NULL;
        }
        strcpy(&ligneCom[offset], atts[i]);
        offset += strlen(atts[i]);
        ligneCom[offset++] = '/';
    }
    ligneCom[offset] = '\n';
    return ligneCom;
}

char *stringFromCommandeChoixNom(char *nomCom, char *atts[100], int nbAtts){
    char *ligneCom = (char *)calloc(300,sizeof(char));
    int offset = 0;
    ligneCom[offset++] = '/';
    strcpy(&ligneCom[offset], nomCom);
    offset += strlen(nomCom);
    ligneCom[offset++] = '/';

    int i;
    for(i = 0 ; i < nbAtts ; i++){
        if(offset>300){
            printf("Attributs trop grands !");
            return NULL;
        }
        strcpy(&ligneCom[offset], atts[i]);
        offset += strlen(atts[i]);
        ligneCom[offset++] = '/';
    }
    ligneCom[offset] = '\n';
    return ligneCom;
}

int* compteNbLigne(char *texte, int *nb){
    int i, j = 1;
    int *inds = (int*)malloc(10*sizeof(int));
    inds[0] = 0;
    int nbLigne = 0;printf("(10)\n");
    for(i=0;i<strlen(texte);i++){
        if(texte[i]=='\n'){printf("(15)\n");
            nbLigne++;
            if(i+1<strlen(texte)){printf("(16)\n");
                //inds[j] = (int*)malloc(sizeof(int));
                inds[j] = i+1; printf("(17) : %d\n", i+1);
                j++;
            }
        }
    }printf("(18)\n");
    *nb = nbLigne;
    return inds;
}

char *piocheMot(char *filename){
    char *mot = (char*)calloc(30, sizeof(char));
    unsigned int indexMot;
    srand(time(NULL));

    FILE *fic;
    unsigned int nb_ligne = 0;
    char c;

    if((fic = fopen(filename,"r"))== NULL){
        printf("Erreur dans l'ouverture du fichier %s\n", filename);
        return NULL;
    }
    while((c = fgetc(fic)) != EOF){
    if(c == '\n')
        nb_ligne++;
    }

    rewind(fic);

    indexMot = rand() % nb_ligne;
    int i = 0;
    while( i < indexMot && (c = fgetc(fic)) != EOF){
    if(c == '\n')
        i++;
    }
    fgets(mot, 30, fic);
    mot[strlen(mot)-1] = 0;

    fclose(fic);
    return mot;
}

int contientMot (const char *phrase, const char *mot)
{
    if (strstr (phrase, mot)==NULL){
        return 1;
    }else{
        return 0;
    }
}

