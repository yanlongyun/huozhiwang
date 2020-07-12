CREATE TABLE `ods_user` (
 `pk` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ods主键',
        `id` bigint(20) unsigned NOT NULL COMMENT '主键',
        `phone` varchar(32) DEFAULT '' COMMENT '手机，加唯一索引，可用作登陆',
        `password` varchar(256) DEFAULT '' COMMENT '密码',
        `salt` varchar(32) DEFAULT '' COMMENT '密码加盐',
        `status` tinyint(4) DEFAULT 0 COMMENT '状态:-1:用户注销；0:正常使用',
        `real_name` varchar(8) DEFAULT '' COMMENT '真实姓名',
        `nick_name` varchar(32) DEFAULT '' COMMENT '昵称',
        `id_no` varchar(32) DEFAULT '' COMMENT '身份证号',
        `sex` tinyint(4) DEFAULT -1 COMMENT '性别,0:女；1：男；-1：未知',
        `head_img` varchar(256) DEFAULT NULL COMMENT '用户头像',
        `country` varchar(32) DEFAULT '中国' COMMENT '住址-国家',
        `province` varchar(32) DEFAULT NULL COMMENT '住址-省份',
        `city` varchar(32) DEFAULT NULL COMMENT '住址-市级',
        `county` varchar(32) DEFAULT NULL COMMENT '住址-县',
        `detail` varchar(32) DEFAULT NULL COMMENT '住址-详细地址',
        `version` int(11) DEFAULT '0' COMMENT '版本',
        `creator` bigint(20) unsigned DEFAULT NULL COMMENT '创建者',
        `updator` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
        `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

PRIMARY KEY (`pk`) USING BTREE 
)  ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='用户\n表示在网站上购买、收藏、评论商品的买家';


CREATE TABLE `ods_user_address` (
 `pk` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ods主键',
        `id` bigint(20) unsigned NOT NULL COMMENT '主键',
        `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
        `is_default` tinyint(4) DEFAULT 0 COMMENT '是否是默认地址，0：不默认，1：默认',
        `country` varchar(32) DEFAULT '中国' COMMENT '国家',
        `province` varchar(32) DEFAULT NULL COMMENT '省份',
        `city` varchar(32) DEFAULT NULL COMMENT '市级',
        `county` varchar(32) DEFAULT NULL COMMENT '县',
        `detail` varchar(32) DEFAULT NULL COMMENT '详细地址',
         `version` int(11) DEFAULT '0' COMMENT '版本',
         `creator` bigint(20) unsigned DEFAULT NULL COMMENT '创建者',
         `updator` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
         `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
         `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

PRIMARY KEY (`pk`) USING BTREE 
)  ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='用户收货地址表\n标记用户的收货地址';


CREATE TABLE `ods_sku_car` (
 `pk` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ods主键',
        `id` bigint(20) unsigned NOT NULL COMMENT '主键',
        `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
        `sku_id` bigint(20) unsigned DEFAULT NULL COMMENT '单品id',
        `number` int(11) unsigned DEFAULT NULL COMMENT '数量',
         `version` int(11) DEFAULT '0' COMMENT '版本',
         `creator` bigint(20) unsigned DEFAULT NULL COMMENT '创建者',
         `updator` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
         `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
         `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

PRIMARY KEY (`pk`) USING BTREE 
)  ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='购物车\n用于放置用户即将购买的商品';


CREATE TABLE `ods_shop` (
 `pk` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ods主键',
        `id` bigint(20) unsigned NOT NULL COMMENT '主键',
        `user_id` bigint(20) unsigned NOT NULL COMMENT '店主',
        `id_no` varchar(32) DEFAULT NULL COMMENT '营业执照',
        `name` varchar(50) DEFAULT '' COMMENT '名称',
        `country` varchar(32) DEFAULT '中国' COMMENT '国家',
        `province` varchar(32) DEFAULT NULL COMMENT '省份',
        `city` varchar(32) DEFAULT NULL COMMENT '市级',
        `county` varchar(32) DEFAULT NULL COMMENT '县',
        `detail` varchar(32) DEFAULT NULL COMMENT '详细地址',
        `status` tinyint(4) DEFAULT 0 COMMENT '状态，-1：注销；0：正常运营',
        `phone` varchar(32) DEFAULT NULL COMMENT '客服电话',
        `business_scope` varchar(128) DEFAULT NULL COMMENT '经营范围',
        `version` int(11) DEFAULT '0' COMMENT '版本',
        `creator` bigint(20) unsigned DEFAULT NULL COMMENT '创建者',
        `updator` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
        `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

PRIMARY KEY (`pk`) USING BTREE 
)  ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='';


CREATE TABLE `ods_spu` (
 `pk` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ods主键',
        `id` bigint(20) unsigned NOT NULL COMMENT '主键',
         `name`  varchar(50) DEFAULT NULL COMMENT '产品名称',
         `brand`  varchar(50) DEFAULT NULL COMMENT '品牌',
          `classify_id` int(11) NOT NULL COMMENT '产品分类',
         `desc` varchar(50) DEFAULT NULL COMMENT '商品描述',
         `img`  varchar(255) DEFAULT NULL COMMENT '商品图片',
         `product_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品价格',
         `status` tinyint(4) DEFAULT 0 COMMENT '状态，-1：已下架；0：正常售卖',
         `version` int(11) DEFAULT '0' COMMENT '版本',
         `creator` bigint(20) unsigned DEFAULT NULL COMMENT '创建者',
         `updator` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
         `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
         `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

PRIMARY KEY (`pk`) USING BTREE 
)  ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='商品表\n标识商家提供给用户的可供交易的物品';


CREATE TABLE `ods_sku` (
 `pk` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ods主键',
        `id` bigint(20) unsigned NOT NULL COMMENT '主键',
         `shop_id` bigint(20) unsigned DEFAULT NULL COMMENT '商户id',
         `spu_id` bigint(20) unsigned DEFAULT NULL COMMENT '商品id',
         `spec`  varchar(50) DEFAULT NULL COMMENT '规格',
         `spec_desc` varchar(50) DEFAULT NULL COMMENT '规格描述',
         `img`  varchar(255) DEFAULT NULL COMMENT '商品图片',
         `stock`  int(11) DEFAULT NULL COMMENT '库存',
         `product_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品价格',
         `status` tinyint(4) DEFAULT 0 COMMENT '状态，-1：已下架；0：正常售卖',
         `version` int(11) DEFAULT '0' COMMENT '版本',
         `creator` bigint(20) unsigned DEFAULT NULL COMMENT '创建者',
         `updator` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
         `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
         `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

PRIMARY KEY (`pk`) USING BTREE 
)  ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='单品表\n表示物理不可分割的库存单元';


CREATE TABLE `ods_brand` (
 `pk` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ods主键',
        `id` bigint(20) unsigned NOT NULL COMMENT '主键',
         `name`  varchar(50) DEFAULT NULL COMMENT '品牌名称',
         `logo` varchar(50) DEFAULT NULL COMMENT '品牌logo',
         `desc`  varchar(255) DEFAULT NULL COMMENT '品牌描述',
         `version` int(11) DEFAULT '0' COMMENT '版本',
         `creator` bigint(20) unsigned DEFAULT NULL COMMENT '创建者',
         `updator` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
         `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
         `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

PRIMARY KEY (`pk`) USING BTREE 
)  ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='品牌表\n标识品牌';


CREATE TABLE `ods_product_comment` (
 `pk` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ods主键',
         `id` bigint(20) unsigned NOT NULL COMMENT '主键',
         `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
         `spu_id` bigint(20) unsigned DEFAULT NULL COMMENT '商品id',
         `comment` varchar(1024) DEFAULT NULL COMMENT '评论',
         `praise_degree` tinyint(4) DEFAULT NULL COMMENT '好评度',
         `status` tinyint(4) DEFAULT NULL COMMENT '状态',
         `version` int(11) DEFAULT '0' COMMENT '版本',
         `creator` bigint(20) unsigned DEFAULT NULL COMMENT '创建者',
         `updator` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
         `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
         `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

PRIMARY KEY (`pk`) USING BTREE 
)  ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='商品评价\n用户对商品的评价';


CREATE TABLE `ods_order` (
 `pk` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ods主键',
         `id` bigint(20) unsigned NOT NULL COMMENT '主键',
         `sn` varchar(32) DEFAULT NULL COMMENT '订单编号',
         `pay_status` tinyint(4) DEFAULT '-1' COMMENT '支付状态，0：未支付；1：支付成功；2：支付失败；3：待退款；4：已退款',
         `order_status` tinyint(4) DEFAULT '-1' COMMENT '订单状态，0：订单打开；1：订单取消；2：订单关闭；3：订单完成',
         `express_status` tinyint(4) DEFAULT '-1' COMMENT '快递状态，0：初始状态；1：待发货；2：待收货；3：已收货；4：退货-商家待收货；5：退货-商家已收货',
         `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
         `shop_id` bigint(20) unsigned DEFAULT NULL COMMENT '商户id',
         `discount_money` decimal(10,2) DEFAULT '0.00' COMMENT '优惠金额',
         `real_money` decimal(10,2) DEFAULT '0.00' COMMENT '实际消费金额',
         `pay_money` decimal(10,2) DEFAULT '0.00' COMMENT '实际支付金额：实际消费金额-优惠金额',
         `refund_money` decimal(10,2) DEFAULT '0.00' COMMENT '退款金额',
         `remark` varchar(200) DEFAULT NULL COMMENT '订单处理备注',
         `version` int(11) DEFAULT '0' COMMENT '版本',
         `creator` bigint(20) unsigned DEFAULT NULL COMMENT '创建者',
         `updator` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
         `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
         `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

PRIMARY KEY (`pk`) USING BTREE 
)  ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='订单\n用户购买商品后向商家提供的记录购买过程、详细商品信息和货值的购物凭据';


CREATE TABLE `ods_order_detail` (
 `pk` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ods主键',
         `id` bigint(20) unsigned NOT NULL COMMENT '主键',
         `order_id` bigint(20) unsigned NOT NULL COMMENT '主键',
         `sn` varchar(32) DEFAULT NULL COMMENT '订单编号',
         `sku_id` bigint(20) unsigned DEFAULT NULL COMMENT '商品id',
         `discount_money` decimal(10,2) DEFAULT '0.00' COMMENT '优惠金额',
         `real_money` decimal(10,2) DEFAULT '0.00' COMMENT '实际消费金额',
         `pay_money` decimal(10,2) DEFAULT '0.00' COMMENT '实际支付金额：实际消费金额-优惠金额',
         `refund_money` decimal(10,2) DEFAULT '0.00' COMMENT '退款金额',
         `remark` varchar(200) DEFAULT NULL COMMENT '处理备注',
         `version` int(11) DEFAULT '0' COMMENT '版本',
         `creator` bigint(20) unsigned DEFAULT NULL COMMENT '创建者',
         `updator` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
         `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
         `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

PRIMARY KEY (`pk`) USING BTREE 
)  ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='订单详情表\n记录详细商品信息和货值的购物凭据。';


CREATE TABLE `ods_com_prov_city_area` (
 `pk` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ods主键',
            `areano` mediumint(6) unsigned NOT NULL,
            `areaname` varchar(45) DEFAULT NULL,
            `parentno` mediumint(6) unsigned DEFAULT NULL,
            `areacode` varchar(5) DEFAULT NULL,
            `arealevel` tinyint(1) DEFAULT NULL,
            `typename` char(3) DEFAULT NULL,

PRIMARY KEY (`pk`) USING BTREE 
)  ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='地区查找表\n标记全国地址';


CREATE TABLE `ods_com_industry` (
 `pk` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ods主键',
              `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
              `parent_id` smallint(5) unsigned NOT NULL DEFAULT '0',
              `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
              `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',

PRIMARY KEY (`pk`) USING BTREE 
)  ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='经营范围查找表\n标记商家经营范围';


CREATE TABLE `ods_com_spu_classify` (
 `pk` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ods主键',
      `cid` int(11) NOT NULL,
      `name` varchar(30) NOT NULL COMMENT '类别名称',
      `is_parent` int(1) NOT NULL DEFAULT '0' COMMENT '是否有子类0否1是',
      `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级类别',
      `level` smallint(1) NOT NULL DEFAULT '0',
      `pathid` varchar(200) DEFAULT NULL COMMENT '类别缩略图',
      `path` varchar(200) DEFAULT NULL COMMENT '种类背景图',

PRIMARY KEY (`pk`) USING BTREE 
)  ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='';


