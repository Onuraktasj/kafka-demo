create table if not exists notification
(
    id BIGSERIAL PRIMARY KEY,
    shipment_id BIGINT not null,
    message varchar(255),
    status varchar(50)

);