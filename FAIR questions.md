# FAIR questions

## FINDABLE (113 credits)

### F1. (Meta)data are assigned a globally unique and persistent identifier.  (41credits)
- Q1. Does an ontology have a “local” identifier i.e., a globally unique and potentially persistent identifier assigned by the developer (or developing organization)? 3 credits
- Q2. If yes, is this identifier a resolvable/dereferenceable URI? 6 credits 	
- Q3. Does an ontology provide an additional “external” identifier i.e., a guarantee globally unique and persistent identifier assigned by an accredited body? 6 credits 
- Q4. If yes, is this external identifier a DOI? 	5 credits 
- Q5. Are the ontology metadata included in the ontology file –and consequently share the same identifiers? 6 credits 
- Q6. If not, is the metadata record clearly identified by its own GUPRI? 6 credits
- Q7. Does an ontology provide a version specific URI? 4 credits 	
- Q8. If yes, is this URI resolvable/dereferenceable? 5 credits

### F2. Data are described with rich metadata. (27 credits)  
- Q1 Is an ontology described with additional “MIRO must” metadata properties? (4 points per property up to 16 credits) 16 credits 
- Q2. Is an ontology described with additional “MIRO should” metadata properties? (2 points per property up to 5 credits) 5 credits 
- Q3. Is an ontology described with additional “MIRO optional” metadata properties? (1 point per property up to 3 credits) 3 credits 
- Q4. Is an ontology described with another metadata property with no explicit corresponding MIRO requirement? 	(1 pt per property up to 3 credits) 3 credits

### F3. Metadata clearly and explicitly include the identifier of the data they describe. (21 credits)  
- Q1. Are the ontology metadata included and maintained in the ontology file? 21 credits
- Q2. If not, are the ontology metadata described in an external file? 11 credits
- Q3. Does that external file explicitly link to the ontology and vice-versa? 	10 credits

### F4. (Meta)data are registered or indexed in a searchable resource. (24 credits)
- Q1. Is the ontology registered in multiple ontology libraries? (1 point per ontology library up to 7 credits) 7 credits
- Q2. Is the ontology registered in multiple open ontology repositories? (1 point per ontology repository up to 9 credits) 	9 credits
- Q3. Is an ontology registered in the "de facto" reference libraries or repositories ? (1 point per a “de facto” reference library up to 4 credits) 	4 credits
- Q4. Are the ontology libraries or repositories properly indexed by Web search engines? 4 credits

## ACCESSIBLE (credits 113)

### A1. (Meta)data are retrievable by their identifier using a standardised communications protocol. (43 credits)
- Q1.Does the ontology URI and other identifiers, if exist, resolve to the ontology? (3 points per a resolvable identifier up to 6 credits) 6 credits
- Q2. Does the ontology URI (if metadata is included in the ontology file) or the external metadata URI resolve to the metadata record? 7 credits
- Q3. Are ontology and its metadata supporting content negotiation? (6 points per format up to 24 credits) 24 credits
- Q4. Is an ontology and its metadata accessible through another standard protocol such as SPARQL? 6 credits

### A1.1.  The protocol is open, free, and universally implementable. (28 credits)
- Q1. Is an ontology relying on HTTP/URIs for its identification and access mechanisms? 20 credits 
- Q2. Are the other protocols –if any– open, free, and universally implementable? 8 credits

### A1.2. The protocol allows for an authentication and authorization where necessary. (22 credits)
- Q1. Does the protocols used to resolve ontology identifiers support authentication and authorization? 4 credits
- Q2. Is the ontology accessible in an ontology repository or library that supports authentication and authorization (such as NCBO BioPortal, AgroPortal)?  (3 points per ontology repository up to 18 credits) 18 credits

### A2. Metadata are accessible, even when the data are no longer available. (20 credits)
- Q1. Are "most" ontology versions accessible? 7 credits
- Q2. Are the metadata of each version available? 4 credits 
- Q3. Are ontology metadata accessible even if no more versions of the ontology are available? 4 credits
- Q4. Is the ontology accessible in an ontology repository or library that supports metadata archiving? 2 credits

## INTEROPERABLE(109 credits)

### I1. (Meta)data use a formal, accessible, shared, and broadly applicable language for knowledge representation. (44 credits)
- Q1. What is the representation language used for ontology and ontology metadata? 20 credits (if the ontology is in an OWL format else follow our representation format scoring scale*)
- Q2. Is the representation language used a W3C Recommendations? 10 credits
- Q3. Is the syntax of the ontology informed? 5 credits
- Q4. Is the formality level of the ontology asserted by the author? 5 credits
- Q5. Is the availability of other formats informed? 4 credits
(*) Scoring scale of each representation format: (OWL, 20 pts) - (SKOS, 18 pts) - (RDFS, 16 pts) - (OBO, 14 pts) - (XML, 12 pts) - (CSV, 11 pts) - (PDF, 5 pts) - (TXT, 5 pts).

### I2. (Meta)data use vocabularies that follow FAIR principles. (31 credits)
- Q1. Does the ontology import other FAIR vocabularies? 5 credits
- Q2. Does the ontology reuse URIs from other vocabularies? 4 credits
- Q3. If yes, does it include the minimum information for those URIs (cf. MERIOT)? 3 credits
- Q4. Is an ontology aligned to other vocabularies? 5 credits
- Q5. If yes, are those alignments well represented and to unambiguous entities? 3 credits
- Q6. If yes, are those alignments curated? 4 credits
- Q7. Does an ontology provide metadata information about relation to or influence of other vocabularies? 3 credits (1 point per metadata property up to 3 credits)
- Q8. Does the ontology reuse standards metadata vocabularies to describe its metadata? 5 credits

### I3. (Meta)data include qualified references to other (meta)data. (32 credits)
- Q1. Non-automatically assessable qualified references. 10 credits 
- Q2. Does an ontology provide cross-references to external resources? 9 credits 
- Q3. If yes, are those cross-references well represented and to unambiguous entities? 3 credits 
- Q4. Does an ontology provide information about projects using or organization endorsing? 6 credits (3 points per property up to 6 credits)
- Q5. Is an ontology using GUPRIs to encode some metadata values? 5 credits 

## REUSABLE (143 credits)
### R1. (Meta)data are richly described with a plurality of accurate and relevant attributes. (32 credits)
- Q1. Does the ontology provide metadata information about how classes are defined? 8 credits
- Q2. Does the ontology provide metadata information about its hierarchy? 8 credits
- Q3. How much of the ontology classes (or concepts) are defined using a property restriction (e.g. defining a class using OWL “quantifier”, “cardinality” or “has value” restrictions ) or an equivalent class (e.g. an OWL named class with a necessary and sufficient condition) ? 8 credits
- Q4. How much of the ontology objects provide provenance information with annotation properties (e.g. author, date)? 8 credits 

### R1.1 (Meta)data are released with a clear and accessible data usage license. (37 credits)
- Q1. Is the ontology license clearly specified (i.e., with a persistent, unique identifier)? 8 credits
- Q2. If yes, is the license description accessible and resolvable by a machine? 7 credits
- Q3. Are the ontology access rights clearly specified/declared? 7 credits
- Q4. Are the permissions, usage guidelines and copyright holder clearly documented? 15 credits

### R1.2 (Meta)data are associated with detailed provenance. (38 credits)
- Q1. Does an ontology metadata inform on its general provenance (e.g., source, creator, validator)? 10 credits 
- Q2. Are the accrual methods and policies documented? 10 credits 
- Q3. Is the ontology clearly versioned? 5 credits
- Q4. Are the methodology and tools used to build the ontology documented? 5 credits
- Q5. Is the ontology rationale documented? 5 credits
- Q6. Does an ontology inform on its funding organization? 3 credits

### R1.3 (Meta)data meet domain-relevant community standards. (36 credits)
- Q1. Is the ontology recognized by one or several groups* (or organizations) which attest it meets standard practices and guidelines relevant for a specific community? 30 credits
- Q2. Is the ontology openly and freely available?  6 credits
(*) In the case of AgroPortal, an ontology can be assigned to a “group”. Groups associate ontologies from the same project or organization. Currently, AgroPortal considers 8 main groups in the agriculture domain: OBO foundry initiative, WHEAT, CROP ontology project, and INRAE. 













