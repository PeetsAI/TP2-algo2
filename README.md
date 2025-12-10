# TP2 - Génération aléatoire d'arbres couvrants

**Membres du binôme :**
- Peter NICOLAS
- Prénom NOM (Numéro étudiant)

## 1. Description du projet
Ce projet s'inscrit dans le cadre de l'UE Algorithmique 2. L'objectif est d'implémenter et de comparer différents algorithmes permettant de générer des arbres couvrants aléatoires dans un graphe. Nous explorons des approches **uniformes** (où chaque arbre a la même probabilité d'être choisi) et **non-uniformes** (biaisées par la méthode de construction).

## 2. Utilisation
Le projet utilise un `Makefile` pour simplifier la compilation et l'exécution. Ouvrez un terminal à la racine du projet :

### Commandes principales
* **Compilation :**
    ```bash
    make compile
    ```
* **Exécution standard (par défaut : Grid / RandomBFS) :**
    ```bash
    make exec
    ```
* **Exécution avec paramètres :**
    Vous pouvez choisir le type de graphe et l'algorithme directement en ligne de commande :
    ```bash
    make execWithArgs typeGraph=grid algorithm=Wilson
    ```
    * **typeGraph** : `grid`, `complete`, `erdosrenyi`, `lollipop`
    * **algorithm** : `RandomBFS`, `RandomDFS`, `RandomKruskal`, `AldousBroder`, `Wilson`

* **Création de l'archive de rendu :**
    ```bash
    make zip
    ```

## 3. Algorithmes implémentés
Nous avons implémenté les 5 algorithmes suivants :

- [x] **Random BFS (Parcours en Largeur Aléatoire)** : Non-uniforme. Construit un arbre en choisissant aléatoirement l'ordre des voisins explorés.
- [x] **Random DFS (Parcours en Profondeur Aléatoire)** : Non-uniforme. Explore le graphe en profondeur en choisissant un voisin aléatoire à chaque étape.
- [x] **Random Kruskal (Insertion d'arêtes)** : Non-uniforme. Mélange toutes les arêtes du graphe et utilise Union-Find pour construire l'arbre sans cycle.
- [x] **Aldous-Broder** : Uniforme. Utilise une marche aléatoire pour sélectionner les arêtes lors de la première visite d'un sommet.
- [x] **Wilson** : Uniforme. Utilise une "Marche aléatoire à boucles effacées" (Loop-Erased Random Walk) pour construire l'arbre.

## 4. Comparaison et Analyse
Les tests suivants ont été réalisés sur une **Grille (Grid)**  avec 10 échantillons pour chaque algorithme.

| Algorithme      | Diamètre Moyen | Excentricité Moy. | Indice Wiener Moy. | Temps d'exec. (ms) |
|-----------------|----------------|-------------------|--------------------|--------------------|
| **Random BFS** | 441             |104.3236           | 250195419          |34                  |
| **Random DFS** | 6044            |1618.32334037      | 312499267166       |32                  |
| **Kruskal** | 895                |231.85997          |48770825820         |35                  |
| **Aldous-Broder**| 980           |256.5722273        |53420537940         |62                  |
| **Wilson** | 995                 |257.975897255      |53738627517         |43                  |


### Analyse des résultats

#### 1. Forme des arbres (Diamètre et Excentricité)
* **"Compact" (BFS) :** Avec un diamètre de **441**, le BFS produit des arbres très denses. L'excentricité moyenne (~104) est très faible, ce qui signifie que tout nœud est proche du centre. Visuellement, cela ressemble à une explosion ou un buisson.
* **"Profond" (DFS) :** Le DFS produit des arbres aux propriétés radicalement opposées, avec un diamètre moyen de **6044** (soit plus de 13 fois celui du BFS !). L'indice de Wiener est également un ordre de grandeur au-dessus des autres. Cela confirme que le DFS génère des labyrinthes composés de très longs couloirs sinueux avec peu d'embranchements.
* **L'Uniformité (Aldous-Broder & Wilson) :** Ces deux algorithmes donnent des résultats statistiques quasi-identiques (Diamètre ~980-995, Excentricité ~257). Cela valide expérimentalement qu'ils échantillonnent bien la même distribution uniforme. Ils représentent l'équilibre parfait entre profondeur et largeur.
* **Kruskal :** Il se situe dans une zone intermédiaire (Diamètre ~895), distinctement différent des arbres uniformes, mais plus proche d'eux que des extrêmes BFS/DFS.

#### 2. Performance (Temps de calcul)
* **Efficacité maximale :** `Random DFS` (32ms) et `Random BFS` (34ms) sont les plus rapides car ils sont linéaires $O(V+E)$ et déterministes dans leur parcours.
* **Efficacité de Kruskal :** Avec 35ms, Kruskal est extrêmement compétitif, quasi aussi rapide que les parcours simples grâce à la structure de données Union-Find optimisée.
* **Coût de l'Uniformité :** Générer un arbre parfaitement uniforme coûte plus cher.
    * `Aldous-Broder` (62ms) est le plus lent, pénalisé par le temps nécessaire pour atteindre les derniers sommets isolés.
    * `Wilson` (43ms) est nettement plus performant (~30% plus rapide ici) car l'effacement des boucles et l'arrêt dès connexion à l'arbre existant raccourcissent considérablement le processus par rapport à la marche aléatoire

## 5. Provenance du code
* **Squelette du projet** (Classes `Graph`, `Grid`, `Labyrinth`, `RootedTree`, `Main`) : Fourni par les enseignants.
* **Algorithmes** (BFS modifié, Graph modifié, DFS, Kruskal, Aldous-Broder, Wilson) 
* **Structure de données** (`UnionFind`)