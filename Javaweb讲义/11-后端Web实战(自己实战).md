**注意：所有的接口，在开发时，一定一定一定 要按照接口文档开发。 否则，再和前端进行联调时，将会出现问题。**

1. ## 数据准备

在数据库中，创建学生表 `clazz` , `student`，SQL如下：

```SQL
create table clazz(
    id   int unsigned primary key auto_increment comment 'ID,主键',
    name  varchar(30) not null unique  comment '班级名称',
    room  varchar(20) comment '班级教室',
    begin_date date not null comment '开课时间',
    end_date date not null comment '结课时间',
    master_id int unsigned null comment '班主任ID, 关联员工表ID',
    subject tinyint unsigned not null comment '学科, 1:java, 2:前端, 3:大数据, 4:Python, 5:Go, 6: 嵌入式',
    create_time datetime  comment '创建时间',
    update_time datetime  comment '修改时间'
)comment '班级表';

INSERT INTO clazz VALUES (1,'JavaEE就业163期','212','2024-04-30','2024-06-29',10,1,'2024-06-01 17:08:23','2024-06-01 17:39:58'),
    (4,'前端就业90期','210','2024-07-10','2024-01-20',3,2,'2024-06-01 17:45:12','2024-06-01 17:45:12'),
    (5,'JavaEE就业165期','108','2024-06-15','2024-12-25',6,1,'2024-06-01 17:45:40','2024-06-01 17:45:40'),
    (6,'JavaEE就业166期','105','2024-07-20','2024-02-20',20,1,'2024-06-01 17:46:10','2024-06-01 17:46:10'),
    (7,'大数据就业58期','209','2024-08-01','2024-02-15',7,3,'2024-06-01 17:51:21','2024-06-01 17:51:21'),
    (8,'JavaEE就业167期','325','2024-11-20','2024-05-10',36,1,'2024-11-15 11:35:46','2024-12-13 14:31:24');


create table student(
  id int unsigned primary key auto_increment comment 'ID,主键',
  name varchar(10)  not null comment '姓名',
  no char(10)  not null unique comment '学号',
  gender tinyint unsigned  not null comment '性别, 1: 男, 2: 女',
  phone  varchar(11)  not null unique comment '手机号',
  id_card  char(18)  not null unique comment '身份证号',
  is_college tinyint unsigned  not null comment '是否来自于院校, 1:是, 0:否',
  address  varchar(100)  comment '联系地址',
  degree  tinyint unsigned  comment '最高学历, 1:初中, 2:高中, 3:大专, 4:本科, 5:硕士, 6:博士',
  graduation_date date comment '毕业时间',
  clazz_id  int unsigned not null comment '班级ID, 关联班级表ID',
  violation_count tinyint unsigned default '0' not null comment '违纪次数',
  violation_score tinyint unsigned default '0' not null comment '违纪扣分',
  create_time  datetime  comment '创建时间',
  update_time  datetime  comment '修改时间'
) comment '学员表';


INSERT INTO student VALUES (1,'段誉','2022000001',1,'18800000001','110120000300200001',1,'北京市昌平区建材城西路1号',1,'2021-07-01',2,0,0,'2024-11-14 21:22:19','2024-11-15 16:20:59'),
    (2,'萧峰','2022000002',1,'18800210003','110120000300200002',1,'北京市昌平区建材城西路2号',2,'2022-07-01',1,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),
    (3,'虚竹','2022000003',1,'18800013001','110120000300200003',1,'北京市昌平区建材城西路3号',2,'2024-07-01',1,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),
    (4,'萧远山','2022000004',1,'18800003211','110120000300200004',1,'北京市昌平区建材城西路4号',3,'2024-07-01',1,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),
    (5,'阿朱','2022000005',2,'18800160002','110120000300200005',1,'北京市昌平区建材城西路5号',4,'2020-07-01',1,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),
    (6,'阿紫','2022000006',2,'18800000034','110120000300200006',1,'北京市昌平区建材城西路6号',4,'2021-07-01',2,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),
    (7,'游坦之','2022000007',1,'18800000067','110120000300200007',1,'北京市昌平区建材城西路7号',4,'2022-07-01',2,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),
    (8,'康敏','2022000008',2,'18800000077','110120000300200008',1,'北京市昌平区建材城西路8号',5,'2024-07-01',2,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),
    (9,'徐长老','2022000009',1,'18800000341','110120000300200009',1,'北京市昌平区建材城西路9号',3,'2024-07-01',2,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),
    (10,'云中鹤','2022000010',1,'18800006571','110120000300200010',1,'北京市昌平区建材城西路10号',2,'2020-07-01',2,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),
    (11,'钟万仇','2022000011',1,'18800000391','110120000300200011',1,'北京市昌平区建材城西路11号',4,'2021-07-01',1,0,0,'2024-11-14 21:22:19','2024-11-15 16:21:24'),
    (12,'崔百泉','2022000012',1,'18800000781','110120000300200018',1,'北京市昌平区建材城西路12号',4,'2022-07-05',8,6,17,'2024-11-14 21:22:19','2024-12-13 14:33:58'),
    (13,'耶律洪基','2022000013',1,'18800008901','110120000300200013',1,'北京市昌平区建材城西路13号',4,'2024-07-01',2,0,0,'2024-11-14 21:22:19','2024-11-15 16:21:21'),
    (14,'天山童姥','2022000014',2,'18800009201','110120000300200014',1,'北京市昌平区建材城西路14号',4,'2024-07-01',1,0,0,'2024-11-14 21:22:19','2024-11-15 16:21:17'),
    (15,'刘竹庄','2022000015',1,'18800009401','110120000300200015',1,'北京市昌平区建材城西路15号',3,'2020-07-01',4,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),
    (16,'李春来','2022000016',1,'18800008501','110120000300200016',1,'北京市昌平区建材城西路16号',4,'2021-07-01',4,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),
    (17,'王语嫣','2022000017',2,'18800007601','110120000300200017',1,'北京市昌平区建材城西路17号',2,'2022-07-01',4,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),
    (18,'郑成功','2024001101',1,'13309092345','110110110110110110',0,'北京市昌平区回龙观街道88号',5,'2021-07-01',8,2,7,'2024-11-15 16:26:18','2024-11-15 16:40:10');
```

实体类Clazz：

```Java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {
    private Integer id; //ID
    private String name; //班级名称
    private String room; //班级教室
    private LocalDate beginDate; //开课时间
    private LocalDate endDate; //结课时间
    private Integer masterId; //班主任
    private Integer subject; //学科
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间

    private String masterName; //班主任姓名
    private String status; //班级状态 - 未开班 , 在读 , 已结课
}
```

实体类Student：

```Java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id; //ID
    private String name; //姓名
    private String no; //序号
    private Integer gender; //性别 , 1: 男 , 2 : 女
    private String phone; //手机号
    private String idCard; //身份证号
    private Integer isCollege; //是否来自于院校, 1: 是, 0: 否
    private String address; //联系地址
    private Integer degree; //最高学历, 1: 初中, 2: 高中 , 3: 大专 , 4: 本科 , 5: 硕士 , 6: 博士
    private LocalDate graduationDate; //毕业时间
    private Integer clazzId; //班级ID
    private Short violationCount; //违纪次数
    private Short violationScore; //违纪扣分
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间

    private String clazzName;//班级名称
}
```

1. ## 需求：班级管理

需要开发如下几个接口，且开发的时候建议按照如下顺序开发 ：

1. ### 条件分页查询接口

参照接口文档 **`班级管理`** -> **`班级列表查询`**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NjhjZWJiYzQ5YmRiYWIwNzBkNzc0MmUyYzI4MTM0NmFfSmRqUWEwRjJublRSYmpZcldERXE3dU43WkFwTG5hdzdfVG9rZW46SjJWbWJnOHlMb3Y5Q1l4TjhzS2NCdzg0bndtXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

1. ### 查询所有员工接口

参照接口文档 **`员工管理`** -> **`查询全部员工`**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NjNjN2NmYjI2MzBkOWY1ZTkxN2I4Zjc0ZDlkOWE2NDlfRkhKVHptNWpFdVl6Y0lWZ0kwNzVnQ3FNYndyNWVYSUNfVG9rZW46UWFGZ2J5eFZVb1pGMkt4Q0lCd2NLblBVbjNjXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

1. ### 新增班级信息接口

参照接口文档 **`班级管理`** -> **`添加班级`**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YTFiZDIxN2IzZmVkOWE4NGMxZDFlNTNkODY1YzM0MTNfc3RzeUpJZlJKUXc0MjZYakNlUHNjdGNXRUluZkdBN1FfVG9rZW46RjJPSmJtSnAyb2RvV1h4OFJSZ2Nyd2NxbjhnXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

1. ### **根据ID查询班级接口**

参照接口文档 **`班级管理`** -> **`根据ID查询`**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NGFiYWQ2Y2RhNzg1OWJhMThlMzA3MzdkNmE3NDM1Y2RfaEQweXV2YmZhc1hCblN0U056dFl0dEVUODlGRnROb0pfVG9rZW46UXVSSGJERVVPb2xnRjR4ZnRLVGM3NGJNbmhiXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

1. ### **修改班级信息接口**

参照接口文档 **`班级管理`** -> **`修改班级`**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NmZiYzlmZmQ2OGMwNzZkMmE2NjVhNGRiYWZiZWE5ZDFfeld1bVBjOTNTT2Fyb2Fid2lWODVNdldXM3BQMndpc3ZfVG9rZW46VVFZWmJBVjRMb0dlTzl4Q3lqdWNlTG50bnhRXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

1. ### 删除班级信息接口

参照接口文档 **`班级管理`** -> **`删除班级`**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NzQxMDIxMmRlMjgyZTc4ZTlmMzQ4YTQxZjQyMGM5ZjFfZlc3RmRHcW1yOUFzTkhWcGRvbElWVEo5N0xLSW5JandfVG9rZW46WExwR2JvRUZabzEwamF4clpEWmNXYzNBblVlXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

注意：在页面原型中，要求如果该班级下关联的有学生，是不允许删除的，并提示错误信息："对不起, 该班级下有学生, 不能直接删除"。

到此，关于班级管理的增删改查功能，我们就已经全部实现了。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YmRlOGRjOTM2Yzg1MzQwZDNiNDc2MGVlMTM1YzJkZjNfVXlDNDFaWjFIZkFabjVEMnZjYjFzczFsaVFLTnJ6OHRfVG9rZW46SDFYY2JhODlxb3BVcHJ4eHBkYmM1SXlIbkJkXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

1. ## 需求：学员管理

需要开发如下几个接口，建议按照如下顺序开发接口：

1. ### 查询所有班级接口

参照接口文档 **`班级管理`** -> **`查询所有班级`**

在新增学员的时候，要展示出所有的班级信息。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTBjNWJjY2I5ODkwNjg4ODdkYzI5NzQyNWY4YjRkZWRfYTByWlFORWlkUXlrWnRETk5GNlFBanl4VGlzcHVHV01fVG9rZW46VHpVUWJYU3BhbzZYTGh4a01CMWNDZUN2blVoXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

1. ### 条件分页查询接口

参照接口文档 **`学员管理`** -> **`学员列表查询`**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YzY2NWI2MmNlYmU3NjIwYjAwNGVmYWRkZWVkNDY0ZmJfOUUyNk9mVDg0RGF3bEtSNkJ5MUhJMWJ5Q01nUm5ucHVfVG9rZW46RzFHbWJGdm03bzFMb1d4ZWMzUGNORm5VbnRoXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

1. ### 新增学生信息接口

参照接口文档 **`学员管理`** -> **`添加学员`**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OGUyNzA1ZGQ5M2ZiY2NiODc1NWY0YmVhMTM5NzZlN2ZfRlhhbkhQV2ViVm5HMmIxdVU5NFhCNW16WXRiRTNJNk5fVG9rZW46SFhkTGJHdFJrb2djSUR4Q2J6QmNwYndBbnBnXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

1. ### 根据ID查询学生接口

参照接口文档 **`学员管理`** -> **`根据ID查询学员`**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YTA2YmM1ODFhMWNmMjU1Y2EyYjRkYzNmYzVkMzExMTRfVjNDVUhCc3pYNzJ6TXF0dlk3M0FJWkZ0cXBGbDFEczZfVG9rZW46U2NZWWJBUGZrb3BSVDd4VFJoV2NnQkNkbjdmXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

1. ### 修改学生信息接口

参照接口文档 **`学员管理`** -> **`修改学员`**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YjkzZDNhZWVjNDAzZmVjYWEzNmVhNGQ3Y2ZmODNhNGNfWmFqVnVVY094RXRuRXd6YXRyaHlXNXlEV0JCR0NnWEhfVG9rZW46SGFyOGI0QzNsb0VWUHV4bzhlTmNCQTNFbm5FXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

1. ### 删除学生信息接口

参照接口文档 **`学员管理`** -> **`删除学员`**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YjFhNDFiMTlkZWE5ZDM5MzgzMWY2Njk4YzNlMGRlODVfbGFlc2p0dnV4S0xSVDU5QVdNWlZGazV6cjNYRG83aW1fVG9rZW46SEVYYmI0SGNyb3VxMFF4aEtqZGNZbFAybjFlXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

1. ### **违纪处理接口**

参照接口文档 **`学员管理`** -> **`违纪处理`**

违纪处理一次，需要将违纪次数+1，违纪扣分+前端输入的分数。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YTcwNDYxNmRkOTg3ZjM5OTEyZDk1YTdkMjE2YzFjYTJfRWpYRjloSnVmbGtGMnJMa2ZzTHdhUmkzV29mbTY0M0RfVG9rZW46TTNHNmJZTTlrbzNpYzh4TVlSaWNKV2oxblFjXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

到此，关于学生管理的增删改查功能，我们就已经全部实现了。

1. ## 需求：学员信息统计

需要开发如下几个接口：

- 班级人数统计接口开发：参照接口文档 **`数据统计`** -> **`班级人数统计`**
- 学员学历信息统计接口开发：参照接口文档 **`数据统计`** -> **`学员学历统计`**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ODk0ODg5YzZmMWFiODYzMGY2YTc4ZjcyMDdiMTg2M2JfMnk4RElUQjA0NkhWejJpQXkwc3BTZDVRd3lwUXJObE1fVG9rZW46U0FhU2IzWkFOb0JoTlp4MnZTTmNhcjdpbm5JXzE3OTA4ODE4NjA6MTc5MDg4NTQ2MF9WNA&add_watermark=true&scene_type=CCM)

1. ## 功能完善

- **删除部门时：**如果部门下有员工，则不允许删除该部门，并给前端提示错误信息：对不起，当前部门下有员工，不能直接删除！
- **删除班级时：**如果班级下有学生，则不允许删除该班级，并给前端提示错误信息：对不起，当前班级下有学生，不能直接删除！