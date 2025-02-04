create table if not exists customer
(
    id BIGSERIAL PRIMARY KEY,
    first_name varchar(255) not null,
    last_name varchar(255) not null ,
    shipment_id BIGINT not null,
    shipment_status varchar(50)

);


create table if not exists notification
(
    id BIGSERIAL PRIMARY KEY,
    shipment_id BIGINT not null,
    message varchar(255),
    status varchar(50)

);