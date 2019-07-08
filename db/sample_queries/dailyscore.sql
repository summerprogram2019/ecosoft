-- Provides a breakdown of user scores on a daily basis
-- Score is calculated by: amount of activity * value of activity
SELECT u.uid AS 'User', u.date AS 'Day', SUM(u.amount * a.points) AS 'Total Score' 
FROM user_activities u, activities a
WHERE u.activity = a.id
GROUP BY u.uid, u.date;