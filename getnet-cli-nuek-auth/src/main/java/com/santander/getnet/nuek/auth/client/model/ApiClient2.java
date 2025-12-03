package com.santander.getnet.nuek.auth.client.model;

/*import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.santander.getnet.api.ExternalClientApi;
import com.santander.getnet.nuek.client.model.auth.ApiKeyAuth;
import com.santander.getnet.nuek.client.model.auth.Authentication;
import com.santander.getnet.nuek.client.model.auth.HttpBasicAuth;
import com.santander.getnet.nuek.client.model.auth.HttpBearerAuth;
import jakarta.annotation.Nullable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.http.codec.json.JacksonJsonDecoder;
import org.springframework.http.codec.json.JacksonJsonEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeType;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import tools.jackson.core.json.JsonReadFeature;
import tools.jackson.databind.json.JsonMapper;*/

public class ApiClient2 {//implements ExternalClientApi {
    /*private static final String URI_TEMPLATE_ATTRIBUTE = WebClient.class.getName() + ".uriTemplate";
    private final HttpHeaders defaultHeaders;
    private final MultiValueMap<String, String> defaultCookies;
    private String basePath;
    private WebClient webClient;
    private final DateFormat dateFormat;
    private final JsonMapper jsonMapper;
    private Map<String, Authentication> authentications;

    public ApiClient2() {
        this.defaultHeaders = new HttpHeaders();
        this.defaultCookies = new LinkedMultiValueMap<>();
        this.basePath = "https://srvnuarintra.santander.pru.bsch";
        this.dateFormat = createDefaultDateFormat();
        this.jsonMapper = createDefaultJsonMapper(this.dateFormat);
        this.webClient = buildWebClient(this.jsonMapper);
        this.init();
    }

    public ApiClient2(WebClient webClient) {
        this(Optional.ofNullable(webClient)
                .orElseGet(ApiClient2::buildWebClient), createDefaultDateFormat());
    }

    public ApiClient2(JsonMapper mapper, DateFormat format) {
        this(buildWebClient(mapper.rebuild().build()), format);
    }

    public ApiClient2(WebClient webClient, JsonMapper mapper, DateFormat format) {
        this((WebClient)Optional.ofNullable(webClient).orElseGet(() -> {
            return buildWebClient(mapper.rebuild().build());
        }), format);
    }

    private ApiClient2(WebClient webClient, DateFormat format) {
        this.defaultHeaders = new HttpHeaders();
        this.defaultCookies = new LinkedMultiValueMap<>();
        this.basePath = "https://srvnuarintra.santander.pru.bsch";
        this.webClient = webClient;
        this.dateFormat = format;
        this.jsonMapper = createDefaultJsonMapper(format);
        this.init();
    }

    public static DateFormat createDefaultDateFormat() {
        DateFormat dateFormat = new RFC3339DateFormat();
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat;
    }

    public static JsonMapper createDefaultJsonMapper(@Nullable DateFormat dateFormat) {
        if (null == dateFormat) {
            dateFormat = createDefaultDateFormat();
        }

        return JsonMapper.builder()
                .configure(JsonReadFeature.ALLOW_MISSING_VALUES, true)
                .configure(JsonReadFeature.ALLOW_SINGLE_QUOTES, true)
                .defaultDateFormat(dateFormat)
                .changeDefaultPropertyInclusion(incl -> incl.withContentInclusion(JsonInclude.Include.NON_NULL))
                .findAndAddModules()
                .build();
    }

    protected void init() {
        this.authentications = new HashMap<>();
        this.authentications.put("bearerAuth", new HttpBearerAuth("bearer"));
        this.authentications = Collections.unmodifiableMap(this.authentications);
    }

    public static WebClient.Builder buildWebClientBuilder(JsonMapper mapper) {
        ExchangeStrategies strategies = ExchangeStrategies.builder().codecs((clientDefaultCodecsConfigurer) -> {
            clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new JacksonJsonEncoder(mapper, MediaType.APPLICATION_JSON));
            clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new JacksonJsonDecoder(mapper, MediaType.APPLICATION_JSON));
        }).build();
        return WebClient.builder().exchangeStrategies(strategies);
    }

    public static WebClient.Builder buildWebClientBuilder() {
        return buildWebClientBuilder(createDefaultJsonMapper((DateFormat)null));
    }

    public static WebClient buildWebClient(JsonMapper mapper) {
        return buildWebClientBuilder(mapper).build();
    }

    public static WebClient buildWebClient() {
        return buildWebClientBuilder(createDefaultJsonMapper((DateFormat)null)).build();
    }

    public String getBasePath() {
        return this.basePath;
    }

    public ApiClient2 setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public Map<String, Authentication> getAuthentications() {
        return this.authentications;
    }

    public Authentication getAuthentication(String authName) {
        return this.authentications.get(authName);
    }

    private <T> T getAuthenticationObject(Class<T> authClass, String errorMessage) {
        return this.authentications.values().stream()
                .filter(authClass::isInstance)
                .findFirst()
                .map(authClass::cast)
                .orElseThrow(() -> new RuntimeException(errorMessage));
    }

    public void setBearerToken(String bearerToken) {
        getAuthenticationObject(HttpBearerAuth.class, "No Bearer authentication configured!")
                .setBearerToken(bearerToken);
    }

    public void setUsername(String username) {
        getAuthenticationObject(HttpBasicAuth.class, "No HTTP basic authentication configured!")
                .setUsername(username);
    }

    public void setPassword(String password) {
        getAuthenticationObject(HttpBasicAuth.class, "No HTTP basic authentication configured!")
                .setPassword(password);
    }

    public void setApiKey(String apiKey) {
        getAuthenticationObject(ApiKeyAuth.class, "No API key authentication configured!")
                .setApiKey(apiKey);
    }

    public void setApiKeyPrefix(String apiKeyPrefix) {
        getAuthenticationObject(ApiKeyAuth.class, "No API key authentication configured!")
                .setApiKeyPrefix(apiKeyPrefix);
    }

    public ApiClient2 setUserAgent(String userAgent) {
        this.addDefaultHeader("User-Agent", userAgent);
        return this;
    }

    public ApiClient2 addDefaultHeader(String name, String value) {
        if (this.defaultHeaders.containsHeader(name)) {
            this.defaultHeaders.remove(name);
        }

        this.defaultHeaders.add(name, value);
        return this;
    }

    public ApiClient2 addDefaultCookie(String name, String value) {
        this.defaultCookies.add(name, value);
        return this;
    }

    public DateFormat getDateFormat() {
        return this.dateFormat;
    }

    public Date parseDate(String str) {
        try {
            return this.dateFormat.parse(str);
        } catch (ParseException var3) {
            ParseException e = var3;
            throw new RuntimeException(e);
        }
    }

    public String formatDate(Date date) {
        return this.dateFormat.format(date);
    }

    public JsonMapper getJsonMapper() {
        return this.jsonMapper;
    }

    public WebClient getWebClient() {
        return this.webClient;
    }

    public void setWebClient(WebClient client) {
        this.webClient = client;
    }

    public String parameterToString(Object param) {
        if (param == null) {
            return "";
        } else if (param instanceof Date) {
            return this.formatDate((Date)param);
        } else if (param instanceof Collection) {
            return String.join(",", ((Collection<?>) param).stream().map(String::valueOf).toList());
        } else {
            return String.valueOf(param);
        }
    }

    @SuppressWarnings("unchecked")
    public MultiValueMap<String, String> parameterToMultiValueMap(CollectionFormat collectionFormat, String name, Object value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (name != null && !name.isEmpty() && value != null) {
            if (collectionFormat == null) {
                collectionFormat = ApiClient2.CollectionFormat.CSV;
            }

            if (value instanceof Map) {
                final var map = ((Map<String, Object>)value);
                map.keySet()
                    .forEach(key -> params.add(key, this.parameterToString(map.get(key))));

                return params;
            } else {
                if (!(value instanceof Collection)) {
                    params.add(name, this.parameterToString(value));
                    return params;
                } else {
                    var valueCollection = (Collection)value;
                    if (valueCollection.isEmpty()) {
                        return params;
                    } else if (collectionFormat.equals(ApiClient.CollectionFormat.MULTI)) {
                        valueCollection
                            .forEach(val -> params.add(name, this.parameterToString(val)));
                        return params;
                    } else {
                        Optional.of(valueCollection)
                            .map(col -> col.stream().toList())
                            .map(collectionFormat::collectionToString)
                            .ifPresent(valueString -> params.add(name, valueString));
                        return params;
                    }
                }
            }
        } else {
            return params;
        }
    }*/

    //public boolean isJsonMime(String mediaType) {
        //if ("*/*".equals(mediaType)) {
            /*return true;
        } else {
            try {
                return this.isJsonMime(MediaType.parseMediaType(mediaType));
            } catch (InvalidMediaTypeException var3) {
                return false;
            }
        }
    }

    public boolean isJsonMime(MediaType mediaType) {
        return mediaType != null && (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType) || mediaType.getSubtype().matches("^.*(\\+json|ndjson)[;]?\\s*$"));
    }

    public boolean isProblemJsonMime(String mediaType) {
        return "application/problem+json".equalsIgnoreCase(mediaType);
    }

    public List<MediaType> selectHeaderAccept(String[] accepts) {
        if (accepts.length == 0) {
            return null;
        } else {
            return Stream.of(accepts)
              .filter(accept -> this.isJsonMime(MediaType.parseMediaType(accept)) && !this.isProblemJsonMime(accept))
              .findFirst()
              .map(accept -> Collections.singletonList(MediaType.parseMediaType(accept)))
              .orElseGet(() -> MediaType.parseMediaTypes(StringUtils.arrayToCommaDelimitedString(accepts)));
        }
    }

    public MediaType selectHeaderContentType(String[] contentTypes) {
        if (contentTypes.length == 0) {
            return null;
        } else {
            return Stream.of(contentTypes)
                .map(MediaType::parseMediaType)
                .filter(this::isJsonMime)
                .findFirst()
                .orElseGet(() -> MediaType.parseMediaType(contentTypes[0]));
        }
    }

    protected BodyInserter<?, ? super ClientHttpRequest> selectBody(Object obj, MultiValueMap<String, Object> formParams, MediaType contentType) {
        if (MediaType.APPLICATION_FORM_URLENCODED.equals(contentType)) {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            formParams.toSingleValueMap().forEach((key, value) -> map.add(key, String.valueOf(value)));
            return BodyInserters.fromFormData(map);
        } else if (MediaType.MULTIPART_FORM_DATA.equals(contentType)) {
            return BodyInserters.fromMultipartData(formParams);
        } else {
            return obj != null ? BodyInserters.fromValue(obj) : null;
        }
    }

    public <T> WebClient.ResponseSpec invokeAPI(String path, HttpMethod method, Map<String, Object> pathParams, MultiValueMap<String, String> queryParams, Object body, HttpHeaders headerParams, MultiValueMap<String, String> cookieParams, MultiValueMap<String, Object> formParams, List<MediaType> accept, MediaType contentType, String[] authNames, ParameterizedTypeReference<T> returnType) throws RestClientException {
        WebClient.RequestBodySpec requestBuilder = this.prepareRequest(path, method, pathParams, queryParams, body, headerParams, cookieParams, formParams, accept, contentType, authNames);
        return requestBuilder.retrieve();
    }

    private String generateQueryUri(MultiValueMap<String, String> queryParams, Map<String, Object> uriParams) {
        StringBuilder queryBuilder = new StringBuilder();
        queryParams.forEach((name, values) -> {
            if (CollectionUtils.isEmpty(values)) {
                if (queryBuilder.length() != 0) {
                    queryBuilder.append('&');
                }

                queryBuilder.append(name);
            } else {
                int valueItemCounter = 0;
                Iterator var5 = values.iterator();

                while(var5.hasNext()) {
                    Object value = var5.next();
                    if (queryBuilder.length() != 0) {
                        queryBuilder.append('&');
                    }

                    queryBuilder.append(name);
                    if (value != null) {
                        String templatizedKey = name + valueItemCounter++;
                        uriParams.put(templatizedKey, value.toString());
                        queryBuilder.append('=').append("{").append(templatizedKey).append("}");
                    }
                }
            }

        });
        return queryBuilder.toString();
    }

    private WebClient.RequestBodySpec prepareRequest(String path, HttpMethod method, Map<String, Object> pathParams, MultiValueMap<String, String> queryParams, Object body, HttpHeaders headerParams, MultiValueMap<String, String> cookieParams, MultiValueMap<String, Object> formParams, List<MediaType> accept, MediaType contentType, String[] authNames) {
        this.updateParamsForAuth(authNames, queryParams, headerParams, cookieParams);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(this.basePath).path(path);
        String finalUri = builder.build(false).toUriString();
        Map<String, Object> uriParams = new HashMap();
        uriParams.putAll(pathParams);
        if (queryParams != null && !queryParams.isEmpty()) {
            String queryUri = this.generateQueryUri(queryParams, uriParams);
            finalUri = finalUri + "?" + queryUri;
        }

        WebClient.RequestBodySpec requestBuilder = (WebClient.RequestBodySpec)this.webClient.method(method).uri(finalUri, uriParams);
        if (accept != null) {
            requestBuilder.accept((MediaType[])accept.toArray(new MediaType[accept.size()]));
        }

        if (contentType != null) {
            requestBuilder.contentType(contentType);
        }

        this.addHeadersToRequest(headerParams, requestBuilder);
        this.addHeadersToRequest(this.defaultHeaders, requestBuilder);
        this.addCookiesToRequest(cookieParams, requestBuilder);
        this.addCookiesToRequest(this.defaultCookies, requestBuilder);
        requestBuilder.attribute(URI_TEMPLATE_ATTRIBUTE, path);
        requestBuilder.body(this.selectBody(body, formParams, contentType));
        return requestBuilder;
    }

    protected void addHeadersToRequest(HttpHeaders headers, WebClient.RequestBodySpec requestBuilder) {
        Iterator var3 = headers.headerSet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, List<String>> entry = (Map.Entry)var3.next();
            List<String> values = (List)entry.getValue();
            Iterator var6 = values.iterator();

            while(var6.hasNext()) {
                String value = (String)var6.next();
                if (value != null) {
                    requestBuilder.header((String)entry.getKey(), new String[]{value});
                }
            }
        }

    }

    protected void addCookiesToRequest(MultiValueMap<String, String> cookies, WebClient.RequestBodySpec requestBuilder) {
        Iterator var3 = cookies.entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, List<String>> entry = (Map.Entry)var3.next();
            List<String> values = (List)entry.getValue();
            Iterator var6 = values.iterator();

            while(var6.hasNext()) {
                String value = (String)var6.next();
                if (value != null) {
                    requestBuilder.cookie((String)entry.getKey(), value);
                }
            }
        }

    }

    protected void updateParamsForAuth(String[] authNames, MultiValueMap<String, String> queryParams, HttpHeaders headerParams, MultiValueMap<String, String> cookieParams) {
        String[] var5 = authNames;
        int var6 = authNames.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String authName = var5[var7];
            Authentication auth = (Authentication)this.authentications.get(authName);
            if (auth == null) {
                throw new RestClientException("Authentication undefined: " + authName);
            }

            auth.applyToParams(queryParams, headerParams, cookieParams);
        }

    }

    public String collectionPathParameterToString(CollectionFormat collectionFormat, Collection<?> values) {
        if (ApiClient2.CollectionFormat.MULTI.equals(collectionFormat)) {
            return this.parameterToString(values);
        } else {
            if (collectionFormat == null) {
                collectionFormat = ApiClient2.CollectionFormat.CSV;
            }

            return collectionFormat.collectionToString(values);
        }
    }

    public static enum CollectionFormat {
        CSV(","),
        TSV("\t"),
        SSV(" "),
        PIPES("|"),
        MULTI((String)null);

        private final String separator;

        private CollectionFormat(String separator) {
            this.separator = separator;
        }

        private String collectionToString(Collection<?> collection) {
            return StringUtils.collectionToDelimitedString(collection, this.separator);
        }
    }*/
}
