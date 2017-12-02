#import database mysql
db_distribute_ticket.sql

#thay đổi đường dẫn kết nối database trong file conf.properties
key=value

str=jdbc:mysql://localhost:3306/db_distribute_ticket?useUnicode=true&characterEncoding=utf-8

user=root

pass=

//Cách 1
#run ==> mặc định 4 client. 
java -jar run.jar 4
//Cach 2
#chay file database ==> tạo 1 server
java -jar Database.jar
#chạy file client ==> 1 client mới
java -jar Server.jar



