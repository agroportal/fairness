package fr.lirmm.fairness.assessment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.model.PortalInstance;
import fr.lirmm.fairness.assessment.utils.converters.FairJsonConverter;

public class FairServlet extends HttpServlet {

	private static final long serialVersionUID = -2749023988723161904L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String pPortalInstanceName = req.getParameter("portal");
			PortalInstance portalInstance = PortalInstance.getInstanceByName(pPortalInstanceName);

			if (portalInstance == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, String.format("Portal instance '%s' unknown", pPortalInstanceName));
			} else {
				String pOntologyAcronym = req.getParameter("ontology");
				List<String> allOntologyAcronyms = portalInstance.getAllOntologiesAcronyms();
				List<String> ontologyAcronymsToEvaluate = new ArrayList<String>();
				if (pOntologyAcronym != null && pOntologyAcronym.equals("all")) {
					if(portalInstance.isEnableAllOntologies()) {
						ontologyAcronymsToEvaluate.addAll(allOntologyAcronyms);
					}
					else {
						resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, String.format("Processing all ontologies is not allowed for instance '%s'", pPortalInstanceName));
					}
				} else {
					if(!allOntologyAcronyms.contains(pOntologyAcronym)) {
						resp.sendError(HttpServletResponse.SC_NOT_FOUND, String.format("Ontology '%s' not found", pOntologyAcronym));
					}
					else {
						ontologyAcronymsToEvaluate.add(pOntologyAcronym);
					}
				}
				
				Iterator<String> it = ontologyAcronymsToEvaluate.iterator();
				List<JsonObject> jsonObjects = new ArrayList<JsonObject>(ontologyAcronymsToEvaluate.size());
				while(it.hasNext()) {
					Fair.getInstance().evaluate(new Ontology(it.next(), portalInstance));
					jsonObjects.add(new FairJsonConverter(Fair.getInstance()).toJson());
				}
				PrintWriter out = resp.getWriter();
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				out.print(jsonObjects);
				out.flush();
			}
		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
