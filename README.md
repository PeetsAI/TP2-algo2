# TP3-Génération aléatoire d'arbres couvrants
ici votre rapport

**Membres du binôme :**
- Peter NICOLAS
- Prénom NOM (Numéro étudiant)

## 1. Description du projet
L'objectif de ce projet est d'implémenter et de comparer différents algorithmes permettant de générer des arbres couvrants aléatoires dans un graphe. Nous explorons des approches uniformes  et non-uniformes.

## 2. Utilisation
Le projet utilise un `Makefile` pour simplifier la compilation et l'exécution. Ouvrez un terminal à la racine du projet :

### Commandes principales
* **Compilation :**
    ```bash
    make compile
    ```
* **Exécution :**
    ```bash
    make exec
    ```
    *Lance le programme principal, affiche les statistiques dans la console et ouvre une visualisation graphique du labyrinthe généré.*
    
* **Création de l'archive de rendu :**
    ```bash
    make zip
    ```
    *Génère un fichier zip contenant les sources et ce rapport, prêt à être déposé.*


1.  **Changer d'algorithme :** Dans la méthode `genTree(Graph graph)`, décommentez l'algorithme souhaité (BFS, Kruskal, Aldous-Broder, DFS...).
2.  **Changer de graphe :** Dans la méthode `chooseFromGraphFamily()`, choisissez entre `Grid`, `Complete`, `ErdosRenyi` ou `Lollipop`.

## 3. Algorithmes implémentés
Nous avons implémenté les algorithmes suivants :

- [x] **Random BFS (Parcours en Largeur Aléatoire)** : Non-uniforme. Construit un arbre en choisissant aléatoirement l'ordre des voisins explorés.
- [x] **Random Kruskal (Insertion d'arêtes)** : Non-uniforme. Mélange toutes les arêtes du graphe et utilise Union-Find pour construire l'arbre sans cycle.
- [x] **Aldous-Broder** : Uniforme. Utilise une marche aléatoire pour sélectionner les arêtes.
- [x] **Random DFS (Parcours en Profondeur Aléatoire)** : Non-uniforme. Explore le graphe en profondeur en choisissant un voisin aléatoire à chaque étape.
- [x] **Wilson** : Uniforme (Algorithme par "Loop-Erased Random Walk").

## 4. Comparaison et Analyse
Les tests suivants ont été réalisés sur une **Grille (Grid)** (taille par défaut du sujet) avec 10 échantillons pour chaque algorithme.

| Algorithme      | Diamètre Moyen | Excentricité Moy. | Indice Wiener Moy. | Temps d'exec. (ms) |

| **RandomBFS** | : 
*(Remplacez les "..." par les valeurs affichées dans votre console lors de l'exécution de `make exec`)*

### Analyse des résultats

1.  **Diamètre et Forme :**
    * **Random DFS :** Produit des arbres avec un **très grand diamètre**. Visuellement, le labyrinthe est constitué de très longs corridors tortueux avec peu d'embranchements (beaucoup de sommets de degré 2). C'est un arbre "profond".
    * **Random BFS :** À l'opposé, il produit des arbres avec un **très petit diamètre**. Le labyrinthe ressemble à une étoile ou un buisson dense, rayonnant depuis la racine. C'est un arbre "large".
    * **Aldous-Broder (Uniforme) :** Se situe entre les deux. Il génère des arbres "typiques" avec un diamètre moyen. C'est ce qui ressemble le plus à un labyrinthe complexe standard.
    * **Kruskal :** Donne des résultats intermédiaires, souvent assez proches de l'uniforme sur des grilles, mais structurellement différent (souvent très rapide).

2.  **Performance :**
    * **Kruskal** et **DFS/BFS** sont généralement très rapides car ils ne repassent pas (ou peu) sur les sommets visités.
    * **Aldous-Broder** est beaucoup plus lent, surtout sur les grands graphes, car la marche aléatoire peut "tourner en rond" longtemps avant de trouver le dernier sommet manquant.

## 5. Provenance du code
* Squelette du projet (Classes `Graph`, `Grid`, `Labyrinth`, etc.) : Fourni par les enseignants.
* Algorithmes (BFS, DFS, Kruskal, Aldous-Broder) : Implémentés par le binôme.
* Structure `UnionFind` : Implémentée par le binôme (inspirée du cours).

