############
### prod ###
############

# base image
FROM nginx:latest

# copy artifact build from the 'build environment'
COPY b73d841fa38e75f2d164d681417ad76e534476cb9c0694ca944654c64af70f3f.pem /usr/share/nginx/html
#COPY nginx.conf /etc/nginx/conf.d/default.conf

# expose port 80
EXPOSE 80

# run nginx
CMD ["nginx", "-g", "daemon off;"]