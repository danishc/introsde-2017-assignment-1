package jaxb.json;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import jaxb.xjc.ActivitypreferenceType;
import jaxb.xjc.People;
import jaxb.xjc.Person;

public class ObjectToJson {
	
	public static People people = new People();

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
		Person p=new Person();
		p.setId(b);
		p.setBirthdate(date);
		p.setFirstname(string);
		p.setLastname(string2);
		p.setActivitypreference(ar);
		return p;
	}

	private static ActivitypreferenceType setActivity(byte b, String string, String string2, String string3,
			XMLGregorianCalendar date) {
		ActivitypreferenceType ar= new ActivitypreferenceType();
		ar.setId(b);
		ar.setName(string);
		ar.setDescription(string2);
		ar.setPlace(string3);
		ar.setStartdate(date);
		return ar;
	}

public static void main(String[] args) throws DatatypeConfigurationException, IOException {
		
		// Jackson Object Mapper 
		ObjectMapper mapper = new ObjectMapper();
	
		// Adding the Jackson Module to process JAXB annotations
		JaxbAnnotationModule module = new JaxbAnnotationModule();
    
		// configure as necessary
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		
		// initializing DB
		initializeDB();
		
		String result = mapper.writeValueAsString(people);
        System.out.println(result);
        mapper.writeValue(new File("people.json"), people);
		
	}
}
