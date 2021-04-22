package fr.lirmm.fairness.assessment.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.utils.OntologyRestApi;

public class Ontology {

	private final static String FORMAT_APPLICATION_JSON = "application/json";
	private final static String FORMAT_APPLICATION_XML = "application/xml"; 
	private final static String FORMAT_TEXT_HTML = "text/html";
	private final static String FORMAT_TEXT_PLAIN = "text/plain";

	private PortalInstance portalInstance = null;
	private OntologyRestApi restApi = null;

	private String acronym = null, uri = null, id = null, versionIri = null, name = null, ontologyMetadata = null,
			group = null, alternative = null, hiddenLabel = null, hasOntoLang = null, description = null,
			homePage = null, pullLocation = null, keyWords = null, coverage = null;
	private String prefNamSpacUri = null, uriRegexPat = null, expId = null, creator = null, contributor = null,
			publisher = null, curatedBy = null, translator = null, domain = null, compatWith = null, sameDomain = null,
			knownUsage = null;
	private String audience = null, repository = null, bugDatabase = null, mailingList = null, metrics = null,
			preferredNamespacePrefix = null, abstra = null, publication = null, notes = null;
	private String numberOfClasses=null, numberOfIndividuals=null, numberOfProperties=null, numberOfAxioms=null;
	private String depiction = null, logo = null, toDoList = null, award = null, associatedMedia = null, hasPart = null,
			workTranslation = null, hasDisparateModelling = null, hasDisjunctionsWith = null, keyClasses = null;
	private String dataDump = null, openSearchDescription = null, uriLookupEndpoint = null, released = null,
			modificationDate = null, valid = null, type = null, endPoint = null, viewingrestriction = null,
			hasOntoSyntax = null, hasFormalLevel = null;
	private String isFormatOf = null, hasFormat = null, viewOf = null, hasPriorVersion = null,
			explanationEvolution = null, translationOfWork = null, hierarchyProperty = null, obsoleteParent = null,
			maxChildCount = null, averageChildCount = null;
	private String classesWithOneChild = null, classesWithNoDefinition = null, prefLabelProperty = null,
			synonymProperty = null, definitionProperty = null, authorProperty = null, obsoleteProperty = null,
			license = null, accessRights = null;
	private String morePermissions = null, useGuidelines = null, contact = null, source = null,
			wasGeneratedBy = null, WasInvalidatedBy = null, accrualMethod = null, accrualPeriodicity = null,
			accrualPolicy = null, version = null, diffFilePath = null, submissions = null;
	private String usedOntologyEngineeringTool = null, usedOntologyEngineeringMethodology = null,
			conformsToKnowledgeRepresentationParadigm = null, designedForOntologyTask = null, competencyQuestion = null,
			fundedBy = null, status = null, dateSubmitted = null;
	private String projects= null;
	private List<String> endorsedBy= new ArrayList<String>();
	private List<String> isIncompatibleWith = new ArrayList<String>();
	private List<String> language = new ArrayList<String>();
	private List<String> usedBy = new ArrayList<String>();
	private List<String> includedInDataCatalog = new ArrayList<String>();
	private List<String> useImports = new ArrayList<String>();
	private List<String> ontologyRelatedTo = new ArrayList<String>();
	private List<String> isAlignedTo = new ArrayList<String>();
	private List<String> metadataVoc = new ArrayList<String>();
	private List<String> similarTo = new ArrayList<String>();

	public Ontology(String acronym, PortalInstance portalInstance) throws IOException, JSONException {
		super();
		this.acronym = acronym;
		this.portalInstance = portalInstance;
		this.fillOntology();
	}

	private void fillOntology() throws IOException, JSONException {
		this.ontologyMetadata = this.getJsonMetadata();
		this.restApi = new OntologyRestApi(this.ontologyMetadata);
		uri = this.restApi.getSubmissionJsonObject("URI");
		id = this.restApi.getSubmissionJsonObject("identifier");
		acronym = this.restApi.getOntologyJsonObject("acronym");
		versionIri = this.restApi.getSubmissionJsonObject("versionIRI");
		name = this.restApi.getOntologyJsonObject("name");
		group = this.restApi.getOntologyJsonObject("group");
		alternative = this.restApi.getSubmissionJsonObject("alternative");
		hiddenLabel = this.restApi.getSubmissionJsonObject("hiddenLabel");
		hasOntoLang = this.restApi.getSubmissionJsonObject("hasOntologyLanguage");
		description = this.restApi.getSubmissionJsonObject("description");
		homePage = this.restApi.getSubmissionJsonObject("homepage");
		pullLocation = this.restApi.getSubmissionJsonObject("pullLocation");
		keyWords = this.restApi.getSubmissionJsonObject("keywords");
		coverage = this.restApi.getSubmissionJsonObject("coverage");
		prefNamSpacUri = this.restApi.getSubmissionJsonObject("preferredNamespaceUri");
		uriRegexPat = this.restApi.getSubmissionJsonObject("uriRegexPattern");
		expId = this.restApi.getSubmissionJsonObject("exampleIdentifier");
		creator = this.restApi.getSubmissionJsonObject("hasCreator");
		contributor = this.restApi.getSubmissionJsonObject("hasContributor");
		publisher = this.restApi.getSubmissionJsonObject("publisher");
		curatedBy = this.restApi.getSubmissionJsonObject("curatedBy");
		translator = this.restApi.getSubmissionJsonObject("translator");
		domain = this.restApi.getSubmissionJsonObject("hasDomain");
		compatWith = this.restApi.getSubmissionJsonObject("isBackwardCompatibleWith");
		sameDomain = this.restApi.getSubmissionJsonObject("comesFromTheSameDomain");
		knownUsage = this.restApi.getSubmissionJsonObject("knownUsage");
		audience = this.restApi.getSubmissionJsonObject("audience");
		repository = this.restApi.getSubmissionJsonObject("repository");
		bugDatabase = this.restApi.getSubmissionJsonObject("bugDatabase");
		mailingList = this.restApi.getSubmissionJsonObject("mailingList");
		numberOfClasses= this.restApi.getSubmissionJsonObject("numberOfClasses");
		metrics = this.restApi.getSubmissionJsonObject("metrics");
		numberOfIndividuals = this.restApi.getSubmissionJsonObject("numberOfIndividuals");
		numberOfProperties = this.restApi.getSubmissionJsonObject("numberOfProperties");
		numberOfAxioms = this.restApi.getSubmissionJsonObject("numberOfAxioms");
		preferredNamespacePrefix = this.restApi.getSubmissionJsonObject("preferredNamespacePrefix");
		language = this.restApi.getJsonMetadataArrayObject("naturalLanguage");
		abstra = this.restApi.getSubmissionJsonObject("abstract");
		publication = this.restApi.getSubmissionJsonObject("publication");
		includedInDataCatalog = this.restApi.getJsonMetadataArrayObject("includedInDataCatalog");
		notes = this.restApi.getSubmissionJsonObject("notes");
		depiction = this.restApi.getSubmissionJsonObject("depiction");
		logo = this.restApi.getSubmissionJsonObject("logo");
		toDoList = this.restApi.getSubmissionJsonObject("toDoList");
		award = this.restApi.getSubmissionJsonObject("award");
		associatedMedia = this.restApi.getSubmissionJsonObject("associatedMedia");
		isIncompatibleWith = this.restApi.getJsonMetadataArrayObject("isIncompatibleWith");
		hasPart = this.restApi.getSubmissionJsonObject("hasPart");
		workTranslation = this.restApi.getSubmissionJsonObject("workTranslation");
		hasDisparateModelling = this.restApi.getSubmissionJsonObject("hasDisparateModelling");
		usedBy = this.restApi.getJsonMetadataArrayObject("usedBy");
		hasDisjunctionsWith = this.restApi.getSubmissionJsonObject("hasDisjunctionsWith");
		keyClasses = this.restApi.getSubmissionJsonObject("keyClasses");
		dataDump = this.restApi.getSubmissionJsonObject("dataDump");
		openSearchDescription = this.restApi.getSubmissionJsonObject("openSearchDescription");
		uriLookupEndpoint = this.restApi.getSubmissionJsonObject("uriLookupEndpoint");
		modificationDate = this.restApi.getSubmissionJsonObject("modificationDate");
		valid = this.restApi.getSubmissionJsonObject("valid");
		type = this.restApi.getOntologyJsonObject("ontologyType");
		viewingrestriction = this.restApi.getOntologyJsonObject("viewingRestriction");
		hasOntoSyntax = this.restApi.getSubmissionJsonObject("hasOntologySyntax");
		hasFormalLevel = this.restApi.getSubmissionJsonObject("hasFormalityLevel");
		isFormatOf = this.restApi.getSubmissionJsonObject("isFormatOf");
		hasFormat = this.restApi.getSubmissionJsonObject("hasFormat");
		useImports = this.restApi.getJsonMetadataArrayObject("useImports");
		ontologyRelatedTo = this.restApi.getJsonMetadataArrayObject("ontologyRelatedTo");
		isAlignedTo = this.restApi.getJsonMetadataArrayObject("isAlignedTo");
		viewOf = this.restApi.getOntologyJsonObject("viewOf");
		metadataVoc = this.restApi.getJsonMetadataArrayObject("metadataVoc");
		hasPriorVersion = this.restApi.getSubmissionJsonObject("hasPriorVersion");
		explanationEvolution = this.restApi.getSubmissionJsonObject("explanationEvolution");
		translationOfWork = this.restApi.getSubmissionJsonObject("translationOfWork");
		similarTo = this.restApi.getJsonMetadataArrayObject("similarTo");
		classesWithNoDefinition= this.restApi.getSubmissionJsonObject("classesWithNoDefinition");
		hierarchyProperty = this.restApi.getSubmissionJsonObject("hierarchyProperty");
		obsoleteParent = this.restApi.getSubmissionJsonObject("obsoleteParent");
		maxChildCount = this.restApi.getSubmissionJsonObject("maxChildCount");
		averageChildCount = this.restApi.getSubmissionJsonObject("averageChildCount");
		prefLabelProperty=this.restApi.getSubmissionJsonObject("prefLabelProperty");
		definitionProperty= this.restApi.getSubmissionJsonObject("definitionProperty");
		authorProperty= this.restApi.getSubmissionJsonObject("authorProperty");
		obsoleteProperty= this.restApi.getSubmissionJsonObject("obsoleteProperty");
		license = this.restApi.getSubmissionJsonObject("hasLicense");
		accessRights = this.restApi.getOntologyJsonObject("viewingRestriction");
		morePermissions= this.restApi.getSubmissionJsonObject("morePermissions");
		useGuidelines=this.restApi.getSubmissionJsonObject("useGuidelines");
		contact=this.restApi.getSubmissionJsonObject("contact");	
		source = this.restApi.getSubmissionJsonObject("source");
		wasGeneratedBy = this.restApi.getSubmissionJsonObject("wasGeneratedBy");
		WasInvalidatedBy = this.restApi.getSubmissionJsonObject("wasInvalidatedBy");
		accrualMethod = this.restApi.getSubmissionJsonObject("accrualMethod");
		accrualPeriodicity = this.restApi.getSubmissionJsonObject("accrualPeriodicity");
		accrualPolicy = this.restApi.getSubmissionJsonObject("accrualPolicy");
		version = this.restApi.getSubmissionJsonObject("version");
		diffFilePath = this.restApi.getSubmissionJsonObject("diffFilePath");
		submissions = this.restApi.getOntologyLinksyJsonObject("submissions");
		usedOntologyEngineeringTool = this.restApi.getSubmissionJsonObject("usedOntologyEngineeringTool");
		usedOntologyEngineeringMethodology = this.restApi.getSubmissionJsonObject("usedOntologyEngineeringMethodology");
		conformsToKnowledgeRepresentationParadigm = this.restApi.getSubmissionJsonObject("conformsToKnowledgeRepresentationParadigm");
		designedForOntologyTask = this.restApi.getSubmissionJsonObject("designedForOntologyTask");
		competencyQuestion = this.restApi.getSubmissionJsonObject("competencyQuestion");
		fundedBy = this.restApi.getSubmissionJsonObject("fundedBy");
		status = this.restApi.getSubmissionJsonObject("status");
		dateSubmitted = this.restApi.getSubmissionJsonObject("creationDate");
		released = this.restApi.getSubmissionJsonObject("released");
		projects= this.restApi.getOntologyLinksyJsonObject("projects");
		endorsedBy= this.restApi.getJsonMetadataArrayObject("endorsedBy");
	}
	
	private String getMetadata(String format) throws IOException {
		final String url = String.join("/", new String[] { this.portalInstance.getUrl(), "ontologies", this.acronym,
				"latest_submission?display=all" });
		return OntologyRestApi.get(url, this.portalInstance.getApikey(), format);
	}

	public String getHtmlMetadata() throws IOException {
		return this.getMetadata(FORMAT_TEXT_HTML);
	}

	public String getJsonMetadata() throws IOException {
		return this.getMetadata(FORMAT_APPLICATION_JSON);
	}

	public String getTextMetadata() throws IOException {
		return this.getMetadata(FORMAT_TEXT_PLAIN);
	}

	public String getXmlMetadata() throws IOException {
		return this.getMetadata(FORMAT_APPLICATION_XML);
	}

	public String getAcronym() {
		return acronym;
	}

	public String getUri() {
		return uri;
	}

	public PortalInstance getPortalInstance() {
		return portalInstance;
	}

	public String getOntologyMetadata() {
		return ontologyMetadata;
	}

	public String getId() {
		return id;
	}

	public String getVersionIri() {
		return versionIri;
	}

	public String getName() {
		return name;
	}

	public String getGroup() {
		return group;
	}

	public String getAlternative() {
		return alternative;
	}

	public String getHiddenLabel() {
		return hiddenLabel;
	}

	public String getHasOntoLang() {
		return hasOntoLang;
	}

	public String getDescription() {
		return description;
	}

	public String getHomePage() {
		return homePage;
	}

	public String getPullLocation() {
		return pullLocation;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public String getCoverage() {
		return coverage;
	}

	public String getPrefNamSpacUri() {
		return prefNamSpacUri;
	}

	public String getUriRegexPat() {
		return uriRegexPat;
	}

	public String getExpId() {
		return expId;
	}

	public String getCreator() {
		return creator;
	}

	public String getContributor() {
		return contributor;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getCuratedBy() {
		return curatedBy;
	}

	public String getTranslator() {
		return translator;
	}

	public String getDomain() {
		return domain;
	}

	public String getCompatWith() {
		return compatWith;
	}

	public String getSameDomain() {
		return sameDomain;
	}

	public String getKnownUsage() {
		return knownUsage;
	}

	public String getAudience() {
		return audience;
	}

	public String getRepository() {
		return repository;
	}

	public String getBugDatabase() {
		return bugDatabase;
	}

	public String getMailingList() {
		return mailingList;
	}

	public String getMetrics() {
		return metrics;
	}

	public String getNumberOfClasses()
	{
		return numberOfClasses;
	}
	
	public String getNumberOfIndividuals()
	{
		return numberOfIndividuals;
	}
	
	public String getNumberOfProperties()
	{
		return numberOfProperties;
	}
	

	public String getNumberOfAxioms()
	{
		return numberOfAxioms;  
	}

	public String getPreferredNamespacePrefix() {
		return preferredNamespacePrefix;
	}

	public String getAbstra() {
		return abstra;
	}

	public String getPublication() {
		return publication;
	}

	public String getNotes() {
		return notes;
	}

	public String getDepiction() {
		return depiction;
	}

	public String getLogo() {
		return logo;
	}

	public String getToDoList() {
		return toDoList;
	}

	public String getAward() {
		return award;
	}

	public String getAssociatedMedia() {
		return associatedMedia;
	}

	public String getHasPart() {
		return hasPart;
	}

	public String getWorkTranslation() {
		return workTranslation;
	}

	public String getHasDisparateModelling() {
		return hasDisparateModelling;
	}

	public String getHasDisjunctionsWith() {
		return hasDisjunctionsWith;
	}

	public String getKeyClasses() {
		return keyClasses;
	}

	public String getDataDump() {
		return dataDump;
	}

	public String getOpenSearchDescription() {
		return openSearchDescription;
	}

	public String getUriLookupEndpoint() {
		return uriLookupEndpoint;
	}

	public String getReleased() {
		return released;
	}

	public String getModificationDate() {
		return modificationDate;
	}

	public String getValid() {
		return valid;
	}

	public String getType() {
		return type;
	}

	public String getEndPoint() {
		
		return endPoint;
	}

	public String getViewingrestriction() {
		return viewingrestriction;
	}

	public String getHasOntoSyntax() {
		return hasOntoSyntax;
	}

	public String getHasFormalLevel() {
		return hasFormalLevel;
	}

	public String getIsFormatOf() {
		return isFormatOf;
	}

	public String getHasFormat() {
		return hasFormat;
	}

	public String getViewOf() {
		return viewOf;
	}

	public String getHasPriorVersion() {
		return hasPriorVersion;
	}

	public String getExplanationEvolution() {
		return explanationEvolution;
	}

	public String getTranslationOfWork() {
		return translationOfWork;
	}

	public String getHierarchyProperty() {
		return hierarchyProperty;
	}

	public String getObsoleteParent() {
		return obsoleteParent;
	}

	public String getMaxChildCount() {
		return maxChildCount;
	}

	public String getAverageChildCount() {
		return averageChildCount;
	}

	public String getClassesWithOneChild() {
		return classesWithOneChild;
	}

	public String getClassesWithNoDefinition() {
		return classesWithNoDefinition;
	}

	public String getPrefLabelProperty() {
		return prefLabelProperty;
	}

	public String getSynonymProperty() {
		return synonymProperty;
	}

	public String getDefinitionProperty() {
		return definitionProperty;
	}

	public String getAuthorProperty() {
		return authorProperty;
	}

	public String getObsoleteProperty() {
		return obsoleteProperty;
	}

	public String getLicense() {
		return license;
	}

	public String getAccessRights() {
		return accessRights;
	}

	public String getMorePermissions() {
		return morePermissions;
	}

	public String getUseGuidelines() {
		return useGuidelines;
	}

	public String getConatct() {
		return contact;
	}

	public String getSource() {
		return source;
	}

	public String getWasGeneratedBy() {
		return wasGeneratedBy;
	}

	public String getWasInvalidatedBy() {
		return WasInvalidatedBy;
	}

	public String getAccrualMethod() {
		return accrualMethod;
	}

	public String getAccrualPeriodicity() {
		return accrualPeriodicity;
	}

	public String getAccrualPolicy() {
		return accrualPolicy;
	}

	public String getVersion() {
		return version;
	}

	public String getDiffFilePath() {
		return diffFilePath;
	}

	public String getSubmissions() {
		return submissions;
	}

	public String getUsedOntologyEngineeringTool() {
		return usedOntologyEngineeringTool;
	}

	public String getUsedOntologyEngineeringMethodology() {
		return usedOntologyEngineeringMethodology;
	}

	public String getConformsToKnowledgeRepresentationParadigm() {
		return conformsToKnowledgeRepresentationParadigm;
	}

	public String getDesignedForOntologyTask() {
		return designedForOntologyTask;
	}

	public String getCompetencyQuestion() {
		return competencyQuestion;
	}

	public String getFundedBy() {
		return fundedBy;
	}

	public String getStatus() {
		return status;
	}

	public String getDateSubmitted() {
		return dateSubmitted;
	}

	public List<String> getIsIncompatibleWith() {
		return isIncompatibleWith;
	}

	public List<String> getLanguage() {
		return language;
	}

	public List<String> getUsedBy() {
		return usedBy;
	}

	public List<String> getIncludedInDataCatalog() {
		return includedInDataCatalog;
	}

	public List<String> getUseImports() {
		return useImports;
	}

	public List<String> getOntologyRelatedTo() {
		return ontologyRelatedTo;
	}

	public List<String> getIsAlignedTo() {
		return isAlignedTo;
	}

	public List<String> getMetadataVoc() {
		return metadataVoc;
	}

	public List<String> getSimilarTo() {
		return similarTo;
	}
	
   public String getProjects() 
   {
	   return projects;  
   }
   
   public List <String> getendorsedBy()
   {
	   return endorsedBy; 
   }
   
}










