FROM tomcat:8.5.41-alpine

LABEL maintainer=”eaka@bhci.ci”

ENV DATASOURCE_URL=$DATASOURCE_URL
ENV KEYCLOAK_URL=$KEYCLOAK_URL

ADD /target/bhciLogement.war /usr/local/tomcat/webapps/
ADD setenv.sh /usr/local/tomcat/bin/


EXPOSE 8080

# CMD [“catalina.sh”, “run”]