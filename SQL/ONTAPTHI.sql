CREATE DATABASE QUANLYPHIM
GO
USE QUANLYPHIM
GO
-- Bảng KhachHang
CREATE TABLE KhachHang (
    MaKhachHang INT PRIMARY KEY NOT NULL,
    HoTen NVARCHAR(100) NOT NULL,
    SoDienThoai NVARCHAR(15) NOT NULL,
    Email NVARCHAR(100) NOT NULL,
    NgaySinh DATE NOT NULL
);

-- Bảng Phim
CREATE TABLE Phim (
    MaPhim INT PRIMARY KEY NOT NULL,
    TenPhim NVARCHAR(100) NOT NULL,
    TheLoai NVARCHAR(50) NOT NULL,
    ThoiLuong INT NOT NULL,
    DaoDien NVARCHAR(100) NOT NULL,
    NgayKhoiChieu DATE NOT NULL
);

-- Bảng LichChieu
CREATE TABLE LichChieu (
    MaLichChieu INT PRIMARY KEY NOT NULL,
    MaPhim INT NOT NULL,
    NgayChieu DATE NOT NULL,
    GioChieu TIME NOT NULL,
    PhongChieu NVARCHAR(50) NOT NULL,
    FOREIGN KEY (MaPhim) REFERENCES Phim(MaPhim)
);

-- Bảng VeXemPhim
CREATE TABLE VeXemPhim (
    MaVe INT PRIMARY KEY NOT NULL,
    MaLichChieu INT NOT NULL,
    MaKhachHang INT NOT NULL,
    SoGhe NVARCHAR(10) NOT NULL,
    GiaVe DECIMAL(10,2) NOT NULL CHECK (GiaVe >= 0),
    TrangThaiVe NVARCHAR(20) NOT NULL,
    FOREIGN KEY (MaLichChieu) REFERENCES LichChieu(MaLichChieu),
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang)
);

-- Insert KhachHang
INSERT INTO KhachHang (MaKhachHang, HoTen, SoDienThoai, Email, NgaySinh) VALUES
(1, 'Nguyen Van A', '0901234567', 'a@gmail.com', '1990-01-01'),
(2, 'Tran Thi B', '0902345678', 'b@gmail.com', '1991-02-02'),
(3, 'Le Van C', '0903456789', 'c@gmail.com', '1992-03-03'),
(4, 'Pham Thi D', '0904567890', 'd@gmail.com', '1993-04-04'),
(5, 'Do Van E', '0905678901', 'e@gmail.com', '1994-05-05');

-- Insert Phim
INSERT INTO Phim (MaPhim, TenPhim, TheLoai, ThoiLuong, DaoDien, NgayKhoiChieu) VALUES
(1, 'Phim 1', 'Hành Động', 120, 'DaoDien 1', '2024-01-01'),
(2, 'Phim 2', 'Tình Cảm', 90, 'DaoDien 2', '2024-02-01'),
(3, 'Phim 3', 'Kinh Dị', 110, 'DaoDien 3', '2024-03-01'),
(4, 'Phim 4', 'Hài', 100, 'DaoDien 4', '2024-04-01'),
(5, 'Phim 5', 'Phiêu Lưu', 130, 'DaoDien 5', '2024-05-01');

-- Insert LichChieu
INSERT INTO LichChieu (MaLichChieu, MaPhim, NgayChieu, GioChieu, PhongChieu) VALUES
(1, 1, '2024-01-10', '18:00:00', 'Phong 1'),
(2, 2, '2024-02-10', '19:00:00', 'Phong 2'),
(3, 3, '2024-03-10', '20:00:00', 'Phong 3'),
(4, 4, '2024-04-10', '21:00:00', 'Phong 4'),
(5, 5, '2024-05-10', '22:00:00', 'Phong 5');

-- Insert VeXemPhim
INSERT INTO VeXemPhim (MaVe, MaLichChieu, MaKhachHang, SoGhe, GiaVe, TrangThaiVe) VALUES
(1, 1, 1, 'A1', 100000, 'Đã đặt'),
(2, 2, 2, 'B1', 150000, 'Đã đặt'),
(3, 3, 3, 'C1', 120000, 'Đã hủy'),
(4, 4, 4, 'D1', 130000, 'Đã đặt'),
(5, 5, 5, 'E1', 110000, 'Đã đặt');

--Bai1 STORE PRODUCE
GO

CREATE PROCEDURE insertKhachHang(
	@MaKhachHang INT ,
    @HoTen NVARCHAR(100) ,
    @SoDienThoai NVARCHAR(15) ,
    @Email NVARCHAR(100) ,
    @NgaySinh DATE 
)
AS 
BEGIN
    IF @MaKhachHang IS NULL OR @HoTen IS NULL OR @SoDienThoai IS NULL OR @Email IS NULL OR @NgaySinh IS NULL
    BEGIN
        RAISERROR('Khong dc de trong', 16,1);
		RETURN;
    END
        INSERT INTO dbo.KhachHang
        (
            MaKhachHang,
            HoTen,
            SoDienThoai,
            Email,
            NgaySinh
        )
        VALUES
        (   @MaKhachHang,        -- MaKhachHang - int
            @HoTen,      -- HoTen - nvarchar(100)
            @SoDienThoai,      -- SoDienThoai - nvarchar(15)
            @Email,      -- Email - nvarchar(100)
            @NgaySinh -- NgaySinh - date
            )
END

GO
CREATE PROCEDURE InsertPhim(
	@MaPhim INT ,
    @TenPhim NVARCHAR(100) ,
    @TheLoai NVARCHAR(50) ,
    @ThoiLuong INT ,
    @DaoDien NVARCHAR(100) ,
    @NgayKhoiChieu DATE
)
AS
BEGIN
    IF @MaPhim IS NULL OR @TenPhim IS NULL OR @TheLoai IS NULL OR @ThoiLuong IS NULL OR @DaoDien IS NULL OR @NgayKhoiChieu IS NULL
    BEGIN
        RAISERROR('Khong dc de trong', 16,1);
		RETURN;
    END
	ELSE
	BEGIN
	    INSERT INTO dbo.Phim
	    (
	        MaPhim,
	        TenPhim,
	        TheLoai,
	        ThoiLuong,
	        DaoDien,
	        NgayKhoiChieu
	    )
	    VALUES
	    (   @MaPhim,        -- MaPhim - int
	        @TenPhim,      -- TenPhim - nvarchar(100)
	        @TheLoai,      -- TheLoai - nvarchar(50)
	        @ThoiLuong,        -- ThoiLuong - int
	        @DaoDien,      -- DaoDien - nvarchar(100)
	        @NgayKhoiChieu -- NgayKhoiChieu - date
	        )
	END
END

GO

CREATE PROCEDURE InsertLichChieu(
	@MaLichChieu INT ,
    @MaPhim INT ,
    @NgayChieu DATE ,
    @GioChieu TIME ,
    @PhongChieu NVARCHAR(50)
)
AS
BEGIN
    IF @MaLichChieu IS NULL OR @MaPhim IS NULL OR @NgayChieu IS NULL OR @GioChieu IS NULL OR @PhongChieu IS NULL
    BEGIN
		RAISERROR('Khong dc de trong', 16,1);
		RETURN;
    END

	INSERT INTO dbo.LichChieu
	(
	    MaLichChieu,
	    MaPhim,
	    NgayChieu,
	    GioChieu,
	    PhongChieu
	)
	VALUES
	(   @MaLichChieu,          -- MaLichChieu - int
	    @MaPhim,          -- MaPhim - int
	    @NgayChieu,  -- NgayChieu - date
	    @GioChieu, -- GioChieu - time
	    @PhongChieu         -- PhongChieu - nvarchar(50)
	    )
END

GO

CREATE PROCEDURE InsertVeXemPhim(
	@MaVe INT,
    @MaLichChieu INT ,
    @MaKhachHang INT ,
    @SoGhe NVARCHAR(10) ,
    @GiaVe DECIMAL(10,2),
    @TrangThaiVe NVARCHAR(20)
)
AS
BEGIN
    IF @MaVe IS NULL OR @MaLichChieu IS NULL OR @MaKhachHang IS NULL OR @SoGhe IS NULL OR @GiaVe IS NULL OR @TrangThaiVe IS NULL
    BEGIN
        RAISERROR('Khong dc de trong', 16,1);
		RETURN;
    END

	INSERT INTO dbo.VeXemPhim
	(
	    MaVe,
	    MaLichChieu,
	    MaKhachHang,
	    SoGhe,
	    GiaVe,
	    TrangThaiVe
	)
	VALUES
	(   @MaVe,    -- MaVe - int
	    @MaLichChieu,    -- MaLichChieu - int
	    @MaKhachHang,    -- MaKhachHang - int
	    @SoGhe,  -- SoGhe - nvarchar(10)
	    @GiaVe, -- GiaVe - decimal(10, 2)
	    @TrangThaiVe   -- TrangThaiVe - nvarchar(20)
	    )
END

--Bai3 RETURN
go
CREATE FUNCTION ThongTinGhe(
	@MaKhachHang INT,
	@MaLichChieu int
)
RETURNS VARCHAR(100)
AS
BEGIN
    DECLARE @SoGhe VARCHAR(100)

	IF NOT EXISTS (SELECT 1 FROM dbo.KhachHang WHERE KhachHang.MaKhachHang = @MaKhachHang)
	BEGIN
	    RETURN NULL;
	END

	IF NOT EXISTS (SELECT 1 FROM dbo.LichChieu WHERE LichChieu.MaLichChieu = @MaLichChieu)
	BEGIN
	    RETURN NULL;
	END

	SELECT @SoGhe = vxp.SoGhe FROM dbo.VeXemPhim vxp

WHERE @MaKhachHang = vxp.MaKhachHang AND @MaLichChieu = vxp.MaLichChieu

	RETURN @SoGhe
END

GO
SELECT dbo.ThongTinGhe(1,1) AS SoGhe

--Bai4 - VIEW
GO
CREATE VIEW Top2MuaVe
AS
SELECT TOP 2 kh.MaKhachHang, kh.HoTen, kh.SoDienThoai, kh.Email, COUNT(vxp.MaVe) AS 'SoLuongVeDaMua' FROM dbo.KhachHang kh
JOIN dbo.VeXemPhim vxp ON vxp.MaKhachHang = kh.MaKhachHang
GROUP BY kh.MaKhachHang, kh.HoTen, kh.SoDienThoai, kh.Email
ORDER BY COUNT(vxp.MaVe) DESC

GO
SELECT * FROM dbo.Top2MuaVe

--Bai 5 - TRIGGER
GO
CREATE TRIGGER KiemTraSoVe
ON dbo.VeXemPhim
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @MaVe INT,
    @MaLichChieu INT ,
    @MaKhachHang INT ,
    @SoGhe NVARCHAR(10) ,
    @GiaVe DECIMAL(10,2),
    @TrangThaiVe NVARCHAR(20)
END
SELECT @MaVe = i.MaVe, @MaLichChieu = i.MaLichChieu, @MaKhachHang = i.MaKhachHang, @SoGhe = i.SoGhe, @GiaVe = i.GiaVe, @TrangThaiVe = i.TrangThaiVe FROM Inserted i
IF EXISTS(SELECT 1 FROM dbo.VeXemPhim WHERE @MaLichChieu = VeXemPhim.MaLichChieu AND @SoGhe = VeXemPhim.SoGhe)
BEGIN
    RAISERROR('so ghe da duoc dat',16,1);
	RETURN
END
ELSE
BEGIN
    INSERT INTO dbo.VeXemPhim
    (
        MaVe,
        MaLichChieu,
        MaKhachHang,
        SoGhe,
        GiaVe,
        TrangThaiVe
    )
    VALUES
    (   @MaVe,    -- MaVe - int
        @MaLichChieu,    -- MaLichChieu - int
        @MaKhachHang,    -- MaKhachHang - int
        @SoGhe,  -- SoGhe - nvarchar(10)
        @GiaVe, -- GiaVe - decimal(10, 2)
        @TrangThaiVe   -- TrangThaiVe - nvarchar(20)
        )
END

INSERT INTO dbo.VeXemPhim
(
    MaVe,
    MaLichChieu,
    MaKhachHang,
    SoGhe,
    GiaVe,
    TrangThaiVe
)
VALUES
(   1234,    -- MaVe - int
    1,    -- MaLichChieu - int
    2,    -- MaKhachHang - int
    'A9',  -- SoGhe - nvarchar(10)
    123, -- GiaVe - decimal(10, 2)
    N'dat de'   -- TrangThaiVe - nvarchar(20)
    )
--Bai6 - Stored Procedure TRANSACTION
GO
CREATE PROCEDURE KiemTraDieuKienTruocKhiDatVe(
	@MaVe INT,
    @MaLichChieu INT ,
    @MaKhachHang INT ,
    @SoGhe NVARCHAR(10) ,
    @GiaVe DECIMAL(10,2),
    @TrangThaiVe NVARCHAR(20)
)
AS
BEGIN
BEGIN TRY
    BEGIN TRANSACTION
	IF EXISTS (SELECT 1 FROM dbo.VeXemPhim vxp WHERE @MaLichChieu = vxp.MaLichChieu AND @SoGhe = vxp.SoGhe)
	BEGIN
	    RAISERROR('So ghe da duoc dat', 16,1);
		ROLLBACK TRANSACTION;
		RETURN;
	END
	ELSE
    BEGIN
        INSERT INTO dbo.VeXemPhim
        (
            MaVe,
            MaLichChieu,
            MaKhachHang,
            SoGhe,
            GiaVe,
            TrangThaiVe
        )
        VALUES
        (   @MaVe,    -- MaVe - int
            @MaLichChieu,    -- MaLichChieu - int
            @MaKhachHang,    -- MaKhachHang - int
            @SoGhe,  -- SoGhe - nvarchar(10)
            @GiaVe, -- GiaVe - decimal(10, 2)
            @TrangThaiVe   -- TrangThaiVe - nvarchar(20)
            )
			COMMIT TRANSACTION;
    END
END TRY
BEGIN CATCH
		ROLLBACK TRANSACTION
		DECLARE @error VARCHAR(2000)
		SET @error = ERROR_MESSAGE();
		PRINT 'Loi cua ban la: ' + @error
END CATCH
END

EXECUTE dbo.KiemTraDieuKienTruocKhiDatVe @MaVe = 10,         -- int
                                         @MaLichChieu = 1,  -- int
                                         @MaKhachHang = 2,  -- int
                                         @SoGhe = N'A6',      -- nvarchar(10)
                                         @GiaVe = 123,     -- decimal(10, 2)
                                         @TrangThaiVe = N'da' -- nvarchar(20)
SELECT * FROM dbo.VeXemPhim









