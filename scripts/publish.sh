#!/bin/sh

mvn package
aws lambda update-function-code --function-name l-train-java --zip-file fileb://target/alexa-l-train-java-1.0-SNAPSHOT.jar --profile iam-admin --region us-east-1

