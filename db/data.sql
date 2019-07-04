INSERT INTO `user` (`uid`, `username`, `password`) VALUES
	(1, 'rafraser', 'klagjaihsakg'),
	(2, 'guthers', 'yeuwtaxa'),
	(3, 'sunshine', 'nfalfiaasx'),
	(4, 'cooldude42', 'htiafalm'),
	(5, 'harry2', 'bncngkahghcv'),
	(6, 'voyager2', 'oriaaron'),
	(7, 'lolcatz', 'kangjakh');

INSERT INTO `activities` (`id`, `name`, `points`) VALUES
	(1, 'act_step', 1),
	(2, 'act_planttree', 250),
	(3, 'act_nostraw', 50),
	(4, 'act_bus', 150);

INSERT INTO `articles` (`id`, `title`, `description`, `url`, `date`) VALUES
	(1, 'Bees & Why They\'re Important', 'Bees are some of the most important creatures in the world.', 'bees.com', '2019-07-04'),
	(2, 'Polar Bears: Extinction', 'The icecaps are melting and the polar bears are in danger', 'polarbears.com', '2019-07-04'),
	(3, '10 Facts About Trees', '#7 will surprise you!', 'buzzfeed.com', '2019-07-03'),
	(4, 'Which type of flower are you?', 'Take this exciting quiz to find out what type of flower you are!', 'buzzfeed.com', '2019-07-02'),
	(5, 'Stop Burning Paper!', 'Paper fumes are one of the atmosphere\'s biggest killers!', 'trees.com', '2019-06-04');

INSERT INTO `flowers` (`id`, `name`, `price`, `instore`) VALUES
	(1, 'Sunflower', 250, 'T'),
	(2, 'Dragonbreeze', 600, 'F'),
	(3, 'Rose', 300, 'T'),
	(4, 'Tulip', 300, 'T'),
	(5, 'Marigold', 250, 'T');

INSERT INTO `friends` (`user1`, `user2`) VALUES
	(2, 1),
	(5, 1),
	(6, 1),
	(1, 2),
	(1, 3),
	(4, 3),
	(3, 4),
	(1, 6);

INSERT INTO `garden` (`uid`, `flower`, `planted`) VALUES
	(1, 1, '2019-07-04 08:55:59'),
	(1, 1, '2019-07-04 09:24:49'),
	(1, 1, '2019-07-04 09:43:44'),
	(2, 1, '2019-07-04 09:25:03'),
	(2, 2, '2019-07-03 07:21:20'),
	(3, 4, '2019-07-02 07:44:09'),
	(4, 5, '2019-06-02 08:46:22');

INSERT INTO `goals` (`uid`, `description`, `completion`) VALUES
	(1, 'Adopted a walrus', '2019-07-07'),
	(1, 'Walked 1000 miles', '2019-07-04'),
	(2, 'Saved the world', '2019-07-03'),
	(2, 'Used reusable straws for a month', '2019-06-05'),
	(2, 'Walked 1000 miles', '2019-07-02'),
	(3, 'Used reusable straws for a month', '2019-07-01'),
	(4, 'Used reusable straws for a month', '2019-07-02'),
	(5, 'Adopted a polar bear', '2019-07-03');

INSERT INTO `liked_articles` (`uid`, `article`) VALUES
	(4, 1),
	(1, 2),
	(4, 2),
	(1, 4),
	(2, 4),
	(2, 5),
	(3, 5),
	(4, 5);

INSERT INTO `settings` (`uid`, `setting`, `state`) VALUES
	(1, 'cloud_sync', 'T'),
	(1, 'private_profile', 'T'),
	(2, 'cloud_sync', 'T'),
	(2, 'private_profile', 'F'),
	(3, 'private_profile', 'F'),
	(4, 'private_profile', 'F');

INSERT INTO `user_activities` (`uid`, `activity`, `amount`, `date`) VALUES
	(1, 1, 7000, '2019-05-12'),
	(1, 1, 4000, '2019-07-03'),
	(1, 1, 5000, '2019-07-04'),
	(1, 2, 20, '2019-05-12'),
	(1, 3, 5, '2019-07-03'),
	(2, 1, 20000, '2019-07-01'),
	(2, 1, 10000, '2019-07-02'),
	(3, 1, 4000, '2019-07-04'),
	(3, 3, 2, '2019-07-03'),
	(3, 3, 2, '2019-07-04'),
	(3, 4, 40, '2019-07-03'),
	(3, 4, 50, '2019-07-04'),
	(4, 2, 10, '2019-07-01'),
	(4, 2, 10, '2019-07-02'),
	(4, 2, 20, '2019-07-03');