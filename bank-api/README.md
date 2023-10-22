# Bank-API

### What has been done

- [Domain](src/main/java/com/itnov/bankapi/domain) object that will represent our business entities
- [REST controllers](src/main/java/com/itnov/bankapi/rest)
  - AccountController to handle
    - Listing of all account for a specific client
    - Make operation (withdraw, deposit, transfer) from an account
  - OperationController to get all the operations for a specific client
  - DTO when necessary to encapsulate domain object with request specific fields
- [Services](src/main/java/com/itnov/bankapi/service) to implement the business logic
- [Repository](src/main/java/com/itnov/bankapi/repository) to handle the communication with database entitie
- Some testing has been done on service

### How to improve

- Add missing unit tests + integration tests
- Do a real separation between domain object and api dto
- Add validation on Request/Responses objects
- Add ExceptionHandler to better return handle BadRequest body response
- Refactor the service code by adding some classes responsible of mapping between dtos and domain object