# 项目简介
**工作中一些最佳实践的汇总**，希望代码丝滑柔顺

目前包含的有：
* pom工程模块组织
* 数据访问层相关
    * 增删改查
    * 分页查询
* 日志相关配置
* 全局异常处理相关
* docker部署
* 代码生成器-snail（dal层）

# 快速部署
1. 拉取docker镜像
    ```bash
    docker pull registry.cn-hangzhou.aliyuncs.com/leonsong95/finch:[最新版本号]
    ```
2. 运行容器
    ```bash
    docker run --name myfinch -v /root/data/logs:/root/logs -p 8080:8080 -d registry.cn-hangzhou.aliyuncs.com/leonsong95/finch:[最新版本号]
    ```
    这里挂在了日志文件到宿主机
    
# 快速构建
1. 打包镜像
    ```bash
    docker build -t finch:[最新版本号] .
    ```
2. 推到阿里云镜像仓库
    ```bash
    docker push registry.cn-hangzhou.aliyuncs.com/leonsong95/finch:[最新版本号]
    ```