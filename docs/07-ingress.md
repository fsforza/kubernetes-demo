# Ingress

With Kubernetes [Ingress](https://kubernetes.io/docs/concepts/services-networking/ingress/), we can expose the Microservice to the outside.

## Deploy NGINX Ingress Controller

First, of all you must deploy an Ingress Controller, for example [NGINX](https://kubernetes.github.io/ingress-nginx/).

You can do this with:

```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/mandatory.yaml
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/provider/cloud-generic.yaml
```

## Upload Ingress Rule

Now, you can add the Ingress Rule with:

```bash
kubectl apply -f kubernetes/ingress.yaml
```

And obtain the created Ingress Rule with:

```bash
kubectl get ing kube-demo -n demo
```

## Set Microservice DNS

The Ingress is exposed at **127.0.0.1** and the applied Ingress Rule uses the domain **kube-demo.local**.
So you need the set the domain on your DNS server or update *C:\Windows\System32\drivers\etc\hosts* file adding:

```cmd
127.0.0.1       localhost kube-demo.local
```

## Invoke Microservice through Ingress

You can invoke the microservice with:

```bash
curl http://kube-demo.local/actuator/health
```

## Add Basic Authentication

Ingress can also add Basic Authentication to our pod.
To do that, you need to generic an **auth** file with **htpasswd**

```bash
htpasswd -bc auth kube-demo changeme1
```

And upload it as Secret:

```bash
kubectl create secret generic ingress-auth --from-file=auth
```

Then, remove comments from line 6-8 from file **kubernetes/ingress.yaml** and update ingress rule with:

```bash
kubectl apply -f kubernetes/ingress.yaml
```

You can invoke the microservice with:

```bash
curl -u kube-demo:changeme1 http://kube-demo.local/actuator/health
```
