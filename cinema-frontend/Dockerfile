FROM node:20

WORKDIR /opt/app

COPY package.json .
RUN npm i

COPY . .

RUN npm run build

CMD ["npm", "run", "start"]
