SELECT * FROM friends a
WHERE user2 NOT IN (
	SELECT user1 FROM FRIENDS b WHERE user2 = a.user1
)