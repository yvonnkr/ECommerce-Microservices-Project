# ECommerce App - Microservices Architecture REST API's

---

Business Requirements
===========================

As a business owner in the e-commerce sector, I am currently operating without the aid of any digital solutions.
My product line consists of a variety of items, each identified by a unique code and accompanied by a detailed description.

Customers interact with my business by placing orders from this list of products.
I identify my customers using their first name, last name, email and address.
Each customer transaction involves a specific payment method.
Upon the completion of a successful payment transaction, I take the responsibility to inform the customer via an email, confirming
the success of their payment or conversely, notifying them of any payment failures.

In a bid to streamline operations and promote growth within my business, I am looking to invest in the development of a dedicated application.
This application will serve to simplify my business processes and contribute significantly to the overall efficiency and scalability of my online venture.

## Features
### Micro-Services
    . Config Server
    . Discovery Service
    . Gateway Service
    . Notification Service
    . Customer Service
    . Product Service
    . Order Service
    . Payment Service
#### Database
    . MongoDB (NoSql)
    . Postgress (SQL)
### Security
    . Keycloak
#### Misc
    . Docker Containers
    . OpenFeign
    . Kafka
    . RabbitMQ
    . Zipkin
    . Maildev

# Implementation Overview

---
### Microservices Communication via HTTP
- RestTemplates
- OpenFeign

### Service Discovery with Eureka
- Register microservices
- Externalize ms configuration

###  Routing with API Gateway
- Services Routing
- Externalize configuration

### Messaging - with RabbitMQ 
- Asynchronous communication using RabbitMQ

### Notification
- MailDev 
- Notification service
- Java-mail
- RabbitMQ consumer
- Consume order-queue and send email
- Save to the database
- consume payment-queue and send email

### Tracing & Observability 
- Distributed Tracing and observabilty with Sleuth & Zipkin

### Security - KeyCloak
- Authentication & Authorisation using KeyCloak oath2 resource server
- Set up keycloak on Api Gateway Level, to authenticate every request passing through the gateway
### Distributed documentation
- Expose services documentation through API Gateway