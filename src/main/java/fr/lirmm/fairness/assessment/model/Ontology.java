package fr.lirmm.fairness.assessment.model;

import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.lirmm.fairness.assessment.Configuration;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.utils.OntologyRestApi;

public class Ontology {

	private final static String FORMAT_APPLICATION_JSON = "application/json";
	private final static String FORMAT_APPLICATION_XML = "application/xml"; 
	private final static String FORMAT_TEXT_HTML = "text/html";
	private final static String FORMAT_TEXT_PLAIN = "text/plain";

	private PortalInstance portalInstance = null;
	private OntologyRestApi restApi = null;

	private final Map<String,Property> properties = new HashMap<>();
	private String acronym;

	public Ontology(String acronym, PortalInstance portalInstance) throws IOException, JSONException {
		super();
		this.acronym = acronym;
		this.portalInstance = portalInstance;
		this.fillOntology();
	}

	private void fillOntology() throws IOException, JSONException {
		String ontologyMetadata = this.getJsonMetadata();
		this.restApi = new OntologyRestApi(ontologyMetadata);
		//TODO rename properties to the mod nomination https://docs.google.com/spreadsheets/d/16GPvfkUTPZaMc7I7_3qZ5H7xse915kPMS3z00tptV6g/edit#gid=1638855124
		// Colonne F pour agroportal et Colonne H MOD1.4 a utiliser
		List<Map<?,?>> properties = Configuration.getInstance().getPropertiesConfig();

		properties.forEach(propertyMap -> {
			Property property = new Property((Map<String, String>) propertyMap);
			switch (property.getSource()){
				case "submission":
						try {
							if(property.getType().equals("array")) {
								this.addSubmissionArrayPropertyValue(property);
							}else {
								this.addSubmissionPropertyValue(property);
							}
						} catch (JSONException | IOException e) {
							e.printStackTrace();
						}

					break;
				case "ontology":
					try {
						this.addOntologyPropertyValue(property);
					} catch (JSONException | IOException e) {
						e.printStackTrace();
					}
					break;
				case "link":
					try {
						if(property.getType().equals("array")) {
							this.addOntologyLinkArrayPropertyValue(property);
						}else {
							this.addOntologyLinkPropertyValue(property);
						}
					} catch (JSONException | IOException e) {
						e.printStackTrace();
					}
			}
		});
	}

	public Map<String, Property> getInstanceProperties() {
		return properties;
	}

	private List<String> getPropertyValues(String property) {
		return (List<String>) this.properties.get(property).getValue();
	}
	public String getPropertyValue(String property){
		List<?> out = this.getPropertyValues(property);
		if(out!=null && !out.isEmpty())
			return out.get(0).toString();
		else
			return "";
	}

	private void addPropertyValue(Property property){
		this.properties.put(property.getLabel() , property);
	}
	private void addSubmissionPropertyValue(Property property) throws JSONException, IOException {
		property.setValue(List.of(this.restApi.getSubmissionJsonObject(property.getLabel())));
		this.addPropertyValue(property);
	}
	private void addSubmissionArrayPropertyValue(Property property) throws JSONException, IOException {
		property.setValue(this.restApi.getJsonMetadataArrayObject(property.getLabel()));
		this.addPropertyValue(property);
	}

	private void addOntologyPropertyValue(Property property) throws JSONException, IOException {
		property.setValue(List.of(this.restApi.getOntologyJsonObject(property.getLabel())));
		this.addPropertyValue(property);
	}

	private void addOntologyLinkPropertyValue(Property property) throws JSONException, IOException {
		property.setValue(List.of(this.restApi.getOntologyLinksJsonObject(property.getLabel())));
		this.addPropertyValue(property);
	}

	private void addOntologyLinkArrayPropertyValue(Property property) throws JSONException, IOException {
		Gson gson = new GsonBuilder().create();
		property.setValue((gson.fromJson(
				OntologyRestApi.get(this.restApi.getOntologyLinksJsonObject(property.getLabel()) , portalInstance.getApikey() ,"application/json"),
				List.class)));
		this.addPropertyValue(property);

	}


	public String getMetaDataURL(){
		return String.join("/", new String[] { this.portalInstance.getUrl(), "ontologies", this.acronym,
				"latest_submission?display=all" });
	}

	private String getMetadata(String format) throws IOException {
		return OntologyRestApi.get(getMetaDataURL(), this.portalInstance.getApikey(), format);
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

	public String getOntologyIRI() {
		return this.getPropertyValue("URI");
	}

	public PortalInstance getPortalInstance() {
		return portalInstance;
	}



	public String getIdentifier() {
		return this.getPropertyValue("identifier");
	}

	public String getGeneralizes() {
		return this.getPropertyValue("generalizes");
	}

	public String getMaxDepth() {
		return this.getPropertyValue("maxDepth");
	}
	public String getVersionIri() {
		return this.getPropertyValue("versionIRI");
	}

	public String getName() {
		return this.getPropertyValue("name");
	}

	public String getGroup() {
		return this.getPropertyValue("group");
	}

	public String getAlternative() {
		return this.getPropertyValue("alternative");
	}

	public String getHiddenLabel() {
		return this.getPropertyValue("hiddenLabel");
	}

	public String getHasOntoLang() {
		return this.getPropertyValue("hasOntologyLanguage");
	}

	public String getDescription() {
		return this.getPropertyValue("description");
	}

	public String getHomePage() {
		return this.getPropertyValue("homepage");
	}

	public String getPullLocation() {
		return this.getPropertyValue("pullLocation");
	}

	public String getKeyWords() {
		return this.getPropertyValue("keywords");
	}

	public String getCoverage() {
		return this.getPropertyValue("coverage");
	}

	public String getPrefNamSpacUri() {
		return this.getPropertyValue("preferredNamespaceUri");
	}

	public String getUriRegexPat() {
		return this.getPropertyValue("uriRegexPattern");
	}

	public String getExampleIdentifier() {
		return this.getPropertyValue("exampleIdentifier");
	}

	public String getCreator() {
		return this.getPropertyValue("hasCreator");
	}

	public String getContributor() {
		return this.getPropertyValue("hasContributor");
	}

	public String getPublisher() {
		return this.getPropertyValue("publisher");
	}

	public String getCuratedBy() {
		return this.getPropertyValue("curatedBy");
	}

	public String getTranslator() {
		return this.getPropertyValue("translator");
	}

	public String getDomain() {
		return this.getPropertyValue("hasDomain");
	}

	public String getCompatWith() {
		return this.getPropertyValue("isBackwardCompatibleWith");
	}

	public String getComesFromTheSameDomain() {
		return this.getPropertyValue("comesFromTheSameDomain");
	}

	public String getKnownUsage() {
		return this.getPropertyValue("knownUsage");
	}

	public String getAudience() {
		return this.getPropertyValue("audience");
	}

	public String getRepository() {
		return this.getPropertyValue("repository");
	}

	public String getBugDatabase() {
		return this.getPropertyValue("bugDatabase");
	}

	public String getMailingList() {
		return this.getPropertyValue("mailingList");
	}

	public String getMetrics() {
		return this.getPropertyValue("metrics");
	}

	public String getNumberOfClasses()
	{
		return this.getPropertyValue("numberOfClasses");
	}
	
	public String getNumberOfIndividuals()
	{
		return this.getPropertyValue("numberOfIndividuals");
	}
	
	public String getNumberOfProperties()
	{
		return this.getPropertyValue("numberOfProperties");
	}
	

	public String getNumberOfAxioms()
	{
		return this.getPropertyValue("numberOfAxioms");
	}

	public String getPreferredNamespacePrefix() {
		return this.getPropertyValue("preferredNamespacePrefix");
	}
	public String getPreferredNamespaceUri() {
		return this.getPropertyValue("preferredNamespaceUri");
	}

	public String getAbstract() {
		return this.getPropertyValue("abstract");
	}
	public String getAnalytics() {
		return this.getPropertyValue("analytics");
	}
	public String getRoots() {
		return this.getPropertyValue("roots");
	}
	public String getUI() {
		return this.getPropertyValue("ui");
	}
	public String getCreationDate() {
		return this.getPropertyValue("creationDate");
	}
	public String getCuratedOn() {
		return this.getPropertyValue("curatedOn");
	}

	public String getPublication() {
		return this.getPropertyValue("publication");
	}

	public String getNotes() {
		return this.getPropertyValue("notes");
	}

	public String getDepiction() {
		return this.getPropertyValue("depiction");
	}

	public String getLogo() {
		return this.getPropertyValue("logo");
	}

	public String getToDoList() {
		return this.getPropertyValue("toDoList");
	}

	public String getAward() {
		return this.getPropertyValue("award");
	}

	public String getAssociatedMedia() {
		return this.getPropertyValue("associatedMedia");
	}

	public String getHasPart() {
		return this.getPropertyValue("hasPart");
	}

	public String getWorkTranslation() {
		return this.getPropertyValue("workTranslation");
	}

	public String getHasDisparateModelling() {
		return this.getPropertyValue("hasDisparateModelling");
	}

	public String getHasDisjunctionsWith() {
		return this.getPropertyValue("hasDisjunctionsWith");
	}

	public String getKeyClasses() {
		return this.getPropertyValue("keyClasses");
	}

	public String getDataDump() {
		return this.getPropertyValue("dataDump");
	}

	public String getOpenSearchDescription() {
		return this.getPropertyValue("openSearchDescription");
	}

	public String getUriLookupEndpoint() {
		return this.getPropertyValue("uriLookupEndpoint");
	}

	public String getReleased() {
		return this.getPropertyValue("released");
	}

	public String getModificationDate() {
		return this.getPropertyValue("modificationDate");
	}

	public String getValid() {
		return this.getPropertyValue("valid");
	}

	public String getType() {
		return this.getPropertyValue("isOfType");
	}

	public String getEndPoint() {
		return this.getPropertyValue("endpoint");
	}

	public String getAccessRights() {
		return this.getPropertyValue("viewingRestriction");
	}

	public String getHasOntoSyntax() {
		return this.getPropertyValue("hasOntologySyntax");
	}

	public String getHasFormalLevel() {
		return this.getPropertyValue("hasFormalityLevel");
	}

	public String getIsFormatOf() {
		return this.getPropertyValue("isFormatOf");
	}

	public String getHasFormat() {
		return this.getPropertyValue("hasFormat");
	}

	public String getViewOf() {
		return this.getPropertyValue("viewOf");
	}

	public String getHasPriorVersion() {
		return this.getPropertyValue("hasPriorVersion");
	}

	public String getExplanationEvolution() {
		return this.getPropertyValue("explanationEvolution");
	}

	public String getTranslationOfWork() {
		return this.getPropertyValue("translationOfWork");
	}

	public String getHierarchyProperty() {
		return this.getPropertyValue("hierarchyProperty");
	}

	public String getObsoleteParent() {
		return this.getPropertyValue("obsoleteParent");
	}

	public String getMaxChildCount() {
		return this.getPropertyValue("maxChildCount");
	}

	public String getAverageChildCount() {
		return this.getPropertyValue("averageChildCount");
	}

	public String getClassesWithOneChild() {
		return this.getPropertyValue("classesWithOneChild");
	}

	public String getClassesWithNoDefinition() {
		return this.getPropertyValue("classesWithNoDefinition");
	}

	public String getPrefLabelProperty() {
		return this.getPropertyValue("prefLabelProperty");
	}

	public String getSynonymProperty() {
		return this.getPropertyValue("synonymProperty");
	}

	public String getDefinitionProperty() {
		return this.getPropertyValue("definitionProperty");
	}

	public String getAuthorProperty() {
		return this.getPropertyValue("authorProperty");
	}

	public String getObsoleteProperty() {
		return this.getPropertyValue("obsoleteProperty");
	}

	public String getLicense() {
		return this.getPropertyValue("hasLicense");
	}


	public String getMorePermissions() {
		return this.getPropertyValue("morePermissions");
	}

	public String getUseGuidelines() {
		return this.getPropertyValue("useGuidelines");
	}

	public String getContact() {
		return this.getPropertyValue("contact");
	}

	public String getSource() {
		return this.getPropertyValue("source");
	}

	public String getWasGeneratedBy() {
		return this.getPropertyValue("wasGeneratedBy");
	}

	public String getWasInvalidatedBy() {
		return this.getPropertyValue("wasInvalidatedBy");
	}

	public String getAccrualMethod() {
		return this.getPropertyValue("accrualMethod");
	}

	public String getAccrualPeriodicity() {
		return this.getPropertyValue("accrualPeriodicity");
	}

	public String getAccrualPolicy() {
		return this.getPropertyValue("accrualPolicy");
	}

	public String getVersion() {
		return this.getPropertyValue("version");
	}

	public String getDiffFilePath() {
		return this.getPropertyValue("diffFilePath");
	}

	public String getReviews() {
		return this.getPropertyValue("reviews");
	}

	public String getSubmissions() {
		return this.getPropertyValue("submissions");
	}

	public String getUsedOntologyEngineeringTool() {
		return this.getPropertyValue("usedOntologyEngineeringTool");
	}

	public String getUsedOntologyEngineeringMethodology() {
		return this.getPropertyValue("usedOntologyEngineeringMethodology");
	}

	public String getConformsToKnowledgeRepresentationParadigm() {
		return this.getPropertyValue("conformsToKnowledgeRepresentationParadigm");
	}

	public String getDesignedForOntologyTask() {
		return this.getPropertyValue("designedForOntologyTask");
	}

	public String getCompetencyQuestion() {
		return this.getPropertyValue("competencyQuestion");
	}

	public String getFundedBy() {
		return this.getPropertyValue("fundedBy");
	}

	public String getStatus() {
		return this.getPropertyValue("status");
	}

	public String getDocumentation() { return this.getPropertyValue("documentation");}
	public String getExample() { return this.getPropertyValue("example");}
	public String getDateSubmitted() {
		return this.getPropertyValue("dateSubmitted");
	}

	public List<String> getIsIncompatibleWith() {
		return this.getPropertyValues("isIncompatibleWith");
	}

	public List<String> getLanguage() {
		return this.getPropertyValues("naturalLanguage");
	}

	public List<String> getUsedBy() {
		return this.getPropertyValues("usedBy");
	}

	public List<String> getIncludedInDataCatalog() {
		return this.getPropertyValues("includedInDataCatalog");
	}

	public List<String> getUseImports() {
		return this.getPropertyValues("useImports");
	}

	public List<String> getOntologyRelatedTo() {
		return this.getPropertyValues("ontologyRelatedTo");
	}

	public List<String> getIsAlignedTo() {
		return this.getPropertyValues("isAlignedTo");
	}

	public List<String> getMetadataVoc() {
		return this.getPropertyValues("metadataVoc");
	}

	public List<String> getSimilarTo() {
		return this.getPropertyValues("similarTo");
	}
	
   public List<String> getProjects()
   {
	   return this.getPropertyValues("projects");
   }


	public List <String> getEndorsedBy()
   {
	   return this.getPropertyValues("endorsedBy");
   }

	public String getProperties()
	{
		return this.getPropertyValue("properties");
	}

	public String getClasses()
	{
		return this.getPropertyValue("classes");
	}
   
}










