参考iCloud云盘项目,地址：https://github.com/martin-wong/iCloud

将iCloud项目重新编写，不使用框架 

文件路径
项目路径+userFile+用户名
存在的问题：
1.数据库连接失败也能正常跳转到myfiles.jsp(已修改)


下一步改进：
1.文件上传改为可以上传多个（目前为单个）


思路：
1.文件校验，使用文件md5确保唯一性：文件名相同，内容不同（不同文件）；文件名不同，内容相同（相同文件）
2.file：fileName指向文件上传时的名字，filepath：文件路径+生成的随机名
3.搜索时，直接搜索文件名，即fileName
4.还存在一个问题，两个内容相同的文件，由不同用户上传，文件名不同：
    解决办法：把现有file表中fileName存储到file_user表中,原有file表中fileName存储随机名，filepath不变