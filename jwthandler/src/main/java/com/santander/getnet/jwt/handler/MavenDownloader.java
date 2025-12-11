package com.santander.getnet.jwt.handler;

import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class MavenDownloader {
    private static final String URL_LOCAL_BASE = "C:/Users/x283611/.m2/repository";
    private static final String URL_BASE = "https://nexus.alm.europe.cloudcenter.corp/repository/maven-releases-master";

    private static final String[] URLS = {
    "https://repo.maven.apache.org/maven2/com/fasterxml/jackson/core/jackson-core/2.19.2/jackson-core-2.19.2.jar"/*,
    "https://repo.maven.apache.org/maven2/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.19.2/jackson-datatype-jsr310-2.19.2.jar",
    "https://repo.maven.apache.org/maven2/io/micrometer/micrometer-observation/1.15.4/micrometer-observation-1.15.4.jar",
    "https://repo.maven.apache.org/maven2/io/micrometer/micrometer-commons/1.15.4/micrometer-commons-1.15.4.jar",
    "https://repo.maven.apache.org/maven2/io/micrometer/micrometer-jakarta9/1.15.4/micrometer-jakarta9-1.15.4.jar",
    "https://repo.maven.apache.org/maven2/io/micrometer/micrometer-core/1.15.4/micrometer-core-1.15.4.jar",
    "https://repo.maven.apache.org/maven2/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.19.2/jackson-datatype-jdk8-2.19.2.jar",
    "https://repo.maven.apache.org/maven2/com/fasterxml/jackson/module/jackson-module-parameter-names/2.19.2/jackson-module-parameter-names-2.19.2.jar",
    "https://repo.maven.apache.org/maven2/io/swagger/core/v3/swagger-core-jakarta/2.2.36/swagger-core-jakarta-2.2.36.jar",
    "https://repo.maven.apache.org/maven2/io/swagger/core/v3/swagger-models-jakarta/2.2.36/swagger-models-jakarta-2.2.36.jar",
    "https://repo.maven.apache.org/maven2/org/webjars/swagger-ui/5.28.1/swagger-ui-5.28.1.jar",
    "https://repo.maven.apache.org/maven2/io/projectreactor/reactor-core/3.7.11/reactor-core-3.7.11.jar",
    "https://repo.maven.apache.org/maven2/io/projectreactor/netty/reactor-netty-core/1.2.10/reactor-netty-core-1.2.10.jar",
    "https://repo.maven.apache.org/maven2/io/netty/netty-common/4.1.127.Final/netty-common-4.1.127.Final.jar",
    "https://repo.maven.apache.org/maven2/io/projectreactor/netty/reactor-netty-http/1.2.10/reactor-netty-http-1.2.10.jar",
    "https://repo.maven.apache.org/maven2/io/projectreactor/netty/incubator/reactor-netty-incubator-quic/0.2.10/reactor-netty-incubator-quic-0.2.10.jar",
    "https://repo.maven.apache.org/maven2/org/apache/commons/commons-lang3/3.17.0/commons-lang3-3.17.0.jar",
    "https://repo.maven.apache.org/maven2/io/swagger/core/v3/swagger-annotations-jakarta/2.2.36/swagger-annotations-jakarta-2.2.36.jar",
    "https://repo.maven.apache.org/maven2/io/zipkin/contrib/brave-propagation-w3c/brave-propagation-tracecontext/0.2.0/brave-propagation-tracecontext-0.2.0.jar",
    "https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-slf4j2-impl/2.24.3/log4j-slf4j2-impl-2.24.3.jar",
    "https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-core/2.24.3/log4j-core-2.24.3.jar",
    "https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-jul/2.24.3/log4j-jul-2.24.3.jar",
    "https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-jcl/2.24.3/log4j-jcl-2.24.3.jar",
    "https://repo.maven.apache.org/maven2/commons-logging/commons-logging/1.3.4/commons-logging-1.3.4.jar",
    "https://repo.maven.apache.org/maven2/com/lmax/disruptor/4.0.0/disruptor-4.0.0.jar",
    "https://repo.maven.apache.org/maven2/org/aspectj/aspectjweaver/1.9.24/aspectjweaver-1.9.24.jar",
    "https://repo.maven.apache.org/maven2/com/github/luben/zstd-jni/1.5.6-4/zstd-jni-1.5.6-4.jar",
    "https://repo.maven.apache.org/maven2/org/xerial/snappy/snappy-java/1.1.10.5/snappy-java-1.1.10.5.jar",
    "https://repo.maven.apache.org/maven2/com/nimbusds/nimbus-jose-jwt/9.37.4/nimbus-jose-jwt-9.37.4.jar",
    "https://repo.maven.apache.org/maven2/io/projectreactor/addons/reactor-extra/3.5.3/reactor-extra-3.5.3.jar",
    "https://repo.maven.apache.org/maven2/org/owasp/encoder/encoder/1.3.1/encoder-1.3.1.jar",
    "https://repo.maven.apache.org/maven2/com/sun/xml/messaging/saaj/saaj-impl/3.0.4/saaj-impl-3.0.4.jar",
    "https://repo.maven.apache.org/maven2/jakarta/xml/soap/jakarta.xml.soap-api/3.0.2/jakarta.xml.soap-api-3.0.2.jar",
    "https://repo.maven.apache.org/maven2/org/glassfish/jaxb/jaxb-runtime/4.0.5/jaxb-runtime-4.0.5.jar",
    "https://repo.maven.apache.org/maven2/org/glassfish/jaxb/jaxb-core/4.0.5/jaxb-core-4.0.5.jar",
    "https://repo.maven.apache.org/maven2/org/glassfish/jaxb/txw2/4.0.5/txw2-4.0.5.jar",
    "https://repo.maven.apache.org/maven2/com/sun/istack/istack-commons-runtime/4.1.2/istack-commons-runtime-4.1.2.jar",
    "https://repo.maven.apache.org/maven2/org/apache/santuario/xmlsec/4.0.4/xmlsec-4.0.4.jar",
    "https://repo.maven.apache.org/maven2/com/fasterxml/woodstox/woodstox-core/7.1.0/woodstox-core-7.1.0.jar",
    "https://repo.maven.apache.org/maven2/org/codehaus/woodstox/stax2-api/4.2.2/stax2-api-4.2.2.jar",
    "https://repo.maven.apache.org/maven2/org/jvnet/staxex/stax-ex/2.1.0/stax-ex-2.1.0.jar",
    "https://repo.maven.apache.org/maven2/jakarta/xml/ws/jakarta.xml.ws-api/4.0.2/jakarta.xml.ws-api-4.0.2.jar",
    "https://repo.maven.apache.org/maven2/org/mongodb/mongodb-driver-sync/5.5.1/mongodb-driver-sync-5.5.1.jar",
    "https://repo.maven.apache.org/maven2/org/mongodb/bson/5.5.1/bson-5.5.1.jar",
    "https://repo.maven.apache.org/maven2/org/mongodb/mongodb-driver-core/5.5.1/mongodb-driver-core-5.5.1.jar",
    "https://repo.maven.apache.org/maven2/org/mongodb/bson-record-codec/5.5.1/bson-record-codec-5.5.1.jar",
    "https://repo.maven.apache.org/maven2/net/minidev/json-smart/2.5.2/json-smart-2.5.2.jar",
    "https://repo.maven.apache.org/maven2/net/minidev/accessors-smart/2.5.2/accessors-smart-2.5.2.jar",
    "https://repo.maven.apache.org/maven2/org/mockito/mockito-core/5.17.0/mockito-core-5.17.0.jar",
    "https://repo.maven.apache.org/maven2/org/mockito/mockito-junit-jupiter/5.17.0/mockito-junit-jupiter-5.17.0.jar",
    "https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter/5.12.2/junit-jupiter-5.12.2.jar",
    "https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter-api/5.12.2/junit-jupiter-api-5.12.2.jar",
    "https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-commons/1.12.2/junit-platform-commons-1.12.2.jar",
    "https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter-params/5.12.2/junit-jupiter-params-5.12.2.jar",
    "https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter-engine/5.12.2/junit-jupiter-engine-5.12.2.jar",
    "https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-launcher/1.12.2/junit-platform-launcher-1.12.2.jar",
    "https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-engine/1.12.2/junit-platform-engine-1.12.2.jar",
    "https://repo.maven.apache.org/maven2/org/junit/vintage/junit-vintage-engine/5.12.2/junit-vintage-engine-5.12.2.jar",
    "https://repo.maven.apache.org/maven2/commons-codec/commons-codec/1.18.0/commons-codec-1.18.0.jar",
    "https://repo.maven.apache.org/maven2/commons-io/commons-io/2.20.0/commons-io-2.20.0.jar",
    "https://repo.maven.apache.org/maven2/org/yaml/snakeyaml/2.4/snakeyaml-2.4.jar",
    "https://repo.maven.apache.org/maven2/jakarta/servlet/jakarta.servlet-api/6.0.0/jakarta.servlet-api-6.0.0.jar",
    "https://repo.maven.apache.org/maven2/commons-codec/commons-codec/1.18.0/commons-codec-1.18.0.jar",
    "https://repo.maven.apache.org/maven2/commons-io/commons-io/2.20.0/commons-io-2.20.0.jar"*/
};

    public static void main(String[] args) {

        var downloader = new MavenDownloader();

        Stream.of(URLS)
           .forEach(url -> downloader.download(url, downloader.buildLocalUri(url)));
    }

    URI buildLocalUri(String url) {
        return URI.create("file:///" + URL_LOCAL_BASE + url.substring(URL_BASE.length()));
    }

    void download(String url, URI uri) {
        try {
            var urlObject = new URL(url);
            try (var in = urlObject.openStream()) {
                Files.copy(in, Paths.get(uri), StandardCopyOption.REPLACE_EXISTING);
                System.out.println(uri.toASCIIString() + " saved OK");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
