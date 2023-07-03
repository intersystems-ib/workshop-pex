# multi-stage Dockerfile

###
### builder: used to build the jars from the java source code
FROM openjdk:8 AS builder

# copy java src code to $APP_HOME
ARG APP_HOME=/tmp/app
COPY src $APP_HOME/src

# copy iris pex libraries to $APP_HOME/jgw
COPY --from=intersystemsdc/jgw:latest /jgw/*.jar $APP_HOME/jgw/

# create $APP_HOME/jar
WORKDIR $APP_HOME/jar/

# compile java source code and create a jar file in $APP_HOME/jar
# you could also use maven, gradle, ant
WORKDIR $APP_HOME/src/
RUN javac -classpath $APP_HOME/jar/*:$APP_HOME/jgw/* workshop/pex/*.java && \
    jar -cvf $APP_HOME/jar/FirstDemo.jar workshop/pex/*.class

###
### jgw: java gateway server
FROM intersystemsdc/jgw:latest

# copy jars built in builder image into java gateway server 
COPY --from=builder /tmp/app/jar/*.jar $GWDIR/
