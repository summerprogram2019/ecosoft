-- Friendships in the database should be stored two-way
-- This finds any friendships that are only stored in one direction
SELECT * FROM friends a
WHERE user2 NOT IN (
	SELECT user1 FROM FRIENDS b WHERE user2 = a.user1
)