# Level 04 - 工具、调试和日志

## 业务背景

运营反馈商品搜索偶尔搜不到结果。你接到的不是“重写搜索”，而是定位问题。

典型反馈：

- “后台明明创建了商品，用户端搜索不到。”
- “输入 `Lunar` 有结果，输入 ` lunar ` 或 `LUNAR` 时结果不稳定。”
- “删除过的商品偶尔还会被同学误以为能搜到。”

## 工作能力

快速使用 IDE、debug、日志、断点和报错信息定位问题。

## 任务

- 先写一个能复现搜索问题的 MockMvc 回归测试。
- 用 debug 跟踪 `ProductController -> ProductService -> ProductRepository`。
- 记录请求参数、规范化后的 keyword、命中的商品数量。
- 区分用户端搜索和后台排查：用户端只看未删除且已上架商品，后台排查可以看到草稿和已删除商品。
- 增加必要日志时避免打印手机号、邮箱、token、cookie 等敏感信息。

## 验收标准

- 能复现 bug。
- 能写出定位路径和根因。
- 修复后增加回归测试。
- 能说明为什么用户端搜索不应该返回 `DRAFT` 或已删除商品。
- 能用后台排查接口确认“数据存在但前台不可见”的原因。

## 操作提示

1. 从失败测试开始，不要直接猜。
2. 在 Controller 断点看原始参数。
3. 在 Service 断点看 keyword 是否 `trim` 和 `toLowerCase`。
4. 在 Repository 或过滤逻辑处看 `deleted/status/name/description`。
5. 修复后把复现路径写进测试名。

更多流程见 [调试与排障 playbook](../playbooks/debugging.md)。
