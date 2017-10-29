CREATE DATABASE  IF NOT EXISTS `nokia_tool_manager` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `nokia_tool_manager`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: nokia_tool_manager
-- ------------------------------------------------------
-- Server version	5.6.28-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ask`
--

DROP TABLE IF EXISTS `ask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ask` (
  `ask-id` int(11) NOT NULL AUTO_INCREMENT,
  `sender-uid` int(11) NOT NULL,
  `tool-id` int(11) NOT NULL,
  `content` text NOT NULL,
  `time` int(11) NOT NULL,
  PRIMARY KEY (`ask-id`),
  UNIQUE KEY `ask-id_UNIQUE` (`ask-id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ask`
--

LOCK TABLES `ask` WRITE;
/*!40000 ALTER TABLE `ask` DISABLE KEYS */;
INSERT INTO `ask` VALUES (1,3,2,'还有谁有问题？',1494340975),(2,4,2,'这个工具怎么用的呀',1494500660),(3,3,3,'有谁不会的可以来请教我。我会好好教导教导的',1494500943),(4,3,4,'还有谁要的 私MMMMMM我。',1494502239),(5,3,3,'谁想学',1494505056),(6,6,1,'为什么我打不开啊？',1494570999),(7,2,11,'为什么我用不了？谁来教教我？',1494573234);
/*!40000 ALTER TABLE `ask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attention`
--

DROP TABLE IF EXISTS `attention`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attention` (
  `attention-id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `tool-id` int(11) NOT NULL,
  PRIMARY KEY (`uid`,`tool-id`),
  UNIQUE KEY `attention-id_UNIQUE` (`attention-id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attention`
--

LOCK TABLES `attention` WRITE;
/*!40000 ALTER TABLE `attention` DISABLE KEYS */;
INSERT INTO `attention` VALUES (2,4,9),(5,3,4),(6,3,1),(8,6,2);
/*!40000 ALTER TABLE `attention` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `categoryid_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'办公'),(2,'网络'),(3,'安全'),(4,'系统维护'),(5,'其他');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `comment-id` int(11) NOT NULL AUTO_INCREMENT,
  `sender-uid` int(11) NOT NULL,
  `tool-id` int(11) NOT NULL,
  `content` text NOT NULL,
  `comment-time` int(11) NOT NULL,
  PRIMARY KEY (`comment-id`),
  UNIQUE KEY `comment-id_UNIQUE` (`comment-id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,3,2,'我是第一个上传工具的人！哈哈哈哈哈哈哈',1494340960),(2,2,2,'楼上的，我才是第一个！',1494341008),(3,4,1,'很不错的一款工具，大赞！',1494500601),(4,3,3,'破解作业，想学破解的快来试试！',1494500914),(5,3,4,'唯美动态壁纸',1494502226),(6,3,5,'真好用',1494502360),(7,3,5,'棒棒的。',1494502369),(8,3,5,'我还要！',1494502373),(9,3,5,'楼主再多发几个资源。',1494502382),(10,3,8,'神器！一键激活！毫无任何烦恼！',1494503346),(11,3,12,'devcpp好用！c、c++初学者神器！',1494503890),(12,3,7,'haiyoushei',1494505018),(13,3,13,'码农神器',1494505205),(14,6,1,'非常实用，赞一个！',1494570963),(15,3,2,'今天发布新版本！ 各位测试测试',1494571449),(16,2,16,'好用！',1494572793),(17,5,16,'Nice！',1494572817),(18,2,18,'实用',1494573159),(19,2,11,'希望能尽快上传新版本！',1494573206),(20,2,11,'这个版本有大问题',1494573212),(21,3,2,'henbang!',1494588850),(22,3,2,'!!!',1494666656);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `management`
--

DROP TABLE IF EXISTS `management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `management` (
  `idmanagement` int(11) NOT NULL AUTO_INCREMENT,
  `user-id` int(11) NOT NULL,
  `category-id` int(11) NOT NULL,
  PRIMARY KEY (`user-id`,`category-id`),
  UNIQUE KEY `idmanagement_UNIQUE` (`idmanagement`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `management`
--

LOCK TABLES `management` WRITE;
/*!40000 ALTER TABLE `management` DISABLE KEYS */;
INSERT INTO `management` VALUES (3,1,3),(4,1,4),(5,1,1),(7,1,2),(10,4,3);
/*!40000 ALTER TABLE `management` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_push`
--

DROP TABLE IF EXISTS `message_push`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_push` (
  `idmessage` int(11) NOT NULL AUTO_INCREMENT,
  `receiver-uid` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `title` text NOT NULL,
  `content` text NOT NULL,
  `sendtime` int(11) NOT NULL,
  `link` text,
  `have-read` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idmessage`),
  UNIQUE KEY `idmessage_UNIQUE` (`idmessage`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_push`
--

LOCK TABLES `message_push` WRITE;
/*!40000 ALTER TABLE `message_push` DISABLE KEYS */;
INSERT INTO `message_push` VALUES (1,3,1,'BillChen 评论了您的工具','我是第一个上传工具的人！哈哈哈哈哈哈哈',1494340960,'/tool?tid=2',1),(2,3,2,'BillChen 向您提问','还有谁有问题？',1494340975,'/tool?tid=2',1),(3,3,1,'Rin 评论了您的工具','楼上的，我才是第一个！',1494341008,'/tool?tid=2',1),(4,1,1,'CoderLee 评论了您的工具','很不错的一款工具，大赞！',1494500601,'/tool?tid=1',1),(5,3,2,'CoderLee 向您提问','这个工具怎么用的呀',1494500660,'/tool?tid=2',1),(6,3,1,'BillChen 评论了您的工具','破解作业，想学破解的快来试试！',1494500915,'/tool?tid=3',1),(7,3,2,'BillChen 向您提问','有谁不会的可以来请教我。我会好好教导教导的',1494500943,'/tool?tid=3',1),(8,3,1,'BillChen 评论了您的工具','唯美动态壁纸',1494502226,'/tool?tid=4',1),(9,3,2,'BillChen 向您提问','还有谁要的 私MMMMMM我。',1494502239,'/tool?tid=4',1),(10,3,1,'BillChen 评论了您的工具','真好用',1494502360,'/tool?tid=5',1),(11,3,1,'BillChen 评论了您的工具','棒棒的。',1494502369,'/tool?tid=5',1),(12,3,1,'BillChen 评论了您的工具','我还要！',1494502373,'/tool?tid=5',1),(13,3,1,'BillChen 评论了您的工具','楼主再多发几个资源。',1494502382,'/tool?tid=5',1),(14,3,1,'BillChen 评论了您的工具','神器！一键激活！毫无任何烦恼！',1494503346,'/tool?tid=8',1),(15,3,1,'BillChen 评论了您的工具','devcpp好用！c、c++初学者神器！',1494503890,'/tool?tid=12',1),(16,3,1,'BillChen 评论了您的工具','haiyoushei',1494505018,'/tool?tid=7',1),(17,3,2,'BillChen 向您提问','谁想学',1494505056,'/tool?tid=3',1),(18,3,1,'BillChen 评论了您的工具','码农神器',1494505205,'/tool?tid=13',1),(19,1,1,'张三 评论了您的工具','非常实用，赞一个！',1494570963,'/tool?tid=1',1),(20,1,2,'张三 向您提问','为什么我打不开啊？',1494570999,'/tool?tid=1',1),(21,6,2,'admin 回复了您的问题','你需要为其授予管理员权限。',1494571052,'/tool?tid=1',0),(22,3,1,'BillChen 评论了您的工具','今天发布新版本！ 各位测试测试',1494571449,'/tool?tid=2',1),(23,2,1,'Rin 评论了您的工具','好用！',1494572793,'/tool?tid=16',0),(24,2,1,'superhh 评论了您的工具','Nice！',1494572817,'/tool?tid=16',0),(25,2,1,'Rin 评论了您的工具','实用',1494573159,'/tool?tid=18',0),(26,3,1,'Rin 评论了您的工具','希望能尽快上传新版本！',1494573206,'/tool?tid=11',1),(27,3,1,'Rin 评论了您的工具','这个版本有大问题',1494573212,'/tool?tid=11',1),(28,3,2,'Rin 向您提问','为什么我用不了？谁来教教我？',1494573234,'/tool?tid=11',1),(29,4,2,'Rin 回复了您的问题','双击打开使用。',1494578283,'/tool?tid=2',0),(30,3,1,'BillChen 评论了您的工具','henbang!',1494588850,'/tool?tid=2',1),(31,3,1,'BillChen 评论了您的工具','!!!',1494666656,'/tool?tid=2',1);
/*!40000 ALTER TABLE `message_push` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reply` (
  `reply-id` int(11) NOT NULL AUTO_INCREMENT,
  `reply-sender` int(11) NOT NULL,
  `reply-type` int(11) NOT NULL DEFAULT '0',
  `ask-id` int(11) NOT NULL,
  `reply-target-uid` int(11) DEFAULT NULL,
  `reply-time` int(11) NOT NULL,
  `reply-content` text NOT NULL,
  PRIMARY KEY (`reply-id`),
  UNIQUE KEY `reply-id_UNIQUE` (`reply-id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (1,1,0,6,6,1494571052,'你需要为其授予管理员权限。'),(2,2,0,2,4,1494578283,'双击打开使用。');
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `tag-id` int(11) NOT NULL AUTO_INCREMENT,
  `tag-name` varchar(90) NOT NULL,
  PRIMARY KEY (`tag-id`),
  UNIQUE KEY `tag-id_UNIQUE` (`tag-id`),
  UNIQUE KEY `tag-name_UNIQUE` (`tag-name`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (25,'C'),(26,'C++'),(24,'DEV'),(22,'mysql'),(16,'一键激活'),(40,'不错'),(33,'交友神器'),(20,'代码管理'),(19,'便捷'),(12,'值得拥有'),(17,'傻瓜激活'),(35,'净网大师'),(36,'净网神器'),(8,'动态壁纸'),(32,'即时语音'),(1,'命令行'),(11,'唯美壁纸'),(39,'好用'),(21,'学习工具'),(34,'安全管家'),(5,'实用'),(37,'工具'),(38,'很好用'),(28,'急速解压'),(14,'指令大全'),(30,'方便快捷'),(29,'无广告'),(10,'梦幻桌面'),(13,'汇编神器'),(15,'激活神器'),(18,'版本控制'),(7,'破解作业'),(6,'破解小软件'),(3,'破解神器'),(27,'神器'),(2,'网络'),(4,'轻量级');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tool`
--

DROP TABLE IF EXISTS `tool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tool` (
  `tool-id` int(11) NOT NULL AUTO_INCREMENT,
  `tool-name` text NOT NULL,
  `uploader-uid` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  `brief-intro` text,
  `description` mediumtext,
  `download-count` int(11) NOT NULL DEFAULT '0',
  `zan-count` int(11) NOT NULL DEFAULT '0',
  `comment-count` int(11) NOT NULL DEFAULT '0',
  `newest-file-id` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`tool-id`),
  UNIQUE KEY `tool-id_UNIQUE` (`tool-id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tool`
--

LOCK TABLES `tool` WRITE;
/*!40000 ALTER TABLE `tool` DISABLE KEYS */;
INSERT INTO `tool` VALUES (1,'网络流量监测器',1,2,'一款轻巧的用于监测网络流量的工具，兼容性好，底层驱动稳定高效。适用于Windows和Linux系统。','自主研发的抓包引擎，兼容性更好，底层驱动稳定高效。使用简单，易学易用。\r\n软件集成六种部署模式，适应用户各种管理需求。\r\n\r\n操作系统要求：适用于Windows和Linux',2,1,2,21),(2,'OllyDbg',3,3,'OLLYDBG是一个新的动态追踪工具，将IDA与SoftICE结合起来的思想，Ring 3级调试器，非常容易上手，己代替SoftICE成为当今最为流行的调试解密工具了。同时还支持插件扩展功能，是目前最强大的调试工具。','运行环境：OllyDbg 可以以在任何采用奔腾处理器的 Windows 95、98、ME、NT 或是 XP（未经完全测试）操作系统中工作，但我们强烈建议您采用300-MHz以上的奔腾处理器以达到最佳效果。还有，OllyDbg 是极占内存的，因此如果您需要使用诸如追踪调试[Trace]之类的扩展功能话，建议您最好使用128MB以上的内存。\r\n目前测试 OllyDbg 可以运行在任意Windows x86架构的平台下。\r\n支持的处理器 OllyDbg 支持所有 80x86、奔腾、MMX、3DNOW！、Athlon扩展指令集、SSE指令集以及相关的数据格式，但是不支持SSE2指令集。\r\n配置：有多达百余个选项用来设置 OllyDbg 的外观和运行。\r\n数据格式：OllyDbg 的数据窗口能够显示的所有数据格式：HEX、ASCII、UNICODE、 16/32位有/无符号/HEX整数、32/64/80位浮点数、地址、反汇编（MASM、IDEAL或是HLA）、PE文件头或线程数据块。\r\n帮助：此文件中包含了关于理解和使用 OllyDbg 的必要的信息。如果您还有 Windows API 帮助文件的话（由于版权的问题 win32.hlp 没有包括在内），您可以将它挂在 OllyDbg 中，这样就可以快速获得系统函数的相关帮助。\r\n启动：您可以采用命令行的形式指定可执行文件、也可以从菜单中选择，或直接拖放到OllyDbg中，或者重新启动上一个被调试程序，或是挂接[Attach]一个正在运行的程序。OllyDbg支持即时调试。OllyDbg根本不需要安装，可直接在软盘中运行！\r\n调试DLLs：您可以利用OllyDbg调试标准动态链接库(DLLs）。OllyDbg 会自动运行一个可执行程序。这个程序会加载链接库，并允许您调用链接库的输出函数。\r\n源码级调试：OllyDbg 可以识别所有 Borland 和 Microsoft 格式的调试信息。这些信息包括源代码、函数名、标签、全局变量、静态变量。有限度的支持动态（栈）变量和结构。\r\n代码高亮：OllyDbg 的反汇编器可以高亮不同类型的指令（如：跳转、条件跳转、入栈、出栈、调用、返回、特殊的或是无效的指令）和不同的操作数（常规[general]、\r\nFPU/SSE、段/系统寄存器、在栈或内存中的操作数，常量）。您可以定制个性化高亮方案。\r\n线程：OllyDbg 可以调试多线程程序。因此您可以在多个线程之间转换，挂起、恢复、终止线程或是改变线程优先级。并且线程窗口将会显示每个线程的错误（就像调用 GETLASTERROR 返回一样）。\r\n分析：OllyDbg 的最大特点之一就是分析。它会分析函数过程、循环语句、选择语句、表[tables]、常量、代码中的字符串、欺骗性指令[tricky constructs]、API调用、函数中参数的数目，import表等等.. 这些分析增加了二进制代码的可读性，减少了出错的可能性，使得我们的调试工作更加容易。\r\nObject扫描。OllyDbg 可以扫描Object文件/库（包括 OMF 和 COFF 格式），解压代码段[code segments]并且对其位置进行定向。',10,1,5,15),(3,'TraceMe',3,3,'基础破解小作业。提高你的动态分析技术。','【详细过程】\r\n  很多新手朋友在这个问题上疑惑重重，今天以TraceMe.exe【加密与解密 第三版---调试片】为例说明一下消息断点+Run跟踪的使用.\r\n  如果大家不知道消息机制的话,建议好好看看【罗云彬版Win32汇编】，当你熟练掌握里面提到的窗口编程时，消息机制的学习就没甚问题了！\r\n  \r\n  ollydbg载入TraceMe.exe，F9运行程序，输入下面的信息.但是不要点那个\"Check\"按钮.\r\n  【用户名】：Gall_pediy\r\n  【序列号】：123456\r\n  \r\n  \r\n【第一部分：消息断点的设置】\r\n  1.选择View>>Windows,右键选择Actualize,显示下面的信息\r\n  Windows\r\n  Handle         Title                             Parent     WinProc    ID         Style      ExtStyle   Thread     ClsProc    Class\r\n  0010028C       TraceMe 动态分析技术                    Topmost               021D00C5   94CE0844   00010100   Main       77D3E577   #32770\r\n  K00090264      Default IME                       0010028C                         8C000000              Main       77D6C930   IME\r\n  IE001201F6     M                                 00090264                         8C000000              Main       FFFF02E3   MSCTFIME UI\r\n  K000B028A                                        0010028C              FFFFFFFF   50000007   00000004   Main       77D3B036   Button\r\n  K000C027E      Exit                              0010028C              000003EA   50010000   00020004   Main       77D3B036   Button\r\n  K000E01FA      ?                                 0010028C              000003F6   50010000   00020004   Main       77D3B036   Button\r\n  K000E01FC      www.PEDIY.com                     0010028C              000003F8   58020001   00000004   Main       77D3E5BB   Static\r\n  K000E0238                                        0010028C              000003E8   50030080   00000204   Main       77D3B3EC   Edit\r\n  K000E0278                                        0010028C              0000006E   50030080   00000204   Main       77D3B3EC   Edit\r\n  K0015023C      Check                             0010028C              000003F5   50010000   00020004   Main       77D3B036   Button\r\n  K001B0234      序列号:                              0010028C              FFFFFFFF   50000007   00000004   Main       77D3B036   Button\r\n  E001D026E      用户名:                              0010028C              FFFFFFFF   50000007   00000004   Main       77D3B036   Button\r\n  \r\n  我们这里的消息断点对象就是Check按钮,\r\n  在Check处右键，选择Message breakpoint on ClassProc，在出现的新窗口中选择消息\"202 WM_LBUTTONUP\",意思就是对Check按钮下断点，当按下按钮，然后松开时，就被断下来.\r\n  \r\n  到此为止,消息断点的设置完成了,\r\n  这个时候我们可以点击那个Check按钮.在下面的位置被断下来.\r\n  \r\n  77D3B036 >  8BFF            mov     edi, edi                         ; 在此处被断下\r\n  77D3B038    55              push    ebp\r\n  77D3B039    8BEC            mov     ebp, esp\r\n  77D3B03B    8B4D 08         mov     ecx, dword ptr [ebp+8]\r\n  \r\n  \r\n  2.返回到程序的领空\r\n  然后选择View>>Memory，程序的关键信息如下:\r\n  003F0000   0000E000                                       Map    RW        RW\r\n  00400000   00001000   TraceMe               PE header     Imag   R         RWE\r\n  00401000   00003000   TraceMe    .text      code          Imag   R         RWE\r\n  00404000   00001000   TraceMe    .rdata     imports       Imag   R         RWE\r\n  00405000   00001000   TraceMe    .data      data          Imag   R         RWE\r\n  00406000   00001000   TraceMe    .rsrc      resources     Imag   R         RWE\r\n  \r\n  我们在00401000处设置断点,设置完断点后F9运行程序，程序庭在下面的位置\r\n  004010D0   .  81EC F4000000 sub     esp, 0F4                         ;  程序停在这里【返回到程序领空是的位置】\r\n  004010D6   .  56            push    esi\r\n  004010D7   .  57            push    edi\r\n  004010D8   .  B9 05000000   mov     ecx, 5\r\n  004010DD   .  BE 60504000   mov     esi, 00405060\r\n  004010E2   .  8D7C24 18     lea     edi, dword ptr [esp+18]\r\n  \r\n  接下来就是Run跟踪\r\n  \r\n  \r\n  \r\n  【第二部分：Run跟踪】\r\n  1》分析代码\r\n  \r\n  接着上面的操作，程序此时停在004010D0这里，在Run跟踪钱，我们需要分析一下代码，快捷键是Ctrl+A【很多朋友无法Run跟踪,问题就在这里】\r\n  -------------------------------------------------------------------------------\r\n  \r\n  \r\n  2》 Run跟踪的大小设置\r\n  \r\n  选择Options>>Trace,设置大小为1M/45K\r\n  其他的选项根据自己的要求设置.【按照自己的要求设置】\r\n  -------------------------------------------------------------------------------\r\n  \r\n  \r\n  \r\n  3》 开始Run跟踪\r\n  1.选择Debug>>Open or clear run trace，\r\n  2.然后在004010D0右键，选择Run trace>>Add entries of all procudures\r\n  3.F9运行程序，然后点击View>>Run trace查看跟踪记录,\r\n  4.在记录处右键Profile module\r\n  \r\n  Profile of TraceMe\r\n  Count      Address    First command                     Comment\r\n  1.         004010D0   sub     esp, 0F4\r\n  1.         00401340   push    ebp\r\n  1.         00401360   xor     edx, edx\r\n  \r\n  双击00401360将来到关键位置.其他的分析打击自己完成吧',0,1,1,3),(4,'Advanced Codecs',3,5,'This module defines base classes for standard Python codecs (encoders and decoders) and provides access to the internal Python codec registry which manages the codec and error handling lookup process.','Nice!',28,1,1,4),(5,'DreamScene',3,5,'windows 7 dreamscene activator(梦幻桌面激活器)是一款能够一键开启win7的动态桌面和奇幻桌面的工具，通过该工具允许用户使用高清视频做为桌面的动态背景，支持32和64位系统，不需要安装第三方软件，让你的桌面瞬间变成高大上。有需要的朋友们可以下载试试！ ','windows 7 dreamscene activator可以将视频文件设置为桌面的一个功能，形式上类似实现桌面播放的播放软件，但又有本质上的区别，因为梦幻桌面是深嵌入操作系统内部，可以说是“原生”的桌面主题，不需要安装第三方软件，但需要用户自己动手开启。 \r\nwindows 7 dreamscene activator怎么用？为什么不能用？ \r\n\r\n打开梦幻桌面激活器，点击“启动dreamscene”，这时程序会自动激活系统的视频壁纸功能，过程可能会重启桌面程序，不用担心，等会就好了 \r\n\r\n安装以后，这时我们再点击桌面下右键菜单，你会发现，多出了个“Play DreamScene“(播放梦幻桌面)菜单： \r\n\r\n然后我们在选择我们想要的动态壁纸的视频，格式是WAM。 \r\n如果弹出不能使用梦幻桌面，可能有如下原因： \r\n确认aero特效开启，开启方法右键单击计算机->属性->高级系统设置->在高级选项卡里性能一栏点设置->调整为最佳外观或让windows选择计算机的最佳设置 \r\n确定你用的视频为*.wmv,*.mpg或*.mpeg \r\n确定你的电脑为win7家庭高级版以上。',0,1,4,5),(6,'2号B型动态壁纸',3,5,'这是《Wallpaper Engine》2B小姐姐动态壁纸，由“RatCoToChat”制作，一张非常不错的高清动态壁纸。','《Wallpaper Engine》2B小姐姐动态壁纸：是针对近些期间玩家都比较喜爱关注的火热软件《Wallpaper Engine》制作的2B小姐姐动态壁纸，该款壁纸取材于尼尔机械纪元中的性感角色2B小姐姐作为本次动态壁纸的主要题材，该款壁纸完美体现出了2B小姐姐的温柔的一面，非常适合学生党与上班族的玩家们，喜欢的玩家快来下载吧。',0,1,0,6),(7,'汇编金手指',3,5,'指令系统，常用伪操作，DOS中断，BIOS中断，错误信息，DEBUG命令，高级汇编，技术指标，ASCII码表，32位指令。','汇编语言技术指标 \r\n\r\n一、程序状态寄存器\r\n二、中断屏蔽寄存器\r\n三、中断命令寄存器\r\n四、键盘状态字节\r\n五、键盘缓冲区结构\r\n六、彩色属性字节\r\n七、DOS预定义文件代号\r\n八、文件代号式文件操作错误返回代码\r\n九、文件属性字节\r\n十、存取代码\r\n十一、磁道地址区',1,1,1,7),(8,'microKMS',3,4,'1.使用本软件需保持网络畅通 \r\n2.点击\"激活\"按钮后,请稍后片刻 \r\n3.此软件纯属技术交流,请支持微软 \r\n4.本软件绿色无毒,经得起任何杀软查杀','MicroKMS神龙版可以在线激活win8/8.1和office2013等的kms激活工具，其实笔者发布这款软件已经很久了，但一直没有对软件进行书面描述，以至于很多同学拿到工具后无从下手，从而无法激活windows和office，从微软发布win8以来，针对win10、win8/8.1系统的kms激活工具层出不穷，但慢慢的可以用来在线激活的kms工具却销声匿迹了，这也是为何笔者自己要发布kms在线激活工具的原因，加上目前网络上的激活工具鱼目混珠，杂乱无章，笔者果断推出了MicroKMS 神龙版。',2,1,1,10),(9,'github离线版客户端',4,1,'这是github客户端，一款代码管理工具。','解压后直接安装即可。',0,1,0,9),(10,'mysql-connector-java',3,1,'MySQL provides standards-based drivers for JDBC, ODBC, and .Net enabling developers to build database applications in their language of choice. In addition, a native C library allows developers to embed MySQL directly into their applications.','mysql-connector-java是一款由mysql推出的官方驱动，它是java通过JDBC连接操作mysql的驱动，解压之后是jar包，需安装Java环境方可使用。需要mysql connector java的朋友们可以前来下载使用。',0,2,0,11),(11,'Navicat For MySQL',3,1,'Navicat Premium 是一个可多重连接的数据库管理工具，它可让你以单一程序同时连接到 MySQL、Oracle、PostgreSQL、SQLite 及 SQL Server 数据库，让管理不同类型的数据库更加方便。Navicat Premium 结合了其他 Navicat 成员的功能。有了不同数据库类型的连接能力，Navicat Premium 支持在 MySQL、Oracle、PostgreSQL、SQLite 及 SQL Server 之间传输数据。它支持大部份 MySQL、Oracle、PostgreSQL、SQLite 及 SQL Server 的功能。\r\n\r\nNavicat Premium 使你能简单并快速地在各种数据库系统间传输数据，或传输一份指定 SQL 格式及编码的纯文本文件。这可以简化从一台服务器迁移数据到另一台服务器的类型的进程。不同数据库的批处理作业也可以计划并在指定的时间运行。','Navicat for MySQL 是一套专为 MySQL 设计的高性能数据库管理及开发工具。它可以用于任何版本 3.21 或以上的 MySQL 数据库服务器，并支持大部份 MySQL 最新版本的功能，包括触发器、存储过程、函数、事件、视图、管理用户等。 \r\n\r\n点击 或选择文件 -> 新建连接 来设置连接属性。 \r\n连接设置 \r\n在创建连接后，你可以连接到数据库，管理它的对象、表中的数据等。请看下面的帮助，以了解如何用最简单的方法运行这些操作。\r\n\r\n与数据库或模式工作 \r\n\r\n与数据库或模式的对象工作Navicat 浏览器！\r\n\r\nNavicat 窗口包括一个导览窗格（左边的窗格）及一个对象窗格（右边的窗格）。\r\n\r\n导览窗格一个是导览连接、数据库及数据库对象的基本途径。它采用树状结构，让你透过弹出菜单快捷及方便地使用数据库和它们的对象。\r\n\r\n对象窗格显示开启表、查询等。在窗口顶部的工具栏提供其他控制项，你可以用它来操作你的数据。\r\n下面分享一些navicat的使用心得，帮助那些入门的程序员们尽快的熟悉上这个软件。今天就先写navicat如何连接本地mysql数据库。',0,1,2,12),(12,'devcpp',3,1,'dBloodshed dev-c++是一个windows下的c和c++程序的集成开发环境。它使用mingw32/gcc编译器，遵循c/c++标准。开发环境包括多页面窗口、工程编辑器以及调试器等，在工程编辑器中集合了编辑器、编译器、连接程序和执行程序，提供高亮度语法显示的，以减少编辑错误，还有完善的调试功能，能够适合初学者与编程高手的不同需求，是学习c或c++的首选开发工具！','它集合了MinGW等众多自由软件，并且可以取得最新版本的各种工具支持，而这一切工作都是来自全球的狂热者所做的工作。Dev-C++是NOI、NOIP等比赛的指定工具，缺点是Debug功能弱。由于原开发公司在开发完4.9.9.2后停止开发，所以现在正由其它公司更新开发，但都基于4.9.9.2.Dev-C++ 最新版本：5.8.3。',2,1,1,13),(13,'SublimeText3',3,1,'Sublime Text中文破解版，是一款程序员必备代码编辑器，几乎每位程序员提到Sublime Text都是赞不绝口！它体积小巧，无需安装，绿色便携；它可跨平台支持Windows/Mac/Linux；支持32与64位操作系统，它在支持语法高亮、代码补全、代码片段（Snippet）、代码折叠、行号显示、自定义皮肤、配色方案等所有其它代码编辑器所拥有的功能的同时，又保证了其飞快的速度！还有着自身独特的功能，比如代码地图、多种界面布局以及全屏免打扰模式等，这些优秀特性让Sublime Text 2成了所有程序员眼中的神！','基于 Sublime Text 3 官方版进行修改汉化\r\n软件已默认注册且已去除自动检测升级提示\r\n整合GBK插件支持简体中文整合多款主题及细节优化\r\n整合Git、DocBlockr、MarkdownPreview、ColorPicker、AllAutocomplete等插件\r\n参考清风流音的汉化，清除个人信息\r\n完全绿色便携化',1,1,1,14),(14,'好压',2,1,'打开2345好压主界面，上方的主工具按钮可以帮助你完成绝大部分的操作，包括压缩、解压、删除等系列功能;文件夹的界面与资源管理器类似，您可以尝试用您习惯的操作方式，2345好压支持了在资源管理器中各种基本操作，上手熟悉十分简便。\r\n熟悉后您会发觉，好压主界面的右键菜单上面展示了大部分压缩、解压相关以及常用资源管理功能。在windows资源管理器中也集成了最常用快捷的压缩解压方式选项，智能帮助您完成操作。','2345好压，截至2012年4月底，用户数量已经过亿，是市场占有率第一的，拥有自主知识产权的国产压缩软件。对所有个人用户和企业用户永久免费。仅需安装一次，就可以支持包括ZIP、RAR、7Z等超过50种压缩文件格式。最新完美支持ZIPX格式解压。完美支持包括Win8，Win7，Vista，WinXP，和Win2003在内的所有WINDOWS系统。在速度、效率、通用性上均赶超国外知名的Winzip和Winrar等同类产品，在功能上拥有实用的多种文件处理小工具，是装机推崇的必备软件。',0,0,0,16),(15,'美图秀秀',5,5,'新版特征\r\n1.统一弹窗界面风格;\r\n2.优化二级弹窗功能放缩方式，支持居中缩放;\r\n3.修改文字卡顿问题;','美图秀秀是一款很好用的免费图片处理软件，不用学习就会用，比PS简单很多。能够一键式轻松打造各种影楼、lomo等艺术照；强大的人像美容，可祛斑祛痘、美白等；宝宝专用可爱边框、场景等是妈妈的首选；非主流炫酷、个性照随意处理。加上每天更新的精选素材，可以让你1分钟做出影楼级照片，还能一键分享到新浪微博、人人网。',0,0,0,17),(16,'YY语音',2,5,'最优秀的在线音视频软件，提供实时语音、娱乐表演、交友棋牌游戏等服务。','YY语音，是广州多玩信息技术有限公司研发的一款基于Internet 团队语音通信平台，功能强大、音质清晰、安全稳定、不占资源、适应游戏玩家的免费语音软件。在网络上通常用YY表示。',0,1,2,18),(17,'电脑管家',5,3,'1.桌面整理版本更新\r\n2.加速新增残留进程清理提示\r\n3.首页体检支持检出插件类型启动项','电脑管家是QQ医生3.3的升级版本，在功能上更全面、更智能、更贴心。主要功能包括安全防护、系统优化和软件管理，适合上网用户每天使用。',0,1,0,19),(18,'净网专家',2,3,'1.软件弹窗拦截\r\n2.捆绑安装拦截','不想在电脑上被各种不想要的弹窗和软件捆绑骚扰了，用净网守护者彻底拦截，让电脑清净又省心。',0,1,1,20),(19,'工具2',3,1,'很棒','解压后使用',0,0,0,23),(20,'工具5',3,1,'不错的工具','解压后使用哈哈',0,0,0,25);
/*!40000 ALTER TABLE `tool` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tool_file`
--

DROP TABLE IF EXISTS `tool_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tool_file` (
  `file-id` int(11) NOT NULL AUTO_INCREMENT,
  `tool-id` int(11) NOT NULL,
  `version` varchar(90) NOT NULL,
  `upload-time` int(11) NOT NULL,
  `file-position` text NOT NULL,
  `file-size` float NOT NULL,
  PRIMARY KEY (`file-id`),
  UNIQUE KEY `file-id_UNIQUE` (`file-id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tool_file`
--

LOCK TABLES `tool_file` WRITE;
/*!40000 ALTER TABLE `tool_file` DISABLE KEYS */;
INSERT INTO `tool_file` VALUES (1,1,'1.0beta',1494338565,'tools/网络流量监测器/1.0beta/networkScan.rar',1.1),(2,2,'1.0.0',1494340934,'tools/OllyDbg/1.0.0/OD.zip',22529),(3,3,'V1.0.0',1494500859,'tools/TraceMe/V1.0.0/TraceMe.rar',9),(4,4,'v717',1494502206,'tools/Advanced Codecs/v717/ADVANCED_Codecs_v717.exe',56192),(5,5,'V1.0.0',1494502345,'tools/DreamScene/V1.0.0/Windows 7 DreamScene Activator.exe',668),(6,6,'V1.0.0',1494502634,'tools/2号B型动态壁纸/V1.0.0/N.A.mp4.mpg',116651),(7,7,'V1.0.0',1494502738,'tools/汇编金手指/V1.0.0/汇编金手指.exe',272),(8,8,'V1.0.0',1494503270,'tools/microKMS/V1.0.0/microKMS.exe',336),(9,9,'v1.0',1494503305,'tools/github离线版客户端/v1.0/github离线版_3_0_5_2.zip',63300),(10,8,'V2.0.0',1494503308,'tools/microKMS/V2.0.0/KMSpico.7z',3115),(11,10,'5.1.30',1494503645,'tools/mysql-connector-java/5.1.30/mysql-connector-java-5.1.30.zip',3963),(12,11,'V1.0.0',1494503748,'tools/Navicat For MySQL/V1.0.0/Navicat For MySQL.zip',20487),(13,12,'V5.11.0',1494503862,'tools/devcpp/V5.11.0/devcpp_5.11.0.zip',49242),(14,13,'V1.0.0',1494505186,'tools/SublimeText3/V1.0.0/SublimeText3.zip',39864),(15,2,'V1.1.0',1494571426,'tools/OllyDbg/V1.1.0/OD V1.1.0.zip',22529),(16,14,'1.0',1494572412,'tools/好压/1.0/好压.rar',12372),(17,15,'2.0.3',1494572561,'tools/美图秀秀/2.0.3/美图秀秀.rar',37118),(18,16,'公测版',1494572783,'tools/YY语音/公测版/YY语音.rar',18559),(19,17,'V2.1.0',1494573048,'tools/电脑管家/V2.1.0/电脑管家.rar',30932),(20,18,'V2.3.3',1494573150,'tools/净网专家/V2.3.3/净网专家.rar',49491),(21,1,'V2.0',1494575208,'tools/网络流量监测器/V2.0/Chipset.rar',4464),(22,19,'v1.1',1494590209,'tools/工具2/v1.1/OD V1.1.0.zip',22529),(23,19,'v1.2',1494590288,'tools/工具2/v1.2/OD V1.1.0.zip',22529),(24,20,'v1.1',1494668227,'tools/工具5/v1.1/OD.zip',22529),(25,20,'V2.0',1494668327,'tools/工具5/V2.0/OD.zip',22529);
/*!40000 ALTER TABLE `tool_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tool_tag_relation`
--

DROP TABLE IF EXISTS `tool_tag_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tool_tag_relation` (
  `relation-id` int(11) NOT NULL AUTO_INCREMENT,
  `tool-id` int(11) NOT NULL,
  `tag-id` int(11) NOT NULL,
  PRIMARY KEY (`relation-id`),
  UNIQUE KEY `relation-id_UNIQUE` (`relation-id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tool_tag_relation`
--

LOCK TABLES `tool_tag_relation` WRITE;
/*!40000 ALTER TABLE `tool_tag_relation` DISABLE KEYS */;
INSERT INTO `tool_tag_relation` VALUES (1,1,1),(2,1,2),(3,2,3),(4,2,4),(5,2,5),(6,3,6),(7,3,7),(8,4,8),(9,5,8),(10,5,10),(11,6,11),(12,6,12),(13,7,13),(14,7,14),(15,8,15),(16,8,16),(17,8,17),(18,9,18),(19,9,19),(20,9,20),(21,10,21),(22,10,22),(23,11,22),(24,12,24),(25,12,25),(26,12,26),(27,13,27),(28,14,28),(29,14,29),(30,14,30),(31,15,5),(32,16,32),(33,16,33),(34,17,34),(35,18,35),(36,18,36),(37,19,37),(38,19,38),(39,20,39),(40,20,40);
/*!40000 ALTER TABLE `tool_tag_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(90) NOT NULL,
  `password` varchar(60) NOT NULL,
  `department` varchar(60) DEFAULT NULL,
  `sex` int(11) NOT NULL DEFAULT '0',
  `self-intro` text,
  `job` varchar(60) DEFAULT NULL,
  `rank` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','123456','管理部',1,'我才是最高管理员!','管理员',2),(2,'Rin','123123','开发部',1,'某程序员','程序员',0),(3,'BillChen','666666','测试部',2,'你见过这么帅的头像吗？没见过是吧，其实我也没见过。','部长',2),(4,'CoderLee','123456','董事会',0,'很帅，技术控。','董事长',2),(5,'superhh','123456',NULL,0,NULL,NULL,2),(6,'张三','123123','产品部',1,'大家好，我是张三',NULL,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zan`
--

DROP TABLE IF EXISTS `zan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zan` (
  `zan-id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `tool-id` int(11) NOT NULL,
  PRIMARY KEY (`uid`,`tool-id`),
  UNIQUE KEY `zan-id_UNIQUE` (`zan-id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zan`
--

LOCK TABLES `zan` WRITE;
/*!40000 ALTER TABLE `zan` DISABLE KEYS */;
INSERT INTO `zan` VALUES (10,3,4),(11,3,5),(12,3,6),(13,3,3),(14,3,1),(15,3,2),(16,3,7),(17,3,8),(22,4,9),(23,3,10),(24,3,11),(25,3,12),(27,3,13),(28,2,16),(29,5,17),(30,2,18),(31,4,10);
/*!40000 ALTER TABLE `zan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'nokia_tool_manager'
--

--
-- Dumping routines for database 'nokia_tool_manager'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-14 18:34:57
