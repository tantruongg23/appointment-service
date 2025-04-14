INSERT INTO public.specialties (created_at,created_by,updated_at,updated_by,code,"name") VALUES
	 (NULL,NULL,NULL,NULL,NULL,'CK Nội tổng quát'),
	 (NULL,NULL,NULL,NULL,NULL,'CK Thần kinh'),
	 (NULL,NULL,NULL,NULL,NULL,'CK Nội tiết'),
	 (NULL,NULL,NULL,NULL,NULL,'CK Dinh dưỡng'),
	 (NULL,NULL,NULL,NULL,NULL,'CK Sản phụ khoa'),
	 (NULL,NULL,NULL,NULL,NULL,'CK Tiêu hóa');

INSERT INTO public.doctors (created_at,created_by,updated_at,updated_by,full_name,specialty_id) VALUES
	 (NULL,NULL,NULL,NULL,'BS.CKI Phạm Huyền Thu',1),
	 (NULL,NULL,NULL,NULL,'BS.CKI Phan Thị Thu Ngân',1),
	 (NULL,NULL,NULL,NULL,'BS.CKI Trần Thanh Thúy',2),
	 (NULL,NULL,NULL,NULL,'ThS.BS Huỳnh Thị Thúy Hằng',2),
	 (NULL,NULL,NULL,NULL,'TS.BS Lâm Văn Hoàng',3),
	 (NULL,NULL,NULL,NULL,'BS CK1 Võ Trần Nguyên Duy',3),
	 (NULL,NULL,NULL,NULL,'BS.CKI Đào Thị Yến Thủy',4),
	 (NULL,NULL,NULL,NULL,'BS Lê Thị Phương Thảo',4),
	 (NULL,NULL,NULL,NULL,'BS.CKI Hồ Thị Khánh Quyên',5),
	 (NULL,NULL,NULL,NULL,'BS.CKI Nguyễn Huy Cường',5);
INSERT INTO public.doctors (created_at,created_by,updated_at,updated_by,full_name,specialty_id) VALUES
	 (NULL,NULL,NULL,NULL,'ThS.BS. Huỳnh Hoài Phương',6),
	 (NULL,NULL,NULL,NULL,'Ths.BS. Đoàn Hoàng Long',6);


INSERT INTO public.doctor_schedule (created_at,created_by,updated_at,updated_by,available,"date",doctor_id,end_hour,"period",start_hour) VALUES
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',1,'11:00:00','MORNING','07:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',1,'13:00:00','LUNCH','12:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',1,'17:00:00','AFTERNOON','13:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',2,'11:00:00','MORNING','07:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',2,'13:00:00','LUNCH','12:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',2,'17:00:00','AFTERNOON','13:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',3,'11:00:00','MORNING','07:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',3,'13:00:00','LUNCH','12:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',3,'17:00:00','AFTERNOON','13:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',4,'11:00:00','MORNING','07:00:00');
INSERT INTO public.doctor_schedule (created_at,created_by,updated_at,updated_by,available,"date",doctor_id,end_hour,"period",start_hour) VALUES
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',4,'13:00:00','LUNCH','12:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',4,'17:00:00','AFTERNOON','13:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',5,'11:00:00','MORNING','07:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',5,'13:00:00','LUNCH','12:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',5,'17:00:00','AFTERNOON','13:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',6,'11:00:00','MORNING','07:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',6,'13:00:00','LUNCH','12:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',6,'17:00:00','AFTERNOON','13:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',7,'11:00:00','MORNING','07:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',7,'13:00:00','LUNCH','12:00:00');
INSERT INTO public.doctor_schedule (created_at,created_by,updated_at,updated_by,available,"date",doctor_id,end_hour,"period",start_hour) VALUES
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',7,'17:00:00','AFTERNOON','13:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',8,'11:00:00','MORNING','07:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',8,'13:00:00','LUNCH','12:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',8,'17:00:00','AFTERNOON','13:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',9,'11:00:00','MORNING','07:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',9,'13:00:00','LUNCH','12:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',9,'17:00:00','AFTERNOON','13:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',10,'11:00:00','MORNING','07:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',10,'13:00:00','LUNCH','12:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',10,'17:00:00','AFTERNOON','13:00:00');
INSERT INTO public.doctor_schedule (created_at,created_by,updated_at,updated_by,available,"date",doctor_id,end_hour,"period",start_hour) VALUES
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',11,'11:00:00','MORNING','07:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',11,'13:00:00','LUNCH','12:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',11,'17:00:00','AFTERNOON','13:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',12,'11:00:00','MORNING','07:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',12,'13:00:00','LUNCH','12:00:00'),
	 (NULL,NULL,NULL,NULL,true,'2025-04-15',12,'17:00:00','AFTERNOON','13:00:00'),
	 ('2025-04-14 11:28:11.882',NULL,'2025-04-14 11:28:11.882',NULL,true,'2025-04-18',1,'11:00:00','MORNING','07:00:00'),
	 ('2025-04-14 11:28:53.528',NULL,'2025-04-14 11:28:53.528',NULL,true,'2025-04-18',2,'11:00:00','MORNING','07:00:00'),
	 ('2025-04-14 11:37:13.164',NULL,'2025-04-14 11:37:13.164',NULL,true,'2025-04-17',2,'11:00:00','MORNING','07:00:00');
