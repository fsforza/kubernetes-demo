# Microservice REST Api

## Basic Auth

If you have activated Basic Authentication, launch **curl** with:

```bash
curl -u kube-demo:changeme1 ...
```

## HTTPS

If you have activated HTTPS with a self-signed certificated, launch **curl** with:

```bash
curl -k ...
```

## Create Person

```bash
curl -v -X POST -H "Content-Type: application/json" -d '{ "name": "person A", "age": 23, "emailId": "person.a@kube-demo.com" }' http://kube-demo.local/persons
```

## Get Person

Now, you can add the Ingress Rule with:

```bash
curl -v -H "Accept: application/json" http://kube-demo.local/persons/1
```
