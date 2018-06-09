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
$ mvn clean package -DskipTests=true
```

### Esecuzione test-cases

```sh
$ cd Test-PRJ
$ mvn test
```

_I test-case implementati fanno riferimento ad una installazione dell'applicazione in locale, su localhost:8080_

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