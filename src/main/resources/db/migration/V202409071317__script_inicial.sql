CREATE TABLE sale (
	id uuid primary key,
	purchaser_name varchar(150),
	item_description varchar(250),
	item_price numeric,
	purchase_count numeric,
	merchant_address varchar(250),
	merchant_name varchar(150)
);

CREATE TABLE tb_user (
	id uuid primary key,
	username varchar(150),
    password varchar(100)
);