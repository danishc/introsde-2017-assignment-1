package xpath.reader;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ActivityReader {
	
    Document doc;
    XPath xpath;
    
// main class
    public static void main(String[] args) throws ParserConfigurationException, SAXException,
    														IOException, XPathExpressionException {

    		ActivityReader test = new ActivityReader();
    		//loading xml from root path
    		test.loadXML("database.xml");
    		int id=5;

    		//getting Activity Description by id
//    	Node node = test.getActivityDescription(id);
//  		System.out.println("Person id: " + id +" Activity Description : " + node.getTextContent());

//    	//getting Activity Place by id
//    	node = test.getActivityPlace(id);
//    	System.out.println("Person id: " + id +" Activity Place : " + node.getTextContent());
    		
    		System.out.println("\n \n \n *********printing all people with detail*********");
    		System.out.println("*********runs instruction 3 based on Lab 3*********\n");
    		//prints all people in the list with details
    		test.printall();
    		
    		System.out.println("\n \n \n *********printing activity of person id = 5 *********");
    		System.out.println("*********runs instruction 4 based on Lab 3*********\n");
    		
    		//getting Activity Preference by id = 5
    		System.out.println("\nPerson id: " + id );
    		test.printActivitiesByPID(id);
    		
    		System.out.println("\n \n \n *********printing people with start date condition*********");
    		System.out.println("*********runs instruction 5 based on Lab 3 with startdate > 2017-13-10 *********\n");
        
        //printing people with condition
        test.printPeopleWithCondition(">2017-13-10");
    }
//end of main

    
	public void printall() throws XPathExpressionException {
		//i used dom w3c API in order to convert object from xPath
		// getting object of persons array and convert it to NodeList.
		NodeList nodeList = (NodeList) xpath.compile("/people/person").evaluate(doc, XPathConstants.NODESET);
		//NodeList childNodeList = (NodeList) xpath.compile("/people/person/activitypreference").evaluate(doc, XPathConstants.NODESET);
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element personElement = (Element) nNode;
				//print person data
				printPerson(personElement);
				//print all related activities
				printActivitiesByPID(Integer.parseInt(personElement.getAttribute("id"))); 
			}
         }
	}
	
	public void printActivity(Element eElement2) {
		System.out.println("Activity Preference id : " + eElement2.getAttribute("id"));
        System.out.println("	Name of the Activity : " + eElement2.getElementsByTagName("name").item(0).getTextContent());
        System.out.println("	Description : " + eElement2.getElementsByTagName("description").item(0).getTextContent());
        System.out.println("	Place : " + eElement2.getElementsByTagName("place").item(0).getTextContent());
        System.out.println("	Start Date : " + eElement2.getElementsByTagName("startdate").item(0).getTextContent());
	}
	public void printPerson(Element eElement) {
		System.out.println("\nPerson id :" + eElement.getAttribute("id"));
        System.out.println("	First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
        System.out.println("	Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
        System.out.println("	Date of Birth : " + eElement.getElementsByTagName("birthdate").item(0).getTextContent());
	}
	
	private void printActivitiesByPID(int id) throws XPathExpressionException {
		NodeList node = getActivitiesByPID(id);
		for (int j = 0; j < node.getLength(); j++) {
			Node cNode = node.item(j);
    
			//checking if a Node is an Element type in order to convert it to element and iterate the data
			if (cNode.getNodeType() == Node.ELEMENT_NODE ) {
				Element activityElement = (Element) cNode;
				//printing elements to standard output
				printActivity(activityElement);
			}
		}
	}

	public void loadXML(String db) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		System.out.println("Loading database.xml...");
		doc = builder.parse(db);

		//creating xpath object and saving it to global variable "xpath"
		genXPathObj();
	}
	
	private void genXPathObj() {
		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
	}
	//return Activity Description identified by Activity id 
	public Node getActivityDescription(int id) throws XPathExpressionException {
		XPathExpression expr = xpath.compile("/people/person[@id="+id+"]/activitypreference/description");
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
		return node;
	}
	//return Activity Place identified by Activity id 
	public Node getActivityPlace(int id) throws XPathExpressionException {
		XPathExpression expr = xpath.compile("/people/person[@id=" + id + "]/activitypreference/place");
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
		return node;
	}
	//return Object of Node type Activity identified by Person id 
	private NodeList getActivitiesByPID(int id) throws XPathExpressionException {
		XPathExpression expr = xpath.compile("/people/person[@id=" + id + "]/activitypreference");
		NodeList node = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		return node;
	}

	public void printPeopleWithCondition(String startDate) throws XPathExpressionException {
		
		NodeList nodeList = (NodeList) xpath.compile("/people/person").evaluate(doc, XPathConstants.NODESET);
		NodeList childNodeList = (NodeList) xpath.compile("/people/person/activitypreference").evaluate(doc, XPathConstants.NODESET);
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			Node cNode = childNodeList.item(i);
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE && cNode.getNodeType() == Node.ELEMENT_NODE ) {
               Element eElement = (Element) nNode;
               Element eElement2 = (Element) cNode;
               
               String actDate = eElement2.getElementsByTagName("startdate").item(0).getTextContent().substring(0,10);
               String condition = startDate.substring(0,1);
               StringBuilder stringBuilder = new StringBuilder();
               //changing the format from YEAR/DATE/MONTH to YEAR/MONTH/DATE
               //in order to do the date comparison
               stringBuilder.append(startDate.substring(1,5));
               stringBuilder.append(startDate.substring(8,11));
               stringBuilder.append(startDate.substring(5,8));
               String givenDate= stringBuilder.toString();
               
               
               if(condition.equals(">")) {
            	   	if(actDate.compareTo(givenDate)>0) {
            	   		System.out.println("\nperson with activity date " + startDate);
            	   		printPerson(eElement);
            	   		printActivity(eElement2);
            	   	}
               }	
            	   if(condition.equals("<")) {
            	   	if(actDate.compareTo(givenDate)<0) {
            	   		System.out.println("\nperson with activity date " + startDate);
            	   		printPerson(eElement);
            	   		printActivity(eElement2);
            	   	}
            	   }
            	   if(condition.equals("=")) {
            	   	if(actDate.compareTo(givenDate)==0) {
            	   		System.out.println("\nperson with activity date " + startDate);
            	   		printPerson(eElement);
            	   		printActivity(eElement2);
            	   	}
               }
               
            }
         }
	}

	
}