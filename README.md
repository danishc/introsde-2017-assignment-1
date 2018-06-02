# introsde-2017-assignment-1


Identification:
NAME: Cheema Danish Asghar
EMAIL: danishasghar.cheema@studenti.unitn.it

Server Git ripo: https://github.com/danishc/introsde-2017-assignment-3-server



Project Requirements:
Here i am implementing a server which is used by a University who wants to manage their student�s data and their relative activities, each student is consist of firstname, lastname and dateofbirth. on the other hand the students can have multiple activities and each activity is consist of name, type, description, place, evaluation and start date.

The project is consisting of different classes where Dao class is used to connect to database with the properties defined in persistence.xml. then there are Activity and person classes which contains the initialization of different parameters and and JPA and XML annotations in order to persist the data to database and move around the data via JAXB. also there is Data Initialization class which is execute once at the deploy time by the webservice implementation class. webservce classes define the interface and implementation of that interface which is available to the client side with the help SOAP and JAX-WS API.

Implementation:
All the following methods are available via wsdl url as required in the assignment
Method #1: list all the people in the database.
Method #2: prints only the personal info of a person identified by given id.
Method #3: update the personal info of the person without modifying the related activities.
Method #4: create a new person and activity if provided and return a person object.
Method #5: delete a person by given id and returns the id if successful.
Method #6: return the list of values of {activity_type} (e.g. sport) for a Person identified by {id}
Method #7: return the list of all activities.
Method #8: return the value identified by {activity_id} for a Person identified by {id}
Method #9: save a new activity object {activity} of a Person identified by {id}
Method #10: update the activity identified with {activity.id}, related to the Person identified by {id}
Method #11 (Extra): it update the activity identified with {activity.id}, related to the Person identified by {id} with the value that define a specific value of preferences (in my case its out of 10).
Method #12 (Extra): return the best preference (or preferences if there are more) of the Person identified by {id} from his/her list of preferences. 

NOTE: in the code form method 6 onwards i modify the name of the methods a bit to be more specific what the method does.

Execution: 
1: clone or download the code from server git repo.
2: run following commands on command line 
-> $ mvn install
-> $ mvn exec:java -Dexec.mainClass="introsde.assignment3.soap.endpoint.PeoplePublisher"

