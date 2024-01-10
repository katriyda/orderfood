# 在线订餐系统
## 项目简介
在线订餐系统是基于 Springboot 的 Web 应用，主要实现了管理员登录、菜品管理、订单管理、用户下单等功能。
## 项目说明
项目做得很随意，代码写得很乱，有很多不规范的地方，为了能跑就行。前端并没有上传。
## 项目运行
1. 先在数据库中创建一个名为 `online_order` 的数据库，然后将 `src/main/resources/sql` 目录下的数据库的 `online_order` 表导入到数据库中。
2. 修改 `application.yml` 文件中的数据库连接信息，将 `username`、`password`、`url`、`port`、`database` 改为自己的数据库连接信息。
3. 修改 `application.yml` 文件中的 `path` 配置信息，将 `path` 根据自己的项目需要修改路径。
4. maven 构建项目，执行 `mvn clean package` 命令。
5. 使用 Java -jar 启动项目

## 运行效果
![](https://img.katr.tk/2024/01/fc871ec7b62486f4bb46f1541c9d8e8d.png)
![](https://img.katr.tk/2024/01/bfed1228c43be68cbccfa63ab48129ac.png)