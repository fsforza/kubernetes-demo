# Microservice REST Api

## Create Person

```bash
curl -v -X POST -H "Content-Type: application/json" -d '{ "name": "person A", "age": 23, "emailId": "person.a@kube-demo.com" }' http://kube-demo.local/persons
```

## Get Person

Now, you can add the Ingress Rule with:

```bash
curl -v -H "Accept: application/json" http://kube-demo.local/persons/1
```
