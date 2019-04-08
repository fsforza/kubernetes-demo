# Deploy The Microservice

## Create Namespace

First of all you have to create the **demo** [namespace](https://kubernetes.io/docs/concepts/overview/working-with-objects/namespaces/):

```bash
kubectl create -f kubernetes/namespace.yaml
```

## Deploy The microservice Docker Image

Then, you can deploy the Docker Image with:

```bash
kubectl create -f kubernetes/deployment.yaml
```

You can get the [deployment](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/) with:

```bash
kubectl get deploy -l app=kube-demo -n demo
```

The [replicaset](https://kubernetes.io/docs/concepts/workloads/controllers/replicaset/):

```bash
kubectl get deploy -l app=kube-demo -n demo
```

And the [pod](https://kubernetes.io/docs/concepts/workloads/pods/pod/):

```bash
kubectl get pod -l app=kube-demo -n demo
```

You can see the pod log with:

```bash
POD=$(kubectl get pods -l app=kube-demo -n demo -o jsonpath='{.items[0].metadata.name}')
kubectl logs -f $POD -n demo
```

## Access Pod from outside the cluster

You can access to the microservice using [port-forward](https://kubernetes.io/docs/tasks/access-application-cluster/port-forward-access-application-cluster/):

```bash
POD=$(kubectl get pods -l app=kube-demo -n demo -o jsonpath='{.items[0].metadata.name}')
kubectl port-forward $POD 8080:8080 -n demo
```

## Invoke Microservice Health Endpoint

After that, you can invoke the health endpoint with

```bash
curl http://localhost:8080/actuator/health
```
