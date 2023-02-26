This is the application server situationthat we need to support

Editors's Note: we could go for docker to really impress them. but that might be dtm

https://dev.to/jakmar17/deploy-spring-boot-on-wildfly-application-server-2029
DEV Community 👩💻👨💻DEV Community 👩💻👨💻
Deploy Spring Boot on WildFly application server
By default Spring Boot comes with embedded Tomcat server, which, when build as fat jar enables deploy... (36 kB)
https://dev.to/jakmar17/deploy-spring-boot-on-wildfly-application-server-2029

10:18
<?xml version="1.0" encoding="UTF-8"?>
<jboss-deployment-structure>
	<deployment>
		<exclusions>
			<module name='org.slf4j' />
			<module name='org.slf4j.impl' />
		</exclusions>
	</deployment>
</jboss-deployment-structure>