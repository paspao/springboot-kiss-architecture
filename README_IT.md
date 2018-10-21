Springboot with Angular, a kiss architecture
============================================

Like suggested by wikipedia, KISS is an acronym for "Keep it simple, stupid" as a design principle noted by the U.S. Navy in 1960. The KISS principle states that most systems work best if they are kept simple rather than made complicated; therefore simplicity should be a key goal in design, and that unnecessary complexity should be avoided. The phrase has been associated with aircraft engineer Kelly Johnson.

Nell'arco della mia carriera lavorativa ho avuto modo di sperimentare vari tipi di tecnologie, avendo la possibilità di vedere lo sviluppo di un applicativo sia dal punto di vista client (che possa essere una SPA o un App nativa) sia dal punto di vista server. Accumulando queste esperienze ho provato a sviluppare un'architettura semplice che prevede il rispetto di PATTERN fondamentali in un contesto CRUD, di seguito vi illustrerò le fondmenta di questa architettura, utilizzata come punto di partenza in tutti i progetti realizzati da me o dai mie colleghi.

Nella progettazione di questa architettura ho cercato di tenere a mente sempre il concetto KISS, e proprio in quest'ottica ho previsto 5 strati anche detti tier, ognuno con delle logiche specifiche di utilizzo, me vediamo un elenco

* FRONTEND
* API
* BUSINESS LOGIC
* INTEGRATION
* DAO

L'ordine è lo stesso seguito dal flusso di informazioni che partono dal frontend per arrivare fino ai DAO. Entrando nei dettagli di ogni tier preferisco utilizzare un approccio di tipo BottomUp, partiamo dunque dai dati.

## DAO

Quando parliamo di applicativi CRUD per prima cosa parliamo di dati, in mondi SQL, NOSQL ma comunque dati da collezionare e trattare. Questo contact nel mio disegno rappresenta il punto più profondo se la guardiamo come uno stack di tier, quello che a che fare con i dati e nei quali ricade la descrizione delle entità e la logica di accesso ai dati. Attenzione semplice logica di accesso ai dati: inserimento, modifica, cancellazione e visualizzazione nulla di più e nulla che la leghi ad altri tier; è il tier piu profondo ed anche uno di quelli che non ha dipendenze con nessun altro, non gestisce aspetti specifici dell'applicativo, come autorizzazioni, transazioni o altro solo ed esclusivamente accesso ai dati.

## INTEGRATION

Di solito la semplice gestione CRUD dei dati non basta, ma probabilmente avremmo bisogno di colloquiare con altri sistemi che prescindono dai nostri dati, come webservices del tipo JAX-WS o JAX-RS, oppure sistemi di stampa specifici, con protocolli diversi. In questa componente vi ricadono tutte queste logiche, come per il DAO anche questo componente è di tipo foglia nel senso che non colloquierà con nessuna altro strato della nostra architettura, così facendo DAO e INTEGRATION tier hanno un livello di riusabilità altissimo non avendo specifiche dipendenze verso altri tiers.

## BUSINESS LOGIC

Ogni applicativo dopo l'identificazione dei dati da trattare, deve occuparsi della gestione della logica di interazione tra queste entità, coniugare i requisiti utente in logica applicativa, spezzentandoli per poi offrire ad i tiers superiori, signature dalla firma semplice ed efficace che permettano di elaborare dati senza entrare nei dettagli di come è strutturata la banca dati. In questo tier inoltre troviamo anche la definizione dei DTO che permettano di mascherare le entity che effettivamente sono sulla banca dati, un po' per proteggere alcune informazioni che possano essere sensibili (Esempio entity utente con password, o timestamp o informazioni necessarie alle entity ma non al resto dell'applicativo) (o problemi di serializzazione tra tipi di dati trattati sul db o meno).
La BUSINESS LOGIC quindi comunica con il tier DAO e il tier INTEGRATION, creando sinergia ed interazione tra i due, in più aggiunge la logica e la trasformazione dei dati in DTO fruibili da altri tiers. Attenzione il livello di business logic prende in input dei DTO definiti al suo interno e lo stesso dicasi per i dati  resituiti, non sono MAI oggetti definiti in altri tier, questo per garantire una sorta di cuscinetto (come se fosse un'interfaccia), che permetta sempre la gestione di quello che viene restituito ai taier superiori.

## API

In questo tier ricade la logica di presentation, rappresenta il punto di ingresso del nostro applicativo, almeno dal punto vista server, in esso infatti vi finiscono tutti i servizi che siano essi di tipo JAX-RS o JAX-WS che quindi hanno principalmente il compito di presentare i dati in XML, JSON o altro. La sua unica forma di comunicazione è verso il tier della BUSINESS LOGIC, un servizio non farà altro che chiamare uno o piu servizi offerti dal layer di Business, non utilizzerà mai il tier di INTEGRATION o DAO, nè tantomeno utilizzerà oggetti definiti al loro interno, questo per evitare hard coupling e spaghetti programming. Inoltre si occuperà di gestire aspetti di autenticazione ed autorizzazione. Swagger

## FRONTEND

Rappresenta la Single Page Application