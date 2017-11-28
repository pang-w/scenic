1. eclipse 导出 可运行 oa.jar
2. 更改文件目录
	2.1 resources文件夹下的所有文件（除mappers文件夹）移动到父文件夹下
	2.2 复制webappp文件夹及文件到 oa.jar内部的根目录下
	2.3 删除application.properties文件，把application-win.properties更改为application.properties
	2.4 复制resources/mappers文件夹及文件到 oa.jar同级目录下
3. 复制jre
4. 复制mariadb
5. 运行脚本
.\mariadb\bin\mysqld.exe
.\jre\bin\java -jar oa.jar
6 访问测试