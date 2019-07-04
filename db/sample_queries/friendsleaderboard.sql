SELECT u.uid AS 'User', SUM(u.amount * a.points) AS 'Total Score' 
FROM user_activities u, activities a
WHERE u.activity = a.id
AND (u.uid IN (
	SELECT user2 FROM friends WHERE user1 = 1
) OR u.uid = 1)
GROUP BY u.uid
ORDER BY 2 DESC;