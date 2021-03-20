# Derived from official mysql image (our base image)
FROM mysql

# Add a database
#ENV MYSQL_DATABASE test
#ENV MYSQL_ROOT_HOST localhost
#ENV MYSQL_ROOT_PASSWORD test
#ENV MYSQL_USER user
#ENV MYSQL_PASSWORD: pwd

# Add the content of the sql-scripts/ directory to your image
# All scripts in docker-entrypoint-initdb.d/ are automatically
# executed during container startup
COPY ./sql-scripts/DumpSQL.sql /docker-entrypoint-initdb.d/
