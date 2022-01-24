 ## Findable
 <table>
    <tr>
        <th>Sub Principles </th>
        <th>Questions</th>
        <th>Used properties</th>
    </tr>    <tr>
         <td rowspan="4">
              F1: Ontologies and ontology metadata are assigned a globally unique and persistent identifier.              <ul>
                <li>Max Credits : 41</li>
                <li>Portal max Credits : 41</li>
              </ul>
          </td>         <td>
            <p>Does the ontology have a "local" identifier, i.e., a globally unique and potentially identifier assigned by the developer (or developing organization)?</p>
            <p>
              Responses :                    <ul>                        <li>The Ontology URI is not present. (score: 0)</li>                        <li>The ontology URI is present but invalid. (score: 3)</li>                        <li>The ontology URI is present and valid. (score: 9)</li>                    </ul>            </p>
         </td>
         <td>                    <ul>                          <li>owl:ontologyIRI</li>                    </ul>         </td>    </tr>    <tr>
            <td>
                 <p>Does the ontology provide an additional "external" identifier, i.e., a guarantee globally unique and persistent identifier assigned by an accredited body? If yes, is the external identifier a DOI?</p>
                  <p>
                    Responses :                          <ul>                              <li>The external identifier is not present and invalid. (score: 0)</li>                              <li>The external identifier is present but invalid. (score: 3)</li>                              <li>The external identifier is valid but is not a DOI. (score: 6)</li>                              <li>The external DOI identifier is invalid. (score: 9)</li>                              <li>The external DOI identifier is valid. (score: 11)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>dct:identifier</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Are the ontology metadata clearly identified either by the same identifier than the ontology (if included in the ontology file) or with its own globally unique and persistent identifier?</p>
                  <p>
                    Responses :                          <ul>                              <li>The repository makes explicit relation between metadata and ontology. (score: 12)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
            <td>
                 <p>Does the ontology provide a version-specific URI, and is this URI resolvable?</p>
                  <p>
                    Responses :                          <ul>                              <li>The version URI is not present. (score: 0)</li>                              <li>The version URI is present but invalid. (score: 2)</li>                              <li>The version URI is present but not resolvable. (score: 4)</li>                              <li>The version URI is present, valid and resolvable. (score: 9)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>owl:versionIRI</li>                    </ul>            </td>
    </tr>    <tr>
         <td rowspan="3">
              F2: Ontologies are described with rich ontology metadata.
              <ul>
                <li>Max Credits : 27</li>
                <li>Portal max Credits : 27</li>
              </ul>
          </td>         <td>
            <p>Is the ontology described with additional 'MIRO must' metadata properties? </p>
            <p>
              Responses :                    <ul>                        <li>The ontology describes 0 'MIRO must' properties. (score: 0)</li>                        <li>The ontology describes 1 'MIRO must' properties. (score: 2)</li>                        <li>The ontology describes 2 'MIRO must' properties. (score: 4)</li>                        <li>The ontology describes 3 'MIRO must' properties. (score: 6)</li>                        <li>The ontology describes 4 'MIRO must' properties. (score: 8)</li>                        <li>The ontology describes 5 'MIRO must' properties. (score: 10)</li>                        <li>The ontology describes 6 'MIRO must' properties. (score: 12)</li>                        <li>The ontology describes 7 'MIRO must' properties. (score: 14)</li>                        <li>The ontology describes 8 or more 'MIRO must' properties. (score: 16)</li>                    </ul>            </p>
         </td>
         <td>                    <ul>                          <li>omv:acronym</li>                          <li>dct:title</li>                          <li>dct:alternative</li>                          <li>skos:hiddenLabel</li>                          <li>dct:description</li>                          <li>foaf:page</li>                          <li>omv:resourceLocator</li>                          <li>omv:keywords</li>                          <li>dct:coverage</li>                          <li>foaf:homepage</li>                          <li>vann:example</li>                          <li>vann:preferredNamespaceUri</li>                          <li>void:uriRegexPattern</li>                          <li>idot:exampleIdentifier</li>                          <li>dct:publisher</li>                          <li>dct:subject</li>                          <li>owl:backwardCompatibleWith</li>                          <li>door:comesFromTheSameDomain</li>                          <li>omv:knownUsage</li>                          <li>dct:audience</li>                          <li>doap:repository</li>                          <li>doap:bugDatabase</li>                          <li>doap:mailing-list</li>                          <li>mod:hasEvaluation</li>                    </ul>         </td>    </tr>    <tr>
            <td>
                 <p>Is the ontology described with additional 'MIRO should' or 'optional' metadata properties?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology describes 0 'should or optional' properties. (score: 0)</li>                              <li>The ontology describes 1 'should or optional' properties. (score: 1)</li>                              <li>The ontology describes 2 'should or optional' properties. (score: 2)</li>                              <li>The ontology describes 3 'should or optional' properties. (score: 3)</li>                              <li>The ontology describes 4 'should or optional' properties. (score: 3)</li>                              <li>The ontology describes 5 'should or optional' properties. (score: 4)</li>                              <li>The ontology describes 6 or more 'should or optional' properties. (score: 4)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>mod:metrics</li>                         <li>omv:numberOfClasses</li>                         <li>omv:numberOfIndividuals</li>                         <li>omv:numberOfProperties</li>                         <li>omv:numberOfAxioms</li>                         <li>vann:preferredNamespacePrefix</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Is the ontology described with another metadata property with no explicit corresponding MIRO requirement?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology describes 0 'no MIRO' properties. (score: 0)</li>                              <li>The ontology describes 1 'no MIRO' properties. (score: 1)</li>                              <li>The ontology describes 2 'no MIRO' properties. (score: 2)</li>                              <li>The ontology describes 3 'no MIRO' properties. (score: 3)</li>                              <li>The ontology describes 4 'no MIRO' properties. (score: 4)</li>                              <li>The ontology describes 5 'no MIRO' properties. (score: 5)</li>                              <li>The ontology describes 6 'no MIRO' properties. (score: 6)</li>                              <li>The ontology describes 7 or more  'no MIRO' properties. (score: 7)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>dct:language</li>                         <li>dct:abstract</li>                         <li>mod:analytics</li>                         <li>dct:bibliographicCitation</li>                         <li>rdfs:comment</li>                         <li>foaf:depiction</li>                         <li>foaf:logo</li>                         <li>voaf:toDoList</li>                         <li>schema:award</li>                         <li>schema:associatedMedia</li>                         <li>owl:incompatibleWith</li>                         <li>dct:hasPart</li>                         <li>schema:workTranslation</li>                         <li>door:hasDisparateModelling</li>                         <li>voaf:usedBy</li>                         <li>voaf:hasDisjunctionsWith</li>                         <li>omv:keyClasses</li>                         <li>void:rootResource</li>                         <li>mod:browsingUI</li>                         <li>void:propertyPartition</li>                         <li>void;classPartition</li>                         <li>void:dataDump</li>                         <li>void:openSearchDescription</li>                         <li>void:uriLookupEndpoint</li>                         <li>schema:comment</li>                         <li>dct:created</li>                         <li>dct:modified</li>                         <li>dct:valid</li>                         <li>dct:dateSubmitted</li>                         <li>pav:curatedOn</li>                         <li>omv:isOfType</li>                         <li>void:propertyPartition</li>                         <li>void:classPartition</li>                         <li>mod:classesWithMoreThan25Children</li>                         <li>mod:classesWithOneChild</li>                         <li>mod:classesWithNoDefinition</li>                         <li>mod:maxChildCount</li>                    </ul>            </td>
    </tr>    <tr>
         <td rowspan="3">
              F3: Ontology metadata clearly and explicitly include the identifier of the ontology they describe.
              <ul>
                <li>Max Credits : 21</li>
                <li>Portal max Credits : 21</li>
              </ul>
          </td>         <td>
            <p>Are the ontology metadata included and maintained in the ontology file?</p>
            <p>
              Responses :                    <ul>                        <li> (score: 21)</li>                    </ul>            </p>
         </td>
         <td>                    None         </td>    </tr>    <tr>
            <td>
                 <p>If not, are the ontology metadata described in an external file?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology repository provides ontology metadata in an external file. (score: 11)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
            <td>
                 <p>Does that external file explicitly link to the ontology and vice-versa?</p>
                  <p>
                    Responses :                          <ul>                              <li> No explicit link from metadata to the ontology. (score: 0)</li>                              <li>It exist a link only from one direction (form ontology to metadata or the inverse). (score: 5)</li>                              <li>It exist a bidirectional link between the ontology and it's metadata. (score: 10)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
         <td rowspan="3">
              F4: Ontologies and ontology metadata are registered or indexed in a searchable resource typically an ontology repository.
              <ul>
                <li>Max Credits : 24</li>
                <li>Portal max Credits : 16</li>
              </ul>
          </td>         <td>
            <p>Is the ontology registered in multiple ontology 'libraries' ?</p>
            <p>
              Responses :                    <ul>                        <li>The ontology is registered in 0 ontology library. (score: 0)</li>                        <li>The ontology is registered in 1 ontology library. (score: 2)</li>                        <li>The ontology is registered in  2 ontology libraries. (score: 4)</li>                        <li>The ontology is registered in 3 or more ontology libraries. (score: 6)</li>                    </ul>            </p>
         </td>
         <td>                    <ul>                          <li>schema:includedInDataCatalog</li>                    </ul>         </td>    </tr>    <tr>
            <td>
                 <p>Is the ontology registered in multiple open ontology 'repositories' ?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology is registered in 0 ontology repository. (score: 0)</li>                              <li>The ontology is registered in 1 ontology repository. (score: 2)</li>                              <li>The ontology is registered in 2 ontology repositories. (score: 4)</li>                              <li>The ontology is registered in 3  ontology repositories. (score: 6)</li>                              <li>The ontology is registered in 4  ontology repositories. (score: 8)</li>                              <li>The ontology is registered in 5 or more ontology repositories. (score: 10)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>schema:includedInDataCatalog</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Are the ontology libraries or repositories properly indexed by Web search engines?</p>
                  <p>
                    Responses :                          <ul>                              <li> (score: 8)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr></table> ## Accessible
 <table>
    <tr>
        <th>Sub Principles </th>
        <th>Questions</th>
        <th>Used properties</th>
    </tr>    <tr>
         <td rowspan="4">
              A1: Ontologies and ontology metadata are retrievable by their identifier using a standardized communication protocol.
              <ul>
                <li>Max Credits : 43</li>
                <li>Portal max Credits : 43</li>
              </ul>
          </td>         <td>
            <p>Does the ontology URI and other identifiers, if exist, resolve to the ontology?</p>
            <p>
              Responses :                    <ul>                        <li>The ontology have 0 resolvable identifier. (score: 0)</li>                        <li>The ontology have 1 resolvable identifier. (score: 3)</li>                        <li>The ontology have 2 or more resolvable identifier. (score: 6)</li>                    </ul>            </p>
         </td>
         <td>                    <ul>                          <li>owl:ontologyIRI</li>                          <li>dct:identifier</li>                    </ul>         </td>    </tr>    <tr>
            <td>
                 <p>Does the ontology URI (if metadata is included in the ontology file) or the external metadata URI resolve to the metadata record?</p>
                  <p>
                    Responses :                          <ul>                              <li>The repository provides an external metadata URI which resolves to the metadata record (score: 7)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
            <td>
                 <p>Do the ontology URI and the external metadata URI (if the metadata are not included in the ontology file), support content negotiation?</p>
                  <p>
                    Responses :                          <ul>                              <li>Ontology and its metadata does not support content negotiation. (score: 0)</li>                              <li>Ontology or its metadata support together 1  format. (score: 3)</li>                              <li>Ontology and its metadata support together 2 formats. (score: 6)</li>                              <li>Ontology and its metadata support together 3 formats. (score: 9)</li>                              <li>Ontology and its metadata support together 4 formats. (score: 12)</li>                              <li>Ontology and its metadata support together 5 formats. (score: 15)</li>                              <li>Ontology and its metadata support together 6 formats. (score: 18)</li>                              <li>Ontology and its metadata support together 7 formats. (score: 21)</li>                              <li>Ontology and its metadata support together 8 or more formats. (score: 24)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>owl:ontologyIRI</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Are the ontology and its metadata accessible through another standard protocol such as SPARQL?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology is not accessible through a SPARQL endpoint. (score: 0)</li>                              <li>Ontology is not accessible through SPARQL endpoint. (score: 6)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>sd:endpoint</li>                    </ul>            </td>
    </tr>    <tr>
         <td rowspan="3">
              A1.1: The protocol to retrieve ontologies and ontology metadata is open, free and universally implementable.
              <ul>
                <li>Max Credits : 28</li>
                <li>Portal max Credits : 28</li>
              </ul>
          </td>         <td>
            <p>Is the ontology relying on HTTP/URIs for its identification and access mechanisms?</p>
            <p>
              Responses :                    <ul>                        <li>The repository supports HTTP for access and URI for identification. (score: 20)</li>                    </ul>            </p>
         </td>
         <td>                    None         </td>    </tr>    <tr>
            <td>
                 <p>If the ontology is accessible through another protocol, is that protocol open, free, and universally implementable?</p>
                  <p>
                    Responses :                          <ul>                              <li>The repository is relying on protocols that are open, free and universally implementable. (score: 4)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
            <td>
                 <p> If the ontology metadata are accessible through another protocol, is that protocol open, free, and universally implementable?</p>
                  <p>
                    Responses :                          <ul>                              <li>The repository is relying on protocols that are open, free and universally implementable. (score: 4)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
         <td rowspan="2">
              A1.2: The protocol to retrieve ontologies and ontology metadata support authentification and authorization when an ontology has access restriction.
              <ul>
                <li>Max Credits : 22</li>
                <li>Portal max Credits : 22</li>
              </ul>
          </td>         <td>
            <p>Is the ontology accessible through a protocol that supports authentication and authorization?</p>
            <p>
              Responses :                    <ul>                        <li>The repository supports authentification and authorization. (score: 11)</li>                    </ul>            </p>
         </td>
         <td>                    None         </td>    </tr>    <tr>
            <td>
                 <p>Are the ontology metadata accessible through a protocol that supports authentification and authorization?</p>
                  <p>
                    Responses :                          <ul>                              <li>The repository supports authentification and authorization. (score: 11)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
         <td rowspan="4">
              A2: Ontology metadata should be accessible even when the ontology is no longer available.
              <ul>
                <li>Max Credits : 20</li>
                <li>Portal max Credits : 20</li>
              </ul>
          </td>         <td>
            <p>Is the ontology accessible in a repository that supports versioning?</p>
            <p>
              Responses :                    <ul>                        <li>The repository supports versioning. (score: 7)</li>                    </ul>            </p>
         </td>
         <td>                    None         </td>    </tr>    <tr>
            <td>
                 <p>Are the ontology metadata of each version available?</p>
                  <p>
                    Responses :                          <ul>                              <li>The repository provides metadata for each version. (score: 5)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
            <td>
                 <p>Are the ontology metadata accessible even if no more versions of the ontology are available?</p>
                  <p>
                    Responses :                          <ul>                              <li>The repository supports accessibility even if no more versions are available. (score: 4)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
            <td>
                 <p>Is the status of the ontology clearly informed?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology status is not specified. (score: 0)</li>                              <li>The ontology status is partially specified. (score: 2)</li>                              <li>The ontology status is fully specified. (score: 4)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>omv:status</li>                         <li>owl:deprecated</li>                    </ul>            </td>
    </tr></table> ## Interoperable
 <table>
    <tr>
        <th>Sub Principles </th>
        <th>Questions</th>
        <th>Used properties</th>
    </tr>    <tr>
         <td rowspan="5">
              I1: Ontologies and ontology metadata use a formal, accessible, shared and broadly  applicable language for knowledge representation.              <ul>
                <li>Max Credits : 44</li>
                <li>Portal max Credits : 44</li>
              </ul>
          </td>         <td>
            <p>What is the representation language used for ontology and ontology metadata?</p>
            <p>
              Responses :                    <ul>                        <li>Ontology and ontology metadata are not in a formal, accessible, shared and broadly applicable language. (score: 0)</li>                        <li>Ontology and ontology metadata are represented in PDF. (score: 5)</li>                        <li>Ontology and ontology metadata are represented in TXT. (score: 7)</li>                        <li>Ontology and ontology metadata are represented in CSV. (score: 9)</li>                        <li>Ontology and ontology metadata are represented in XML. (score: 10)</li>                        <li>Ontology and ontology metadata are represented in OBO. (score: 11)</li>                        <li>Ontology and ontology metadata are represented in RDFS. (score: 12)</li>                        <li>Ontology and ontology metadata are represented in SKOS. (score: 15)</li>                        <li>Ontology and ontology metadata are represented in OWL. (score: 18)</li>                    </ul>            </p>
         </td>
         <td>                    <ul>                          <li>omv:hasOntologyLanguage</li>                    </ul>         </td>    </tr>    <tr>
            <td>
                 <p>Is the representation language used a W3C Recommendation?</p>
                  <p>
                    Responses :                          <ul>                              <li>Ontology and ontology metadata are not represented in a W3C language. (score: 0)</li>                              <li>Ontology and ontology metadata are represented in a W3C language. (score: 10)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>omv:hasOntologyLanguage</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Is the syntax of the ontology informed?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology syntax is not informed. (score: 0)</li>                              <li>The ontology syntax is informed. (score: 5)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>omv:hasOntologySyntax</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Is the formality level of the ontology informed?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology formality level is not informed. (score: 0)</li>                              <li>The ontology formality level is informed. (score: 5)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>omv:hasFormalityLevel</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Is the availability of other syntaxes/formats informed?</p>
                  <p>
                    Responses :                          <ul>                              <li>No information is provided about the availability of the ontology in other formats. (score: 0)</li>                              <li>The availability of other ontology formats is informed. (score: 4)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>dct:hasFormat</li>                         <li>dct:isFormatOf</li>                    </ul>            </td>
    </tr>    <tr>
         <td rowspan="7">
              I2: Ontologies and ontology metadata use vocabularies that follow FAIR principles.
              <ul>
                <li>Max Credits : 32</li>
                <li>Portal max Credits : 22</li>
              </ul>
          </td>         <td>
            <p>Does the ontology import other FAIR vocabularies?</p>
            <p>
              Responses :                    <ul>                        <li>The ontology does not import other vocabularies. (score: 0)</li>                        <li>The ontology imports other FAIR vocabularies. (score: 5)</li>                    </ul>            </p>
         </td>
         <td>                    <ul>                          <li>owl:imports</li>                    </ul>         </td>    </tr>    <tr>
            <td>
                 <p>Does the ontology reuse terms from other vocabularies (URIs)?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology does not reuse other vocabularies. (score: 0)</li>                              <li>The ontology reuses terms from other vocabularies. (score: 5)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>dct:relation</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>If yes, does it include the minimum information for those terms?</p>
                  <p>
                    Responses :                          <ul>                              <li> (score: 3)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
            <td>
                 <p>Is the ontology aligned to other vocabularies?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology is not aligned to other vocabularies. (score: 0)</li>                              <li>The ontology is aligned to other vocabularies. (score: 5)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>voaf:hasEquivalencesWith</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>If yes, are those alignments well represented and to unambiguous entities? if yes, are those alignements curated?</p>
                  <p>
                    Responses :                          <ul>                              <li> (score: 7)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
            <td>
                 <p>Does the ontology provide metadata information about relation to or influence of other vocabularies?</p>
                  <p>
                    Responses :                          <ul>                              <li>Ontology does not declare influential relations. (score: 0)</li>                              <li>Ontology declares influential relations. (score: 2)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>voaf:specializes</li>                         <li>schema:translationOfWork</li>                         <li>voaf:similar</li>                         <li>voaf:generalizes</li>                         <li>dct:isPartOf</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Does the ontology reuse standards & FAIR metadata vocabularies to describe its metadata?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology does not reuse standards metadata vocabularies to describe its metadata. (score: 0)</li>                              <li>The ontology reuses standards metadata vocabularies to describe its metadata. (score: 5)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>voaf:metadataVoc</li>                    </ul>            </td>
    </tr>    <tr>
         <td rowspan="3">
              I3: Ontologies or ontology metadata include qualified references to other (meta)data.              <ul>
                <li>Max Credits : 33</li>
                <li>Portal max Credits : 7</li>
              </ul>
          </td>         <td>
            <p>Does an ontology provide qualified cross-references to external resources/databases?</p>
            <p>
              Responses :                    <ul>                        <li> (score: 20)</li>                    </ul>            </p>
         </td>
         <td>                    None         </td>    </tr>    <tr>
            <td>
                 <p>If yes, are those cross-references well represented and to unambiguous entities?</p>
                  <p>
                    Responses :                          <ul>                              <li> (score: 6)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
            <td>
                 <p>Does the ontology use valid URIs to encode some metadata values?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology has at least 7 metadata values encoded as URIs (score: 7)</li>                              <li>The ontology has at least 6 metadata values encoded as URIs (score: 6)</li>                              <li>The ontology has at least 5 metadata values encoded as URIs (score: 5)</li>                              <li>The ontology has at least 4 metadata values encoded as URIs (score: 4)</li>                              <li>The ontology has at least 3 metadata values encoded as URIs (score: 3)</li>                              <li>The ontology has at least 2 metadata values encoded as URIs (score: 2)</li>                              <li>The ontology has at least 1 metadata value encoded as URI (score: 1)</li>                              <li>The ontology does not use URIs to encode metadata values. (score: 0)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>owl:imports</li>                         <li>owl:priorVersion</li>                         <li>owl:backwardCompatibleWith</li>                         <li>owl:incompatibleWith</li>                         <li>dct:isPartOf</li>                         <li>dct:hasPart</li>                         <li>dct:hasVersion</li>                         <li>dct:isFormatOf</li>                         <li>dct:hasFormat</li>                         <li>dct:relation</li>                         <li>dct:relation</li>                         <li>schema:workTranslation</li>                         <li>schema:translationOfWork</li>                         <li>door:comesFromTheSameDomain</li>                         <li>voaf:similar</li>                         <li>voaf:hasEquivalencesWith</li>                         <li>door:hasDisparateModelling</li>                         <li>voaf:usedBy</li>                         <li>voaf:generalizes</li>                         <li>voaf:hasDisjunctionsWith</li>                         <li>omv:hasFormalityLevel</li>                         <li>dct:license</li>                         <li>omv:hasOntologySyntax</li>                         <li>dct:subject</li>                         <li>omv:hasOntologyLanguage</li>                         <li>mod:prefLabelProperty</li>                         <li>mod:definitionProperty</li>                         <li>mod:synonymProperty</li>                         <li>mod:authorProperty</li>                         <li>mod:hierarchyProperty</li>                         <li>mod:obsoleteProperty</li>                    </ul>            </td>
    </tr></table> ## Reusable
 <table>
    <tr>
        <th>Sub Principles </th>
        <th>Questions</th>
        <th>Used properties</th>
    </tr>    <tr>
         <td rowspan="6">
              R1: Ontologies and ontology metadata are richly described with a plurality of accurate and relevant attributes.
              <ul>
                <li>Max Credits : 32</li>
                <li>Portal max Credits : 5</li>
              </ul>
          </td>         <td>
            <p>Does the ontology provide information about how classes or concept are defined?</p>
            <p>
              Responses :                    <ul>                        <li>The ontology provides 0 information about its classes or concept. (score: 0)</li>                        <li>The ontology provides 1 information about its classes or concept. (score: 1)</li>                        <li>The ontology provides 2 informations about its classes or concept. (score: 2)</li>                        <li>The ontology provides 3 informations about its classes or concept. (score: 3)</li>                        <li>The ontology provides 4 informations about its classes or concept. (score: 4)</li>                        <li>The ontology provides 5 or more informations about its classes or concept. (score: 5)</li>                    </ul>            </p>
         </td>
         <td>                    <ul>                          <li>mod:prefLabelProperty</li>                          <li>mod:synonymProperty</li>                          <li>mod:definitionProperty</li>                          <li>mod:authorProperty</li>                          <li>mod:obsoleteProperty</li>                    </ul>         </td>    </tr>    <tr>
            <td>
                 <p>Does the ontology provide information about its hierarchy?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology provides 0 information about its hierarchy. (score: 0)</li>                              <li>The ontology provides 1 information about its hierarchy. (score: 1)</li>                              <li>The ontology provides 2 informations about its hierarchy. (score: 2)</li>                              <li>The ontology provides 2 or more informations about its hierarchy. (score: 3)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>mod:hierarchyProperty</li>                         <li>mod:obsoleteParent</li>                         <li>mod:maxDepth</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>How much of the ontology objects are described with labels?</p>
                  <p>
                    Responses :                          <ul>                              <li> (score: 7)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
            <td>
                 <p>How much of the ontology objects are defined using a text description?</p>
                  <p>
                    Responses :                          <ul>                              <li> (score: 6)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
            <td>
                 <p>How much ontology objects are defined using a property restriction or an equivalent class?</p>
                  <p>
                    Responses :                          <ul>                              <li> (score: 6)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
            <td>
                 <p>How much of the ontology objects provide provenance information with annotation properties? </p>
                  <p>
                    Responses :                          <ul>                              <li> (score: 5)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
         <td rowspan="3">
              R1.1: Ontologies and ontology metadata are released with a clear and accessible  usage license.
              <ul>
                <li>Max Credits : 37</li>
                <li>Portal max Credits : 37</li>
              </ul>
          </td>         <td>
            <p>Is the ontology license clearly specified, with a URI that is resolvable ans supports content negotiation?</p>
            <p>
              Responses :                    <ul>                        <li>The ontology license is not specified. (score: 0)</li>                        <li>The ontology license is specified but not valid URI. (score: 4)</li>                        <li>The ontology license is valid but not resolvable. (score: 8)</li>                        <li>The ontology license is resolvable but not content negotiable. (score: 12)</li>                        <li>The ontology license is clearly specified. (score: 15)</li>                    </ul>            </p>
         </td>
         <td>                    <ul>                          <li>dct:license</li>                    </ul>         </td>    </tr>    <tr>
            <td>
                 <p>Are the ontology access rights specified and permissions documented?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology access rights and permissions are not specified. (score: 0)</li>                              <li>The ontology access rights are clearly specified. (score: 4)</li>                              <li>The ontology access rights are clearly specified and permissions are documented. (score: 7)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>dct:accessRights</li>                         <li>cc:morePermissions</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Are the ontology usage guidelines and copyright holder documented?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology documents 2 properties about usage guidelines and copyright holder. (score: 15)</li>                              <li>The ontology documents 1 property about usage guidelines and copyright holder. (score: 7)</li>                              <li>Neither ontology usage guidelines nor copyright holder are documented. (score: 0)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>cc:useGuidelines</li>                         <li>dct:rightsHolder</li>                    </ul>            </td>
    </tr>    <tr>
         <td rowspan="8">
              R1.2: Ontologies and ontology metadata are associated with detailed provenance.
              <ul>
                <li>Max Credits : 38</li>
                <li>Portal max Credits : 36</li>
              </ul>
          </td>         <td>
            <p>Does the ontology provide information about the actors involved in its development?</p>
            <p>
              Responses :                    <ul>                        <li>The ontology provide 0 information about the actors involved in its development. (score: 0)</li>                        <li>The ontology provide 1 information about the actors involved in its development. (score: 3)</li>                        <li>The ontology provide 2 informations about the actors involved in its development. (score: 5)</li>                        <li>The ontology provide 3 informations about the actors involved in its development. (score: 7)</li>                        <li>The ontology provide 4 or more informations about the actors involved in its development.  (score: 8)</li>                    </ul>            </p>
         </td>
         <td>                    <ul>                          <li>dct:creator</li>                          <li>pav:curatedBy</li>                          <li>dct:contributor</li>                          <li>schema:translator</li>                    </ul>         </td>    </tr>    <tr>
            <td>
                 <p>Does an ontology provide information on its general provenance?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology provide O information on its general provenance. (score: 0)</li>                              <li>The ontology provide 1 information on its general provenance. (score: 2)</li>                              <li>The ontology provide 2 informations on its general provenance. (score: 4)</li>                              <li>The ontology provide 3 or more informations on its general provenance. (score: 6)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>dct:source</li>                         <li>prov:wasGeneratedBy</li>                         <li>prov:wasInvalidatedBy</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Are the accrual methods and policy of the ontology documented?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology provide 0 accrual information. (score: 0)</li>                              <li>The ontology provide 1 accrual information. (score: 2)</li>                              <li>The ontology provide 2 accrual informations. (score: 4)</li>                              <li>The ontology provide 3 or more accrual informations. (score: 6)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>dct:accrualMethod</li>                         <li>dct:accrualPeriodicity</li>                         <li>dct:accrualPolicy</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Is the ontology clearly versioned with version information and links to previous versions?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology provide O information about versioning. (score: 0)</li>                              <li>The ontology provide 1 information about versioning. (score: 2)</li>                              <li>The ontology provide 2 informations about versioning. (score: 4)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>owl:versionInfo</li>                         <li>owl:priorVersion</li>                         <li>dct:hasVersion</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Are the ontology latest change documented?</p>
                  <p>
                    Responses :                          <ul>                              <li> (score: 2)</li>                          </ul>                  </p>
            </td>
            <td>                    None            </td>
    </tr>    <tr>
            <td>
                 <p>Are the methodology and tools used to build the ontology documented?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology provides 0 information about methodology and tools used to build it. (score: 0)</li>                              <li>The ontology provides 2 informations about methodology and tools used to build it. (score: 2)</li>                              <li>The ontology provides 4 informations about methodology and tools used to build it. (score: 4)</li>                              <li>The ontology provides 6 informations about methodology and tools used to build it. (score: 6)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>omv:usedOntologyEngineeringTool</li>                         <li>omv:usedOntologyEngineeringMethodology</li>                         <li>omv:conformsToKnowledgeRepresentationParadigm</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Is the ontology rationale documented?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology provides 0 information about rationale documentation. (score: 0)</li>                              <li>The ontology provides 1 informations about rationale documentation. (score: 2)</li>                              <li>The ontology provides 2 informations about rationale documentation. (score: 4)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>omv:designedForOntologyTask</li>                         <li>mod:competencyQuestion</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Does an ontology inform about its funding organization?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology does not inform about its funding organization. (score: 0)</li>                              <li>The ontology informs about its funding organization. (score: 2)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>foaf:fundedBy</li>                    </ul>            </td>
    </tr>    <tr>
         <td rowspan="3">
              R1.3: Ontologies and ontology metadata meet domain-relevant community standards.
              <ul>
                <li>Max Credits : 36</li>
                <li>Portal max Credits : 36</li>
              </ul>
          </td>         <td>
            <p>Does an ontology provide information about projects using or organization endorsing?</p>
            <p>
              Responses :                    <ul>                        <li>The ontology does not provide information about projects and endorsing organizations. (score: 0)</li>                        <li>The ontology provide information about projects or endorsing organizations. (score: 5)</li>                        <li>The ontology provide information about projects and endorsing organizations (score: 10)</li>                    </ul>            </p>
         </td>
         <td>                    <ul>                          <li>mod:ontologyInUse</li>                          <li>omv:endorsedBy</li>                    </ul>         </td>    </tr>    <tr>
            <td>
                 <p>Is the ontology included in a specific community set or group?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology does not provide information about its community set or group. (score: 0)</li>                              <li>The ontology is included in one of this groups: "WHEAT", "CROP", "INRAE". (score: 10)</li>                              <li>The ontology is included in the OBO library. (score: 15)</li>                              <li>The ontology is included in the OBO library and is part of the OBO foundry. (score: 20)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>mod:group</li>                    </ul>            </td>
    </tr>    <tr>
            <td>
                 <p>Is the ontology openly and freely available?</p>
                  <p>
                    Responses :                          <ul>                              <li>The ontology is not openly and freely available. (score: 0)</li>                              <li>The ontology is openly and freely available. (score: 6)</li>                          </ul>                  </p>
            </td>
            <td>                    <ul>                         <li>dct:accessRights</li>                    </ul>            </td>
    </tr></table>