

CREATE TABLE `njit_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `njit_privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `njit_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `njit_role_privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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
  `id` bigint(20) NOT NULL COMMENT 'id',
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


CREATE TABLE `njit_syy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `njit_teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `job_no` varchar(50) DEFAULT '' COMMENT '工号',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `head_img` varchar(50) DEFAULT '' COMMENT '头像',
  `name` varchar(50) DEFAULT '' COMMENT '姓名',
  `gender` int(2) DEFAULT '0' COMMENT '性别 0保密 1男 2女',
  `phone` varchar(50) DEFAULT '' COMMENT '手机',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `desc` varchar(200) DEFAULT '' COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除标志 0正常 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师表';
