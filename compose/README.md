# How to start

Step 1: Run database-compose.yml first to initialize the DB for all services

Step 2: Run service-compose.yml to initialize all services

# Definition
### Database compose:
- Database Server Containerization
- Each DB container contains healthcheck and backup data in a volumn folder _**/db-data/{service-name}**_
- Including:
  - **account-db:** postgresql
  - **cards-db:** postgresql
  - **loans-db:** postgresql

### Service compose:
- Service containerization
- Including:
  - **rabbitmq**
  - **config-central**
  - **accounts**
  - **cards**
  - **loans**
