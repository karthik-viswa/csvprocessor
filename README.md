# CSV Processor

## Instructions
* Check out the project and build using `gradlew clean build`
* This produces a jar file `build\libs\csvprocessor-0.0.1-SNAPSHOT.jar`
* Run the jar file (use Java 8) using `java -jar -Dupload.path=<upload-path> build\libs\csvprocessor-0.0.1-SNAPSHOT.jar`
  * where `<upload-path>` is the path to the directory where uploaded files will be stored. *you must ensure this directory has already been created*
  * if you do not specify the `upload.path` property, it defaults to what is set in `src\main\resources\application.properties`
* when the jar is running, go to `http:localhost:8080` to access the interface which allows you to upload a csv file and view the results
* error handling / validation has not been implemented. the application assumes that the csv you upload is in the correct format