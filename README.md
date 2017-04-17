# lab
使用summernote富文本编辑器实现实验室赛事发布功能，后台由Java实现
## mysql数据库数据表
```sql
#Host: localhost  (Version: 5.5.53)
#Date: 2017-03-25 17:00:21
#Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
#Structure for table "libmatch"
#

DROP TABLE IF EXISTS `libmatch`;
CREATE TABLE `libmatch` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `content` text,
  `filePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
```
## 实验室发布系统富文本编辑器截图
![image](https://github.com/say-hello-user/lab/blob/master/image/title.png)
## 实验室发布系统整体截图
![image](https://github.com/say-hello-user/lab/blob/master/image/main.png)
## 实验室发布系统预览截图
### 预览的URL地址为：http://127.0.0.1:8080/lab/servlet/ListFileServlet?id=29 id为发布文章的id
![image](https://github.com/say-hello-user/lab/blob/master/image/look.png)
