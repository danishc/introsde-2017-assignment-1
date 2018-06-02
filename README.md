# introsde-2017-assignment-1


### Identification:
NAME: Cheema Danish Asghar  
EMAIL: danishasghar.cheema@studenti.unitn.it  

```
Git ripo: https://github.com/danishc/introsde-2017-assignment-1
```


### Project Requirements:  
. Retrieving the data from xml file using XPATH and running different queries on the data.  
. Creating the XML schema XSD file for the database.xml document of people.  
. Writing java applications that does the marshalling and un-marshalling using classes generated with JAXB XJC.  
. Create 3 persons using java and marshal them to JSON  
  
  
### Implementation:  
```
ActivityReader.java  
Method #1: list all the people in the database.  
Method #2: prints only the personal info of a person identified by given id.  
Method #3: prints only the personal info of a persons with startdate > 2017-13-10.  
```
```
ObjectToXml.java  
Method #4: Create 3 persons using java and marshal them to marshaling.xml and also printing to standard output.  
```
```
XmlToObject.java  
Method #5: executing un-marshaling from database.xml.  
```
```
ObjectToJson.java  
Method #6: Create 3 persons using java and marshal them to people.json and also printing to standard output.  
```
  
### Execution:   
1: clone or download the code from git repo.  
2: run following commands on command line   
```
ant execute.evaluation  
```
