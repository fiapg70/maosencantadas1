node -e "console.log(require('crypto').randomBytes(32).toString('hex'))"
docker exec -it mysql_container bash
mysql -u root -p
SHOW DATABASES;
DROP DATABASE maosencantadas;
CREATE DATABASE maosencantadas;