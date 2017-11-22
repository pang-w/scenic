scp -P {} web-b3.war root@120.25.193.26:/usr/local/iukiss/docker/web.war

ssh root@120.25.193.26 -p

cd /usr/local/iukiss/docker/
docker build . --tag=iukissb3
docker rm -f iukiss2
docker run -d -p 8083:8080 --name=iukissb3 iukissb3

vi /etc/nginx/nginx.conf 
nginx -s reload