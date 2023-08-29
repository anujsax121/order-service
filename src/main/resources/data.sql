

DROP TABLE [IF EXISTS] order
[CASCADE | RESTRICT];

CREATE TABLE order (id varchar(255) not null, amount float(53), name varchar(255), quantity integer, status varchar(255), primary key (id));

INSERT INTO order VALUES('d28df9a7-40c8-467f-8293-504bb4cd0086', 'IPhone',1, 69900.12, 'PENDING');