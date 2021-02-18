docker-compose exec -u postgres db pg_dump --no-owner --no-acl -U ruian -t rn_vusc -t rn_okres -t rn_obec -t rn_momc -t rn_ulice -t rn_vo ruian> ruian.sql
