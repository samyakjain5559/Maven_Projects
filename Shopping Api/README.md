# ShoppingApi
Guide to setup Shopping cart Api on local machine

To clone/run Spring boot application:
(1)->	Open powershell or command prompt 
(2)->	Navigate to project repo on local machine
(3)->	Run java -jar .\ProductDetails-1.1.0.jar
(4)-> To access swagger for this api navigate to: http://localhost:8083/swagger-ui.html#/shopping-api-controller

Note: It will also execute all unit test cases and setup memory DB


To clone/run Angular repo:
(1)->	git clone https://github.com/samyak5559/ShoppingCart.git
(2)->	Navigate to project repo on local machine
(3)->	run npm start
(4)-> Go to http://localhost:4200/ to check the app

Note: If npm start fails make sure below two angular dependencies are properly installed and setup in local:
(1) -> npm install -g @angular/cli@latest
(2) -> npm install --save-dev @angular-devkit/build-angular
