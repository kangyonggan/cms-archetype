DROP DATABASE IF EXISTS ${parentArtifactId};

CREATE DATABASE ${parentArtifactId}
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE ${parentArtifactId};

-- ----------------------------
--  Table structure for tb_user
-- ----------------------------
DROP TABLE
IF EXISTS tb_user;

CREATE TABLE tb_user
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  username     VARCHAR(32)                           NOT NULL
  COMMENT '用户名',
  realname     VARCHAR(32)                           NOT NULL
  COMMENT '真实姓名',
  password     VARCHAR(64)                           NOT NULL
  COMMENT '密码',
  salt         VARCHAR(64)                           NOT NULL
  COMMENT '密码盐',
  avatar       VARCHAR(256)                          NOT NULL                    DEFAULT ''
  COMMENT '头像',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '用户表';
CREATE UNIQUE INDEX username_UNIQUE
  ON tb_user (username);

-- ----------------------------
--  Table structure for tb_role
-- ----------------------------
DROP TABLE
IF EXISTS tb_role;

CREATE TABLE tb_role
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(32)                           NOT NULL
  COMMENT '角色代码',
  name         VARCHAR(32)                           NOT NULL
  COMMENT '角色名称',
  is_deleted   TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '角色表';
CREATE UNIQUE INDEX code_UNIQUE
  ON tb_role (code);

-- ----------------------------
--  Table structure for tb_menu
-- ----------------------------
DROP TABLE
IF EXISTS tb_menu;

CREATE TABLE tb_menu
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(32)                           NOT NULL
  COMMENT '菜单代码',
  name         VARCHAR(32)                           NOT NULL
  COMMENT '菜单名称',
  pcode        VARCHAR(32)                           NOT NULL                DEFAULT ''
  COMMENT '父菜单代码',
  url          VARCHAR(128)                          NOT NULL                DEFAULT ''
  COMMENT '菜单地址',
  sort         INT(11)                               NOT NULL                DEFAULT 0
  COMMENT '菜单排序(从0开始)',
  icon         VARCHAR(128)                          NOT NULL                DEFAULT ''
  COMMENT '菜单图标的样式',
  is_deleted   TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '菜单表';
CREATE INDEX sort_ix
  ON tb_menu (sort);
CREATE UNIQUE INDEX code_UNIQUE
  ON tb_menu (code);

-- ----------------------------
--  Table structure for tb_user_role
-- ----------------------------
DROP TABLE
IF EXISTS tb_user_role;

CREATE TABLE tb_user_role
(
  username  VARCHAR(32) NOT NULL
  COMMENT '用户名',
  role_code VARCHAR(32) NOT NULL
  COMMENT '角色代码',
  PRIMARY KEY (username, role_code)
)
  COMMENT '用户角色表';

-- ----------------------------
--  Table structure for rtb_ole_menu
-- ----------------------------
DROP TABLE
IF EXISTS tb_role_menu;

CREATE TABLE tb_role_menu
(
  role_code VARCHAR(32) NOT NULL
  COMMENT '角色代码',
  menu_code VARCHAR(32) NOT NULL
  COMMENT '菜单代码',
  PRIMARY KEY (role_code, menu_code)
)
  COMMENT '角色菜单表';

-- ----------------------------
--  Table structure for tb_login_log
-- ----------------------------
DROP TABLE
IF EXISTS tb_login_log;

CREATE TABLE tb_login_log
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  username     VARCHAR(32)                           NOT NULL
  COMMENT '用户名',
  ip           VARCHAR(20)                           NOT NULL
  COMMENT '登录IP',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '登录日志表';
CREATE INDEX ix_username
  ON tb_login_log (username);
CREATE INDEX ix_created_time
  ON tb_login_log (created_time);

-- ----------------------------
--  Table structure for tb_monitor
-- ----------------------------
DROP TABLE
IF EXISTS tb_monitor;

CREATE TABLE tb_monitor
(
  id               BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  app              VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '应用名称',
  type             VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '监控类型',
  description      VARCHAR(2048)                         NOT NULL                    DEFAULT ''
  COMMENT '监控描述',
  begin_time       TIMESTAMP                             NULL
  COMMENT '开始时间',
  end_time         TIMESTAMP                             NULL
  COMMENT '结束时间',
  has_return_value TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '是否有返回值:{0:没有返回值, 1:有返回值}',
  return_value     LONGTEXT                              NOT NULL
  COMMENT '返回值',
  args             LONGTEXT                              NOT NULL
  COMMENT '参数',
  username         VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '用户名',
  is_deleted       TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time     TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time     TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '监控表';
CREATE INDEX ix_app
  ON tb_monitor (app);
CREATE INDEX ix_type
  ON tb_monitor (type);

-- ----------------------------
--  Table structure for tb_preference
-- ----------------------------
DROP TABLE
IF EXISTS tb_preference;

CREATE TABLE tb_preference
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  username     VARCHAR(32)                           NOT NULL
  COMMENT '用户名',
  type         VARCHAR(20)                           NOT NULL
  COMMENT '偏好类型',
  name         VARCHAR(64)                           NOT NULL
  COMMENT '偏好名称',
  value        VARCHAR(256)                          NOT NULL
  COMMENT '偏好的值',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '偏好表';
CREATE INDEX ix_username
  ON tb_preference (username);
CREATE UNIQUE INDEX username_type_name_UNIQUE
  ON tb_preference (username, type, name);

-- ----------------------------
--  Table structure for tb_dictionary_type
-- ----------------------------
DROP TABLE
IF EXISTS tb_dictionary_type;

CREATE TABLE tb_dictionary_type
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  type         VARCHAR(20)                           NOT NULL
  COMMENT '字典类型',
  name         VARCHAR(64)                           NOT NULL
  COMMENT '类型名称',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '字典表';
CREATE INDEX ix_type
  ON tb_dictionary_type (type);

-- ----------------------------
--  Table structure for tb_dictionary
-- ----------------------------
DROP TABLE
IF EXISTS tb_dictionary;

CREATE TABLE tb_dictionary
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  type         VARCHAR(20)                           NOT NULL
  COMMENT '字典类型',
  code         VARCHAR(64)                           NOT NULL
  COMMENT '字典代码',
  value        VARCHAR(256)                          NOT NULL
  COMMENT '字典的值',
  remark       VARCHAR(256)                          NOT NULL                    DEFAULT ''
  COMMENT '备注',
  sort         INT(11)                               NOT NULL                    DEFAULT 0
  COMMENT '排序（从0开始）',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '字典表';
CREATE UNIQUE INDEX type_code_UNIQUE
  ON tb_dictionary (type, code);

#====================初始数据====================#

-- ----------------------------
--  data for tb_user
-- ----------------------------
INSERT INTO tb_user
(username, realname, password, salt)
VALUES
  # 密码：111111
  ('admin', '管理员', 'df9e06125caf2f421535bc725b9c96d329ae377b', '5cf6b1a302cabcbd'),
  ('xiaoxin', '小新', 'df9e06125caf2f421535bc725b9c96d329ae377b', '5cf6b1a302cabcbd');

-- ----------------------------
--  data for tb_role
-- ----------------------------
INSERT INTO tb_role
(code, name)
VALUES
  ('ROLE_ADMIN', '超级管理员'),
  ('ROLE_USER', '普通用户');

-- ----------------------------
--  data for tb_menu
-- ----------------------------
INSERT INTO tb_menu
(code, name, pcode, url, sort, icon)
VALUES
  ('DASHBOARD', '工作台', '', 'index', 0, 'menu-icon fa fa-dashboard'),

  ('SYSTEM', '系统', 'DASHBOARD', 'system', 1, 'menu-icon fa fa-cogs'),
  ('SYSTEM_USER', '用户管理', 'SYSTEM', 'system/user', 0, ''),
  ('SYSTEM_ROLE', '角色管理', 'SYSTEM', 'system/role', 1, ''),
  ('SYSTEM_MENU', '菜单管理', 'SYSTEM', 'system/menu', 2, ''),
  ('SYSTEM_DICT', '字典管理', 'SYSTEM', 'system/dictionary', 3, ''),
  ('SYSTEM_DICT_TYPE', '字典类型', 'SYSTEM_DICT', 'system/dict/type', 0, ''),
  ('SYSTEM_DICT_CONTENT', '字典内容', 'SYSTEM_DICT', 'system/dict/content', 1, ''),
  ('SYSTEM_PREFERENCE', '偏好管理', 'SYSTEM', 'system/preference', 4, ''),

  ('MONITOR', '监控', 'DASHBOARD', 'monitor', 2, 'menu-icon fa fa-laptop'),
  ('MONITOR_LOGIN', '登录日志', 'MONITOR', 'monitor/login', 0, ''),
  ('MONITOR_OPERATE', '操作日志', 'MONITOR', 'monitor/operate', 1, ''),

  ('USER', '我的', 'DASHBOARD', 'user', 3, 'menu-icon fa fa-user'),
  ('USER_INFO', '基本信息', 'USER', 'user/info', 0, '');

-- ----------------------------
--  data for tb_user_role
-- ----------------------------
INSERT INTO tb_user_role
VALUES
  ('admin', 'ROLE_ADMIN'),
  ('admin', 'ROLE_USER');

-- ----------------------------
--  data for tb_role_menu
-- ----------------------------
INSERT INTO tb_role_menu SELECT
                           'ROLE_ADMIN',
                           code
                         FROM tb_menu;

INSERT INTO tb_role_menu SELECT
                           'ROLE_USER',
                           code
                         FROM tb_menu
                         WHERE code LIKE 'USER%' OR code = 'DASHBOARD';

INSERT INTO tb_dictionary_type
(type, name) VALUES
  ('MONITOR_TYPE', '监控类型');

INSERT INTO tb_dictionary (type, code, value, sort)
VALUES
  ('MONITOR_TYPE', 'INSERT', '新增', 0),
  ('MONITOR_TYPE', 'UPDATE', '更新', 1),
  ('MONITOR_TYPE', 'DELETE', '删除', 2);