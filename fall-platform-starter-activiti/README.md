# fall-platform-starter-activiti

基于 activiti7

## 一、activiti7 工作流使用步骤：
- 部署activiti 
- 流程定义 
- 流程定义部署 
- 启动一个流程实例 
- 用户查询待办任务(Task) 
- 用户办理任务 
- 流程结束

## 二、activiti7 数据库表结构参考
- ACT_RE ：'RE'表示 repository。 这个前缀的 表包含了流程定义和流程静态资源 （图片，规则，等等）。 
- ACT_RU：'RU'表示 runtime。 这些运行时的表，包含流 程实例，任务，变量，异步任务，等运行中的数据。 Activiti 只在流程实例执行过程中保存这些数据， 在流程结束时 就会删除这些记录。 这样运行时表可以一直很小速度很快。 
- ACT_HI：'HI'表示 history。 这些表包含历史数据，比如历史流程实例， 变量，任务等等。 
- ACT_GE ： GE 表示 general。 通用数据， 用于不同场景下。

Activiti 数据表介绍
```
一般数据
[ACT_GE_BYTEARRAY] 通用的流程定义和流程资源
[ACT_GE_PROPERTY] 系统相关属性

流程历史记录    
[ACT_HI_ACTINST]            历史的流程实例
[ACT_HI_ATTACHMENT]         历史的流程附件
[ACT_HI_COMMENT]            历史的说明性信息
[ACT_HI_DETAIL]             历史的流程运行中的细节信息
[ACT_HI_IDENTITYLINK]       历史的流程运行过程中用户关系
[ACT_HI_PROCINST]           历史的流程实例（核心）
[ACT_HI_TASKINST]           历史的任务实例
[ACT_HI_VARINST]            历史的流程运行中的变量信息

流程定义表    
[ACT_RE_DEPLOYMENT]         部署单元信息
[ACT_RE_MODEL]              模型信息
[ACT_RE_PROCDEF]            已部署的流程定义

运行实例表    
[ACT_RU_EVENT_SUBSCR]       运行时事件
[ACT_RU_EXECUTION]          运行时流程执行实例
[ACT_RU_IDENTITYLINK]       运行时用户关系信息，存储任务节点与参与者的相关信息
[ACT_RU_JOB]                运行时作业
[ACT_RU_TASK]               运行时任务
[ACT_RU_VARIABLE]           运行时变量表
```

各个表字段含义见：
```
https://blog.csdn.net/hj7jay/article/details/51302829
```

## 三、功能
引擎中提供的封装好的接口。   
Service总览 
```
RepositoryService       activiti的资源管理类
RuntimeService          activiti的流程运行管理类
TaskService             activiti的任务管理类
HistoryService          activiti的历史管理类
ManagerService          activiti的引擎管理类
```

### RepositoryService
是activiti的资源管理类，提供了管理和控制流程发布包和流程定义的操作。使用工作流建模工具设计的业务流程图需 要使用此service将流程定义文件的内容部署到计算机。   
除了部署流程定义以外还可以：查询引擎中的发布包和流程定义。  
暂停或激活发布包，对应全部和特定流程定义。 暂停意味着它们不能再执行任何操作了，激活是对应的反向操作。 获得多种资源，像是包含在发布包里的文件， 或引擎自动生成的流程图。   
获得流程定义的pojo版本， 可以用来通过java解析流程，而不必通过xml。  

### RuntimeService 
Activiti的流程运行管理类。可以从这个服务类中获取很多关于流程执行相关的信息

### TaskService 
Activiti的任务管理类。可以从这个类中获取任务的信息。

### HistoryService 
Activiti的历史管理类，可以查询历史信息，执行流程时，引擎会保存很多数据（根据配置），比如流程实例启动时 间，任务的参与者， 完成任务的时间，每个流程实例的执行路径，等等。 这个服务主要通过查询功能来获得这些数 据。

### ManagementService 
Activiti的引擎管理类，提供了对 Activiti 流程引擎的管理和维护功能，这些功能不在工作流驱动的应用程序中使用， 主要用于 Activiti 系统的日常维护。 





参考：  
https://segmentfault.com/a/1190000020286897
