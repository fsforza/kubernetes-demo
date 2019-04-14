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

## Set Microservice Domain

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

Ingress can add Basic Authentication to our pod.
To do that, you need to generate an **auth** file with **htpasswd**

```bash
htpasswd -bc auth kube-demo changeme1
```

And upload it as Secret:

```bash
kubectl create secret generic ingress-auth --from-file=auth
```

Then, remove comments from line 6-8 from file **kubernetes/ingress.yaml**

```yaml
annotations:
  nginx.ingress.kubernetes.io/auth-type: basic
  nginx.ingress.kubernetes.io/auth-secret: ingress-auth
```

And update ingress rule with:

```bash
kubectl apply -f kubernetes/ingress.yaml
```

You can now invoke the microservice with:

```bash
curl -u kube-demo:changeme1 http://kube-demo.local/actuator/health
```

## Add TLS

Ingress can also expose the microservice in **HTTPS**.
First, you need to create certificates with **openssl**:

```bash
openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout ./tls.key -out ./tls.crt -subj "/CN=kube-demo.local"
```

Upload then as Secret with:

```bash
kubectl create secret tls kube-demo-tls --key ./tls.key --cert ./tls.crt
```

Then, remove comments from lines 10-13 from file **kubernetes/ingress.yaml**:

```yaml
tls:
  - hosts:
    - kube-demo.local
    secretName: kube-demo-tls
```

You can now invoke the microservice in **HTTPS**:

```bash
curl -k -u kube-demo:changeme1 https://kube-demo.local/actuator/health
```
