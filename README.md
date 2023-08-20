# Country Information

The RESTful APIs allow user to retrieve following country information:

- Sorted list of countries by population density in descending order
- Country in Asia containing the most bordering countries of a different region

## Tech stack 

- **Spring Boot**

  The API is built by Springboot. RESTful API can provide uniform interface and objects in REST are always manipulated from the URI, which is easy for different endpoints to consume.

- **FeignClient**
    
  Feign is a declarative web service client. It makes writing web service clients easier.
- **Swagger UI**

  Swagger UI is a tool that allows you to visualize and interact with your RESTful APIs. API descriptions, example requests/responses can also be found in the Swagger UI

## Quickstart

1. Clone the codebase to your local machine.

   ```bash
   git clone https://github.com/yuchen9459/country-info.git
   ```
2. Run the application by Intellij, please make sure the port 8080 is available.

3. Access to the Swagger UI via following URL:

   ```bash
   http://localhost:8080/swagger-ui/index.html
   ```
4. You can simply retrieve the desired results via sending request to the two API endpoints via Swagger UI. Detailed schema is also provided in the Swagger UI.

## Highlights

1. The second endpoint `/get-most-bordering-country` is generalized by having a argument `region` with `Asia` as default value. User can acquire Country in other regions containing the most bordering countries of a different region by simply changing the input argument.
2. The API communication with the source data is done via `FeignClient`, also the URL is configured as part of the `application.yml`. When there's anything changed in the source data URL, we can simply update the config file without changing the source code base.
3. Swagger UI provides user an easy way to acquire the results without installing POSTMAN or using terminal.
4. Test cases were added to ensure the functionality and maintainability of the code.
