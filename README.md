# Url Shortner

### tech-stack
```bash
- frontend
    - react 18
- backend
    - java
- db
    - mysql
```

#### Create Short Urls:
![image](https://github.com/user-attachments/assets/ac98a850-08c8-4328-94b8-8764465a2fbd)

#### Get Long Urls from short url code:
![image](https://github.com/user-attachments/assets/06db5eaa-c089-451f-9c78-6af1dc548520)

### run locally
```bash
- install mysql
    - DB schema will get created automatically after Java spring boot project run
- backend
    - run => java spring boot project
- frontend
    - install node
    - run => npm install
    - run => vite OR npm run dev
```

### Why Short URL is 7 Characters long?
```bash
I've taken the reference for 7 Characters long from Bitly & Tiny URL
For a 7-character alphanumeric short URL, consisting of uppercase letters (A-Z), lowercase letters (a-z), and digits (0-9),
there are 62 possible characters (26 lowercase + 26 uppercase + 10 digits).

The total number of possible combinations for a 7-character URL would be:
62^7 = 3,521,614,606,208

So, there are 3.52 trillion possible combinations.
``` 
