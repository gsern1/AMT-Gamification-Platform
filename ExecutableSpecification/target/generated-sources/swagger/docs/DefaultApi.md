# DefaultApi

All URIs are relative to *http://localhost:8090/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addApplication**](DefaultApi.md#addApplication) | **POST** /application | Post new application on the platform
[**addBadge**](DefaultApi.md#addBadge) | **POST** /badges | Creates a new badge
[**addEvent**](DefaultApi.md#addEvent) | **POST** /events | Post events on the platform
[**addPointScale**](DefaultApi.md#addPointScale) | **POST** /pointScales | Creates a new pointScale
[**deleteApplication**](DefaultApi.md#deleteApplication) | **DELETE** /application | Delete an existing application
[**deleteBadge**](DefaultApi.md#deleteBadge) | **DELETE** /badges/{badgeId} | Delete an existing badge
[**deletePointScale**](DefaultApi.md#deletePointScale) | **DELETE** /pointScales/{pointScaleId} | Delete an existing pointScale
[**findBadge**](DefaultApi.md#findBadge) | **GET** /badges/{badgeId} | Returns a single badge
[**findBadges**](DefaultApi.md#findBadges) | **GET** /badges | Returns every badges
[**findPointScale**](DefaultApi.md#findPointScale) | **GET** /pointScales/{pointScaleId} | Returns a single pointScale
[**findPointScales**](DefaultApi.md#findPointScales) | **GET** /pointScales | Returns every pointScales
[**findUserBadges**](DefaultApi.md#findUserBadges) | **GET** /user/{userId}/badges | Returns a user&#39;s badges
[**findUserPointScales**](DefaultApi.md#findUserPointScales) | **GET** /user/{userId}/pointScales | Returns a user&#39;s pointScales
[**findUsers**](DefaultApi.md#findUsers) | **GET** /users | Returns every users
[**leaderboard**](DefaultApi.md#leaderboard) | **GET** /leaderboard | Returns the leaderboard
[**loginApplication**](DefaultApi.md#loginApplication) | **GET** /application | Returns a the application token
[**updateBadge**](DefaultApi.md#updateBadge) | **PUT** /badges/{badgeId} | Update an existing badge
[**updatePointScale**](DefaultApi.md#updatePointScale) | **PUT** /pointScales/{pointScaleId} | Update an existing pointScale


<a name="addApplication"></a>
# **addApplication**
> addApplication(application)

Post new application on the platform

Creates a new application 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Application application = new Application(); // Application | application object to add to the platform
try {
    apiInstance.addApplication(application);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#addApplication");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **application** | [**Application**](Application.md)| application object to add to the platform |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="addBadge"></a>
# **addBadge**
> Badge addBadge(badge, token)

Creates a new badge

Creates a new badge 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Badge badge = new Badge(); // Badge | Badge object to add to the store
String token = "token_example"; // String | token to be passed as a header
try {
    Badge result = apiInstance.addBadge(badge, token);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#addBadge");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **badge** | [**Badge**](Badge.md)| Badge object to add to the store |
 **token** | **String**| token to be passed as a header |

### Return type

[**Badge**](Badge.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="addEvent"></a>
# **addEvent**
> addEvent(event, token)

Post events on the platform

Creates a new event 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Event event = new Event(); // Event | event object to add to the platform
String token = "token_example"; // String | token to be passed as a header
try {
    apiInstance.addEvent(event, token);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#addEvent");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **event** | [**Event**](Event.md)| event object to add to the platform |
 **token** | **String**| token to be passed as a header |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="addPointScale"></a>
# **addPointScale**
> PointScale addPointScale(pointScale, token)

Creates a new pointScale

Creates a new pointScale 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
PointScale pointScale = new PointScale(); // PointScale | pointScale object to add to the store
String token = "token_example"; // String | token to be passed as a header
try {
    PointScale result = apiInstance.addPointScale(pointScale, token);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#addPointScale");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pointScale** | [**PointScale**](PointScale.md)| pointScale object to add to the store |
 **token** | **String**| token to be passed as a header |

### Return type

[**PointScale**](PointScale.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteApplication"></a>
# **deleteApplication**
> deleteApplication(token)

Delete an existing application

Delete an existing application 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String token = "token_example"; // String | token to be passed as a header
try {
    apiInstance.deleteApplication(token);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deleteApplication");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**| token to be passed as a header |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteBadge"></a>
# **deleteBadge**
> deleteBadge(badgeId, token)

Delete an existing badge

Delete an existing badge 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Long badgeId = 789L; // Long | Id of the badge that needs to be deleted
String token = "token_example"; // String | token to be passed as a header
try {
    apiInstance.deleteBadge(badgeId, token);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deleteBadge");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **badgeId** | **Long**| Id of the badge that needs to be deleted |
 **token** | **String**| token to be passed as a header |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deletePointScale"></a>
# **deletePointScale**
> deletePointScale(pointScaleId, token)

Delete an existing pointScale

Delete an existing pointScale 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Long pointScaleId = 789L; // Long | Id of the pointScale that needs to be deleted
String token = "token_example"; // String | token to be passed as a header
try {
    apiInstance.deletePointScale(pointScaleId, token);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deletePointScale");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pointScaleId** | **Long**| Id of the pointScale that needs to be deleted |
 **token** | **String**| token to be passed as a header |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findBadge"></a>
# **findBadge**
> Badge findBadge(badgeId, token)

Returns a single badge

Returns a single badge 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Long badgeId = 789L; // Long | ID of badge to fetch
String token = "token_example"; // String | token to be passed as a header
try {
    Badge result = apiInstance.findBadge(badgeId, token);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#findBadge");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **badgeId** | **Long**| ID of badge to fetch |
 **token** | **String**| token to be passed as a header |

### Return type

[**Badge**](Badge.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findBadges"></a>
# **findBadges**
> List&lt;Badge&gt; findBadges(token)

Returns every badges

Returns every badges 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String token = "token_example"; // String | token to be passed as a header
try {
    List<Badge> result = apiInstance.findBadges(token);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#findBadges");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**| token to be passed as a header |

### Return type

[**List&lt;Badge&gt;**](Badge.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findPointScale"></a>
# **findPointScale**
> PointScale findPointScale(pointScaleId, token)

Returns a single pointScale

Returns a single pointScale 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Long pointScaleId = 789L; // Long | ID of pointScale to fetch
String token = "token_example"; // String | token to be passed as a header
try {
    PointScale result = apiInstance.findPointScale(pointScaleId, token);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#findPointScale");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pointScaleId** | **Long**| ID of pointScale to fetch |
 **token** | **String**| token to be passed as a header |

### Return type

[**PointScale**](PointScale.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findPointScales"></a>
# **findPointScales**
> List&lt;PointScale&gt; findPointScales(token)

Returns every pointScales

Returns every pointScales 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String token = "token_example"; // String | token to be passed as a header
try {
    List<PointScale> result = apiInstance.findPointScales(token);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#findPointScales");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**| token to be passed as a header |

### Return type

[**List&lt;PointScale&gt;**](PointScale.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findUserBadges"></a>
# **findUserBadges**
> List&lt;Badge&gt; findUserBadges(userId, token)

Returns a user&#39;s badges

Returns a user&#39;s badges 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Long userId = 789L; // Long | ID of user
String token = "token_example"; // String | token to be passed as a header
try {
    List<Badge> result = apiInstance.findUserBadges(userId, token);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#findUserBadges");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| ID of user |
 **token** | **String**| token to be passed as a header |

### Return type

[**List&lt;Badge&gt;**](Badge.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findUserPointScales"></a>
# **findUserPointScales**
> List&lt;PointScale&gt; findUserPointScales(userId, token)

Returns a user&#39;s pointScales

Returns a user&#39;s pointScales 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Long userId = 789L; // Long | ID of user
String token = "token_example"; // String | token to be passed as a header
try {
    List<PointScale> result = apiInstance.findUserPointScales(userId, token);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#findUserPointScales");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Long**| ID of user |
 **token** | **String**| token to be passed as a header |

### Return type

[**List&lt;PointScale&gt;**](PointScale.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findUsers"></a>
# **findUsers**
> List&lt;User&gt; findUsers(token)

Returns every users

Returns every users 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String token = "token_example"; // String | token to be passed as a header
try {
    List<User> result = apiInstance.findUsers(token);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#findUsers");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**| token to be passed as a header |

### Return type

[**List&lt;User&gt;**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="leaderboard"></a>
# **leaderboard**
> List&lt;User&gt; leaderboard(token)

Returns the leaderboard

Returns the leaderboard 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String token = "token_example"; // String | token to be passed as a header
try {
    List<User> result = apiInstance.leaderboard(token);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#leaderboard");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**| token to be passed as a header |

### Return type

[**List&lt;User&gt;**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="loginApplication"></a>
# **loginApplication**
> PointScale loginApplication(application)

Returns a the application token

Returns the application token 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Application application = new Application(); // Application | application object to add to the store
try {
    PointScale result = apiInstance.loginApplication(application);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#loginApplication");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **application** | [**Application**](Application.md)| application object to add to the store |

### Return type

[**PointScale**](PointScale.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateBadge"></a>
# **updateBadge**
> Badge updateBadge(badge, badgeId, token)

Update an existing badge

Update an existing badge 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Badge badge = new Badge(); // Badge | Badge object to add to the store
Long badgeId = 789L; // Long | Id of the badge that needs to be updated
String token = "token_example"; // String | token to be passed as a header
try {
    Badge result = apiInstance.updateBadge(badge, badgeId, token);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#updateBadge");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **badge** | [**Badge**](Badge.md)| Badge object to add to the store |
 **badgeId** | **Long**| Id of the badge that needs to be updated |
 **token** | **String**| token to be passed as a header |

### Return type

[**Badge**](Badge.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updatePointScale"></a>
# **updatePointScale**
> PointScale updatePointScale(pointScale, pointScaleId, token)

Update an existing pointScale

Update an existing pointScale 

### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
PointScale pointScale = new PointScale(); // PointScale | pointScale object to add to the store
Long pointScaleId = 789L; // Long | Id of the pointScale that needs to be updated
String token = "token_example"; // String | token to be passed as a header
try {
    PointScale result = apiInstance.updatePointScale(pointScale, pointScaleId, token);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#updatePointScale");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pointScale** | [**PointScale**](PointScale.md)| pointScale object to add to the store |
 **pointScaleId** | **Long**| Id of the pointScale that needs to be updated |
 **token** | **String**| token to be passed as a header |

### Return type

[**PointScale**](PointScale.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

