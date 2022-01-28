<h1 align="center"> O'FAIRe: Ontology FAIRness evaluator</h1>

<p align="center">
  <br>
   O'FAIRe is a open source FAIRness assessment methodology and tool for ontologies, vocabularies and semantic resources developped within <a href="http://www.d2kab.org/">D2KAB</a> and <a href="http://foosin.fr/">FooSIN</a> projects. 
   O'FAIRe comes as web service (implemented in Java) which executes tests automatically evaluating how a semantic resource stored within an ontology repositry responds to a set of FAIRness assessment questions. 
  <br>
  <br>
   We implemented O’FAIRe as a Web service working with any <a href="https://ontoportal.org/">OntoPortal</a> installations respecting the Metadata for Ontology Description and Publication Ontology (MOD1.4) metadata profile to harmonize metadata (https://github.com/sifrproject/MOD-Ontology). 
   We deployed it in <a href="http://agroportal.lirmm.fr/">AgroPortal</a> and the <a href="http://bioportal.lirmm.fr/">SIFR BioPortal</a> and designed specific user interfaces. 
   <br>
</p>

<p align="center">
 The documentation illustrates the web service with examples from AgroPortal. 
 <a href="http://services.agroportal.lirmm.fr/ofaire/"><strong>http://services.agroportal.lirmm.fr/ofaire/</strong></a>
  <br>
  The service can be deployed anywhere on Tomcat.
</p>

## Main Features
*   Compute the _FAIR score_ of a list of ontologies. 
*   Compute the _average, min, median and max FAIR score_ of a list of ontologies.

## General Usage 
The Web service is callable with an http request using a programming languages (like java ) , command line (like curl ) or directly with a web browser (like Firefox) at [http://services.agroportal.lirmm.fr/ofaire](http://services.agroportal.lirmm.fr/ofaire).

### Web Service request full syntax
This web service can be used in three disctinct ways:
* Using URL/APIKEY parameters
``` java
 http://services.agroportal.lirmm.fr/ofaire/?url={an url to the endpoint where to get the jsonld}&ontologies={comma-separated list of acronyms|all}[&combined][&sync]
```

* Using Portal name paramters
``` java
 http://services.agroportal.lirmm.fr/ofaire/?portal={stageportal|agroportal|bioportal}&ontologies={comma-separated list of acronyms|all}[&combined][&sync]
```
* Using the defautl portal 
``` java
 http://services.agroportal.lirmm.fr/ofaire/?ontologies={comma-separated list of acronyms|all}[&combined][&sync]
```
> In this last case, the default portal will be **"agroportal"** if http://services.agroportal.lirmm.fr is used. And **"bioportal"** in case of http://services.bioportal.lirmm.fr/ofaire.


### Web Service request parameters
Parameter | Possible Values | Description | Mandatory
------------ | -------------  | ------------- | -------------
*url* | a valid URL | Specifies the repository API url where the semantic resource is stored | no
*portal* | agroportal , bioportal ( or stageportal in test environnement ) | Specifies the repository  where the semantic resource is stored | no
*apikey* | the apikey to use with the URL or portal | Specifies the repository  where the semantic resource is stored | mandatory with URL parameter and no with the portal parameter
*ontologies* | all or comma-separated list of acronyms (EX: EPHY,FCU) | Specifies the semantic resources for the fair assessment | yes
*combined* |  no value | If is present,in addition of the listed ontologies FAIR scores, it adds the average FAIR scores of the set  | no
*sync* | no value | If present , forces to not use the cache and recompute the FAIR scores| no

### Web Service request response
In result, it returns a JSON with the following skeleton :  

 ```yaml
{
  "ontologies": { 
    "FCU": { // ontology acronym
      "Findable": { // FAIR principal
        "F1": { // Subprincipal
          "label": "Ontologies and ontology metadata are assigned a globally unique and persistent identifier. ",
          "results": {
           "F1Q1": {
                 "question": "Does an ontology have a \"local\" identifier i.e., a globally unique and potentially persistent identifier assigned by the developer                               (or developing organization)?",
                 "score": 9,
                 "explanation": "Present and valid ontology URI.", //Score explanation
                 "properties": {//List of properties used in the test with there values
                              "owl:ontologyIRI": "http://ontology.inrae.fr/frenchcropusage"
                 },
                 "maxCredits": 9,
                 "points": [ //Array of possible scores and explanation for this question
                        {
                          "explanation": "Ontology URI is not present.",
                          "score": 0
                        },
                        {
                        "explanation": "Present but invalid ontology URI.",
                        "score": 3
                        },
                        {
                        "explanation": "Present and valid ontology URI.",
                        "score": 9
                        }
               ]
            },
            "F1Q2": {...},
            "F1Q3": {...},
            "F1Q4": {...},
            "F1Q5": {...},
            "F1Q6": {...},
            "F1Q7": {...},
          },
          "score": 12, // Subprincipal FAIR score
          "normalizedScore": 29, // Creiterion normalized FAIR score
          "maxCredits": 41, // Subprincipal theoretical max score 
          "portalMaxCredits": 35 // Subprincipal max points calculable with this version of API
        },
        "F2": {...},
        "F3": {...},
        "F4": {...},
        "score": 41, // Principle FAIR score
        "normalizedScore": 36, // Principle normalized FAIR score
        "maxCredits": 113, // Principle theoretical max score 
        "portalMaxCredits": 86 // Principle max points calculable with this version of API
      },
      "Accessible": {...},
      "Reusable": {...},
      "score": 248.46, // Ontology FAIR score
      "normalizedScore": 51,  // Ontology normalized FAIR score
      "maxCredits": 478, // Ontology theoretical max score 
      "executionTime": 90 // Ontology max points calculable with this version of API
    }
  },
  "status": { // operation meta-data
    "request": "http://service.agroportal.lirmm.fr?portal=stageportal&ontologies=FCU", // original request
    "success": true,
    "executionTime": 959,
    "endpoint": "http://data.agroportal.lirmm.fr",
    "useCache": true
  }
}
 ```
Each **score** in the result comes with :

* A **maxCredits**, the maximum that a score can go , defined in the original Framework
* A **portalMaxCredits**, the maximum that a score can go in this implementation
  ``` javascript
  portalMaxCredits = maxCredits - not implemented questions
  ```
* A **normalizedScore** which is
  ``` javascript
  normalizedScore = score / maxCredits
  ```

## How it works

From the definition of the questions proposed in the Framework ["FAIR or FAIRer? An integrated quantitative FAIRness assessment grid for semantic resources and ontologies"](https://hal.archives-ouvertes.fr/lirmm-03208544/) ,we implemented a Java functions to each of them that test its validity using the resources MOD meta-data.

* The FAIR questions used by the tool to assess FAIRness is here : [FAIR-Questions](https://github.com/agroportal/fairness/blob/master/doc/results/FAIR-questions.md)
* The obtained results over AgroPortal semantic resources is here : [FAIR-Results](https://github.com/agroportal/fairness/tree/master/doc/results/FAIR-results.xlsx)

To make the application reusable , me use a list on configuration files written in json for :
* [questions.config.json](https://github.com/agroportal/fairness/blob/master/src/main/resources/config/common/questions.config.json) defining the questions with there scores , properties used and possible explanation.
* [properties.config.json](https://github.com/agroportal/fairness/blob/master/src/main/resources/config/common/properties.config.json) defining the list of MOD properties with there equivalent in Agroportal, source (ontology ,summision or links) and type (String or Array).
* [metadata.voc.config.json](https://github.com/agroportal/fairness/blob/master/src/main/resources/config/common/metadata.voc.config.json) defining a list of standards metadata vocabularies (e.g OWL,DC,RDFS), used in I2Q7.
* [catalogs.config.json](https://github.com/agroportal/fairness/blob/master/src/main/resources/config/common/catalogs.config.json) defining a list of open ontology repositories and labries (e.g Fair Sharing , OLS), used in F4Q1 and F4Q2.
## Local installation 
To do a local installation of this web service, you will nill 
### 1- Prerequisites

- Have a Tomcat server >= 7 running
- Have Java >= 8

### 2- Clone this projet
``` shell
      git clone git@github.com:agroportal/fairness.git
```
### 3- Configuration
* **Portals configuration**
   Portal configuration will be selected from the "portal" parameter and it will be placed in *config/portals/{portal_name}/config.properties* containing the following
   ``` properties 
    name=BioPortal #The name of the portal
    url=http://data.bioportal.lirmm.fr #The rest api uri of the portal
    apikey=<your_api_key> #The api key of an user in the portal
    cacheFilePath=<your_path> #The path where to save the cache 
    cacheEnabled=true # enable or disable the cache globaly
  ```
  Fill up the **apikey** and **cacheFilePath** in *config.properties.example* with the corresponding infos , and finally rename it to **config.properties**


### 4- Build the project

### 5- Deploy the built war in the Tomcat server
Deploy it by simply dropping the war file into the *$CATALINA_HOME\webapps* directory of any Tomcat instance. If the instance is running, the deployment will start instantly as Tomcat unpacks the archive and configures its context path.
If the instance is not running, then the server will deploy the project the next time it is started.
### 6- Create the SERVER_DEFAULT_PORTAL env variable
add the SERVER_DEFAULT_PORTAL env variable, by adding this line  export SERVER_DEFAULT_PORTAL='{portal_name}’  to this file /usr/share/tomcat/conf/conf.d/java_opts.conf
### 7- (Optional) Create a cron job to do a daily update of the cache files
The following command program  update the cache every day at midnight
``` shell
  0 0 * * * /home/ontoportal/cache_rest.sh /usr/share/tomacat/webapps/fairness-assessment
 ```
To add this job to the local system, execute the command **crontab -e** , it opens a text file, press "i" to go to insert mode, copy the above command.
Then press "Esc" and type ":wq" to save and exit  

## Contact us
Emna Amdouni, University of Montpellier (emna.amdouni@lirmm.fr). Syphax Bouazzouni, University of Montpellier (syphax.bouazzouni@lirmm.fr) and Clement Jonquet, University of Montpellier (jonquet@lirmm.fr).

Your suggestions and comments are welcome. Thank you!

