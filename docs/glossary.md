# Glossary

- API envelope：统一响应外壳，例如 `{ code, message, data }`。
- DTO：Data Transfer Object，请求或响应传输对象。
- VO：View Object，面向前端展示的对象。
- CRUD：Create、Read、Update、Delete，增删改查。
- Logical delete：逻辑删除，用字段标记删除，不立即物理删除数据库记录。
- C 端：Consumer 端，面向普通用户的产品端。
- B 端：Business 端，面向商家、运营、后台人员的系统端。
- PRD：Product Requirement Document，产品需求文档。
- Mock：模拟服务或数据，用于并行开发和测试。
- Dirty data：历史遗留或不符合当前规则的数据。
- Pagination：分页，常见字段包括 `page`、`size`、`total`、`hasMore`、`isEnd`。
- Request lifecycle：请求从客户端进入网关、应用、数据库，再返回响应的完整链路。
- JWT：JSON Web Token，常见无状态登录凭证。
- Session：服务端保存登录态的会话机制。
- Filter/Interceptor：请求进入业务代码前后的拦截处理。
- Transaction：事务，保证一组数据库操作的原子性。
- ACID：事务的原子性、一致性、隔离性、持久性。
- Redis：常用缓存和数据结构中间件。
- MQ：Message Queue，消息队列，用于异步、削峰和解耦。
- Idempotency：幂等，同一请求重复执行不会产生额外副作用。
- Eventual consistency：最终一致性，分布式系统中常见的数据一致性模型。
- CI/CD：持续集成和持续部署。
- Docker Compose：本地编排多个容器的工具。
- Service boundary：服务边界，决定哪些能力属于同一个模块或服务。
- Over-engineering：过度设计，使用超出当前问题所需的复杂方案。

