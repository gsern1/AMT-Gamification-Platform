# swagger-java-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-java-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-java-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import ch.heigvd.gamification.*;
import ch.heigvd.gamification.auth.*;
import ch.heigvd.gamification.model.*;
import ch.heigvd.gamification.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        Application application = new Application(); // Application | application object to add to the platform
        try {
            apiInstance.addApplication(application);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#addApplication");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://localhost:8090/api*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DefaultApi* | [**addApplication**](docs/DefaultApi.md#addApplication) | **POST** /application | Post new application on the platform
*DefaultApi* | [**addBadge**](docs/DefaultApi.md#addBadge) | **POST** /badges | Creates a new badge
*DefaultApi* | [**addEvent**](docs/DefaultApi.md#addEvent) | **POST** /events | Post events on the platform
*DefaultApi* | [**addPointScale**](docs/DefaultApi.md#addPointScale) | **POST** /pointScales | Creates a new pointScale
*DefaultApi* | [**deleteApplication**](docs/DefaultApi.md#deleteApplication) | **DELETE** /application | Delete an existing application
*DefaultApi* | [**deleteBadge**](docs/DefaultApi.md#deleteBadge) | **DELETE** /badges/{badgeId} | Delete an existing badge
*DefaultApi* | [**deletePointScale**](docs/DefaultApi.md#deletePointScale) | **DELETE** /pointScales/{pointScaleId} | Delete an existing pointScale
*DefaultApi* | [**findBadge**](docs/DefaultApi.md#findBadge) | **GET** /badges/{badgeId} | Returns a single badge
*DefaultApi* | [**findBadges**](docs/DefaultApi.md#findBadges) | **GET** /badges | Returns every badges
*DefaultApi* | [**findPointScale**](docs/DefaultApi.md#findPointScale) | **GET** /pointScales/{pointScaleId} | Returns a single pointScale
*DefaultApi* | [**findPointScales**](docs/DefaultApi.md#findPointScales) | **GET** /pointScales | Returns every pointScales
*DefaultApi* | [**findUserBadges**](docs/DefaultApi.md#findUserBadges) | **GET** /user/{userId}/badges | Returns a user&#39;s badges
*DefaultApi* | [**findUserPointScales**](docs/DefaultApi.md#findUserPointScales) | **GET** /user/{userId}/pointScales | Returns a user&#39;s pointScales
*DefaultApi* | [**findUsers**](docs/DefaultApi.md#findUsers) | **GET** /users | Returns every users
*DefaultApi* | [**leaderboard**](docs/DefaultApi.md#leaderboard) | **GET** /leaderboard | Returns the leaderboard
*DefaultApi* | [**loginApplication**](docs/DefaultApi.md#loginApplication) | **GET** /application | Returns a the application token
*DefaultApi* | [**updateBadge**](docs/DefaultApi.md#updateBadge) | **PUT** /badges/{badgeId} | Update an existing badge
*DefaultApi* | [**updatePointScale**](docs/DefaultApi.md#updatePointScale) | **PUT** /pointScales/{pointScaleId} | Update an existing pointScale


## Documentation for Models

 - [Application](docs/Application.md)
 - [Badge](docs/Badge.md)
 - [ErrorModel](docs/ErrorModel.md)
 - [Event](docs/Event.md)
 - [PointScale](docs/PointScale.md)
 - [User](docs/User.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author

guillaume.serneels@heig-vd.ch

