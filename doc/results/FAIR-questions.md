
  
  
  
 ## Findable  
 <table>  
    <tr>  
        <th>Sub Principles </th>  
        <th>Questions</th>  
        <th>Used properties</th>  
        <th>State</th>  
    </tr>  
      
 <tr> <td rowspan="4"> F1: Ontologies and ontology metadata are assigned a globally unique and persistent identifier.              <ul>  
 <li>Max Credits : 41</li> <li>Portal max Credits : 41</li> </ul> </td>          <td>  
 <p>Does an ontology have a "local" identifier i.e., a globally unique and potentially persistent identifier assigned by the developer (or developing organization)?</p> <p> Responses :                     <ul>  
                         <li>The Ontology URI is not present. (score: 0)</li>  
                         <li>The ontology URI is present but invalid. (score: 3)</li>  
                         <li>The ontology URI is present and valid. (score: 9)</li>  
                     </ul>  
             </p>  
 </td> <td>                     <ul>  
                           <li>owl:ontologyIRI</li>  
                     </ul>  
          </td>  
 <td>implemented</td>  
 </tr>     <tr>  
 <td> <p>Does an ontology provide an additional "external" identifier i.e., a guarantee globally unique and persistent identifier assigned by an accredited body?  If yes, is the external identifier a DOI?</p> <p> Responses :                           <ul>  
                               <li>The external identifier is not present and invalid. (score: 0)</li>  
                               <li>The external identifier is present but invalid. (score: 3)</li>  
                               <li>The external identifier is valid but is not a DOI. (score: 6)</li>  
                               <li>The external DOI identifier is invalid. (score: 9)</li>  
                               <li>The external DOI identifier is valid. (score: 11)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>dct:identifier</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Are the ontology metadata included in the ontology file- and consequently share the same identifiers or is the metadata record clearly identified by its own URI.</p> <p> Responses :                           <ul>  
                               <li>The repository makes explicit relation between metadata and ontology. (score: 12)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
 <tr> <td> <p>Does an ontology provide a version specific URI and is this URI resolvable/ dereferenceable?</p> <p> Responses :                           <ul>  
                               <li>The version URI is not present. (score: 0)</li>  
                               <li>The version URI is present but invalid. (score: 2)</li>  
                               <li>The version URI is present but not resolvable. (score: 4)</li>  
                               <li>The version URI is present, valid and resolvable. (score: 9)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>owl:versionIRI</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
     <tr>  
 <td rowspan="4"> F2: Ontologies are described with rich ontology metadata. <ul> <li>Max Credits : 27</li> <li>Portal max Credits : 27</li> </ul> </td>          <td>  
 <p>Is an ontology described with additional 'MIRO must' metadata properties? </p> <p> Responses :                     <ul>  
                         <li>no MIRO must property found. (score: 0)</li>  
                         <li>1 MIRO must property found. (score: 4)</li>  
                         <li>2 MIRO must properties found. (score: 8)</li>  
                         <li>3 MIRO must properties found. (score: 12)</li>  
                         <li>4 or more MIRO must properties found. (score: 16)</li>  
                     </ul>  
             </p>  
 </td> <td>                     <ul>  
                           <li>omv:acronym</li>  
                           <li>dct:title</li>  
                           <li>dct:alternative</li>  
                           <li>skos:hiddenLabel</li>  
                           <li>dct:description</li>  
                           <li>foaf:page</li>  
                           <li>omv:resourceLocator</li>  
                           <li>omv:keywords</li>  
                           <li>dct:coverage</li>  
                           <li>foaf:homepage</li>  
                           <li>vann:example</li>  
                           <li>vann:preferredNamespaceUri</li>  
                           <li>void:uriRegexPattern</li>  
                           <li>idot:exampleIdentifier</li>  
                           <li>dct:publisher</li>  
                           <li>dct:subject</li>  
                           <li>owl:backwardCompatibleWith</li>  
                           <li>door:comesFromTheSameDomain</li>  
                           <li>mod:sampleQueries</li>  
                           <li>void:uriLookuPEndscore</li>  
                           <li>omv:knownUsage</li>  
                           <li>dct:audience</li>  
                           <li>doap:repository</li>  
                           <li>doap:bugDatabase</li>  
                           <li>doap:mailing-list</li>  
                           <li>mod:hasEvaluation</li>  
                     </ul>  
          </td>  
 <td>implemented</td>  
 </tr>     <tr>  
 <td> <p>Is an ontology described with additional 'MIRO should' metadata properties? </p> <p> Responses :                           <ul>  
                               <li>The ontology describes 0 'MIRO should' properties. (score: 0)</li>  
                               <li>The ontology describes 1 'MIRO should' properties. (score: 2)</li>  
                               <li>The ontology describes 2 'MIRO should' properties. (score: 4)</li>  
                               <li>The ontology describes 3 or more 'MIRO should' properties. (score: 5)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>mod:metrics</li>  
                          <li>omv:numberOfClasses</li>  
                          <li>omv:numberOfIndividuals</li>  
                          <li>omv:numberOfProperties</li>  
                          <li>omv:numberOfAxioms</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Is an ontology described with additional 'MIRO optional' metadata properties?</p> <p> Responses :                           <ul>  
                               <li>The ontology describes 0 'MIRO optional' properties. (score: 0)</li>  
                               <li>The ontology describes 1 'MIRO optional' properties. (score: 1.5)</li>  
                               <li>The ontology describes 2 or more 'MIRO optional' properties. (score: 2)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>vann:preferredNamespacePrefix</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Is an ontology described with another metadata property with no explicit corresponding MIRO requirement?</p> <p> Responses :                           <ul>  
                               <li>The ontology describes 0 'no MIRO' properties. (score: 0)</li>  
                               <li>The ontology describes 1 'no MIRO' properties. (score: 1)</li>  
                               <li>The ontology describes 2 'no MIRO' properties. (score: 2)</li>  
                               <li>The ontology describes 3 or more  'no MIRO' properties. (score: 3)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>dct:language</li>  
                          <li>dct:abstract</li>  
                          <li>mod:analytics</li>  
                          <li>dct:bibliographicCitation</li>  
                          <li>rdfs:comment</li>  
                          <li>foaf:depiction</li>  
                          <li>foaf:logo</li>  
                          <li>voaf:toDoList</li>  
                          <li>schema:award</li>  
                          <li>schema:associatedMedia</li>  
                          <li>owl:isIncompatibleWith</li>  
                          <li>dct:hasPart</li>  
                          <li>schema:workTranslation</li>  
                          <li>door:hasDisparateModelling</li>  
                          <li>voaf:usedBy</li>  
                          <li>voaf:hasDisjunctionsWith</li>  
                          <li>omv:keyClasses</li>  
                          <li> void:rootResource</li>  
                          <li>mod:browsingUI</li>  
                          <li>mod:sampleQueries</li>  
                          <li>void:propertyPartition</li>  
                          <li>void;classPartition</li>  
                          <li>void:dataDump</li>  
                          <li>void:openSearchDescription</li>  
                          <li>void:uriLookupEndpoint</li>  
                          <li>schema:comments</li>  
                          <li>dct:created</li>  
                          <li>dct:modified</li>  
                          <li>dct:valid</li>  
                          <li>dct:dateSubmitted</li>  
                          <li>pav:curatedOn</li>  
                          <li>omv:IsOfType</li>  
                          <li>void:propertyPartition</li>  
                          <li>void:classPartition</li>  
                          <li>schema:comment</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
     <tr>  
 <td rowspan="3"> F3: Ontology metadata clearly and explicitly include the identifier of the ontology they describe. <ul> <li>Max Credits : 21</li> <li>Portal max Credits : 16</li> </ul> </td>          <td>  
 <p>Are the ontology metadata included and maintained in the ontology file?</p> <p> Responses :                     <ul>  
                         <li> (score: 21)</li>  
                     </ul>  
             </p>  
 </td> <td>                     None  
          </td>  
 <td>not implemented</td>  
 </tr>     <tr>  
 <td> <p>If not, are the ontology metadata described in an external file?</p> <p> Responses :                           <ul>  
                               <li>The ontology repository provides ontology metadata in an external file. (score: 11)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
 <tr> <td> <p>Does that external file explicitly link to the ontology and vice-versa?</p> <p> Responses :                           <ul>  
                               <li> No explicit link from metadata to the ontology. (score: 0)</li>  
                               <li>It exist a link only from one direction (form ontology to metadata or the inverse). (score: 5)</li>  
                               <li>It exist a bidirectional link between the ontology and it's metadata. (score: 10)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
     <tr>  
 <td rowspan="3"> F4: Ontologies and ontology metadata are registered or indexed in a searchable resource typically an ontology repository. <ul> <li>Max Credits : 20</li> <li>Portal max Credits : 16</li> </ul> </td>          <td>  
 <p>Is the ontology registered in multiple ontology 'libraries' ?</p> <p> Responses :                     <ul>  
                         <li>The ontology is registered in 0 ontology 'libraries'. (score: 0)</li>  
                         <li>The ontology is registered in 1 ontology 'libraries'. (score: 2)</li>  
                         <li>The ontology is registered in  2 ontology 'libraries'. (score: 4)</li>  
                         <li>The ontology is registered in 3 or more ontology 'libraries'. (score: 6)</li>  
                     </ul>  
             </p>  
 </td> <td>                     <ul>  
                           <li>schema:includedInDataCatalog</li>  
                     </ul>  
          </td>  
 <td>implemented</td>  
 </tr>     <tr>  
 <td> <p>Is the ontology registered in multiple open ontology 'repositories' ?</p> <p> Responses :                           <ul>  
                               <li>The ontology is registered in 0 ontology 'repository'. (score: 0)</li>  
                               <li>The ontology is registered in 1 ontology 'repository'. (score: 2)</li>  
                               <li>The ontology is registered in 2 ontology 'repository'. (score: 4)</li>  
                               <li>The ontology is registered in 3 ontology 'repository'. (score: 6)</li>  
                               <li>The ontology is registered in 4 ontology 'repository'. (score: 8)</li>  
                               <li>The ontology is registered in 5 or more ontology 'repository'. (score: 10)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>schema:includedInDataCatalog</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Are the ontology libraries or repositories properly indexed by Web search engines?</p> <p> Responses :                           <ul>  
                               <li> (score: 8)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
  </table>  
   
  
 ## Accessible  
 <table>  
    <tr>  
        <th>Sub Principles </th>  
        <th>Questions</th>  
        <th>Used properties</th>  
        <th>State</th>  
    </tr>  
      
 <tr> <td rowspan="4"> A1: Ontologies and ontology metadata are retrievable by their identifier using a standardized communication protocol. <ul> <li>Max Credits : 43</li> <li>Portal max Credits : 43</li> </ul> </td>          <td>  
 <p>Does the ontology URI and other identifiers, if exist, resolve to the ontology?</p> <p> Responses :                     <ul>  
                         <li>The ontology have 0 resolvable identifier. (score: 0)</li>  
                         <li>The ontology have 1 resolvable identifier. (score: 3)</li>  
                         <li>The ontology have 2 or more resolvable identifier. (score: 6)</li>  
                     </ul>  
             </p>  
 </td> <td>                     <ul>  
                           <li>owl:ontologyIRI</li>  
                           <li>dct:identifier</li>  
                     </ul>  
          </td>  
 <td>implemented</td>  
 </tr>     <tr>  
 <td> <p>Does the ontology URI (if metadata is included in the ontology file) or the external metadata URI resolve to the metadata record?</p> <p> Responses :                           <ul>  
                               <li>The repository provides an external metadata URI which resolves to the metadata record (score: 7)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
 <tr> <td> <p>Are ontology and its metadata supporting content negotiation?</p> <p> Responses :                           <ul>  
                               <li>Ontology and its metadata does not support content negotiation. (score: 0)</li>  
                               <li>Ontology or its metadata support together 1  format. (score: 6)</li>  
                               <li>Ontology and its metadata support together 2 formats. (score: 12)</li>  
                               <li>Ontology and its metadata support together 3 formats. (score: 18)</li>  
                               <li>Ontology and its metadata support together 4 or more formats. (score: 24)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>owl:ontologyIRI</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Are ontology and its metadata accessible through another standard protocol such as SPARQL?</p> <p> Responses :                           <ul>  
                               <li>The ontology is not accessible through a SPARQL endpoint. (score: 0)</li>  
                               <li>Ontology is not accessible through SPARQL endpoint. (score: 6)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>sd:endpoint</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
     <tr>  
 <td rowspan="3"> A11: The protocol to retrieve ontologies and ontology metadata is open, free and universally implementable. <ul> <li>Max Credits : 28</li> <li>Portal max Credits : 28</li> </ul> </td>          <td>  
 <p>Is the ontology relying on HTTP/URIs for its identification and access mechanisms?</p> <p> Responses :                     <ul>  
                         <li>The repository supports HTTP for access and URI for identification. (score: 20)</li>  
                     </ul>  
             </p>  
 </td> <td>                     None  
          </td>  
 <td>not implemented</td>  
 </tr>     <tr>  
 <td> <p>If the ontology and its metadata are accessible through another protocol, is that protocol open, free and universally implementable?</p> <p> Responses :                           <ul>  
                               <li>The repository is relying on protocols that are open, free and universally implementable. (score: 4)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
 <tr> <td> <p>Is the ontology relying on HTTP/URIs for its identification and access mechanisms?</p> <p> Responses :                           <ul>  
                               <li>The repository is relying on protocols that are open, free and universally implementable. (score: 4)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
     <tr>  
 <td rowspan="2"> A12: The protocol to retrieve ontologies and ontology metadata support authentification and authorization when an ontology has access restriction. <ul> <li>Max Credits : 22</li> <li>Portal max Credits : 22</li> </ul> </td>          <td>  
 <p>Is the ontology accessible through a protocol that supports authentication and authorization?</p> <p> Responses :                     <ul>  
                         <li>The repository supports authentification and authorization. (score: 11)</li>  
                     </ul>  
             </p>  
 </td> <td>                     None  
          </td>  
 <td>not implemented</td>  
 </tr>     <tr>  
 <td> <p>Are the ontology metadata accessible through a protocol that supports authentification and authorization?</p> <p> Responses :                           <ul>  
                               <li>The repository supports authentification and authorization. (score: 11)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
     <tr>  
 <td rowspan="4"> A2: Ontology metadata should be accessible even when the ontology is no longer available. <ul> <li>Max Credits : 20</li> <li>Portal max Credits : 20</li> </ul> </td>          <td>  
 <p>Is the ontology accessible in a repository that supports versioning?</p> <p> Responses :                     <ul>  
                         <li>The repository supports versioning. (score: 7)</li>  
                     </ul>  
             </p>  
 </td> <td>                     None  
          </td>  
 <td>not implemented</td>  
 </tr>     <tr>  
 <td> <p>Are the metadata of each version available?</p> <p> Responses :                           <ul>  
                               <li>The repository provides metadata for each version. (score: 5)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
 <tr> <td> <p>Are ontology metadata accessible even if no more versions of the ontology are available?</p> <p> Responses :                           <ul>  
                               <li>The repository supports accessibility even if no more versions are available. (score: 4)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
 <tr> <td> <p>Is the status of the ontology clearly informed?</p> <p> Responses :                           <ul>  
                               <li>The ontology status is not specified. (score: 0)</li>  
                               <li>The ontology status is clearly specified. (score: 4)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>omv:status</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
  </table>  
   
  
 ## Interoperable  
 <table>  
    <tr>  
        <th>Sub Principles </th>  
        <th>Questions</th>  
        <th>Used properties</th>  
        <th>State</th>  
    </tr>  
      
 <tr> <td rowspan="5"> I1: Ontologies and ontology metadata use a formal, accessible, shared and broadly  applicable language for knowledge representation.              <ul>  
 <li>Max Credits : 44</li> <li>Portal max Credits : 44</li> </ul> </td>          <td>  
 <p>What is the representation language used for ontology and ontology metadata?</p> <p> Responses :                     <ul>  
                         <li>Ontology and ontology metadata are not in a formal, accessible, shared and broadly applicable language. (score: 0)</li>  
                         <li>Ontology and ontology metadata are represented in PDF. (score: 4)</li>  
                         <li>Ontology and ontology metadata are represented in TXT. (score: 5)</li>  
                         <li>Ontology and ontology metadata are represented in CSV. (score: 11)</li>  
                         <li>Ontology and ontology metadata are represented in XML. (score: 12)</li>  
                         <li>Ontology and ontology metadata are represented in OBO. (score: 14)</li>  
                         <li>Ontology and ontology metadata are represented in RDFS. (score: 16)</li>  
                         <li>Ontology and ontology metadata are represented in SKOS. (score: 18)</li>  
                         <li>Ontology and ontology metadata are represented in OWL. (score: 20)</li>  
                     </ul>  
             </p>  
 </td> <td>                     <ul>  
                           <li>omv:hasOntologyLanguage</li>  
                     </ul>  
          </td>  
 <td>implemented</td>  
 </tr>     <tr>  
 <td> <p>Is the representation language used a W3C Recommendations?</p> <p> Responses :                           <ul>  
                               <li>Ontology and ontology metadata are not represented in a W3C language. (score: 0)</li>  
                               <li>Ontology and ontology metadata are represented in a W3C language. (score: 10)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>omv:hasOntologyLanguage</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Is the syntax of the ontology informed?</p> <p> Responses :                           <ul>  
                               <li>The ontology syntax is not informed. (score: 0)</li>  
                               <li>The ontology syntax is informed. (score: 5)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>omv:hasOntologySyntax</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Is the formality level (e.g. domain ontology) of the ontology informed by the author?</p> <p> Responses :                           <ul>  
                               <li>The ontology formality level is not informed. (score: 0)</li>  
                               <li>The ontology formality level is informed. (score: 5)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>omv:hasFormalityLevel</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Is the format of the ontology informed?</p> <p> Responses :                           <ul>  
                               <li>No information is provided about the availability of the ontology in other formats. (score: 0)</li>  
                               <li>The availability of other ontology formats is informed. (score: 4)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>dct:hasFormat</li>  
                          <li>dct:isFormatOf</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
     <tr>  
 <td rowspan="7"> I2: Ontologies and ontology metadata use vocabularies that follow FAIR principles. <ul> <li>Max Credits : 32</li> <li>Portal max Credits : 22</li> </ul> </td>          <td>  
 <p>Does the ontology import other FAIR vocabularies?</p> <p> Responses :                     <ul>  
                         <li>The ontology does not import other vocabularies. (score: 0)</li>  
                         <li>The ontology imports other FAIR vocabularies. (score: 5)</li>  
                     </ul>  
             </p>  
 </td> <td>                     <ul>  
                           <li>owl:imports</li>  
                     </ul>  
          </td>  
 <td>implemented</td>  
 </tr>     <tr>  
 <td> <p>Does the ontology reuse URIs from other vocabularies (URIs)?</p> <p> Responses :                           <ul>  
                               <li>The ontology does not reuse other vocabularies. (score: 0)</li>  
                               <li>The ontology reuses terms from other vocabularies. (score: 5)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>dct:relation</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>If yes, does it include for those termes (e.g. MIREOT)? </p> <p> Responses :                           <ul>  
                               <li> (score: 3)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
 <tr> <td> <p>Is an ontology aligned to other FAIR vocabularies?</p> <p> Responses :                           <ul>  
                               <li>The ontology is not aligned to other vocabularies. (score: 0)</li>  
                               <li>The ontology is aligned to other vocabularies. (score: 5)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>voaf:hasEquivalencesWith</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>If yes, are those alignments well represented and to unambiguous entities? if yes, are those alignements curated?</p> <p> Responses :                           <ul>  
                               <li> (score: 7)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
 <tr> <td> <p>Does an ontology provide metadata information about relation to or influence of other vocabularies?</p> <p> Responses :                           <ul>  
                               <li>Ontology does not declare influential relations. (score: 0)</li>  
                               <li>Ontology declares influential relations. (score: 2)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>voaf:specializes</li>  
                          <li>schema:translationOfWork</li>  
                          <li>voaf:similar</li>  
                          <li>owl:priorVersion</li>  
                          <li>voaf:generalizes</li>  
                          <li>dct:isPartOf</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Does the ontology reuse standard & FAIR metadata vocabularies to describe its metadata?</p> <p> Responses :                           <ul>  
                               <li>The ontology does not reuse standards metadata vocabularies to describe its metadata. (score: 0)</li>  
                               <li>The ontology reuses standards metadata vocabularies to describe its metadata. (score: 5)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>voaf:metadataVoc</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
     <tr>  
 <td rowspan="3"> I3: Ontologies or ontology metadata include qualified references to other (meta)data.              <ul>  
 <li>Max Credits : 33</li> <li>Portal max Credits : 7</li> </ul> </td>          <td>  
 <p>Does an ontology provide qualified cross-references to external resources/databases?</p> <p> Responses :                     <ul>  
                         <li> (score: 20)</li>  
                     </ul>  
             </p>  
 </td> <td>                     None  
          </td>  
 <td>not implemented</td>  
 </tr>     <tr>  
 <td> <p>If yes, are those cross-references well represented and to unambiguous entities?</p> <p> Responses :                           <ul>  
                               <li> (score: 6)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
 <tr> <td> <p>Does the ontology use valid URIs to encode some metadata values?</p> <p> Responses :                           <ul>  
                               <li>The ontology use URIs to encode metadata values. (score: 7)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
  </table>  
   
  
 ## Reusable  
 <table>  
    <tr>  
        <th>Sub Principles </th>  
        <th>Questions</th>  
        <th>Used properties</th>  
        <th>State</th>  
    </tr>  
      
 <tr> <td rowspan="6"> R1: Ontologies and ontology metadata are richly described with a plurality of accurate and relevant attributes. <ul> <li>Max Credits : 32</li> <li>Portal max Credits : 5</li> </ul> </td>          <td>  
 <p>Does the ontology provide information about how classes or concept are defined?</p> <p> Responses :                     <ul>  
                         <li>The ontology provides 0 information about its classes or concept. (score: 0)</li>  
                         <li>The ontology provides 1 information about its classes or concept. (score: 1)</li>  
                         <li>The ontology provides 2 informations about its classes or concept. (score: 2)</li>  
                         <li>The ontology provides 3 informations about its classes or concept. (score: 3)</li>  
                         <li>The ontology provides 4 informations about its classes or concept. (score: 4)</li>  
                         <li>The ontology provides 5 or more informations about its classes or concept. (score: 5)</li>  
                     </ul>  
             </p>  
 </td> <td>                     <ul>  
                           <li>mod:prefLabelProperty</li>  
                           <li>mod:synonymProperty</li>  
                           <li>mod:definitionProperty</li>  
                           <li>mod:authorProperty</li>  
                           <li>bpm:obsoleteProperty</li>  
                     </ul>  
          </td>  
 <td>implemented</td>  
 </tr>     <tr>  
 <td> <p>Does the ontology provide information about its hierarchy?</p> <p> Responses :                           <ul>  
                               <li>The ontology provides 0 information about its hierarchy. (score: 0)</li>  
                               <li>The ontology provides 1 information about its hierarchy. (score: 1)</li>  
                               <li>The ontology provides 2 informations about its hierarchy. (score: 2)</li>  
                               <li>The ontology provides 2 or more informations about its hierarchy. (score: 3)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>mod:hierarchyProperty</li>  
                          <li> mod:obsoleteParent</li>  
                          <li>mod:maxDepth</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>How much of the ontology objects are described with labels?</p> <p> Responses :                           <ul>  
                               <li> (score: 7)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
 <tr> <td> <p>How much of the ontology objects are defined using a text description?</p> <p> Responses :                           <ul>  
                               <li> (score: 6)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
 <tr> <td> <p>How much of the ontology objects are defined using a property restriction (e.g. OWL quantifier, cardinality or has value restrictions) or an equivalent objects (e.g. an OWL named class)?</p> <p> Responses :                           <ul>  
                               <li> (score: 6)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
 <tr> <td> <p>How much of the ontology objects provide provenance information with annotation properties (e.g., author, date)? </p> <p> Responses :                           <ul>  
                               <li> (score: 5)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
     <tr>  
 <td rowspan="3"> R11: Ontologies and ontology metadata are released with a clear and accessible  usage license. <ul> <li>Max Credits : 37</li> <li>Portal max Credits : 37</li> </ul> </td>          <td>  
 <p>Is the ontology license clearly specified, with a URI that is resolvable ans supports content negotiation?</p> <p> Responses :                     <ul>  
                         <li>The ontology license is not specified. (score: 0)</li>  
                         <li>The ontology license is specified but not valid URI. (score: 4)</li>  
                         <li>The ontology license is valid but not resolvable. (score: 8)</li>  
                         <li>The ontology license is resolvable but not content negotiable. (score: 12)</li>  
                         <li>The ontology license is clearly specified. (score: 15)</li>  
                     </ul>  
             </p>  
 </td> <td>                     <ul>  
                           <li>dct:license</li>  
                     </ul>  
          </td>  
 <td>implemented</td>  
 </tr>     <tr>  
 <td> <p>Are the ontology access rights clearly specified?</p> <p> Responses :                           <ul>  
                               <li>The ontology access rights are not clearly specified. (score: 0)</li>  
                               <li>The ontology access rights are clearly specified. (score: 7)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>dct:rightsHolder</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Are the permissions, usage guidelines and copyright holder clearly documented?</p> <p> Responses :                           <ul>  
                               <li>Neither ontology license nor access rights are documented (score: 0)</li>  
                               <li>The ontology document one of them. (score: 5)</li>  
                               <li>The ontology document two of them. (score: 10)</li>  
                               <li>The ontology document the permissions, usage guidelines and copyright holder. (score: 15)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>cc:morePermissions</li>  
                          <li>cc:useGuidelines</li>  
                          <li>dct:rightsHolder</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
     <tr>  
 <td rowspan="8"> R12: Ontologies and ontology metadata are associated with detailed provenance. <ul> <li>Max Credits : 38</li> <li>Portal max Credits : 36</li> </ul> </td>          <td>  
 <p>Does the ontology provide information about the actors involved in its development? (e.g., creator, contributor, curator, translator)</p> <p> Responses :                     <ul>  
                         <li>The ontology provide 0 information about the actors involved in its development. (score: 0)</li>  
                         <li>The ontology provide 1 information about the actors involved in its development. (score: 3)</li>  
                         <li>The ontology provide 2 informations about the actors involved in its development. (score: 5)</li>  
                         <li>The ontology provide 3 informations about the actors involved in its development. (score: 7)</li>  
                         <li>The ontology provide 4 or more informations about the actors involved in its development.  (score: 8)</li>  
                     </ul>  
             </p>  
 </td> <td>                     <ul>  
                           <li>dct:creator</li>  
                           <li>pav:curatedBy</li>  
                           <li>dct:contributor</li>  
                           <li>schema:translator</li>  
                     </ul>  
          </td>  
 <td>implemented</td>  
 </tr>     <tr>  
 <td> <p>Does an ontology provide information on its general provenance? (e.g., source, generated by, invalidated by)</p> <p> Responses :                           <ul>  
                               <li>The ontology provide O information on its general provenance. (score: 0)</li>  
                               <li>The ontology provide 1 information on its general provenance. (score: 2)</li>  
                               <li>The ontology provide 2 informations on its general provenance. (score: 4)</li>  
                               <li>The ontology provide 3 or more informations on its general provenance. (score: 6)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>dct:source</li>  
                          <li>prov:wasGeneratedBy</li>  
                          <li>prov:WasInvalidatedBy</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Are the accrual methods and policy documented? </p> <p> Responses :                           <ul>  
                               <li>The ontology provide 0 accrual information. (score: 0)</li>  
                               <li>The ontology provide 1 accrual information. (score: 2)</li>  
                               <li>The ontology provide 2 accrual informations. (score: 4)</li>  
                               <li>The ontology provide 3 or more accrual informations. (score: 6)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>dct:accrualMethod</li>  
                          <li>dct:accrualPeriodicity</li>  
                          <li>dct:accrualPolicy</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Is the ontology clearly versioned?</p> <p> Responses :                           <ul>  
                               <li>The ontology provide O information about versioning. (score: 0)</li>  
                               <li>The ontology provide 1 information about versioning. (score: 2)</li>  
                               <li>The ontology provide 2 informations about versioning. (score: 4)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>owl:versionInfo</li>  
                          <li>owl:priorVersion</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Are the ontology latest change documented?</p> <p> Responses :                           <ul>  
                               <li> (score: 2)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     None  
             </td>  
 <td>not implemented</td> </tr>  
 <tr> <td> <p>Are the methodology and tools used to build the ontology documented?</p> <p> Responses :                           <ul>  
                               <li>The ontology provides 0 information about methodology and tools used to build it. (score: 0)</li>  
                               <li>The ontology provides 2 informations about methodology and tools used to build it. (score: 2)</li>  
                               <li>The ontology provides 4 informations about methodology and tools used to build it. (score: 4)</li>  
                               <li>The ontology provides 6 informations about methodology and tools used to build it. (score: 6)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>omv:usedOntologyEngineeringTool</li>  
                          <li>omv:usedOntologyEngineeringMethodology</li>  
                          <li>omv:conformsToKnowledgeRepresentationParadigm</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Is the ontology rationale documented?</p> <p> Responses :                           <ul>  
                               <li>The ontology provides 0 information about rationale documentation. (score: 0)</li>  
                               <li>The ontology provides 1 informations about rationale documentation. (score: 2)</li>  
                               <li>The ontology provides 2 informations about rationale documentation. (score: 4)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>omv:designedForOntologyTask</li>  
                          <li>mod:competencyQuestion</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Does an ontology inform on its funding organization?</p> <p> Responses :                           <ul>  
                               <li>The ontology does not inform about its funding organization. (score: 0)</li>  
                               <li>The ontology informs about its funding organization. (score: 2)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>foaf:fundedBy</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
     <tr>  
 <td rowspan="3"> R13: Ontologies and ontology metadata meet domain-relevant community standards. <ul> <li>Max Credits : 36</li> <li>Portal max Credits : 36</li> </ul> </td>          <td>  
 <p>Does an ontology provide information about projects using or organization endorsing?</p> <p> Responses :                     <ul>  
                         <li>The ontology does not provide information about projects and endorsing organizations. (score: 0)</li>  
                         <li>The ontology provide information about projects or endorsing organizations. (score: 5)</li>  
                         <li>The ontology provide information about projects and endorsing organizations (score: 10)</li>  
                     </ul>  
             </p>  
 </td> <td>                     <ul>  
                           <li>mod:ontologyInUse</li>  
                           <li>omv:endorsedBy</li>  
                     </ul>  
          </td>  
 <td>implemented</td>  
 </tr>     <tr>  
 <td> <p>Is the ontology included in a specific community set or group?</p> <p> Responses :                           <ul>  
                               <li>The ontology does not provide information about its community set or group. (score: 0)</li>  
                               <li>The ontology is included in one of this groups: "WHEAT", "CROP", "INRAE". (score: 10)</li>  
                               <li>The ontology is included in the OBO library. (score: 15)</li>  
                               <li>The ontology is included in the OBO library and is part of the OBO foundry. (score: 20)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>mod:group</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
 <tr> <td> <p>Is the ontology openly and freely available?</p> <p> Responses :                           <ul>  
                               <li>The ontology is not openly and freely available. (score: 0)</li>  
                               <li>The ontology is openly and freely available. (score: 6)</li>  
                           </ul>  
                   </p>  
 </td>  
 <td>                     <ul>  
                          <li>dct:accessRights</li>  
                     </ul>  
             </td>  
 <td>implemented</td> </tr>  
  </table>
