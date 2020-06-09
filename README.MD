# 主从多数据源mysql配置demo
## 一、框架说明
> 本demo实现Spring Boot集成Mybatis并进行主从库分离

> 本项目延续之前的Springboot集成mybatis的自动生成框架


## 二、框架结构
 1. demo采用一主二从的mysql读写分离模式
 2. 原理：
    - 实现方式主要是通过扩展Spring AbstractRoutingDataSource
    - 这个类充当DataSource路由中介，在运行当中根据自定义KEY值动态切换正真的DataSource
 3. 实现步骤：
    - yml配置文件配置一主二从数据库信息
    - DataSourceConfig配置类中配置对应数据源masterDataSource、slaveDataSource1、slaveDataSource2
    - 使用自定义DynamicDataSource类实现动态数据源切换，并配置dynamicDataSource的Bean
    - 在DynamicDataSource中重写AbstractRoutingDataSource的determineCurrentLookupKey方法，获取动态数据源的枚举
    - DBContextHolder类中，主要是用于记录当前线程操作的是需要操作主库还是从库，核心是通过 ThreadLocal 记录，对于多从情况，采用轮询方式访问从库
    - 采用aop技术，根据mapper接口方法的名称定义读、写切点，获取对应的主 或 从库