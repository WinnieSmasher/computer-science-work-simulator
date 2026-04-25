# NovaMart API 清单

当前 API 采用统一返回结构：

```json
{
  "code": "OK",
  "message": "success",
  "data": {}
}
```

## 商品标签

### 创建标签

```http
POST /api/admin/tags
```

```json
{
  "name": "Fresh Picks",
  "slug": "fresh-picks",
  "description": "Freshly promoted catalogue items"
}
```

### 标签列表

```http
GET /api/admin/tags
```

## 后台商品

### 创建商品

```http
POST /api/admin/products
```

```json
{
  "name": "Lunar Notebook",
  "description": "Notebook for space science field notes",
  "priceCents": 1299,
  "status": "ACTIVE",
  "tagIds": []
}
```

### 商品详情

```http
GET /api/admin/products/{id}
```

### 后台商品列表

```http
GET /api/admin/products?keyword=Lunar&includeDeleted=false&page=1&size=20
```

用途：

- 后台排查商品是否存在。
- 看到 `DRAFT` 草稿商品。
- 默认不展示逻辑删除数据。
- 设置 `includeDeleted=true` 时可用于排查误删、脏数据和搜索不可见问题。

### 更新商品

```http
PUT /api/admin/products/{id}
```

请求体与创建商品一致。

### 逻辑删除商品

```http
DELETE /api/admin/products/{id}
```

## 用户端商品

### 商品搜索

```http
GET /api/products?keyword=Lunar&page=1&size=20
```

约束：

- 只返回 `ACTIVE` 商品。
- 不返回逻辑删除商品。
- keyword 会做首尾空格清理和大小写归一。
- 返回 `hasMore/isEnd` 方便用户端列表判断是否继续加载。
