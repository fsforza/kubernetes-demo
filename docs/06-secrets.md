# Secrets

## Upload MySQL Password as Secret

With Kubernetes [Secrets](https://kubernetes.io/docs/concepts/configuration/secret/), you can hide the MySQL password.

Upload the MySQL password with:

```bash
kubectl create secret generic kube-demo --from-literal=mysql-password=changeme1 -n demo
```

You can decode the secret with:

```bash
kubectl get secret kube-demo -o jsonpath='{.data.mysql-password}' -n demo | base64 -d
```

## Remove clear password value from ConfigMap

You need to remove the password clear value from the ConfiMap.
You can do this editing the ConfigMap with:

```bash
kubectl edit cm kube-demo -n demo
```

## Inject Secret as Environment Variable

To use the Secret, you can inject it as Environment Variable removing the comment from lines into **kubernetes/deployment.yaml** file:

```yaml
- name: SPRING_DATASOURCE_PASSWORD
  valueFrom:
    secretKeyRef:
      name: kube-demo
      key: mysql-password
```

And redeploy the microservice with:

```bash
kubectl apply -f kubernetes/deployment.yaml
```
