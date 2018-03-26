create database iCloud;
use iCloud;

-- 用于存放用户数据
create table user(
  id int primary key auto_increment,
  uaerName varchar(10),
  password varchar(20)
);
-- 用于存放文件数据
create table files(
  id int primary key auto_increment,
  fileName varchar(100),
  fileMD5 varchar(500),
  path varchar(200)
);
create table file_user(
  fileId int,
  userName varchar(10)
);
