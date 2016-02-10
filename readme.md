# Mandiri Hackathon E-Cash API for Java

Java Wrapper for Mandiri E-Cash API.

## Add maven dependency

Add following tags to your pom.xml

```
<project>
    ...
    <dependencies>
        ...
        <dependency>
            <groupId>com.github.yafithekid</groupId>
            <artifactId>mandiri-ecash-api-java</artifactId>
            <version>0.1.1</version>
        </dependency>
        ...
    </dependencies>
    ...
    <repositories>
       ...
       <repository>
           <id>jitpack.io</id>
           <url>https://jitpack.io</url>
       </repository>
       ...
    </repositories>
    ...
</project>
```

## How to use

```
MEASyncRESTClient meaSyncRESTClient = new MEASyncRESTClientImpl();
String uid = "1"; //change to your user id
String msisdn = "081112345678"; //change to your phone number
String credentials = "111111"; //change to your password
try {
    MEALoginResponse meaLoginResponse = meaSyncRESTClient.login((new MEALoginRequest.Builder())
        .uid(uid)
        .msisdn(msisdn)
        .credentials(credentials)
        .build());
        
    System.out.println(meaLoginResponse.getStatus());
    System.out.println(meaLoginResponse.getToken());
} catch (MEAIOException e){
    e.printStackTrace();
}
```
