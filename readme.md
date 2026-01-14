# Marketplace

### Key features
1. Products creating
2. Pin images to products
3. Login, Registration
4. User roles model
5. Searching product by title

### Additional info

Environment variable ```BG_COLOR``` using by me to deploying in different environment stages

### Deploying using IDE

1. Create ```marketplace``` database in MySql Database.
2. Go to ```src/main/resources/application.properties``` and replace  ```DATABASE_URL```, ```DATABASE_USERNAME```, ```DATABASE_PASSWORD``` by your values OR export this values to environment variables
3. Run using IDE by starting ```MarketplaceApplication``` class (another variants of deploying in the next items)

### Deploying using IDE

1. Create ```marketplace``` database in MySql Database.
2. Go to ```src/main/resources/application.properties``` and replace  ```DATABASE_URL```, ```DATABASE_USERNAME```, ```DATABASE_PASSWORD```, ```BG_COLOR```(optional) by your values OR export this values to environment variables
3. Run using IDE by starting ```MarketplaceApplication``` class (another variants of deploying in the next items)

### Deploying using Apache Tomcat

1. Create ```marketplace``` database in MySql Database.
2. Go to ```src/main/resources/application.properties``` and replace  ```DATABASE_URL```, ```DATABASE_USERNAME```, ```DATABASE_PASSWORD```, ```BG_COLOR```(optional) by your values OR export this values to environment variables
3. Go to root and run next command: ```mvn clean package``` it will start building and tests(if test failures add to the end of command ``` -DskipTests```)
4. Go to ```target/``` and run next command: ```java -jar marketplace-0.0.1-SNAPSHOT.war```
5. Put artifact from previous step to ```<your_tomcat_directory_path>/webapps/```
6. Go to ```http://localhost:8080```

### Deploying using Java Embedded Server

1. Create ```marketplace``` database in MySql Database.
2. Go to ```src/main/resources/application.properties``` and replace  ```DATABASE_URL```, ```DATABASE_USERNAME```, ```DATABASE_PASSWORD```, ```BG_COLOR```(optional) by your values OR export this values to environment variables
3. Go to ```pom.xml``` and find "packaging" and replace from ```war``` to ```jar```
4. Go to root and run next command: ```mvn clean package``` it will start building and tests(if test failures add to the end of command ``` -DskipTests```)
4. Go to ```target/``` and run next command: ```java -jar marketplace-0.0.1-SNAPSHOT.war```
5. Put artifact from previous step to ```<your_tomcat_directory_path>/webapps/```
6. Go to ```http://localhost:8080```