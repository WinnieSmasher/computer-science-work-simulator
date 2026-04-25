# Level 02 - 模仿团队接口风格

## 业务背景

运营需要给商品加标签，例如 `fresh-picks`、`research-tools`、`limited-sale`。团队已有统一响应格式和后台接口前缀。

## 工作能力

模仿已有接口、数据库字段、返回结构和错误风格。

## 任务

- 新增后台标签创建接口：`POST /api/admin/tags`。
- 新增后台标签列表接口：`GET /api/admin/tags`。
- 返回统一 API envelope：`code`、`message`、`data`。

## 验收标准

- 标签 slug 只能使用小写字母、数字和连字符。
- 重复 slug 要返回明确业务错误。
- MockMvc 测试覆盖创建和列表查询。

