CREATE DATABASE DU_AN_MAU
GO
USE DU_AN_MAU
GO
CREATE TABLE HocVien (
    MaHV int PRIMARY KEY IDENTITY,
    Name VARCHAR(100) NOT NULL,
	Gender VARCHAR(100),
    Birthday DATE,
    SDT VARCHAR(100),
    Email VARCHAR(100),
	Avatar VARCHAR(200),
	Status VARCHAR(100)
);
SELECT name, SDT, Email FROM HocVien WHERE maHV = 1

SELECT * FROM dbo.HocVien;
SELECT hv.MaHV, hv.Name, hv.Score FROM dbo.HocVien hv

UPDATE dbo.HocVien 
SET Score = 10
WHERE HocVien.MaHV = 11

DROP TABLE dbo.HocVien
ALTER TABLE dbo.HocVien
ADD Score DECIMAL(10,2)

	SELECT * FROM dbo.HocVien
	SELECT * FROM dbo.TaiKhoan


CREATE TRIGGER trg_DeleteHocVien
ON TaiKhoan
AFTER DELETE
AS
BEGIN
    DELETE FROM HocVien
    WHERE MaHV IN (SELECT deleted.MaHV FROM deleted);
END;

CREATE TABLE TaiKhoan(
	MaTK int PRIMARY KEY IDENTITY,
	MaHV int FOREIGN KEY(MaHV) REFERENCES dbo.HocVien(MaHV) ON DELETE CASCADE,
	Username VARCHAR(100),
	Password VARCHAR(100)
);		


SELECT * FROM dbo.TaiKhoan
DROP TABLE dbo.TaiKhoan

DELETE dbo.KhoaHoc
WHERE KhoaHoc.ID = 12

SELECT TaiKhoan.Username FROM dbo.TaiKhoan
SELECT TaiKhoan.Password FROM dbo.TaiKhoan

CREATE TABLE KhoaHoc (
    IDKH INT IDENTITY(1,1) PRIMARY KEY,
	IDCD INT,-- Mã khóa học (tự động tăng)
    TenKhoaHoc NVARCHAR(255) NOT NULL, -- Tên khóa học
    MoTa NVARCHAR(MAX),                -- Mô tả khóa học
    GiaTien DECIMAL(10,2) NOT NULL CHECK (GiaTien >= 0), -- Giá tiền khóa học
    HinhAnh VARCHAR(255)              -- Đường dẫn ảnh đại diện
	FOREIGN KEY(IDCD) REFERENCES dbo.ChuyenDe(IDCD)
);

ALTER TABLE dbo.HocVien
DROP COLUMN Status

DROP TABLE dbo.KhoaHoc
SELECT * FROM dbo.KhoaHoc
SELECT * FROM dbo.ChuyenDe

SELECT tenKhoahoc FROM Khoahoc WHERE idcd = (SELECT idcd FROM Chuyende WHERE tenChuyende = 'Lập trình Java')


INSERT INTO KhoaHoc_ChuyenDe (ID_KhoaHoc, ID_ChuyenDe) VALUES (9, 6);
INSERT INTO KhoaHoc_ChuyenDe (ID_KhoaHoc, ID_ChuyenDe) VALUES (10, 6);
INSERT INTO KhoaHoc_ChuyenDe (ID_KhoaHoc, ID_ChuyenDe) VALUES (11, 6);


CREATE TABLE ChuyenDe (
    IDCD INT IDENTITY(1,1) PRIMARY KEY,  -- Mã chuyên đề (tự động tăng)
    TenChuyenDe NVARCHAR(255) NOT NULL, -- Tên chuyên đề
    MoTa NVARCHAR(MAX),                 -- Mô tả chuyên đề
    GiaTien FLOAT CHECK (GiaTien >= 0), -- Giá tiền chuyên đề (nếu bán lẻ)
    HinhAnh VARCHAR(255)                -- Đường dẫn ảnh chuyên đề
);

DROP TABLE dbo.ChuyenDe


INSERT INTO dbo.ChuyenDe
(
    TenChuyenDe,
    MoTa,
    GiaTien,
    HinhAnh
)
VALUES
(N'Lập trình Java', N'Khóa học Java căn bản đến nâng cao', 3000000, 'D:\CODE\Du An Mau\ImageCD\Java.png'),
(N'Lập trình Python', N'Khóa học Python từ cơ bản đến chuyên sâu', 2500000, 'D:\CODE\Du An Mau\ImageCD\Python.png'),
(N'Web Frontend', N'Khóa học HTML, CSS, JavaScript', 2000000, 'D:\CODE\Du An Mau\ImageCD\Web.png');

INSERT INTO KhoaHoc (IDCD,TenKhoaHoc, MoTa, GiaTien, HinhAnh) 
VALUES 
	(1,N'Sigma 2 bước đi trước', N'Cách trở thành sigma', 9999999, 'D:\CODE\Du An Mau\ImageCD\thosewhoknow.jpg'),
	(3,N'Photoshop cơ bản', N'Photoshop cho newbie', 2000000, 'D:\CODE\Du An Mau\ImageCD\photoshop.png'),
	(1,N'Khóa học Java cơ bản', N'Học lập trình Java từ cơ bản đến nâng cao.', 1500000, 'D:\CODE\Du An Mau\ImageCD\Java.png'),
    (2,N'Khóa học Python nâng cao', N'Chuyên sâu về Python, AI, và Machine Learning.', 2000000, 'D:\CODE\Du An Mau\ImageCD\Python.png'),
    (3,N'Khóa học Web Fullstack', N'Học HTML, CSS, JavaScript, Node.js và React.', 2500000, 'D:\CODE\Du An Mau\ImageCD\Web.png')

	DELETE dbo.ChuyenDe WHERE ChuyenDe.GiaTien = 999999999

SELECT * FROM dbo.KhoaHoc
SELECT * FROM dbo.ChuyenDe
SELECT * FROM dbo.KhoaHoc_ChuyenDe

INSERT INTO dbo.KhoaHoc_ChuyenDe
(
    ID_ChuyenDe,
    ID_KhoaHoc
)
VALUES
(   1, -- ID_ChuyenDe - int
    2  -- ID_KhoaHoc - int
    ),(   2, -- ID_ChuyenDe - int
    4  -- ID_KhoaHoc - int
    ),(   3, -- ID_ChuyenDe - int
    5  -- ID_KhoaHoc - int
    ),(   1, -- ID_ChuyenDe - int
    1  -- ID_KhoaHoc - int
    ),(   3, -- ID_ChuyenDe - int
    2  -- ID_KhoaHoc - int
    )

	SELECT COLUMN_NAME 
FROM INFORMATION_SCHEMA.COLUMNS 
WHERE TABLE_NAME = 'KhoaHoc';

INSERT INTO dbo.ChuyenDe
(
    TenChuyenDe,
    MoTa,
    GiaTien,
    HinhAnh
)
VALUES
(   N'Vật Lý pro gyat',  -- TenChuyenDe - nvarchar(255)
    'Trở thành sigma mewing vật lý', -- MoTa - nvarchar(max)
    1000000, -- GiaTien - float
    'D:\CODE\Du An Mau\CodeDM\src\Resources\physic.png'  -- HinhAnh - varchar(255)
    )

INSERT INTO dbo.KhoaHoc
	(
	    TenKhoaHoc,
	    MoTa,
	    GiaTien,
	    HinhAnh
	)
	VALUES
	(   N'Cho AI vô web',  -- TenKhoaHoc - nvarchar(255)
	    'AI sẽ xâm chiếm con người', -- MoTa - nvarchar(max)
	    3000000, -- GiaTien - decimal(10, 2)
	    'D:\CODE\Du An Mau\CodeDM\src\Resources\AI.jpg'  -- HinhAnh - varchar(255)
	    )

	



SELECT * FROM dbo.KhoaHoc
SELECT * FROM dbo.ChuyenDe
SELECT * FROM DangKyKH
SELECT * FROM dbo.HocVien
SELECT * FROM dbo.KhoaHoc_ChuyenDe

INSERT INTO dbo.KhoaHoc_ChuyenDe
(
    ID_ChuyenDe,
    ID_KhoaHoc
)
VALUES
(   5, -- ID_ChuyenDe - int
    6  -- ID_KhoaHoc - int
    ),(   5, -- ID_ChuyenDe - int
      -- ID_KhoaHoc - int
    );

CREATE TABLE ThanhToan (
    maTT INT PRIMARY KEY IDENTITY,  -- Mã thanh toán
    maHV INT NOT NULL,  -- Mã học viên
    Total DECIMAL(10,2) NOT NULL CHECK (Total >= 0),
    Payment NVARCHAR(100) NOT NULL CHECK (Payment IN ('Chuyển khoản', 'Tiền mặt')),

    FOREIGN KEY (maHV) REFERENCES dbo.HocVien(MaHV) ON DELETE CASCADE
);

CREATE TABLE DangKyKH (
    IDDKKH INT PRIMARY KEY IDENTITY,
    maHV INT NOT NULL,
    maKH INT NOT NULL,

    FOREIGN KEY (maHV) REFERENCES dbo.HocVien(MaHV) ON DELETE CASCADE,
    FOREIGN KEY (maKH) REFERENCES dbo.KhoaHoc(IDKH) ON DELETE CASCADE
);


DROP TABLE dbo.DangKyKH

CREATE TABLE ChiTietThanhToan (
    maTT INT NOT NULL,
    maKH INT NOT NULL,

    FOREIGN KEY (maTT) REFERENCES ThanhToan(maTT) ON DELETE CASCADE,
    FOREIGN KEY (maKH) REFERENCES KhoaHoc(IDKH) ON DELETE CASCADE
);

SELECT * FROM dbo.ThanhToan
SELECT * FROM dbo.ChiTietThanhToan
SELECT * FROM dbo.DangKyKH

SELECT * FROM dbo.HocVien
SELECT * FROM dbo.TaiKhoan
SELECT * FROM dbo.ChuyenDe

	

DELETE dbo.HocVien WHERE HocVien.MaHV = 13

SELECT cd.TenChuyenDe, COUNT(kh.IDKH) FROM dbo.ChuyenDe cd
JOIN dbo.KhoaHoc kh ON kh.IDCD = cd.IDCD
JOIN dbo.DangKyKH dk ON dk.maKH = kh.IDKH
GROUP BY cd.TenChuyenDe

SELECT 
    CD.TenChuyenDe AS [Chuyên đề], 
    COUNT(DISTINCT KH.IDKH) AS [Số khóa học], 
    COUNT(DISTINCT DK.maHV) AS [Số học viên], 
    SUM(TT.Total) AS [Doanh thu], 
    MAX(TT.Total) AS [Cao nhất], 
    MIN(TT.Total) AS [Thấp nhất], 
    AVG(TT.Total) AS [Trung bình]
FROM ChuyenDe CD
LEFT JOIN KhoaHoc KH ON CD.IDCD = KH.IDCD
LEFT JOIN DangKyKH DK ON KH.IDKH = DK.IDDKKH
LEFT JOIN ThanhToan TT ON DK.maHV = TT.maHV
GROUP BY CD.TenChuyenDe;

SELECT * FROM dbo.TaiKhoan
ALTER TABLE dbo.TaiKhoan
ADD NgayTao DATE 

UPDATE dbo.TaiKhoan
SET NgayTao = '1999-11-29'
WHERE TaiKhoan.MaTK = 5

SELECT YEAR(tk.NgayTao), COUNT(hv.MaHV), MIN(tk.NgayTao), MAX(tk.ngaytao) FROM dbo.HocVien hv
JOIN dbo.TaiKhoan tk ON tk.MaHV = hv.MaHV
GROUP BY YEAR(tk.NgayTao)

SELECT * FROM dbo.TaiKhoan