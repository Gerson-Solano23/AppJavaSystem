@echo off
echo "Starting API of Spring Boot..."
cd %~dp0..\AppJavaSystem\backend\myAgenda
start "" mvnw spring-boot:run

echo "Waiting..."
timeout /t 10

echo "Starting App Web..."
cd %~dp0..\AppJavaSystem\frontend\AppContactList
start "" ng serve
