#Cricket Scorer API

To build locally run  
```./gradlew clean build```

To run unit test locally  
```./gradlew clean test```

To start application:

```./gradlew bootRun```




## Follow the below steps to deploy it

1. Fork the repository
2. Add your team members as collaborators to the forked repo.
3. You will need to make changes to the pipeline.gocd.yml to rename everywhere team1 is to team2, etc. depending on which team you are on.
    1. Change all places where team1 appears to your team name.
    2. Change the git url to the forked git hub url.
    3. Change the deploy job script - ```sh buildAndDeploy/deploy/deploy.sh team1 sethu-origin-key-pair``` to ```buildAndDeploy/deploy/deploy.sh <your team name> <your ssh key name>```. You need to create an ssh key pair under AWS EC2 in the aws console. You need to do this to log on to the EC2 box in case you need to debug something. Create and save it locally. You can use the same SSH key created for the gocd box here as well.
4. Then follow the instruction on the https://github.com/Sethuraman/bootcamp-gocdinfra to complete the deployment
5. You can test your API deployment by running the below command
```curl <your deployed ip address>/game``` If this print out json then your deployment has worked. Be aware, that the curl endpoint might take a while before it works. Track the API deployment via the cloudformation console. 
5. Get the IP address of the EC2 box created to host your API and update it in the build stage of the UI project build.  





