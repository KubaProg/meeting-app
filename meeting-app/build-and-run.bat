call mvn clean install -DskipTests
call docker compose up --build --abort-on-container-exit --remove-orphans
call docker system prune