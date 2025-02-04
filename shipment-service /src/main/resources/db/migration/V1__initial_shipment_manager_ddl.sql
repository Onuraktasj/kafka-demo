create table if not exists shipment
(
    id BIGSERIAL PRIMARY KEY,
    status varchar(255),
    customer_email varchar(255),
    courier_id int
);