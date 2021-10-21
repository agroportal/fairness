<h1 align="center"> O'FAIRE- an Ontology FAIRness Evaluator</h1>

<p align="center">
 <i>
  <img src="https://avatars.githubusercontent.com/u/16088111?s=200&v=4" alt="fair-logo" width="120px" height="120px"/>
 </i>
  <br>
  <i>
   This project is a FAIRness assessment tool for ontologies, vocabularies and semantic resources in general hosted in
   <a href="http://agroportal.lirmm.fr/">argoportal</a> or <a href="http://bioportal.lirmm.fr/">bioportal-sifr</a>. 
  </i>
  <br>
   <i>
    It's an implementation in a Java Web service of the framework 
    <a href="https://hal.archives-ouvertes.fr/lirmm-03208544/">"FAIR or FAIRer? An integrated quantitative FAIRness assessment grid for semantic resources and ontologies"</a>
    ,a list of defined FAIR questions related to a metadata of <a href="https://github.com/sifrproject/MOD-Ontology/">the MOD ontology metadata model 1.4</a>.
  </i>
  <br>
</p>

<p align="center">
  <a href="http://services.stageportal.lirmm.fr/fairness/v2/"><strong>www.services.stageportal.lirmm.fr/fairness/v2/</strong></a>
  <br>
</p>

<p align="center">
  <a href="https://circleci.com/gh/angular/workflows/angular/tree/master">
    <img src="https://img.shields.io/github/license/agroportal/fairness" alt="license" />
 </a>
</p>

## Main Features
*   Compute the FAIR score of a list of ontologies. 
*   Compute the average FAIR score of a list of ontologies.
*   A cache system to store the FAIR score result  of all ontologies in a portal

## General Usage 
The Web service is callable with an http request using programming languages (like java ) , command line (like curl ) or directly with a web browser (like Firefox) at [http://services.stageportal.lirmm.fr/fairness/v2/](http://services.stageportal.lirmm.fr/fairness/v2/).
### Web Service request full syntax
``` java
 www.services.stageportal.lirmm.fr/fairness/v2/?portal={stageportal|agroportal|bioportal}&ontologies={comma-separated list of acronyms|all}[&combined][&sync]
```
### Web Service request parameters
Parameter | Possible Values | Description | Mandatory
------------ | -------------  | ------------- | -------------
*portal* | agroportal , bioportal ( or stageportal in test environnement ) | Specifies the repository  where the semantic resource is stored | yes
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
    "request": "http://service.stageportal.lirmm.fr?portal=stageportal&ontologies=FCU", // original request
    "success": true, //status of the operation success or failure
    "executionTime": 202 / execution time in millisecond
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
* [questions.config.json](https://github.com/agroportal/fairness/blob/master/src/main/resources/config/common/questions.config.json) defining the questions with there scores , properties used and possible explanation.(Note: An html table version of this file is findable [here](https://github.com/agroportal/fairness/blob/master/doc/questions.config.md) )
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
  Currently are preconfigured with 3 portals *agroportal* , *sifr-bioportal* and *stageportal* , each of them have a folder with it's *config.properties.example* containing the following
   ``` properties 
    name=BioPortal #The name of the portal
    url=http://data.bioportal.lirmm.fr #The rest api uri of the portal
    apikey=<your_api_key> #The api key of an user in the portal
    cacheFilePath=<your_path> #The path where to save the cache 
    cacheEnabled=true # enabble or disable the cache globaly
  ```
  Fill up the **apikey** and **cacheFilePath** *config.properties.example* with the corresponding infos , finally rename it to **config.properties**
  > **To note** : Other portals can be added simply  by adding a folder with their names  and put on it the  **config.properties** file, but are supported only portals with MOD meta-data implemented


### 4- Build the project

### 5- Deploy the built war in the Tomcat server
Deploy it by simply dropping the war file into the *$CATALINA_HOME\webapps* directory of any Tomcat instance. If the instance is running, the deployment will start instantly as Tomcat unpacks the archive and configures its context path.
If the instance is not running, then the server will deploy the project the next time it is started.

### 6- (Optional) Create a cron job to do a daily update of the cache files
The following command program  update the cache every day at midnight
``` shell
  0 0 * * * /home/ontoportal/cache_rest.sh /usr/share/tomacat/webapps/fairness-assessment
 ```
To add this job to the local system, execute the command **crontab -e** , it opens a text file, press "i" to go to insert mode, copy the above command.
Then press "Esc" and type ":wq" to save and exit  
## Contact Us
Emna Amdouni, University of Montpellier (emna.amdouni@lirmm.fr). Clement Jonquet, University of Montpellier (jonquet@lirmm.fr) and Syphax Bouazzouni, University of Montpellier (syphax.bouazzouni@lirmm.fr).
Your suggestions and comments are welcome. Thank you.

