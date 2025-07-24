CREATE DATABASE QUANLYNHANVIEN
GO
USE QUANLYNHANVIEN

CREATE TABLE PhongBan(
	PhongBanID INT Primary KEY,
	TenPhongBan VARCHAR(100)
);

CREATE TABLE NhanVien(
	NhanVienID INT Primary KEY,
	TenNhanVien NVARCHAR(100),
	SDT NVARCHAR(15),
	Email NVARCHAR(100),
	PhongBanID INT,
	FOREIGN KEY(PhongBanID) REFERENCES dbo.PhongBan(PhongBanID)
);

CREATE TABLE LichTruc(
	LichTrucID INT Primary KEY,
NgayTruc DATE,
PhongBanID INT, Foreign KEY(PhongBanID) REFERENCES dbo.PhongBan(PhongBanID)
);

CREATE TABLE ChiTietLichTruc(
	ChiTietTrucID INT Primary KEY,
	LichTrucID INT,
	NhanVienID INT,
	BatDauTruc DATETIME,
	ThoiGianTruc INT,

	FOREIGN KEY(LichTrucID) REFERENCES dbo.LichTruc(LichTrucID),
	FOREIGN KEY(NhanVienID) REFERENCES dbo.NhanVien(NhanVienID)
);

INSERT INTO dbo.PhongBan (PhongBanID, TenPhongBan)
VALUES
(1, 'Phong Ban 1'),
(2, 'Phong Ban 2'),
(3, 'Phong Ban 3'),
(4, 'Phong Ban 4'),
(5, 'Phong Ban 5');


INSERT INTO dbo.NhanVien (NhanVienID, TenNhanVien, SDT, Email, PhongBanID)
VALUES
(1, 'Nguyen Van A', '0123456789', 'nguyenvana@example.com', 1),
(2, 'Tran Thi B', '0987654321', 'tranthib@example.com', 2),
(3, 'Le Van C', '0111222333', 'levanc@example.com', 3),
(4, 'Pham Thi D', '0222333444', 'phamthid@example.com', 4),
(5, 'Hoang Van E', '0333444555', 'hoangvane@example.com', 5);


INSERT INTO dbo.LichTruc (LichTrucID, NgayTruc, PhongBanID)
VALUES
(1, '2024-09-26', 1),
(2, '2024-09-27', 2),
(3, '2024-09-28', 3),
(4, '2024-09-29', 4),
(5, '2024-09-30', 5);


INSERT INTO dbo.ChiTietLichTruc (ChiTietTrucID, LichTrucID, NhanVienID, BatDauTruc, ThoiGianTruc)
VALUES
(1, 1, 1, '2024-09-26 08:00:00', 8),
(2, 2, 2, '2024-09-27 09:00:00', 8),
(3, 3, 3, '2024-09-28 10:00:00', 8),
(4, 4, 4, '2024-09-29 11:00:00', 8),
(5, 5, 5, '2024-09-30 12:00:00', 8);


--Bai1
DECLARE @NhanvienID INT = 1
SELECT * FROM dbo.NhanVien
WHERE @NhanvienID = NhanVienID

--Bai 2

DECLARE @LichtrucID INT = 1;

SELECT nv.NhanVienID, nv.TenNhanVien, SUM(ct.ThoiGianTruc) AS 'Tong TG truc'  FROM dbo.NhanVien nv
JOIN dbo.ChiTietLichTruc ct ON ct.NhanVienID = nv.NhanVienID
WHERE @LichtrucID = ct.LichTrucID
GROUP BY  nv.NhanVienID, nv.TenNhanVien

--Bai 3

DECLARE @ChiTietLichTruc TABLE(ChiTietTrucID INT Primary KEY,
	LichTrucID INT,
	NhanVienID INT,
	BatDauTruc DATETIME,
	ThoiGianTruc INT);

INSERT INTO @ChiTietLichTruc
(
    ChiTietTrucID,
    LichTrucID,
    NhanVienID,
    BatDauTruc,
    ThoiGianTruc
)
SELECT ChiTietTrucID,
       LichTrucID,
       NhanVienID,
       BatDauTruc,
       ThoiGianTruc FROM dbo.ChiTietLichTruc
SELECT * FROM @ChiTietLichTruc

--Bai 4

DECLARE @DanhSachNhanVien  TABLE(MaNhanVien INT, TenNhanVien VARCHAR(50), SoCaThamGiaTruc int);

INSERT INTO @DanhSachNhanVien
(
    MaNhanVien,
    TenNhanVien,
    SoCaThamGiaTruc
)
SELECT
	nv.NhanVienID,
	nv.TenNhanVien,
	COUNT(ct.ChiTietTrucID)
FROM dbo.NhanVien nv
JOIN dbo.ChiTietLichTruc ct ON ct.NhanVienID = nv.NhanVienID 
GROUP BY nv.NhanVienID, nv.TenNhanVien

SELECT * FROM @DanhSachNhanVien


--Bai 5

DECLARE @NhanvienID int = 1;
DECLARE @Socatruc INT;

SELECT @Socatruc = COUNT(ct.ChiTietTrucID)

FROM dbo.NhanVien nv
JOIN dbo.ChiTietLichTruc ct ON ct.NhanVienID = nv.NhanVienID

GROUP BY nv.NhanVienID, nv.TenNhanVien

IF @Socatruc >2
	PRINT 'Truc nhieu ca'
ELSE
	PRINT 'Truc it ca'


--Bai 6

DECLARE @NhanVienID INT = 1;

SELECT nv.NhanVienID, nv.TenNhanVien, COUNT(ct.ChiTietTrucID) AS 'So lan truc',
CASE
	WHEN COUNT(ct.ChiTietTrucID) >5 THEN 'NhanVien XuatSac'
	ELSE 'NhanVien BinhThuong'
END AS 'Xep Loai'

FROM dbo.NhanVien nv
JOIN dbo.ChiTietLichTruc ct ON ct.NhanVienID = nv.NhanVienID
GROUP BY nv.NhanVienID, nv.TenNhanVien

--Bai 7

DECLARE @ChiTietTrucID INT = 1;
DECLARE @TenNhanVien VARCHAR(50);
DECLARE @BatDauTruc DATETIME;
DECLARE @ThoiGianTruc INT;

WHILE @ChiTietTrucID <=5
BEGIN
	SELECT @ChiTietTrucID = ct.ChiTietTrucID,
	@TenNhanVien = nv.TenNhanVien,
	@BatDauTruc = ct.BatDauTruc,
	@ThoiGianTruc = ct.ThoiGianTruc
	FROM dbo.ChiTietLichTruc ct
	JOIN dbo.NhanVien nv ON nv.NhanVienID = ct.NhanVienID
	WHERE @ChiTietTrucID = ct.ChiTietTrucID

	PRINT 'Chi tiet truc so ' + CAST(@ChiTietTrucID AS VARCHAR) + ' có tên nhân viên là ' + CAST(@TenNhanVien AS VARCHAR) + ' truc luc ' + CAST(@BatDauTruc AS VARCHAR) + ' trong ' + CAST(@ThoiGianTruc AS VARCHAR) + ' gio';

	SET @ChiTietTrucID = @ChiTietTrucID + 1;
END;

--Bai 8

SELECT ct.NhanVienID, ct.ThoiGianTruc,
	
	CASE
		WHEN ct.ThoiGianTruc >3 THEN 'Truc dai'
		WHEN ct.ThoiGianTruc <=3 THEN 'Truc ngan'
	END AS 'TG truc'
FROM dbo.ChiTietLichTruc ct

