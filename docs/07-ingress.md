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
kubectl create -f kubernetes/ingress.yaml
```

And obtain the created Ingress Rule with:

```bash
kubectl get ing kube-demo -n demo
```
