drop database  assets if exists assets;
create database assets 
use assets

drop table if exists action;

drop table if exists organization;

drop table if exists role;

drop table if exists role_action;

drop table if exists user;


create table action
(
   actionid             int comment 'id' AUTO_INCREMENT,
   actionname           varchar(100) comment '动作权限详情',
   action               varchar(50) not null,
   primary key (actionid)
);

alter table action comment '权限动作表';

create table organization
(
   oid                  int not null auto_increment ,
   name                 varchar(50) comment '名字',
   remarks              varchar(200) comment '备注',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (oid)
);
alter table organization AUTO_INCREMENT=0;

alter table organization comment '组织机构';

create table role
(
   roleid               int not null auto_increment,
   name                 varchar(50) comment '名字',
   remarks              varchar(200) comment '备注',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (roleid)
);

alter table role comment '角色表';

create table role_action
(
   roleid               int,
   action               varchar(50),
   id                   int not null auto_increment,
   primary key (id)
);

alter table role_action comment '角色权限映射表';

create table user
(
   id                   int not null auto_increment,
   roleid               int,
   oid                  int,
   name                 varchar(50) comment '姓名',
   pwd                  varchar(50) comment '密码',
   sex                  decimal(1,0) comment '性别 1-男、2-女',
   birth                varchar(20) comment '生日',
   phone                varchar(11) comment '手机',
   email                varchar(500) comment '邮件',
   logintime            varchar(20) comment '登录时间',
   loginip              varchar(500) comment '登录ip',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (id)
);

alter table user comment '用户';




drop table if exists device;

drop table if exists devicereceive;

drop table if exists devicerepair;

drop table if exists devicesout;

drop table if exists devicetype;


create table device
(
   did                  int not null auto_increment,
   dtid                 int comment '设备类型id',
   oid                  int,
   code                 varchar(7) comment '设备流水号',
   residual             decimal(16,2) comment '净残值',
   original             decimal(16,2) comment '原值',
   status               decimal(2,0) comment '状态 1-入库、2-出库中、3-出库、4-领用、5-报修',
   proddate             date comment '生产日期',
   creator              varchar(50) comment '登记人',
   createtime           datetime comment '登记时间',
   buyer                varchar(50) comment '购买人',
   bugdate              date comment '购买日期',
   sno                  varchar(32) comment '序列号',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (did)
);

alter table device comment '设备明细';


create table devicereceive
(
   id                   int not null auto_increment,
   did                  int comment '设备id',
   code                 varchar(9) comment '单据号',
   recipients           varchar(32) comment '领用人',
   receivedate          datetime comment '领取时间',
   returndate           datetime comment '归还日期',
   status               decimal(2) comment '状态 1-领用、2-归还',
   rcremarks            varchar(200) comment '领用备注',
   rtremarks            varchar(200) comment '归还备注',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (id)
);

alter table devicereceive comment '设备领用表';

create table devicerepair
(
   id                   int not null auto_increment,
   did                  int comment '设备id',
   damager              varchar(50) comment '报修人',
   damagedate           datetime comment '报修时间',
   damageremarks        varchar(200) comment '报修备注',
   repairdate           datetime comment '维修时间',
   repairer             varchar(50) comment '维修人',
   repairremarks        varchar(200) comment '维修备注',
   status               decimal(1,0) comment '状态 1-报修、2-维修',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (id)
);

alter table devicerepair comment '设备维修表';


create table devicesout
(
   id                   int not null auto_increment,
   did                  int comment '设备id',
   outter               varchar(50) comment '报废人',
   outdate              datetime comment '报废日期',
   remarks              varchar(200) comment '备注',
   status               decimal(1,0) comment '状态 1-申请、2-准许、3-拒绝',
   approver             varchar(50) comment '审批人',
   approvalremarks      varchar(200) comment '审批意见',
   approvaldate         datetime comment '审批日期',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (id)
);

alter table devicesout comment '设备出库';


create table devicetype
(
   dtid                 int not null auto_increment,
   name                 varchar(50) comment '名字',
   brand                varchar(100) comment '品牌',
   intlcode             varchar(100) comment '国际编号',
   model                varchar(100) comment '型号',
   remarks              varchar(200) comment '备注',
   asl                  decimal(3) comment '使用年限',
   anrv                 decimal(5,3) comment '预计净残值',
   crtm                 varchar(32) comment '创建时间',
   mdtm                 varchar(32) comment '修改时间',
   primary key (dtid)
);

alter table devicetype comment '设备类型';

alter table device add constraint FK_Reference_5 foreign key (dtid)
      references devicetype (dtid) on delete restrict on update restrict;

alter table device add constraint FK_Reference_6 foreign key (oid)
      references organization (oid) on delete restrict on update restrict;




drop table if exists car;

drop table if exists carreceive;

drop table if exists carrepair;

drop table if exists carsout;

drop table if exists cartype;


create table car
(
   did                  int not null auto_increment,
   dtid                 int comment '车辆类型id',
   oid                  int,
   code                 varchar(7) comment '车辆流水号',
   residual             decimal(16,2) comment '净残值',
   original             decimal(16,2) comment '原值',
   status               decimal(2,0) comment '状态 1-入库、2-出库中、3-出库、4-领用、5-报修',
   proddate             date comment '生产日期',
   creator              varchar(50) comment '登记人',
   createtime           datetime comment '登记时间',
   buyer                varchar(50) comment '购买人',
   bugdate              date comment '购买日期',
   sno                  varchar(32) comment '序列号',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (did)
);

alter table car comment '车辆明细';


create table carreceive
(
   id                   int not null auto_increment,
   did                  int comment '车辆id',
   code                 varchar(9) comment '单据号',
   recipients           varchar(32) comment '领用人',
   receivedate          datetime comment '领取时间',
   returndate           datetime comment '归还日期',
   status               decimal(2) comment '状态 1-领用、2-归还',
   rcremarks            varchar(200) comment '领用备注',
   rtremarks            varchar(200) comment '归还备注',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (id)
);

alter table carreceive comment '车辆领用表';

create table carrepair
(
   id                   int not null auto_increment,
   did                  int comment '车辆id',
   damager              varchar(50) comment '报修人',
   damagedate           datetime comment '报修时间',
   damageremarks        varchar(200) comment '报修备注',
   repairdate           datetime comment '维修时间',
   repairer             varchar(50) comment '维修人',
   repairremarks        varchar(200) comment '维修备注',
   status               decimal(1,0) comment '状态 1-报修、2-维修',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (id)
);

alter table carrepair comment '车辆维修表';


create table carsout
(
   id                   int not null auto_increment,
   did                  int comment '车辆id',
   outter               varchar(50) comment '报废人',
   outdate              datetime comment '报废日期',
   remarks              varchar(200) comment '备注',
   status               decimal(1,0) comment '状态 1-申请、2-准许、3-拒绝',
   approver             varchar(50) comment '审批人',
   approvalremarks      varchar(200) comment '审批意见',
   approvaldate         datetime comment '审批日期',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (id)
);

alter table carsout comment '车辆出库';


create table cartype
(
   dtid                 int not null auto_increment,
   name                 varchar(50) comment '名字',
   brand                varchar(100) comment '品牌',
   intlcode             varchar(100) comment '国际编号',
   model                varchar(100) comment '型号',
   remarks              varchar(200) comment '备注',
   asl                  decimal(3) comment '使用年限',
   anrv                 decimal(5,3) comment '预计净残值',
   crtm                 varchar(32) comment '创建时间',
   mdtm                 varchar(32) comment '修改时间',
   primary key (dtid)
);

alter table cartype comment '车辆类型';

alter table car add constraint FK_Reference_5 foreign key (dtid)
      references cartype (dtid) on delete restrict on update restrict;

alter table car add constraint FK_Reference_6 foreign key (oid)
      references organization (oid) on delete restrict on update restrict;




drop table if exists books;

drop table if exists booksreceive;

drop table if exists booksrepair;

drop table if exists bookssout;

drop table if exists bookstype;


create table books
(
   did                  int not null auto_increment,
   dtid                 int comment '家具图书类型id',
   oid                  int,
   code                 varchar(7) comment '家具图书流水号',
   residual             decimal(16,2) comment '净残值',
   original             decimal(16,2) comment '原值',
   status               decimal(2,0) comment '状态 1-入库、2-出库中、3-出库、4-领用、5-报修',
   proddate             date comment '生产日期',
   creator              varchar(50) comment '登记人',
   createtime           datetime comment '登记时间',
   buyer                varchar(50) comment '购买人',
   bugdate              date comment '购买日期',
   sno                  varchar(32) comment '序列号',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (did)
);

alter table books comment '家具图书明细';


create table booksreceive
(
   id                   int not null auto_increment,
   did                  int comment '家具图书id',
   code                 varchar(9) comment '单据号',
   recipients           varchar(32) comment '领用人',
   receivedate          datetime comment '领取时间',
   returndate           datetime comment '归还日期',
   status               decimal(2) comment '状态 1-领用、2-归还',
   rcremarks            varchar(200) comment '领用备注',
   rtremarks            varchar(200) comment '归还备注',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (id)
);

alter table booksreceive comment '家具图书领用表';

create table booksrepair
(
   id                   int not null auto_increment,
   did                  int comment '家具图书id',
   damager              varchar(50) comment '报修人',
   damagedate           datetime comment '报修时间',
   damageremarks        varchar(200) comment '报修备注',
   repairdate           datetime comment '维修时间',
   repairer             varchar(50) comment '维修人',
   repairremarks        varchar(200) comment '维修备注',
   status               decimal(1,0) comment '状态 1-报修、2-维修',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (id)
);

alter table booksrepair comment '家具图书维修表';


create table bookssout
(
   id                   int not null auto_increment,
   did                  int comment '家具图书id',
   outter               varchar(50) comment '报废人',
   outdate              datetime comment '报废日期',
   remarks              varchar(200) comment '备注',
   status               decimal(1,0) comment '状态 1-申请、2-准许、3-拒绝',
   approver             varchar(50) comment '审批人',
   approvalremarks      varchar(200) comment '审批意见',
   approvaldate         datetime comment '审批日期',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (id)
);

alter table bookssout comment '家具图书出库';


create table bookstype
(
   dtid                 int not null auto_increment,
   name                 varchar(50) comment '名字',
   brand                varchar(100) comment '品牌',
   intlcode             varchar(100) comment '国际编号',
   model                varchar(100) comment '型号',
   remarks              varchar(200) comment '备注',
   asl                  decimal(3) comment '使用年限',
   anrv                 decimal(5,3) comment '预计净残值',
   crtm                 varchar(32) comment '创建时间',
   mdtm                 varchar(32) comment '修改时间',
   primary key (dtid)
);

alter table bookstype comment '家具图书类型';

alter table books add constraint FK_Reference_5 foreign key (dtid)
      references bookstype (dtid) on delete restrict on update restrict;

alter table books add constraint FK_Reference_6 foreign key (oid)
      references organization (oid) on delete restrict on update restrict;



drop table if exists land;
drop table if exists landout;

create table land
(
   did                  int not null auto_increment,
   oid                  int,
   landcode             varchar(100) comment '土地代号',
   landlaction varchar(255)  comment  '地理位置',
   landarea varchar(50)  comment '占地面积',
   status               decimal(1,0) comment '状态 1 空闲 2分配中 3 已分配',
   creator              varchar(50) comment '登记人',
   crtm                  varchar(50) comment '创建时间',
   mdtm                  varchar(50)  comment '修改时间',
   primary key (did),
   FOREIGN KEY (oid) REFERENCES organization (oid)
);

create table landout
(
   id                   int not null auto_increment,
   did                  int comment '土地id',
   outter               varchar(50) comment '申请人',
   applyer              varchar(50) comment '被分配人',
   remarks              varchar(200) comment '备注',
   status               decimal(1,0) comment '状态 1-申请、2-准许、3-拒绝',
   approver             varchar(50) comment '审批人',
   approvalremarks      varchar(200) comment '审批意见',
   approvaldate         datetime comment '审批日期',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (id)
);

drop table if exists house;
drop table if exists houseout;
create table house
(
   did                  int not null auto_increment,
   oid                  int,
   housecode             varchar(100) comment '房子代号',
   houselaction varchar(255)  comment  '地理位置',
   housearea varchar(50)  comment '占地面积',
   status               decimal(1,0) comment '状态 1 空闲 2分配中 3 已分配',
   creator              varchar(50) comment '登记人',
   crtm                  varchar(50) comment '创建时间',
   mdtm                  varchar(50)  comment '修改时间',
   primary key (did),
   FOREIGN KEY (oid) REFERENCES organization (oid)
);

create table houseout
(
   id                   int not null auto_increment,
   did                  int comment '房子id',
   outter               varchar(50) comment '申请人',
   applyer              varchar(50) comment '被分配人',
   remarks              varchar(200) comment '备注',
   status               decimal(1,0) comment '状态 1-申请、2-准许、3-拒绝',
   approver             varchar(50) comment '审批人',
   approvalremarks      varchar(200) comment '审批意见',
   approvaldate         datetime comment '审批日期',
   crtm                 datetime comment '创建时间',
   mdtm                 datetime comment '修改时间',
   primary key (id)
);



alter table role_action add constraint FK_Reference_2 foreign key (roleid)
      references role (roleid) on delete restrict on update restrict;

alter table role_action add constraint FK_Reference_3 foreign key (action)
      references action (action) on delete restrict on update restrict;

alter table user add constraint FK_Reference_1 foreign key (roleid)
      references role (roleid) on delete restrict on update restrict;

alter table user add constraint FK_Reference_4 foreign key (oid)
      references organization (oid) on delete restrict on update restrict;




INSERT INTO organization(name, remarks,crtm, mdtm) VALUES ("超级管理部门",NULL,NULL,NULL);
update organization set oid=1 

INSERT INTO action(action.action,action.actionname) VALUES("delete","删除");
INSERT INTO action(action.action,action.actionname) VALUES("insert","增加");
INSERT INTO action(action.action,action.actionname) VALUES("update","更改");
INSERT INTO action(action.action,action.actionname) VALUES("select","查询");

INSERT INTO role( name, remarks,crtm, mdtm) VALUES ( "管理员", NULL,NULL, NULL) ; 
update role set roleid=1 

INSERT INTO role_action( roleid,action) VALUES (1,"delete") ; 
INSERT INTO role_action( roleid,action) VALUES (1,"insert") ; 
INSERT INTO role_action( roleid,action) VALUES (1,"update") ; 
INSERT INTO role_action( roleid,action) VALUES (1,"select") ; 

INSERT INTO user(roleid, oid, name, pwd, sex, birth, phone, email, logintime, loginip,crtm, mdtm) VALUES (1, 1, "admin", "123", 1, NULL, NULL, NULL,NULL, NULL,NULL, NULL);    