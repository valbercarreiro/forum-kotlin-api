FROM adoptopenjdk/openjdk11

EXPOSE 8080
ADD /target/forum-0.0.1-SNAPSHOT forum.jar
ENTRYPOINT ["java", "$JAVA_OPTS -XX:UserContainerSupport", "-Xmx300m -Xss512k -XX:CICompilerCount=2", "-Dserver.port=$PORT", "-Dspring.profiles.active=prod", "-jar", "forum.jar"]