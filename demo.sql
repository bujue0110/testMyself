/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-04-24 21:45:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `answer`
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `userid` int(16) NOT NULL,
  `paper_id` int(16) NOT NULL,
  `student_answer` varchar(2000) DEFAULT NULL,
  `score` int(3) DEFAULT NULL,
  `wrong_list` varchar(128) DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL,
  `marked` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`,`paper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES ('2', '1', '第1题:A;第2题:A;第3题:C;第4题:SSS;', null, null, null, null);
INSERT INTO `answer` VALUES ('2', '2', 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX', null, null, null, '未批阅');

-- ----------------------------
-- Table structure for `favorite`
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `userid` int(16) NOT NULL,
  `subject_id` int(16) NOT NULL,
  `timestamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`userid`,`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES ('2', '6', '2017-04-20 22:15:39.264000');

-- ----------------------------
-- Table structure for `paper`
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `paper_id` int(5) NOT NULL AUTO_INCREMENT,
  `paper_name` varchar(255) DEFAULT NULL,
  `subject_list` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`paper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES ('1', '第一套试卷', '1;2;4;5;');
INSERT INTO `paper` VALUES ('2', '1', '1;');
INSERT INTO `paper` VALUES ('7', '第二', '1;2');

-- ----------------------------
-- Table structure for `subject`
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `subject_id` int(5) NOT NULL AUTO_INCREMENT,
  `type_id` int(2) DEFAULT NULL,
  `content` varchar(5000) NOT NULL,
  `answer` varchar(1000) DEFAULT NULL,
  `analysis` varchar(1000) DEFAULT NULL,
  `a_item` varchar(1000) DEFAULT NULL,
  `b_item` varchar(1000) DEFAULT NULL,
  `c_item` varchar(1000) DEFAULT NULL,
  `d_item` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('1', '1', 'volatile关键字的说法错误的是', 'A', '出于运行速率的考虑，java编译器会把经常经常访问的变量放到缓存（严格讲应该是工作内存）中，读取变量则从缓存中读。但是在多线程编程中,内存中的值和缓存中的值可能会出现不一致。volatile用于限定变量只能从内存中读取，保证对所有线程而言，值都是一致的。但是volatile不能保证原子性，也就不能保证线程安全。', '能保证线程安全', 'volatile关键字用在多线程同步中，可保证读取的可见性', 'JVM保证从主内存加载到线程工作内存的值是最新的', 'volatile能禁止进行指令重排序');
INSERT INTO `subject` VALUES ('2', '1', '下面有关java final的基本规则，描述错误的是？', 'B', 'final修饰的成员变量为基本数据类型是，在赋值之后无法改变。当final修饰的成员变量为引用数据类型时，在赋值后其指向地址无法改变，但是对象内容还是可以改变的。\r\nfinal修饰的成员变量在赋值时可以有三种方式。1、在声明时直接赋值。2、在构造器中赋值。3、在初始代码块中进行赋值。', 'final修饰的类不能被继承', 'final修饰的成员变量只允许赋值一次，且只能在类方法赋值', 'final修饰的局部变量即为常量，只能赋值一次。', 'final修饰的方法不允许被子类覆盖');
INSERT INTO `subject` VALUES ('3', '1', '如何跳出Array的forEach循环？（ ）', 'A', 'BC项可以跳出一个返回值为boolean类型的函数里面的forEach。', 'break', 'return false', 'return true', '以上都不是');
INSERT INTO `subject` VALUES ('4', '4', '<p><b>PYTHON</b></p><pre><code class=\"lang-python\">sss</code></pre><p><a href=\"http://www.example.com\" target=\"_blank\">链接文字</a><br></p>', 'A', '呼呼哈哈', 'AAA', 'BBB', 'CCC', 'DDD');
INSERT INTO `subject` VALUES ('5', '1', '下列关于构造函数的描述中，错误的是（）', 'D', '构造方法可以更具参数列表的不同实现重载', '构造函数可以设置默认的参数', '构造函数在定义类对象时自动执行', '构造函数可以是内联函数', '构造函数不可以重载');
INSERT INTO `subject` VALUES ('6', '1', '以下叙述正确的是\r\n\r\n以下叙述正确的是', 'D', 'A错误，类的实例方法是与该类的实例对象相关联的，不能直接调用，只能通过创建超类的一个实例对象，再进行调用\r\nB错误，当父类的类方法定义为private时，对子类是不可见的，所以子类无法调用\r\nC错误，子类具体的实例方法对父类是不可见的，所以无法直接调用， 只能通过创建子类的一个实例对象，再进行调用\r\nD正确，实例方法可以调用自己类中的实例方法', '实例方法可直接调用超类的实例方法', '实例方法可直接调用超类的类方法', '实例方法可直接调用子类的实例方法', '实例方法可直接调用本类的实例方法');

-- ----------------------------
-- Table structure for `subject_type`
-- ----------------------------
DROP TABLE IF EXISTS `subject_type`;
CREATE TABLE `subject_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject_type
-- ----------------------------
INSERT INTO `subject_type` VALUES ('1', 'JAVA');
INSERT INTO `subject_type` VALUES ('2', 'C');
INSERT INTO `subject_type` VALUES ('3', 'JavaScript');
INSERT INTO `subject_type` VALUES ('4', 'Python');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `interest` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `age` smallint(3) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'ROLE_ADMIN', null, null, null, null);
INSERT INTO `user` VALUES ('2', 'user', 'user', 'ROLE_USER', null, null, null, null);
INSERT INTO `user` VALUES ('3', 'teacher', 'teacher', 'ROLE_TEACHER', null, null, null, null);
