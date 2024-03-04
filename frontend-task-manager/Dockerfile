# FROM node:20-alpine3.16 as dev-deps
# WORKDIR /app
# COPY package.json package-lock.json ./
# RUN npm install

# FROM node:20-alpine3.16 as builder
# WORKDIR /app
# COPY --from=dev-deps /app/node_modules ./node_modules
# COPY . .
# RUN npm run build

# FROM nginx:1.23.3
# EXPOSE 80
# COPY --from=builder /app/dist/task-manager /usr/share/nginx/html
# RUN rm etc/nginx/conf.d/default.conf
# COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf
# CMD ["nginx", "-g", "daemon off;"]

### STAGE 1: Build ###
FROM node:20-alpine3.16 AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.18
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /usr/src/app/dist/task-manager /usr/share/nginx/html
