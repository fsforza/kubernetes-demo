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

Build the microservice with:

```bash
./mvnw clean package
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

### Create MySQL Service

Create the MySQL [Service](https://kubernetes.io/docs/concepts/services-networking/service/) with:

```bash
kubectl apply -f kubernetes/mysql-service.yaml
```

You can obtain the created service with:

```bash
kubectl get svc -n demo
```

### Create ConfigMap

You must upload the properties files as [ConfigMap](https://kubernetes.io/docs/tasks/configure-pod-container/configure-pod-configmap/) with:

```bash
kubectl create cm kube-demo from-file=./kubernetes/configmap -n demo
```

You can obtain the created confimap with:

```bash
kubectl get cm kube-demo -n demo -o yaml
```

### Add ConfigMap into the microservice

You need to mount the ConfigMap as Volume into the microservice.
To do this, remove the comments from lines 58-64 into **kubernetes/deployment.yaml** file:

```yaml
    volumeMounts:
      - mountPath: /home/kube-demo/config
        name: kube-demo
volumes:
- configMap:
    name: kube-demo
  name: kube-demo
```

And redeploy the microservice with:

```bash
kubectl apply -f kubernetes/deployment.yaml
```

After that, you can check the mounted configmap going into the microservice pod shell:

```bash
POD=$(kubectl get pods -l app=kube-demo -n demo -o jsonpath='{.items[0].metadata.name}')
kubectl exec -it $POD sh -n demo
```

and:

```bash
ls -l /home/kube-demo/config
```

## Configure the microservice to use application-prod.properties file

Now you need to instruct the microservice to use the **application-prod.properties** file.
To do this, remove the comment from the lines into **kubernetes/deployment.yaml** file:

```yaml
env:
- name: SPRING_CONFIG_LOCATION
  value: classpath:application.properties,file:/home/kube-demo/config/
- name: SPRING_PROFILES_ACTIVE
  value: prod
```

To learn more about how Spring Boot handles configuration files, you can read the paragraph [Spring Boot Externalized Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html) from its official documentation.

Then, redeploy the microservice with:

```bash
kubectl apply -f kubernetes/deployment.yaml
```

After that, check the log to see the connection to MySQL Database:

```bash
POD=$(kubectl get pods -l app=kube-demo -n demo -o jsonpath='{.items[0].metadata.name}')
kubectl logs -f $POD -n demo
```
