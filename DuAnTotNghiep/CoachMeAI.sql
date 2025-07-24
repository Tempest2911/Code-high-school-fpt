	CREATE DATABASE Coach_Me_AI
	GO
	USE Coach_Me_AI
	GO
	DROP DATABASE Coach_Me_AI

	CREATE TABLE Tai_Khoan (
		ID_Tai_Khoan INT PRIMARY KEY IDENTITY,
		Username NVARCHAR(255) UNIQUE,
		Password NVARCHAR(255),
		Ho_Va_Ten NVARCHAR(255),
		Avatar NVARCHAR(255),
		So_Dien_Thoai NVARCHAR(20) unique,
		Email NVARCHAR(255) unique,
		Ngay_Dang_Ki DATE,
		Role NVARCHAR(50),
		Lien_Ket NVARCHAR(50),
		Trang_Thai NVARCHAR(50)
	);

	INSERT INTO dbo.Tai_Khoan
	(
		Username,
		Password,
		Ho_Va_Ten,
		Avatar,
		So_Dien_Thoai,
		Email,
		Ngay_Dang_Ki,
		Role,
		Lien_Ket,
		Trang_Thai
	)
	VALUES
	(   'Dragonvnx', -- Username - nvarchar(255)
		'phong2007', -- Password - nvarchar(255)
		'Nguyễn Duy Phong', -- Ho_Va_Ten - nvarchar(255)
		NULL, -- Avatar - nvarchar(255)
		'0988958930', -- So_Dien_Thoai - nvarchar(20)
		'Phong@gmail.com', -- Email - nvarchar(255)
		'3-16-2025', -- Ngay_Dang_Ki - date
		'Admin', -- Role - nvarchar(50)
		'BAKA', -- Lien_Ket - nvarchar(50)
		'MUSIC'  -- Trang_Thai - nvarchar(50)
		)

	CREATE TABLE Mon_Hoc (
		ID_Mon_Hoc INT PRIMARY KEY IDENTITY,
		Ten NVARCHAR(255),
		Mo_Ta NVARCHAR(MAX)
	);

	INSERT INTO dbo.Mon_Hoc
	(
		Ten,
		Mo_Ta
	)
	VALUES
	(   'History', -- Ten - nvarchar(255)
		'History'  -- Mo_Ta - nvarchar(max)
		)

	CREATE TABLE Nhiem_Vu (
		ID_Nhiem_Vu INT PRIMARY KEY IDENTITY,
		ID_Tai_Khoan INT FOREIGN KEY REFERENCES Tai_Khoan(ID_Tai_Khoan),
		Tieu_De NVARCHAR(255),
		ID_Mon_Hoc INT FOREIGN KEY REFERENCES Mon_Hoc(ID_Mon_Hoc),
		Thoi_Gian DATETIME,
		Noi_Dung NVARCHAR(MAX),
		Ghi_Chu NVARCHAR(MAX)
	);


	SELECT * FROM dbo.Tai_Khoan WHERE ID_Tai_Khoan = 1;
	SELECT * FROM Tai_Khoan WHERE ID_Tai_Khoan = 1;
	SELECT * FROM Mon_Hoc WHERE ID_Mon_Hoc = 1;
	SELECT 1;
	SELECT ID_Mon_Hoc FROM Mon_Hoc WHERE LOWER(Mon_Hoc.Ten) LIKE '%toán%';

	SELECT * FROM dbo.Mon_Hoc
	SELECT * FROM dbo.Nhiem_Vu

	DELETE FROM dbo.Nhiem_Vu 
	WHERE Nhiem_Vu.ID_Nhiem_Vu = 3

	CREATE TABLE Bai_Test (
		ID_Bai_Test INT PRIMARY KEY IDENTITY,
		ID_Nhiem_Vu INT FOREIGN KEY REFERENCES Nhiem_Vu(ID_Nhiem_Vu),
		Diem FLOAT
	);

	CREATE TABLE Cau_Hoi_Bai_Test (
		ID_Cau_Hoi INT PRIMARY KEY IDENTITY,
		ID_Bai_Test INT FOREIGN KEY REFERENCES Bai_Test(ID_Bai_Test),
		Tieu_De NVARCHAR(255),
		Cau_Hoi NVARCHAR(MAX),
		Dap_An_Dung NVARCHAR(MAX),
		Dap_An_Cua_Ban NVARCHAR(MAX),
		Trang_Thai_Dung_Sai NVARCHAR(50)
	);

	CREATE TABLE Ket_Qua_Bai_Test (
		ID_Ket_Qua INT PRIMARY KEY IDENTITY,
		ID_Nhiem_Vu INT FOREIGN KEY REFERENCES Nhiem_Vu(ID_Nhiem_Vu),
		Diem FLOAT,
		Thoi_Gian DATETIME
	);

	CREATE TABLE Phu_Huynh_Hoc_Sinh (
		ID_Tai_Khoan_Phu_Huynh INT FOREIGN KEY REFERENCES Tai_Khoan(ID_Tai_Khoan),
		ID_Tai_Khoan_Hoc_Sinh INT FOREIGN KEY REFERENCES Tai_Khoan(ID_Tai_Khoan),
		Tinh_Trang_Ket_Noi NVARCHAR(50)
	);

	CREATE TABLE Bai_Viet (
		ID INT PRIMARY KEY IDENTITY,
		ID_TAI_KHOAN_ADMIN INT FOREIGN KEY REFERENCES Tai_Khoan(ID_Tai_Khoan),
		Tieu_De NVARCHAR(255),
		Mo_Ta NVARCHAR(max),
		Noi_Dung NVARCHAR(MAX),
		Ngay_Dang DATETIME,
		Trang_Thai NVARCHAR(50),
		Hinh_Anh NVARCHAR(255)
	);

	CREATE TABLE FeedBack (
		ID_FeedBack INT PRIMARY KEY IDENTITY,
		ID_Tai_Khoan INT FOREIGN KEY REFERENCES Tai_Khoan(ID_Tai_Khoan),
		Noi_Dung_FeedBack NVARCHAR(MAX),
		Ngay_Gui DATETIME
	);

	CREATE TABLE Tra_Loi_FeedBack (
		ID_Tra_Loi_FeedBack INT PRIMARY KEY IDENTITY,
		ID_FeedBack INT FOREIGN KEY REFERENCES FeedBack(ID_FeedBack),
		Noi_Dung_Tra_Loi NVARCHAR(MAX),
		Thoi_Gian DATETIME,
		ID_Admin INT FOREIGN KEY REFERENCES Tai_Khoan(ID_Tai_Khoan)
	);

	CREATE TABLE Data_Thong_Bao (
		ID INT PRIMARY KEY IDENTITY,
		Tieu_De NVARCHAR(255),
		Noi_Dung NVARCHAR(MAX)
	);

	CREATE TABLE Thong_Bao_Da_Gui (
		ID INT PRIMARY KEY IDENTITY,
		ID_Thong_Bao INT FOREIGN KEY REFERENCES Data_Thong_Bao(ID),
		ID_Tra_Loi_FeedBack INT FOREIGN KEY REFERENCES Tra_Loi_FeedBack(ID_Tra_Loi_FeedBack),
		ID_Tai_Khoan INT FOREIGN KEY REFERENCES Tai_Khoan(ID_Tai_Khoan),
		Thoi_Gian_Gui DATETIME
	);



