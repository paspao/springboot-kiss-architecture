A "Kiss architecture": Springboot + Angular
============================================
[![Build Status](https://travis-ci.org/paspao/springboot-kiss-architecture.svg?branch=master)](https://travis-ci.org/paspao/springboot-kiss-architecture)


Like suggested by wikipedia, KISS is an acronym for "Keep it simple, stupid" as a design principle noted by the U.S. Navy in 1960. The KISS principle states that most systems work best if they are kept simple rather than made complicated; therefore simplicity should be a key goal in design, and that unnecessary complexity should be avoided. The phrase has been associated with aircraft engineer Kelly Johnson.

In the course of my development experiences, I have been able to experiment with various types of technologies, having the possibility to see the development of an application both from the client side (which can be a SPA or a native App) and from server side. By relying on such experience, I tried to develop a simple architecture that involves the respect of fundamental **PATTERN** in a **CRUD** context. I will try to illustrate the foundations of this architecture, used as a starting point in all my projects.

Into the design of this architecture I have tried to keep in mind the KISS concept, and in this perspective I have provided 5 layers also called tiers, with a specific logics of use:

* FRONTEND
* API
* BUSINESS LOGIC
* INTEGRATION
* DAO

I use a *multi-tier* architectural model where the tiers order in the list, is strictly associated to the information flow: from the frontend to get up to the DAO

![Architecture](img/Ska.png)

The article refers to the project downloadable at the link [https://github.com/paspao/springboot-kiss-architecture](https://github.com/paspao/springboot-kiss-architecture)

```bash
git clone https://github.com/paspao/springboot-kiss-architecture
```

The project is organized using a *maven* structure (i.e. parent and child types); even the frontend, developed in Angular, is included into the *maven* building phase in order to create a single artifact (further details available in the **FRONTEND** paragraph below).

Going into the details of each tier, I prefer to use a *BottomUp* approach, so let’s start with the data.


## DAO

Data Access Object. When I’m talking about CRUD applications in early step I’m talking about data, in SQL, NOSQL, but in any case data to collect and process. This module in my drawing represents the deepest point if you look at it as a tier’s stack. This tier works with the datas, it describes the entities and the access logic. Caution: simple data access logic of inserting, modifying, deleting and displaying data, without any binding to other tiers; it’s the deepest tier and also it has no dependencies with any of its brothers, it does not handle specific aspects of the application, such as authorizations, transactions or other: only and exclusively access to data.
In a Springboot context I’m using technologies such as **Entity** and **Repository*. In detail I show you the *@Configuration* of the DAO module, central *annotation* in each Springboot application/module.


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

So I define where the *Components* are located by specifying where the Entities and Repository are packaged. In addition I enable transactions, so anyone using the DAO module will not have to worry about aspects of configuring the DAO module.




## INTEGRATION

Usually the simple CRUD data management is not enough: we probably need to talk with other systems that don’t depend on our data, such as JAX-WS or JAX-RS services, or specific printing systems with different protocols, etc. This component includes all these interactions/integrations without a specific binding to the application’s context in order to guarantee you a very high reusability (like the DAO module, also this tier is leaf type).

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

Here I report the central point of the module configuration: there is only one reference to the **Service** definition and the instance of a *third party* service (GeoApiContext).

## BUSINESS LOGIC

Each application, after the identification of the data to be processed, must deal with the logic of interaction between entities defined into the DAO tier. You have to combine user requirements with application logic, break them up and then expose to the upper tiers simple and readable *signatures*. So this tier lets developers to make some processing without entering into the details of how the database is structured or what integration is underneath.

Also we find here the definition and usage of **DTO** useful to mask the data that are in the database system or in the various integration beans: but why?
There are many reasons: first and foremost it is a form of information hiding for sensitive data (for example password, timestamp or other informations needed for data consistency, but no to the end user). Instead, if the returned data needs to be elaborated (like data combined from multiple sources), DTO helps to structure these data in a coherent way.
Another aspect is the serialization of data: in some contexts it is necessary to transform the information present in the database, to make it usable to humans. So the developers have to “stain” the *Entities* with serialization logics, whose purpose should be only to represent the data, for example: the DATE field on the database system is perhaps a number, but we must represent it in a readable way, so probably we will use a formatting annotation; that’s a solution! but in this way we will link aspects of serialization to an entity, and in the long run this solution will leads to unusable codes. Harold Abelson said: *Programs must be written for people to read , and only incidentally for machines to execute*.

The DTOs allow to face the problems listed, creating something like a "buffer", consequently more *loosely coupled* and more reusability.

Summing up, the BUSINESS LOGIC communicates with the DAO tier and the INTEGRATION tier, creating a synergy and an interaction between them. Besides, it brings logic and data transformation into DTO, usable by other tiers. Warning: the business logic tier uses DTO defined within and the same applies to the data returned. They *NEVER* are objects defined in other tiers, so to ensure what we said above and to give the ability to work on data returned.

Another feature of this tier is the transaction management: since it implements business logic it is able to determine whether an operation on the data can be successful or not, therefore defines the “transactionality” of the operation.

Here is the **@Configuration** of the business logic tier:

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

It is the only module with a direct link to the DAO and INTEGRATION tier, so it will necessarily have to import the configurations in order to use them. Moreover, to speed up the mapping between Entity and DTO it is a good practice to use frameworks born for this, avoiding long and unreadable code blocks of *setter* and *getter*; in my example, I use a mapping framework called **Dozer**.

## API

In this tier falls the logic of presentation, it represents the entry point of our application, at least from the server point of view. The services like JAX-RS or JAX-WS are defined with the mainly task of presenting the data in XML, JSON or other. It talks only with the BUSINESS LOGIC tier: a service will only call one or more services offered by the Business layer, it will never use the INTEGRATION or DAO tier directly, nor it will use objects defined within them, in order to avoid *tightly coupled* and *spaghetti code*.

It deals with the management of authentication and authorization: here it is possible to determine who can perform an operation or not: in the layers below is much more complicated or the informations to determine what role is necessary is not yet known.

The APIs ALWAYS need some documentation: one of the lack of the REST world is the absence of a universal descriptor of these services. A technology that guarantees this aspect is **Swagger** (now OpenAPI): it allows to document the APIs, but the documentation produced can also be reused to generate the client part, therefore not just a descriptive one. For example, in my case the communication layer with the REST services was completely generated by the Swagger description of the services: in the **FRONTEND** module there is the *remote-services* folder that contains the result of this generation by the tool [https://editor.swagger.io] (https://editor.swagger.io).

In the configuration class of the api tier, I import the business tier’s one and set the generation of the Swagger documentation.


```java
@Configuration
@Import(KissBusinessConfiguration.class)
@EnableSwagger2
public class KissApiConfiguration {
	
	    @Autowired
	    private Environment env;

	    @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("org.ska.api.web"))
	                .paths(PathSelectors.any())
	                .build()
	                .apiInfo(apiInfo());
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfo(
	                "Contact management REST API",
	                "API",
	                env.getProperty("info.version"),
	                null,
	                new Contact("Pasquale Paola", "https://www.linkedin.com/in/pasqualepaola/", "pasquale.paola@gmail.com"),
	                null, null, Collections.emptyList());
	    }
	    
}
```

## FRONTEND

It represents the Single Page Application: this type of application must be completely separated from the application and the usage of the Rest technology already guarantees this aspect, but it is necessary to pay attention to how the communications with remote services are implemented. Often I have fallen into very bad organization and management of various HTTP clients used to invoke remote services, and I mean the references that are found throughout the application. To solve this problem and to make the SPA strictly separated from everything that represents the communication with remote services, as already mentioned, I use Swagger technology to generate a stub that allows communication with the Rest API. So the developers will use what is produced by Swagger, mainly because it provides a lot of ready to use code, with different usage options, and you no longer need to rewrite it. Also the logic will be implemented elsewhere, because the remote communication section (Stub) will be continuously regenerated and no developer will ever dream to implement its own logic in sources that could be overwritten (I hope).

To ensure that an application written in Angular can be included in the building cycle of a Maven project, I ensure that even the **FRONTEND** becomes a Maven module by adding a *pom.xml* file. This module will not produce any artifact so the packaging will be *pom type*, but in this way I can insert it into the maven build and create dependencies with its siblings. To be able to integrate an Angular build in a Maven context, I use a plugin named *frontend-maven-plugin*: it allows the installation of a *Node* and *Npm* instance

```xml
...
          <execution>
            <id>install node and npm</id>
            <goals>
              <goal>install-node-and-npm</goal>
            </goals>
            <phase>generate-resources</phase>
          </execution>
          <execution>
            <id>npm install</id>
            <goals>
              <goal>npm</goal>
            </goals>
            <configuration>
              <arguments>install</arguments>
            </configuration>
          </execution>
...
```

 and the consequent invocation of the Angular *CLI* to manage dependencies and builds angular side.
 

```xml
...
          <execution>
            <id>npm build</id>
            <goals>
              <goal>npm</goal>
            </goals>
            <phase>generate-resources</phase>
            <configuration>
              <arguments>run build</arguments>
            </configuration>
          </execution>
...
```

When the Npm build task is invoked, the control will be taken from the Angular CLI, as described in *package.json*:

```json
...
"build": "ng build --prod --progress --build-optimizer --delete-output-path --base-href /kiss/ui/ --output-path dist/resources/static/ui"
...
```

The output path is set to *dist/resources/static/ui*, and the path *dist/resources* is also configured as *resource* of the frontend module. Combined with the configuration of the tier API that follows below, it allows to inject the result of the Angular build in the Springboot application. In the output path (of the *build* command in package.json) there is a special directory **…/static/…**, one of those where Springboot allows to define static contents.


```xml
...
<resources>
	<resource>
		<directory>../frontend/dist/resources</directory>
	</resource>
	<resource>
		<directory>src/main/resources</directory>
		<filtering>true</filtering>
	</resource>

</resources>
...
```


Build
=====


```bash
mvn clean install
```

Run
===



```bash
java -jar api/target/api-0.0.1-SNAPSHOT.jar
```

Got to url [http://localhost:8080/kiss/](http://localhost:8080/kiss/)

