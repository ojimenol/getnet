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
    private static final String URL_BASE2 = "https://repo.maven.apache.org/maven2";
    private static final String[] URLS = {
            "https://nexus.alm.europe.cloudcenter.corp/repository/maven-releases-master/org/junit/platform/junit-platform-engine/1.13.3/junit-platform-engine-1.13.3.jar",
            "https://nexus.alm.europe.cloudcenter.corp/repository/maven-releases-master/org/junit/platform/junit-platform-commons/1.13.3/junit-platform-commons-1.13.3.jar"

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
