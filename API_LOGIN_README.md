# 登录 API 使用说明

## 接口信息

- **接口名称**: 用户登录接口
- **请求路径**: `/api/login`
- **请求方式**: `POST`
- **数据格式**: `application/json`

## 请求参数

| 参数名 | 类型 | 是否必填 | 说明 |
|--------|------|----------|------|
| account | String | 是 | 用户账号 |
| password | String | 是 | 用户密码 |

### 请求示例

```json
{
  "account": "admin",
  "password": "123456"
}
```

## 返回格式

统一返回 JSON 结构:

```json
{
  "state": Boolean,   // 是否成功
  "msg": String,      // 提示信息
  "data": Object      // 返回数据（成功为 UUID，失败为 null）
}
```

## 返回情况

### 1. 未输入账号
```json
{
  "state": false,
  "msg": "请输入账号",
  "data": null
}
```

### 2. 未输入密码
```json
{
  "state": false,
  "msg": "请输入密码",
  "data": null
}
```

### 3. 账号不存在
```json
{
  "state": false,
  "msg": "登录失败，账号不存在",
  "data": null
}
```

### 4. 密码错误
```json
{
  "state": false,
  "msg": "登录失败，密码错误",
  "data": null
}
```

### 5. 登录成功
```json
{
  "state": true,
  "msg": "登录成功",
  "data": "550e8400-e29b-41d4-a716-446655440000"
}
```

## 测试账号

数据库初始化后会创建以下测试账号:

| 账号 | 密码 |
|------|------|
| admin | 123456 |
| test | test123 |

## 使用步骤

### 1. 初始化数据库

执行 `database/init.sql` 脚本创建数据库和表:

```bash
mysql -u root -p < database/init.sql
```

### 2. 配置数据库连接

修改 `src/main/resources/application.properties` 中的数据库配置:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/laboratory?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8
spring.datasource.username=root
spring.datasource.password=你的密码
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 3. 启动项目

```bash
mvn spring-boot:run
```

### 4. 测试接口

使用 curl 测试:

```bash
curl -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{"account":"admin","password":"123456"}'
```

或使用 Postman 等工具测试。

## 技术栈

- Spring Boot 4.0.3
- MyBatis 4.0.1
- MySQL
- Lombok
