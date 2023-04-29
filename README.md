# ReqRes API Test Automation

[ReqRes](https://reqres.in/) API Test Automation using Java and REST Assured.

## Installation Steps

1. [Fork](https://github.com/Tahanima/reqres-api-test-automation/fork) the repository.
2. Clone, i.e, download your copy of the repository to your local machine using
```
git clone https://github.com/[your_username]/reqres-api-test-automation.git
```
3. Import the project in [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).
4. Use IntelliJ IDEA to run your desired tests. Alternatively, you can use the terminal to run the tests, for example `./gradlew test`.

## Languages and Frameworks

The project uses the following:

- *[Java 11](https://openjdk.java.net/projects/jdk/11/)*
- *[REST Assured](https://rest-assured.io/)*
- *[Jackson](https://github.com/FasterXML/jackson)*
- *[Lombok](https://projectlombok.org/)*
- *[JUnit 5](https://junit.org/junit5/)*

## Project Architecture

```bash
```
📦 reqres-api-test-automation  
├─ .github  
│  └─ workflows  
│     └─ test-execution.yml  
├─ .gitignore  
├─ LICENSE  
├─ README.md  
├─ build.gradle  
├─ gradle  
│  └─ wrapper  
│     ├─ gradle-wrapper.jar  
│     └─ gradle-wrapper.properties  
├─ gradlew  
├─ gradlew.bat  
├─ settings.gradle  
└─ src  
   ├─ main  
   │  └─ java  
   │     └─ io  
   │        └─ github  
   │           └─ tahanima  
   │              ├─ client  
   │              │  ├─ AuthClient.java  
   │              │  ├─ BaseClient.java  
   │              │  ├─ ResourceClient.java  
   │              │  └─ UserClient.java  
   │              └─ model  
   │                 ├─ Auth.java  
   │                 ├─ Error.java  
   │                 ├─ List.java  
   │                 ├─ Resource.java  
   │                 ├─ ResourceData.java  
   │                 ├─ ResourceList.java  
   │                 ├─ Support.java  
   │                 ├─ User.java  
   │                 ├─ UserData.java  
   │                 └─ UserList.java  
   └─ test  
      ├─ java  
      │  └─ io  
      │     └─ github  
      │        └─ tahanima  
      │           └─ api  
      │              ├─ AuthTest.java  
      │              ├─ ResourceTest.java  
      │              └─ UserTest.java  
      └─ resources  
         └─ junit-platform.properties  
```
