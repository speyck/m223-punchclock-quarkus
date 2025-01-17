<h1 align="center"><img src="https://github.com/speyck/m223-punchclock-quarkus/blob/main/docs/zli-logo.png" width="75px"/> M223</h1>

## Installation
1. Clone this repository into any directory on your computer: `git clone https://github.com/speyck/m223-punchclock-quarkus/` 
2. Make sure that you have at least OpenJDK 11 installed (just download the [latest](https://openjdk.java.net/projects/jdk/) version) and `JAVA_HOME` is set as enviroment variable
3. Install Apache Maven 3.8.1 or higher (latest version can be found [here](https://maven.apache.org/download.cgi))

## How to run
To run the application you need to
1. Head into the repository directory `cd m223-punchclock-quarkus`
2. Start the `start.bat` file either from the file explorer or cmd (for powershell: `start start.bat`)
 
## Avaliable services after start
There are two services (apart of the main API and GUI of coure) that you can use to use the API and manage the database:

### Swagger UI
The swagger UI shows all avaiable requests and you can make request right on the page.
Head on to http://localhost:8080/q/swagger-ui/ to use it

### H2 Console
The H2 Console allows insight onto the database and editing similar to PhpMyAdmin.
Head on to http://localhost:8080/h2/ to use it. You need the following credentials to login:

- **JDBC URL**: jdbc:h2:mem:punchclock
- **User Name**: zli
- **Password**: zli

## GUI Login
Here's the default user to log into the GUI app:

- **Username**: zli
- **Password**: root

## Planing
All diagrams and planing documentation can be found in the [/docs/planing.md](https://github.com/speyck/m223-punchclock-quarkus/blob/main/docs/planing.md) file.

## Disclaimer
Sadly, the GUI is not working as it should. There's a error that I just can't fix and I had no time left to fix it. The API can still be tested though from the swagger ui and it should function with no problems. :)
