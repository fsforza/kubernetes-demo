# Deploy The Microservice

## Deploy MySQL

Then, you can deploy MySQL Database with:

```bash
kubectl create -f kubernetes/mysql-deployment.yaml
```

You can get the [deployment](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/) with:

```bash
kubectl get deploy -l app=mysql -n demo
```

The [replicaset](https://kubernetes.io/docs/concepts/workloads/controllers/replicaset/):

```bash
kubectl get deploy -l app=mysql -n demo
```

And the [pod](https://kubernetes.io/docs/concepts/workloads/pods/pod/):

```bash
kubectl get pod -l app=mysql -n demo
```

## Persistent Volume

The **mysql-deployment.yaml** file creates also a Kubernetes [Persistent Volume Claim](https://kubernetes.io/docs/concepts/storage/persistent-volumes/)

You can get the **pvc** with:

```bash
kubectl get pvc -l app=mysql -n demo
```

And the related **pv**:

```bash
kubectl get pv -l app=mysql -n demo
```
