#Build IRIS image
ARG IMAGE=containers.intersystems.com/intersystems/iris-community:latest-em

FROM ${IMAGE}

USER root
RUN mkdir -p /opt/pex && chown ${ISC_PACKAGE_MGRUSER}:${ISC_PACKAGE_IRISGROUP} /opt/pex

USER ${ISC_PACKAGE_MGRUSER}

#Java PEX Compilation
#Copy java source code

COPY --chown=${ISC_PACKAGE_MGRUSER}:${ISC_PACKAGE_IRISGROUP} \
    java/src /tmp/java/src

#Compile Java classes using IRIS Java libraries

RUN mkdir -p /opt/pex/build &&\
    javac --release 11 \
    -cp "/usr/irissys/dev/java/lib/1.8/*" \
    -d /opt/pex/build \
    /tmp/java/src/workshop/pex/*.java && \
    jar -cvf /opt/pex/FirstDemo.jar -C /opt/pex/build . && \
    rm -rf /opt/pex/build /tmp/java

# IRIS Configuration

COPY --chown=$ISC_PACKAGE_MGRUSER:$ISC_PACKAGE_IRISGROUP iris/irissession.sh /
RUN chmod +x /irissession.sh

# copy iris source code
COPY --chown=$ISC_PACKAGE_MGRUSER:$ISC_PACKAGE_IRISGROUP iris/src /tmp

SHELL ["/irissession.sh"]

RUN \
  zn "%SYS" \
  set gateway("Type") = "Java" \
  set gateway("Port") = 44445 \
  set gateway("UseSharedMemory") = 0 \
  set gateway("JavaHome") = ""\
  set gateway("ClassPath") = "/opt/pex/FirstDemo.jar:/usr/irissys/dev/java/lib/1.8/*"\
  set sc = ##class(Config.Gateways).Create("PEXJavaServer", .gateway) \
  if 'sc { do $SYSTEM.Status.DisplayError(sc) do ##class(%SYSTEM.Process).Terminate(, 1) } \
  zn "USER" \
  # load & compile source code
  do $SYSTEM.OBJ.LoadDir("/tmp", "ck", , 1) \
  do ##class(%File).RemoveDirectoryTree("/tmp") \
  # start production
  set production = "Workshop.PEX.FirstDemo.Production" \
  set ^Ens.Configuration("csp","LastProduction") = production \
  do ##class(Ens.Director).SetAutoStart(production) \
  set sc = 1

# bringing the standard shell back
SHELL ["/bin/bash", "-c"]
