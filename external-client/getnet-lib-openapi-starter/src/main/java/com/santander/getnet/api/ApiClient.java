package com.santander.getnet.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;
import reactor.util.retry.Retry;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ApiClient implements ExternalClientApi {
    private static final Logger LOG = LoggerFactory.getLogger(ApiClient.class);
    private static final String URI_TEMPLATE_ATTRIBUTE = WebClient.class.getName() + ".uriTemplate";
    private final HttpHeaders defaultHeaders;
    private final MultiValueMap<String, String> defaultCookies;
    private String basePath;
    private int retries;
    private int retrySeconds;
    private WebClient webClient;
    private final DateFormat dateFormat;
    private final ObjectMapper objectMapper;
    private Map<String, Object> authentications;

    public ApiClient() {
        this.defaultHeaders = new HttpHeaders();
        this.defaultCookies = new LinkedMultiValueMap<>();
        this.basePath = "https://srvnuarintra.santander.pru.bsch";
        this.retries = 0;
        this.retrySeconds = 0;
        this.dateFormat = createDefaultDateFormat();
        this.objectMapper = createDefaultObjectMapper(this.dateFormat);
        this.webClient = buildWebClient(this.objectMapper);
        this.init();
    }

    public ApiClient(WebClient webClient) {
        this(Optional.ofNullable(webClient)
                .orElseGet(ApiClient::buildWebClient), createDefaultDateFormat());
    }

    public ApiClient(ObjectMapper mapper, DateFormat format) {
        this(buildWebClient(mapper.copy()), format);
    }

    public ApiClient(WebClient webClient, ObjectMapper mapper, DateFormat format) {
        this(Optional.ofNullable(webClient)
                .orElseGet(() -> buildWebClient(mapper.copy())), format);
    }

    private ApiClient(WebClient webClient, DateFormat format) {
        this.defaultHeaders = new HttpHeaders();
        this.defaultCookies = new LinkedMultiValueMap<>();
        this.basePath = "https://srvnuarintra.santander.pru.bsch";
        this.retries = 0;
        this.retrySeconds = 0;
        this.webClient = webClient;
        this.dateFormat = format;
        this.objectMapper = createDefaultObjectMapper(format);
        this.init();
    }

    public static DateFormat createDefaultDateFormat() {
        //DateFormat dateFormat = new RFC3339DateFormat();
        //dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return null;
    }

    public static ObjectMapper createDefaultObjectMapper(@Nullable DateFormat dateFormat) {
        if (null == dateFormat) {
            dateFormat = createDefaultDateFormat();
        }

        final var dtFormat = dateFormat;

        return Optional.of(new JsonNullableModule())
            .map(new ObjectMapper()::registerModule)
            .map(mapper -> mapper.setDateFormat(dtFormat))
            .map(mapper -> mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false))
            .orElse(null);
    }

    protected void init() {
    }

    public static WebClient.Builder buildWebClientBuilder(ObjectMapper mapper) {
        return Optional.of(ExchangeStrategies.builder())
            .map(builder -> builder.codecs(clientDefaultCodecsConfigurer -> {
                clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(
                     new Jackson2JsonEncoder(mapper, MediaType.APPLICATION_JSON));
                 clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(
                     new Jackson2JsonDecoder(mapper, MediaType.APPLICATION_JSON));
                })
            )
            .map(ExchangeStrategies.Builder::build)
            .map(WebClient.builder()::exchangeStrategies)
            .orElse(null);
    }

    public static WebClient.Builder buildWebClientBuilder() {
        return buildWebClientBuilder(createDefaultObjectMapper(null));
    }

    public static WebClient buildWebClient(ObjectMapper mapper) {
        return buildWebClientBuilder(mapper).build();
    }

    public static WebClient buildWebClient() {
        return buildWebClientBuilder(createDefaultObjectMapper(null)).build();
    }

    public String getBasePath() {
        return this.basePath;
    }

    public ApiClient setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public int getRetries() {
        return this.retries;
    }

    public ApiClient setRetries(int numRetries) {
        this.retries = numRetries;
        return this;
    }

    public int getRetrySeconds() {
        return this.retrySeconds;
    }

    public ApiClient setRetrySeconds(int seconds) {
        this.retrySeconds = seconds;
        return this;
    }

    /*public Map<String, Authentication> getAuthentications() {
        return this.authentications;
    }

    public Authentication getAuthentication(String authName) {
        return this.authentications.get(authName);
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
    }*/

    public ApiClient setUserAgent(String userAgent) {
        this.addDefaultHeader("User-Agent", userAgent);
        return this;
    }

    public ApiClient addDefaultHeader(String name, String value) {
        if (this.defaultHeaders.containsKey(name)) {
            this.defaultHeaders.remove(name);
        }

        this.defaultHeaders.add(name, value);
        return this;
    }

    public ApiClient addDefaultCookie(String name, String value) {
        this.defaultCookies.add(name, value);
        return this;
    }

    public DateFormat getDateFormat() {
        return this.dateFormat;
    }

    public Date parseDate(String str) {
        try {
            return this.dateFormat.parse(str);
        } catch (ParseException pe) {
            throw new RuntimeException(pe);
        }
    }

    public String formatDate(Date date) {
        return this.dateFormat.format(date);
    }

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
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
                collectionFormat = CollectionFormat.CSV;
            }

            if (value instanceof Map) {
                final var map = ((Map<String, Object>)value);
                map.keySet()
                    .forEach(key -> params.add(key, this.parameterToString(map.get(key))));

                return params;
            } else {
                if (!(value instanceof Collection valueCollection)) {
                    params.add(name, this.parameterToString(value));
                    return params;
                } else {
                    if (valueCollection.isEmpty()) {
                        return params;
                    } else if (collectionFormat.equals(CollectionFormat.MULTI)) {
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
    }

    public boolean isJsonMime(String mediaType) {
        if ("*/*".equals(mediaType)) {
            return true;
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
            var newMap = formParams.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> List.of(String.valueOf(entry.getValue()))));
            return BodyInserters.fromFormData(MultiValueMap.fromMultiValue(newMap));
        } else if (MediaType.MULTIPART_FORM_DATA.equals(contentType)) {
            return BodyInserters.fromMultipartData(formParams);
        } else {
            return obj != null ? BodyInserters.fromValue(obj) : null;
        }
    }

    public <T> WebClient.ResponseSpec invokeAPI(String path, HttpMethod method, Map<String, Object> pathParams, MultiValueMap<String, String> queryParams, Object body, HttpHeaders headerParams, MultiValueMap<String, String> cookieParams, MultiValueMap<String, Object> formParams, List<MediaType> accept, MediaType contentType, String[] authNames, ParameterizedTypeReference<T> returnType) throws RestClientException {
        return this.prepareRequest(path, method, pathParams, queryParams, body, headerParams, cookieParams, formParams, accept, contentType, authNames)
            .retrieve();
    }

    private String generateQueryUri(MultiValueMap<String, String> queryParams, Map<String, Object> uriParams) {

        Function<String, List<Tuple3<String, String, String>>> flattenMap = name -> Optional.of(name)
            .map(queryParams::get)
            .filter(Predicate.not(Collection::isEmpty))
            .map(values -> IntStream.range(0, values.size())
                .boxed()
                .map(idx -> Tuples.of(name, name + idx, values.get(idx)))
                .toList())
            .orElseGet(() -> List.of(Tuples.of(name, name, "")));

        Function<Tuple3<String, String, String>, String> toQueryParam = data -> {
            if (!data.getT1().equals(data.getT2())) {
              uriParams.put(data.getT2(), data.getT3());
              return data.getT1() + "={" + data.getT2() + "}";
            } else {
              return data.getT1();
            }
        };

        return queryParams.keySet().stream()
           .map(flattenMap)
           .flatMap(List::stream)
           .map(toQueryParam)
           .collect(Collectors.joining("&"));
    }

    private WebClient.RequestBodySpec prepareRequest(String path, HttpMethod method, Map<String, Object> pathParams, MultiValueMap<String, String> queryParams, Object body, HttpHeaders headerParams, MultiValueMap<String, String> cookieParams, MultiValueMap<String, Object> formParams, List<MediaType> accept, MediaType contentType, String[] authNames) {
        this.updateParamsForAuth(authNames, queryParams, headerParams, cookieParams);

        Map<String, Object> uriParams = new HashMap<>(pathParams);

        UnaryOperator<String> finalizeUriBuild = uri -> Optional.of(uri)
            .filter(value -> Objects.nonNull(queryParams) && !queryParams.isEmpty())
            .map(value -> value + "?" + this.generateQueryUri(queryParams, uriParams))
            .orElse(uri);

        var requestBuilder = Optional.of(path)
            .map(UriComponentsBuilder.fromUriString(this.basePath)::path)
            .map(builder -> builder.build(false).toUriString())
            .map(finalizeUriBuild)
            .map(uri -> this.webClient.method(method).uri(uri, uriParams))
            .orElseThrow(RuntimeException::new);

        if (accept != null) {
            requestBuilder.accept(accept.toArray(MediaType[]::new));
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

    private <T> T getAuthenticationObject(Class<T> authClass, String errorMessage) {
        return this.authentications.values().stream()
                .filter(authClass::isInstance)
                .findFirst()
                .map(authClass::cast)
                .orElseThrow(() -> new RuntimeException(errorMessage));
    }

    protected void addHeadersToRequest(HttpHeaders headers, WebClient.RequestBodySpec requestBuilder) {
        headers.headerSet()
            .forEach(header ->
                requestBuilder.header(header.getKey(), header.getValue().toArray(String[]::new)));
    }

    protected void addCookiesToRequest(MultiValueMap<String, String> cookies, WebClient.RequestBodySpec requestBuilder) {
        cookies.forEach((name, values) ->
            requestBuilder.cookie(name, values.stream().findFirst().orElse("")));
    }

    protected void updateParamsForAuth(String[] authNames, MultiValueMap<String, String> queryParams, HttpHeaders headerParams, MultiValueMap<String, String> cookieParams) {
        UnaryOperator<String> checkAuthenticationExists = name -> Optional.of(name)
            .filter(this.authentications::containsKey)
            .orElseThrow(() -> new RestClientException("Authentication undefined: " + name));

        /*Stream.of(authNames)
            .map(checkAuthenticationExists)
            .map(this.authentications::get)
            .forEach(auth -> auth.applyToParams(queryParams, headerParams, cookieParams));*/
    }

    public Retry getRetryConfig(String apiName) {
        return Retry.backoff(this.getRetries(), Duration.ofSeconds(this.getRetrySeconds()))
                .doBeforeRetry(signal -> LOG.debug("Error getting data from api: {}. Trying to get data again", apiName))
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
                        new RuntimeException(apiName + " Service failed to process after max retries"));
    }

    public String collectionPathParameterToString(CollectionFormat collectionFormat, Collection<?> values) {
        final var collFormat = Objects.isNull(collectionFormat) ? CollectionFormat.CSV : collectionFormat;

        return Optional.of(collFormat)
            .filter(CollectionFormat.MULTI::equals)
            .map(format -> this.parameterToString(values))
            .orElseGet(() -> collFormat.collectionToString(values));
    }

    public enum CollectionFormat {
        CSV(","),
        TSV("\t"),
        SSV(" "),
        PIPES("|"),
        MULTI(",");

        private final String separator;

        CollectionFormat(String separator) {
            this.separator = separator;
        }

        private String collectionToString(Collection<?> collection) {
            return StringUtils.collectionToDelimitedString(collection, this.separator);
        }
    }
}
