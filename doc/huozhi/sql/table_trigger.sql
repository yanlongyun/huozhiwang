CREATE trigger user_insert  after insert on `user` for each row 
 begin
 insert into .ods_user(id,phone,password,salt,status,real_name,nick_name,id_no,sex,head_img,country,province,city,county,detail,version,creator,updator,create_time,update_time) 
values (new.id,new.phone,new.password,new.salt,new.status,new.real_name,new.nick_name,new.id_no,new.sex,new.head_img,new.country,new.province,new.city,new.county,new.detail,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger user_update  after update on `user` for each row 
 begin
 insert into .ods_user(id,phone,password,salt,status,real_name,nick_name,id_no,sex,head_img,country,province,city,county,detail,version,creator,updator,create_time,update_time) 
values (new.id,new.phone,new.password,new.salt,new.status,new.real_name,new.nick_name,new.id_no,new.sex,new.head_img,new.country,new.province,new.city,new.county,new.detail,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger user_address_insert  after insert on `user_address` for each row 
 begin
 insert into .ods_user_address(id,user_id,is_default,country,province,city,county,detail,version,creator,updator,create_time,update_time) 
values (new.id,new.user_id,new.is_default,new.country,new.province,new.city,new.county,new.detail,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger user_address_update  after update on `user_address` for each row 
 begin
 insert into .ods_user_address(id,user_id,is_default,country,province,city,county,detail,version,creator,updator,create_time,update_time) 
values (new.id,new.user_id,new.is_default,new.country,new.province,new.city,new.county,new.detail,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger sku_car_insert  after insert on `sku_car` for each row 
 begin
 insert into .ods_sku_car(id,user_id,sku_id,number,version,creator,updator,create_time,update_time) 
values (new.id,new.user_id,new.sku_id,new.number,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger sku_car_update  after update on `sku_car` for each row 
 begin
 insert into .ods_sku_car(id,user_id,sku_id,number,version,creator,updator,create_time,update_time) 
values (new.id,new.user_id,new.sku_id,new.number,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger shop_insert  after insert on `shop` for each row 
 begin
 insert into .ods_shop(id,user_id,id_no,name,country,province,city,county,detail,status,phone,business_scope,version,creator,updator,create_time,update_time) 
values (new.id,new.user_id,new.id_no,new.name,new.country,new.province,new.city,new.county,new.detail,new.status,new.phone,new.business_scope,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger shop_update  after update on `shop` for each row 
 begin
 insert into .ods_shop(id,user_id,id_no,name,country,province,city,county,detail,status,phone,business_scope,version,creator,updator,create_time,update_time) 
values (new.id,new.user_id,new.id_no,new.name,new.country,new.province,new.city,new.county,new.detail,new.status,new.phone,new.business_scope,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger spu_insert  after insert on `spu` for each row 
 begin
 insert into .ods_spu(id,name,brand,classify_id,desc,img,product_price,status,version,creator,updator,create_time,update_time) 
values (new.id,new.name,new.brand,new.classify_id,new.desc,new.img,new.product_price,new.status,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger spu_update  after update on `spu` for each row 
 begin
 insert into .ods_spu(id,name,brand,classify_id,desc,img,product_price,status,version,creator,updator,create_time,update_time) 
values (new.id,new.name,new.brand,new.classify_id,new.desc,new.img,new.product_price,new.status,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger sku_insert  after insert on `sku` for each row 
 begin
 insert into .ods_sku(id,shop_id,spu_id,spec,spec_desc,img,stock,product_price,status,version,creator,updator,create_time,update_time) 
values (new.id,new.shop_id,new.spu_id,new.spec,new.spec_desc,new.img,new.stock,new.product_price,new.status,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger sku_update  after update on `sku` for each row 
 begin
 insert into .ods_sku(id,shop_id,spu_id,spec,spec_desc,img,stock,product_price,status,version,creator,updator,create_time,update_time) 
values (new.id,new.shop_id,new.spu_id,new.spec,new.spec_desc,new.img,new.stock,new.product_price,new.status,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger brand_insert  after insert on `brand` for each row 
 begin
 insert into .ods_brand(id,name,logo,desc,version,creator,updator,create_time,update_time) 
values (new.id,new.name,new.logo,new.desc,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger brand_update  after update on `brand` for each row 
 begin
 insert into .ods_brand(id,name,logo,desc,version,creator,updator,create_time,update_time) 
values (new.id,new.name,new.logo,new.desc,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger product_comment_insert  after insert on `product_comment` for each row 
 begin
 insert into .ods_product_comment(id,user_id,spu_id,comment,praise_degree,status,version,creator,updator,create_time,update_time) 
values (new.id,new.user_id,new.spu_id,new.comment,new.praise_degree,new.status,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger product_comment_update  after update on `product_comment` for each row 
 begin
 insert into .ods_product_comment(id,user_id,spu_id,comment,praise_degree,status,version,creator,updator,create_time,update_time) 
values (new.id,new.user_id,new.spu_id,new.comment,new.praise_degree,new.status,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger order_insert  after insert on `order` for each row 
 begin
 insert into .ods_order(id,sn,pay_status,order_status,express_status,user_id,shop_id,discount_money,real_money,pay_money,refund_money,remark,version,creator,updator,create_time,update_time) 
values (new.id,new.sn,new.pay_status,new.order_status,new.express_status,new.user_id,new.shop_id,new.discount_money,new.real_money,new.pay_money,new.refund_money,new.remark,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger order_update  after update on `order` for each row 
 begin
 insert into .ods_order(id,sn,pay_status,order_status,express_status,user_id,shop_id,discount_money,real_money,pay_money,refund_money,remark,version,creator,updator,create_time,update_time) 
values (new.id,new.sn,new.pay_status,new.order_status,new.express_status,new.user_id,new.shop_id,new.discount_money,new.real_money,new.pay_money,new.refund_money,new.remark,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger order_detail_insert  after insert on `order_detail` for each row 
 begin
 insert into .ods_order_detail(id,order_id,sn,sku_id,discount_money,real_money,pay_money,refund_money,remark,version,creator,updator,create_time,update_time) 
values (new.id,new.order_id,new.sn,new.sku_id,new.discount_money,new.real_money,new.pay_money,new.refund_money,new.remark,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger order_detail_update  after update on `order_detail` for each row 
 begin
 insert into .ods_order_detail(id,order_id,sn,sku_id,discount_money,real_money,pay_money,refund_money,remark,version,creator,updator,create_time,update_time) 
values (new.id,new.order_id,new.sn,new.sku_id,new.discount_money,new.real_money,new.pay_money,new.refund_money,new.remark,new.version,new.creator,new.updator,new.create_time,new.update_time);
end;


CREATE trigger com_prov_city_area_insert  after insert on `com_prov_city_area` for each row 
 begin
 insert into .ods_com_prov_city_area(areano,areaname,parentno,areacode,arealevel,typename) 
values (new.areano,new.areaname,new.parentno,new.areacode,new.arealevel,new.typename);
end;


CREATE trigger com_prov_city_area_update  after update on `com_prov_city_area` for each row 
 begin
 insert into .ods_com_prov_city_area(areano,areaname,parentno,areacode,arealevel,typename) 
values (new.areano,new.areaname,new.parentno,new.areacode,new.arealevel,new.typename);
end;


CREATE trigger com_industry_insert  after insert on `com_industry` for each row 
 begin
 insert into .ods_com_industry(id,parent_id,name,code) 
values (new.id,new.parent_id,new.name,new.code);
end;


CREATE trigger com_industry_update  after update on `com_industry` for each row 
 begin
 insert into .ods_com_industry(id,parent_id,name,code) 
values (new.id,new.parent_id,new.name,new.code);
end;


CREATE trigger com_spu_classify_insert  after insert on `com_spu_classify` for each row 
 begin
 insert into .ods_com_spu_classify(cid,name,is_parent,parent_id,level,pathid,path) 
values (new.cid,new.name,new.is_parent,new.parent_id,new.level,new.pathid,new.path);
end;


CREATE trigger com_spu_classify_update  after update on `com_spu_classify` for each row 
 begin
 insert into .ods_com_spu_classify(cid,name,is_parent,parent_id,level,pathid,path) 
values (new.cid,new.name,new.is_parent,new.parent_id,new.level,new.pathid,new.path);
end;


