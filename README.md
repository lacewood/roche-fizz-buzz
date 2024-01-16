# roche-fizz-buzz
Implement a robust and production-ready Fizz-Buzz REST Server that follows the classic Fizz-Buzz logic

16/01/2024 
1. exposed get endpoint to process fizz-buzz request
2. exposed static metrics get end-point to get most used api params and hit count
3. configured swagger-ui
4. configured spring actuator end point
5. used executor approach to handled big request(limit param)
5. handled exception in globally
6. implement interceptor approach for metrics statistics
7. written junits
8. created docker file to deploy in cloud(render.com)

i,e
1.0.RELEASE version is deployed in cloud 
swagger link: https://roche-fizz-buzz.onrender.com/swagger-ui/index.html
get endpoint link:https://roche-fizz-buzz.onrender.com/fizzbuzz/v1/compute?int1=3&int2=5&limit=20&str1=fizz&str2=buzz
metrics endpoint link: https://roche-fizz-buzz.onrender.com/fizzbuzz/v1/metrics
