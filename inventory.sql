create database inventory_management charset utf8;
use inventory_management;

create table users(
	id int(11) not null auto_increment,
    user_name varchar(50) not null,
    password varchar(50) not null,
    email varchar(100) default null,
    name varchar(100) not null,
    gender bit,
    image varchar(255),
    active_flag int(2) not null default 1,
    create_date timestamp not null default current_timestamp,
	primary key(id)
);

create table user_role(
	id int(11) not null auto_increment,
    user_id int(11) not null,
    role_id int(11) not null,
    active_flag int(2) not null default 1,
    create_date timestamp not null default current_timestamp,
	primary key(id)
);

create table role(
	id int(11) not null auto_increment,
    role_name varchar(50) not null,
    description varchar(200) not null,
    active_flag int(2) not null default 1,
    create_date timestamp not null default current_timestamp,
	primary key(id)
);

create table auth(
	id int(11) not null auto_increment,
    role_id int(11) not null,
    menu_id int(11) not null,
    permission int(1) not null default 1,
    active_flag int(2) not null default 1,
    create_date timestamp not null default current_timestamp,
	primary key(id)
);

create table menu(
	id int(11) not null auto_increment,
    parent_id int(11) not null,
    url varchar(100) not null,
    name varchar(100) not null default 1,
    order_index int(1) not null default 0,
    active_flag int(2) not null default 1,
    create_date timestamp not null default current_timestamp,
	primary key(id)
);

create table category(
	id int(11) not null auto_increment,
    name varchar(100) not null,
    code varchar(50),
    description varchar(200) not null,
    active_flag int(2) not null default 1,
    create_date timestamp not null default current_timestamp,
	primary key(id)
);

create table product_info(
	id int(11) not null auto_increment,
    cate_id int(11) not null,
    name varchar(100) not null,
    code varchar(50),
    img_url varchar(200) not null,
    description varchar(200) not null,
    active_flag int(2) not null default 1,
    create_date timestamp not null default current_timestamp,
	primary key(id)
);

create table product_in_stock(
	id int(11) not null auto_increment,
    product_id int(11) not null,
    qty int(11) not null,
    price double not null ,
    discount int(2) ,
    color varchar(50) not null,
    active_flag int(2) not null default 1,
    create_date timestamp not null default current_timestamp,
	primary key(id)
);

CREATE TABLE history (
    id INT(11) NOT NULL AUTO_INCREMENT,
    action_name varchar(100) not null,
    type int(1) not null,
    product_id int(11) not null,
    qty int(11) not null,
    price decimal(15,2) not null,
    active_flag INT(2) NOT NULL DEFAULT 1,
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

create table invoice(
	id int(11) not null auto_increment,
    code varchar(50),
    type int(1) not null, 
    product_id int(11) not null,
    qty int(11) not null,
    price decimal(15,2) not null,
    active_flag int(2) not null default 1,
    create_date timestamp not null default current_timestamp,
	primary key(id)
);

create table ioinvoice(
	id int(11) not null auto_increment,
    type int(1),
    create_date date,
    primary key(id)
);

create table ioinvoice_product(
	id int (11) not null auto_increment,
    id_invoice int(11) not null,
    id_product int(11) not null,
    qty int(11) not null,
    primary key(id)
);

alter table ioinvoice_product add index `id_product` (`id_product` ASC) visible;
alter table ioinvoice_product add constraint fk_io_pro foreign key(id_product) references product_info(id) on delete cascade on update cascade;
alter table ioinvoice_product add index `ioinvoice` (`id_invoice` ASC) visible;
alter table ioinvoice_product add constraint fk_io_invoice foreign key(id_invoice) references ioinvoice(id) on delete cascade on update cascade;

alter table user_role add index `user` (`user_id` ASC) visible;
alter table user_role add constraint fk_role_userid foreign key (user_id) references users(id) on delete cascade on update cascade;
alter table user_role add index `role` (`role_id` ASC) visible;
alter table user_role add constraint fk_role_roleid foreign key(role_id) references role(id) on delete cascade on update cascade;

alter table auth add index `role_id` (`role_id` ASC) visible;
alter table auth add constraint fk_auth_roleid foreign key(role_id) references role(id) on delete cascade on update cascade;
alter table auth add index `menu` (`menu_id` ASC) visible;
alter table auth add constraint fk_auth_menuid foreign key(menu_id) references menu(id) on delete cascade on update cascade;

alter table product_info add index `cate` (`cate_id` ASC) visible;
alter table product_info add constraint fk_product_info_cateid foreign key(cate_id) references category(id) on delete cascade on update cascade;

alter table product_in_stock add index `product` (`product_id` ASC) visible;
alter table product_in_stock add constraint fk_product_in_stock_productid foreign key(product_id) references product_info(id) on delete cascade on update cascade;

alter table history add index `product_id_his` (`product_id` ASC) visible;
alter table history add constraint fk_history_productid foreign key(product_id) references product_info(id) on delete cascade on update cascade;

alter table invoice add index `product_id_invoice` (`product_id` ASC) visible;
alter table invoice add constraint fk_invoice_productid foreign key(product_id) references product_info(id) on delete cascade on update cascade;
