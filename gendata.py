from datetime import date, time, timedelta


# List of doctors with their IDs (from the provided INSERT statement)
doctors = [
    (1, 'BS.CKI Phạm Huyền Thu'),
    (2, 'BS.CKI Phan Thị Thu Ngân'),
    (3, 'BS.CKI Trần Thanh Thúy'),
    (4, 'ThS.BS Huỳnh Thị Thúy Hằng'),
    (5, 'TS.BS Lâm Văn Hoàng'),
    (6, 'BS CK1 Võ Trần Nguyên Duy'),
    (7, 'BS.CKI Đào Thị Yến Thủy'),
    (8, 'BS Lê Thị Phương Thảo'),
    (9, 'BS.CKI Hồ Thị Khánh Quyên'),
    (10, 'BS.CKI Nguyễn Huy Cường'),
    (11, 'ThS.BS. Huỳnh Hoài Phương'),
    (12, 'Ths.BS. Đoàn Hoàng Long')
]

# Period definitions
periods = {
    'MORNING': ('07:00', '11:00'),
    'LUNCH': ('12:00', '13:00'),
    'AFTERNOON': ('13:00', '17:00')
}

# Generate schedule
start_date = start_date = date.today()

end_date = date(2025, 4, 21)  # 7 ngày

# Generate insert statements
insert_statements = []

current_date = start_date
while current_date <= end_date:
    for doctor_id, _ in doctors:
        for period, (start, end) in periods.items():
            insert_statements.append(
                f"INSERT INTO public.doctor_schedule (available, \"date\", doctor_id, end_hour, \"period\", start_hour) "
                f"VALUES (true, '{current_date}', {doctor_id}, '{end}', '{period}', '{start}');"
            )
    current_date += timedelta(days=1)

insert_statements[:5]  # Preview first 5

