# Computer Science Work Simulator

一个公开的计算机科学工作模拟课程仓库。

这个项目不是刷题仓库，也不是某个培训材料的复刻。它把新人进入真实工程团队后会遇到的工作拆成 27 个关卡，用一个原创业务系统 `NovaMart` 串起来：商品、标签、用户端列表、订单、库存、支付、通知、缓存、消息队列、部署、文档和复杂方案阅读。

## NovaMart

`NovaMart` 是一个模拟大型电商系统的教学项目，业务主线参考 Google Cloud Online Boutique、Microsoft eShop 和 Backstage 的公开工程思想，但所有代码和课程文档都是原创。

第一版采用 Java 17 + Spring Boot 3 的模块化单体，后续逐步演进到中间件和微服务。

## 学习方式

每一关都包含：

- 业务背景：你在团队里接到的真实感需求
- 工作能力：这一关训练什么工程能力
- 任务说明：需要设计、编码、测试或写文档的内容
- 验收标准：什么样算完成
- 名词解释：相关工程术语

推荐学习顺序：

1. 阅读 `docs/roadmap.md`。
2. 从 `docs/levels/level-01.md` 开始理解业务。
3. 运行现有 API 和测试。
4. 每次只完成一个关卡，像工作迭代一样提交代码。

## 工程 Playbooks

- [调试与排障 playbook](docs/playbooks/debugging.md)：把“线上反馈一个 bug”拆成复现、观察、定位、修复和回归测试。

## 当前进度

- Level 01-03 已有基础文档、商品/标签 API、MockMvc 测试。
- Level 04 已补充调试任务和排障 playbook。
- Level 05-27 已有任务书，后续逐关补代码。

## 本地运行

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-21'
.\mvnw.cmd test
.\mvnw.cmd -pl services/novamart-api spring-boot:run
```

启动基础设施：

```powershell
docker compose -f platform/docker-compose.yml up -d
```

示例接口：

```bash
curl -X POST http://localhost:8080/api/admin/tags \
  -H "Content-Type: application/json" \
  -d '{"name":"Fresh Picks","slug":"fresh-picks","description":"Freshly promoted catalogue items"}'

curl -X POST http://localhost:8080/api/admin/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Lunar Notebook","description":"Notebook for space science field notes","priceCents":1299,"status":"ACTIVE","tagIds":[]}'

curl "http://localhost:8080/api/products?keyword=Lunar&page=1&size=20"
```

## References

见 `docs/references.md`。
