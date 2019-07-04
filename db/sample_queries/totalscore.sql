SELECT u.uid AS 'User', SUM(u.amount * a.points) AS 'Total Score' 
FROM user_activities u, activities a
WHERE u.activity = a.id
GROUP BY u.uid;