package com.santander.getnet.external.client.model.api;

import com.santander.getnet.external.client.model.ApiClient;

import com.santander.getnet.external.client.model.data.AccountDetailsResponse;
import com.santander.getnet.external.client.model.data.AccountListResponse;
import com.santander.getnet.external.client.model.data.AccountVerificationsResponse;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import com.santander.getnet.external.client.model.data.ResponseErrors;

  import java.util.HashMap;
  import java.util.List;
  import java.util.Locale;
  import java.util.Map;
  import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-04T10:47:12.022669+01:00[Europe/Madrid]")
  public class AccountsApi {
  private ApiClient apiClient;

  public AccountsApi() {
  this(new ApiClient());
  }

  @Autowired
  public AccountsApi(ApiClient apiClient) {
  this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
  return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
  this.apiClient = apiClient;
  }

    /**
    * Get account verifications
    * Retrieves information from the account given an accountId.
         * <p><b>200</b> - Returns an OK. Account verifications are retrieved.
         * <p><b>204</b> - No content
         * <p><b>400</b> - Bad Request
         * <p><b>401</b> - Unauthorized
         * <p><b>403</b> - Forbidden
         * <p><b>404</b> - The account does not exist.
         * <p><b>405</b> - Method Not Allowed
         * <p><b>406</b> - Not Acceptable
         * <p><b>409</b> - Conflict
         * <p><b>413</b> - Payload too large
         * <p><b>414</b> - URI too large
         * <p><b>415</b> - Unsupported Media Type
         * <p><b>422</b> - Unprocessable Entity
         * <p><b>429</b> - Too Many Requests
         * <p><b>500</b> - Internal Server Error
         * <p><b>503</b> - Service Down Error
         * <p><b>504</b> - Gateway timeout
         * <p><b>0</b> - Internal Server Error
         * @param accountId Number of the account.  The number format is defined in the \&quot;account_id_type\&quot; parameter. 
         * @param xSantanderClientId Client ID header.
         * @param accountIdType Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
         * @param accountIdCountry Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
         * @param xB3Traceid Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
         * @param xB3Parentspanid Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
         * @param xB3Spanid Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
         * @param xB3Sampled Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept 
         * @param xSantanderDevice Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
         * @return AccountVerificationsResponse
         * @throws WebClientResponseException if an error occurs while attempting to invoke the API
      */
      private ResponseSpec getAccountVerificationsRequestCreation(HttpHeaders headers, String accountId, String xSantanderClientId, String accountIdType, String accountIdCountry, String xB3Traceid, String xB3Parentspanid, String xB3Spanid, String xB3Sampled, String xSantanderDevice) throws WebClientResponseException {
      Object postBody = null;
          // verify the required parameter 'accountId' is set
          if (accountId == null) {
          throw new WebClientResponseException("Missing the required parameter 'accountId' when calling getAccountVerifications", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
          }
          // verify the required parameter 'xSantanderClientId' is set
          if (xSantanderClientId == null) {
          throw new WebClientResponseException("Missing the required parameter 'xSantanderClientId' when calling getAccountVerifications", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
          }

      // create path and map variables
      final Map<String, Object> pathParams = new HashMap<String, Object>();

          pathParams.put("account_id", accountId);

      final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
      final HttpHeaders headerParams = new HttpHeaders();
      final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
      final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

      if(headers!=null) {
        if(!headers.isEmpty()) {
          for (String header : headers.keySet()) {
            headerParams.add(header, apiClient.parameterToString(headers.get(header)));
          }
        }
      }


          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "account_id_type", accountIdType));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "account_id_country", accountIdCountry));

          if (xSantanderClientId != null)
          headerParams.add("x-santander-client-id", apiClient.parameterToString(xSantanderClientId));
          if (xB3Traceid != null)
          headerParams.add("x-b3-traceid", apiClient.parameterToString(xB3Traceid));
          if (xB3Parentspanid != null)
          headerParams.add("x-b3-parentspanid", apiClient.parameterToString(xB3Parentspanid));
          if (xB3Spanid != null)
          headerParams.add("x-b3-spanid", apiClient.parameterToString(xB3Spanid));
          if (xB3Sampled != null)
          headerParams.add("x-b3-sampled", apiClient.parameterToString(xB3Sampled));
          if (xSantanderDevice != null)
          headerParams.add("x-santander-device", apiClient.parameterToString(xSantanderDevice));
      final String[] localVarAccepts = { 
      "application/json"
    };
      final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
      final String[] localVarContentTypes = { };
      final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

      String[] localVarAuthNames = new String[] { "JWTProfile", "bearerJWTAuth" };

    ParameterizedTypeReference<AccountVerificationsResponse> localVarReturnType = new ParameterizedTypeReference<AccountVerificationsResponse>() {};
      return apiClient.invokeAPI("/accounts/{account_id}/verifications", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }

      /**
      * Get account verifications
      * Retrieves information from the account given an accountId.
         * <p><b>200</b> - Returns an OK. Account verifications are retrieved.
         * <p><b>204</b> - No content
         * <p><b>400</b> - Bad Request
         * <p><b>401</b> - Unauthorized
         * <p><b>403</b> - Forbidden
         * <p><b>404</b> - The account does not exist.
         * <p><b>405</b> - Method Not Allowed
         * <p><b>406</b> - Not Acceptable
         * <p><b>409</b> - Conflict
         * <p><b>413</b> - Payload too large
         * <p><b>414</b> - URI too large
         * <p><b>415</b> - Unsupported Media Type
         * <p><b>422</b> - Unprocessable Entity
         * <p><b>429</b> - Too Many Requests
         * <p><b>500</b> - Internal Server Error
         * <p><b>503</b> - Service Down Error
         * <p><b>504</b> - Gateway timeout
         * <p><b>0</b> - Internal Server Error
         * @param accountId Number of the account.  The number format is defined in the \&quot;account_id_type\&quot; parameter. 
         * @param xSantanderClientId Client ID header.
         * @param accountIdType Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
         * @param accountIdCountry Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
         * @param xB3Traceid Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
         * @param xB3Parentspanid Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
         * @param xB3Spanid Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
         * @param xB3Sampled Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept 
         * @param xSantanderDevice Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
         * @return AccountVerificationsResponse
         * @throws WebClientResponseException if an error occurs while attempting to invoke the API
      */
      public Mono<AccountVerificationsResponse> getAccountVerifications(HttpHeaders headers, String accountId, String xSantanderClientId, String accountIdType, String accountIdCountry, String xB3Traceid, String xB3Parentspanid, String xB3Spanid, String xB3Sampled, String xSantanderDevice) throws WebClientResponseException {
    ParameterizedTypeReference<AccountVerificationsResponse> localVarReturnType = new ParameterizedTypeReference<AccountVerificationsResponse>() {};
    return getAccountVerificationsRequestCreation(headers, accountId, xSantanderClientId, accountIdType, accountIdCountry, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice).bodyToMono(localVarReturnType);
      }

      /**
      * Get account verifications
      * Retrieves information from the account given an accountId.
         * <p><b>200</b> - Returns an OK. Account verifications are retrieved.
         * <p><b>204</b> - No content
         * <p><b>400</b> - Bad Request
         * <p><b>401</b> - Unauthorized
         * <p><b>403</b> - Forbidden
         * <p><b>404</b> - The account does not exist.
         * <p><b>405</b> - Method Not Allowed
         * <p><b>406</b> - Not Acceptable
         * <p><b>409</b> - Conflict
         * <p><b>413</b> - Payload too large
         * <p><b>414</b> - URI too large
         * <p><b>415</b> - Unsupported Media Type
         * <p><b>422</b> - Unprocessable Entity
         * <p><b>429</b> - Too Many Requests
         * <p><b>500</b> - Internal Server Error
         * <p><b>503</b> - Service Down Error
         * <p><b>504</b> - Gateway timeout
         * <p><b>0</b> - Internal Server Error
         * @param accountId Number of the account.  The number format is defined in the \&quot;account_id_type\&quot; parameter. 
         * @param xSantanderClientId Client ID header.
         * @param accountIdType Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
         * @param accountIdCountry Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
         * @param xB3Traceid Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
         * @param xB3Parentspanid Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
         * @param xB3Spanid Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
         * @param xB3Sampled Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept 
         * @param xSantanderDevice Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
         * @return ResponseEntity&lt;AccountVerificationsResponse&gt;
         * @throws WebClientResponseException if an error occurs while attempting to invoke the API
      */
      public Mono<ResponseEntity<AccountVerificationsResponse>> getAccountVerificationsWithHttpInfo(HttpHeaders headers, String accountId, String xSantanderClientId, String accountIdType, String accountIdCountry, String xB3Traceid, String xB3Parentspanid, String xB3Spanid, String xB3Sampled, String xSantanderDevice) throws WebClientResponseException {
    ParameterizedTypeReference<AccountVerificationsResponse> localVarReturnType = new ParameterizedTypeReference<AccountVerificationsResponse>() {};
      return getAccountVerificationsRequestCreation(headers, accountId, xSantanderClientId, accountIdType, accountIdCountry, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice).toEntity(localVarReturnType);
      }

      /**
      * Get account verifications
      * Retrieves information from the account given an accountId.
         * <p><b>200</b> - Returns an OK. Account verifications are retrieved.
         * <p><b>204</b> - No content
         * <p><b>400</b> - Bad Request
         * <p><b>401</b> - Unauthorized
         * <p><b>403</b> - Forbidden
         * <p><b>404</b> - The account does not exist.
         * <p><b>405</b> - Method Not Allowed
         * <p><b>406</b> - Not Acceptable
         * <p><b>409</b> - Conflict
         * <p><b>413</b> - Payload too large
         * <p><b>414</b> - URI too large
         * <p><b>415</b> - Unsupported Media Type
         * <p><b>422</b> - Unprocessable Entity
         * <p><b>429</b> - Too Many Requests
         * <p><b>500</b> - Internal Server Error
         * <p><b>503</b> - Service Down Error
         * <p><b>504</b> - Gateway timeout
         * <p><b>0</b> - Internal Server Error
         * @param accountId Number of the account.  The number format is defined in the \&quot;account_id_type\&quot; parameter. 
     * @param xSantanderClientId Client ID header.
     * @param accountIdType Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
     * @param accountIdCountry Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
     * @param xB3Traceid Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
     * @param xB3Parentspanid Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
     * @param xB3Spanid Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
     * @param xB3Sampled Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept 
     * @param xSantanderDevice Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
      * @return ResponseSpec
      * @throws WebClientResponseException if an error occurs while attempting to invoke the API
      */
      public ResponseSpec getAccountVerificationsWithResponseSpec(HttpHeaders headers, String accountId, String xSantanderClientId, String accountIdType, String accountIdCountry, String xB3Traceid, String xB3Parentspanid, String xB3Spanid, String xB3Sampled, String xSantanderDevice) throws WebClientResponseException {
      return getAccountVerificationsRequestCreation(headers, accountId, xSantanderClientId, accountIdType, accountIdCountry, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice);
      }
    /**
    * Get account details
    * Gets account details based on an account ID.
         * <p><b>200</b> - Example response
         * <p><b>204</b> - No content
         * <p><b>400</b> - Bad Request
         * <p><b>401</b> - Unauthorized
         * <p><b>403</b> - Forbidden
         * <p><b>404</b> - The account does not exist in the system.
         * <p><b>405</b> - Method Not Allowed
         * <p><b>406</b> - Not Acceptable
         * <p><b>409</b> - Conflict
         * <p><b>413</b> - Payload too large
         * <p><b>414</b> - URI too large
         * <p><b>415</b> - Unsupported Media Type
         * <p><b>422</b> - Unprocessable Entity
         * <p><b>429</b> - Too Many Requests
         * <p><b>500</b> - Internal Server Error
         * <p><b>503</b> - Service Down Error
         * <p><b>504</b> - Gateway timeout
         * <p><b>0</b> - Internal Server Error
         * @param accountId Number of the account.  The number format is defined in the \&quot;account_id_type\&quot; parameter. 
         * @param xSantanderClientId Client ID header.
         * @param accountIdType Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
         * @param accountIdCountry Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
         * @param xB3Traceid Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
         * @param xB3Parentspanid Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
         * @param xB3Spanid Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
         * @param xB3Sampled Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept 
         * @param xSantanderDevice Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
         * @return AccountDetailsResponse
         * @throws WebClientResponseException if an error occurs while attempting to invoke the API
      */
      private ResponseSpec getClientAccountDetailsRequestCreation(HttpHeaders headers, String accountId, String xSantanderClientId, String accountIdType, String accountIdCountry, String xB3Traceid, String xB3Parentspanid, String xB3Spanid, String xB3Sampled, String xSantanderDevice) throws WebClientResponseException {
      Object postBody = null;
          // verify the required parameter 'accountId' is set
          if (accountId == null) {
          throw new WebClientResponseException("Missing the required parameter 'accountId' when calling getClientAccountDetails", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
          }
          // verify the required parameter 'xSantanderClientId' is set
          if (xSantanderClientId == null) {
          throw new WebClientResponseException("Missing the required parameter 'xSantanderClientId' when calling getClientAccountDetails", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
          }

      // create path and map variables
      final Map<String, Object> pathParams = new HashMap<String, Object>();

          pathParams.put("account_id", accountId);

      final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
      final HttpHeaders headerParams = new HttpHeaders();
      final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
      final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

      if(headers!=null) {
        if(!headers.isEmpty()) {
          for (String header : headers.keySet()) {
            headerParams.add(header, apiClient.parameterToString(headers.get(header)));
          }
        }
      }


          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "account_id_type", accountIdType));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "account_id_country", accountIdCountry));

          if (xSantanderClientId != null)
          headerParams.add("x-santander-client-id", apiClient.parameterToString(xSantanderClientId));
          if (xB3Traceid != null)
          headerParams.add("x-b3-traceid", apiClient.parameterToString(xB3Traceid));
          if (xB3Parentspanid != null)
          headerParams.add("x-b3-parentspanid", apiClient.parameterToString(xB3Parentspanid));
          if (xB3Spanid != null)
          headerParams.add("x-b3-spanid", apiClient.parameterToString(xB3Spanid));
          if (xB3Sampled != null)
          headerParams.add("x-b3-sampled", apiClient.parameterToString(xB3Sampled));
          if (xSantanderDevice != null)
          headerParams.add("x-santander-device", apiClient.parameterToString(xSantanderDevice));
      final String[] localVarAccepts = { 
      "application/json"
    };
      final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
      final String[] localVarContentTypes = { };
      final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

      String[] localVarAuthNames = new String[] { "JWTProfile", "bearerJWTAuth" };

    ParameterizedTypeReference<AccountDetailsResponse> localVarReturnType = new ParameterizedTypeReference<AccountDetailsResponse>() {};
      return apiClient.invokeAPI("/accounts/{account_id}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }

      /**
      * Get account details
      * Gets account details based on an account ID.
         * <p><b>200</b> - Example response
         * <p><b>204</b> - No content
         * <p><b>400</b> - Bad Request
         * <p><b>401</b> - Unauthorized
         * <p><b>403</b> - Forbidden
         * <p><b>404</b> - The account does not exist in the system.
         * <p><b>405</b> - Method Not Allowed
         * <p><b>406</b> - Not Acceptable
         * <p><b>409</b> - Conflict
         * <p><b>413</b> - Payload too large
         * <p><b>414</b> - URI too large
         * <p><b>415</b> - Unsupported Media Type
         * <p><b>422</b> - Unprocessable Entity
         * <p><b>429</b> - Too Many Requests
         * <p><b>500</b> - Internal Server Error
         * <p><b>503</b> - Service Down Error
         * <p><b>504</b> - Gateway timeout
         * <p><b>0</b> - Internal Server Error
         * @param accountId Number of the account.  The number format is defined in the \&quot;account_id_type\&quot; parameter. 
         * @param xSantanderClientId Client ID header.
         * @param accountIdType Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
         * @param accountIdCountry Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
         * @param xB3Traceid Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
         * @param xB3Parentspanid Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
         * @param xB3Spanid Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
         * @param xB3Sampled Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept 
         * @param xSantanderDevice Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
         * @return AccountDetailsResponse
         * @throws WebClientResponseException if an error occurs while attempting to invoke the API
      */
      public Mono<AccountDetailsResponse> getClientAccountDetails(HttpHeaders headers, String accountId, String xSantanderClientId, String accountIdType, String accountIdCountry, String xB3Traceid, String xB3Parentspanid, String xB3Spanid, String xB3Sampled, String xSantanderDevice) throws WebClientResponseException {
    ParameterizedTypeReference<AccountDetailsResponse> localVarReturnType = new ParameterizedTypeReference<AccountDetailsResponse>() {};
    return getClientAccountDetailsRequestCreation(headers, accountId, xSantanderClientId, accountIdType, accountIdCountry, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice).bodyToMono(localVarReturnType);
      }

      /**
      * Get account details
      * Gets account details based on an account ID.
         * <p><b>200</b> - Example response
         * <p><b>204</b> - No content
         * <p><b>400</b> - Bad Request
         * <p><b>401</b> - Unauthorized
         * <p><b>403</b> - Forbidden
         * <p><b>404</b> - The account does not exist in the system.
         * <p><b>405</b> - Method Not Allowed
         * <p><b>406</b> - Not Acceptable
         * <p><b>409</b> - Conflict
         * <p><b>413</b> - Payload too large
         * <p><b>414</b> - URI too large
         * <p><b>415</b> - Unsupported Media Type
         * <p><b>422</b> - Unprocessable Entity
         * <p><b>429</b> - Too Many Requests
         * <p><b>500</b> - Internal Server Error
         * <p><b>503</b> - Service Down Error
         * <p><b>504</b> - Gateway timeout
         * <p><b>0</b> - Internal Server Error
         * @param accountId Number of the account.  The number format is defined in the \&quot;account_id_type\&quot; parameter. 
         * @param xSantanderClientId Client ID header.
         * @param accountIdType Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
         * @param accountIdCountry Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
         * @param xB3Traceid Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
         * @param xB3Parentspanid Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
         * @param xB3Spanid Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
         * @param xB3Sampled Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept 
         * @param xSantanderDevice Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
         * @return ResponseEntity&lt;AccountDetailsResponse&gt;
         * @throws WebClientResponseException if an error occurs while attempting to invoke the API
      */
      public Mono<ResponseEntity<AccountDetailsResponse>> getClientAccountDetailsWithHttpInfo(HttpHeaders headers, String accountId, String xSantanderClientId, String accountIdType, String accountIdCountry, String xB3Traceid, String xB3Parentspanid, String xB3Spanid, String xB3Sampled, String xSantanderDevice) throws WebClientResponseException {
    ParameterizedTypeReference<AccountDetailsResponse> localVarReturnType = new ParameterizedTypeReference<AccountDetailsResponse>() {};
      return getClientAccountDetailsRequestCreation(headers, accountId, xSantanderClientId, accountIdType, accountIdCountry, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice).toEntity(localVarReturnType);
      }

      /**
      * Get account details
      * Gets account details based on an account ID.
         * <p><b>200</b> - Example response
         * <p><b>204</b> - No content
         * <p><b>400</b> - Bad Request
         * <p><b>401</b> - Unauthorized
         * <p><b>403</b> - Forbidden
         * <p><b>404</b> - The account does not exist in the system.
         * <p><b>405</b> - Method Not Allowed
         * <p><b>406</b> - Not Acceptable
         * <p><b>409</b> - Conflict
         * <p><b>413</b> - Payload too large
         * <p><b>414</b> - URI too large
         * <p><b>415</b> - Unsupported Media Type
         * <p><b>422</b> - Unprocessable Entity
         * <p><b>429</b> - Too Many Requests
         * <p><b>500</b> - Internal Server Error
         * <p><b>503</b> - Service Down Error
         * <p><b>504</b> - Gateway timeout
         * <p><b>0</b> - Internal Server Error
         * @param accountId Number of the account.  The number format is defined in the \&quot;account_id_type\&quot; parameter. 
     * @param xSantanderClientId Client ID header.
     * @param accountIdType Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
     * @param accountIdCountry Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
     * @param xB3Traceid Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
     * @param xB3Parentspanid Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
     * @param xB3Spanid Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
     * @param xB3Sampled Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept 
     * @param xSantanderDevice Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
      * @return ResponseSpec
      * @throws WebClientResponseException if an error occurs while attempting to invoke the API
      */
      public ResponseSpec getClientAccountDetailsWithResponseSpec(HttpHeaders headers, String accountId, String xSantanderClientId, String accountIdType, String accountIdCountry, String xB3Traceid, String xB3Parentspanid, String xB3Spanid, String xB3Sampled, String xSantanderDevice) throws WebClientResponseException {
      return getClientAccountDetailsRequestCreation(headers, accountId, xSantanderClientId, accountIdType, accountIdCountry, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice);
      }
    /**
    * Retrieves the eMoney accounts list of PagoNXT
    * Retrieves a list for all the accounts of a PagoNXT customer.  This operation is available to PagoNXT staff and 3rd parties.  For the customer Id requested, it will return all its accounts.  Sort order by default is opening date ascending. 
         * <p><b>200</b> - Response to a request to retrieve a transaction list
         * <p><b>204</b> - No content
         * <p><b>400</b> - Bad Request
         * <p><b>401</b> - Unauthorized
         * <p><b>403</b> - Forbidden
         * <p><b>404</b> - Not found
         * <p><b>405</b> - Method Not Allowed
         * <p><b>406</b> - Not Acceptable
         * <p><b>409</b> - Conflict
         * <p><b>413</b> - Payload too large
         * <p><b>414</b> - URI too large
         * <p><b>415</b> - Unsupported Media Type
         * <p><b>422</b> - Unprocessable Entity
         * <p><b>429</b> - Too Many Requests
         * <p><b>500</b> - Internal Server Error
         * <p><b>503</b> - Service Down Error
         * <p><b>504</b> - Gateway timeout
         * <p><b>0</b> - Internal Server Error
         * @param xSantanderClientId Client ID header.
         * @param customerId Customer identifier
         * @param expand Whether balances are to be included in the response.  If the value is &#39;balances&#39;, the response includes the following balance types are:  - consolidated   - pendingConsolidation   - withholding   - overdraft   - limit   - available   - current  The default value is empty and returns no balances. 
         * @param status Status of the accounts to be included in the response. The possible values are a comma separated list of: - Open &#x3D; Account is open and movements are restricted only by the customer&#39;s available balance and account contract attributes. This includes accounts with warnings. - Blocked &#x3D; Account has some restrictive block. - Closed &#x3D; Account is closed. 
         * @param historical Whether closed accounts are included in the response. The possible values are: - true &#x3D; Accounts with closed status are included - false &#x3D; Accounts with closed status are not included    The default value is true. If the &#39;status&#39; parameter is provided, this parameter must not be used. 
         * @param accountsList List of accounts to be included in the response. The accounts must be defined with comma-separated account numbers without blank spaces.   All accounts must be identified using the account number format defined in the &#39;accountIdType&#39; parameter. The maximum number of included accounts is 20.   If you want data about more than 20 accounts, call the API multiple times, for 20 accounts at a time. 
         * @param accountIdType Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
         * @param accountIdCountry Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
         * @param customerName Customer name associated with the account.
         * @param legalEntityName Legal Name of the legal entity associated with the account.
         * @param legalEntityShortCodeList Short code list of the legal entities associated with the account.
         * @param productIdList Unique identifiers of products associated with an account.
         * @param productName Name of the product associated with the account.
         * @param nickname It is an alias of account name the customer has assigned to the account for easy identification.
         * @param limit Number of values that should be informed per page
         * @param offset Pagination identifier
         * @param sort Sorting order for the retrieved accounts.  The possible values are: - +product_name &#x3D; ascending by product_name   - -product_name &#x3D; descending by product_name   - +customer_name &#x3D; ascending by customer_name   - -customer_name &#x3D; descending by customer_name   - +opening_date_time &#x3D; ascending by opening_date_time   - -opening_date_time &#x3D; descending by opening_date_time   - +last_update_date_time &#x3D; ascending by last_update_date_time   - -last_update_date_time &#x3D; descending by last_update_date_time   - +closing_date_time &#x3D; ascending by closing_date_time   - -closing_date_time &#x3D; descending by closing_date_time  If the value is omitted, the default value is &#39;-opening_date_time&#39;. 
         * @param xB3Traceid Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
         * @param xB3Parentspanid Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
         * @param xB3Spanid Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
         * @param xB3Sampled Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept 
         * @param xSantanderDevice Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
         * @param virtualAccountStructureId Virtual account structure identifier
         * @param realAccountId Real account identifier
         * @param realAccountIdType Real account type
         * @param realAccountIdCountry Data structure containing details of the country for the account.  Necessary if accountIdType BBAN format is requested.   Country code.  The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
         * @param additionalInfo Additional Account Info
         * @param fromCreatedAt Start date for the time period from which accounts are to be retrieved, based on the date when the accounts have been created in the system. The date format is &#39;yyyy-MM-ddThh:mm:ss.fff±timezone&#39;, where fff are milliseconds and timezone can be &#39;Z&#39; or &#39;hh:mm&#39; 
         * @param toCreatedAt End date for the time period from which accounts are to be retrieved, based on the date when the accounts have been created in the system. The date format is &#39;yyyy-MM-ddThh:mm:ss.fff±timezone&#39;, where fff are milliseconds and timezone can be &#39;Z&#39; or &#39;hh:mm&#39; 
         * @param accountingName Name of the account type.
         * @param accountingIdList List of account type identifiers.
         * @return AccountListResponse
         * @throws WebClientResponseException if an error occurs while attempting to invoke the API
      */
      private ResponseSpec getClientAccountsListRequestCreation(HttpHeaders headers, String xSantanderClientId, String customerId, String expand, String status, Boolean historical, String accountsList, String accountIdType, String accountIdCountry, String customerName, String legalEntityName, String legalEntityShortCodeList, String productIdList, String productName, String nickname, BigDecimal limit, BigDecimal offset, String sort, String xB3Traceid, String xB3Parentspanid, String xB3Spanid, String xB3Sampled, String xSantanderDevice, String virtualAccountStructureId, String realAccountId, String realAccountIdType, String realAccountIdCountry, String additionalInfo, OffsetDateTime fromCreatedAt, OffsetDateTime toCreatedAt, String accountingName, String accountingIdList) throws WebClientResponseException {
      Object postBody = null;
          // verify the required parameter 'xSantanderClientId' is set
          if (xSantanderClientId == null) {
          throw new WebClientResponseException("Missing the required parameter 'xSantanderClientId' when calling getClientAccountsList", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
          }

      // create path and map variables
      final Map<String, Object> pathParams = new HashMap<String, Object>();

      final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
      final HttpHeaders headerParams = new HttpHeaders();
      final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
      final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

      if(headers!=null) {
        if(!headers.isEmpty()) {
          for (String header : headers.keySet()) {
            headerParams.add(header, apiClient.parameterToString(headers.get(header)));
          }
        }
      }


          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "customer_id", customerId));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "_expand", expand));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "status", status));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "historical", historical));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "accounts_list", accountsList));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "account_id_type", accountIdType));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "account_id_country", accountIdCountry));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "customer_name", customerName));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "legal_entity_name", legalEntityName));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "legal_entity_short_code_list", legalEntityShortCodeList));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "product_id_list", productIdList));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "product_name", productName));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nickname", nickname));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "_limit", limit));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "_offset", offset));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "_sort", sort));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "virtual_account_structure_id", virtualAccountStructureId));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "real_account_id", realAccountId));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "real_account_id_type", realAccountIdType));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "real_account_id_country", realAccountIdCountry));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "additional_info", additionalInfo));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "from_created_at", fromCreatedAt));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "to_created_at", toCreatedAt));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "accounting_name", accountingName));
          queryParams.putAll(apiClient.parameterToMultiValueMap(null, "accounting_id_list", accountingIdList));

          if (xSantanderClientId != null)
          headerParams.add("x-santander-client-id", apiClient.parameterToString(xSantanderClientId));
          if (xB3Traceid != null)
          headerParams.add("x-b3-traceid", apiClient.parameterToString(xB3Traceid));
          if (xB3Parentspanid != null)
          headerParams.add("x-b3-parentspanid", apiClient.parameterToString(xB3Parentspanid));
          if (xB3Spanid != null)
          headerParams.add("x-b3-spanid", apiClient.parameterToString(xB3Spanid));
          if (xB3Sampled != null)
          headerParams.add("x-b3-sampled", apiClient.parameterToString(xB3Sampled));
          if (xSantanderDevice != null)
          headerParams.add("x-santander-device", apiClient.parameterToString(xSantanderDevice));
      final String[] localVarAccepts = { 
      "application/json"
    };
      final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
      final String[] localVarContentTypes = { };
      final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

      String[] localVarAuthNames = new String[] { "JWTProfile", "bearerJWTAuth" };

    ParameterizedTypeReference<AccountListResponse> localVarReturnType = new ParameterizedTypeReference<AccountListResponse>() {};
      return apiClient.invokeAPI("/accounts", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }

      /**
      * Retrieves the eMoney accounts list of PagoNXT
      * Retrieves a list for all the accounts of a PagoNXT customer.  This operation is available to PagoNXT staff and 3rd parties.  For the customer Id requested, it will return all its accounts.  Sort order by default is opening date ascending. 
         * <p><b>200</b> - Response to a request to retrieve a transaction list
         * <p><b>204</b> - No content
         * <p><b>400</b> - Bad Request
         * <p><b>401</b> - Unauthorized
         * <p><b>403</b> - Forbidden
         * <p><b>404</b> - Not found
         * <p><b>405</b> - Method Not Allowed
         * <p><b>406</b> - Not Acceptable
         * <p><b>409</b> - Conflict
         * <p><b>413</b> - Payload too large
         * <p><b>414</b> - URI too large
         * <p><b>415</b> - Unsupported Media Type
         * <p><b>422</b> - Unprocessable Entity
         * <p><b>429</b> - Too Many Requests
         * <p><b>500</b> - Internal Server Error
         * <p><b>503</b> - Service Down Error
         * <p><b>504</b> - Gateway timeout
         * <p><b>0</b> - Internal Server Error
         * @param xSantanderClientId Client ID header.
         * @param customerId Customer identifier
         * @param expand Whether balances are to be included in the response.  If the value is &#39;balances&#39;, the response includes the following balance types are:  - consolidated   - pendingConsolidation   - withholding   - overdraft   - limit   - available   - current  The default value is empty and returns no balances. 
         * @param status Status of the accounts to be included in the response. The possible values are a comma separated list of: - Open &#x3D; Account is open and movements are restricted only by the customer&#39;s available balance and account contract attributes. This includes accounts with warnings. - Blocked &#x3D; Account has some restrictive block. - Closed &#x3D; Account is closed. 
         * @param historical Whether closed accounts are included in the response. The possible values are: - true &#x3D; Accounts with closed status are included - false &#x3D; Accounts with closed status are not included    The default value is true. If the &#39;status&#39; parameter is provided, this parameter must not be used. 
         * @param accountsList List of accounts to be included in the response. The accounts must be defined with comma-separated account numbers without blank spaces.   All accounts must be identified using the account number format defined in the &#39;accountIdType&#39; parameter. The maximum number of included accounts is 20.   If you want data about more than 20 accounts, call the API multiple times, for 20 accounts at a time. 
         * @param accountIdType Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
         * @param accountIdCountry Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
         * @param customerName Customer name associated with the account.
         * @param legalEntityName Legal Name of the legal entity associated with the account.
         * @param legalEntityShortCodeList Short code list of the legal entities associated with the account.
         * @param productIdList Unique identifiers of products associated with an account.
         * @param productName Name of the product associated with the account.
         * @param nickname It is an alias of account name the customer has assigned to the account for easy identification.
         * @param limit Number of values that should be informed per page
         * @param offset Pagination identifier
         * @param sort Sorting order for the retrieved accounts.  The possible values are: - +product_name &#x3D; ascending by product_name   - -product_name &#x3D; descending by product_name   - +customer_name &#x3D; ascending by customer_name   - -customer_name &#x3D; descending by customer_name   - +opening_date_time &#x3D; ascending by opening_date_time   - -opening_date_time &#x3D; descending by opening_date_time   - +last_update_date_time &#x3D; ascending by last_update_date_time   - -last_update_date_time &#x3D; descending by last_update_date_time   - +closing_date_time &#x3D; ascending by closing_date_time   - -closing_date_time &#x3D; descending by closing_date_time  If the value is omitted, the default value is &#39;-opening_date_time&#39;. 
         * @param xB3Traceid Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
         * @param xB3Parentspanid Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
         * @param xB3Spanid Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
         * @param xB3Sampled Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept 
         * @param xSantanderDevice Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
         * @param virtualAccountStructureId Virtual account structure identifier
         * @param realAccountId Real account identifier
         * @param realAccountIdType Real account type
         * @param realAccountIdCountry Data structure containing details of the country for the account.  Necessary if accountIdType BBAN format is requested.   Country code.  The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
         * @param additionalInfo Additional Account Info
         * @param fromCreatedAt Start date for the time period from which accounts are to be retrieved, based on the date when the accounts have been created in the system. The date format is &#39;yyyy-MM-ddThh:mm:ss.fff±timezone&#39;, where fff are milliseconds and timezone can be &#39;Z&#39; or &#39;hh:mm&#39; 
         * @param toCreatedAt End date for the time period from which accounts are to be retrieved, based on the date when the accounts have been created in the system. The date format is &#39;yyyy-MM-ddThh:mm:ss.fff±timezone&#39;, where fff are milliseconds and timezone can be &#39;Z&#39; or &#39;hh:mm&#39; 
         * @param accountingName Name of the account type.
         * @param accountingIdList List of account type identifiers.
         * @return AccountListResponse
         * @throws WebClientResponseException if an error occurs while attempting to invoke the API
      */
      public Mono<AccountListResponse> getClientAccountsList(HttpHeaders headers, String xSantanderClientId, String customerId, String expand, String status, Boolean historical, String accountsList, String accountIdType, String accountIdCountry, String customerName, String legalEntityName, String legalEntityShortCodeList, String productIdList, String productName, String nickname, BigDecimal limit, BigDecimal offset, String sort, String xB3Traceid, String xB3Parentspanid, String xB3Spanid, String xB3Sampled, String xSantanderDevice, String virtualAccountStructureId, String realAccountId, String realAccountIdType, String realAccountIdCountry, String additionalInfo, OffsetDateTime fromCreatedAt, OffsetDateTime toCreatedAt, String accountingName, String accountingIdList) throws WebClientResponseException {
    ParameterizedTypeReference<AccountListResponse> localVarReturnType = new ParameterizedTypeReference<AccountListResponse>() {};
    return getClientAccountsListRequestCreation(headers, xSantanderClientId, customerId, expand, status, historical, accountsList, accountIdType, accountIdCountry, customerName, legalEntityName, legalEntityShortCodeList, productIdList, productName, nickname, limit, offset, sort, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice, virtualAccountStructureId, realAccountId, realAccountIdType, realAccountIdCountry, additionalInfo, fromCreatedAt, toCreatedAt, accountingName, accountingIdList).bodyToMono(localVarReturnType);
      }

      /**
      * Retrieves the eMoney accounts list of PagoNXT
      * Retrieves a list for all the accounts of a PagoNXT customer.  This operation is available to PagoNXT staff and 3rd parties.  For the customer Id requested, it will return all its accounts.  Sort order by default is opening date ascending. 
         * <p><b>200</b> - Response to a request to retrieve a transaction list
         * <p><b>204</b> - No content
         * <p><b>400</b> - Bad Request
         * <p><b>401</b> - Unauthorized
         * <p><b>403</b> - Forbidden
         * <p><b>404</b> - Not found
         * <p><b>405</b> - Method Not Allowed
         * <p><b>406</b> - Not Acceptable
         * <p><b>409</b> - Conflict
         * <p><b>413</b> - Payload too large
         * <p><b>414</b> - URI too large
         * <p><b>415</b> - Unsupported Media Type
         * <p><b>422</b> - Unprocessable Entity
         * <p><b>429</b> - Too Many Requests
         * <p><b>500</b> - Internal Server Error
         * <p><b>503</b> - Service Down Error
         * <p><b>504</b> - Gateway timeout
         * <p><b>0</b> - Internal Server Error
         * @param xSantanderClientId Client ID header.
         * @param customerId Customer identifier
         * @param expand Whether balances are to be included in the response.  If the value is &#39;balances&#39;, the response includes the following balance types are:  - consolidated   - pendingConsolidation   - withholding   - overdraft   - limit   - available   - current  The default value is empty and returns no balances. 
         * @param status Status of the accounts to be included in the response. The possible values are a comma separated list of: - Open &#x3D; Account is open and movements are restricted only by the customer&#39;s available balance and account contract attributes. This includes accounts with warnings. - Blocked &#x3D; Account has some restrictive block. - Closed &#x3D; Account is closed. 
         * @param historical Whether closed accounts are included in the response. The possible values are: - true &#x3D; Accounts with closed status are included - false &#x3D; Accounts with closed status are not included    The default value is true. If the &#39;status&#39; parameter is provided, this parameter must not be used. 
         * @param accountsList List of accounts to be included in the response. The accounts must be defined with comma-separated account numbers without blank spaces.   All accounts must be identified using the account number format defined in the &#39;accountIdType&#39; parameter. The maximum number of included accounts is 20.   If you want data about more than 20 accounts, call the API multiple times, for 20 accounts at a time. 
         * @param accountIdType Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
         * @param accountIdCountry Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
         * @param customerName Customer name associated with the account.
         * @param legalEntityName Legal Name of the legal entity associated with the account.
         * @param legalEntityShortCodeList Short code list of the legal entities associated with the account.
         * @param productIdList Unique identifiers of products associated with an account.
         * @param productName Name of the product associated with the account.
         * @param nickname It is an alias of account name the customer has assigned to the account for easy identification.
         * @param limit Number of values that should be informed per page
         * @param offset Pagination identifier
         * @param sort Sorting order for the retrieved accounts.  The possible values are: - +product_name &#x3D; ascending by product_name   - -product_name &#x3D; descending by product_name   - +customer_name &#x3D; ascending by customer_name   - -customer_name &#x3D; descending by customer_name   - +opening_date_time &#x3D; ascending by opening_date_time   - -opening_date_time &#x3D; descending by opening_date_time   - +last_update_date_time &#x3D; ascending by last_update_date_time   - -last_update_date_time &#x3D; descending by last_update_date_time   - +closing_date_time &#x3D; ascending by closing_date_time   - -closing_date_time &#x3D; descending by closing_date_time  If the value is omitted, the default value is &#39;-opening_date_time&#39;. 
         * @param xB3Traceid Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
         * @param xB3Parentspanid Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
         * @param xB3Spanid Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
         * @param xB3Sampled Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept 
         * @param xSantanderDevice Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
         * @param virtualAccountStructureId Virtual account structure identifier
         * @param realAccountId Real account identifier
         * @param realAccountIdType Real account type
         * @param realAccountIdCountry Data structure containing details of the country for the account.  Necessary if accountIdType BBAN format is requested.   Country code.  The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
         * @param additionalInfo Additional Account Info
         * @param fromCreatedAt Start date for the time period from which accounts are to be retrieved, based on the date when the accounts have been created in the system. The date format is &#39;yyyy-MM-ddThh:mm:ss.fff±timezone&#39;, where fff are milliseconds and timezone can be &#39;Z&#39; or &#39;hh:mm&#39; 
         * @param toCreatedAt End date for the time period from which accounts are to be retrieved, based on the date when the accounts have been created in the system. The date format is &#39;yyyy-MM-ddThh:mm:ss.fff±timezone&#39;, where fff are milliseconds and timezone can be &#39;Z&#39; or &#39;hh:mm&#39; 
         * @param accountingName Name of the account type.
         * @param accountingIdList List of account type identifiers.
         * @return ResponseEntity&lt;AccountListResponse&gt;
         * @throws WebClientResponseException if an error occurs while attempting to invoke the API
      */
      public Mono<ResponseEntity<AccountListResponse>> getClientAccountsListWithHttpInfo(HttpHeaders headers, String xSantanderClientId, String customerId, String expand, String status, Boolean historical, String accountsList, String accountIdType, String accountIdCountry, String customerName, String legalEntityName, String legalEntityShortCodeList, String productIdList, String productName, String nickname, BigDecimal limit, BigDecimal offset, String sort, String xB3Traceid, String xB3Parentspanid, String xB3Spanid, String xB3Sampled, String xSantanderDevice, String virtualAccountStructureId, String realAccountId, String realAccountIdType, String realAccountIdCountry, String additionalInfo, OffsetDateTime fromCreatedAt, OffsetDateTime toCreatedAt, String accountingName, String accountingIdList) throws WebClientResponseException {
    ParameterizedTypeReference<AccountListResponse> localVarReturnType = new ParameterizedTypeReference<AccountListResponse>() {};
      return getClientAccountsListRequestCreation(headers, xSantanderClientId, customerId, expand, status, historical, accountsList, accountIdType, accountIdCountry, customerName, legalEntityName, legalEntityShortCodeList, productIdList, productName, nickname, limit, offset, sort, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice, virtualAccountStructureId, realAccountId, realAccountIdType, realAccountIdCountry, additionalInfo, fromCreatedAt, toCreatedAt, accountingName, accountingIdList).toEntity(localVarReturnType);
      }

      /**
      * Retrieves the eMoney accounts list of PagoNXT
      * Retrieves a list for all the accounts of a PagoNXT customer.  This operation is available to PagoNXT staff and 3rd parties.  For the customer Id requested, it will return all its accounts.  Sort order by default is opening date ascending. 
         * <p><b>200</b> - Response to a request to retrieve a transaction list
         * <p><b>204</b> - No content
         * <p><b>400</b> - Bad Request
         * <p><b>401</b> - Unauthorized
         * <p><b>403</b> - Forbidden
         * <p><b>404</b> - Not found
         * <p><b>405</b> - Method Not Allowed
         * <p><b>406</b> - Not Acceptable
         * <p><b>409</b> - Conflict
         * <p><b>413</b> - Payload too large
         * <p><b>414</b> - URI too large
         * <p><b>415</b> - Unsupported Media Type
         * <p><b>422</b> - Unprocessable Entity
         * <p><b>429</b> - Too Many Requests
         * <p><b>500</b> - Internal Server Error
         * <p><b>503</b> - Service Down Error
         * <p><b>504</b> - Gateway timeout
         * <p><b>0</b> - Internal Server Error
         * @param xSantanderClientId Client ID header.
     * @param customerId Customer identifier
     * @param expand Whether balances are to be included in the response.  If the value is &#39;balances&#39;, the response includes the following balance types are:  - consolidated   - pendingConsolidation   - withholding   - overdraft   - limit   - available   - current  The default value is empty and returns no balances. 
     * @param status Status of the accounts to be included in the response. The possible values are a comma separated list of: - Open &#x3D; Account is open and movements are restricted only by the customer&#39;s available balance and account contract attributes. This includes accounts with warnings. - Blocked &#x3D; Account has some restrictive block. - Closed &#x3D; Account is closed. 
     * @param historical Whether closed accounts are included in the response. The possible values are: - true &#x3D; Accounts with closed status are included - false &#x3D; Accounts with closed status are not included    The default value is true. If the &#39;status&#39; parameter is provided, this parameter must not be used. 
     * @param accountsList List of accounts to be included in the response. The accounts must be defined with comma-separated account numbers without blank spaces.   All accounts must be identified using the account number format defined in the &#39;accountIdType&#39; parameter. The maximum number of included accounts is 20.   If you want data about more than 20 accounts, call the API multiple times, for 20 accounts at a time. 
     * @param accountIdType Format used for the account number in the &#39;account_id&#39; property.  The possible values are: - iban &#x3D; IBAN code - bban &#x3D; Basic Bank Account Number (BBAN) that represents a country-specific bank account number. Each country has its own standards for the format and length of the BBAN. The value is at most 31 char or 35 bytes long. - uuid &#x3D; Unique account identifier. It is an internal code to uniquely identify the account. Some accounts are internal so they have UUID but no IBAN/BBAN.  For example:  - In UK, the BBAN format is: Sort code(6) + Account(8) - In Spain, the BBAN format: Bank(4) + Branch(4) + CheckDigit(2) + Account(10)  - In BE, the BBAN format is: Bank(3) + Account(7) + CheckDigit(2) 
     * @param accountIdCountry Data structure containing details of the country  for the account. Needed if BBAN format por the accountIdType is requested.  Country code. The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
     * @param customerName Customer name associated with the account.
     * @param legalEntityName Legal Name of the legal entity associated with the account.
     * @param legalEntityShortCodeList Short code list of the legal entities associated with the account.
     * @param productIdList Unique identifiers of products associated with an account.
     * @param productName Name of the product associated with the account.
     * @param nickname It is an alias of account name the customer has assigned to the account for easy identification.
     * @param limit Number of values that should be informed per page
     * @param offset Pagination identifier
     * @param sort Sorting order for the retrieved accounts.  The possible values are: - +product_name &#x3D; ascending by product_name   - -product_name &#x3D; descending by product_name   - +customer_name &#x3D; ascending by customer_name   - -customer_name &#x3D; descending by customer_name   - +opening_date_time &#x3D; ascending by opening_date_time   - -opening_date_time &#x3D; descending by opening_date_time   - +last_update_date_time &#x3D; ascending by last_update_date_time   - -last_update_date_time &#x3D; descending by last_update_date_time   - +closing_date_time &#x3D; ascending by closing_date_time   - -closing_date_time &#x3D; descending by closing_date_time  If the value is omitted, the default value is &#39;-opening_date_time&#39;. 
     * @param xB3Traceid Overall ID of the trace, shared by every span in the trace.  The value is 64 or 128 bits long. 
     * @param xB3Parentspanid Position of the parent operation in the trace tree.  The value is 64 bits long. The value is omitted when the span is the root of the trace tree. 
     * @param xB3Spanid Position of the current operation in the trace tree. The value is 64 bits long.  Do not interpret the value: it may or may not be derived from the value of the TraceId. 
     * @param xB3Sampled Sampling decision. Sampling is a mechanism to reduce the volume of data in the tracing system.  In B3, sampling applies consistently per-trace: once the sampling decision is made,  the same value must be consistently sent downstream.   This means that either all or no spans share a trace ID.  The possible values are: - 0 &#x3D; Deny - 1 &#x3D; Accept 
     * @param xSantanderDevice Device information permitted for the data query. You can use this parameter to apply an experience base to optimize data collection. 
     * @param virtualAccountStructureId Virtual account structure identifier
     * @param realAccountId Real account identifier
     * @param realAccountIdType Real account type
     * @param realAccountIdCountry Data structure containing details of the country for the account.  Necessary if accountIdType BBAN format is requested.   Country code.  The value is based on the ISO 3166-1 alpha-2 (https://www.iso.org/obp/ui/#search/code/). 
     * @param additionalInfo Additional Account Info
     * @param fromCreatedAt Start date for the time period from which accounts are to be retrieved, based on the date when the accounts have been created in the system. The date format is &#39;yyyy-MM-ddThh:mm:ss.fff±timezone&#39;, where fff are milliseconds and timezone can be &#39;Z&#39; or &#39;hh:mm&#39; 
     * @param toCreatedAt End date for the time period from which accounts are to be retrieved, based on the date when the accounts have been created in the system. The date format is &#39;yyyy-MM-ddThh:mm:ss.fff±timezone&#39;, where fff are milliseconds and timezone can be &#39;Z&#39; or &#39;hh:mm&#39; 
     * @param accountingName Name of the account type.
     * @param accountingIdList List of account type identifiers.
      * @return ResponseSpec
      * @throws WebClientResponseException if an error occurs while attempting to invoke the API
      */
      public ResponseSpec getClientAccountsListWithResponseSpec(HttpHeaders headers, String xSantanderClientId, String customerId, String expand, String status, Boolean historical, String accountsList, String accountIdType, String accountIdCountry, String customerName, String legalEntityName, String legalEntityShortCodeList, String productIdList, String productName, String nickname, BigDecimal limit, BigDecimal offset, String sort, String xB3Traceid, String xB3Parentspanid, String xB3Spanid, String xB3Sampled, String xSantanderDevice, String virtualAccountStructureId, String realAccountId, String realAccountIdType, String realAccountIdCountry, String additionalInfo, OffsetDateTime fromCreatedAt, OffsetDateTime toCreatedAt, String accountingName, String accountingIdList) throws WebClientResponseException {
      return getClientAccountsListRequestCreation(headers, xSantanderClientId, customerId, expand, status, historical, accountsList, accountIdType, accountIdCountry, customerName, legalEntityName, legalEntityShortCodeList, productIdList, productName, nickname, limit, offset, sort, xB3Traceid, xB3Parentspanid, xB3Spanid, xB3Sampled, xSantanderDevice, virtualAccountStructureId, realAccountId, realAccountIdType, realAccountIdCountry, additionalInfo, fromCreatedAt, toCreatedAt, accountingName, accountingIdList);
      }
    }
