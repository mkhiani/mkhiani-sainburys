	dependencies needed
	===================
    maven

	how to compile
	==============
    - go to project root folder
    - execute the following command: 
		mvn compile

	how to run application
	=======================
    - go to project root folder
    - execute the following command: 
		mvn exec:java -Dexec.mainClass="com.sainsburys.SainsburyApp"
		
	how to run unit tests
	=====================
	- go to project root folder
	- execute the following command
	  mvn surefire:test
	
	how to command line output to a file
	- go to project root folder
	- execute the following command:
		mvn exec:java -Dexec.mainClass="com.sainsburys.SainsburyApp" > Output.txt
	
	troubleshooting
	===============
	- go to project root folder
	- if compile errors please execute the following command to download all dependencies, compile and run all unit tests
	  mvn install 	