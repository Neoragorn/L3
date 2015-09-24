Casier Sofian
TP 1
COO

Pour compiler le programme, placez vous dans le dossier src  et tapez la commande ;
javac game/utils/*.java -d ../classes
javac game.*.java -d ../classes

Pour Executer le programme, placez vous a la racine et tapez la commande :
java -classpath classes game.Main 'x'

Pour compiler les tests, placez vous a la racine et taper la commande:
javac -classpath test-1.7.jar test/*.java

Pour executer les tests, placez-vous a la racine et taper la commande:
java -jar test-1.7.jar NomDeClasseTest
