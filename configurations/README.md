# CONFIGURATION CENTRAL
Centralize all service configuration into a single server using 
Spring Cloud power to provide a way to externalize and manage
configuration within a distributed system.

## How to use
Step 1: Create a service folder in the ***resources/configs***

Step 2: Provide all configuration files with postfix is environment name

***Note that: properties will be found from global file first, 
then be override by the environment file if duplicated***. For example:
- serviceName.yml (global)
- serviceName-qa.yml
- serviceName-prod.yml

Step 3: Bind the property file into the client service
- Import spring-cloud-config-client dependency
```agsl
       <dependencies>
            ...
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-config-client</artifactId>
            </dependency>
       </dependencies>
            
       
        <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```
- Add this configuration to local application:
```agsl
spring:
  application:
    name: serviceName
  profiles:
    active: environment -- default,qa,prod
  config:
    import: "optional:configserver:http://localhost:8071/finapp-configuration-central"
```
- Rerun service and check API getBuildVersion, getServiceInfo