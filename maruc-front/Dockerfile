# Dockerfile
#FROM node:latest
#LABEL authors="Sebastián Carabali"

#COPY package.json .


# Agregamos un usuario
#RUN useradd --user-group --create-home --shell /bin/false app

#ENV HOME=/usr/src/app
#RUN mkdir $HOME
#WORKDIR $HOME

# Instalamos angular cli en nuestra imágen
#RUN npm install --legacy-peer-deps
#RUN npm install --save-dev @angular-devkit/build-angular --force

# Puerto entorno dev
#EXPOSE 4200
# Puerto livereload
#EXPOSE 49153
# Puerto Test
#EXPOSE 9876
# Puerto e2e
#EXPOSE 49152
# Puerto Digital
#EXPOSE 80

#RUN ["npm", "start"]

FROM node:16-alpine as build-stage

RUN mkdir -p /app

WORKDIR /app

COPY package.json /app

RUN npm install --legacy-peer-deps

COPY . /app

RUN npm run build --prod

#SEGUNDA ETAPA
FROM nginx:latest

COPY --from=build-stage /app/dist/maruc-front /usr/share/nginx/html
 
