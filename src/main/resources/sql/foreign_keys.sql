alter table distributer add constraint fk_distributer_customer_id foreign key(customer_id) references chenkuserdb1.skt1(skf223);
alter table distributer add constraint fk_distributer_followedByStore_id foreign key (followedByStore_id) references chenkuserdb1.tbldept(ID);
alter table moneyrecord add constraint fk_moneyrecord_order_id foreign key(order_id) references chenkuserdb1.skt18(SKF254);