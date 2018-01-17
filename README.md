# presto-uuid
Plugin for https://prestodb.io/ that adds UUID support

## How to setup a test presto-server using docker

### postgresql
docker run --name pg -v `pwd`/postgresql:/var/lib/postgresql -e POSTGRES_PASSWORD=postgres -e PGDATA=/var/lib/postgresql/data/pgdata -d postgres:9.6.6
docker run -it --rm --link pg:pg1 postgres:9.6.6 psql -Upostgres -hpg1 -c "create database test"
docker run -it --rm --link pg:pg1 postgres:9.6.6 psql -Upostgres -hpg1 -dtest -c "create table books(id uuid PRIMARY KEY not null, title varchar(100), author varchar(100), publisher varchar(100), publish_time date)"

### presto-server

#### etc/catalog/pg4test.properties
connector.name=postgresql
connection-url=jdbc:postgresql://pg1:5432/test
connection-user=postgres
connection-password=postgres

#### etc/config.properties
coordinator=true
node-scheduler.include-coordinator=true
http-server.http.port=8080
query.max-memory=10GB
query.max-memory-per-node=2GB
discovery-server.enabled=true
discovery.uri=http://localhost:8080

#### etc/log.properties
com.facebook.presto=DEBUG

#### etc/node.properties
node.environment=test
node.id=ffffffff-ffff-ffff-ffff-ffffffffffff
node.data-dir=/var/presto/data

#### jvm.config
-server
-Xmx4G
-XX:+UseG1GC
-XX:G1HeapRegionSize=32M
-XX:+UseGCOverheadLimit
-XX:+ExplicitGCInvokesConcurrent
-XX:+HeapDumpOnOutOfMemoryError
-XX:+ExitOnOutOfMemoryError

docker run -it --link pg:pg1 -v `pwd`/presto-server-0.189:/usr/local/bin/presto-server -v `pwd`/presto:/var/presto/data java:openjdk-8u111 
cd /usr/local/bin/presto-server/
bin/launcher start
./presto-cli-0.189-executable.jar --execute "show catalogs"
./presto-cli-0.189-executable.jar --catalog pg4test --execute "show schemas"
./presto-cli-0.189-executable.jar --catalog pg4test --schema public --execute "show tables"
./presto-cli-0.189-executable.jar --catalog pg4test --schema public --execute "show columns from books"
./presto-cli-0.189-executable.jar --execute "select uuid_type1()"

