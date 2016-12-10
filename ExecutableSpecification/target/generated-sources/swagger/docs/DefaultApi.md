# DefaultApi

All URIs are relative to *http://localhost:8090/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**registrationsGet**](DefaultApi.md#registrationsGet) | **GET** /registrations | 
[**registrationsPost**](DefaultApi.md#registrationsPost) | **POST** /registrations | 


<a name="registrationsGet"></a>
# **registrationsGet**
> List&lt;RegistrationSummary&gt; registrationsGet()



### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    List<RegistrationSummary> result = apiInstance.registrationsGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#registrationsGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;RegistrationSummary&gt;**](RegistrationSummary.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="registrationsPost"></a>
# **registrationsPost**
> registrationsPost(body)



### Example
```java
// Import classes:
//import ch.heigvd.gamification.ApiException;
//import ch.heigvd.gamification.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Registration body = new Registration(); // Registration | The info required to register an application
try {
    apiInstance.registrationsPost(body);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#registrationsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Registration**](Registration.md)| The info required to register an application |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

