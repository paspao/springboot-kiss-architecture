Springboot with Angular, a kiss architecture
============================================
[![Build Status](https://travis-ci.org/paspao/springboot-kiss-architecture.svg?branch=master)](https://travis-ci.org/paspao/springboot-kiss-architecture)
[![Coverage Status](https://coveralls.io/repos/github/paspao/springboot-kiss-architecture/badge.svg?branch=master)](https://coveralls.io/github/paspao/springboot-kiss-architecture?branch=master)

Like suggested by wikipedia, KISS is an acronym for "Keep it simple, stupid" as a design principle noted by the U.S. Navy in 1960. The KISS principle states that most systems work best if they are kept simple rather than made complicated; therefore simplicity should be a key goal in design, and that unnecessary complexity should be avoided. The phrase has been associated with aircraft engineer Kelly Johnson.

Nell'arco della mia carriera lavorativa ho avuto modo di sperimentare vari tipi di tecnologie, avendo la possibilità di vedere lo sviluppo di un applicativo sia dal punto di vista client (che possa essere una SPA o un App nativa) sia dal punto di vista server. Accumulando queste esperienze ho provato a sviluppare un'architettura semplice che prevede il rispetto di PATTERN fondamentali in un contesto CRUD, di seguito vi illustrerò le fondmenta di questa architettura, utilizzata come punto di partenza in tutti i progetti realizzati da me o dai mie colleghi.

Nella progettazione di questa architettura ho cercato di tenere a mente sempre il concetto KISS, e proprio in quest'ottica ho previsto 5 strati anche detti tier, ognuno con delle logiche specifiche di utilizzo, ovvero:

* FRONTEND
* API
* BUSINESS LOGIC
* INTEGRATION
* DAO

L'ordine è lo stesso seguito dal flusso di informazioni che partono dal frontend per arrivare fino ai DAO. Entrando nei dettagli di ogni tier preferisco utilizzare un approccio di tipo BottomUp, partiamo dunque dai dati.

L'articolo fa riferimento al progetto scaricabile al link [https://github.com/paspao/springboot-kiss-architecture](https://github.com/paspao/springboot-kiss-architecture)

```bash
git clone https://github.com/paspao/springboot-kiss-architecture
```

E' un progetto organizzato utilizzando una struttra **maven** di tipo padre figlio, lo stesso frontend, sviluppato in Angular, viene inserito nella fase di building per poter garantire coerenza nella necessità di dover consegnare un unico artefatto.

## DAO

Quando parliamo di applicativi CRUD per prima cosa parliamo di dati, in mondi SQL, NOSQL ma comunque dati da collezionare e trattare. Questo modulo nel mio disegno rappresenta il punto più profondo se la guardiamo come uno stack di tier, quello che a che fare con i dati e nei quali ricade la descrizione delle entità e la logica di accesso. Attenzione semplice logica di accesso ai dati: inserimento, modifica, cancellazione e visualizzazione nulla di più e nulla che la leghi ad altri tier; è il tier piu profondo ed anche uno di quelli che non ha dipendenze con nessun altro dei suoi fratelli, non gestisce aspetti specifici dell'applicativo, come autorizzazioni, transazioni o altro: solo ed esclusivamente accesso ai dati.
In un contesto Springboot utilizziamo tecnologie quali **Entity** e **Repository**, nel dettaglio mostro la **@Configuration** del modulo DAO, oggetto centrale in ogni applictivo Springboot, infatti vederemo che ogni modulo ha il suo oggetto **@Configuration**

```java
@Configuration
@ComponentScan("org.ska.dao")
@EntityScan(basePackages = {"org.ska.dao.entity"})
@EnableJpaRepositories(basePackages = {"org.ska.dao.repository"})
@EnableAutoConfiguration
@EnableTransactionManagement
public class KissDaoConfiguration {
}
```
Quindi semplicemente definisco dove si trovano le Componenty ed in particolare dove si trovano le Entity ed i Repository. In più abilito le transazioni, così facendo chiunque utilizzerà il modulo DAO non dovrà preoccuparsi di aspetti relativi alla configurazione del DAO.



## INTEGRATION

Di solito la semplice gestione CRUD dei dati non basta, ma probabilmente avremmo bisogno di colloquiare con altri sistemi che prescindono dai nostri dati, come servizi di tipo JAX-WS o JAX-RS, oppure sistemi di stampa specifici, con protocolli diversi. In questa componente vi ricadono tutte queste logiche che prescindono dalla lagica dell'applicativo e che hanno un altissima possibilità di riutilizzo. Come per il DAO anche questo componente è di tipo foglia nel senso che non colloquierà direttamente con nessun altro strato dell' architettura piuttosto verrà utilizzato, così facendo DAO ed INTEGRATION garantiranno il concetto di riusabilità come già detto.

```java
@Configuration
@ComponentScan(basePackages = {"org.ska.integration.beans"})
public class KissIntegrationConfiguration {

    @Bean
    public GeoApiContext geocoder(){
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("Your apikey")
                .build();
        return context;
    }
}
```

## BUSINESS LOGIC

Ogni applicativo dopo l'identificazione dei dati da trattare, deve occuparsi della gestione della logica di interazione tra queste entità, coniugare i requisiti utente in logica applicativa, spezzentandoli per poi offrire ad i tiers superiori, strumenti dall'utilizzo semplice ed efficace che permettano di elaborare dati senza entrare nei dettagli di come è strutturata la banca dati. In questo tier inoltre troviamo anche la definizione e l'utilizzo dei DTO che permettano di mascherare le entity che effettivamente sono sulla banca dati, un po' per proteggere alcune informazioni che possano essere sensibili (ad esempio entity utente con password, o timestamp o informazioni necessarie alle entity ma non al resto dell'applicativo) ed anche perchè spesso il dato restituito è un dato elaborato, che probabilmente attingerà da più fonti, c'è anche da sottolineare il problema della serializzazione dei dati: in alcuni contesti è necessario trasformare le informazioni presenti in banca dati, per renderle fruibili all'uomo, questo ad esempio obbliga gli sviluppatori a macchiare con delle logiche di serializzazione le Entità, il cui scopo però è solo quello di rappresentare i dati, non serializzarli, un esempio: il campo DATE sul db magari è un numero, ma all'utente dobbiamo presentare una data, per garantire questo utilizziamo probabilmente delle annotazioni per la formattazione, che può essere una soluzione, ma così facendo leghiamo aspetti di serializzazione ad un entità e a lungo andare causerà propblemi di riutilizzo e frammentazione del codice.
La BUSINESS LOGIC quindi comunica con il tier DAO e il tier INTEGRATION, creando sinergia ed interazione tra i due, in più aggiunge la logica e la trasformazione dei dati in DTO fruibili da altri tiers. Attenzione il livello di business logic prende in input dei DTO definiti al suo interno e lo stesso dicasi per i dati  resituiti, non sono MAI oggetti definiti in altri tier, questo per garantire una sorta di cuscinetto (come se fosse un'interfaccia), che permetta sempre la gestione di quello che viene restituito ai tier superiori.

```java
@Configuration
@ComponentScan(basePackages = {"org.ska.business"})
@Import({KissDaoConfiguration.class, KissIntegrationConfiguration.class})
public class KissBusinessConfiguration {
	
	@Bean
    public Mapper dozerMapper(){
        Mapper dozerBeanMapper =  DozerBeanMapperBuilder.buildDefault();
	    return dozerBeanMapper;
    }

}
```

## API

In questo tier ricade la logica di presentation, rappresenta il punto di ingresso del nostro applicativo, almeno dal punto vista server, in esso infatti vi finiscono tutti i servizi che siano essi di tipo JAX-RS o JAX-WS che quindi hanno principalmente il compito di presentare i dati in XML, JSON o altro. La sua unica forma di comunicazione è verso il tier della BUSINESS LOGIC, un servizio non farà altro che chiamare uno o piu servizi offerti dal layer di Business, non utilizzerà mai il tier di INTEGRATION o DAO, nè tantomeno utilizzerà oggetti definiti al loro interno, questo sempre nell'ottica di evitare hard coupling e spaghetti programming. Inoltre si occuperà di gestire aspetti di autenticazione ed autorizzazione. Le API vanno in qualche modo documentate, SEMPRE: uno dei difetti del mondo REST è proprio l'assenza di un descrittore universale di questi servizi. Una tecnologia che garantisce questo aspetto è Swagger, oggi diventata OpenAPI: permette di documentare le API, documentazione riutilizzata anche per generare in modo automatico la parte client, quindi non solo puramente descrittiva, ad esempio nel nostro caso il layer di comunicazione con i servizi REST è stato completamente generato dalla descrizione Swagger dei servizi.

## FRONTEND

Rappresenta la Single Page Application: questo tipo di applicazione deve essere completamente scissa dall'applicativo e l'utilizzo della tencologia Rest già garantisce questo aspetto ma è necessario prestare attenzione a come viene implementata la comunicazione con i servizi remoti. Spesso mi sono imbattutto in pessime organizzazioni e gestioni dei vari client HTTP utilizzati per invocare i servizi remoti, e mi riferisco alle reference che si trovano lungo tutto l'applicativo, per risolvere questo problema e rendere la SPA strettamente separata da tutto ciò che rappresenta la comunciazione con i servizi remoti, come già detto, utilizzo la tecnologia Swagger per generare uno stub che permetta la comunicazione con l'API Rest. In questo modo, nella mia esperienza, gli sviluppatori utilizzeranno quanto prodotto da Swagger innanzitutto perchè è tanto codice già scritto, con diverse opzioni di utilizzo, non hanno più quindi bisogno di riscreverlo, inoltre gli sviluppatori implementeranno le loro logiche altrove, si limiteranno ad utilizzare quanto prodotto da Swagger, inoltre la sezione di comunicazione remota (Stub) sarà continuamente rigenerata e nessuno sviluppatore si sognerà mai di implementare le proprie logiche in sorgenti che potrebbero essere sovrascritti.

To build the project:

```bash
mvn clean install
```

