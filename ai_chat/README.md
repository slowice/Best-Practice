# ai_chat

AI chat 学习模块。

## 学习目标

- 搭建一个独立的聊天服务模块。
- 预留后续接入大模型 API、本地模型或 Spring AI 的边界。
- 练习请求 DTO、响应 DTO、Controller、Service 的分层组织。

## 运行方式

```bash
mvn -Pai -pl ai_chat spring-boot:run
```

## 当前接口

```text
GET /ai-chat/health
POST /ai-chat/message
```

`POST /ai-chat/message` 当前只返回一个占位回复，后续可以在 service 层替换为真实模型调用。

## 后续 TODO

- 增加模型 provider 抽象。
- 增加本地配置示例，例如 `application-example.yml`。
- 增加会话上下文管理。
- 增加流式响应示例。
