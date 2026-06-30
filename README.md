# Best-Practice

这是一个早期积累的 Java 后端学习仓，包含 Java 基础、算法、并发、Spring、CRUD、持久化、中间件、微服务等多个方向的 demo。

当前仓库更像一个“学习代码集合”。后续优化目标不是一次性大重构，而是逐步把它整理成一个可检索、可运行、可维护的学习地图。

## 后续架构优化路线图

### 1. 调整父 POM 职责

状态：已初步完成。根 POM 已移除公共依赖继承，改为通过 `dependencyManagement` 和 `pluginManagement` 管理版本；子模块按需显式声明依赖。

当前根 `pom.xml` 同时承担了聚合模块、版本管理、公共依赖继承等职责。建议后续逐步改成：

- 根 POM 主要保留 `<modules>`、`<dependencyManagement>`、`<pluginManagement>`。
- 不在根 POM 的 `<dependencies>` 中直接放 `spring-boot-starter-web`、AOP、fastjson 等业务依赖。
- 子模块按需声明自己的依赖，避免算法、Java 基础、并发 demo 被动继承 Spring Web 相关依赖。
- 统一 Maven Compiler、Surefire、Spring Boot 插件等构建插件版本。

### 2. 按学习主题拆分模块边界

状态：已初步完成。暂不移动目录，先通过 Maven profiles 按主题分组：`all`、`fundamentals`、`spring`、`middleware`、`cloud`。

当前所有模块都挂在根聚合下，整体构建容易被某个依赖外部服务的 demo 拖住。建议后续按主题分组：

```text
fundamentals/
  java_basis
  algorithm_study
  concurrency_demo

spring/
  spring_demo
  simple_crud
  complex_crud
  exception_handler_demo

middleware/
  redis_demo
  message_demo
  elk_demo
  persistence

cloud/
  spring_cloud
  nacos
  gateway_demo

shared/
  common_facade
  common_utils
```

也可以先不移动目录，只在根 POM 里增加 Maven profiles，例如 `fundamentals`、`spring`、`middleware`、`cloud`，让不同主题可以独立构建。

### 3. 统一技术版本线

仓库里存在 Spring Boot 2.x 和 Spring Boot 3.x 并存的情况。建议后续明确两条路线之一：

- 保守路线：主仓保持 Java 8 + Spring Boot 2.3.x，适合保留老 demo。
- 现代化路线：新增独立区域使用 Java 17 + Spring Boot 3.x，逐步迁移新 demo。

不要让 Spring Boot 3.x 模块直接继承 Spring Boot 2.x 的父 POM，避免 `javax.*` / `jakarta.*`、BOM、插件版本混乱。

### 4. 重新划分 common 模块职责

当前 `common_facade` 和 `common_utils` 职责有些混杂。建议后续拆清楚：

```text
shared-model
  DTO / VO / Result / 演示实体

shared-utils
  StringUtils / DateHelper / FileUtil / HttpUtil

shared-logging
  logback 配置、日志 filter、日志 demo
```

原则：

- 工具模块尽量不依赖业务模型。
- facade/model 模块尽量只放数据结构和接口契约。
- 日志、JPA、MyBatis 等框架相关内容不要混入通用 facade。

### 5. 为每个 demo 补充最小说明

每个模块建议补一个简短 README，方便以后回看：

```text
# 模块名

学习目标：
运行方式：
依赖服务：
关键类：
踩坑记录：
```

根 README 后续可以作为总索引，按主题链接到各模块。

### 6. 清理构建产物和本地文件

建议后续检查并清理：

- `target/`、`build/` 等构建产物。
- 本地 IDE 配置。
- 第三方 zip、jar、临时文档等不适合直接入库的文件。
- 重复的子模块 `.gitignore` 配置。

根 `.gitignore` 尽量统一管理常见忽略规则。

### 7. 隔离本地配置和敏感信息

部分 demo 可能包含本地 Redis、MySQL、Jenkins、Nacos 等配置。建议后续统一改成：

```text
application-example.yml
application-local.yml
```

其中：

- `application-example.yml` 入库，说明需要哪些配置项。
- `application-local.yml` 加入 `.gitignore`，放本机真实配置。
- 代码中避免硬编码 token、密码、IP、端口等环境信息。

## 推荐执行顺序

1. 先整理根 POM，让父 POM 只做版本和插件管理。
2. 补充根 README 的模块索引。
3. 给每个模块补最小 README。
4. 增加 Maven profiles，按主题独立构建。
5. 清理 common 模块职责。
6. 决定是否新增 Java 17 + Spring Boot 3.x 的现代化学习区。
7. 清理构建产物、本地配置和敏感信息。
