#To run the project
mvn clean install verify

mvn exec:java

#Test with Curl or Swagger UI
Swagger UI - http://localhost:8080/swagger-ui.html#/

#Curl Commands-
1. Get request to fetch all the FAQ-
#Request URL - http://localhost:8080/faq/getAllFAQ

#Curl- curl -X GET "http://localhost:8080/faq/getAllFAQ" -H "accept: */*"

2.Post request to insert a faq into the database

#Request URL- http://localhost:8080/faq/saveFAQ

#Curl- curl -X POST "http://localhost:8080/faq/saveFAQ" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"answers\": \"This is a knowledge base application\", \"question\": \"What is this ?\"}"

3. Post request for update the knowledge base 

#Request URL- http://localhost:8080/faq/updateFAQ

#Curl - curl -X POST "http://localhost:8080/faq/updateFAQ" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"answers\": \"Abhishek\", \"docID\": 2, \"question\": \"name ?\"}"

4. Delete a FAQ with specified ID.

#Request URL - http://localhost:8080/faq/delete/1


#Curl - curl -X DELETE "http://localhost:8080/faq/delete/1" -H "accept: */*"

