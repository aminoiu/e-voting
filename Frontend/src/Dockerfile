FROM nginx:1.15
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY nginx_backend.conf /etc/nginx/conf.d/backend.conf
COPY dist/Frontend/ /usr/share/nginx/html