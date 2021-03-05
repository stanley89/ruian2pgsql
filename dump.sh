sudo docker-compose exec -u postgres db pg_dump --no-owner --no-acl -U ruian -t rn_vusc ruian>vusc.sql
sudo docker-compose exec -u postgres db pg_dump --no-owner --no-acl -U ruian -t rn_okres ruian>okres.sql
sudo docker-compose exec -u postgres db pg_dump --no-owner --no-acl -U ruian -t rn_obec ruian>obec.sql
sudo docker-compose exec -u postgres db pg_dump --no-owner --no-acl -U ruian -t rn_momc ruian>momc.sql
sudo docker-compose exec -u postgres db pg_dump --no-owner --no-acl -U ruian -t rn_ulice ruian>ulice.sql
sudo docker-compose exec -u postgres db pg_dump --no-owner --no-acl -U ruian -t rn_vo ruian>vo.sql
