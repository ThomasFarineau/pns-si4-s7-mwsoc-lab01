# LAB01

- FARINEAU Thomas
- KITABDJIAN Léo

Le projet fonctionne simplement, une fois le client lancé, un nombre de choix lui est demandé dans la console.

Il est possible de créer directement une liste de film en ajoutant des films dans le fichier `movies.csv` à la racine du projet du serveur.
Ce fichier est lu à chaque lancement du serveur et doit être au format suivant :
```csv
name§isbn§sysnopsis
name§isbn§sysnopsis$teaser
```
Le premier champ est le nom du film, le second est son ISBN et le troisième est sa synopsis (MovieDesc).
Le champ `teaser` est optionnel et permet d'ajouter un teaser au film (MovieDescExtended).
Le séparateur est le caractère `§`.

Le client dispose de d'un menu avec les choix suivants :
- 1 : S'inscrire (Sign up)
- 2 : Se connecter (Login)
- 3 : Quitter (Exit)

Une fois connecté, le client affiche automatiquement la liste des films disponibles, il devra alors saisir un ISBN pour choisir un film.
Une fois le film fini, le client reçoit une facture et peut alors choisir un autre film ou se déconnecter.
