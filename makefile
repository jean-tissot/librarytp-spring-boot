h help default:
	@echo "Use : make [action]" \
	&& echo "Actions:" \
	&& echo " → database_create (alias dc): (re)creates and starts the database" \
	&& echo " → database_up (alias du): starts the database (without creating it again)" \
	&& echo " → database_down (alias dd): stops the database without destroying it" \
	&& echo " → server_up (alias su): starts the server"


server_up su:
	@echo "Serving library server on http://localhost:8080..." \
		&& cd server && ./gradlew bootRun

database_create dc:
	@echo "Creating and starting the database... You can access to it via pgadmin at http://localhost:15432 (id: admin@pgadmin.com, password: password)" \
		&& cd database && docker-compose down \
		&& docker-compose up -d \
		&& docker exec -it -u root tp-library-spring-pgadmin cp /tmp/pgpass.conf /pgadmin4 \
		&& docker exec -it -u root tp-library-spring-pgadmin chown pgadmin:pgadmin /pgadmin4/pgpass.conf \
		&& docker exec -it -u root tp-library-spring-pgadmin chmod 600 /pgadmin4/pgpass.conf

database_up du:
	@echo "Starting the database... You can access to pgadmin at http://localhost:15432 (id: admin@pgadmin.com, password: password)" \
		&& cd database && docker-compose start

database_down dd:
	@echo "Stopping the database..." \
		&& cd database && docker-compose stop