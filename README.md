# Test-PRJ

Soluzione progetto test per candidatura J2EE Senior basata su archetipo Maven (J2EE 8 compliancy).

### Prerequisiti
* GIT client
* Maven CLI

### Compilazione e generazione pacchetto di deploy

```sh
$ git clone git@github.com:lucaarcangeli/Test-PRJ.git Test-PRJ
$ cd Test-PRJ
$ git checkout master
$ mvn clean package
```

_Le specifiche J2EE 6 ammettono la generazione di un WAR al cui interno siano contenuti anche gli EJB (non è più necessario creare un EAR). L'archtipo Maven si comporta seguendo questo schema._

### Deploy
Il file di cui eseguire il deploy:

```sh
$ Test-PRJ/target/testprj.war
```

### Documentazione API
Pubblicata via Postman all'indirizzo: https://documenter.getpostman.com/view/60224/RWEawhWb

### Note
I sorgenti del progetto comprendono:
* **directory DDL:** contiene gli script SQL utilzizati per aggiornare la struttura dati fornita, in base a quanto resosi necessario.