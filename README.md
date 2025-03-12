<h1 align="center"> O'FAIRe: Ontology FAIRness evaluator</h1>

<p align="center">
  <br>
   O'FAIRe is a open source FAIRness assessment methodology and tool for ontologies, vocabularies and semantic resources developped within <a href="http://www.d2kab.org/">D2KAB</a> and <a href="http://foosin.fr/">FooSIN</a> projects. 
   O'FAIRe comes as web service (implemented in Java) which executes tests automatically evaluating how a semantic resource stored within an ontology repositry responds to a set of FAIRness assessment questions. 
  <br>
  <br>
   We implemented O’FAIRe as a Web service working with any <a href="https://ontoportal.org/">OntoPortal</a> installations respecting the <a href="https://github.com/sifrproject/MOD-Ontology">Metadata for Ontology Description and Publication Ontology (MOD1.4)</a> metadata profile to harmonize metadata. We deployed it in <a href="http://agroportal.lirmm.fr/">AgroPortal</a> and the <a href="http://bioportal.lirmm.fr/">SIFR BioPortal</a> and designed specific user interfaces. 
   Our collaborator at ENIT have also deployed O'FAIRe within <a href="http://industryportal.enit.fr/">IndustryPortal</a>.
   <br>
</p>

<img width="1128" alt="image" src="https://user-images.githubusercontent.com/7379183/159973558-3ef49b10-f363-4172-9565-c3cc8a347881.png">


<p align="center">
 The documentation illustrates the web service with examples from AgroPortal. 
 <a href="http://services.agroportal.lirmm.fr/ofaire/"><strong>http://services.agroportal.lirmm.fr/ofaire/</strong></a>
  <br>
  The service can be deployed anywhere on Tomcat and can request any ontology repository based on the OntoPortal technology (however the scores will be function of the amount of metadata fields available).
</p>

## Credits
Emna Amdouni (@eamdouniGIT), Syphax Bouazzouni (@syphax-bouazzouni) and Clement Jonquet (@jonquet) from LIRMM (University of Montpellier) and MISTEA (INRAE Montpellier).
Your suggestions and comments are welcome. Thank you!

## Cite us 

*   E. Amdouni, S. Bouazzouni, C. Jonquet, O'FAIRe makes you an offer: Metadata-based Automatic FAIRness Assessment for Ontologies and Semantic Resources, Int. J. of Metadata, Semantics and Ontologies, Inderscience, 2022, TO BE PUBLISHED https://hal.archives-ouvertes.fr/lirmm-03630233

*   E. Amdouni, S. Bouazzouni, C. Jonquet. O'FAIRe: Ontology FAIRness Evaluator in the AgroPortal semantic resource repository. ESWC 2022 - 19th Extended Semantic Web Conference, Poster and demonstration., May 2022, Hersonissos, Greece. https://dx.doi.org/10.1007/978-3-031-11609-4_17

*   E. Amdouni, C. Jonquet. FAIR or FAIRer? An integrated quantitative FAIRness assessment grid for semantic resources and ontologies. MTSR 2021 - 15th International Conference on Metadata and Semantics Research, Nov 2021, Madrid, Spain. pp.67-80. https://dx.doi.org/10.1007/978-3-030-98876-0_6

## Main features
*   Assess each aspects of the 15 FAIR principles with 61 questions specific for ontologies and semantic reosurces.
*   Compute the _FAIR score_ of one ontology or a list of ontologies. 
*   Compute the _average, min, median and max FAIR score_ of a list of ontologies.

## General Usage 
The Web service can be called with a simple HTTP GET with the following base URL: [http://services.agroportal.lirmm.fr/ofaire](http://services.agroportal.lirmm.fr/ofaire). For examples: 

``` http://services.agroportal.lirmm.fr/ofaire?ontologies=AGRO ```

or

``` http://services.agroportal.lirmm.fr/ofaire?ontologies=ATOL,EOL,AHOL&combined ```

### Web Service request mode
This web service can be used in three disctinct mode:

* Requesting the default ontology repository on which the service is deployed with no specific parameters (examples above).
* Requesting an external ontology repositrory independant of where O'FAIRe is deployed using the *url* and *apikey* parameters. For instance, with the NCI Thesaurus in the <a href="https://bioportal.bioontology.org/">NCBO BioPortal</a>: 

``` http://services.agroportal.lirmm.fr/ofaire?url=https://data.bioontology.org&ontologies=NCIT&apikey={your API-KEY on the NCBO BioPortal} ```

* Requesting an ontology repository pre-registered in O'FAIRe and for which FAIRness scores will be precomputed and cached), using the *portal* parameter. For instance, with the MDRFRE ontlogy in the <a href="http://bioportal.lirmm.fr/">SIFR BioPortal</a>:

``` http://services.agroportal.lirmm.fr/ofaire?portal=bioportal&ontologies=MDRFRE ```

### Web Service request parameters

Parameter | Possible Values | Description | Mandatory
------------ | -------------  | ------------- | -------------
*url* | a valid endpoint URL | Specifies the repository web service API endpoint URL where the semantic resource is hosted e.g., https://data.bioontology.org/ or http://ecoportal.lifewatch.eu:8080/  | no (but require apikey too if used)
*apikey* | user apikey | Specifies the user API-KEY to query the ontology repository where the semantic resource is stored. | mandatory with *url* parameter and possible with the 2 other modes (it is preferred to provide a user API-KEY, but O'FAIRe will be default uses is own one)
*portal* | agroportal, bioportal | Specifies the ontology repository pre-registered in O'FAIRe where the semantic resource is stored | no
*ontologies* | all or comma-separated list of acronyms (e.g.,: EPHY, FCU) | Specifies one or several semantic resources to process. Acronyms must be the ones provided by the requested ontology repository. | yes
*combined* |  no value | If present, in addition of the list of FAIR scores for each ontologies requested, O'FAIRe will also compute metrics for the group of ontologies requested (average, min, max and median | no
*sync* | no value | If present, forces not to use the cache and recompute the FAIR scores. | no

### Web Service response
O'FAIRe returns a JSON with the following skeleton :  

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
Each **score** in the result set comes with:
* **maxCredits**, the maximum score for this principle or sub-principles as defined by O'FAIRe methodology;
* **portalMaxCredits**, the maximum score that can be obtained in AgroPortal with urrent O'FAIRe implementation;
  ``` javascript
  portalMaxCredits = maxCredits => Some FAIRness assessment questions have not been (or cannot be) implemented yet.
  ```
* **normalizedScore**, the score normalized betweem 0 and 100.
  ``` javascript
  normalizedScore = (score / maxCredits) * 100
  ```

## O'FAIRe methodology and configuration

* O'FAIRe is based on a FAIRness assessment grid defined in https://hal.archives-ouvertes.fr/lirmm-03208544. 

* O’FAIRe implementation relies on the ontology metadata description as returned by the ontology repository. For instance, the following call returns the description for the latest version of the Agronomy Ontology in JSON-LD: 
``` http://data.agroportal.lirmm.fr/ontologies/AGRO/latest_submission?display=all ```

* O'FAIRe (v1) implements 61 FAIRness assessment questions 'projecting' the FAIR principles for semantic resources. The 61 question of the O'FAIRe methodology can be found here: https://hal.archives-ouvertes.fr/lirmm-03630233 or here: [FAIR-Questions](https://github.com/agroportal/fairness/blob/master/doc/results/FAIR-questions.md). To implemement each questions, we developped a set of Java functions to test how a semantic resources respond to the quesitons. O'FAIRe most of the time uses metadata property values but also sometimes relies directly on the features provided by the the ontology repository itself.

Configuration files include:
* [questions.config.json](https://github.com/agroportal/fairness/blob/master/src/main/resources/config/common/questions.config.json) defines the FAIRness assement questions with their possible credits, metadata property used (if any) and possible explanations.
* [properties.config.json](https://github.com/agroportal/fairness/blob/master/src/main/resources/config/common/properties.config.json) defines the correspondences betweem MOD1.4 properties and AgroPortal's metadata model as well as the type (String or Array) and the object in AgroPortal where the proprety is available.
* [metadata.voc.config.json](https://github.com/agroportal/fairness/blob/master/src/main/resources/config/common/metadata.voc.config.json) defines a list of standards metadata vocabularies (e.g., OWL,DC,RDFS), used in I2Q7.
* [catalogs.config.json](https://github.com/agroportal/fairness/blob/master/src/main/resources/config/common/catalogs.config.json) defines a list of open ontology repositories and libraries, used in F4Q1 and F4Q2.

## Latest result set of FAIRness scores on AgroPortal ontology corpus 

The latest (October 2021) results obtained over AgroPortal semantic resources is here : https://doi.org/10.57745/DEUATN

## Local installation 
O'FAIRe is open source and can be modified and/or installed locally:  

### 1- Prerequisites
- Tomcat server >= 7 running
- Java >= 8

### 2- Clone this projet
``` shell
      git clone git@github.com:agroportal/fairness.git
```
### 3- Configuration
Portal configuration is required by the *portal* parameter. File is available in *config/portals/{portal_name}/config.properties*:
``` properties 
    name=BioPortal #The name of the portal
    url=http://data.bioportal.lirmm.fr #A valid endpoint URL
    apikey=<your_api_key> #The API-KEY of an user on this repository 
    adminApikey= <your_admin_apikey> #The API-KEY of an admin user on this repository used for creating the cache
    cacheFilePath=<your_path> #The path where to save the cache 
    cacheEnabled=true # enable or disable the cache globaly
  ```
 Change the **apikey**, **adminApikey** and **cacheFilePath** in *config.properties.example* with the corresponding infos, and rename it to **config.properties**.

### 4- Build the project
```bash
mvn clean package
```
### 5- Deploy the war file in the Tomcat server
Simply drop the war file into the *$CATALINA_HOME\webapps* directory of any Tomcat instance. If the instance is running, the deployment will start instantly as Tomcat unpacks the archive and configures its context path. If the instance is not running, then the server will deploy the project the next time it is started.

### 6- Create the SERVER_DEFAULT_PORTAL env variable
Add the SERVER_DEFAULT_PORTAL env variable, by adding this line  export SERVER_DEFAULT_PORTAL='{portal_name}’ to this file /usr/share/tomcat/conf/conf.d/java_opts.conf

### 7- (Optional) Create a cron job to daily update cache files
The following command programs an update of the cache every day at midnight
``` shell
  0 0 * * * /srv/ontoportal/tomcat/fairness/current/cron/cache_reset.sh /usr/share/tomcat/webapps/fairness-assessment
 ```
To add this job to the local system, execute the command **crontab -e** , it opens a text file, press "i" to go to insert mode, copy the above command.
Then press "Esc" and type ":wq" to save and exit.  

