


 ## Findable
 <table>
    <tr>
        <th>Sub Principles </th>
        <th>Questions</th>
        <th>Questions credits</th>
        <th>Used properties</th>
        <th>State</th>
    </tr>
    <tr>
         <td rowspan="4">
              F1: Ontologies and ontology metadata are assigned a globally unique and persistent identifier. 
              <ul>
                <li>Max Credits : 41</li>
                <li>Portal max Credits : 41</li>
              </ul>
          </td>
         <td>Does an ontology have a "local" identifier i.e., a globally unique and potentially persistent identifier assigned by the developer (or developing organization)?</td>
         <td>[{"score"=>0, "explanation"=>"The Ontology URI is not present."}, {"score"=>3, "explanation"=>"The ontology URI is present but invalid."}, {"score"=>9, "explanation"=>"The ontology URI is present and valid."}]</td>
      </tr>
      <tr>
            <td>Does an ontology provide an additional "external" identifier i.e., a guarantee globally unique and persistent identifier assigned by an accredited body?  If yes, is the external identifier a DOI?</td>
            <td>[{"score"=>0, "explanation"=>"The external identifier is not present and invalid."}, {"score"=>3, "explanation"=>"The external identifier is present but invalid."}, {"score"=>6, "explanation"=>"The external identifier is valid but is not a DOI."}, {"score"=>9, "explanation"=>"The external DOI identifier is invalid."}, {"score"=>11, "explanation"=>"The external DOI identifier is valid."}]</td>
            <td>["dct:identifier"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Are the ontology metadata included in the ontology file- and consequently share the same identifiers or is the metadata record clearly identified by its own URI.</td>
            <td>[{"score"=>12, "explanation"=>"The repository makes explicit relation between metadata and ontology."}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr><tr>
            <td>Does an ontology provide a version specific URI and is this URI resolvable/ dereferenceable?</td>
            <td>[{"score"=>0, "explanation"=>"The version URI is not present."}, {"score"=>2, "explanation"=>"The version URI is present but invalid."}, {"score"=>4, "explanation"=>"The version URI is present but not resolvable."}, {"score"=>9, "explanation"=>"The version URI is present, valid and resolvable."}]</td>
            <td>["owl:versionIRI"]</td>
            <td>implemented</td>
         </tr>
    <tr>
         <td rowspan="4">
              F2: Ontologies are described with rich ontology metadata.
              <ul>
                <li>Max Credits : 27</li>
                <li>Portal max Credits : 27</li>
              </ul>
          </td>
         <td>Is an ontology described with additional 'MIRO must' metadata properties? </td>
         <td>[{"score"=>0, "explanation"=>"no MIRO must property found."}, {"score"=>4, "explanation"=>"1 MIRO must property found."}, {"score"=>8, "explanation"=>"2 MIRO must properties found."}, {"score"=>12, "explanation"=>"3 MIRO must properties found."}, {"score"=>16, "explanation"=>"4 or more MIRO must properties found."}]</td>
      </tr>
      <tr>
            <td>Is an ontology described with additional 'MIRO should' metadata properties? </td>
            <td>[{"score"=>0, "explanation"=>"The ontology describes 0 'MIRO should' properties."}, {"score"=>2, "explanation"=>"The ontology describes 1 'MIRO should' properties."}, {"score"=>4, "explanation"=>"The ontology describes 2 'MIRO should' properties."}, {"score"=>5, "explanation"=>"The ontology describes 3 or more 'MIRO should' properties."}]</td>
            <td>["mod:metrics", "omv:numberOfClasses", "omv:numberOfIndividuals", "omv:numberOfProperties", "omv:numberOfAxioms"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Is an ontology described with additional 'MIRO optional' metadata properties?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology describes 0 'MIRO optional' properties."}, {"score"=>1.5, "explanation"=>"The ontology describes 1 'MIRO optional' properties."}, {"score"=>2, "explanation"=>"The ontology describes 2 or more 'MIRO optional' properties."}]</td>
            <td>["vann:preferredNamespacePrefix"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Is an ontology described with another metadata property with no explicit corresponding MIRO requirement?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology describes 0 'no MIRO' properties."}, {"score"=>1, "explanation"=>"The ontology describes 1 'no MIRO' properties."}, {"score"=>2, "explanation"=>"The ontology describes 2 'no MIRO' properties."}, {"score"=>3, "explanation"=>"The ontology describes 3 or more  'no MIRO' properties."}]</td>
            <td>["dct:language", "dct:abstract", "mod:analytics", "dct:bibliographicCitation", "rdfs:comment", "foaf:depiction", "foaf:logo", "voaf:toDoList", "schema:award", "schema:associatedMedia", "owl:isIncompatibleWith", "dct:hasPart", "schema:workTranslation", "door:hasDisparateModelling", "voaf:usedBy", "voaf:hasDisjunctionsWith", "omv:keyClasses", " void:rootResource", "mod:browsingUI", "mod:sampleQueries", "void:propertyPartition", "void;classPartition", "void:dataDump", "void:openSearchDescription", "void:uriLookupEndpoint", "schema:comments", "dct:created", "dct:modified", "dct:valid", "dct:dateSubmitted", "pav:curatedOn", "omv:IsOfType", "void:propertyPartition", "void:classPartition", "schema:comment"]</td>
            <td>implemented</td>
         </tr>
    <tr>
         <td rowspan="3">
              F3: Ontology metadata clearly and explicitly include the identifier of the ontology they describe.
              <ul>
                <li>Max Credits : 21</li>
                <li>Portal max Credits : 16</li>
              </ul>
          </td>
         <td>Are the ontology metadata included and maintained in the ontology file?</td>
         <td>[{"score"=>21}]</td>
      </tr>
      <tr>
            <td>If not, are the ontology metadata described in an external file?</td>
            <td>[{"score"=>11, "explanation"=>"The ontology repository provides ontology metadata in an external file."}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr><tr>
            <td>Does that external file explicitly link to the ontology and vice-versa?</td>
            <td>[{"score"=>0, "explanation"=>" No explicit link from metadata to the ontology."}, {"score"=>5, "explanation"=>"It exist a link only from one direction (form ontology to metadata or the inverse)."}, {"score"=>10, "explanation"=>"It exist a bidirectional link between the ontology and it's metadata."}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr>
    <tr>
         <td rowspan="3">
              F4: Ontologies and ontology metadata are registered or indexed in a searchable resource typically an ontology repository.
              <ul>
                <li>Max Credits : 20</li>
                <li>Portal max Credits : 16</li>
              </ul>
          </td>
         <td>Is the ontology registered in multiple ontology 'libraries' ?</td>
         <td>[{"score"=>0, "explanation"=>"The ontology is registered in 0 ontology 'libraries'."}, {"score"=>2, "explanation"=>"The ontology is registered in 1 ontology 'libraries'."}, {"score"=>4, "explanation"=>"The ontology is registered in  2 ontology 'libraries'."}, {"score"=>6, "explanation"=>"The ontology is registered in 3 or more ontology 'libraries'."}]</td>
      </tr>
      <tr>
            <td>Is the ontology registered in multiple open ontology 'repositories' ?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology is registered in 0 ontology 'repository'."}, {"score"=>2, "explanation"=>"The ontology is registered in 1 ontology 'repository'."}, {"score"=>4, "explanation"=>"The ontology is registered in 2 ontology 'repository'."}, {"score"=>6, "explanation"=>"The ontology is registered in 3 ontology 'repository'."}, {"score"=>8, "explanation"=>"The ontology is registered in 4 ontology 'repository'."}, {"score"=>10, "explanation"=>"The ontology is registered in 5 or more ontology 'repository'."}]</td>
            <td>["schema:includedInDataCatalog"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Are the ontology libraries or repositories properly indexed by Web search engines?</td>
            <td>[{"score"=>8}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr>
     </table>
 

 ## Accessible
 <table>
    <tr>
        <th>Sub Principles </th>
        <th>Questions</th>
        <th>Questions credits</th>
        <th>Used properties</th>
        <th>State</th>
    </tr>
    <tr>
         <td rowspan="4">
              A1: Ontologies and ontology metadata are retrievable by their identifier using a standardized communication protocol.
              <ul>
                <li>Max Credits : 43</li>
                <li>Portal max Credits : 43</li>
              </ul>
          </td>
         <td>Does the ontology URI and other identifiers, if exist, resolve to the ontology?</td>
         <td>[{"score"=>0, "explanation"=>"The ontology have 0 resolvable identifier."}, {"score"=>3, "explanation"=>"The ontology have 1 resolvable identifier."}, {"score"=>6, "explanation"=>"The ontology have 2 or more resolvable identifier."}]</td>
      </tr>
      <tr>
            <td>Does the ontology URI (if metadata is included in the ontology file) or the external metadata URI resolve to the metadata record?</td>
            <td>[{"score"=>7, "explanation"=>"The repository provides an external metadata URI which resolves to the metadata record"}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr><tr>
            <td>Are ontology and its metadata supporting content negotiation?</td>
            <td>[{"score"=>0, "explanation"=>"Ontology and its metadata does not support content negotiation."}, {"score"=>6, "explanation"=>"Ontology or its metadata support together 1  format."}, {"score"=>12, "explanation"=>"Ontology and its metadata support together 2 formats."}, {"score"=>18, "explanation"=>"Ontology and its metadata support together 3 formats."}, {"score"=>24, "explanation"=>"Ontology and its metadata support together 4 or more formats."}]</td>
            <td>["owl:ontologyIRI"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Are ontology and its metadata accessible through another standard protocol such as SPARQL?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology is not accessible through a SPARQL endpoint."}, {"score"=>6, "explanation"=>"Ontology is not accessible through SPARQL endpoint."}]</td>
            <td>["sd:endpoint"]</td>
            <td>implemented</td>
         </tr>
    <tr>
         <td rowspan="3">
              A11: The protocol to retrieve ontologies and ontology metadata is open, free and universally implementable.
              <ul>
                <li>Max Credits : 28</li>
                <li>Portal max Credits : 28</li>
              </ul>
          </td>
         <td>Is the ontology relying on HTTP/URIs for its identification and access mechanisms?</td>
         <td>[{"score"=>20, "explanation"=>"The repository supports HTTP for access and URI for identification."}]</td>
      </tr>
      <tr>
            <td>If the ontology and its metadata are accessible through another protocol, is that protocol open, free and universally implementable?</td>
            <td>[{"score"=>4, "explanation"=>"The repository is relying on protocols that are open, free and universally implementable."}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr><tr>
            <td>Is the ontology relying on HTTP/URIs for its identification and access mechanisms?</td>
            <td>[{"score"=>4, "explanation"=>"The repository is relying on protocols that are open, free and universally implementable."}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr>
    <tr>
         <td rowspan="2">
              A12: The protocol to retrieve ontologies and ontology metadata support authentification and authorization when an ontology has access restriction.
              <ul>
                <li>Max Credits : 22</li>
                <li>Portal max Credits : 22</li>
              </ul>
          </td>
         <td>Is the ontology accessible through a protocol that supports authentication and authorization?</td>
         <td>[{"score"=>11, "explanation"=>"The repository supports authentification and authorization."}]</td>
      </tr>
      <tr>
            <td>Are the ontology metadata accessible through a protocol that supports authentification and authorization?</td>
            <td>[{"score"=>11, "explanation"=>"The repository supports authentification and authorization."}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr>
    <tr>
         <td rowspan="4">
              A2: Ontology metadata should be accessible even when the ontology is no longer available.
              <ul>
                <li>Max Credits : 20</li>
                <li>Portal max Credits : 20</li>
              </ul>
          </td>
         <td>Is the ontology accessible in a repository that supports versioning?</td>
         <td>[{"score"=>7, "explanation"=>"The repository supports versioning."}]</td>
      </tr>
      <tr>
            <td>Are the metadata of each version available?</td>
            <td>[{"score"=>5, "explanation"=>"The repository provides metadata for each version."}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr><tr>
            <td>Are ontology metadata accessible even if no more versions of the ontology are available?</td>
            <td>[{"score"=>4, "explanation"=>"The repository supports accessibility even if no more versions are available."}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr><tr>
            <td>Is the status of the ontology clearly informed?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology status is not specified."}, {"score"=>4, "explanation"=>"The ontology status is clearly specified."}]</td>
            <td>["omv:status"]</td>
            <td>implemented</td>
         </tr>
     </table>
 

 ## Interoperable
 <table>
    <tr>
        <th>Sub Principles </th>
        <th>Questions</th>
        <th>Questions credits</th>
        <th>Used properties</th>
        <th>State</th>
    </tr>
    <tr>
         <td rowspan="5">
              I1: Ontologies and ontology metadata use a formal, accessible, shared and broadly  applicable language for knowledge representation. 
              <ul>
                <li>Max Credits : 44</li>
                <li>Portal max Credits : 44</li>
              </ul>
          </td>
         <td>What is the representation language used for ontology and ontology metadata?</td>
         <td>[{"score"=>0, "explanation"=>"Ontology and ontology metadata are not in a formal, accessible, shared and broadly applicable language."}, {"score"=>4, "explanation"=>"Ontology and ontology metadata are represented in PDF."}, {"score"=>5, "explanation"=>"Ontology and ontology metadata are represented in TXT."}, {"score"=>11, "explanation"=>"Ontology and ontology metadata are represented in CSV."}, {"score"=>12, "explanation"=>"Ontology and ontology metadata are represented in XML."}, {"score"=>14, "explanation"=>"Ontology and ontology metadata are represented in OBO."}, {"score"=>16, "explanation"=>"Ontology and ontology metadata are represented in RDFS."}, {"score"=>18, "explanation"=>"Ontology and ontology metadata are represented in SKOS."}, {"score"=>20, "explanation"=>"Ontology and ontology metadata are represented in OWL."}]</td>
      </tr>
      <tr>
            <td>Is the representation language used a W3C Recommendations?</td>
            <td>[{"score"=>0, "explanation"=>"Ontology and ontology metadata are not represented in a W3C language."}, {"score"=>10, "explanation"=>"Ontology and ontology metadata are represented in a W3C language."}]</td>
            <td>["omv:hasOntologyLanguage"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Is the syntax of the ontology informed?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology syntax is not informed."}, {"score"=>5, "explanation"=>"The ontology syntax is informed."}]</td>
            <td>["omv:hasOntologySyntax"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Is the formality level (e.g. domain ontology) of the ontology informed by the author?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology formality level is not informed."}, {"score"=>5, "explanation"=>"The ontology formality level is informed."}]</td>
            <td>["omv:hasFormalityLevel"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Is the format of the ontology informed?</td>
            <td>[{"score"=>0, "explanation"=>"No information is provided about the availability of the ontology in other formats."}, {"score"=>4, "explanation"=>"The availability of other ontology formats is informed."}]</td>
            <td>["dct:hasFormat", "dct:isFormatOf"]</td>
            <td>implemented</td>
         </tr>
    <tr>
         <td rowspan="7">
              I2: Ontologies and ontology metadata use vocabularies that follow FAIR principles.
              <ul>
                <li>Max Credits : 32</li>
                <li>Portal max Credits : 22</li>
              </ul>
          </td>
         <td>Does the ontology import other FAIR vocabularies?</td>
         <td>[{"score"=>0, "explanation"=>"The ontology does not import other vocabularies."}, {"score"=>5, "explanation"=>"The ontology imports other FAIR vocabularies."}]</td>
      </tr>
      <tr>
            <td>Does the ontology reuse URIs from other vocabularies (URIs)?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology does not reuse other vocabularies."}, {"score"=>5, "explanation"=>"The ontology reuses terms from other vocabularies."}]</td>
            <td>["dct:relation"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>If yes, does it include for those termes (e.g. MIREOT)? </td>
            <td>[{"score"=>3}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr><tr>
            <td>Is an ontology aligned to other FAIR vocabularies?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology is not aligned to other vocabularies."}, {"score"=>5, "explanation"=>"The ontology is aligned to other vocabularies."}]</td>
            <td>["voaf:hasEquivalencesWith"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>If yes, are those alignments well represented and to unambiguous entities? if yes, are those alignements curated?</td>
            <td>[{"score"=>7}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr><tr>
            <td>Does an ontology provide metadata information about relation to or influence of other vocabularies?</td>
            <td>[{"score"=>0, "explanation"=>"Ontology does not declare influential relations."}, {"score"=>2, "explanation"=>"Ontology declares influential relations."}]</td>
            <td>["voaf:specializes", "schema:translationOfWork", "voaf:similar", "owl:priorVersion", "voaf:generalizes", "dct:isPartOf"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Does the ontology reuse standard & FAIR metadata vocabularies to describe its metadata?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology does not reuse standards metadata vocabularies to describe its metadata."}, {"score"=>5, "explanation"=>"The ontology reuses standards metadata vocabularies to describe its metadata."}]</td>
            <td>["voaf:metadataVoc"]</td>
            <td>implemented</td>
         </tr>
    <tr>
         <td rowspan="3">
              I3: Ontologies or ontology metadata include qualified references to other (meta)data. 
              <ul>
                <li>Max Credits : 33</li>
                <li>Portal max Credits : 7</li>
              </ul>
          </td>
         <td>Does an ontology provide qualified cross-references to external resources/databases?</td>
         <td>[{"score"=>20}]</td>
      </tr>
      <tr>
            <td>If yes, are those cross-references well represented and to unambiguous entities?</td>
            <td>[{"score"=>6}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr><tr>
            <td>Does the ontology use valid URIs to encode some metadata values?</td>
            <td>[{"score"=>7, "explanation"=>"The ontology use URIs to encode metadata values."}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr>
     </table>
 

 ## Reusable
 <table>
    <tr>
        <th>Sub Principles </th>
        <th>Questions</th>
        <th>Questions credits</th>
        <th>Used properties</th>
        <th>State</th>
    </tr>
    <tr>
         <td rowspan="6">
              R1: Ontologies and ontology metadata are richly described with a plurality of accurate and relevant attributes.
              <ul>
                <li>Max Credits : 32</li>
                <li>Portal max Credits : 5</li>
              </ul>
          </td>
         <td>Does the ontology provide information about how classes or concept are defined?</td>
         <td>[{"score"=>0, "explanation"=>"The ontology provides 0 information about its classes or concept."}, {"score"=>1, "explanation"=>"The ontology provides 1 information about its classes or concept."}, {"score"=>2, "explanation"=>"The ontology provides 2 informations about its classes or concept."}, {"score"=>3, "explanation"=>"The ontology provides 3 informations about its classes or concept."}, {"score"=>4, "explanation"=>"The ontology provides 4 informations about its classes or concept."}, {"score"=>5, "explanation"=>"The ontology provides 5 or more informations about its classes or concept."}]</td>
      </tr>
      <tr>
            <td>Does the ontology provide information about its hierarchy?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology provides 0 information about its hierarchy."}, {"score"=>1, "explanation"=>"The ontology provides 1 information about its hierarchy."}, {"score"=>2, "explanation"=>"The ontology provides 2 informations about its hierarchy."}, {"score"=>3, "explanation"=>"The ontology provides 2 or more informations about its hierarchy."}]</td>
            <td>["mod:hierarchyProperty", " mod:obsoleteParent", "mod:maxDepth"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>How much of the ontology objects are described with labels?</td>
            <td>[{"score"=>7}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr><tr>
            <td>How much of the ontology objects are defined using a text description?</td>
            <td>[{"score"=>6}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr><tr>
            <td>How much of the ontology objects are defined using a property restriction (e.g. OWL quantifier, cardinality or has value restrictions) or an equivalent objects (e.g. an OWL named class)?</td>
            <td>[{"score"=>6}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr><tr>
            <td>How much of the ontology objects provide provenance information with annotation properties (e.g., author, date)? </td>
            <td>[{"score"=>5}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr>
    <tr>
         <td rowspan="3">
              R11: Ontologies and ontology metadata are released with a clear and accessible  usage license.
              <ul>
                <li>Max Credits : 37</li>
                <li>Portal max Credits : 37</li>
              </ul>
          </td>
         <td>Is the ontology license clearly specified, with a URI that is resolvable ans supports content negotiation?</td>
         <td>[{"score"=>0, "explanation"=>"The ontology license is not specified."}, {"score"=>4, "explanation"=>"The ontology license is specified but not valid URI."}, {"score"=>8, "explanation"=>"The ontology license is valid but not resolvable."}, {"score"=>12, "explanation"=>"The ontology license is resolvable but not content negotiable."}, {"score"=>15, "explanation"=>"The ontology license is clearly specified."}]</td>
      </tr>
      <tr>
            <td>Are the ontology access rights clearly specified?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology access rights are not clearly specified."}, {"score"=>7, "explanation"=>"The ontology access rights are clearly specified."}]</td>
            <td>["dct:rightsHolder"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Are the permissions, usage guidelines and copyright holder clearly documented?</td>
            <td>[{"score"=>0, "explanation"=>"Neither ontology license nor access rights are documented"}, {"score"=>5, "explanation"=>"The ontology document one of them."}, {"score"=>10, "explanation"=>"The ontology document two of them."}, {"score"=>15, "explanation"=>"The ontology document the permissions, usage guidelines and copyright holder."}]</td>
            <td>["cc:morePermissions", "cc:useGuidelines", "dct:rightsHolder"]</td>
            <td>implemented</td>
         </tr>
    <tr>
         <td rowspan="8">
              R12: Ontologies and ontology metadata are associated with detailed provenance.
              <ul>
                <li>Max Credits : 38</li>
                <li>Portal max Credits : 36</li>
              </ul>
          </td>
         <td>Does the ontology provide information about the actors involved in its development? (e.g., creator, contributor, curator, translator)</td>
         <td>[{"score"=>0, "explanation"=>"The ontology provide 0 information about the actors involved in its development."}, {"score"=>3, "explanation"=>"The ontology provide 1 information about the actors involved in its development."}, {"score"=>5, "explanation"=>"The ontology provide 2 informations about the actors involved in its development."}, {"score"=>7, "explanation"=>"The ontology provide 3 informations about the actors involved in its development."}, {"score"=>8, "explanation"=>"The ontology provide 4 or more informations about the actors involved in its development. "}]</td>
      </tr>
      <tr>
            <td>Does an ontology provide information on its general provenance? (e.g., source, generated by, invalidated by)</td>
            <td>[{"score"=>0, "explanation"=>"The ontology provide O information on its general provenance."}, {"score"=>2, "explanation"=>"The ontology provide 1 information on its general provenance."}, {"score"=>4, "explanation"=>"The ontology provide 2 informations on its general provenance."}, {"score"=>6, "explanation"=>"The ontology provide 3 or more informations on its general provenance."}]</td>
            <td>["dct:source", "prov:wasGeneratedBy", "prov:WasInvalidatedBy"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Are the accrual methods and policy documented? </td>
            <td>[{"score"=>0, "explanation"=>"The ontology provide 0 accrual information."}, {"score"=>2, "explanation"=>"The ontology provide 1 accrual information."}, {"score"=>4, "explanation"=>"The ontology provide 2 accrual informations."}, {"score"=>6, "explanation"=>"The ontology provide 3 or more accrual informations."}]</td>
            <td>["dct:accrualMethod", "dct:accrualPeriodicity", "dct:accrualPolicy"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Is the ontology clearly versioned?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology provide O information about versioning."}, {"score"=>2, "explanation"=>"The ontology provide 1 information about versioning."}, {"score"=>4, "explanation"=>"The ontology provide 2 informations about versioning."}]</td>
            <td>["owl:versionInfo", "owl:priorVersion"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Are the ontology latest change documented?</td>
            <td>[{"score"=>2}]</td>
            <td>none</td>
            <td>not implemented</td>
         </tr><tr>
            <td>Are the methodology and tools used to build the ontology documented?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology provides 0 information about methodology and tools used to build it."}, {"score"=>2, "explanation"=>"The ontology provides 2 informations about methodology and tools used to build it."}, {"score"=>4, "explanation"=>"The ontology provides 4 informations about methodology and tools used to build it."}, {"score"=>6, "explanation"=>"The ontology provides 6 informations about methodology and tools used to build it."}]</td>
            <td>["omv:usedOntologyEngineeringTool", "omv:usedOntologyEngineeringMethodology", "omv:conformsToKnowledgeRepresentationParadigm"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Is the ontology rationale documented?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology provides 0 information about rationale documentation."}, {"score"=>2, "explanation"=>"The ontology provides 1 informations about rationale documentation."}, {"score"=>4, "explanation"=>"The ontology provides 2 informations about rationale documentation."}]</td>
            <td>["omv:designedForOntologyTask", "mod:competencyQuestion"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Does an ontology inform on its funding organization?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology does not inform about its funding organization."}, {"score"=>2, "explanation"=>"The ontology informs about its funding organization."}]</td>
            <td>["foaf:fundedBy"]</td>
            <td>implemented</td>
         </tr>
    <tr>
         <td rowspan="3">
              R13: Ontologies and ontology metadata meet domain-relevant community standards.
              <ul>
                <li>Max Credits : 36</li>
                <li>Portal max Credits : 36</li>
              </ul>
          </td>
         <td>Does an ontology provide information about projects using or organization endorsing?</td>
         <td>[{"score"=>0, "explanation"=>"The ontology does not provide information about projects and endorsing organizations."}, {"score"=>5, "explanation"=>"The ontology provide information about projects or endorsing organizations."}, {"score"=>10, "explanation"=>"The ontology provide information about projects and endorsing organizations"}]</td>
      </tr>
      <tr>
            <td>Is the ontology included in a specific community set or group?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology does not provide information about its community set or group."}, {"score"=>10, "explanation"=>"The ontology is included in one of this groups: \"WHEAT\", \"CROP\", \"INRAE\"."}, {"score"=>15, "explanation"=>"The ontology is included in the OBO library."}, {"score"=>20, "explanation"=>"The ontology is included in the OBO library and is part of the OBO foundry."}]</td>
            <td>["mod:group"]</td>
            <td>implemented</td>
         </tr><tr>
            <td>Is the ontology openly and freely available?</td>
            <td>[{"score"=>0, "explanation"=>"The ontology is not openly and freely available."}, {"score"=>6, "explanation"=>"The ontology is openly and freely available."}]</td>
            <td>["dct:accessRights"]</td>
            <td>implemented</td>
         </tr>
     </table>
 
