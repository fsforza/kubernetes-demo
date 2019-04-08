# Connect the microservice to MySQL

## Add MySQL JDBC Driver

In order to connect the microservice to MySQL, you must add the MySQL JDBC Driver.
To do this, remove the comments from the lines 41-45 into the **pom.xml**

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

Update microservice version to 1.1:

```bash
./mvnw -DnewVersion=1.1 -DgenerateBackupPoms=false
```

And build the new Docker Image with:

```bash
docker build -t kube-demo:1.1
```

## Deploy microservice 1.1 version

You need to update line 20 on **kubernetes/deployment.yaml** file setting the Docker Image version to 1.1

```yaml
image: kube-demo:1.1
```

And redeploy the microservice with:

```bash
kubectl apply -f kubernetes/deployment.yaml
```

## Configure the connection to MySQL
