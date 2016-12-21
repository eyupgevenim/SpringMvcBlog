-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 21 Ara 2016, 19:43:43
-- Sunucu sürümü: 5.6.24
-- PHP Sürümü: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Veritabanı: `blogdb`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `blogs`
--

CREATE TABLE IF NOT EXISTS `blogs` (
  `Id` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `BlogName` varchar(50) NOT NULL,
  `BlogUrl` varchar(30) NOT NULL,
  `Theme` varchar(255) NOT NULL DEFAULT 'Default',
  `CategoryId` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `blogs`
--

INSERT INTO `blogs` (`Id`, `UserId`, `BlogName`, `BlogUrl`, `Theme`, `CategoryId`) VALUES
(2, 3, 'Java Günlügü', 'javaGunlugu', 'Default', 1),
(3, 3, 'Eyüp Geveim', 'eyupgevenim', 'Default', 3),
(8, 8, 'Alpay Hoca', 'alpayhoca', 'Default', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `blogsmenus`
--

CREATE TABLE IF NOT EXISTS `blogsmenus` (
  `Id` int(11) NOT NULL,
  `BlogId` int(11) NOT NULL,
  `MenuId` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `blogsmenus`
--

INSERT INTO `blogsmenus` (`Id`, `BlogId`, `MenuId`) VALUES
(15, 3, 1),
(25, 8, 1),
(27, 1, 1),
(42, 1, 2),
(48, 3, 3),
(49, 2, 1),
(50, 2, 3),
(51, 9, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `Id` int(11) NOT NULL,
  `CategoryName` varchar(50) NOT NULL,
  `Statement` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `categories`
--

INSERT INTO `categories` (`Id`, `CategoryName`, `Statement`) VALUES
(1, 'Bilim', 'Bilimsel içerikler yer alıyor...'),
(2, 'Sanat', 'Sanatsal içerikler yer alıyor...'),
(3, 'Spor', 'Spor içerikleri yer alıyor...'),
(4, 'Sağlık', 'Sağlık konuları içeriyor...');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `Id` int(11) NOT NULL,
  `PostId` int(11) NOT NULL,
  `CommentatorId` int(11) NOT NULL,
  `Comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `State` int(11) NOT NULL DEFAULT '0',
  `DateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `comments`
--

INSERT INTO `comments` (`Id`, `PostId`, `CommentatorId`, `Comment`, `State`, `DateTime`) VALUES
(1, 18, 1, 'bu yazı için bir yorumdur....', 0, '2016-12-21 14:17:56'),
(2, 18, 3, 'Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in ', 0, '2016-12-21 14:33:41'),
(3, 28, 4, 'styles for key aspects of the framework. With Bootstrap 3, we''ve rewritten the project to be mobile friendly from the start. Instead of adding on optional mobile styles, they''re baked right into the core. In fact, Bootstrap is mobile first. Mobile', 0, '2016-12-21 20:46:21');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `complaints`
--

CREATE TABLE IF NOT EXISTS `complaints` (
  `Id` int(11) NOT NULL,
  `PostId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `VisitorIp` varchar(15) NOT NULL,
  `DateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin5;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `likes`
--

CREATE TABLE IF NOT EXISTS `likes` (
  `Id` int(11) NOT NULL,
  `PostId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `VisitorIp` varchar(15) NOT NULL,
  `DateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin5;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `menus`
--

CREATE TABLE IF NOT EXISTS `menus` (
  `Id` int(11) NOT NULL,
  `MenuName` varchar(30) NOT NULL,
  `State` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `menus`
--

INSERT INTO `menus` (`Id`, `MenuName`, `State`) VALUES
(1, 'Anasayfa', 1),
(2, 'Hakkımızda', 2),
(3, 'İletişim', 2);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `posts`
--

CREATE TABLE IF NOT EXISTS `posts` (
  `Id` int(11) NOT NULL,
  `PostTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `PostContent` text CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `RequestMapping` varchar(255) NOT NULL,
  `BlogMenuId` int(11) NOT NULL,
  `DateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `posts`
--

INSERT INTO `posts` (`Id`, `PostTitle`, `PostContent`, `RequestMapping`, `BlogMenuId`, `DateTime`) VALUES
(16, 'Asp Dot Net', '<ol><li>Asp Dot Net</li><li>Asp Dot Net</li><li>Asp Dot Net</li><li>Asp Dot Net</li><li>Asp Dot Net </li></ol>', 'Asp-Dot-Net', 15, '2016-12-15 10:44:47'),
(17, 'Çevik Yazilim', '<ol><li>Çevik Yazilim</li><li>Çevik Yazilim</li><li>Çevik Yazilim</li><li>Çevik Yazilim</li><li>Çevik Yazilim </li></ol>', 'Cevik-Yazilim', 15, '2016-12-15 10:48:04'),
(18, 'Man must explore, and this is exploration at its greatest', '<div><br></div><div>Never in all their history have men been able truly to conceive of the world as one: a single sphere, a globe, having the qualities of a globe, a round earth in which all the directions eventually meet, in which there is no center because every point, or none, is center  an equal earth which all men occupy as equals. The airman''s earth, if free men make it, will be truly round: a globe in practice, not in theory.</div><div><br></div><div>Science cuts two ways, of course; its products can be used for both good and evil. But there''s no turning back from science. The early warnings about technological dangers also come from science.</div><div><br></div><div>What was most significant about the lunar voyage was not that man set foot on the Moon but that they set eye on the earth.</div><div><br></div><div>A Chinese tale tells of some men sent to harm a young girl who, upon seeing her beauty, become her protectors rather than her violators. That''s how I felt seeing the Earth for the first time. I could not help but love and cherish her.</div><div><br></div><div>For those who have seen the Earth from space, and for the hundreds and perhaps thousands more who will, the experience most certainly changes your perspective. The things that we share in our world are far more valuable than those which divide us.</div><div><br></div><div>The Final Frontier</div><div>There can be no thought of finishing for aiming for the stars. Both figuratively and literally, it is a task to occupy the generations. And no matter how much progress one makes, there is always the thrill of just beginning.</div><div><br></div><div>There can be no thought of finishing for aiming for the stars. Both figuratively and literally, it is a task to occupy the generations. And no matter how much progress one makes, there is always the thrill of just beginning.</div><div><br></div><div>The dreams of yesterday are the hopes of today and the reality of tomorrow. Science has not yet mastered prophecy. We predict too much for the next year and yet far too little for the next ten.</div><div>Spaceflights cannot be stopped. This is not the work of any one man or even a group of men. It is a historical process which mankind is carrying out in accordance with the natural laws of human development.</div><div><br></div><div>Reaching for the Stars</div><div>As we got further and further away, it [the Earth] diminished in size. Finally it shrank to the size of a marble, the most beautiful you can imagine. That beautiful, warm, living object looked so fragile, so delicate, that if you touched it with a finger it would crumble and fall apart. Seeing this has to change a man.</div><div><br></div><div>&nbsp;</div><div>To go places and do things that have never been done before  thats what living is all about.</div><div>Space, the final frontier. These are the voyages of the Starship Enterprise. Its five-year mission: to explore strange new worlds, to seek out new life and new civilizations, to boldly go where no man has gone before.</div><div><br></div><div>As I stand out here in the wonders of the unknown at Hadley, I sort of realize theres a fundamental truth to our nature, Man must explore, and this is exploration at its greatest.</div><div><br></div><div>Placeholder text by Space Ipsum. Photographs by NASA on The Commons.</div> ', 'Man-must-explore-and-this-is-exploration-at-its-greatest', 15, '2016-12-15 11:02:19'),
(22, 'Deneme Demo Baslik', '<ol><li>Deneme Demo Baslik</li><li>Deneme Demo Baslik</li><li>Deneme Demo Baslik</li><li>Deneme Demo Baslik</li><li>Deneme Demo Baslik</li><li>Deneme Demo Baslik </li></ol>', 'Deneme-Demo-Baslik', 25, '2016-12-20 09:43:42'),
(27, ' İletişim', 'Burası iletişim bilgileri yer alıyor...', 'Iletisim', 48, '2016-12-21 00:08:26'),
(28, 'grup list demo', 'Mobile first\r\nWith Bootstrap 2, we added optional mobile friendly styles for key aspects of the framework. With Bootstrap 3, we''ve rewritten the project to be mobile friendly from the start. Instead of adding on optional mobile styles, they''re baked right into the core. In fact, Bootstrap is mobile first. Mobile first styles can be found throughout the entire library instead of in separate files.\r\n\r\nMobile first\r\nWith Bootstrap 2, we added optional mobile friendly styles for key aspects of the framework. With Bootstrap 3, we''ve rewritten the project to be mobile friendly from the start. Instead of adding on optional mobile styles, they''re baked right into the core. In fact, Bootstrap is mobile first. Mobile first styles can be found throughout the entire library instead of in separate files.\r\nMobile first\r\nWith Bootstrap 2, we added optional mobile friendly styles for key aspects of the framework. With Bootstrap 3, we''ve rewritten the project to be mobile friendly from the start. Instead of adding on optional mobile styles, they''re baked right into the core. In fact, Bootstrap is mobile first. Mobile first styles can be found throughout the entire library instead of in separate files.\r\n\r\nTo ensure proper rendering and touch zooming, add the viewport meta tag to your\r\n\r\n<pre style="padding: 0px; margin-bottom: 0px; line-height: 1.42857; word-break: normal; background-color: transparent; border-width: 0px; border-style: initial; border-color: initial; white-space: nowrap;"><code class="language-html" data-lang="html" style="padding-right: 45px; color: rgb(51, 51, 51); display: inline-block;"><span class="nt" style="color: rgb(47, 111, 159);">&lt;ul</span> <span class="na" style="color: rgb(79, 159, 207);">class=</span><span class="s" style="color: rgb(212, 73, 80);">"list-group"</span><span class="nt" style="color: rgb(47, 111, 159);">&gt;</span>\r\n  <span class="nt" style="color: rgb(47, 111, 159);">&lt;li</span> <span class="na" style="color: rgb(79, 159, 207);">class=</span><span class="s" style="color: rgb(212, 73, 80);">"list-group-item list-group-item-success"</span><span class="nt" style="color: rgb(47, 111, 159);">&gt;</span>Dapibus ac facilisis in<span class="nt" style="color: rgb(47, 111, 159);">&lt;/li&gt;</span>\r\n  <span class="nt" style="color: rgb(47, 111, 159);">&lt;li</span> <span class="na" style="color: rgb(79, 159, 207);">class=</span><span class="s" style="color: rgb(212, 73, 80);">"list-group-item list-group-item-info"</span><span class="nt" style="color: rgb(47, 111, 159);">&gt;</span>Cras sit amet nibh libero<span class="nt" style="color: rgb(47, 111, 159);">&lt;/li&gt;</span>\r\n  <span class="nt" style="color: rgb(47, 111, 159);">&lt;li</span> <span class="na" style="color: rgb(79, 159, 207);">class=</span><span class="s" style="color: rgb(212, 73, 80);">"list-group-item list-group-item-warning"</span><span class="nt" style="color: rgb(47, 111, 159);">&gt;</span>Porta ac consectetur ac<span class="nt" style="color: rgb(47, 111, 159);">&lt;/li&gt;</span>\r\n  <span class="nt" style="color: rgb(47, 111, 159);">&lt;li</span> <span class="na" style="color: rgb(79, 159, 207);">class=</span><span class="s" style="color: rgb(212, 73, 80);">"list-group-item list-group-item-danger"</span><span class="nt" style="color: rgb(47, 111, 159);">&gt;</span>Vestibulum at eros<span class="nt" style="color: rgb(47, 111, 159);">&lt;/li&gt;</span>\r\n<span class="nt" style="color: rgb(47, 111, 159);">&lt;/ul&gt;</span>\r\n<span class="nt" style="color: rgb(47, 111, 159);">&lt;div</span> <span class="na" style="color: rgb(79, 159, 207);">class=</span><span class="s" style="color: rgb(212, 73, 80);">"list-group"</span><span class="nt" style="color: rgb(47, 111, 159);">&gt;</span>\r\n  <span class="nt" style="color: rgb(47, 111, 159);">&lt;a</span> <span class="na" style="color: rgb(79, 159, 207);">href=</span><span class="s" style="color: rgb(212, 73, 80);">"#"</span> <span class="na" style="color: rgb(79, 159, 207);">class=</span><span class="s" style="color: rgb(212, 73, 80);">"list-group-item list-group-item-success"</span><span class="nt" style="color: rgb(47, 111, 159);">&gt;</span>Dapibus ac facilisis in<span class="nt" style="color: rgb(47, 111, 159);">&lt;/a&gt;</span>\r\n  <span class="nt" style="color: rgb(47, 111, 159);">&lt;a</span> <span class="na" style="color: rgb(79, 159, 207);">href=</span><span class="s" style="color: rgb(212, 73, 80);">"#"</span> <span class="na" style="color: rgb(79, 159, 207);">class=</span><span class="s" style="color: rgb(212, 73, 80);">"list-group-item list-group-item-info"</span><span class="nt" style="color: rgb(47, 111, 159);">&gt;</span>Cras sit amet nibh libero<span class="nt" style="color: rgb(47, 111, 159);">&lt;/a&gt;</span>\r\n  <span class="nt" style="color: rgb(47, 111, 159);">&lt;a</span> <span class="na" style="color: rgb(79, 159, 207);">href=</span><span class="s" style="color: rgb(212, 73, 80);">"#"</span> <span class="na" style="color: rgb(79, 159, 207);">class=</span><span class="s" style="color: rgb(212, 73, 80);">"list-group-item list-group-item-warning"</span><span class="nt" style="color: rgb(47, 111, 159);">&gt;</span>Porta ac consectetur ac<span class="nt" style="color: rgb(47, 111, 159);">&lt;/a&gt;</span>\r\n  <span class="nt" style="color: rgb(47, 111, 159);">&lt;a</span> <span class="na" style="color: rgb(79, 159, 207);">href=</span><span class="s" style="color: rgb(212, 73, 80);">"#"</span> <span class="na" style="color: rgb(79, 159, 207);">class=</span><span class="s" style="color: rgb(212, 73, 80);">"list-group-item list-group-item-danger"</span><span class="nt" style="color: rgb(47, 111, 159);">&gt;</span>Vestibulum at eros<span class="nt" style="color: rgb(47, 111, 159);">&lt;/a&gt;</span>\r\n<span class="nt" style="color: rgb(47, 111, 159);">&lt;/div&gt;</span></code></pre> ', 'grup-list-demo', 15, '2016-12-21 11:37:44'),
(37, 'Test Title', 'this test post content', 'Test-Title', 15, '2016-12-21 21:32:20');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `Id` int(11) NOT NULL,
  `RoleName` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `roles`
--

INSERT INTO `roles` (`Id`, `RoleName`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `Id` int(11) NOT NULL,
  `UserName` varchar(30) NOT NULL,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `RoleId` int(11) NOT NULL DEFAULT '2',
  `Enabled` varchar(20) NOT NULL DEFAULT '1',
  `DateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `users`
--

INSERT INTO `users` (`Id`, `UserName`, `FirstName`, `LastName`, `Email`, `Password`, `RoleId`, `Enabled`, `DateTime`) VALUES
(1, 'eyupgevenim', 'Eyup', 'Gevenim', 'eyupgevenim@gmail.com', '202cb962ac59075b964b07152d234b70', 1, '1', '2016-11-20 16:42:36'),
(3, 'demoUser', 'Eyüp', 'Gevenim', 'e@e.com', '202cb962ac59075b964b07152d234b70', 2, '1', '2016-11-20 16:42:36'),
(8, 'alpay', 'Alpay', 'Doruk', 'al@e.com', '202cb962ac59075b964b07152d234b70', 2, '1', '2016-12-08 14:13:30');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `visitors`
--

CREATE TABLE IF NOT EXISTS `visitors` (
  `Id` int(11) NOT NULL,
  `VisitorName` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `visitors`
--

INSERT INTO `visitors` (`Id`, `VisitorName`, `Email`) VALUES
(1, 'Ahmet Deneme', 'co@co.com'),
(2, 'Donec Lacinia ', 'cr@cr.com'),
(3, 'Donec Lacinia', 'cx@cx.com'),
(4, 'Hasan Demo', 'y@yorum.com');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `blogs`
--
ALTER TABLE `blogs`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `blogsmenus`
--
ALTER TABLE `blogsmenus`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `complaints`
--
ALTER TABLE `complaints`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `menus`
--
ALTER TABLE `menus`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Id`), ADD KEY `Id` (`Id`);

--
-- Tablo için indeksler `visitors`
--
ALTER TABLE `visitors`
  ADD PRIMARY KEY (`Id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `blogs`
--
ALTER TABLE `blogs`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- Tablo için AUTO_INCREMENT değeri `blogsmenus`
--
ALTER TABLE `blogsmenus`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=71;
--
-- Tablo için AUTO_INCREMENT değeri `categories`
--
ALTER TABLE `categories`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- Tablo için AUTO_INCREMENT değeri `comments`
--
ALTER TABLE `comments`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- Tablo için AUTO_INCREMENT değeri `complaints`
--
ALTER TABLE `complaints`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Tablo için AUTO_INCREMENT değeri `likes`
--
ALTER TABLE `likes`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Tablo için AUTO_INCREMENT değeri `menus`
--
ALTER TABLE `menus`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- Tablo için AUTO_INCREMENT değeri `posts`
--
ALTER TABLE `posts`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=40;
--
-- Tablo için AUTO_INCREMENT değeri `roles`
--
ALTER TABLE `roles`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Tablo için AUTO_INCREMENT değeri `users`
--
ALTER TABLE `users`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- Tablo için AUTO_INCREMENT değeri `visitors`
--
ALTER TABLE `visitors`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
