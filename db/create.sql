CREATE DATABASE IF NOT EXISTS `ecosoft`;
USE `ecosoft`;

CREATE TABLE IF NOT EXISTS `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` tinytext NOT NULL,
  `password` tinytext NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `Username Unique` (`username`(16))
) COMMENT='Stores the basic user identification settings such as username and password. These are primarily used for login.';

CREATE TABLE IF NOT EXISTS `activities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` tinytext NOT NULL,
  `points` smallint(6) NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT='Keeps track of the various activities that a user can perform to earn points';

CREATE TABLE IF NOT EXISTS `articles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL,
  `description` text,
  `url` text NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT='Stores information about the articles that may appear in user feeds.';

CREATE TABLE IF NOT EXISTS `flowers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` tinytext NOT NULL,
  `price` int(11) NOT NULL,
  `instore` enum('T','F') NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT='Keeps track of the different flower types';

CREATE TABLE IF NOT EXISTS `friends` (
  `user1` int(11) NOT NULL,
  `user2` int(11) NOT NULL,
  PRIMARY KEY (`user1`,`user2`),
  KEY `FK_friends_user_2` (`user2`),
  CONSTRAINT `FK_friends_user` FOREIGN KEY (`user1`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_friends_user_2` FOREIGN KEY (`user2`) REFERENCES `user` (`uid`)
) COMMENT='If you wanna get with my lover; ya gotta get with my friends!';

CREATE TABLE IF NOT EXISTS `garden` (
  `uid` int(11) NOT NULL,
  `flower` int(11) NOT NULL,
  `planted` datetime NOT NULL,
  PRIMARY KEY (`uid`,`flower`,`planted`),
  KEY `FK_garden_flowers` (`flower`),
  CONSTRAINT `FK_garden_flowers` FOREIGN KEY (`flower`) REFERENCES `flowers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_garden_user` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='Keeps track of all the flowers that users have in their gardens';

CREATE TABLE IF NOT EXISTS `goals` (
  `uid` int(11) NOT NULL,
  `description` text NOT NULL,
  `completion` date NOT NULL,
  PRIMARY KEY (`uid`,`description`(16),`completion`)
) COMMENT='Goals are long-term, less frequent occurences than activites';

CREATE TABLE IF NOT EXISTS `liked_articles` (
  `uid` int(11) NOT NULL,
  `article` int(11) NOT NULL,
  PRIMARY KEY (`uid`,`article`),
  KEY `FK_liked_articles_articles` (`article`),
  CONSTRAINT `FK_liked_articles_articles` FOREIGN KEY (`article`) REFERENCES `articles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_liked_articles_user` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='Keeps tracks of users liking articles that appear in their feed';

CREATE TABLE IF NOT EXISTS `settings` (
  `uid` int(11) NOT NULL,
  `setting` tinytext NOT NULL,
  `state` enum('T','F') NOT NULL,
  PRIMARY KEY (`uid`,`setting`(16)),
  CONSTRAINT `FK__user` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='Store user privacy settings';

CREATE TABLE IF NOT EXISTS `user_activities` (
  `uid` int(11) NOT NULL,
  `activity` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`uid`,`activity`,`date`),
  KEY `FK__activity` (`activity`),
  CONSTRAINT `FK__activity` FOREIGN KEY (`activity`) REFERENCES `activities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK__user__activity` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='Keeps track of activities that a user performs';
