# TP Java 3D : DataFlight 

 - Application JavaFX qui affiche la Terre en 3D, trouve l'aéroport le plus proche d'un clic droit et
 - (pas encore fait → ) affiche les vols en direction de cet aéroport via l'API aviationstack .

## Etat d'Avancement

### Ce qui a été fait
- **`Aeroport`** : nom, pays, code IATA, latitude/longitude, `toString()`, `calculDistance()`.
- **`World`** : lecture du CSV (aéroports de type `large_airport` uniquement, 616 aéroports), `findNearestAirport(longitude, latitude)`, `findByCode(code)`, `distance(...)`. Testé : le point le plus proche de Paris est bien Orly (ORY).
- **`Earth`** (hérite de `Group`) : sphère de rayon 300 avec texture de la Terre, rotation sur l'axe Y (1 tour en 15 s), `createSphere(Aeroport, Color)` et `displayRedSphere(Aeroport)`.
- **`Interface`** (hérite de `Application`) : fenêtre JavaFX, caméra en perspective, zoom (clic maintenu + déplacement vertical), clic droit sur le globe : affiche latitude, longitude et aéroport le plus proche, puis pose une boule rouge dessus.

En gros j'ai finis la partie 1 et 2, il manque la partie 3 donc la séance 3 de TP j'avais juste la flemme de la faire ^^

### À faire (pour plus tard ou jsp de tfacon on a grave de l'avance)
- lecture du JSON de l'API
- Requête HTTP vers l'api aviationstack  et affichage des aéroports de départ avec une boule jaune

##  Pour lancer le projet

1. Java 25 (11 minimum), JavaFX SDK téléchargé (dossier `lib`).
2. Dans IntelliJ : *Run > Edit Configurations*, classe principale `Classes.Interface`, puis dans **VM options** :
```
   --module-path "CHEMIN_VERS_JAVAFX/lib" --add-modules javafx.controls --enable-native-access=javafx.graphics
```
3. Les fichiers de données sont dans `src/data/` (CSV des aéroports et image de la Terre).

## Notes d'information en plus que j'ai trouvé utile

- **Clic droit** : `pane.setPickOnBounds(false)` est indispensable, sinon le `Pane` capte le clic à la place de la sphère et les coordonnées de texture sont `null`.
- **Placement des boules** : la latitude est convertie avec `Math.asin(latitude / 90.0)` avant le calcul des coordonnées 3D (parce que la sphère JavaFX elle était décalée à la verticale en hauteur par rapport à l'emplacement du clic réel).
- C'est la « correction empirique » du sujet.
- **Encodage** : certains noms d'aéroports du CSV s'affichent mal (`MÃ©rignac`). Cela vient du fichier, pas du code.
