# VHS Rental shop - Blast from the past

We are creating a cutting edge VHS rental application management system for our special clients that value the old time retro VHS experience. 

![img_1.png](backtothepast.png)

### VHS Rental Application

## Prerequisites
1. Have Docker installed and running
2. In the root directory create and fill out `.env` and `.env.development` files. For example:
```env
POSTGRES_DB=
POSTGRES_USER=
POSTGRES_PASSWORD=
DB_HOST=
DB_PORT=
API_PORT=
```
**Note**: 
Make sure `DB_HOST` is set to `postgres` in the `.env` file, and `localhost` in the `.env.development` file.

## Seeded data
The database should be seeded with some data already.

### Vhs

| id | name                                               | description                                                                                                           |
|----|----------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------|
| 1  | The Lord of the Rings - The Fellowship od the Ring | Frodo, a hobbit, embarks on a quest to destroy a powerful ring, joined by a diverse fellowship facing many dangers.   |
| 2  | The Lord of the Rings - The Two Towers             | Frodo and Sam journey to Mordor while Aragorn, Legolas, and Gimli aid Rohan against Saruman's forces in epic battles. |
| 3  | The Lord of the Rings - The Return of the King     | Frodo and Sam reach Mount Doom, while Aragorn leads the final battle against Sauron's forces to save Middle-earth.    |

### Users

| id | email             | roles       |
|----|-------------------|-------------|
| 1  | user1@example.com | USER        |
| 2  | user2@example.com | USER        |
| 3  | user3@example.com | USER, ADMIN |

### Rental

| id | start_date  | end_date    | returned_at | user_id | vhs_id |
|----|-------------|-------------|-------------|---------|--------|
| 1  | NOW         | NOW + 7 DAY | null        | 1       | 3      |
| 2  | NOW - 4 DAY | NOW - 1 DAY | NOW - 1 DAY | 1       | 2      |
| 3  | NOW - 8 DAY | NOW - 5 DAY | NOW - 6 DAY | 1       | 1      |
| 4  | NOW + 8 DAY | NOW + 9 DAY | null        | 2       | 3      |
| 5  | NOW + 8 DAY | NOW + 9 DAY | null        | 2       | 2      |
| 6  | NOW + 8 DAY | NOW + 9 DAY | null        | 2       | 1      |

### ADMIN login
Some endpoints require the ADMIN role to activate. 
In order to obtain login as an admin, please use the following credentials from the seeded database:
```
email: user3@example.com
password: truenorth1950
```

## Running the app
### Production
```shell
docker-compose up
```

### Development
This will generate the public and private key for JWT operations, so make sure you also have `openssl` installed. 
It should be available by default on Mac.

**Running from the shell**
```shell
sh run_dev.sh
```

**Running from the IDE**
Before running from the IDE, please execute the following command in your terminal:
```shell
sh generate_keys.sh
```
Also, before running, add 
```shell
--spring.profiles.active=development
```
as a run argument.

## Sending requests
### Endpoints
The docs can be found at the `/api/docs` path, and be accessible from your browser.

### Postman
In the root folder you can find the [Postman collection](VHS-LAB.postman_collection.json). 
Import it into Postman and start sending requests. The token should be prepopulated for endpoints that require it, 
just make sure to sign up or login first.