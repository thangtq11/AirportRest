========================================================================
README.TXT
========================================================================
*** directory structures 
========================
* src			Application/Library sources.
* config		Configuration files.
* target		The target directory is used to store all output of the build.
* db			store upload files for loading data from disk.
* log			storing log files - for log4j.
* doc			documents of the trial projects: api document.
* javadoc		automatic generated java api document.
* pcap			captured files for debugging communication between client and servers. open with wireshark.
* pom.xml		for building maven project
* weather.iml		for building project
* README.TXT		Project's readme
* assignment.md		Project's requirements and Assignment Objectives
* package.sh		Packaging Your Code to a zip file.
* startServer.sh	killing current WeatherServer process and start it again.
* stopServer.sh		killing current WeatherServer process.
* airportLoader.sh	Loading an airport data file to Weather server. You'd start the server firstly.
* stressTest.sh		Loading a large airport data file and making stress testing to Weather server. You'd start the server firstly.
========================
* run-ws.sh		Please read the run-ws.sh carefull before running the file. 
			It'll launches the server and demonstrates a simple client hitting the REST endpoint follow tasks:
			- 1. rebuild the CrossOWeather project.
			- 2. running all unit test cases.
			- 3. starting the WeatherServer on port 9090.
			- 4. starting a blackbox testing using WeatherClient.
========================
*** Running the application
========================
1. unzip AirportRest.zip 
2. cd AirportRest
3. chmod +x *.sh
4. ./run-ws.sh
