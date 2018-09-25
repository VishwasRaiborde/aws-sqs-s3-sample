The following project is an impelmentation for 
a) S3 feature 
b) SQS messaging 


the code base is developed on spring boot and build tool is gradle
by vishwas raiborde

steps to run the application 

1) import project as gradle project into ecllipse
2) in gradle task console build the project 
3) all the required depencies should be downloaded and successfull build should occur 
    please check network access if libraries are not downloaded 

4) on successfull completion of build 
   run the com.ams.main.Application class as main Java Application 

5) application will be accessable via http://localhost:8080/ams/swagger-ui.html


note : AWS_SECRET_key and AWS_ACCESS_key needs to be set as environment variable
       JMS_QUEUE = "MessageProcessorQueue" queue by this name should be present currenty this is not configurable
