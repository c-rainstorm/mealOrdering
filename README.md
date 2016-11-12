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
│  .gitignore
│  LICENSE
│  README.md
│  
├─src
│  └─com
│      └─chenswe
│          ├─adminmanager
│          │  ├─action
│          │  │      AdminLoginAction.java
│          │  │      AdminLogoutAction.java
│          │  │      
│          │  ├─dao
│          │  │  │  AdminDAO.java
│          │  │  │  
│          │  │  └─impl
│          │  │          AdminDAOImpl.java
│          │  │          
│          │  ├─entity
│          │  │      Admin.java
│          │  │      
│          │  └─service
│          │      │  AdminService.java
│          │      │  
│          │      └─impl
│          │              AdminServiceImpl.java
│          │              
│          ├─filter
│          │      Character.java
│          │      CheckLogin.java
│          │      SellerFore.java
│          │      
│          ├─ordermanager
│          │  ├─action
│          │  │      FinishOrderAction.java
│          │  │      GetOrderDetailAction.java
│          │  │      GetSellerOrderBriefAction.java
│          │  │      SubmitOrderAction.java
│          │  │      UpdateOrderStatusAction.java
│          │  │      
│          │  ├─dao
│          │  │  │  OrderDAO.java
│          │  │  │  
│          │  │  └─impl
│          │  │          OrderDAOImpl.java
│          │  │          
│          │  ├─entity
│          │  │      FoodInOrder.java
│          │  │      Order.java
│          │  │      OrderBrief.java
│          │  │      ShoppingCars.java
│          │  │      
│          │  └─service
│          │      │  OrderService.java
│          │      │  
│          │      └─Impl
│          │              OrderServiceImpl.java
│          │              
│          ├─sellermanager
│          │  ├─action
│          │  │      AddNewFoodAction.java
│          │  │      ChangeFoodStatusAction.java
│          │  │      ChangeStoreStatusAction.java
│          │  │      CheckRegistrationIDAction.java
│          │  │      CheckSellerPhoneAction.java
│          │  │      DeleteFoodAction.java
│          │  │      GetFoodBriefAction.java
│          │  │      GetFoodsAction.java
│          │  │      GetFoodsBriefAction.java
│          │  │      GetSellerBriefAction.java
│          │  │      GetSellerDetailAction.java
│          │  │      GetSellerForeAction.java
│          │  │      GetShoppingCarsAction.java
│          │  │      GetStoreStatusAction.java
│          │  │      ModifyFoodInfoAction.java
│          │  │      SellerLoginAction.java
│          │  │      SellerLogoutAction.java
│          │  │      SellerSignupAction.java
│          │  │      SetShoppingCarsAction.java
│          │  │      UpdateSellerInfoAction.java
│          │  │      
│          │  ├─dao
│          │  │  │  sellerDAO.java
│          │  │  │  
│          │  │  └─impl
│          │  │          SellerDAOImpl.java
│          │  │          
│          │  ├─entity
│          │  │      Food.java
│          │  │      Seller.java
│          │  │      SellerBrief.java
│          │  │      
│          │  └─service
│          │      │  SellerService.java
│          │      │  
│          │      └─impl
│          │              SellerServiceImpl.java
│          │              
│          ├─usermanager
│          │  ├─action
│          │  │      AddNewReceiverAction.java
│          │  │      CheckPhoneAction.java
│          │  │      CheckReceiverPhoneAction.java
│          │  │      CheckUsernameAction.java
│          │  │      GetReceiverInfoAction.java
│          │  │      GetUserInfoAction.java
│          │  │      GetUserOrderBriefAction.java
│          │  │      ReceiverSetDefaultAction.java
│          │  │      RemoveReceiverAction.java
│          │  │      UpdateReceiverInfoAction.java
│          │  │      UpdateUserPasswordAction.java
│          │  │      UserLoginAction.java
│          │  │      UserLogoutAction.java
│          │  │      UserSignupAction.java
│          │  │      
│          │  ├─dao
│          │  │  │  UserDAO.java
│          │  │  │  
│          │  │  └─impl
│          │  │          UserDAOImpl.java
│          │  │          
│          │  ├─entity
│          │  │      Receiver.java
│          │  │      User.java
│          │  │      
│          │  └─service
│          │      │  UserService.java
│          │      │  
│          │      └─impl
│          │              UserServiceImpl.java
│          │              
│          └─util
│                  DBConfig.java
│                  DBUtil.java
│                  StringUtil.java
│                  
└─WebContent
    │  index.jsp
    │  
    ├─config
    │      mysql.xml
    │      
    ├─META-INF
    │      MANIFEST.MF
    │      
    ├─pages
    │  ├─profiles
    │  │      admin.jsp
    │  │      confirm_order.jsp
    │  │      home.jsp
    │  │      seller_behind.jsp
    │  │      seller_fore.jsp
    │  │      user.jsp
    │  │      
    │  ├─signin
    │  │      admin.jsp
    │  │      seller.jsp
    │  │      user.jsp
    │  │      
    │  └─signup
    │          seller.jsp
    │          user.jsp
    │          
    ├─style
    │  ├─asignin
    │  ├─ssignin
    │  ├─usignin
    │  │              
    │  ├─bootstrap
    │  │          
    │  ├─css
    │  │      add_to_card_reset.css
    │  │      add_to_card_style.css
    │  │      carousel.css
    │  │      cover.css
    │  │      dashboard.css
    │  │      form-elements.css
    │  │      signup.css
    │  │      sticky-footer.css
    │  │      summary.css
    │  │      
    │  ├─images
    │  │  ├─backgrounds
    │  │  │      1.jpg
    │  │  │      1@2x.jpg
    │  │  │      2.jpg
    │  │  │      2@2x.jpg
    │  │  │      3.jpg
    │  │  │      3@2x.jpg
    │  │  │      
    │  │  ├─default
    │  │  │      icon.png
    │  │  │      
    │  │  ├─foods
    │  │  │      151.jpg
    │  │  │      152.jpg
    │  │  │      1526.jpg
    │  │  │      default.jpg
    │  │  │      
    │  │  ├─sellers
    │  │  │      default.jpg
    │  │  │      
    │  │  └─shopping_card
    │  │          cd-icon-arrow-next.svg
    │  │          cd-icon-select.svg
    │  │          cd-icons-cart-close.svg
    │  │          product-preview.png
    │  │          
    │  └─js
    │  │      confirmOrderBackground.js
    │  │      homeBackground.js
    │  │      jquery-3.1.0.js
    │  │      jquery.min.js
    │  │      public.js
    │  │      sellerBehindBackground.js
    │  │      sellerForeBackground.js
    │  │      sellerSignup.js
    │  │      shopping_card.js
    │  │      signup.js
    │  │      url.min.js
    │  │      userBackground.js
    │  │      userSignup.js
    │  │      

    │                  
    └─WEB-INF
        │  web.xml
        │  
        └─lib
                commons-fileupload-1.3.1.jar
                commons-io-2.4.jar
                druid-1.0.18-sources.jar
                druid-1.0.18.jar
                gson-2.6.2.jar
                jdom-2.0.6.jar
                jstl-1.2.jar
                junit-4.10.jar
                mysql-connector-java-5.1.38-bin.jar
                servlet-api.jar
                standard-1.1.2.jar
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
