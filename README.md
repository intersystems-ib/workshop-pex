# Workshop: PEX
This repository contains the materials and some examples you can use to learn the basic concepts of PEX (Production EXtension) in IRIS Interoperability. 

You can find more in-depth information in https://learning.intersystems.com.

# What do you need to install? 
* [Git](https://git-scm.com/downloads) 
* [Docker](https://www.docker.com/products/docker-desktop) (if you are using Windows, make sure you set your Docker installation to use "Linux containers").
* [Docker Compose](https://docs.docker.com/compose/install/)
* [Visual Studio Code](https://code.visualstudio.com/download) + [InterSystems ObjectScript VSCode Extension](https://marketplace.visualstudio.com/items?itemName=daimor.vscode-objectscript)

# Setup
Build the image and run the container we will use during the workshop:

```console
$ git clone https://github.com/intersystems-ib/workshop-pex
$ cd workshop-pex
$ docker-compose build
$ docker-compose up -d
```

Then copy some libraries you will need from the *iris* container:

In a VS Code Terminal type:
```console
docker cp iris:/usr/irissys/dev/java/lib/JDK18/intersystems-gateway-3.1.0.jar java/lib
docker cp iris:/usr/irissys/dev/java/lib/JDK18/intersystems-jdbc-3.1.0.jar java/lib
docker cp iris:/usr/irissys/dev/java/lib/JDK18/intersystems-utils-3.1.0.jar java/lib
docker cp iris:/usr/irissys/dev/java/lib/gson/gson-2.8.5.jar java/lib
```
