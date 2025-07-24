CREATE DATABASE SD1801_TH03089
GO
USE SD1801_TH03089
GO
CREATE TABLE LoaiBanh(
	BanhID INT PRIMARY KEY IDENTITY(1,1),
	TenBanh VARCHAR(50),
	GiaBanh DECIMAL(10,2),
	SoLuongBanh INT,
	MotaBanh VARCHAR(255)
);

CREATE TABLE KhachHang(
	KhachHangID INT PRIMARY KEY IDENTITY(1,1),
	Ten VARCHAR(50),
	SDT VARCHAR(15),
	Email VARCHAR(100),
	DiaChi VARCHAR(100),
	XepLoai VARCHAR(50)
);

CREATE TABLE DonHang(
	DonHangID INT PRIMARY KEY IDENTITY(1,1),
	NgayDatHang DATETIME,
	KhachHangID INT,
	TongTien DECIMAL(10,2),
	FOREIGN KEY(KhachHangID) REFERENCES dbo.KhachHang(KhachHangID)
);

DROP TABLE dbo.DonHang

CREATE TABLE ChiTietDonHang(
	DonHang_ChiTietID INT PRIMARY KEY IDENTITY(1,1),
	DonHangID INT,
	BanhID INT,
	SoLuong INT,
	DonGia DECIMAL(10,2),
	FOREIGN KEY(DonHangID) REFERENCES dbo.DonHang(DonHangID),
	FOREIGN KEY(BanhID) REFERENCES dbo.LoaiBanh(BanhID)
);


INSERT INTO dbo.LoaiBanh
(
    TenBanh,
    GiaBanh,
    SoLuongBanh,
    MotaBanh
)
VALUES
(   'SIGMA', -- TenBanh - varchar(50)
    999, -- GiaBanh - decimal(10, 2)
    100, -- SoLuongBanh - int
    'Ngon'  -- MotaBanh - varchar(255)
    ),
	(   'SKIBIDI', -- TenBanh - varchar(50)
    696, -- GiaBanh - decimal(10, 2)
    36, -- SoLuongBanh - int
    'Tuyet'  -- MotaBanh - varchar(255)
    ),
	(   'OHIO', -- TenBanh - varchar(50)
    123, -- GiaBanh - decimal(10, 2)
    35, -- SoLuongBanh - int
    'HEHEHE'  -- MotaBanh - varchar(255)
    );


	INSERT INTO dbo.KhachHang
	(
	    Ten,
	    SDT,
	    Email,
	    DiaChi,
	    XepLoai
	)
	VALUES
	(   'Phong', -- Ten - varchar(50)
	    '092824254', -- SDT - varchar(15)
	    'Phong@gmail.com', -- Email - varchar(100)
	    'HaNoi', -- DiaChi - varchar(100)
	    'VIP'  -- XepLoai - varchar(50)
	    ),
		(   'An', -- Ten - varchar(50)
	    '023532325', -- SDT - varchar(15)
	    'An@gmail.com', -- Email - varchar(100)
	    'TP HCM', -- DiaChi - varchar(100)
	    'THG'  -- XepLoai - varchar(50)
	    ),
		(   'Dan', -- Ten - varchar(50)
	    '032732532', -- SDT - varchar(15)
	    'Dan@gmail.com', -- Email - varchar(100)
	    'ThanhHoa', -- DiaChi - varchar(100)
	    'VIP'  -- XepLoai - varchar(50)
	    ),
		(   'Quyet', -- Ten - varchar(50)
	    '0326353534', -- SDT - varchar(15)
	    'Quyet@gmail.com', -- Email - varchar(100)
	    'VungTau', -- DiaChi - varchar(100)
	    'THG'  -- XepLoai - varchar(50)
	    );

	INSERT INTO dbo.DonHang
	(
	    NgayDatHang,
	    KhachHangID,
	    TongTien
	)
	VALUES
	(   '2024-9-9', -- NgayDatHang - datetime
	    1, -- KhachHangID - int
	    999  -- TongTien - decimal(10, 2)
	    ),
		(   '2024-6-3', -- NgayDatHang - datetime
	    2, -- KhachHangID - int
	    235  -- TongTien - decimal(10, 2)
	    ),
		(   '2024-10-3', -- NgayDatHang - datetime
	    3, -- KhachHangID - int
	    755  -- TongTien - decimal(10, 2)
	    );

		INSERT INTO dbo.ChiTietDonHang
		(
		    DonHangID,
		    BanhID,
		    SoLuong,
		    DonGia
		)
		VALUES
		(   1, -- DonHangID - int
		    1, -- BanhID - int
		    123, -- SoLuong - int
		    935  -- DonGia - decimal(10, 2)
		    ),
			(   2, -- DonHangID - int
		    2, -- BanhID - int
		    46, -- SoLuong - int
		    467  -- DonGia - decimal(10, 2)
		    ),
			(   3, -- DonHangID - int
		    3, -- BanhID - int
		    27, -- SoLuong - int
		    356  -- DonGia - decimal(10, 2)
		    );



--Bai 1

DECLARE @KhachHangID INT = 1;

SELECT * FROM dbo.KhachHang kh
WHERE @KhachHangID = kh.KhachHangID


--Bai 2

DECLARE @DonHangID INT = 1

SELECT ct.DonHang_ChiTietID,
       ct.DonHangID,
       ct.BanhID,
       (ct.DonGia*ct.SoLuong) AS 'Tong Tien' FROM dbo.ChiTietDonHang ct
	   WHERE @DonHangID = ct.DonHangID

--Bai 3

DECLARE @BangFake1 TABLE( Ten VARCHAR(50), NgayDatHang DATETIME, DonHangID INT, TenBanh VARCHAR(50), SoLuong int)

INSERT INTO @BangFake1
(
    Ten,
    NgayDatHang,
    DonHangID,
    TenBanh,
    SoLuong
)
SELECT kh.Ten, dh.NgayDatHang, ct.DonHangID, lb.TenBanh, ct.SoLuong
FROM dbo.KhachHang kh
JOIN dbo.DonHang dh ON dh.KhachHangID = kh.KhachHangID
JOIN dbo.ChiTietDonHang ct ON ct.DonHangID = dh.DonHangID
JOIN dbo.LoaiBanh lb ON lb.BanhID = ct.BanhID

SELECT * FROM @BangFake1

--Bai 4

DECLARE @BangFake2 TABLE(KhachHangID INT, Ten VARCHAR(50), SoDonMua int)
INSERT INTO @BangFake2
(
    KhachHangID,
    Ten,
    SoDonMua
)
SELECT kh.KhachHangID, kh.Ten, COUNT(ct.DonHangID) AS SoDonMua

FROM dbo.KhachHang kh
JOIN dbo.DonHang dh ON dh.KhachHangID = kh.KhachHangID
JOIN dbo.ChiTietDonHang ct ON ct.DonHangID = dh.DonHangID
GROUP BY 
kh.KhachHangID, kh.Ten
SELECT * FROM @BangFake2

--Bai 5

SELECT * FROM dbo.LoaiBanh

UPDATE dbo.LoaiBanh
SET SoLuongBanh = 3
WHERE BanhID = 1

DECLARE @BanhID INT = 1

IF EXISTS(SELECT lb.SoLuongBanh FROM dbo.LoaiBanh lb WHERE @BanhID = lb.BanhID AND lb.SoLuongBanh<10 )
	PRINT'Sap het hang'
ELSE
	PRINT'Con Nhieu Hang'

--Bai 6

DECLARE @KhachHangID INT = 1;

SELECT kh.KhachHangID, kh.Ten, COUNT(ct.DonHangID) AS 'So Lan Mua',
CASE
	WHEN COUNT(ct.DonHangID) >5 THEN 'VIP'
	WHEN COUNT(ct.DonHangID) <5 THEN 'THG'
END AS 'Xep Loai'

FROM dbo.KhachHang kh
JOIN dbo.DonHang dh ON dh.KhachHangID = kh.KhachHangID
JOIN dbo.ChiTietDonHang ct ON ct.DonHangID = dh.DonHangID
WHERE @KhachHangID = kh.KhachHangID
GROUP BY 
kh.KhachHangID, kh.Ten


--Bai 7
SELECT * FROM dbo.LoaiBanh

DECLARE @BanhID int = 1;
DECLARE @TenBanh VARCHAR(50);
DECLARE @GiaBanh INT;

WHILE @BanhID <=3
BEGIN
	SELECT @TenBanh = lb.TenBanh, @GiaBanh = lb.GiaBanh
	FROM dbo.LoaiBanh lb
	WHERE lb.GiaBanh >100 AND @BanhID = lb.BanhID

	PRINT 'BanhID so ' + CAST(@BanhID AS VARCHAR) + ' co ten la ' + CAST(@TenBanh AS VARCHAR) + ' co gia la ' + CAST(@GiaBanh AS VARCHAR);
	SET @BanhID = @BanhID+1;
END

--Bai 8

SELECT * FROM dbo.ChiTietDonHang

SELECT ct.DonHang_ChiTietID,
       ct.DonHangID,
       ct.BanhID,
       ct.SoLuong,
       ct.DonGia,
CASE
	WHEN ct.SoLuong > 5 THEN 'VIPPP'
	WHEN ct.SoLuong >2 AND ct.SoLuong<5 THEN 'THG'
	WHEN ct.SoLuong = 1 THEN 'Le'
END AS PhanLoaiDon
	   FROM dbo.ChiTietDonHang ct

	UPDATE dbo.ChiTietDonHang 
	SET SoLuong = 1
	WHERE DonHang_ChiTietID = 3