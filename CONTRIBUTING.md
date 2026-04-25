# Contributing

欢迎把这个仓库当成真实工作模拟场来练习。贡献不需要“很高级”，但需要像工作提交一样可复现、可评审、可验收。

## 推荐流程

1. 先选择一个关卡文档，例如 `docs/levels/level-04.md`。
2. 在 issue 或本地笔记里写清楚需求、影响范围和验收方式。
3. 如果是代码需求，先补测试或复现步骤。
4. 保持一次 PR 只解决一个清晰问题。
5. 提交信息使用 Conventional Commits，例如 `feat(product): add admin catalogue listing`。

## 代码提交标准

- API 返回结构保持 `ApiResponse` 风格。
- 分页返回保持 `PageResult` 风格。
- 用户端接口不要泄露后台草稿、逻辑删除数据或敏感信息。
- 新增后端能力时至少补一个 MockMvc 测试或单元测试。
- 文档任务需要有 checklist，说明怎样算完成。

## 本地验证

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-21'
.\mvnw.cmd test
```

## PR 描述建议

- 这次改了什么。
- 为什么要改。
- 怎么验证。
- 有没有后续遗留问题。
