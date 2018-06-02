package jaxb.main;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import jaxb.xjc.*;
import xpath.reader.ActivityReader;

//Object to Xml (Marshalling)
public class  ObjectToXml{
	
	
	public static ObjectFactory factory = new ObjectFactory();
	public static People people = factory.createPeople();

	public static void initializeDB() throws DatatypeConfigurationException {
		
		XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar("2018-01-15T09:00:00.0");
		ActivitypreferenceType ar= setActivity((byte) 121,"Hiking1","Hiking in monte bianco1","Torino",date);
		date = DatatypeFactory.newInstance().newXMLGregorianCalendar("1980-06-20");
		Person p= setPerson((byte) 21, date, "george1","michael1",ar);
		
		date = DatatypeFactory.newInstance().newXMLGregorianCalendar("2018-01-16T09:00:00.0");
		ar= setActivity((byte) 122,"Hiking2","Hiking in monte bianco2","Torino",date);
		date = DatatypeFactory.newInstance().newXMLGregorianCalendar("1980-06-19");
		Person p2= setPerson((byte) 22, date, "george2","michael2",ar);
		
		date = DatatypeFactory.newInstance().newXMLGregorianCalendar("2018-01-17T09:00:00.0");
		ar= setActivity((byte) 123,"Hiking3","Hiking in monte bianco3","Torino",date);
		date = DatatypeFactory.newInstance().newXMLGregorianCalendar("1980-06-21");
		Person p3= setPerson((byte) 23, date, "george3","michael3",ar);
		
		
		people.getPerson().add(p);
		people.getPerson().add(p2);
		people.getPerson().add(p3);
	}

	private static Person setPerson(byte b, XMLGregorianCalendar date, String string, String string2,
			ActivitypreferenceType ar) {
		Person p=factory.createPerson();
		p.setId(b);
		p.setBirthdate(date);
		p.setFirstname(string);
		p.setLastname(string2);
		p.setActivitypreference(ar);
		return p;
	}

	private static ActivitypreferenceType setActivity(byte b, String string, String string2, String string3,
			XMLGregorianCalendar date) {
		ActivitypreferenceType ar= factory.createActivitypreferenceType();
		ar.setId(b);
		ar.setName(string);
		ar.setDescription(string2);
		ar.setPlace(string3);
		ar.setStartdate(date);
		return ar;
	}

	public static void main(String[] args) throws JAXBException, DatatypeConfigurationException, ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		
		JAXBContext contextObj = JAXBContext.newInstance(People.class); 
		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty("jaxb.formatted.output", new Boolean(true));
		
		// initializing DB
		initializeDB();
		JAXBElement<People> peopleElement = factory.createPeople(people);
		marshallerObj.marshal(peopleElement, new FileOutputStream("marshalling.xml")); // Marshalling into a file
		
		//printing to system output in better format
		ActivityReader test = new ActivityReader();
		test.loadXML("marshalling.xml");
		test.printall();
		
		// Marshalling into the system default output
//		marshallerObj.marshal(people, System.out);
		

	}

}
