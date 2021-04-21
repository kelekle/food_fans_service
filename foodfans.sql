-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2021-04-21 17:41:50
-- 服务器版本： 8.0.17
-- PHP 版本： 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `foodfans`
--

DELIMITER $$
--
-- 存储过程
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `redeem` (`uid` INT, `gid` INT)  BEGIN
	DECLARE ach VARCHAR(30);
    DECLARE gp1 decimal;
	DECLARE gp2 decimal;
	DECLARE gp3 decimal;
    DECLARE up decimal;
    select achievement into ach from userinfo where userId = uid;
	select 0.8*price into gp1 from goods where goods.id = gid;
	select 0.9*price into gp2 from goods where goods.id = gid;
	select 0.95*price into gp3 from goods where goods.id = gid;
    select socre into up from userinfo where userId = uid;

    IF ((ach like '%大师%') and (up > gp1)) then 
			update userinfo set userinfo.socre = userinfo.socre -  gp1 where userId=uid;
			update goods set goods.number = goods.number - 1 where goods.id=gid;
	ELSEIF ((ach like '%达人%') and (up > gp2)) then 
			update userinfo set userinfo.socre = userinfo.socre -  gp2 where userId=uid;
			update goods set goods.number = goods.number - 1 where goods.id=gid;
	ELSEIF((ach like '%小能手%') and (up > gp3)) then 
			update userinfo set userinfo.socre = userinfo.socre -  gp3 where userId=uid;
			update goods set goods.number = goods.number - 1 where goods.id=gid;
	ELSEIF(up > gp3) then 
			update userinfo set userinfo.socre = userinfo.socre -  gp1*1.25 where userId=uid;
			update goods set goods.number = goods.number - 1 where goods.id=gid;
	end if;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `achievemenkindtinfo`
--

CREATE TABLE `achievemenkindtinfo` (
  `typeid` int(10) NOT NULL,
  `meaning` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `privileges` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `achievemenkindtinfo`
--

INSERT INTO `achievemenkindtinfo` (`typeid`, `meaning`, `privileges`) VALUES
(1, '川菜达人', '0.9,100'),
(2, '川菜小能手', '0.95,50'),
(3, '川菜大师', '0.8,500'),
(4, '做饭小能手', '0.95,50'),
(5, '做饭达人', '0.9,100'),
(6, '做饭大师', '0.8,500'),
(7, '卤菜小能手', '0.95,50'),
(8, '卤菜达人', '0.9,100'),
(9, '卤菜大师', '0.8,500'),
(10, '汤达人', '0.9,50');

-- --------------------------------------------------------

--
-- 表的结构 `articleinfo`
--

CREATE TABLE `articleinfo` (
  `articleId` int(20) NOT NULL,
  `userId` int(20) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `urls` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `praiseNum` int(10) DEFAULT NULL,
  `readNum` int(10) DEFAULT NULL,
  `commentNum` int(10) DEFAULT NULL,
  `avgscore` float(5,2) DEFAULT NULL,
  `creatStamp` bigint(13) DEFAULT NULL,
  `isTop` int(1) DEFAULT NULL,
  `visitNum` int(10) DEFAULT NULL,
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `articleinfo`
--

INSERT INTO `articleinfo` (`articleId`, `userId`, `title`, `urls`, `content`, `praiseNum`, `readNum`, `commentNum`, `avgscore`, `creatStamp`, `isTop`, `visitNum`, `tag`) VALUES
(10001, 1, '皮皮虾炒鸡蛋', 'article/10001/1.png', '这个时节渔民出海有些困难，天气不好出海也很危险，鲜活的皮皮虾不好买了，只能买了些冷冻的皮皮虾肉，当然了，跟鲜活的肯定不能比了，口感和味道都略逊一筹。但是用来炒菜做汤也不错，能提鲜，下面分享个皮皮虾肉炒鸡蛋，快手方便，也很美味哦。\r\n1.将皮皮虾肉解冻清洗干净。\r\n2.磕入两个生鸡蛋。\r\n3.加入少量的盐和鸡精，并搅拌均匀。\r\n4.锅中倒入适量的食用油烧热，倒入皮皮虾肉蛋液。\r\n5.大火翻炒一会儿。\r\n6.断生后关火盛出，撒一点葱花。', 2, 56, 4, 3.84, 1, 1, 2, '川菜/蛋'),
(10002, 1, '糖醋带鱼', 'article/10002/2.jpg', '糖醋带鱼，肉质鲜美，酸甜可口，下酒下饭都不错，是大多数人都喜欢吃的家常菜，我家也不例外，隔三岔五就烧上一盘。\r\n今天这道糖醋带鱼采用家常做法，烹饪方法比较简单，实用，一碗料汁就解决了所有调味问题，而且肉质鲜嫩，酸甜适口，没有一点腥味。喜欢的朋友不妨试一试，不会让你失望。\r\n1.带鱼处理干净，（带鱼腹中的黑色薄膜，腥味重，腥味的主要来源，一定要刮干净）葱切段，姜切片，大蒜用刀拍一下，准备白糖、醋、番茄酱、料酒、生抽带鱼处理干净，（带鱼腹中的黑色薄膜，腥味重，腥味的主要来源，一定要刮干净）葱切段，姜切片，大蒜用刀拍一下，准备白糖、醋、番茄酱、料酒、生抽\r\n2.先来调一碗料汁，白糖20克、醋30克、番茄酱1大茶匙、料酒3茶匙、生抽3茶匙、食盐等所有调料一起放入大碗中，加150克清水搅拌均匀备用。\r\n3.处理干净的带鱼双面薄薄沾上一层面粉（带鱼粘点薄粉煎制，可以防粘，保持带鱼表皮完整。烧制的过程糊化，汤汁粘稠，也可起到勾芡的作用。\r\n4.沾好面粉的带鱼放入煎锅煎制。（煎鱼的时候先不要急着翻动它，估计一面煎的差不多了，晃一下锅，鱼能离锅滑动的时候，再翻面\r\n5.煎至两面金黄捞出备用\r\n6.锅中放少许底油，放入葱姜、大蒜、1粒八角稍稍爆香。\r\n7.爆香葱姜八角后，放入带鱼，加入调好的料汁，料汁稍稍莫过带鱼。\r\n8.大火烧沸，转中小火烧制。\r\n9.大约10分钟左右，带鱼充分入味，大火收汁，把汤汁收浓即好。（留一些汤汁拌饭也是极好的）', 1, 33, 1, 1.00, 1, 1, 2, '鲁菜/鱼'),
(10003, 3, '花甲', 'article/10003/3.png', '花甲价廉物美，营养丰富，很受大家的欢迎。今天这道菜把花甲。用来拌一拌非常的简单好吃哦！\r\n1.锅里放油。放入大葱，洋葱，白蔻，香叶。\r\n2.开中火炸出香味。\r\n3.开大火把香味炸出来。捞出里面的材料只留油\r\n4.你的葱油放入碗里放入辣椒酱。趁热搅拌均匀。	\r\n5.放入生抽盐味精，花椒面。白芝麻，陈醋搅拌均匀。\r\n6.锅里放水水里放入料酒，大葱姜片。水开放入花甲绰水。\r\n7.把花甲煮的微微开壳捞出备用。\r\n8.花甲放凉备用。\r\n9.最后淋上葱姜蒜末，把这个花甲泡在这个油里面。搅拌均匀就可以吃了非常的美味。', 2, 51, 3, 2.78, 1, 1, 2, '川菜/海鲜'),
(10004, 2, '酱牛肉', 'article/10004/4.png', '这道酱牛肉用了黄豆酱来煮，因此有着淡淡的酱香味，如果想要酱香更浓郁，可以加大黄酱的用量，而且煮好的牛肉要泡在汤中几个小时，这样能将汤中的味道更好地吸入到肉中。牛肉的部位随自己的喜好来挑选吧。也不一定非得是牛腱子。我家喜欢吃筋头巴脑的，所以会连牛腱子、牛腩一块买来，分成大块，入凉水锅中同煮。待水开后，撇去表面的浮沫就可以下调料慢慢焖煮了。另外，黄酱中有一些黄豆的残渣，最好用清水将酱澥一下，滤去豆渣，这样在小火焖煮的时候，才不会让豆渣巴在锅底\r\n牛肉小火慢炖，以筷子能插透为好；黄酱澥过之后，一定要过滤掉酱渣子，要不然容易糊锅； 调料和蘸料可以随口味来调整。 肉汤不要倒掉，过滤掉调料残渣后，放在碗中或者保鲜袋中定型，放冰箱冷冻保存，下次可以继续使用，这就是所谓的老汤，越煮越香。\r\n1.牛肉切大块，清洗干净\r\n2.入凉水锅中大火煮开后，撇去浮沫\r\n3.皮、八角、砂仁、姜、白蔻准备好，也可以根据口味加入其它调料	\r\n4.调料均匀分布在肉锅中，盖上锅盖，小火慢炖1小时\r\n5.黄酱舀入碗中\r\n6.加适量的清水澥开，待用\r\n7.牛肉煮了一小时了，可以用筛网将黄酱汤过滤到锅中\r\n8.再倒入适量的红烧酱油，喜欢颜色深的，可以多倒点，喜颜色浅的，可以少倒点\r\n9.再撒入少许的盐，量随自己的口味来定，个人建议可以多撒点，炖肉要味重点才好吃\r\n10.继续盖上锅盖，小火焖炖，直到用筷子能轻松穿过肉块，就可以出锅了。牛肉可以泡在汤中泡两个小时或者更长一点儿，能吸收一部分汤汁中的味道\r\n11.凉后分袋保存，入冰箱冷藏后食用\r\n12.取适量牛肉切薄片，不要顺着肉丝切，要不然易塞牙\r\n13.葱末、姜末、青蒜末准备好	\r\n14.入碗中，再倒入生抽、香醋，调好\r\n15.可以直接食用\r\n16.口重的，可以蘸汁食用', 2, 11, 2, 2.50, 1, 1, 1, '粤菜'),
(10005, 2, '炖牛肉', 'article/10005/5.png', '牛肉是中国人的第二大肉类食品，仅次于猪肉。牛肉蛋白质含量高，而脂肪含量低，所以味道鲜美，受人喜爱，享有“肉中骄子”的美称。中医讲，牛肉有补脾胃、益气血、强筋骨、消水肿等功效。今天就做这锅最简单的炖牛肉。这次的量比较大，一次吃不完，可以炖好后放大碗中，分次做成不同的牛肉美食。比如这第一顿的炖牛肉，还可以做成咖啡牛肉、土豆牛肉、牛肉面、牛肉粉条、牛肉杂蔬煲、肉夹馍……总之，炖好这一锅，可以变着花样来吃啦！\r\n牛肉切块入凉水锅中，可以将内部的血水逼出来； 如果焯水再另起一锅炖肉，味道上会打折扣；但如果很介意汤品的清澈程度，可以焯水再另起锅炖； 调料和香料可以根据口味进行增减、调整； 炖的时间还要根据肉的软烂程度及自己的口感来定，此处不做时间上的建议。\r\n1.牛肉洗净\r\n2.切成2、3厘米的大块\r\n3.入凉水锅，煮开后撇去浮沫\r\n4.将香料准备好，种类和数量可以调整噢\r\n5.待浮沫撇得差不多时，加入香料，盖盖，小火慢慢焖炖\r\n6.待汤去掉一半时，可加入适量盐\r\n7.倒入红烧酱油，并翻拌均匀\r\n8.待汤汁收得差不多时，可夹起一块肉尝尝咸淡和成熟度，便可出锅', 1, 21, 2, 3.19, 1, 1, 1, '苏菜'),
(10006, 2, '红烧牛肉', 'article/10006/6.png', '红烧牛肉块是我们家常备的既食菜，每次都烧好一大锅再分装成二、三份放入冰箱冷冻，需要吃的时候再提前取出解冻后配上土豆、胡萝卜或许香菇等疏菜，炖好后不论配米饭或馒头好吃营养又方便，这次又炖了一大锅，盛出一部分入冰箱冷藏或冷冻保存，另一部分配上土豆就是好吃的“土豆烧牛肉”\r\n1.牛肉洗净切成二厘米的块状，凉水起锅放入牛肉块，倒入一大勺料酒和二片姜，水烧开后再煮三分钟；\r\n2.捞出肉块沥干水分放入盆中；\r\n3.起炒锅倒入适量的植物油放入姜片、桂皮、草菓和八角爆香，放入牛肉块翻炒出香味；\r\n4.倒入生抽、老抽和一小勺细砂糖翻炒至变色\r\n5.倒入开水没过肉块，放入装有花椒和小茴香的调料盒，倒入花雕酒，蚝油和一滳醋，盖上锅盖小火慢炖；\r\n6.趁着炖牛肉时土豆洗净切块，再用清水过洗两遍捞出沥干水分，起锅倒油放入土豆块煎至表面金黄色；\r\n7.捞出备用；\r\n8.牛肉块炖至汤汁过半并肉熟时，加入煎好的土豆块和少许盐；\r\n继续炖至汤汁浓稠关火盛出', 2, 110, 3, 2.78, 1, 1, 1, '浙菜'),
(10007, 3, '牙签牛肉', 'article/10007/7.png', '早就想做一次牙签肉，终于找到了点时间。汗…… 冰箱里正好有牛肉，腌了一晚上，非常入味\r\n1.将牛肉洗净，用肉锤打松，使其筋膜断裂，这样做好的牛肉口感会更好。\r\n2.将牛肉切成1cm大小的正方形块。\r\n3.蒜切碎，姜切成丝。\r\n4.将牛肉和姜蒜放入碗中，然后倒入适量料酒、老抽、生抽、香油，加入盐、黑胡椒粉、生粉、白糖。\r\n5.用手抓匀，抓起牛肉摔打几下\r\n6.将牛肉装入保鲜袋中，冷藏腌制1晚上。\r\n7.牙签用开水烫一下。\r\n8.一根牙签上串3-4块牛肉。\r\n9.平底锅中加入适量油，烧至7分热\r\n10.下入牛肉串煎制。\r\n一面焦黄后翻面，直到煎熟即可', 1, 10, 1, 1.00, 1, 1, 1, '浙菜'),
(10008, 2, '麻辣虾爬子', 'article/10008/8.png', '虾爬子是威海人对皮皮虾的称呼。虾爬子是一种营养丰富、汁鲜肉嫩的海味食品。其肉质含水分较多，肉味鲜甜嫩滑，淡而柔软，并且有一种特殊诱人的鲜味。每年的春季是其产卵的季节，此时食用为最佳，肥壮的虾爬子脑部满是膏脂，肉质十分鲜嫩，味美可口。\r\n虾爬子味道鲜美，价格年年攀升，是我们海边小城人们最喜爱的水产品之一，今年的价格感觉比往年都要高，每斤现在要达到40—50元左右。 爬虾的习性和虾、蟹差不多，每年5月产卵，所以4月份正是它肥壮鲜美的时候。过了四、五月份，下一个吃爬虾的季节就要到9月以后了。此时，它的肉质最为饱满。一般来讲，母的皮皮虾的个头没有公的那么大，区分公母的办法是：公的皮皮虾在颈部最下端大爪下有一对小爪，母的则没有，可用通俗语“公的长胡子”来区分。\r\n虾爬子的吃法，无外乎蒸、煮、炒、炸等方法，在我们当地最普遍、最好的做法就是直接清蒸，这样可以最大的保留虾的原始鲜美味道。我这次用川菜的做法将虾爬子的鲜味和辣味相结合，由于虾爬子肉质鲜嫩特别容易入味，吃起来口感鲜香麻辣，相当的过瘾，回味无穷。\r\n1.主料：虾爬子2斤\r\n2.副料：青、红椒、香菜、大蒜、姜调料：花椒、大料、干红辣椒、香辣火锅底料\r\n3.虾爬子用水冲洗干净，青、红椒切辣椒圈，香菜切段，大蒜、生姜切片备用、锅中做油，油温6成热时将各种佐料全部放入锅中炒制，佐料炒香后将火锅底料放入锅中小火炒制\r\n4.将虾爬子放入锅中大火炒制，中间要不停翻动\r\n5.加入少许生抽、糖、料酒、胡椒粉炒制\r\n6.倒入小半碗清水，让炒制的虾爬子在红油水中咕咚3-4分钟，确保虾爬子成熟\r\n7.待汤汁浓稠时，撒入香菜梗即可出锅', 1, 8, 1, 1.00, 1, 1, 1, '川菜'),
(10009, 2, '螃蟹粥', 'article/10009/9.png', '去海口出差喝了一次当地的螃蟹粥,觉得超级好喝.回来没过多长时间就馋得不行了.因为我在北方没有好的地方可以喝到这种粥,所以我就琢磨着自己做.\r\n  我也没想到一次就成功了,味道还不错一大锅粥一次就干掉了~~~\r\n1.把螃蟹处理干净切大块。\r\n2.在炒锅里用葱姜爆香\r\n3.然后放进汤锅里加入开水煮沸，加入大米同煮\r\n4.在大米完全爆开花之后，把虾洗净放入锅中\r\n5.粥完全熬好之后，加入一些青菜，撒上葱花(如果你不喜欢完全可以不用加入青菜)，加盐。\r\n6.吃的时候撒一些胡椒进取，味道超级好。', 1, 5, 1, 3.12, 1, 1, 1, '闽菜'),
(10010, 2, '脆皮炸鲜奶', 'article/10010/10.png', '诸位亲们，您要是在春节家宴中亲自主厨，可千万别忘了家里的小朋友呦！也要想办法为他（她）们做道可口的小点，您要是稍动心思，定会为孩子们带来欢乐，家里才会更热闹，爷爷奶奶也会高兴的，呵呵！\r\n在这里，我推荐一款传统的小点“脆皮炸鲜奶”，通过我在外面就餐时的观察，这道小点最受小朋友们的欢迎。吃起来奶香浓郁，外皮脆脆的，馅料QQ的甜甜的可好吃了，一般孩子们都喜欢。我发现，几乎带孩子们去餐馆吃饭的客人，都会为孩子要一道这款点心。\r\n其实“脆皮炸鲜奶”做起来很简单，您可提前做好馅料，到炒菜的时候合些面糊，一炸就好。这款小点经过我的稍加改良，味道绝对更棒了！传统的“脆皮炸鲜奶”的馅料，只是用牛奶或三花淡奶加糖和玉米淀粉熬制，牛奶粘稠后倒入盘子里晾凉，然后放入冰箱冷藏两小时，冷藏后切成条裹糊一炸就OK。我在馅料里加了少许的吉士粉，吃起来味道更浓郁，我朋友家的小孩都喜欢，今天特介绍给大家。其主要做法如下\r\n1.馅料；鲜牛奶、干玉米淀粉、吉士粉、白糖、盐。\r\n面糊；面粉、玉米淀粉、泡打粉、盐、清水、色拉油。\r\n炸制；烹调油适量。\r\n2.往鲜牛奶里倒入糖、少许盐、玉米淀粉、吉士粉搅匀，搅到无干粉颗粒奶液均匀即可。\r\n3.然后倒入奶锅中用小火熬煮，如果使用电磁炉熬煮最好。\r\n4.把奶液熬煮至稠糊状熟透即可，然后倒入铁盘中晾凉，晾凉后蒙上保鲜膜放入冰箱冷藏3-4小时备用。\r\n5.把奶液熬煮至稠糊状熟透即可，然后倒入铁盘中晾凉，晾凉后蒙上保鲜膜放入冰箱冷藏3-4小时备用。\r\n6.合好面糊后倒入色拉油搅匀，搅到使油和面糊充分融合即可。\r\n7.把定好型的奶块从冰箱里取出，用刀切成2厘米见方5厘米长的条。\r\n8.把奶条放入面糊盆中裹匀面糊，油温烧至六七成热时下入裹好面糊的奶条进行炸制。\r\n9.炸到皮脆微黄便可捞出，然后码盘上桌便可食用。', 1, 1, 1, 1.00, 1, 1, 1, '苏菜'),
(10011, 2, '萝卜炖腊猪蹄肉', 'article/10011/11.jpg', '在精灵的老家，每年腊月初的时候，都有家家户户杀猪，腌腊肉，灌腊肠的习俗，就算是自已没有养猪，也要去杀猪的亲朋好友家或者街上的肉档买一些肉回家的，反正无论是家底殷实还是家底贫寒的，多多少少都会有一些腊货，挂在堂屋（客厅）之上，正月里亲朋好友走访的时候，都会忍不住相互夸奖一翻，例如：哇，你家的腊货还真是多呢，估计吃一年都没有问题之类等等，听到类似的话语，那主人家心里那一个美滋滋的。。。就算是现在各家条件都富裕了，一样有年年腌腊货的老习惯。。。\r\n过年返程的时候，后备箱也是塞满了各式各样的腊货，爸妈对于这一些腊货，有着浓浓的情意，毕竟吃了好几十年了，故土难离，乡音难改嘛；时不时的炒一些家乡味，也算是一解乡愁。周末的时间，取一些肥瘦相当的腊猪蹄肉，加一根清甜可口的沙窝萝卜炖一锅，咸香美味有嚼劲，既是下酒菜又是下饭菜，汤汁用来泡饭吃，也是极好味道的。隔三差五的炖上这么一锅子，全家老少吃的皆大欢喜，每每是连最后一点汤汁都不会剩下的。（腊制品味道虽好，但不宜天天吃，且吃的时候，最好配搭一起新鲜的食材，避免过度食用）\r\n1.腊猪蹄肉用冷水浸泡1小时，中间换水2~3回。\r\n2.洗净后沥水备用。\r\n3.大葱切段，生姜切片，枸杞备用。\r\n4.腊猪蹄肉下锅焯水。\r\n5.砂锅中加入过滤水，下焯水的腊猪蹄肉。\r\n6.加入大葱，生姜，干辣椒。\r\n7.加料酒。\r\n8.大火烧开后转中小火炖50分钟\r\n9.沙窝萝卜去皮切块。\r\n10.50分钟后加入萝卜。\r\n11.再炖煮10分钟后加胡椒粉。\r\n12.加鸡粉调味。\r\n13.关火，出锅即可美美的享用啦。', 1, 2, 1, 1.00, 1, 1, 1, '苏菜/炖菜'),
(10012, 3, '香辣凤爪', 'article/10012/12.jpg', '一道让你黯然销魂的香辣鸡爪，烧好的鸡爪又香又辣，每一根鸡爪都包裹着浓郁的酱汁，红亮软糯，边吃边舔手指，简直太过瘾。\r\n一道让你黯然销魂的香辣鸡爪，烧好的鸡爪又香又辣，每一根鸡爪都包裹着浓郁的酱汁，红亮软糯，边吃边舔手指，简直太过瘾\r\n1.鸡爪去指甲，清理干净。\r\n2.鸡爪去指甲，清理干净。\r\n3.煮锅放水，放入姜片，鸡爪冷水下锅，烧沸后煮5分钟。（鸡爪异味较重，一定要焯透了才不会有异味）\r\n4.鸡爪焯水的时候准备调料：红烧酱油2茶匙、料酒30克、红油豆瓣酱1大茶匙、冰糖20克、干辣椒5个、八角2粒、香叶3片、桂皮1小段，葱姜适量\r\n5.锅中放少许食用油，放入冰糖小火炒，炒至冰糖全部融化.\r\n6.炒至焦糖色，冒密集小泡泡时.放入鸡爪翻炒上色.\r\n7.快速放入鸡爪翻炒上色\r\n8.加入豆瓣酱，葱姜、辣椒、八角、香叶、桂皮等调料小火翻炒几下\r\n9.加入酱油、料酒。\r\n10.加入水莫过鸡爪，大火烧沸，改小火烧制。\r\n11.烧制大约40分钟，鸡爪软烂，拣去调料不用，尝一尝咸淡，淡了放盐。\r\n12.然后开大火收汁，最后把汤汁收浓收亮即可。\r\n13.撒点葱绿点缀，香辣口味，软糯口感，太好吃了。', 1, 2, 1, 3.00, 1, 1, 1, '川菜'),
(10013, 3, '避风塘鸡翅', 'article/10013/13.jpg', '吃惯了红烧鸡翅、你有尝试吃过避风塘鸡翅吗？炸的酥香的面包糠混合了炒出浓郁蒜香味的蒜末，沾裹在炸的香脆的鸡翅上，咬上一口，满满的香浓鸡肉和蒜香味，一定让你停不下口。\r\n1.食材：鸡翅中8个、面包糠半碗、大蒜5瓣、葱末5克、姜丝5克、料酒1茶匙、孜然粉1/2茶匙、淀粉2茶匙、食盐适量\r\n2.鸡中翅洗净，用厨用纸巾吸干表面水份。用刀从中间两个骨头的骨缝处竖着切开。\r\n3.切好后的鸡翅加入盐、1茶匙料酒、1/2茶匙孜然粉、葱末、姜丝、蒜末（蒜末加三分之一）2茶匙玉米淀粉。\r\n4.加入所有调料后抓匀，至少腌渍1小时以上。\r\n5.腌渍鸡翅的时候处理面包糠，面包糠放在锅中不加油，小火慢慢干炒。\r\n6.炒至呈金黄色关火，加入食盐拌匀，制成“金沙”盛出备用。\r\n7.不粘锅烧热加入少许油，放入腌渍好的鸡翅煎制。（鸡皮的一面朝下，以便排除多余油脂，鸡皮的部分也更香脆）\r\n8.煎至金黄盛出备用。\r\n9.锅中放少许油，放入蒜末小火炒香。\r\n10.蒜末炒至微微发黄。\r\n11.放入煎好的鸡翅、炒好的面包糠。\r\n12.充分兜炒均匀即可关火盛出', 1, 8, 1, 1.00, 1, 1, 1, '苏菜'),
(10014, 2, '西葫芦炒马肉', 'article/10014/14.jpg', '昨天收到了来自远方新疆的快递，早已期待许久的马肉到了。这是一份来自远方的心意，曾经的同事，直至今日的朋友。与其说期待马肉，不如说是期待着一份正在路途中，即将踏入苏州土地的惦念，无比温暖。\r\n从未品尝过马肉的味道，也从没想过，我会在自家的餐桌上吃一回自己炒制的马肉。\r\n说说口感，朋友寄来的是熏制的马肉，脂肪很少，很有嚼劲，好像和熏猪肉的味道差别不大。我一直以为马肉是和牛羊一样，有一股自带的特殊的味道。也或许是熏制的味道盖住了马肉的腥味，反而是香喷喷的。\r\n马肉，市场上并不多见，甚至很多像我一样根本没见过。但是它含有丰富的蛋白质，维生素及钙，磷，铁，镁，锌，硒等矿物质，并且具有恢复肝脏机能以及防止贫血，促进血液循环，预防动脉硬化，增强免疫力的效果。对人体益处很多。\r\n今天先取一些肉，配上西葫芦和胡萝卜，炒一盘尝尝。剩下的再处理。哈哈！\r\n1.马肉清洗干净，切成薄片状。\r\n2.胡萝卜洗净，切成片状。\r\n3.西葫芦对半切开，切片。\r\n4.西葫芦对半切开，切片\r\n5.姜切丝，蒜叶切小段，蒜瓣切片。\r\n6.锅中倒入适量油，烧热，下入蒜瓣和姜丝，爆香。\r\n7.然后倒入马肉片，翻炒一下，倒入适量料酒。\r\n8.熏过的马肉差不多就是熟的了，翻炒一会儿就可以捞出，待用，翻炒时间不要长，影响口感。\r\n9.锅中留底油，下入胡萝卜片，翻炒一下。\r\n10.下入西葫芦，翻炒。西葫芦炒的时间不要长，会软烂，影响口感。\r\n11.然后下入炒制好的马肉，一起翻炒均匀。\r\n12.倒入适量生抽，调下味即可。因为熏过的马肉已经放过盐，不需要再放了。\r\n13.出锅时撒上大蒜叶子，翻炒均匀即可。', 1, 4, 1, 3.00, 1, 1, 1, '炒菜'),
(10015, 2, '肉末豆腐', 'article/10015/15.jpg', '豆腐是我比较喜欢的一种食材，营养好，还不含脂肪，素有“植物肉”的美称。豆腐的消化吸收率达95%以上。两小块豆腐，即可满足一个人一天钙的需要量。\r\n豆腐为补益清热养生食品，常食可补中益气、清热润燥、生津止渴、清洁肠胃。更适于热性体质、口臭口渴、肠胃不清、热病后调养者食用。非常适合夏天吃哦”\r\n1.各种食材准备好\r\n2.豆腐入锅，汆烫\r\n3.油锅烧热，爆香花椒、干红辣椒，捞出食材不用\r\n4.花椒油爆香蒜末，倒入黄豆酱炒出香味\r\n5.将肉泥倒进去，翻炒至微微变色\r\n6.肉泥变色的时候，淋入半碗水，将豆腐块倒进去\r\n7.烧至收汁，撒上葱花、调入盐调味即可出锅', 1, 1, 1, 1.00, 1, 1, 1, '炖菜'),
(10089, 1, '11111,麻辣黄鳝', 'article/1/ar001.jpg', '1111111111111,1.干贝加水浸泡，去除污物\r\n2.猪肉肥瘦三七开，剁碎，加盐，鸡精，生抽充分搅拌上劲，再加少于油继续搅拌，捏成小丸备用\r\n3.鳝鱼洗净切小段（买鳝鱼的时候让卖鱼的洗干净处理好，鳝鱼肉有滋补作用，切好可以不用再洗，不过血比较腥，怕腥的可以洗掉。）\r\n4.大米一杯稍微洗洗\r\n5.芹菜、小葱切末，生姜洗净切丝\r\n6.将大米放入锅里或者放养生壶，加水至1200ml，启动按键。煮10分钟后，放入猪肉末和干贝\r\n7.待煮到差不多快好的时候，放入鳝鱼和生姜丝，再煮5分钟\r\n8.最后加少许油\r\n9.芹菜，葱末，和盐，鸡精调味即可', 0, 1, 0, 0.00, 1577263351324, 0, 0, '川菜,川菜');

--
-- 触发器 `articleinfo`
--
DELIMITER $$
CREATE TRIGGER `achievemen_chuancai1` AFTER INSERT ON `articleinfo` FOR EACH ROW begin
set @var=0;
set @is_exist=0;
SELECT COUNT(userId) INTO @var FROM articleinfo
WHERE articleinfo.tag Like '%川菜%'  and articleinfo.userId=new.userId;
SELECT COUNT(userId) INTO @is_exist FROM userinfo
WHERE userinfo.achievement Like '%川菜小能手%'  and userinfo.userId=new.userId;
if @var>=3 AND @is_exist=0 then 
 UPDATE userinfo SET userinfo.achievement=concat(userinfo.achievement, '/川菜小能手')  where userinfo.userId=new.userId;
 UPDATE userinfo SET userinfo.socre=userinfo.socre+50  where userinfo.userId=new.userId;
end if; 


set @var=0;
set @is_exist=0;
SELECT COUNT(userId) INTO @var FROM articleinfo
WHERE articleinfo.tag Like '%川菜%'  and articleinfo.userId=new.userId;
SELECT COUNT(userId) INTO @is_exist FROM userinfo
WHERE userinfo.achievement Like '%川菜达人%'  and userinfo.userId=new.userId;
if @var>=10 AND @is_exist=0 then 
 UPDATE userinfo SET userinfo.achievement=concat(userinfo.achievement, '/川菜达人')  where userinfo.userId=new.userId;
 UPDATE userinfo SET userinfo.socre=userinfo.socre+100  where userinfo.userId=new.userId;
end if; 


set @var=0;
set @is_exist=0;
SELECT COUNT(userId) INTO @var FROM articleinfo
WHERE articleinfo.tag Like '%川菜%'  and articleinfo.userId=new.userId;
SELECT COUNT(userId) INTO @is_exist FROM userinfo
WHERE userinfo.achievement Like '%川菜大师%'  and userinfo.userId=new.userId;
if @var>=50 AND @is_exist=0 then 
 UPDATE userinfo SET userinfo.achievement=concat(userinfo.achievement, '/川菜大师')  where userinfo.userId=new.userId;
 UPDATE userinfo SET userinfo.socre=userinfo.socre+500  where userinfo.userId=new.userId;
end if; 


set @var=0;
set @is_exist=0;
SELECT COUNT(userId) INTO @var FROM articleinfo
WHERE  articleinfo.userId=new.userId;
SELECT COUNT(userId) INTO @is_exist FROM userinfo
WHERE userinfo.achievement Like '%做菜小能手%'  and userinfo.userId=new.userId;
if @var>=3 AND @is_exist=0 then 
 UPDATE userinfo SET userinfo.achievement=concat(userinfo.achievement, '/做菜小能手')  where userinfo.userId=new.userId;
 UPDATE userinfo SET userinfo.socre=userinfo.socre+500  where userinfo.userId=new.userId;
end if;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `commentinfo`
--

CREATE TABLE `commentinfo` (
  `commentId` int(20) NOT NULL,
  `userId` int(20) NOT NULL,
  `contentId` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `score` int(3) NOT NULL,
  `creatStamp` bigint(13) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `commentinfo`
--

INSERT INTO `commentinfo` (`commentId`, `userId`, `contentId`, `url`, `content`, `score`, `creatStamp`, `status`) VALUES
(1, 1, 'A10001', '', '皮皮虾真的非常的好吃', 5, 0, 1),
(27, 1, 'A10001', '', '学到了谢谢博主，做出了非常好吃的食物', 5, 0, 1),
(28, 1, 'A10001', '', '这道菜真的是即美味又是非常的有营养', 4, 0, 1),
(29, 1, 'A10005', '', '炖牛肉真的非常的美味，我们一家人非常的喜欢吃', 4, 0, 1),
(30, 1, 'A10005', '', '偶也是喜欢牛肉，这道菜也是非常的美味哦哦哦', 5, 0, 1),
(31, 1, 'A10009', '', '我最喜欢喝粥了，平常喝的都是甜粥，这个粥真的是鲜美，非常好喝', 5, 0, 1),
(32, 1, 'A10009', '', '做给宝宝，他今天喝了十碗，现在在医院躺着，你太棒了', 2, 0, 1),
(33, 1, 'A10009', '', '今天学着步骤做了很多粥，喝完之后成仙了，你真的太棒了', 5, 0, 1),
(34, 1, 'A10017', '', '平常最喜欢吃鸡爪，想不到现在还能够这样做，太好吃了', 5, 0, 1),
(35, 1, 'A10019', '', '得到了马肉，不知道怎么做才好吃，今天学到了', 5, 0, 1),
(36, 1, 'A10003', '', '花甲价廉物美，营养丰富，很受大家的欢迎。', 3, 1577206374967, 1),
(37, 1, 'A10001', '', '这个时节渔民出海有些困难，天气不好出海也很危险，鲜活的皮皮虾不好买了，只能买了些冷冻的皮皮虾肉，', 4, 1577206464747, 1),
(38, 1, 'A10001', '', '加入少量的盐和鸡精', 5, 1577206506387, 1),
(39, 1, 'V20002', '', '1524183302@qq.com', 4, 1577206759826, 1),
(40, 1, 'V20002', '', '111111111111111', 3, 1577221318569, 1),
(41, 1, 'V20002', '', '顶顶顶顶顶顶顶顶顶顶', 3, 1577221338823, 1),
(42, 1, 'A10001', '', '水水水水', 3, 1577221461294, 1),
(43, 1, 'A10005', '', '水水水水水', 3, 1577221482566, 1),
(44, 1, 'A10006', '', '很好', 3, 1577260726458, 1),
(45, 1, 'A10004', '', '111111', 3, 1577263311104, 1),
(46, 1, 'A10003', 'url', '很不错', 3, 1578154791987, 1),
(47, 2059, 'A10006', 'url', '很不错', 3, 1578221949999, 1);

--
-- 触发器 `commentinfo`
--
DELIMITER $$
CREATE TRIGGER `update_article_avgscore` AFTER INSERT ON `commentinfo` FOR EACH ROW begin
UPDATE articleinfo set articleinfo.avgscore=(articleinfo.avgscore*articleinfo.commentNum+new.score)/(articleinfo.commentNum+1)
WHERE concat('A', articleinfo.articleId) = new.contentid;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_video_avgscore` AFTER INSERT ON `commentinfo` FOR EACH ROW begin
UPDATE videoinfo set videoinfo.avgscore=(videoinfo.avgscore*videoinfo.commentNum+new.score)/(videoinfo.commentNum+1)
WHERE concat('V', videoinfo.videoid) = new.contentid;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `friendrange`
--

CREATE TABLE `friendrange` (
  `userId` int(20) NOT NULL,
  `friendId` int(20) NOT NULL,
  `creatTimeStamp` bigint(13) NOT NULL,
  `status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `friendrange`
--

INSERT INTO `friendrange` (`userId`, `friendId`, `creatTimeStamp`, `status`) VALUES
(1, 1, 1577263623374, 1),
(1, 2, 1577262862934, 1),
(2055, 1, 1577023350987, 1),
(2055, 3, 1577025030007, 1),
(2055, 8, 1577024722698, 1);

-- --------------------------------------------------------

--
-- 表的结构 `goods`
--

CREATE TABLE `goods` (
  `id` int(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pictureurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` int(10) DEFAULT NULL,
  `number` int(10) DEFAULT NULL,
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `timestamp` bigint(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `goods`
--

INSERT INTO `goods` (`id`, `name`, `pictureurl`, `price`, `number`, `introduction`, `timestamp`) VALUES
(1, '纳米健康锅', 'good/1.jpg', 50, 77, '运用纳米技术，不沾锅，炒出的菜更加香', NULL),
(2, '金色勺叉', 'good/2.jpg', 35, 85, '金色勺子和叉子，让你在吃饭的时候感受到奢华气质', NULL),
(6, '雕花盘子', 'good/3.jpg', 20, 145, '雕花盘子，可以用来盛放炒菜以及水果', NULL),
(7, '不锈钢高压锅', 'good/4.jpg', 200, 47, '采用不锈钢材质，能够进行各种炖菜的制作，用途广泛，物有所值', NULL),
(8, '灰色平板锅', 'good/5.jpg', 66, 598, '用比较轻的铝作为制造物料。平底锅适合作为焙、烘、蒸、烤或炒海鲜、肉类和家禽类佳肴，煮蔬菜或便于用手指取食的健康小吃。容易使用，只需短短几分钟，就能烹调出各式各样的佳肴。', NULL),
(9, '锅铲子', 'good/6.jpg', 25, 798, '锅铲，炒菜时用以翻拨原料，煮饭时搅米、起饭、铲锅粑的工具。一般以熟铁、不锈钢、铝材制成。有大有小，煮饭用的较大，有长柄；炒菜用的较小。', NULL),
(10, '伊莱特电饭煲', 'good/7.jpg', 450, 38, '采用陶瓷内胆，适合做出精美米饭，也可以熬粥。', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `history`
--

CREATE TABLE `history` (
  `userId` int(20) NOT NULL,
  `contentId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createStamp` bigint(13) NOT NULL,
  `isCollected` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `history`
--

INSERT INTO `history` (`userId`, `contentId`, `createStamp`, `isCollected`) VALUES
(1, 'A10001', 1578154798107, 0),
(1, 'A10003', 1578154801519, 0),
(1, 'A10004', 1577263664452, 0),
(1, 'A10005', 1578150257939, 0),
(1, 'A10006', 1577263268591, 0),
(1, 'A10007', 1577215772410, 0),
(1, 'A10008', 1577215172675, 0),
(1, 'A10009', 1577221470809, 0),
(1, 'A10011', 1577259900918, 0),
(1, 'A10012', 1577197575912, 0),
(1, 'A10084', 1577253688067, 0),
(1, 'A10085', 1577219962850, 0),
(1, 'A10086', 1577255568125, 0),
(1, 'A10089', 1577263358825, 0),
(1, 'V20001', 1577263373936, 0),
(1, 'V20002', 1577259611015, 0),
(1, 'V20003', 1577263377341, 0),
(1, 'V20004', 1577215357200, 0),
(2, 'A10001', 1577085498717, 0),
(3, 'A10001', 1577013632632, 0),
(3, 'V20001', 1577086020885, 0),
(4, 'A10001', 1577085821597, 0),
(5, 'A10001', 1577085976127, 0),
(6, 'A10001', 1577087553859, 0),
(7, 'A10001', 1577101205293, 0),
(7, 'V20003', 1577014436615, 0),
(8, 'V20001', 1577013825014, 0),
(8, 'V20004', 1577085706413, 0),
(2055, 'A10001', 1577013693743, 0),
(2059, 'A10006', 1578221952188, 0),
(2059, 'A10008', 1578221984203, 0);

-- --------------------------------------------------------

--
-- 表的结构 `message`
--

CREATE TABLE `message` (
  `msgId` int(11) NOT NULL,
  `sender` int(20) NOT NULL,
  `receive` int(255) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `timestamp` bigint(13) NOT NULL,
  `isread` int(1) NOT NULL,
  `canread` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `message`
--

INSERT INTO `message` (`msgId`, `sender`, `receive`, `content`, `timestamp`, `isread`, `canread`) VALUES
(3, 1, 2, '圣诞节快乐', 1577208993965, 1, 1),
(5, 1, 1, '圣诞节快乐啊', 1577214630071, 1, 1),
(6, 1, 1, '加油加油加油', 1577214954658, 1, 1),
(7, 1, 1, '面对困难,不要害怕', 1577215021771, 1, 1),
(8, 1, 1, '微笑着面对它', 1577215083931, 1, 1),
(9, 1, 1, '面对恐惧最好的办法就是面对它', 1577216048112, 1, 1),
(12, 1, 1, '你好1', 1577259787796, 1, 1),
(13, 1, 1, '你好', 1577263555310, 1, 1),
(14, 1, 2, '11111', 1577263593559, 1, 1),
(15, 1, 2, '我想吃红烧肉', 1577263607527, 1, 1);

-- --------------------------------------------------------

--
-- 表的结构 `record`
--

CREATE TABLE `record` (
  `id` int(20) NOT NULL,
  `userid` int(20) DEFAULT NULL,
  `good_id` int(20) DEFAULT NULL,
  `is_finish` int(1) DEFAULT NULL,
  `timestamp` bigint(13) DEFAULT NULL,
  `scoreSpent` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `record`
--

INSERT INTO `record` (`id`, `userid`, `good_id`, `is_finish`, `timestamp`, `scoreSpent`) VALUES
(1, 12, 21, 1, 123, 1),
(2, 2055, 2, 1, 21, 12),
(3, 2055, 2, 1, 44444, 12),
(4, 2055, 4, 1, 1, 23);

-- --------------------------------------------------------

--
-- 表的结构 `reward`
--

CREATE TABLE `reward` (
  `rewardId` int(11) NOT NULL,
  `sender` int(20) NOT NULL,
  `receiver` int(20) NOT NULL,
  `size` int(20) NOT NULL,
  `timestamp` bigint(13) NOT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `reward`
--

INSERT INTO `reward` (`rewardId`, `sender`, `receiver`, `size`, `timestamp`, `describe`) VALUES
(1, 2055, 1, 10, 1577072492734, '这是一次打赏'),
(2, 2055, 1, 10, 1577072515170, '这是一次打赏'),
(3, 2055, 1, 10, 1577072544307, '这是一次打赏'),
(4, 2055, 1, 10, 1577072571139, '这是一次打赏'),
(5, 2055, 1, 10, 1577072593747, '这是一次打赏'),
(6, 2055, 1, 10, 1577072599608, '这是一次打赏'),
(7, 2055, 1, 10, 1577072627891, '这是一次打赏'),
(8, 2055, 1, 10, 1577072648443, '这是一次打赏'),
(9, 2055, 1, 10, 1577072653973, '这是一次打赏'),
(10, 2055, 1, 211, 1577072689823, '这是一次打赏'),
(11, 2055, 1, 422, 1577072707119, '这是一次打赏'),
(12, 2055, 2055, 12, 1577072918940, '这是一次打赏'),
(13, 2055, 2055, 12, 1577073116132, '这是一次打赏'),
(14, 2055, 2055, 12, 1577073138444, '这是一次打赏'),
(15, 2055, 2055, 12, 1577073139585, '这是一次打赏'),
(16, 1, 1, 5, 1577196604113, '这是一次打赏'),
(17, 1, 1, 5, 1577205595776, '这是一次打赏'),
(18, 1, 1, 10, 1577221305065, '这是一次打赏'),
(19, 1, 1, 10, 1577221415958, '这是一次打赏'),
(20, 1, 1, 5, 1577221449131, '这是一次打赏'),
(21, 1, 3, 10, 1577221702255, '这是一次打赏'),
(22, 1, 3, 10, 1577221868533, '这是一次打赏'),
(23, 1, 3, 10, 1577222348978, '这是一次打赏'),
(24, 1, 3, 10, 1577222660243, '这是一次打赏'),
(25, 1, 3, 10, 1577223079817, '这是一次打赏'),
(26, 1, 2, 10, 1577263289137, '这是一次打赏');

-- --------------------------------------------------------

--
-- 表的结构 `userinfo`
--

CREATE TABLE `userinfo` (
  `userId` int(20) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '未命名',
  `headPicture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'user/1/av003.jpg',
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '用户类别:普通用户,VIP用户,管理员',
  `achievement` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `socre` float(10,0) DEFAULT NULL,
  `level` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `userinfo`
--

INSERT INTO `userinfo` (`userId`, `username`, `headPicture`, `email`, `password`, `phone`, `status`, `type`, `achievement`, `socre`, `level`) VALUES
(2, 'kele', 'user/2/weiqu.jpg', '888', '8888', '1111111111', 1, 0, '做菜小能手', 15, 1),
(3, '小王子', '42423', '2342', '234', '2342', 1, 0, '做菜达人', 55, 1),
(4, '热情问题', '123412', '43214', '4321412', '3241', 1, 0, NULL, 0, 1),
(5, '阿尔法', '234', '2', '42', '23', 1, 0, NULL, 5, 1),
(6, '阿飞', '1234', '234', '1423', '423', 1, 0, NULL, 0, 1),
(7, '3241', '2', '····', '2', '2', 1, 0, NULL, 8, 1),
(8, '4234', '423', '42', '423', '42', 1, 0, NULL, 0, 1),
(9, '热特热v', '324', '234234', '42', '42342', 1, 0, NULL, -7, 1),
(2055, '墨菲特', '3241', '21423', '214234', '2342', 1, 0, NULL, 3, 1),
(2057, NULL, 'user/1/av003.jpg', '17301052@bjtu.edu.cn', '123456', NULL, NULL, NULL, NULL, NULL, NULL),
(2059, NULL, 'user/1/av003.jpg', '1524183302@qq.com', '123456', NULL, NULL, NULL, '做菜小能手', NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `videoinfo`
--

CREATE TABLE `videoinfo` (
  `videoid` int(20) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pictureurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `urls` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `praiseNum` int(10) DEFAULT NULL,
  `readNum` int(10) DEFAULT NULL,
  `commentNum` int(10) DEFAULT NULL,
  `avgscore` float(5,2) DEFAULT NULL,
  `creatStamp` bigint(13) DEFAULT NULL,
  `isTop` int(1) DEFAULT NULL,
  `userId` int(20) DEFAULT NULL,
  `visitNum` int(10) DEFAULT NULL,
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `videoinfo`
--

INSERT INTO `videoinfo` (`videoid`, `title`, `pictureurl`, `urls`, `praiseNum`, `readNum`, `commentNum`, `avgscore`, `creatStamp`, `isTop`, `userId`, `visitNum`, `tag`) VALUES
(20001, '肉末炒蒜苔', 'video/20001/1.PNG', 'video/20001/肉末炒蒜苔', 2, 23, 1, 1.00, 1, 1, 1, 1, '1'),
(20002, '手抓培根卷', 'video/20002/2.PNG', 'video/20002/手抓培根卷.mp4', 2, 32, 4, 3.06, 1, 1, 1, 1, '1'),
(20003, '酸辣猪蹄', 'video/20003/3.PNG', 'video/20003/酸辣猪蹄.mp4', 1, 10, 1, 1.00, 1, 1, 1, 1, '1'),
(20004, '香辣牛肉酱', 'video/20004/4.PNG', 'video/20004/香辣牛肉酱.mp4', 1, 4, 1, 1.00, 1, 1, 1, 1, '1');

--
-- 触发器 `videoinfo`
--
DELIMITER $$
CREATE TRIGGER `score_video_trigger` AFTER INSERT ON `videoinfo` FOR EACH ROW begin
			update userinfo 
            set score = score + 5
            where userinfo.userId = (select new.userId from new);
        end
$$
DELIMITER ;

--
-- 转储表的索引
--

--
-- 表的索引 `achievemenkindtinfo`
--
ALTER TABLE `achievemenkindtinfo`
  ADD PRIMARY KEY (`typeid`) USING BTREE;

--
-- 表的索引 `articleinfo`
--
ALTER TABLE `articleinfo`
  ADD PRIMARY KEY (`articleId`) USING BTREE;

--
-- 表的索引 `commentinfo`
--
ALTER TABLE `commentinfo`
  ADD PRIMARY KEY (`commentId`) USING BTREE;

--
-- 表的索引 `friendrange`
--
ALTER TABLE `friendrange`
  ADD PRIMARY KEY (`userId`,`friendId`) USING BTREE;

--
-- 表的索引 `goods`
--
ALTER TABLE `goods`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 表的索引 `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`userId`,`contentId`) USING BTREE;

--
-- 表的索引 `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`msgId`) USING BTREE;

--
-- 表的索引 `record`
--
ALTER TABLE `record`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 表的索引 `reward`
--
ALTER TABLE `reward`
  ADD PRIMARY KEY (`rewardId`) USING BTREE;

--
-- 表的索引 `userinfo`
--
ALTER TABLE `userinfo`
  ADD PRIMARY KEY (`userId`) USING BTREE,
  ADD UNIQUE KEY `email` (`email`) USING BTREE;

--
-- 表的索引 `videoinfo`
--
ALTER TABLE `videoinfo`
  ADD PRIMARY KEY (`videoid`) USING BTREE,
  ADD KEY `userId` (`userId`) USING BTREE;

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `achievemenkindtinfo`
--
ALTER TABLE `achievemenkindtinfo`
  MODIFY `typeid` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- 使用表AUTO_INCREMENT `articleinfo`
--
ALTER TABLE `articleinfo`
  MODIFY `articleId` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10090;

--
-- 使用表AUTO_INCREMENT `commentinfo`
--
ALTER TABLE `commentinfo`
  MODIFY `commentId` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- 使用表AUTO_INCREMENT `goods`
--
ALTER TABLE `goods`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- 使用表AUTO_INCREMENT `history`
--
ALTER TABLE `history`
  MODIFY `userId` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2060;

--
-- 使用表AUTO_INCREMENT `message`
--
ALTER TABLE `message`
  MODIFY `msgId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- 使用表AUTO_INCREMENT `record`
--
ALTER TABLE `record`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- 使用表AUTO_INCREMENT `reward`
--
ALTER TABLE `reward`
  MODIFY `rewardId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- 使用表AUTO_INCREMENT `userinfo`
--
ALTER TABLE `userinfo`
  MODIFY `userId` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2060;

--
-- 使用表AUTO_INCREMENT `videoinfo`
--
ALTER TABLE `videoinfo`
  MODIFY `videoid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20005;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
