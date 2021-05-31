USE master
IF EXISTS (SELECT * FROM SYS.DATABASES WHERE NAME LIKE 'Quiz')
BEGIN
	DROP DATABASE Quiz
END
GO

USE master
CREATE DATABASE Quiz
GO

USE Quiz
CREATE TABLE subject (
	subject_id INT PRIMARY KEY IDENTITY(1,1),
	subject_name NVARCHAR(100) NOT NULL
)

CREATE TABLE account(
	account_id BIGINT PRIMARY KEY IDENTITY(1,1),
	account_code VARCHAR(12) UNIQUE NOT NULL,
	full_name NVARCHAR(50) NOT NULL,
	gender INT,
	birth_of_date DATE,
	phone_number VARCHAR(10),
	email VARCHAR(50) UNIQUE NOT NULL,
	password VARCHAR(40) NOT NULL,
	role INT NOT NULL,
	avt_path TEXT,
	create_by INT,
	create_date DATETIME ,
	update_by INT ,
	update_date DATETIME,
)

CREATE TABLE question (
	question_id BIGINT PRIMARY KEY IDENTITY(1,1),
	question_code VARCHAR(12) UNIQUE NOT NULL,
	question NTEXT NOT NULL,
	answer_1 NTEXT NOT NULL,
	answer_2 NTEXT NOT NULL,
	answer_3 NTEXT NOT NULL,
	answer_4 NTEXT NOT NULL,
	answer_correct NTEXT NOT NULL,
	level INT NOT NULL,
	subject_id INT NOT NULL,
	create_by INT ,
	create_date DATETIME ,
	update_by INT ,
	update_date DATETIME,
	CONSTRAINT FK_question_subject_id FOREIGN KEY (subject_id) REFERENCES subject(subject_id) ON DELETE CASCADE
)

CREATE TABLE exam (
	exam_id BIGINT PRIMARY KEY IDENTITY(1,1),
	exam_code VARCHAR(12) UNIQUE NOT NULL,
	subject_id INT NOT NULL,
	duration INT NOT NULL,
	total_amount_question INT,
	amount_question_hard INT ,
	amount_question_normal INT ,
	amount_question_easy INT ,
	start_time DATETIME,
	create_by INT ,
	create_date DATETIME ,
	update_by INT ,
	update_date DATETIME ,
	CONSTRAINT FK_exam_subject_id FOREIGN KEY (subject_id) REFERENCES subject(subject_id) ON DELETE CASCADE
) 

CREATE TABLE exam_question(
	exam_question_id BIGINT PRIMARY KEY IDENTITY(1,1),
	exam_code VARCHAR(12) NOT NULL,
	question_code VARCHAR(12) NOT NULL,
	CONSTRAINT FK_eq_exam_code FOREIGN KEY (exam_code) REFERENCES exam(exam_code) ON DELETE CASCADE,
	CONSTRAINT FK_eq_question_code FOREIGN KEY (question_code) REFERENCES question(question_code) ON DELETE NO ACTION
)

CREATE TABLE history(
	history_id BIGINT PRIMARY KEY IDENTITY(1,1),
	exam_code VARCHAR(12) NOT NULL,
	account_code VARCHAR(12) NOT NULL,
	test_day DATETIME,
	result FLOAT NOT NULL,
	CONSTRAINT FK_result_account_code FOREIGN KEY (account_code) REFERENCES account(account_code) ON DELETE CASCADE,
	CONSTRAINT FK_result_exam_code FOREIGN KEY (exam_code) REFERENCES exam(exam_code) ON DELETE NO ACTION
)

CREATE TABLE test(
	test_id BIGINT PRIMARY KEY IDENTITY(1,1),
	history_id BIGINT,
	question_code VARCHAR(12),
	answer VARCHAR(1),
	CONSTRAINT FK_test_question_code FOREIGN KEY (question_code) REFERENCES question(question_code) ON DELETE NO ACTION,
	CONSTRAINT FK_history_id FOREIGN KEY (history_id) REFERENCES history(history_id)
)

INSERT INTO subject VALUES(N'Toán'),(N'Vậy lý'),(N'Hóa học'),(N'Sinh học'),(N'Lịch sử'),(N'Địa lý'),(N'Tiếng anh'),(N'GDCD')

INSERT INTO account VALUES('QA1', N'Nguyễn Minh Hiếu', 1, '1999-08-09', '0337358641', 'admin@gmail.com', '12345678', 0, 'avatar-default.jpg',1,'2021-10-21',1,null)
,('QA2', N'Nguyễn Thị Yến', 1, '1999-08-09', '0337358642', 'exam@gmail.com', '12345678', 1, 'avatar-default.jpg',1,'2020-10-21',1,null)
,('QA3', N'Nguyễn Minh Anh', 1, '1999-08-09', '0337358643', 'user@gmail.com', '12345678', 2, 'avatar-default.jpg',1,'2019-10-21',1,null)
,('QA4', N'Nguyễn Minh Hiếu', 1, '1999-08-09', '0337358645', 'admin4@gmail.com', '123456', 0, 'avatar-default.jpg',1,'2021-11-21',1,null)
,('QA5', N'Nguyễn Minh Hiếu', 1, '1999-08-09', '0337358646', 'admin5@gmail.com', '123456', 1, 'avatar-default.jpg',1,'2018-08-21',1,null)
,('QA6', N'Nguyễn Minh Hiếu', 1, '1999-08-09', '0337358647', 'admin6@gmail.com', '123456', 2, 'avatar-default.jpg',1,'2021-07-21',1,null)
,('QA7', N'Nguyễn Minh Hiếu', 1, '1999-08-09', '0337358648', 'admin7gmail.com', '123456', 0, 'avatar-default.jpg',1,'2017-05-21',1,null)
,('QA8', N'Nguyễn Minh Hiếu', 1, '1999-08-09', '0337358649', 'admin8@gmail.com', '123456', 1, 'avatar-default.jpg',1,'2019-09-21',1,null)


INSERT INTO exam VALUES ('EX1', 8, 20, 10, 9,1,0,null, 1, '2021-01-01',1,'2020-01-01')
,('EX2', 1, 40, 40, 20,10,10, null, 1, '2020-01-01',1,'2019-01-01')
,('EX3', 1, 40, 40, 10,21,9,'2021-01-01 12:00:00', 1, '2020-01-01',1,'2020-01-01')
,('EX4', 1, 40, 40, 9,20,11, null, 1, '2020-01-01',1,'2021-01-01')
,('EX5', 1, 40, 40, 9,19,12, null, 1, '2020-01-01',1,'2020-01-01')
, ('EX6', 1, 40, 40, 16,20,4,'2021-01-01 12:00:00', 1, '2021-01-01',1,'2020-01-01')
,('EX7', 1, 40, 40, 12,25,3,'2021-01-01 12:00:00', 1, '2019-01-01',1,'2020-01-01')


INSERT INTO question VALUES ('Q1', N'Toàn bộ những năng lực thể chất và tinh thần của con người được vận dụng vào quá trình sản xuất là', N'Bối cảnh xã hội', N'Các hợp tác', N'Tư liệu tiêu dùng', N'Sức lao động', N'Sức lao động', 0, 8,1,'2021-01-01',1,'2020-01-01')
,('Q2', N'Theo quy định của pháp luật, việc bắt người trong trường hợp khẩn cấp được tiến hành khi có căn cứ cho rằng người đó đang chuẩn bị thực hiện', N'Phương án độc chiếm thị trường', N'Hồ sơ thế chấp tài sản riêng', N'Kế hoạch phản biện xã hội', N'Tội phạm rất nghiêm trọng',  N'Tội phạm rất nghiêm trọng', 0, 8,1,'2020-01-01',1,'2020-01-01')
,('Q3', N'Cá nhân, tổ chức thực hiện đầy đủ các nghĩa vụ, chủ động làm những gì mà pháp luật quy định phải làm là thực hiện pháp luật theo hình thức nào sau đây?', N'Thi thành pháp luật', N'Tuyên truyền pháp luật', N'Phổ biến pháp luật', N'Điều chỉnh pháp luật', N'Thi thành pháp luật', 0, 8,1,'2021-01-01',1,'2020-01-01')
,('Q4', N'Một trong những nội dung của quyền bình đẳng trong lao động là mọi công dân đều được thực hiện quyền lao động thông qua', N'Chiến lược phân bố dân cư', N'Lựa chọn việc làm phù hợp', N'Kế hoạch điều tra nhân lực', N'Nội dung thông cáo báo chí', N'Lựa chọn việc làm phù hợp', 0, 8,1,'2019-01-01',1,'2020-01-01')
,('Q5', N'Một trong những chức năng của tiền tệ trong nền kinh tế hàng hóa', N'Điều hành sản xuất', N'Khảo sát thi trường', N'Kiểm định chất lượng', N'Phương tiện cất trữ', N'Phương tiện cất trữ',0, 8,1,'2020-01-01',1,'2020-01-01')
, ('Q6', N'Bình đẳng về trách nhiệm pháp lí là bất kì công dân nào vi phạm pháp luật đề phải', N'Tổ chức phục dựng hiện trường', N'Tạo lập bằng chứng ngoại phạm', N'Chấm dứt mọi quan hệ dân sự', N'Bị xử lý theo quy định của pháp luật', N'Bị xử lý theo quy định của pháp luật', 0, 8,1,'2021-01-01',1,'2020-01-01')
,('Q7', N'Một trong những dấu hiệu cơ bản để xác định hành vi vi phạm pháp luật của cá nhân là người vi phạm dù', N'Năng lực trách nhiệm pháp lý', N'Các mối quan hệ xã hội', N'Điều kiện tiếp cận nhân chúng', N'Tiềm lực tài chính vững mạnh', N'Năng lực trách nhiệm pháp lý', 0, 8,1,'2019-01-01',1,'2020-01-01')
,('Q8', N'Khi muốn đề nghị sửa đổi nội dung trong hợp đồng lao động cần căn cứ vào nguyên tắc nào duới đây trong hợp đồng lao động ?', N'Tự do, công bằng, dân chủ.', N'Tự do, tự nguyện, bình đẳng.', N'Tự do ngôn luận.', N'Tự do thực hiện hợp đồng.', N'Tự do, tự nguyện, bình đẳng.',0, 8,1,'2021-01-01',1,'2020-01-01')
,('Q9', N'Bình đẳng giữa vợ và chồng được thể hiện trong quan hệ nhân thân và quan hệ', N'Sở hữu.', N'Thừa kế.', N'Tình cảm.', N'Tài sản.', N'Tài sản.', 1, 8,1,'2020-01-01',1,'2020-01-01')
,('Q10', N'Công dân bình đẳng trong thực hiện quyền lao động được hiểu là mọi người đều có quyền', N'Tự do kinh doanh.', N'Tự do làm mọi việc.', N'Tìm kiếm việc làm.', N'Tìm kiếm thị trường.', N'Tìm kiếm việc làm.', 0, 8,1,'2019-01-01',1,'2020-01-01')
,('Q11', N'Các dân tộc Việt Nam được tham gia quản lí nhà nước và xã hội là biểu hiện quyền bình đẳng về', N'Kinh tế.', N'Chính trị', N'Văn hóa', N'Giáo dục', N'Chính trị', 1, 8,1,'2021-01-01',1,'2020-01-01')
,('Q12', N'Hình thức tín ngưỡng có tổ chức, với những quan niệm, giáo lí thể hiện sự tín ngưỡng và những hình thức lễ nghi thể hiện sự sùng bái tín ngưỡng ấy được gọi là', N'Tôn giáo.', N'Luật lệ.', N'Phong tục.', N'Văn hóa.', N'Tôn giáo.', 1, 8,1,'2021-01-01',1,'2020-01-01')
,('Q13', N'Pháp luật có vai trò là phương tiện để công dân bảo vệ quyền và lợi ích hợp pháp của mình trong trường hợp nào dưới đây?', N'Khiếu nại hành vi vi phạm pháp luật.', N'Giải quyết tranh chấp, khiếu nại.', N' Tổ chức kinh doanh theo nhu cầu cá nhân.', N'Kiểm soát hoạt động của cá nhân, tổ chức.', N'Khiếu nại hành vi vi phạm pháp luật.', 0, 8,1,'2020-01-01',1,'2020-01-01')
,('Q14', N'Trường hợp nào dưới đây thể hiện tính quy phạm phổ biến của pháp luật?', N'Đội mũ bảo hiểm khi điều khiển xe máy.', N'Thu hồi giấy phép kinh doanh.', N'Đình chỉ công tác đối với cán bộ vi phạm kỉ luật.', N' Nam, nữ tự do kết hôn và li hôn.', N'Đội mũ bảo hiểm khi điều khiển xe máy.', 2, 8,1,'2020-01-01',1,'2020-01-01')
,('Q15', N'Công dân sử dụng pháp luật trong trường hợp nào dưới đây?', N'Đóng thuế khi sản xuất, kinh doanh.', N'Bảo vệ môi trường.', N'Không kinh doanh hàng giả, hàng nhái.', N'Kí kết hợp đồng lao động', N'Kí kết hợp đồng lao động', 0, 8,1,'2020-01-01',1,'2020-01-01')
,('Q16', N'Công dân đủ năng lực theo quy định của pháp luật phải chịu trách nhiệm pháp lí khi thực hiện hành vi nào sau đây?', N'Mở rộng quy mô kinh doanh.', N'Từ chối kí hợp đồng lao động', N'Phản bác ý kiến trong các cuộc họp.', N'Công khai danh tính người tố cáo.', N'Công khai danh tính người tố cáo.', 2, 8,1,'2019-01-01',1,'2020-01-01')
,('Q17', N'Công dân tuân thủ pháp luật khi từ chối', N'Thực hiện giao dịch dân sự', N'Thực hiện nghĩa vụ quân sự.', N'Viết hộ phiếu bầu cử cho người khác.', N'Tham gia các hoạt động tôn giáo.', N'Viết hộ phiếu bầu cử cho người khác.', 0, 8,1,'2020-01-01',1,'2020-01-01')
,('Q18', N'Theo quy định của pháp luật, trường hợp nào dưới đây công dân không bình đẳng về quyền và nghĩa vụ?', N'Anh T được tạm hoãn gọi nhập ngũ vì đang trong thời gian học đại học.', N'Công ty Z không tuyển nhân viên là người dân tộc thiểu số vào làm việc.', N'Ngân hàng RQ thưởng tết cho nhân viên nhiều hơn ngân hàng VT.', N'Trong một lớp học có bạn được miễn học phí, có bạn không được miễn.', N'Công ty Z không tuyển nhân viên là người dân tộc thiểu số vào làm việc.', 1, 8,1,'2020-01-01',1,'2020-01-01')
,('Q19', N'Nhận định nào dưới đây làđúng khi nói về quyền bình đẳng của công dân trước pháp luật?', N'Mọi người đều được hưởng quyền ưu tiên như nhau.', N' Quyền và nghĩa vụ của công dân không tách rời nhau.', N'Ai cũng phải chịu trách nhiệm pháp lí như nhau.', N'Quyền của công dân độc lập với nghĩa vụ công dân.', N' Quyền và nghĩa vụ của công dân không tách rời nhau.', 2, 8,1,'2020-01-01',1,'2020-01-01')
,('Q20', N'Nội dung nào dưới đây không thể hiện mọi doanh nghiệp đều bình đẳng về nghĩa vụ trong kinh doanh? ', N'Bảo đảm mọi nhu cầu của người lao động.', N'Kinh doanh đúng ngành, nghề đã đăng kí.', N'Thực hiện nghĩa vụ tài chính đối với Nhà nước.', N'Tuân thủ pháp luật về bảo vệ môi trường.', N'Bảo đảm mọi nhu cầu của người lao động.', 1, 8,1,'2020-01-01',1,'2020-01-01')
,('Q21', N'Quyền bình đẳng của công dân trong kinh doanh được thể hiện ở nội dung nào sau đây?', N'Tự do lựa chọn loại hình doanh nghiệp.', N'Tự do thay đổi địa chỉ đăng kí kinh doanh.', N'Tự do liên kết với mọi tổ chức kinh tế.', N'Tự do cạnh tranh dưới mọi hình thức.', N'Tự do lựa chọn loại hình doanh nghiệp.', 1, 8,1,'2021-01-01',1,'2020-01-01')
,('Q22', N'Theo quy định của pháp luật, những tài sản nào sau đây thuộc quyền sở hữu của cả vợ và chồng?', N'Tất cả tài sản chung mà pháp luật quy định.', N'Tất cả tài sản được thừa kế riêng và chung.', N'Tất cả tài sản trước thời kì hôn nhân.', N'Tất cả tài sản trong thời kì hôn nhân.', N'Tất cả tài sản chung mà pháp luật quy định.', 1, 8,1,'2018-01-01',1,'2020-01-01')
,('Q23', N'Nhận định nào dưới đây là đúng khi nói về quyền bình đẳng giữa các tôn giáo?', N'Công dân phải tham gia một tôn giáo để Nhà nước dễ quản lí.', N'Công dân không được tự ý bỏ tôn giáo này để theo tôn giáo khác.', N'Công dân có thể theo hay không theo bất cứ một tôn giáo nào.', N'Công dân cần thực hiện mọi hành động để bảo vệ các tôn giáo.', N'Công dân có thể theo hay không theo bất cứ một tôn giáo nào.', 2, 8,1,'2020-01-01',1,'2020-01-01')
,('Q24', N'Nội dung nào dưới đây không thể hiện quyền bình đẳng giữa các dân tộc trong lĩnh vực giáo dục?', N'Ưu tiên cộng điểm thi đại học cho con em vùng đồng bào dân tộc thiểu số.', N'Nhà nước đầu tư tài chính để mở mang trường lớp ở vùng sâu,vùng xa.', N'Chỉ có sinh viên vùng dân tộc thiểu số mới được xét để cấp học bổng.', N'Công dân thuộc dân tộc đa số và thiểu số đều bình đẳng về cơ hội học tập.', N'Chỉ có sinh viên vùng dân tộc thiểu số mới được xét để cấp học bổng.', 2, 8,1,'2020-01-01',1,'2020-01-01')
,('Q25', N'Cảnh sát giao thông thành phố X tăng cường việc sử dụng hệ thống camera để phát hiện
vi phạm giao thông do ngày càng nhiều người không có ý thức chấp hành luật giao thông. Việc
làm của Cảnh sát giao thông thành phố X đã thể hiện bản chất nào dưới đây của pháp luật?', N'Bản chất xã hội.', N'Bản chất kinh tế.', N'Bản chất giai cấp.', N'Bản chất khoa học.', N'Bản chất xã hội.', 2, 8,1,'2020-01-01',1,'2020-01-01')
,('Q26', N'Cán bộ sở X là chị K bị Tòa án tuyên phạt tù về tội lợi dụng chức vụ, quyền hạn để
chiếm đoạt số tiền chính sách dành cho học sinh nghèo là 3 tỷ đồng. Chị K đã phải chịu trách
nhiệm pháp lí nào sau đây?', N'Hình sự và kỉ luật.', N'Hành chính và dân sự.', N'Hình sự và dân sự.', N'Hành chính và kỉ luật', N'Hình sự và kỉ luật.', 1, 8,1,'2020-01-01',1,'2020-01-01')
,('Q27', N' Chủ một cơ sở sản xuất tư nhân là anh H bị Tòa án tuyên phạt tù và yêu cầu bồi thường
thiệt hại về tội vi phạm quy định về an toàn lao động khiến một công nhân tử vong. Anh H đã
phải chịu trách nhiệm pháp lí nào sau đây?', N'Hình sự và kỉ luật', N'Hành chính và dân sự.', N'Hình sự và dân sự.', N'Hành chính và kỉ luật.', N'Hình sự và dân sự.', 2, 8,1,'2020-01-01',1,'2020-01-01')
,('Q28', N' Bạn L viết bài chia sẻ về lí tưởng sống của thanh niên hiện nay để đăng lên trang
Web của nhà trường. Bạn L đã thực hiện pháp luật theo hình thức nào sau đây?', N'Sử dụng pháp luật.', N'Thi hành pháp luật. ', N'Tuân thủ pháp luật.', N'Áp dụng pháp luật.', N'Sử dụng pháp luật.', 2, 8,1,'2020-01-01',1,'2020-01-01')
,('Q29', N'Chủ một cửa hàng tạp hóa là bà K thường xuyên nhập hàng hóa không rõ nguồn
gốc về bán, đồng thời không thực hiện nghĩa vụ nộp thuế theo quy định của pháp luật. Bà
K đã không thực hiện pháp luật theo những hình thức nào sau đây?', N'Sử dụng pháp luật và thi hành pháp luật.', N'Thi hành pháp luật và tuân thủ pháp luật.', N'Tuân thủ pháp luật và áp dụng pháp luật.', N'Áp dụng pháp luật và sử dụng pháp luật.', N'Thi hành pháp luật và tuân thủ pháp luật.', 1, 8,1,'2020-01-01',1,'2020-01-01')
,('Q30', N'Tòa án nhân dân tỉnh X đã tuyên phạt 36 năm tù đối với các bị cáo trong vụ trộn
lõi Pin vào phế phẩm cà phê. Tòa án nhân dân tỉnh X đã thực hiện pháp luật theo hình
thức nào sau đây?', N'Sử dụng pháp luật.', N'Thi hành pháp luật.', N'Tuân thủ pháp luật.', N'Áp dụng pháp luật.', N'Áp dụng pháp luật.', 1, 8,1,'2020-01-01',1,'2020-01-01')
,('Q31', N'Đây là câu hỏi', N'Câu trả lời 1', N'Câu trả lời 2', N'Câu trả lời 3', N'Câu trả lời 4', N'Câu trả lời 1', 2, 1,1,'2020-01-01',1,'2020-01-01')

INSERT INTO exam_question VALUES('EX1', 'Q1'),('EX1', 'Q2'),('EX1', 'Q3'),('EX1', 'Q4'),('EX1', 'Q5'),('EX1', 'Q6'),('EX1', 'Q7'),('EX1', 'Q8'),('EX1', 'Q9'),('EX1', 'Q10')

INSERT INTO history VALUES('EX1', 'QA1', '2020-01-01 7:12:0', 10)
,('EX1', 'QA1', '2020-01-01 7:12:0', 10)
,('EX1', 'QA1', '2020-01-01 7:12:0', 10)
,('EX1', 'QA1', '2020-01-01 7:12:0', 10)
,('EX1', 'QA1', '2020-01-01 7:12:0', 10)
,('EX1', 'QA1', '2020-01-01 7:12:0', 10)
