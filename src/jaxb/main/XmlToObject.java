package jaxb.main;

import java.io.File;
import java.util.List;
import javax.xml.bind.*;

import org.xml.sax.SAXException;

import jaxb.xjc.*;

public class XmlToObject {

	public static void main(String[] args) throws JAXBException, SAXException {
		File file = new File("database.xml"); 
		XmlToObject jaxbUnmarshaller = new XmlToObject();

		jaxbUnmarshaller.unMarshall(file);
		

	}
	private void unMarshall(File file) throws JAXBException, SAXException {
		JAXBContext jaxbContext = JAXBContext.newInstance("jaxb.xjc");

		Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();

		@SuppressWarnings("unchecked")
		JAXBElement<People> peopleElement = (JAXBElement<People>) unMarshaller.unmarshal(file);

		People pe = peopleElement.getValue();
		
		List<Person> person=pe.getPerson();
		for(Person p: person) {
			printPerson(p);
			printActivity(p.getActivitypreference());
		}
		
	}
	public void printActivity(ActivitypreferenceType act) {
		System.out.println("Activity Preference id : " + act.getId());
        System.out.println("	Name of the Activity : " + act.getName());
        System.out.println("	Description : " + act.getDescription());
        System.out.println("	Place : " + act.getPlace());
        System.out.println("	Start Date : " + act.getStartdate());
	}
	public void printPerson(Person p) {
		System.out.println("\nPerson id :" + p.getId());
        System.out.println("	First Name : " + p.getFirstname());
        System.out.println("	Last Name : " + p.getLastname());
        System.out.println("	Date of Birth : " + p.getBirthdate());
	}

}
