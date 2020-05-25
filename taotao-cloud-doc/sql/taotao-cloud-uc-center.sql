CREATE DATABASE IF NOT EXISTS `taotao-cloud-uc-center` DEFAULT CHARACTER SET = utf8mb4;
Use `taotao-cloud-uc-center`;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `dept_id`     int(20)   NOT NULL AUTO_INCREMENT COMMENT '部门主键ID',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '部门名称',
    `sort`        int(11)                                                DEFAULT NULL COMMENT '排序',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
    `create_time` timestamp NULL                                         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NULL                                         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `del_flag`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin      DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
    `parent_id`   int(11)                                                DEFAULT NULL COMMENT '上级部门',
#     `tenant_id`   int(11)                                                DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept`
VALUES (4, '某某集团', 0, NULL, '2019-04-21 22:53:33', '2019-04-30 22:27:59', '0', 0);
INSERT INTO `sys_dept`
VALUES (5, '上海公司', 1, NULL, '2019-04-21 22:53:57', '2019-08-30 11:35:50', '0', 4);
INSERT INTO `sys_dept`
VALUES (6, '研发部', 2, NULL, '2019-04-21 22:54:10', '2019-08-30 11:35:55', '0', 5);
INSERT INTO `sys_dept`
VALUES (7, '财务部', 3, NULL, '2019-04-21 22:54:46', '2019-08-30 11:35:59', '0', 5);
INSERT INTO `sys_dept`
VALUES (12, '市场部', 4, '主要负责市场方面工作的部门', '2019-04-30 14:31:46', '2019-08-30 11:35:44', '0', 5);
INSERT INTO `sys_dept`
VALUES (14, '研发一部', 3, '研发一部', '2019-09-02 12:44:31', '2019-09-03 19:07:01', '0', 6);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`
(
    `id`          int(64)                                                       NOT NULL AUTO_INCREMENT COMMENT 'id',
    `dict_name`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
    `dict_code`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT NULL COMMENT '字典编码',
    `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '描述',
    `sort`        int(4)                                                                 DEFAULT NULL COMMENT '排序（升序）',
    `create_time` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT NULL COMMENT '备注信息',
    `del_flag`    int(1)                                                        NOT NULL DEFAULT '0' COMMENT '删除标记',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`
VALUES (4, '用户状态', 'user_status', '用户状态', NULL, '2019-05-26 15:13:55', '2019-09-02 17:47:49', '用户状态', 0);
INSERT INTO `sys_dict`
VALUES (10, '测试', '测试', '测试', NULL, '2019-09-02 17:54:10', '2019-09-02 17:55:59', '测试', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`
(
    `id`          varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `dict_id`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci      DEFAULT NULL COMMENT '字典id',
    `item_text`   varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci     DEFAULT NULL COMMENT '字典项文本',
    `item_value`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci     DEFAULT NULL COMMENT '字典项值',
    `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci     DEFAULT NULL COMMENT '描述',
    `status`      int(11)                                                     DEFAULT NULL COMMENT '状态（1启用 0不启用）',
    `create_time` timestamp                                              NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                              NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `index_table_dict_id` (`dict_id`) USING BTREE,
    KEY `index_table_dict_status` (`status`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='字典详情表';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_item`
VALUES (1, '10', '测试', '测试', '测试', 1, '2019-09-02 18:47:17', '2019-09-02 18:47:17');
COMMIT;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`
(
    `id`          int(11)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `job_name`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '岗位名称',
    `dept_id`     int(11)                                                DEFAULT NULL COMMENT '部门id',
    `sort`        int(10)                                                DEFAULT NULL COMMENT '排序',
    `create_time` timestamp NULL                                         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NULL                                         DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='岗位管理';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_job`
VALUES (3, '全栈开发', 6, 1, '2019-05-03 10:31:03', NULL);
INSERT INTO `sys_job`
VALUES (4, '软件测试', 6, 2, '2019-05-03 10:31:41', NULL);
INSERT INTO `sys_job`
VALUES (5, '财务总监', 7, 3, '2019-06-16 00:44:59', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
    `id`               int(18)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `application_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '服务名称',
    `request_ip`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '操作IP',
    `type`             int(3)                                                  DEFAULT NULL COMMENT '操作类型 1 操作记录 2异常记录',
    `user_name`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '操作人',
    `user_id`          varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '操作人id',
    `client_id`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '客户端id',
    `description`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '操作描述',
    `action_method`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '请求方法',
    `action_url`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '请求url',
    `params`           varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求参数',
    `ua`               varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '浏览器',
    `class_path`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '类路径',
    `request_method`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '请求方法',
    `operate_type`     int(3)                                                  DEFAULT NULL COMMENT '操作类型 1查询/获取，2添加，3修改，4删除',
    `start_time`       timestamp NULL                                          DEFAULT NULL COMMENT '开始时间',
    `finish_time`      timestamp NULL                                          DEFAULT NULL COMMENT '完成时间',
    `consuming_time`   bigint(11)                                              DEFAULT NULL COMMENT '消耗时间',
    `ex_detail`        varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '异常描述',
    `ex_desc`          varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '异常详情信息',
    `tenant_id`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `index_type` (`type`) USING BTREE COMMENT '日志类型'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1369
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='系统日志';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `menu_id`     int(11)                                               NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名称',
    `perms`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin      DEFAULT NULL COMMENT '菜单权限标识',
    `path`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin     DEFAULT NULL COMMENT '前端跳转URL',
    `component`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin     DEFAULT NULL COMMENT '菜单组件',
    `parent_id`   int(11)                                                    DEFAULT NULL COMMENT '父菜单ID',
    `icon`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin      DEFAULT NULL COMMENT '图标',
    `sort`        int(11)                                                    DEFAULT '1' COMMENT '排序',
    `type`        char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '菜单类型 （类型   0：目录   1：菜单   2：按钮）',
    `create_time` timestamp                                             NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                             NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `del_flag`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin          DEFAULT '0' COMMENT '逻辑删除标记(0--正常 1--删除)',
    `is_frame`    tinyint(1)                                                 DEFAULT NULL COMMENT '是否为外链',
    `keep_alive`  tinyint(1)                                                 DEFAULT NULL COMMENT '是否缓存路由',
    `hidden`      tinyint(1)                                                 DEFAULT NULL COMMENT '是否隐藏菜单',
    `redirect`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin      DEFAULT NULL COMMENT '重定向',
    `always_show` tinyint(1)                                                 DEFAULT NULL COMMENT '聚合路由',
    PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 75
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu`
VALUES (1, '权限管理', '', '/admin', 'RouteView', 0, 'cluster', 0, '0', '2019-04-21 22:45:08', '2019-09-12 11:43:30', '0',
        1, 1, 0, '/admin/user', 1);
INSERT INTO `sys_menu`
VALUES (2, '用户管理', '', 'user', 'admin/User', 1, 'user', 1, '1', '2019-04-21 22:49:22', '2019-09-12 10:03:05', '0', 1, 1,
        0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (3, '部门管理', '', 'dept', 'admin/Dept', 1, 'cluster', 2, '1', '2019-04-21 22:52:11', '2019-09-17 19:01:46', '0', 1,
        1, 0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (5, '用户新增', 'sys:user:add', '', NULL, 2, '', 0, '2', '2019-04-22 13:09:11', '2019-09-11 17:00:53', '0', 1, 1,
        NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (6, '系统管理', '', '/sys', 'RouteView', 0, 'setting', 1, '0', '2019-04-22 23:48:02', '2019-09-12 11:34:39', '0', 1,
        1, 0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (7, '日志管理', '', 'log', 'system/Log', 6, 'schedule', 1, '1', '2019-04-22 23:59:40', '2019-09-12 11:41:02', '0', 1,
        1, 0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (11, '部门新增', 'sys:dept:add', '', NULL, 3, '', 0, '2', '2019-04-25 11:09:50', '2019-09-11 17:46:59', '0', NULL,
        NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (13, '角色管理', '', 'role', 'admin/Role', 1, 'team', 1, '1', '2019-04-29 21:08:28', '2019-09-12 11:33:43', '0', 1,
        1, 0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (14, '用户修改', 'sys:user:update', '', NULL, 2, '', 0, '2', '2019-04-30 23:43:31', '2019-06-08 11:22:23', '0', 1,
        NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (15, '角色新增', 'sys:role:add', '', NULL, 13, '', 0, '2', '2019-05-01 08:49:21', '2019-06-09 16:39:48', '0', 1,
        NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (16, '菜单管理', '', 'menu', 'admin/Menu', 1, 'menu', 3, '1', '2019-05-03 15:26:58', '2019-09-12 10:03:43', '0', 1,
        1, 0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (17, '岗位管理', '', 'job', 'admin/Job', 1, 'audit', 4, '1', '2019-05-03 15:27:25', '2019-09-17 19:07:42', '0', 1, 1,
        0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (27, '日志删除', 'sys:log:delete', '', '', 7, '', 0, '2', '2019-05-06 22:47:47', '2019-06-08 13:15:05', '0', 1, NULL,
        NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (28, '菜单增加', 'sys:menu:add', '', '', 16, '', 0, '2', '2019-05-08 16:09:43', '2019-06-08 13:14:02', '0', 1, NULL,
        NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (29, '菜单修改', 'sys:menu:update', '', '', 16, '', 0, '2', '2019-05-08 16:10:06', '2019-06-08 13:14:05', '0', 1,
        NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (30, '部门修改', 'sys:dept:update', '', '', 3, '', 0, '2', '2019-05-08 23:49:54', '2019-06-08 13:13:49', '0', 1,
        NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (31, '部门删除', 'sys:dept:delete', '', '', 3, '', 0, '2', '2019-05-08 23:53:41', '2019-06-08 13:13:52', '0', 1,
        NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (33, '用户查看', 'sys:user:view', '', '', 2, '', 0, '2', '2019-05-12 18:59:46', '2019-06-08 11:23:01', '0', 1, NULL,
        NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (34, '角色修改', 'sys:role:update', '', '', 13, '', 0, '2', '2019-05-12 19:05:03', '2019-06-08 13:13:29', '0', 1,
        NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (35, '用户删除', 'sys:user:delete', '', '', 2, '', 0, '2', '2019-05-12 19:08:13', '2019-06-08 11:23:07', '0', 1,
        NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (36, '菜单删除', 'sys:menu:delete', '', '', 16, '', 0, '2', '2019-05-12 19:10:02', '2019-06-08 13:14:09', '0', 1,
        NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (37, '角色删除', 'sys:role:delete', '', '', 13, '', 0, '2', '2019-05-12 19:11:14', '2019-06-08 13:13:34', '0', 1,
        NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (38, '角色查看', 'sys:role:view', '', '', 13, '', 0, '2', '2019-05-12 19:11:37', '2019-06-08 13:13:37', '0', 1, NULL,
        NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (39, '岗位新增', 'sys:job:add', '', '', 17, '', 0, '2', '2019-05-12 19:15:57', '2019-06-08 13:14:40', '0', 1, NULL,
        NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (40, '岗位修改', 'sys:job:update', '', '', 17, '', 0, '2', '2019-05-12 19:16:30', '2019-06-08 13:14:43', '0', 1,
        NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (41, '岗位查看', 'sys:job:view', '', '', 17, '', 0, '2', '2019-05-12 19:16:44', '2019-06-08 13:14:47', '0', 1, NULL,
        NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (42, '岗位删除', 'sys:job:delete', '', '', 17, '', 0, '2', '2019-05-12 19:17:16', '2019-06-08 13:14:50', '0', 1,
        NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (43, '字典管理', '', 'dict', 'system/Dict', 6, 'tag', 0, '1', '2019-05-16 18:17:32', '2019-09-12 10:05:16', '0', 1,
        1, 0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (44, '部门查看', 'sys:dept:view', '', '', 3, '', 0, '2', '2019-06-07 20:50:31', '2019-06-08 13:13:55', '0', 1, NULL,
        NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (45, '字典查看', 'sys:dipt:view', '', '', 43, '', 0, '2', '2019-06-07 20:55:42', '2019-06-08 13:14:56', '0', 1, NULL,
        NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (46, '菜单查看', 'sys:menu:view', '', '', 16, '', 0, '2', '2019-06-08 13:14:32', NULL, '0', 1, NULL, NULL, NULL,
        NULL);
INSERT INTO `sys_menu`
VALUES (47, '修改密码', 'sys:user:updatePass', '', '', 2, '', 0, '2', '2019-06-15 09:43:20', '2019-06-15 09:43:20', '0', 1,
        NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES (48, '修改邮箱', 'sys:user:updateEmail', '', '', 2, '', 0, '2', '2019-06-15 09:43:58', '2019-08-31 15:33:48', '0', 1,
        0, 0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (61, '系统监控', NULL, '/monitor', 'RouteView', 0, 'dashboard', 2, '0', '2019-09-12 10:08:28', '2019-09-17 17:27:11',
        '0', NULL, 1, 0, '/monitor/redis', 0);
INSERT INTO `sys_menu`
VALUES (62, '缓存监控', NULL, 'redis', 'monitor/RedisInfo', 61, 'calculator', 1, '1', '2019-09-12 10:09:13',
        '2019-09-12 10:09:13', '0', NULL, 1, 0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (63, '磁盘监控', NULL, 'disk', 'monitor/DiskMonitoring', 61, 'bulb', 1, '1', '2019-09-12 10:09:48',
        '2019-09-12 10:09:48', '0', NULL, 1, 0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (64, '客户端管理', NULL, 'client', 'system/Client', 6, 'user-add', 1, '1', '2019-09-12 11:44:56',
        '2019-09-12 11:46:09', '0', NULL, 1, 0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (71, '重置密码', 'sys:user:rest', NULL, NULL, 2, NULL, 1, '2', '2019-09-17 15:05:27', '2019-09-17 15:05:27', '0',
        NULL, 0, 0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (72, '添加客户端', 'sys:client:add', NULL, NULL, 64, NULL, 1, '2', '2019-09-18 15:00:56', '2019-09-18 15:00:56', '0',
        NULL, 0, 0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (73, '删除客户端', 'sys:client:del', NULL, NULL, 64, NULL, 1, '2', '2019-09-18 15:01:20', '2019-09-18 15:01:20', '0',
        NULL, 0, 0, NULL, 0);
INSERT INTO `sys_menu`
VALUES (74, '编辑客户端', 'sys:client:edit', NULL, NULL, 64, NULL, 1, '2', '2019-09-18 15:01:43', '2019-09-18 15:01:43', '0',
        NULL, 0, 0, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `role_id`     int(11)                                               NOT NULL AUTO_INCREMENT COMMENT '角色主键',
    `role_name`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
    `role_code`   varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '角色标识',
    `role_desc`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin         DEFAULT NULL COMMENT '角色描述',
    `create_time` timestamp                                             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                             NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `del_flag`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin              DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
    `ds_type`     int(1)                                                         DEFAULT NULL COMMENT '数据权限类型',
    `ds_scope`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin         DEFAULT NULL COMMENT '数据权限范围 1 全部 2 本级 3 本级以及子级 4 自定义',
    `tenant_id`   int(11)                                                        DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role`
VALUES (5, '管理员', 'ROLE_ADMIN', '管理员', '2019-04-22 21:53:38', '2019-09-16 09:10:56', '0', 1, '4,5,6,7,12,14', NULL);
INSERT INTO `sys_role`
VALUES (7, '开发人员', 'DEV_ROLE', '开发人员', '2019-04-24 21:11:28', '2019-09-10 13:50:35', '0', 3, '6,14', NULL);
INSERT INTO `sys_role`
VALUES (8, '演示角色', 'ROLE_SHOW', '站点演示角色', '2019-09-20 05:28:31', '2019-09-20 05:28:31', '0', 1, '4,5,6,7,12,14', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`
(
    `id`      int(20) NOT NULL AUTO_INCREMENT COMMENT '部门主键ID',
    `role_id` int(20) DEFAULT NULL COMMENT '角色ID',
    `dept_id` int(20) DEFAULT NULL COMMENT '部门ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 234
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='角色与部门对应关系';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_dept`
VALUES (74, 0, 4);
INSERT INTO `sys_role_dept`
VALUES (75, 0, 5);
INSERT INTO `sys_role_dept`
VALUES (76, 0, 6);
INSERT INTO `sys_role_dept`
VALUES (77, 0, 7);
INSERT INTO `sys_role_dept`
VALUES (78, 0, 12);
INSERT INTO `sys_role_dept`
VALUES (99, 0, 4);
INSERT INTO `sys_role_dept`
VALUES (100, 0, 5);
INSERT INTO `sys_role_dept`
VALUES (101, 0, 6);
INSERT INTO `sys_role_dept`
VALUES (102, 0, 7);
INSERT INTO `sys_role_dept`
VALUES (103, 0, 12);
INSERT INTO `sys_role_dept`
VALUES (104, 0, 4);
INSERT INTO `sys_role_dept`
VALUES (105, 0, 5);
INSERT INTO `sys_role_dept`
VALUES (106, 0, 6);
INSERT INTO `sys_role_dept`
VALUES (107, 0, 7);
INSERT INTO `sys_role_dept`
VALUES (108, 0, 12);
INSERT INTO `sys_role_dept`
VALUES (109, 0, 4);
INSERT INTO `sys_role_dept`
VALUES (110, 0, 5);
INSERT INTO `sys_role_dept`
VALUES (111, 0, 6);
INSERT INTO `sys_role_dept`
VALUES (112, 0, 7);
INSERT INTO `sys_role_dept`
VALUES (113, 0, 12);
INSERT INTO `sys_role_dept`
VALUES (197, 7, 6);
INSERT INTO `sys_role_dept`
VALUES (198, 7, 14);
INSERT INTO `sys_role_dept`
VALUES (222, 5, 4);
INSERT INTO `sys_role_dept`
VALUES (223, 5, 5);
INSERT INTO `sys_role_dept`
VALUES (224, 5, 6);
INSERT INTO `sys_role_dept`
VALUES (225, 5, 7);
INSERT INTO `sys_role_dept`
VALUES (226, 5, 12);
INSERT INTO `sys_role_dept`
VALUES (227, 5, 14);
INSERT INTO `sys_role_dept`
VALUES (228, 8, 4);
INSERT INTO `sys_role_dept`
VALUES (229, 8, 5);
INSERT INTO `sys_role_dept`
VALUES (230, 8, 6);
INSERT INTO `sys_role_dept`
VALUES (231, 8, 7);
INSERT INTO `sys_role_dept`
VALUES (232, 8, 12);
INSERT INTO `sys_role_dept`
VALUES (233, 8, 14);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `role_id` int(11) NOT NULL COMMENT '角色ID',
    `menu_id` int(11) NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `index_role_id` (`role_id`) USING BTREE COMMENT '角色Id',
    KEY `index_menu_id` (`menu_id`) USING BTREE COMMENT '菜单Id'
) ENGINE = InnoDB
  AUTO_INCREMENT = 2565
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='角色菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu`
VALUES (1456, 7, 1);
INSERT INTO `sys_role_menu`
VALUES (1457, 7, 2);
INSERT INTO `sys_role_menu`
VALUES (1458, 7, 33);
INSERT INTO `sys_role_menu`
VALUES (1459, 7, 13);
INSERT INTO `sys_role_menu`
VALUES (1460, 7, 38);
INSERT INTO `sys_role_menu`
VALUES (1461, 7, 3);
INSERT INTO `sys_role_menu`
VALUES (1462, 7, 44);
INSERT INTO `sys_role_menu`
VALUES (1463, 7, 16);
INSERT INTO `sys_role_menu`
VALUES (1464, 7, 17);
INSERT INTO `sys_role_menu`
VALUES (1465, 7, 41);
INSERT INTO `sys_role_menu`
VALUES (1466, 7, 6);
INSERT INTO `sys_role_menu`
VALUES (1467, 7, 7);
INSERT INTO `sys_role_menu`
VALUES (1468, 7, 18);
INSERT INTO `sys_role_menu`
VALUES (2497, 5, 1);
INSERT INTO `sys_role_menu`
VALUES (2498, 5, 56);
INSERT INTO `sys_role_menu`
VALUES (2499, 5, 2);
INSERT INTO `sys_role_menu`
VALUES (2500, 5, 5);
INSERT INTO `sys_role_menu`
VALUES (2501, 5, 14);
INSERT INTO `sys_role_menu`
VALUES (2502, 5, 33);
INSERT INTO `sys_role_menu`
VALUES (2503, 5, 35);
INSERT INTO `sys_role_menu`
VALUES (2504, 5, 47);
INSERT INTO `sys_role_menu`
VALUES (2505, 5, 48);
INSERT INTO `sys_role_menu`
VALUES (2506, 5, 13);
INSERT INTO `sys_role_menu`
VALUES (2507, 5, 15);
INSERT INTO `sys_role_menu`
VALUES (2508, 5, 34);
INSERT INTO `sys_role_menu`
VALUES (2509, 5, 38);
INSERT INTO `sys_role_menu`
VALUES (2510, 5, 3);
INSERT INTO `sys_role_menu`
VALUES (2511, 5, 11);
INSERT INTO `sys_role_menu`
VALUES (2512, 5, 30);
INSERT INTO `sys_role_menu`
VALUES (2513, 5, 31);
INSERT INTO `sys_role_menu`
VALUES (2514, 5, 44);
INSERT INTO `sys_role_menu`
VALUES (2515, 5, 16);
INSERT INTO `sys_role_menu`
VALUES (2516, 5, 28);
INSERT INTO `sys_role_menu`
VALUES (2517, 5, 29);
INSERT INTO `sys_role_menu`
VALUES (2518, 5, 36);
INSERT INTO `sys_role_menu`
VALUES (2519, 5, 46);
INSERT INTO `sys_role_menu`
VALUES (2520, 5, 17);
INSERT INTO `sys_role_menu`
VALUES (2521, 5, 39);
INSERT INTO `sys_role_menu`
VALUES (2522, 5, 40);
INSERT INTO `sys_role_menu`
VALUES (2523, 5, 41);
INSERT INTO `sys_role_menu`
VALUES (2524, 5, 42);
INSERT INTO `sys_role_menu`
VALUES (2525, 5, 51);
INSERT INTO `sys_role_menu`
VALUES (2526, 5, 52);
INSERT INTO `sys_role_menu`
VALUES (2527, 5, 54);
INSERT INTO `sys_role_menu`
VALUES (2528, 5, 6);
INSERT INTO `sys_role_menu`
VALUES (2529, 5, 43);
INSERT INTO `sys_role_menu`
VALUES (2530, 5, 45);
INSERT INTO `sys_role_menu`
VALUES (2531, 5, 53);
INSERT INTO `sys_role_menu`
VALUES (2532, 5, 55);
INSERT INTO `sys_role_menu`
VALUES (2533, 5, 7);
INSERT INTO `sys_role_menu`
VALUES (2534, 5, 27);
INSERT INTO `sys_role_menu`
VALUES (2535, 5, 18);
INSERT INTO `sys_role_menu`
VALUES (2536, 5, 25);
INSERT INTO `sys_role_menu`
VALUES (2537, 5, 26);
INSERT INTO `sys_role_menu`
VALUES (2538, 5, 37);
INSERT INTO `sys_role_menu`
VALUES (2539, 5, 61);
INSERT INTO `sys_role_menu`
VALUES (2540, 5, 62);
INSERT INTO `sys_role_menu`
VALUES (2541, 5, 63);
INSERT INTO `sys_role_menu`
VALUES (2542, 5, 64);
INSERT INTO `sys_role_menu`
VALUES (2543, 5, 71);
INSERT INTO `sys_role_menu`
VALUES (2544, 5, 72);
INSERT INTO `sys_role_menu`
VALUES (2545, 5, 73);
INSERT INTO `sys_role_menu`
VALUES (2546, 5, 74);
INSERT INTO `sys_role_menu`
VALUES (2547, 8, 2);
INSERT INTO `sys_role_menu`
VALUES (2548, 8, 33);
INSERT INTO `sys_role_menu`
VALUES (2549, 8, 44);
INSERT INTO `sys_role_menu`
VALUES (2550, 8, 3);
INSERT INTO `sys_role_menu`
VALUES (2551, 8, 13);
INSERT INTO `sys_role_menu`
VALUES (2552, 8, 38);
INSERT INTO `sys_role_menu`
VALUES (2553, 8, 17);
INSERT INTO `sys_role_menu`
VALUES (2554, 8, 16);
INSERT INTO `sys_role_menu`
VALUES (2555, 8, 46);
INSERT INTO `sys_role_menu`
VALUES (2556, 8, 41);
INSERT INTO `sys_role_menu`
VALUES (2557, 8, 6);
INSERT INTO `sys_role_menu`
VALUES (2558, 8, 7);
INSERT INTO `sys_role_menu`
VALUES (2559, 8, 43);
INSERT INTO `sys_role_menu`
VALUES (2560, 8, 45);
INSERT INTO `sys_role_menu`
VALUES (2561, 8, 64);
INSERT INTO `sys_role_menu`
VALUES (2562, 8, 61);
INSERT INTO `sys_role_menu`
VALUES (2563, 8, 62);
INSERT INTO `sys_role_menu`
VALUES (2564, 8, 63);
COMMIT;

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant`
(
    `id`          int(11)   NOT NULL AUTO_INCREMENT COMMENT '租户id',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户名称',
    `code`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '租户编号',
    `start_time`  timestamp NULL                                                DEFAULT NULL COMMENT '授权开始时间',
    `end_time`    timestamp NULL                                                DEFAULT NULL COMMENT '授权结束时间',
    `status`      int(11)   NOT NULL                                            DEFAULT '0' COMMENT '0正常 9-冻结',
    `del_flag`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      DEFAULT '0' COMMENT '删除标记',
    `create_time` timestamp NOT NULL                                            DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL                                            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8 COMMENT ='租户表';

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
BEGIN;
INSERT INTO `sys_tenant`
VALUES (1, '上海某某公司', '1', '2019-08-10 00:00:00', '2020-08-10 00:00:00', 0, '0', '2019-08-10 10:13:12',
        '2019-08-10 12:44:52');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `user_id`     int(11)                                                NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `nickname`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin       DEFAULT NULL COMMENT '昵称',
    `username`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '用户名',
    `type`        int(3)                                                      DEFAULT 1 COMMENT '用户类型 1app用户 2公司管理用户 3商户用户',
    `sex`         int(3)                                                      DEFAULT 0 COMMENT ' 性别 1男 2女 0未知',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
    `mobile`      varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '手机号码',
    `email`       varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '邮箱',
    `dept_id`     int(10)                                                     DEFAULT NULL COMMENT '部门ID',
    `job_id`      int(10)                                                     DEFAULT NULL COMMENT '岗位ID',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin      DEFAULT NULL COMMENT '头像',
    `create_time` timestamp                                              NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                              NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `lock_flag`   char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin           DEFAULT '0' COMMENT '0-正常，1-锁定',
    `del_flag`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin           DEFAULT '0' COMMENT '0-正常，1-删除',
    PRIMARY KEY (`user_id`) USING BTREE,
    KEY `user_idx_dept_id` (`dept_id`) USING BTREE,
    UNIQUE (`username`),
    unique (`password`),
    unique (`mobile`),
    unique (`email`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user`
VALUES (4, '昵称admin', 'admin', 1, 0, '$2a$10$bHivp.Dmy.eISTg3NzcifOaQyhw8cIlfys8IqmGo.6ZPiYjC1H0E.', '15730445330',
        'lihaodongmail@163.com', 6,
        3,
        NULL, '2019-04-23 23:29:51', '2019-09-20 05:26:54', '0', '0');
INSERT INTO `sys_user`
VALUES (8, '昵称root', 'root', 1, 0, '$2a$10$70OSxSXesCk9teXmMqyMMu8i3I3VDVqAOKMzUGUGhJtfaQCGFff5a', '15730445339',
        'im.lihaodong@gmail.com', 6, 3,
        NULL, '2019-09-20 05:26:08', '2019-09-20 05:29:53', '0', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` int(10) NOT NULL COMMENT '用户ID',
    `role_id` int(10) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 71
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role`
VALUES (69, 8, 5);
INSERT INTO `sys_user_role`
VALUES (70, 4, 8);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_social
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_social`;
CREATE TABLE `sys_user_social`
(
    `user_id`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户主键',
    `provider_id`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '社交类型',
    `provider_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '社交的Id',
    `display_name`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '显示名称',
    `image_url`        varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci      DEFAULT NULL COMMENT '头像地址',
    `create_time`      timestamp                                               NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`user_id`, `provider_id`, `provider_user_id`) USING BTREE,
    UNIQUE KEY `UserConnectionRank` (`user_id`, `provider_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='社交登录表';

-- ----------------------------
-- Records of sys_user_social
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_social`
VALUES ('4', 'gitee', '5223301', '小颖啊', 'https://gitee.com/assets/no_portrait.png', '2019-09-16 16:54:47');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `sys_gitee_user`;
CREATE TABLE `sys_gitee_user`
(
    `id`            int(11)   NOT NULL COMMENT '主键ID',
    `login`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT 'login',
    `name`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '名称',
    `avatar_url`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '头像',
    `url`           varchar(1200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'url',
    `html_url`      varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT 'htmlUrl',
    `followers_url` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT 'followersUrl',
    `following_url` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT 'followingUrl',
    `blog`          varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT 'blog',
    `create_time`   timestamp NULL                                           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='gitee用户表';

DROP TABLE IF EXISTS `sys_github_user`;
CREATE TABLE `sys_github_user`
(
    `id`                int(11)   NOT NULL COMMENT '主键ID',
    `name`              varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '名称',
    `username`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '名称',
    `location`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT 'location',
    `company`           varchar(1200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'company',
    `blog`              varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT 'blog',
    `email`             varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT 'email',
    `created_date`      varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT 'created_date',
    `profile_image_url` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT 'profile_image_url',
    `avatar_url`        varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT 'avatar_url',
    `create_time`       timestamp NULL                                           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='github用户表';


DROP TABLE IF EXISTS `sys_qq_user`;
CREATE TABLE `sys_qq_user`
(
    `id`                 int(11)   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `open_id`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT 'openId',
    `ret`                varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '返回码',
    `msg`                varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码。',
    `is_lost`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '不知道什么东西，文档上没写，但是实际api返回里有',
    `province`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '省',
    `city`               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '市',
    `year`               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '年',
    `nickname`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '昵称',
    `figure_url`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '大小为30×30像素的QQ空间头像URL',
    `figure_url1`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '大小为50×50像素的QQ空间头像URL',
    `figure_url2`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '大小为100×100像素的QQ空间头像URL',
    `figure_url_qq1`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '大小为40×40像素的QQ头像URL',
    `figure_url_qq2`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100×100的头像，但40×40像素则是一定会有',
    `gender`             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '性别',
    `is_yellow_vip`      varchar(1200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标识用户是否为黄钻用户（0：不是；1：是）',
    `vip`                varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT ' 标识用户是否为黄钻用户（0：不是；1：是）',
    `yellow_vip_level`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT ' 黄钻等级',
    `level`              varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT ' 黄钻等级',
    `is_yellow_year_vip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT ' 标识是否为年费黄钻用户（0：不是； 1：是）',
    `create_time`        timestamp NULL                                           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='qq用户表';
