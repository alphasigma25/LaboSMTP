# SMPT-Lab

## Description
Ce projet permet de lancer une campagne de canular sur un serveur mock SMTP.
Le client est implémenté en java et envoie des e-mails sur un serveur créé avec MockMock.

## Configuration du serveur mock SMTP
1. Cloner le repo depuis Github :
   `git clone https://github.com/soso24soso/LaboSMTP`
2. Se déplacer dans la racine du projet :
   `cd LaboSMTP`
3. Build l'image docker :
   `docker build -t smtp/mockmock .`
4. Lancer l'image docker :
   `docker run -p 8282:8282 -p 2525:2525 smtp/mockmock`

## Lancer la campagne de canular
Depuis la racine du projet :

1. Modifier les fichiers de configuration à souhait :
   - `resources/config.properties` contient le nombre de groupes et l'adresse de la personne à mettre en copie.
   - `resources/messages.txt` contient les messages qui seront envoyés.
   - `resources/victims.txt` contient la liste des adresses emails des victimes.
2. Lancer le fichier jar :
   `java -jar LaboSMTP-1.0.jar`

## Description de l'implémentation
Le code est séparé en deux parties.
Une partie modèle représentant les personnes, les groupes et les messages.
Une partie implémentant la logique permettant de réaliser les canulars.

La classe MainApp se charge de récupérer la liste des personnes dans le
fichier `resources/victims.txt` et le contenu des e-mails à envoyer dans
`resources/messages.txt.`.
La classe PrankGenerator créé les groupes de personnes et envoie les e-mails à l'aide du client SMTP.
La classe SmtpClient implémente un client SMTP permettant d'envoyer un message à un groupe.

![TODO: Ajouter un exemple de dialogue](figures/...)
