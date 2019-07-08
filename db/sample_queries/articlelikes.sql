-- Accumulates the total number of likes for each article
SELECT a.title AS 'Article Name', COUNT(l.uid) AS 'Number Of Likes' 
FROM liked_articles l, articles a
WHERE a.id = l.article
GROUP BY article;