# 网上订餐系统

使用 Java Web 技术实现的一个简单网上订餐系统。

用来作为网上购物系统编码时的参考系统。里面需要改进的地方有很多，所以只能参考，不能照搬！

---
<!-- vscode-markdown-toc -->
* 1. [仓库目录结构介绍](#)
* 2. [如何使用本库](#-1)

<!-- /vscode-markdown-toc -->

---
##  1. <a name=''></a>仓库目录结构介绍

```
|-- src
|   `-- com
|       `-- chenswe
|           |-- adminmanager
|           |   |-- action
|           |   |-- dao
|           |   |   `-- impl
|           |   |-- entity
|           |   `-- service
|           |       `-- impl
|           |-- filter
|           |-- ordermanager
|           |   |-- action
|           |   |-- dao
|           |   |   `-- impl
|           |   |-- entity
|           |   `-- service
|           |       `-- Impl
|           |-- sellermanager
|           |   |-- action
|           |   |-- dao
|           |   |   `-- impl
|           |   |-- entity
|           |   `-- service
|           |       `-- impl
|           |-- usermanager
|           |   |-- action
|           |   |-- dao
|           |   |   `-- impl
|           |   |-- entity
|           |   `-- service
|           |       `-- impl
|           `-- util
`-- WebContent
    |-- config
    |-- META-INF
    |-- pages
    |   |-- profiles
    |   |-- signin
    |   `-- signup
    |-- style
    |   |-- asignin
    |   |   `-- assets
    |   |       |-- css
    |   |       |-- font-awesome
    |   |       |   |-- css
    |   |       |   `-- fonts
    |   |       `-- js
    |   |-- bootstrap
    |   |   |-- css
    |   |   |-- fonts
    |   |   `-- js
    |   |-- css
    |   |-- images
    |   |   |-- backgrounds
    |   |   |-- default
    |   |   |-- foods
    |   |   |-- sellers
    |   |   `-- shopping_card
    |   |-- js
    |   |-- ssignin
    |   |   `-- assets
    |   |       |-- css
    |   |       |-- font-awesome
    |   |       |   |-- css
    |   |       |   `-- fonts
    |   |       `-- js
    |   `-- usignin
    |       `-- assets
    |           |-- css
    |           |-- font-awesome
    |           |   |-- css
    |           |   `-- fonts
    |           `-- js
    `-- WEB-INF
        `-- lib

```

- `src` 目录下存放后台 Java 源码。
    - `adminmanager` 目录下存放管理员相关代码。
    - `ordermanager` 目录下存放订单相关代码。
    - `sellermanager` 目录下存放商家相关代码。
    - `usermanager`  目录下存放用户相关代码。
    - `filter` 目录下存放过滤器相关代码。
    - `util` 目录下存放工具类
        - `action` 目录下存放控制层 Sevlet。
        - `service` 目录下存放服务，由 action 层（控制层）直接调用。
        - `dao` 目录下存放直接读写数据库的数据库持久层，由 service 层（服务层）调用。
        - `entity` 目录下存放实体，用于在后台传递数据。

- `WebContent` 目录下存放 `JSP`、`CSS`、`JavaScript` 及用到的各种 `jar` 文件

##  2. <a name='-1'></a>如何使用本库

**只用作参考**，遇到问题可以参考该库中的解决方式。
