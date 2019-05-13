# TSS-ACCOUNT

| 作者 |  版本 | 日期 | 说明 |
| ------ | ---- | ----- | ------ |
| 莫庆根 | V1.0 |  2019-05-13 | 初始化 |

## 说明

### 本地环境

1、启动
- Application类下运行main方法即可

2、健康检查
- http://localhost:8080/account/health

3、备注

本项目依赖于以下服务：
- eureka-server（8090）

### 服务器环境

1、打包
- 插件方式：Maven->eureka-server->Lifecycle->package命令
- 命令方式：本地打成jar包：`mvn clean package`

2、部署
- 上传至服务器www.njittss.top服务器：/opt/tss/account目录
- 后台启动学生服务，指定使用prod配置文件：`nohup java -jar account-1.0.0.jar &`

3、健康检查
- curl http://localhost:8080/account/health

5、备注

- 本项目依赖于以下服务：
   - eureka-server（8090）
- 本项目是内部服务，不对外暴露，所以无需配置nginx   
