-- Provides a breakdown of user scores over a total time period
-- Score is calculated by: amount of activity * value of activity
SELECT u.uid AS 'User', SUM(u.amount * a.points) AS 'Total Score' 
FROM user_activities u, activities a
WHERE u.activity = a.id
GROUP BY u.uid;