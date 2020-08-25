FROM openjdk:8 AS builder

ARG APP_HOME=/tmp/app

COPY src $APP_HOME/src

COPY --from=intersystemscommunity/jgw:latest /jgw/*.jar $APP_HOME/jgw/

WORKDIR $APP_HOME/jar/

WORKDIR $APP_HOME/src/

RUN javac -classpath $APP_HOME/jar/*:$APP_HOME/jgw/* workshop/pex/*.java && \
    jar -cvf $APP_HOME/jar/FirstDemo.jar workshop/pex/*.class

FROM intersystemscommunity/jgw:latest

COPY --from=builder /tmp/app/jar/*.jar $GWDIR/
