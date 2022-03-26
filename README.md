# Library project

This project is an interface created with _Spring boot_ and _JSP_ to manage a library

## Running the project

To run this project you need to have `java` installed (tested with java 17).  
You also need `docker-compose` for the database.  
And you need `make` to be able to run all the following commands (from the root directory of the project)  

You need to start the database before starting the server

### Database

You need to have _docker-compose_ (and _docker_) installed to start the database

The environment contains a _postgresql_ database and _pgadmin_.

- Run `make dc` (or `make database_create`) to create the environment (initialises the database and pgadmin). It may take a little time.
- Run `make dd` (or `make database_down`) to shutdown (without destroying) the database and pgadmin.
- Run `make du` (or `make database_up`) to start the database (if created)

To access pgadmin:  
Url: http://localhost:15432  
Identifiant: `admin@pgadmin.com`  
Password: `password`  
Database password (already saved in pgadmin): `password`

### Server

- Run `make su` (or `make server_up`) to serve the the server
