# AccountsApi

All URIs are relative to *https://core.onetrade.dub.dev.api.pagonxt.corp/accounts_validation*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAccountVerifications**](AccountsApi.md#getAccountVerifications) | **GET** /accounts/{account_id}/verifications | Get account verifications |
| [**getClientAccountDetails**](AccountsApi.md#getClientAccountDetails) | **GET** /accounts/{account_id} | Get account details |
| [**getClientAccountsList**](AccountsApi.md#getClientAccountsList) | **GET** /accounts | Retrieves the eMoney accounts list of PagoNXT |



## getAccountVerifications

> AccountVerificationsResponse getAccountVerifications(accountId, xSantanderClientId, accountIdType, accountIdCountry, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice)

Get account verifications

Retrieves information from the account given an accountId.

### Example

```java
// Import classes:
import com.santander.getnet.external.client.model.ApiClient;
import com.santander.getnet.external.client.model.ApiException;
import com.santander.getnet.external.client.model.Configuration;
import com.santander.getnet.external.client.model.auth.*;
import com.santander.getnet.external.client.model.models.*;
import com.santander.getnet.external.client.model.api.AccountsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://core.onetrade.dub.dev.api.pagonxt.corp/accounts_validation");
        
        // Configure OAuth2 access token for authorization: JWTProfile
        OAuth JWTProfile = (OAuth) defaultClient.getAuthentication("JWTProfile");
        JWTProfile.setAccessToken("YOUR ACCESS TOKEN");

        // Configure HTTP bearer authorization: bearerJWTAuth
        HttpBearerAuth bearerJWTAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerJWTAuth");
        bearerJWTAuth.setBearerToken("BEARER TOKEN");

        AccountsApi apiInstance = new AccountsApi(defaultClient);
        String accountId = "0589bd6c-cca4-4b46-9c8a-b786171e66e0"; // String | Number of the account.  The number format is defined in the \"account_id_type\" parameter. 
        String xSantanderClientId = "test"; // String | Client ID header.
        String accountIdType = "iban"; // String | Format used for the account number in the 'account_id' property.  The possible values are: - iban = IBAN code - bban = Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid = Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
        String accountIdCountry = "GB"; // String | Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
        String xB3Traceid = "463ac35c9f6413ad48485a3953bb6124"; // String | Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
        String xB3Parentspanid = "20000000000001"; // String | Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
        String xB3Spanid = "a2fb4a1d1a96d312"; // String | Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
        String xB3Sampled = "1"; // String | Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 = Deny - 1 = Accept 
        String xSantanderDevice = "web"; // String | Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
        try {
            AccountVerificationsResponse result = apiInstance.getAccountVerifications(accountId, xSantanderClientId, accountIdType, accountIdCountry, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountsApi#getAccountVerifications");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **accountId** | **String**| Number of the account.  The number format is defined in the \&quot;account_id_type\&quot; parameter.  | |
| **xSantanderClientId** | **String**| Client ID header. | |
| **accountIdType** | **String**| Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2)  | [optional] [enum: iban, bban, uuid] |
| **accountIdCountry** | **String**| Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/).  | [optional] |
| **xB3Traceid** | **String**| Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long.  | [optional] |
| **xB3Parentspanid** | **String**| Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree.  | [optional] |
| **xB3Spanid** | **String**| Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId.  | [optional] |
| **xB3Sampled** | **String**| Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept  | [optional] |
| **xSantanderDevice** | **String**| Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection.  | [optional] |

### Return type

[**AccountVerificationsResponse**](AccountVerificationsResponse.md)

### Authorization

[JWTProfile](../README.md#JWTProfile), [bearerJWTAuth](../README.md#bearerJWTAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Returns an OK. Account verifications are retrieved. |  -  |
| **204** | No content |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **404** | The account does not exist. |  -  |
| **405** | Method Not Allowed |  -  |
| **406** | Not Acceptable |  -  |
| **409** | Conflict |  -  |
| **413** | Payload too large |  -  |
| **414** | URI too large |  -  |
| **415** | Unsupported Media Type |  -  |
| **422** | Unprocessable Entity |  -  |
| **429** | Too Many Requests |  -  |
| **500** | Internal Server Error |  -  |
| **503** | Service Down Error |  -  |
| **504** | Gateway timeout |  -  |
| **0** | Internal Server Error |  -  |


## getClientAccountDetails

> AccountDetailsResponse getClientAccountDetails(accountId, xSantanderClientId, accountIdType, accountIdCountry, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice)

Get account details

Gets account details based on an account ID.

### Example

```java
// Import classes:
import com.santander.getnet.external.client.model.ApiClient;
import com.santander.getnet.external.client.model.ApiException;
import com.santander.getnet.external.client.model.Configuration;
import com.santander.getnet.external.client.model.auth.*;
import com.santander.getnet.external.client.model.models.*;
import com.santander.getnet.external.client.model.api.AccountsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://core.onetrade.dub.dev.api.pagonxt.corp/accounts_validation");
        
        // Configure OAuth2 access token for authorization: JWTProfile
        OAuth JWTProfile = (OAuth) defaultClient.getAuthentication("JWTProfile");
        JWTProfile.setAccessToken("YOUR ACCESS TOKEN");

        // Configure HTTP bearer authorization: bearerJWTAuth
        HttpBearerAuth bearerJWTAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerJWTAuth");
        bearerJWTAuth.setBearerToken("BEARER TOKEN");

        AccountsApi apiInstance = new AccountsApi(defaultClient);
        String accountId = "0589bd6c-cca4-4b46-9c8a-b786171e66e0"; // String | Number of the account.  The number format is defined in the \"account_id_type\" parameter. 
        String xSantanderClientId = "test"; // String | Client ID header.
        String accountIdType = "iban"; // String | Format used for the account number in the 'account_id' property.  The possible values are: - iban = IBAN code - bban = Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid = Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
        String accountIdCountry = "GB"; // String | Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
        String xB3Traceid = "463ac35c9f6413ad48485a3953bb6124"; // String | Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
        String xB3Parentspanid = "20000000000001"; // String | Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
        String xB3Spanid = "a2fb4a1d1a96d312"; // String | Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
        String xB3Sampled = "1"; // String | Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 = Deny - 1 = Accept 
        String xSantanderDevice = "web"; // String | Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
        try {
            AccountDetailsResponse result = apiInstance.getClientAccountDetails(accountId, xSantanderClientId, accountIdType, accountIdCountry, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountsApi#getClientAccountDetails");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **accountId** | **String**| Number of the account.  The number format is defined in the \&quot;account_id_type\&quot; parameter.  | |
| **xSantanderClientId** | **String**| Client ID header. | |
| **accountIdType** | **String**| Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2)  | [optional] [enum: iban, bban, uuid] |
| **accountIdCountry** | **String**| Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/).  | [optional] |
| **xB3Traceid** | **String**| Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long.  | [optional] |
| **xB3Parentspanid** | **String**| Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree.  | [optional] |
| **xB3Spanid** | **String**| Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId.  | [optional] |
| **xB3Sampled** | **String**| Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept  | [optional] |
| **xSantanderDevice** | **String**| Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection.  | [optional] |

### Return type

[**AccountDetailsResponse**](AccountDetailsResponse.md)

### Authorization

[JWTProfile](../README.md#JWTProfile), [bearerJWTAuth](../README.md#bearerJWTAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Example response |  -  |
| **204** | No content |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **404** | The account does not exist in the system. |  -  |
| **405** | Method Not Allowed |  -  |
| **406** | Not Acceptable |  -  |
| **409** | Conflict |  -  |
| **413** | Payload too large |  -  |
| **414** | URI too large |  -  |
| **415** | Unsupported Media Type |  -  |
| **422** | Unprocessable Entity |  -  |
| **429** | Too Many Requests |  -  |
| **500** | Internal Server Error |  -  |
| **503** | Service Down Error |  -  |
| **504** | Gateway timeout |  -  |
| **0** | Internal Server Error |  -  |


## getClientAccountsList

> AccountListResponse getClientAccountsList(xSantanderClientId, customerId, expand, status, historical, accountsList, accountIdType, accountIdCountry, customerName, legalEntityName, legalEntityShortCodeList, productIdList, productName, nickname, limit, offset, sort, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice, virtualAccountStructureId, realAccountId, realAccountIdType, realAccountIdCountry, additionalInfo, fromCreatedAt, toCreatedAt, accountingName, accountingIdList)

Retrieves the eMoney accounts list of PagoNXT

Retrieves a list for all the accounts of a PagoNXT customer.  This operation is available to PagoNXT staff and 3rd parties.  For the customer Id requested, it will return all its accounts.  Sort order by default is opening date ascending. 

### Example

```java
// Import classes:
import com.santander.getnet.external.client.model.ApiClient;
import com.santander.getnet.external.client.model.ApiException;
import com.santander.getnet.external.client.model.Configuration;
import com.santander.getnet.external.client.model.auth.*;
import com.santander.getnet.external.client.model.models.*;
import com.santander.getnet.external.client.model.api.AccountsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://core.onetrade.dub.dev.api.pagonxt.corp/accounts_validation");
        
        // Configure OAuth2 access token for authorization: JWTProfile
        OAuth JWTProfile = (OAuth) defaultClient.getAuthentication("JWTProfile");
        JWTProfile.setAccessToken("YOUR ACCESS TOKEN");

        // Configure HTTP bearer authorization: bearerJWTAuth
        HttpBearerAuth bearerJWTAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerJWTAuth");
        bearerJWTAuth.setBearerToken("BEARER TOKEN");

        AccountsApi apiInstance = new AccountsApi(defaultClient);
        String xSantanderClientId = "test"; // String | Client ID header.
        String customerId = "pagofx_ukGBDTT667794"; // String | Customer identifier
        String expand = "balances"; // String | Whether balances are to be included in the response.  If the value is 'balances', the response includes the following balance types are:  - consolidated   - pendingConsolidation   - withholding   - overdraft   - limit   - available   - current  The default value is empty and returns no balances. 
        String status = "Open"; // String | Status of the accounts to be included in the response. The possible values are a comma separated list of: - Open = Account is open and movements are restricted only by the customer's available balance and account contract attributes. This includes accounts with warnings. - Blocked = Account has some restrictive block. - Closed = Account is closed. 
        Boolean historical = true; // Boolean | Whether closed accounts are included in the response. The possible values are: - true = Accounts with closed status are included - false = Accounts with closed status are not included    The default value is true. If the 'status' parameter is provided, this parameter must not be used. 
        String accountsList = "account1,account2,account3,account4,account5"; // String | List of accounts to be included in the response. The accounts must be defined with comma-separated account numbers without blank spaces.   All accounts must be identified using the account number format defined in the 'accountIdType' parameter. The maximum number of included accounts is 20.   If you want data about more than 20 accounts, call the API multiple times, for 20 accounts at a time. 
        String accountIdType = "iban"; // String | Format used for the account number in the 'account_id' property.  The possible values are: - iban = IBAN code - bban = Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid = Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
        String accountIdCountry = "GB"; // String | Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
        String customerName = "Customer Company Name"; // String | Customer name associated with the account.
        String legalEntityName = "PagoNxt OneTrade UK Ltd"; // String | Legal Name of the legal entity associated with the account.
        String legalEntityShortCodeList = "pagofx_uk,pagonxt_es"; // String | Short code list of the legal entities associated with the account.
        String productIdList = "42dbdc87-a491-4566-bb97-eb2d9aea4707,2fb17fee-5f47-4bb5-b179-14e0800beceb"; // String | Unique identifiers of products associated with an account.
        String productName = "eMoney Account"; // String | Name of the product associated with the account.
        String nickname = "Test nickname"; // String | It is an alias of account name the customer has assigned to the account for easy identification.
        BigDecimal limit = new BigDecimal("10"); // BigDecimal | Number of values that should be informed per page
        BigDecimal offset = new BigDecimal("0"); // BigDecimal | Pagination identifier
        String sort = "-product_name"; // String | Sorting order for the retrieved accounts.  The possible values are: - +product_name = ascending by product_name   - -product_name = descending by product_name   - +customer_name = ascending by customer_name   - -customer_name = descending by customer_name   - +opening_date_time = ascending by opening_date_time   - -opening_date_time = descending by opening_date_time   - +last_update_date_time = ascending by last_update_date_time   - -last_update_date_time = descending by last_update_date_time   - +closing_date_time = ascending by closing_date_time   - -closing_date_time = descending by closing_date_time  If the value is omitted, the default value is '-opening_date_time'. 
        String xB3Traceid = "463ac35c9f6413ad48485a3953bb6124"; // String | Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
        String xB3Parentspanid = "20000000000001"; // String | Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
        String xB3Spanid = "a2fb4a1d1a96d312"; // String | Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
        String xB3Sampled = "1"; // String | Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 = Deny - 1 = Accept 
        String xSantanderDevice = "web"; // String | Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
        String virtualAccountStructureId = "0589bd6c-cca4-4b46-9c8a-b786171e66e0"; // String | Virtual account structure identifier
        String realAccountId = "0589bd6c-cca4-4b46-9c8a-b786171e66e0"; // String | Real account identifier
        String realAccountIdType = "iban"; // String | Real account type
        String realAccountIdCountry = "GB"; // String | Data structure containing details of the country for the account.  Necessary if accountIdType BBAN format is requested.   Country code.  The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
        String additionalInfo = "docs.file"; // String | Additional Account Info
        OffsetDateTime fromCreatedAt = OffsetDateTime.parse("2017-07-21T17:32:28Z"); // OffsetDateTime | Start date for the time period from which accounts are to be retrieved, based on the date when the accounts have been created in the system. The date format is 'yyyy-MM-ddThh:mm:ss.fff±timezone', where fff are milliseconds and timezone can be 'Z' or 'hh:mm' 
        OffsetDateTime toCreatedAt = OffsetDateTime.parse("2017-07-21T17:32:28Z"); // OffsetDateTime | End date for the time period from which accounts are to be retrieved, based on the date when the accounts have been created in the system. The date format is 'yyyy-MM-ddThh:mm:ss.fff±timezone', where fff are milliseconds and timezone can be 'Z' or 'hh:mm' 
        String accountingName = "Client Account, Virtual Account, Virtual Account Limited"; // String | Name of the account type.
        String accountingIdList = "0589bd6c-cca4-4b46-9c8a-b786171e66e0,0689bd6c-cca4-4b46-9c8a-b786171e66e1"; // String | List of account type identifiers.
        try {
            AccountListResponse result = apiInstance.getClientAccountsList(xSantanderClientId, customerId, expand, status, historical, accountsList, accountIdType, accountIdCountry, customerName, legalEntityName, legalEntityShortCodeList, productIdList, productName, nickname, limit, offset, sort, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice, virtualAccountStructureId, realAccountId, realAccountIdType, realAccountIdCountry, additionalInfo, fromCreatedAt, toCreatedAt, accountingName, accountingIdList);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountsApi#getClientAccountsList");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **xSantanderClientId** | **String**| Client ID header. | |
| **customerId** | **String**| Customer identifier | [optional] |
| **expand** | **String**| Whether balances are to be included in the response.  If the value is &#39;balances&#39;, the response includes the following balance types are:  - consolidated   - pendingConsolidation   - withholding   - overdraft   - limit   - available   - current  The default value is empty and returns no balances.  | [optional] |
| **status** | **String**| Status of the accounts to be included in the response. The possible values are a comma separated list of: - Open &#x3D; Account is open and movements are restricted only by the customer&#39;s available balance and account contract attributes. This includes accounts with warnings. - Blocked &#x3D; Account has some restrictive block. - Closed &#x3D; Account is closed.  | [optional] [enum: Open, Closed, Blocked] |
| **historical** | **Boolean**| Whether closed accounts are included in the response. The possible values are: - true &#x3D; Accounts with closed status are included - false &#x3D; Accounts with closed status are not included    The default value is true. If the &#39;status&#39; parameter is provided, this parameter must not be used.  | [optional] |
| **accountsList** | **String**| List of accounts to be included in the response. The accounts must be defined with comma-separated account numbers without blank spaces.   All accounts must be identified using the account number format defined in the &#39;accountIdType&#39; parameter. The maximum number of included accounts is 20.   If you want data about more than 20 accounts, call the API multiple times, for 20 accounts at a time.  | [optional] |
| **accountIdType** | **String**| Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2)  | [optional] [enum: iban, bban, uuid] |
| **accountIdCountry** | **String**| Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/).  | [optional] |
| **customerName** | **String**| Customer name associated with the account. | [optional] |
| **legalEntityName** | **String**| Legal Name of the legal entity associated with the account. | [optional] |
| **legalEntityShortCodeList** | **String**| Short code list of the legal entities associated with the account. | [optional] |
| **productIdList** | **String**| Unique identifiers of products associated with an account. | [optional] |
| **productName** | **String**| Name of the product associated with the account. | [optional] |
| **nickname** | **String**| It is an alias of account name the customer has assigned to the account for easy identification. | [optional] |
| **limit** | **BigDecimal**| Number of values that should be informed per page | [optional] |
| **offset** | **BigDecimal**| Pagination identifier | [optional] |
| **sort** | **String**| Sorting order for the retrieved accounts.  The possible values are: - +product_name &#x3D; ascending by product_name   - -product_name &#x3D; descending by product_name   - +customer_name &#x3D; ascending by customer_name   - -customer_name &#x3D; descending by customer_name   - +opening_date_time &#x3D; ascending by opening_date_time   - -opening_date_time &#x3D; descending by opening_date_time   - +last_update_date_time &#x3D; ascending by last_update_date_time   - -last_update_date_time &#x3D; descending by last_update_date_time   - +closing_date_time &#x3D; ascending by closing_date_time   - -closing_date_time &#x3D; descending by closing_date_time  If the value is omitted, the default value is &#39;-opening_date_time&#39;.  | [optional] [enum: -product_name, +product_name, -customer_name, +customer_name, -opening_date_time, +opening_date_time, -last_update_date_time, +last_update_date_time, -closing_date_time, +closing_date_time] |
| **xB3Traceid** | **String**| Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long.  | [optional] |
| **xB3Parentspanid** | **String**| Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree.  | [optional] |
| **xB3Spanid** | **String**| Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId.  | [optional] |
| **xB3Sampled** | **String**| Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept  | [optional] |
| **xSantanderDevice** | **String**| Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection.  | [optional] |
| **virtualAccountStructureId** | **String**| Virtual account structure identifier | [optional] |
| **realAccountId** | **String**| Real account identifier | [optional] |
| **realAccountIdType** | **String**| Real account type | [optional] [enum: iban, bban, uuid] |
| **realAccountIdCountry** | **String**| Data structure containing details of the country for the account.  Necessary if accountIdType BBAN format is requested.   Country code.  The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/).  | [optional] |
| **additionalInfo** | **String**| Additional Account Info | [optional] |
| **fromCreatedAt** | **OffsetDateTime**| Start date for the time period from which accounts are to be retrieved, based on the date when the accounts have been created in the system. The date format is &#39;yyyy-MM-ddThh:mm:ss.fff±timezone&#39;, where fff are milliseconds and timezone can be &#39;Z&#39; or &#39;hh:mm&#39;  | [optional] |
| **toCreatedAt** | **OffsetDateTime**| End date for the time period from which accounts are to be retrieved, based on the date when the accounts have been created in the system. The date format is &#39;yyyy-MM-ddThh:mm:ss.fff±timezone&#39;, where fff are milliseconds and timezone can be &#39;Z&#39; or &#39;hh:mm&#39;  | [optional] |
| **accountingName** | **String**| Name of the account type. | [optional] |
| **accountingIdList** | **String**| List of account type identifiers. | [optional] |

### Return type

[**AccountListResponse**](AccountListResponse.md)

### Authorization

[JWTProfile](../README.md#JWTProfile), [bearerJWTAuth](../README.md#bearerJWTAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Response to a request to retrieve a transaction list |  -  |
| **204** | No content |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **404** | Not found |  -  |
| **405** | Method Not Allowed |  -  |
| **406** | Not Acceptable |  -  |
| **409** | Conflict |  -  |
| **413** | Payload too large |  -  |
| **414** | URI too large |  -  |
| **415** | Unsupported Media Type |  -  |
| **422** | Unprocessable Entity |  -  |
| **429** | Too Many Requests |  -  |
| **500** | Internal Server Error |  -  |
| **503** | Service Down Error |  -  |
| **504** | Gateway timeout |  -  |
| **0** | Internal Server Error |  -  |

