create database url_shortener_db;

use url_shortener_db;

select * from url_shortener;

SELECT EXISTS (SELECT 1 FROM url_shortener WHERE short_url = @shortUrl);

SELECT id,count,date_created,long_url,short_url FROM url_shortener WHERE short_url = @shortUrl;

UPDATE url_shortener SET count = @count WHERE id = @id;