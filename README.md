# laa-im
# Http服务方面

## 认证。也就是登录
使用shiro + jwt的形式。
使用jwt即代表服务端无状态。认证流程如下：
用户登录接口不拦截，用户登录以后jwt会用密钥（在服务端存储）把用户名加密在token 中返回给客户端。之后客户端每次访问拦截的接口时都需要带上token，
服务端根据密钥解密得到用户名，然后查询数据库查看时候存在。存在放行。

## 用户注册
简单的用户注册。（或者邮件注册，或者短信注册。）

##用户头像以及其他所有文件上传类的方式统一抽象在存储服务中。（上传文件返回文件url。其他地方存url）
1存储服务使用腾讯云对象存储
2.自己实现存储，放在服务器本地。

## 好友管理
## 群组管理

## 内部服务
用户在线状态管理
1.存在内存中
2.存在redis中

# socket长链接方面
## 通讯服务 基于netty，基于事件
Boss线程 ：负责监听9432端口，接入客户端， 接入一个客户端创建一个chanel，并把chnnel注册给一个多路复用器线程，每个chanel被注册以后会获得生命周期和事件状态（可读，可写，就绪等）
多路复用器线程： 轮训自己手上的chanel，发现有可读chanel时，交给线程池处理，并改变channel的事件状态。

上面是nio模型。chanel是一个管道，双工通道。更加详细的请研究netty。

我们需要做的，
1.chanel和用户的绑定关系。群聊就是channel和群组的关系
2.定义协议包
3.传输层传输的协议包是二进制，所以我们需要解码，发出去的时候要编码成2进制
4.客户端接入以后联动用户在线状态更新
5.心跳，没有心跳服务端端开链接，联动更新用户在线状态。
6.发送消息给在线用户，用户不在线就放到消息队列。
7.用户上线后主动拉取离线消息

# 朋友圈
待定

# 大佬们补充