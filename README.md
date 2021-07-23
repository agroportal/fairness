<h1 align="center">Fariness - The Fariness assement service</h1>

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
    It's an implementatation in Java Web service of the framework 
    <a href="https://hal.archives-ouvertes.fr/lirmm-03208544/">"FAIR or FAIRer? An integrated quantitative FAIRness assessment grid for semantic resources and ontologies"</a>
    ,a list of defined FAIR questions related to metadata of <a href="https://github.com/sifrproject/MOD-Ontology/">the MOD ontology metadata model 1.4</a>.
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
*   A cache system of to store the result FAIR score of all ontologies in a portal

## General Usage 
The Web service is callable with an http request using prgaming languages (like java ) , command line (like curl ) or simply with a web browser (like Firefox) at [http://services.stageportal.lirmm.fr/fairness/v2/](http://services.stageportal.lirmm.fr/fairness/v2/).
### Web Service request full syntax
```
 www.services.stageportal.lirmm.fr/fairness/v2/?portal={stageportal|agroportal|bioportal}&ontologies={comma-separated list of acronyms|all}[&combined][&sync]
```
### Web Service request paramters
Parameter | Possible Values | Description | Mandatory
------------ | -------------  | ------------- | -------------
*portal* | agroportal , bioportal ( or stageportal in test environnement ) | Specifies the repository  where the semantic resource is stored | yes
*ontologies* | all or comma-separated list of acronyms (EX: EPHY,FCU) | Specifies the semantic resources for the fair assessment | yes
*combined* |  no value | If is present,in additation of the listed ontologies FAIR scores, it adds the average FAIR scores of the set  | no
*sync* | no value | If present , forces to not use the cache and recompute the FAIR scores| no
### Web Service request response
In result it returns a JSON with the following skeleton :  
 ```yaml
{
  "ontologies": { 
    "FCU": { // ontology acronym
      "Findable": { // FAIR principle
        "F1": { // Principle criterion
          "label": "Ontologies and ontology metadata are assigned a globally unique and persistent identifier. ",
          "results": {
            "F1Q1": { // Criterion question
              "question": "Does an ontology have a ?local? identifier i.e., a globally unique and potentially persistent identifier assigned by the developer (or developing organization)?", // question statement 
              "score": 6, // Question test result 
              "explication": "Valid ontology URI", // Question result explanation 
              "maxCredits": 6 // Question maximum score
            },
            "F1Q2": {...},
            "F1Q3": {...},
            "F1Q4": {...},
            "F1Q5": {...},
            "F1Q6": {...},
            "F1Q7": {...},
          },
          "score": 12, // Criterion FAIR score
          "normalizedScore": 29, // Creiterion normalized FAIR score
          "maxCredits": 41, // Criterion theoretical max score 
          "portalMaxCredits": 35 // Criterion max points calculable with this version of API
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

* A **maxCredits** it's the maximum that a score can go , definined in the original Framework
* A **portalMaxCredits** it's the maximum that a score can go in this implementation
  ``` javascript
  portalMaxCredits = maxCredits - not implemented questions
  ```
* A **normalizedScore** wich is
  ``` javascript
  normalizedScore = score / maxCredits
  ```
The list ot the FAIR questions used by the tool to assess FAIRness is here : https://github.com/agroportal/fairness/blob/master/FAIR%20questions.md 

## How it works

From the definition of the questions proposed in the Framework ["FAIR or FAIRer? An integrated quantitative FAIRness assessment grid for semantic resources and ontologies"](https://hal.archives-ouvertes.fr/lirmm-03208544/) ,we implemented a Java function to each of the questions that test it's validity using the resources MOD meta-data.

Listed below  ot the FAIR questions used by the tool to assess FAIRness is here : https://github.com/agroportal/fairness/blob/master/FAIR%20questions.md 
The obtained results over AgroPortal semantic resources are prsented here : https://github.com/agroportal/fairness/tree/master/Results

## Local instalation 
### Prerequisites

- Have a Tomcat server >= 7 running
- Have Java >= 8

### Donwload latest version of fairness
The war file is findabale [here]()
### Deploy the war in the Tomcat server
Deploy it by simply dropping the war file into the *$CATALINA_HOME\webapps* directory of any Tomcat instance. If the instance is running, the deployment will start instantly as Tomcat unpacks the archive and configures its context path.
If the instance is not running, then the server will deploy the project the next time it is started.
### Configuration
* **Portals configuration** 
  Currently are preconfigured 3 portals *agroportal* , *sifr-bioportal* and *stageportal* , each of them have a folder with it's *config.properties.example* containing the following 
   ``` properties 
    name=BioPortal #The name of the portal
    url=http://data.bioportal.lirmm.fr #The rest api uri of the portal
    apikey=<your_api_key> #The api key of an user in the portal
    cacheFilePath=<your_path> #The path where to save the cache 
    cacheEnabled=true # enabble or disable the cache globaly
  ```
  Fill up the **apikey** and **cacheFilePath** *config.properties.example* with the correspending infos , finally rename it to **config.properties** 
  > **To note** : Other portals can be added simply  by adding a folder with there name  and a config.properties, but are supported only portals with MOD meta-data implemented
  
* **Questions configuration**
  The configuration json file of the questions is fidable [here](https://github.com/agroportal/fairness/blob/master/src/main/resources/config/common/questions.config.json), where are listed all the questions with there statment and max score


## Contacts 
Emna Amdouni, University of Montpellier (emna.amdouni@lirmm.fr)
Clement Jonquet, University of Montpellier (jonquet@lirmm.fr)
Syphax Bouazzouni , University of Montpellier (syphax.bouazzouni@lirmm.fr) & [ESI](https://www.esi.dz/) (gs_bouazzouni@esi.dz)
