CREATE DATABASE QuanLyKhachSan
GO
USE QuanLyKhachSan
GO
CREATE TABLE KhachSan(
	MaKhachSan INT PRIMARY KEY IDENTITY(1,1),
	TenKhachSan VARCHAR(100),
	DiaChi VARCHAR(100),
	SoPhong INT NOT NULL,
	TienIch VARCHAR(100)
);

CREATE TABLE KhachHang(
	MaKhachHang INT PRIMARY KEY IDENTITY(1,1),
	TenKhachHang VARCHAR(100),
	SoDienThoai VARCHAR(20),
	Email VARCHAR(100) NOT NULL
);

CREATE TABLE DatPhong(
	MaDatPhong INT PRIMARY KEY IDENTITY(1,1),
	MaKhachHang INT,
	MaKhachSan INT ,
	NgayDatHang DATE,
	NgayTra DATE,
	TongTien FLOAT NOT NULL CHECK(TongTien >0),
	FOREIGN KEY(MaKhachHang) REFERENCES dbo.KhachHang(MaKhachHang),
	FOREIGN KEY(MaKhachHang) REFERENCES dbo.KhachSan(MaKhachSan)
);

CREATE TABLE DichVuKhachSan(
	MaDichVu INT PRIMARY KEY IDENTITY(1,1),
	TenDichVu VARCHAR(100),
	GiaDichVu FLOAT
);

CREATE TABLE ChiTietDatPhong(
	MaDatPhong INT PRIMARY KEY IDENTITY(1,1),
	MaDichVu INT,
	SoLuong INT CHECK(SoLuong>0) NOT NULL,
	ThanhTien FLOAT,
	FOREIGN KEY(MaDichVu) REFERENCES dbo.DichVuKhachSan(MaDichVu)
);

GO

--Bai 1

CREATE PROCEDURE Insert1(
	@TenKhachSan VARCHAR(100),
	@DiaChi VARCHAR(100),
	@SoPhong INT,
	@TienIch VARCHAR(100)
)
AS
BEGIN
    INSERT INTO dbo.KhachSan
    (
        TenKhachSan,
        DiaChi,
        SoPhong,
        TienIch
    )
    VALUES
    (   @TenKhachSan, -- TenKhachSan - varchar(100)
        @DiaChi, -- DiaChi - varchar(100)
        @SoPhong,    -- SoPhong - int
        @TienIch  -- TienIch - varchar(100)
        )
END

GO
EXEC dbo.Insert1 @TenKhachSan = 'Ferrari', -- varchar(100)
                 @DiaChi = 'Italia',      -- varchar(100)
                 @SoPhong = 100,      -- int
                 @TienIch = 'Thong lo dit'      -- varchar(100)

EXEC dbo.Insert1 @TenKhachSan = 'Porsche', -- varchar(100)
                 @DiaChi = 'Germany',      -- varchar(100)
                 @SoPhong = 69,      -- int
                 @TienIch = 'Cap long dai'      -- varchar(100)

EXEC dbo.Insert1 @TenKhachSan = 'Dodge', -- varchar(100)
                 @DiaChi = 'USE',      -- varchar(100)
                 @SoPhong = 999,      -- int
                 @TienIch = 'Bu dau ti'      -- varchar(100)


GO

CREATE PROCEDURE INSERT2(
	@TenKhachHang VARCHAR(100),
	@SoDienThoai VARCHAR(20),
	@Email VARCHAR(100)
)
AS
BEGIN
    INSERT INTO dbo.KhachHang
    (
        TenKhachHang,
        SoDienThoai,
        Email
    )
    VALUES
    (   @TenKhachHang, -- TenKhachHang - varchar(100)
        @SoDienThoai, -- SoDienThoai - varchar(20)
        @Email    -- Email - varchar(100)
        )
END
GO
EXEC dbo.INSERT2 @TenKhachHang = 'Phong', -- varchar(100)
                 @SoDienThoai = '098357345',  -- varchar(20)
                 @Email = 'Phong@gmail.com'         -- varchar(100)

EXEC dbo.INSERT2 @TenKhachHang = 'Hung', -- varchar(100)
                 @SoDienThoai = '034534545',  -- varchar(20)
                 @Email = 'Hung@gmail.com'         -- varchar(100)

EXEC dbo.INSERT2 @TenKhachHang = 'Quyet', -- varchar(100)
                 @SoDienThoai = '0237864364',  -- varchar(20)
                 @Email = 'Quyet@gmail.com'         -- varchar(100)

go
CREATE PROCEDURE insert3(
	@MaKhachHang INT,
	@MaKhachSan INT ,
	@NgayDatHang DATE,
	@NgayTra DATE,
	@TongTien FLOAT
)
AS
BEGIN
	IF NOT EXISTS (SELECT dp.NgayDatHang FROM dbo.DatPhong dp WHERE @MaKhachSan = dp.MaKhachSan AND @NgayDatHang = dp.NgayDatHang)
	BEGIN
	    INSERT INTO dbo.DatPhong
	    (
	        MaKhachHang,
	        MaKhachSan,
	        NgayDatHang,
	        NgayTra,
	        TongTien
	    )
	    VALUES
	    (   
		@MaKhachHang, -- MaKhachHang - int
	        @MaKhachSan, -- MaKhachSan - int
	        @NgayDatHang, -- NgayDatHang - date
	        @NgayTra, -- NgayTra - date
	        @TongTien   -- TongTien - float
	        )
	END
	ELSE
		BEGIN
		    PRINT'Khong duoc trung ngay dat phong'
		END
END

GO
EXEC dbo.insert3 @MaKhachHang = 3,            -- int
                 @MaKhachSan = 3,             -- int
                 @NgayDatHang = '2024-3-5', -- date
                 @NgayTra = '2024-4-5',     -- date
                 @TongTien = 456345           -- float

GO
CREATE PROCEDURE insert4(
	@TenDichVu VARCHAR(100),
	@GiaDichVu FLOAT
)
AS
BEGIN
    INSERT INTO dbo.DichVuKhachSan
    (
        TenDichVu,
        GiaDichVu
    )
    VALUES
    (   @TenDichVu, -- TenDichVu - varchar(100)
        @GiaDichVu  -- GiaDichVu - float
        )
END
GO
EXEC dbo.insert4 @TenDichVu = 'GRAHHH', -- varchar(100)
                 @GiaDichVu = 3456 -- float

GO
CREATE PROCEDURE insert5(
@MaDichVu INT,
	@SoLuong INT,
@ThanhTien FLOAT
)
AS
BEGIN
	INSERT INTO dbo.ChiTietDatPhong
	(
	    MaDichVu,
	    SoLuong,
	    ThanhTien
	)
	VALUES
	(   @MaDichVu, -- MaDichVu - int
	    @SoLuong, -- SoLuong - int
	    @ThanhTien  -- ThanhTien - float
	    )

END
GO
EXEC dbo.insert5 @MaDichVu = 1,   -- int
                 @SoLuong = 2343,    -- int
                 @ThanhTien = 55 -- float

--Bai2
GO
CREATE VIEW ThongTinDat
AS
SELECT kh.TenKhachHang, ks.TenKhachSan, dp.NgayDatHang, dp.NgayTra,dp.TongTien  FROM dbo.DatPhong dp
JOIN dbo.KhachHang kh ON kh.MaKhachHang = dp.MaKhachHang
JOIN dbo.KhachSan ks ON ks.MaKhachSan = dp.MaKhachSan

GO

SELECT * FROM dbo.ThongTinDat
--Bai3
GO
CREATE FUNCTION TGLuuTru(
	@MaKhachHang INT
)
RETURNS INT
AS
BEGIN
DECLARE @TGLuuTru INT;
    SELECT  @TGLuuTru = DATEDIFF(DAY, dp.NgayDatHang, dp.NgayTra) FROM dbo.DatPhong dp WHERE @MaKhachHang = dp.MaKhachHang
	RETURN  @TGLuuTru
END
GO
SELECT dbo.TGLuuTru(1) AS TG

--Bai 4
GO
CREATE TRIGGER KiemTraGiaDichVu ON dbo.ChiTietDatPhong
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @TongGiaDV FLOAT;
	DECLARE @GiaTriPhong FLOAT
	DECLARE @MaDatPhong INT;

	SELECT @MaDatPhong = ct.MaDatPhong, @TongGiaDV = SUM(dv.GiaDichVu), @GiaTriPhong = ct.ThanhTien FROM dbo.ChiTietDatPhong ct
	JOIN dbo.DichVuKhachSan dv ON dv.MaDichVu = ct.MaDichVu
	GROUP BY ct.MaDatPhong, ct.ThanhTien


	IF @TongGiaDV > @GiaTriPhong*0.5
	BEGIN
	    ROLLBACK TRANSACTION
		RAISERROR('t?ng giá d?ch v? không ???c l?n h?n 50% giá tr? phòng',16,1)
	END
	ELSE
	BEGIN
	    INSERT INTO dbo.ChiTietDatPhong
	    (
	        MaDichVu,
	        SoLuong,
	        ThanhTien
	    )
	    SELECT MaDichVu,
	        SoLuong,
	        ThanhTien FROM dbo.ChiTietDatPhong
	END
END

SELECT * FROM dbo.DichVuKhachSan
SELECT * FROM dbo.ChiTietDatPhong


GO
    DROP TRIGGER dbo.KiemTraGiaDichVu


INSERT INTO dbo.ChiTietDatPhong
(
    MaDichVu,
    SoLuong,
    ThanhTien
)
VALUES
(   1, -- MaDichVu - int
    234,    -- SoLuong - int
    12  -- ThanhTien - float
    )
				 SELECT * FROM dbo.ChiTietDatPhong

--Bai 5
GO
CREATE PROCEDURE TinhTongTien(
	@MaDatPhong INT
)
AS
BEGIN
    SELECT SUM(ct.SoLuong*ct.ThanhTien) AS Tong FROM dbo.ChiTietDatPhong ct
	WHERE @MaDatPhong = ct.MaDatPhong
END

GO
DROP PROCEDURE dbo.TinhTongTien
EXEC dbo.TinhTongTien @MaDatPhong = 1 -- int





