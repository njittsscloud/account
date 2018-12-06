CREATE USER 'njittss'@'%' IDENTIFIED BY 'njittss!@#$5678'; 
CREATE USER 'njittss'@'127.0.0.1' IDENTIFIED BY 'njittss!@#$5678'; 
CREATE USER 'njittss'@'localhost' IDENTIFIED BY 'njittss!@#$5678'; 

GRANT ALL PRIVILEGES ON njit_account.* TO 'njittss'@'%' IDENTIFIED BY 'njittss!@#$5678' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON njit_data.* TO 'njittss'@'%' IDENTIFIED BY 'njittss!@#$5678' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON njit_report.* TO 'njittss'@'%' IDENTIFIED BY 'njittss!@#$5678' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON njit_experiment.* TO 'njittss'@'%' IDENTIFIED BY 'njittss!@#$5678' WITH GRANT OPTION;
FLUSH PRIVILEGES;

-- CREATE TABLE `njit_admin` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
--
-- CREATE TABLE `njit_privilege` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
--
-- CREATE TABLE `njit_role` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
--
-- CREATE TABLE `njit_role_privilege` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `njit_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `student_no` varchar(50) DEFAULT '' COMMENT '学号',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐值',
  `name` varchar(50) DEFAULT '' COMMENT '姓名',
  `head_img` varchar(255) DEFAULT '' COMMENT '头像',
  `gender` int(1) DEFAULT '0' COMMENT '性别 0保密 1男 2女',
  `phone` varchar(50) DEFAULT '' COMMENT '手机号码',
  `email` varchar(100) DEFAULT '' COMMENT '邮箱',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  `class_id` bigint(20) DEFAULT '-1' COMMENT '班级id',
  `class_name` varchar(50) DEFAULT '' COMMENT '班级名称',
  `academic_id` bigint(20) DEFAULT '-1' COMMENT '学院id',
  `academic_name` varchar(50) DEFAULT '' COMMENT '学院名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` int(1) DEFAULT '0' COMMENT '删除标志 0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生表';


CREATE TABLE `njit_student_session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT '0' COMMENT '用户id',
  `user_acc` varchar(64) DEFAULT '' COMMENT '登录用户名',
  `user_name` varchar(50) DEFAULT '' COMMENT '登录用户姓名',
  `session_id` char(40) DEFAULT '' COMMENT 'session id',
  `status` int(1) DEFAULT '1' COMMENT '状态 1 可用 2 过期',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `expire_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(1) DEFAULT '0' COMMENT '删除标志 0 正常 1删除',
  `extra` varchar(1024) DEFAULT '' COMMENT '扩展信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `njit_teacher` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `job_no` VARCHAR(50) DEFAULT '' COMMENT '工号',
  `password` VARCHAR(50) DEFAULT '' COMMENT '密码',
  `salt` VARCHAR(20) DEFAULT '' COMMENT '盐值',
  `name` VARCHAR(50) DEFAULT '' COMMENT '姓名',
  `head_img` VARCHAR(255) DEFAULT '' COMMENT '头像',
  `gender` INT(1) DEFAULT '0' COMMENT '性别 0保密 1男 2女',
  `phone` VARCHAR(50) DEFAULT '' COMMENT '手机号码',
  `email` VARCHAR(100) DEFAULT '' COMMENT '邮箱',
  `desc` VARCHAR(255) DEFAULT '' COMMENT '描述',
  `academic_id` BIGINT(20) DEFAULT '-1' COMMENT '学院id',
  `academic_name` VARCHAR(50) DEFAULT '' COMMENT '学院名称',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` INT(1) DEFAULT '0' COMMENT '删除标志 0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='教师表';


CREATE TABLE `njit_teacher_session` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` BIGINT(20) DEFAULT '0' COMMENT '用户id',
  `user_acc` VARCHAR(64) DEFAULT '' COMMENT '登录用户名',
  `user_name` VARCHAR(50) DEFAULT '' COMMENT '登录用户姓名',
  `session_id` CHAR(40) DEFAULT '' COMMENT 'session id',
  `status` INT(1) DEFAULT '1' COMMENT '状态 1 可用 2 过期',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `expire_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` INT(1) DEFAULT '0' COMMENT '删除标志 0 正常 1删除',
  `extra` VARCHAR(1024) DEFAULT '' COMMENT '扩展信息',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `njit_syy` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` VARCHAR(50) DEFAULT '' COMMENT '账号',
  `password` VARCHAR(50) DEFAULT '' COMMENT '密码',
  `salt` VARCHAR(20) DEFAULT '' COMMENT '盐值',
  `name` VARCHAR(50) DEFAULT '' COMMENT '姓名',
  `head_img` VARCHAR(255) DEFAULT '' COMMENT '头像',
  `gender` INT(1) DEFAULT '0' COMMENT '性别 0保密 1男 2女',
  `phone` VARCHAR(50) DEFAULT '' COMMENT '手机号码',
  `email` VARCHAR(100) DEFAULT '' COMMENT '邮箱',
  `desc` VARCHAR(255) DEFAULT '' COMMENT '描述',
  `academic_id` BIGINT(20) DEFAULT '-1' COMMENT '学院id',
  `academic_name` VARCHAR(50) DEFAULT '' COMMENT '学院名称',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` INT(1) DEFAULT '0' COMMENT '删除标志 0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='教师表';


CREATE TABLE `njit_syy_session` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` BIGINT(20) DEFAULT '0' COMMENT '用户id',
  `user_acc` VARCHAR(64) DEFAULT '' COMMENT '登录用户名',
  `user_name` VARCHAR(50) DEFAULT '' COMMENT '登录用户姓名',
  `session_id` CHAR(40) DEFAULT '' COMMENT 'session id',
  `status` INT(1) DEFAULT '1' COMMENT '状态 1 可用 2 过期',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `expire_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` INT(1) DEFAULT '0' COMMENT '删除标志 0 正常 1删除',
  `extra` VARCHAR(1024) DEFAULT '' COMMENT '扩展信息',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `njit_admin` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` VARCHAR(50) DEFAULT '' COMMENT '账号',
  `password` VARCHAR(50) DEFAULT '' COMMENT '密码',
  `salt` VARCHAR(20) DEFAULT '' COMMENT '盐值',
  `name` VARCHAR(50) DEFAULT '' COMMENT '姓名',
  `head_img` VARCHAR(255) DEFAULT '' COMMENT '头像',
  `gender` INT(1) DEFAULT '0' COMMENT '性别 0保密 1男 2女',
  `phone` VARCHAR(50) DEFAULT '' COMMENT '手机号码',
  `email` VARCHAR(100) DEFAULT '' COMMENT '邮箱',
  `desc` VARCHAR(255) DEFAULT '' COMMENT '描述',
  `academic_id` BIGINT(20) DEFAULT '-1' COMMENT '学院id',
  `academic_name` VARCHAR(50) DEFAULT '' COMMENT '学院名称',
  `super_admin` INT(1) DEFAULT '0' COMMENT '超级管理员 1是 0否',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` INT(1) DEFAULT '0' COMMENT '删除标志 0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='管理员表';


CREATE TABLE `njit_admin_session` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` BIGINT(20) DEFAULT '0' COMMENT '用户id',
  `user_acc` VARCHAR(64) DEFAULT '' COMMENT '登录用户名',
  `user_name` VARCHAR(50) DEFAULT '' COMMENT '登录用户姓名',
  `session_id` CHAR(40) DEFAULT '' COMMENT 'session id',
  `status` INT(1) DEFAULT '1' COMMENT '状态 1 可用 2 过期',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `expire_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` INT(1) DEFAULT '0' COMMENT '删除标志 0 正常 1删除',
  `extra` VARCHAR(1024) DEFAULT '' COMMENT '扩展信息',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

