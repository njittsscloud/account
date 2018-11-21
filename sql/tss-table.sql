
CREATE TABLE `njit_course` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '课程分配id',
  `curriculumId` BIGINT(20) DEFAULT NULL COMMENT '课程id',
  `year` VARCHAR(20) DEFAULT NULL COMMENT '学年',
  `term` INT(1) DEFAULT NULL COMMENT '学期',
  `week` VARCHAR(100) DEFAULT NULL COMMENT '起止周，以英文逗号相隔',
  `state` INT(1) DEFAULT NULL COMMENT '状态',
  `createTime` DATETIME DEFAULT NULL,
  `updateTime` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='课程分配表';


CREATE TABLE `njit_department` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `parentId` BIGINT(20) DEFAULT NULL COMMENT '上级部门id',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '部门名称',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
  `state` INT(1) DEFAULT NULL COMMENT '状态',
  `deptNo` VARCHAR(10) DEFAULT NULL COMMENT '学院编号',
  `level` INT(1) DEFAULT NULL COMMENT '一级 二级 三级',
  `createTime` DATETIME DEFAULT NULL COMMENT '创建时间 ',
  `updateTime` DATETIME DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`parentId`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='学院/部门/班级表';

CREATE TABLE `njit_department_course` (
  `departmentId` BIGINT(20) NOT NULL COMMENT '部门（班级）id',
  `courseId` BIGINT(20) NOT NULL COMMENT '课程分配id',
  PRIMARY KEY (`departmentId`,`courseId`),
  KEY `FK_Reference_14` (`courseId`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='班级课程表';

CREATE TABLE `njit_department_experiment` (
  `experimentId` BIGINT(20) NOT NULL COMMENT '实验id',
  `departmentId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`experimentId`,`departmentId`),
  KEY `FK_Reference_34` (`departmentId`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='班级实验安排表';

CREATE TABLE `njit_department_task` (
  `taskId` BIGINT(20) NOT NULL COMMENT '任务id',
  `departmentId` BIGINT(20) NOT NULL COMMENT '班级id',
  PRIMARY KEY (`taskId`,`departmentId`),
  KEY `FK_Reference_36` (`departmentId`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='班级任务表';

CREATE TABLE `njit_experiment` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '实验id',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '实验名',
  `expWeek` INT(11) DEFAULT NULL,
  `dayOfWeek` INT(1) DEFAULT NULL,
  `beginOfDay` INT(1) DEFAULT NULL,
  `endOfDay` INT(1) DEFAULT NULL,
  `expDate` DATE DEFAULT NULL COMMENT '实验日期',
  `checked` INT(1) DEFAULT NULL,
  `state` INT(1) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
  `laboratoryId` BIGINT(20) DEFAULT NULL COMMENT '实验室id',
  `projectId` BIGINT(20) DEFAULT NULL COMMENT '实验项目id',
  `courseId` BIGINT(20) DEFAULT NULL COMMENT '课程分配',
  `createTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updateTime` DATETIME DEFAULT NULL COMMENT '修改时间',
  `checkedTime` DATETIME DEFAULT NULL COMMENT '审核时间',
  `approveComment` VARCHAR(1000) DEFAULT NULL COMMENT '审批意见',
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='实验安排表';

CREATE TABLE `njit_form` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) DEFAULT NULL,
  `filepath` VARCHAR(255) DEFAULT NULL,
  `status` INT(11) DEFAULT NULL,
  `createTime` DATETIME DEFAULT NULL,
  `updateTime` DATETIME DEFAULT NULL,
  `userId` BIGINT(20) DEFAULT NULL,
  `formTemplateId` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `njit_formtemplate` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `procDefKey` VARCHAR(255) DEFAULT NULL,
  `filepath` VARCHAR(255) DEFAULT NULL,
  `formTemplateId` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
) ENGINE=INNODBDEFAULT CHARSET=utf8;

CREATE TABLE `njit_laboratory` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '实验室id',
  `labNo` VARCHAR(50) DEFAULT NULL COMMENT '实验室代码',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '实验室名',
  `totalSeat` INT(11) DEFAULT NULL COMMENT '共有座位',
  `availSeat` INT(11) DEFAULT NULL COMMENT '可用座位',
  `state` INT(1) DEFAULT NULL COMMENT '状态',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
  `createTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updateTime` DATETIME DEFAULT NULL COMMENT '更新时间',
  `locellusId` BIGINT(20) DEFAULT NULL COMMENT '实验中心分室id',
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='实验分室（机房）表';

CREATE TABLE `njit_laboratory_center` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '实验中心id',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '中心名',
  `centerNo` VARCHAR(20) DEFAULT NULL COMMENT '中心代码',
  `state` INT(1) DEFAULT NULL COMMENT '状态',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
  `createTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updateTime` DATETIME DEFAULT NULL COMMENT '更新时间',
  `departmentId` BIGINT(20) DEFAULT NULL COMMENT '学院id',
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='实验中心表';

CREATE TABLE `njit_laboratory_center_locellus` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '分室id',
  `locellusNo` VARCHAR(20) DEFAULT NULL COMMENT '分室编号',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '分室名',
  `state` INT(1) DEFAULT NULL COMMENT '状态',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
  `createTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updateTime` DATETIME DEFAULT NULL COMMENT '更新时间',
  `centerId` BIGINT(20) DEFAULT NULL COMMENT '实验中心id',
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='实验中心室表';

CREATE TABLE `njit_message` (
  `id` VARCHAR(50) NOT NULL,
  `content` TEXT COMMENT '内容',
  `title` VARCHAR(255) DEFAULT NULL COMMENT '标题',
  `level` INT(1) DEFAULT NULL COMMENT '消息级别',
  `state` INT(1) DEFAULT NULL COMMENT '状态',
  `createTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updateTime` DATETIME DEFAULT NULL COMMENT '更新时间',
  `userId` BIGINT(20) DEFAULT NULL COMMENT '发送人id',
  `url` VARCHAR(100) DEFAULT NULL COMMENT '消息地址',
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='消息表';


CREATE TABLE `njit_message_receive` (
  `id` VARCHAR(50) NOT NULL,
  `messageId` VARCHAR(50) DEFAULT NULL,
  `userId` BIGINT(20) DEFAULT NULL,
  `isRead` INT(1) DEFAULT NULL,
  `state` INT(1) DEFAULT NULL,
  `readTime` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='接收消息表';


CREATE TABLE `njit_privilege` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '权限名',
  `url` VARCHAR(255) DEFAULT NULL COMMENT '权限url',
  `sort_order` INT(2) DEFAULT NULL COMMENT '权限顺序',
  `privilegeTypeId` BIGINT(20) DEFAULT NULL COMMENT '权限类别id',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
  `state` INT(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='权限表';

CREATE TABLE `njit_privilege_type` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='权限类别表';

CREATE TABLE `njit_project` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '实验项目id',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '实验项目名',
  `projectHour` INT(11) DEFAULT NULL COMMENT '项目学时数',
  `projectNo` VARCHAR(100) DEFAULT NULL COMMENT '编号',
  `state` INT(1) DEFAULT NULL COMMENT '状态',
  `createTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updateTime` DATETIME DEFAULT NULL COMMENT '修改时间',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
  `curriculumId` BIGINT(20) DEFAULT NULL COMMENT '课程id',
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='实验项目表';

CREATE TABLE `njit_project_laboratory` (
  `laboratoryId` BIGINT(20) NOT NULL COMMENT '实验室id',
  `projectId` BIGINT(20) NOT NULL COMMENT '实验项目id',
  PRIMARY KEY (`laboratoryId`,`projectId`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='实验项目可用实验室表';

CREATE TABLE `njit_report` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '报告id',
  `name` VARCHAR(200) DEFAULT NULL COMMENT '报告名',
  `filePath` VARCHAR(255) DEFAULT NULL COMMENT '文件路径',
  `submitDate` DATETIME DEFAULT NULL COMMENT '提交日期',
  `taskId` BIGINT(20) DEFAULT NULL COMMENT '实验报告任务id',
  `isCorrect` INT(1) DEFAULT NULL COMMENT '是否批阅',
  `state` INT(1) DEFAULT NULL COMMENT '报告状态',
  `updateTime` DATETIME DEFAULT NULL COMMENT '更新时间',
  `description` VARCHAR(500) DEFAULT NULL COMMENT '描述',
  `userId` BIGINT(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='实验报告表';

CREATE TABLE `njit_report_correct` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `reportId` BIGINT(20) DEFAULT NULL,
  `content` TEXT,
  `state` INT(1) DEFAULT NULL,
  `isRead` INT(1) DEFAULT NULL,
  `createTime` DATETIME DEFAULT NULL,
  `updateTime` DATETIME DEFAULT NULL,
  `score` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='报告批改表';

CREATE TABLE `njit_role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '角色名',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
  `createTime` DATETIME DEFAULT NULL,
  `updateTime` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE `njit_role_privilege` (
  `roleId` BIGINT(20) NOT NULL COMMENT '角色id',
  `privilegeId` BIGINT(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`roleId`,`privilegeId`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

CREATE TABLE `njit_share` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '资料id',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '资料名',
  `level` INT(1) DEFAULT NULL,
  `filePath` VARCHAR(255) DEFAULT NULL COMMENT '路径',
  `description` VARCHAR(500) DEFAULT NULL COMMENT '描述',
  `userId` BIGINT(20) DEFAULT NULL COMMENT '用户id',
  `curriculumId` BIGINT(20) DEFAULT NULL COMMENT '课程id',
  `createTime` DATETIME DEFAULT NULL COMMENT '上传日期',
  `updateTime` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='共享资料表';

CREATE TABLE `njit_share_receive` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `shareId` BIGINT(20) DEFAULT NULL,
  `userId` BIGINT(20) DEFAULT NULL,
  `createTime` DATETIME DEFAULT NULL,
  `updateTime` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='资料接收表';

CREATE TABLE `njit_task` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `name` VARCHAR(200) DEFAULT NULL COMMENT '任务名',
  `assignDate` DATETIME DEFAULT NULL COMMENT '下发日期',
  `dueDate` DATETIME DEFAULT NULL COMMENT '截止日期',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
  `userId` BIGINT(20) DEFAULT NULL COMMENT '用户（教师）id',
  `courseId` BIGINT(20) DEFAULT NULL COMMENT '课程分配',
  `state` INT(1) DEFAULT NULL,
  `createTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updateTime` DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='实验报告任务表';

CREATE TABLE `njit_task_attachment` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) DEFAULT NULL,
  `filePath` VARCHAR(100) DEFAULT NULL,
  `createTime` DATETIME DEFAULT NULL,
  `updateTime` DATETIME DEFAULT NULL,
  `taskId` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='任务附件表';

CREATE TABLE `njit_template` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '模板id',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '文件名',
  `filePath` VARCHAR(100) DEFAULT NULL COMMENT '文件相对路径',
  `description` VARCHAR(255) DEFAULT NULL,
  `typeId` BIGINT(20) DEFAULT NULL COMMENT '模板类别',
  `createTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updateTime` DATETIME DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='模板表';

CREATE TABLE `njit_template_type` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '模板类别id',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '模板类别名',
  `description` VARCHAR(200) DEFAULT NULL,
  `createTime` DATETIME DEFAULT NULL,
  `updateTime` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='模板类别表';


CREATE TABLE `njit_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `stu_no` varchar(50) DEFAULT '' COMMENT '学号',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `name` varchar(50) DEFAULT '' COMMENT '姓名',
  `head_img` varchar(255) DEFAULT '' COMMENT '头像',
  `gender` int(1) DEFAULT '0' COMMENT '性别 0保密 1男 2女',
  `phone` varchar(50) DEFAULT '' COMMENT '手机号码',
  `email` varchar(100) DEFAULT '' COMMENT '邮箱',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  `class_id` bigint(20) DEFAULT '0' COMMENT '班级id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(1) DEFAULT '0' COMMENT '删除标志 0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生表';

CREATE TABLE `njit_user_course` (
  `id` BIGINT(20),
  `userId` BIGINT(20) NOT NULL,
  `courseId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`userId`,`courseId`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='教师课程分配表';

CREATE TABLE `njit_user_role` (
  `userId` BIGINT(20) NOT NULL COMMENT '用户id',
  `roleId` BIGINT(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`userId`,`roleId`),
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户角色表';
