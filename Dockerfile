FROM java:8
MAINTAINER tankechao
ADD ./target/alloy-auth.jar alloy-auth.jar
EXPOSE 9998
ENTRYPOINT ["java","-jar","alloy-auth.jar"]
