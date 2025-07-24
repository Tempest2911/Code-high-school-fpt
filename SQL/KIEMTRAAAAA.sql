CREATE DATABASE QUANLYNGANHANG
GO
USE QUANLYNGANHANG
GO

CREATE TABLE KHACHHANG(
	MAKHACHHANG INT PRIMARY KEY IDENTITY(1,1),
	HOTEN VARCHAR(50),
	SODIENTHOAI VARCHAR(20) NOT NULL,
	DIACHI VARCHAR(100),
	CMND VARCHAR(100),
	NGAYSINH DATE,
	QUOCTICH VARCHAR(100)
);

CREATE TABLE TAIKHOANNGANHANG(
	SOTAIKHOAN INT PRIMARY KEY IDENTITY(1,1),
	MAKHACHHANG INT,
	SODU INT NOT NULL CHECK(SODU >0),
	LOAITAIKHOAN VARCHAR(100) NOT NULL,
	TRANGTHAI VARCHAR(100)
	FOREIGN KEY(MAKHACHHANG) REFERENCES dbo.KHACHHANG(MAKHACHHANG)
);

CREATE TABLE GIAODICH(
	MAGIAODICH INT IDENTITY(1,1),
	SOTAIKHOAN INT,
	NGAYGIAODICH DATE NOT NULL,
	LOAIGIAODICH VARCHAR(100) NOT NULL,
	SOTIEN INT NOT NULL,
	MOTA VARCHAR(100),
	FOREIGN KEY(SOTAIKHOAN) REFERENCES dbo.TAIKHOANNGANHANG(SOTAIKHOAN)
);

CREATE TABLE THETINDUNG(
	SOTHE INT IDENTITY(1,1),
	 MAKHACHHANG INT,
	 HANMUC INT NOT NULL CHECK(HANMUC>0),
	 SODUNO INT NOT NULL,
	 TRANGTHAITHE VARCHAR(100),
	 FOREIGN KEY(MAKHACHHANG) REFERENCES dbo.KHACHHANG(MAKHACHHANG)
);

ALTER TABLE dbo.THETINDUNG
ADD CHECK(THETINDUNG.HANMUC > THETINDUNG.SODUNO)

--Bai1 STORE PRODUCE
GO
CREATE PROCEDURE insertKhachHang(
	@MAKHACHHANG INT ,
	@HOTEN VARCHAR(50),
	@SODIENTHOAI VARCHAR(20),
	@DIACHI VARCHAR(100),
	@CMND VARCHAR(100),
	@NGAYSINH DATE,
	@QUOCTICH VARCHAR(100)
)
AS 
BEGIN
    IF @MaKhachHang IS NULL OR @HoTen IS NULL OR @SoDienThoai IS NULL OR @DIACHI IS NULL OR @CMND IS NULL OR @NgaySinh IS NULL OR @QUOCTICH IS NULL
    BEGIN
        RAISERROR('Khong dc de trong', 16,1);
		RETURN;
    END
    IF EXISTS(SELECT 1 FROM dbo.KHACHHANG WHERE @MAKHACHHANG = KHACHHANG.MAKHACHHANG)
    BEGIN
        RAISERROR('Ma khach hang da ton tai', 16,1);
		RETURN;
    END
       
	INSERT INTO dbo.KHACHHANG
	(
	    HOTEN,
	    SODIENTHOAI,
	    DIACHI,
	    CMND,
	    NGAYSINH,
	    QUOCTICH
	)
	VALUES
	(   @HOTEN, -- HOTEN - varchar(50)
	    @SODIENTHOAI,   -- SODIENTHOAI - varchar(20)
	    @DIACHI, -- DIACHI - varchar(100)
	    @CMND, -- CMND - varchar(100)
	    @NGAYSINH, -- NGAYSINH - date
	    @QUOCTICH  -- QUOCTICH - varchar(100)
	    )
END

EXEC dbo.insertKhachHang @MAKHACHHANG = 1,         -- int
                         @HOTEN = 'Phong',              -- varchar(50)
                         @SODIENTHOAI = '098824747',        -- varchar(20)
                         @DIACHI = 'HANOI',             -- varchar(100)
                         @CMND = '0182874848274',               -- varchar(100)
                         @NGAYSINH = '2024-10-11', -- date
                         @QUOCTICH = 'VN'            -- varchar(100)

						 EXEC dbo.insertKhachHang @MAKHACHHANG = 2,         -- int
                         @HOTEN = 'Hung',              -- varchar(50)
                         @SODIENTHOAI = '01747427842',        -- varchar(20)
                         @DIACHI = 'China',             -- varchar(100)
                         @CMND = '01832894724',               -- varchar(100)
                         @NGAYSINH = '2024-6-4', -- date
                         @QUOCTICH = 'Chau phi'            -- varchar(100)

						 EXEC dbo.insertKhachHang @MAKHACHHANG = 3,         -- int
                         @HOTEN = 'Quyet',              -- varchar(50)
                         @SODIENTHOAI = '0234324342',        -- varchar(20)
                         @DIACHI = 'CHINCHOGN',             -- varchar(100)
                         @CMND = '0234283532432',               -- varchar(100)
                         @NGAYSINH = '2024-12-5', -- date
                         @QUOCTICH = 'USSA'            -- varchar(100)

GO

CREATE PROCEDURE insertTaiKhoanNganHang(
	@SOTAIKHOAN INT ,
	@MAKHACHHANG INT,
	@SODU INT,
	@LOAITAIKHOAN VARCHAR(100),
	@TRANGTHAI VARCHAR(100)
)
AS 
BEGIN
    IF @SOTAIKHOAN IS NULL OR @MAKHACHHANG IS NULL OR @SODU IS NULL OR @LOAITAIKHOAN IS NULL OR @TRANGTHAI IS NULL
    BEGIN
        RAISERROR('Khong dc de trong', 16,1);
		RETURN;
    END
    IF EXISTS(SELECT 1 FROM dbo.TAIKHOANNGANHANG WHERE @SOTAIKHOAN = TAIKHOANNGANHANG.SOTAIKHOAN)
    BEGIN
        RAISERROR('So tai khoan da ton tai', 16,1);
		RETURN;
    END
       
	INSERT INTO dbo.TAIKHOANNGANHANG
	(
	    MAKHACHHANG,
	    SODU,
	    LOAITAIKHOAN,
	    TRANGTHAI
	)
	VALUES
	(   @MAKHACHHANG, -- MAKHACHHANG - int
	    @SODU,    -- SODU - int
	    @LOAITAIKHOAN,   -- LOAITAIKHOAN - varchar(100)
	    @TRANGTHAI  -- TRANGTHAI - varchar(100)
	    )
END

EXEC dbo.insertTaiKhoanNganHang @SOTAIKHOAN = 1,    -- int
                                @MAKHACHHANG = 1,   -- int
                                @SODU = 500000,          -- int
                                @LOAITAIKHOAN = 'Tiet Kiem', -- varchar(100)
                                @TRANGTHAI = 'Kich hoat'     -- varchar(100)

EXEC dbo.insertTaiKhoanNganHang @SOTAIKHOAN = 2,    -- int
                                @MAKHACHHANG = 2,   -- int
                                @SODU = 1000000,          -- int
                                @LOAITAIKHOAN = 'Thanh toan', -- varchar(100)
                                @TRANGTHAI = 'Dong'     -- varchar(100)

EXEC dbo.insertTaiKhoanNganHang @SOTAIKHOAN = 3,    -- int
                                @MAKHACHHANG = 3,   -- int
                                @SODU = 10000,          -- int
                                @LOAITAIKHOAN = 'Tiet Kiem', -- varchar(100)
                                @TRANGTHAI = 'Dong'     -- varchar(100)

GO

CREATE PROCEDURE insertGiaoDich(
	@MAGIAODICH INT,
	@SOTAIKHOAN INT,
	@NGAYGIAODICH DATE,
	@LOAIGIAODICH VARCHAR(100),
	@SOTIEN INT,
	@MOTA VARCHAR(100)
)
AS 
BEGIN
    IF @MAGIAODICH IS NULL OR @SOTAIKHOAN IS NULL OR @NGAYGIAODICH IS NULL OR @LOAIGIAODICH IS NULL OR @SOTIEN IS NULL OR @MOTA IS NULL
    BEGIN
        RAISERROR('Khong dc de trong', 16,1);
		RETURN;
    END
	IF EXISTS(SELECT 1 FROM dbo.GIAODICH WHERE @MAGIAODICH = @MAGIAODICH)
    BEGIN
        RAISERROR('Ma giao dich da ton tai', 16,1);
		RETURN;
    END
       
	INSERT INTO dbo.GIAODICH
	(
	    SOTAIKHOAN,
	    NGAYGIAODICH,
	    LOAIGIAODICH,
	    SOTIEN,
	    MOTA
	)
	VALUES
	(   @SOTAIKHOAN,      -- SOTAIKHOAN - int
	    @NGAYGIAODICH, -- NGAYGIAODICH - date
	    @LOAIGIAODICH,        -- LOAIGIAODICH - varchar(100)
	    @SOTIEN,         -- SOTIEN - int
	    @MOTA       -- MOTA - varchar(100)
	    )
END

EXECUTE dbo.insertGiaoDich @MAGIAODICH = 1,              -- int
                           @SOTAIKHOAN = 1,              -- int
                           @NGAYGIAODICH = '2024-10-11', -- date
                           @LOAIGIAODICH = 'Nap tien',           -- varchar(100)
                           @SOTIEN = 100,                  -- int
                           @MOTA = 'hello'                    -- varchar(100)
EXECUTE dbo.insertGiaoDich @MAGIAODICH = 2,              -- int
                           @SOTAIKHOAN = 2,              -- int
                           @NGAYGIAODICH = '2024-9-9', -- date
                           @LOAIGIAODICH = 'Rut tien',           -- varchar(100)
                           @SOTIEN = 10000,                  -- int
                           @MOTA = 'nigga'                    -- varchar(100)
EXECUTE dbo.insertGiaoDich @MAGIAODICH = 3,              -- int
                           @SOTAIKHOAN = 3,              -- int
                           @NGAYGIAODICH = '2024-3-5', -- date
                           @LOAIGIAODICH = 'Chuyen khoan',           -- varchar(100)
                           @SOTIEN = 10,                  -- int
                           @MOTA = 'sigma'                    -- varchar(100)

GO

CREATE PROCEDURE insertTheTinDung(
	 @SOTHE INT,
	 @MAKHACHHANG INT,
	 @HANMUC INT ,
	 @SODUNO INT,
	 @TRANGTHAITHE VARCHAR(100)
)
AS 
BEGIN
    IF @SOTHE IS NULL OR @MAKHACHHANG IS NULL OR @HANMUC IS NULL OR @SODUNO IS NULL OR @TRANGTHAITHE IS NULL
    BEGIN
        RAISERROR('Khong dc de trong', 16,1);
		RETURN;
    END
    IF EXISTS(SELECT 1 FROM dbo.THETINDUNG WHERE @SOTHE = THETINDUNG.SOTHE)
    BEGIN
        RAISERROR('So the da ton tai', 16,1);
		RETURN;
    END
	IF (@SODUNO > @HANMUC)
	BEGIN
	    RAISERROR('So du no ko dc qua han muc', 16,1);
		RETURN;
	END
       
	INSERT INTO dbo.THETINDUNG
	(
	    MAKHACHHANG,
	    HANMUC,
	    SODUNO,
	    TRANGTHAITHE
	)
	VALUES
	(   @MAKHACHHANG, -- MAKHACHHANG - int
	    @HANMUC,    -- HANMUC - int
	    @SOTHE,    -- SODUNO - int
	    @TRANGTHAITHE  -- TRANGTHAITHE - varchar(100)
	    )
END

EXECUTE dbo.insertTheTinDung @SOTHE = 1,        -- int
                             @MAKHACHHANG = 1,  -- int
                             @HANMUC = 5000000,       -- int
                             @SODUNO = 100000,       -- int
                             @TRANGTHAITHE = 'Kich hoat' -- varchar(100)

EXECUTE dbo.insertTheTinDung @SOTHE = 2,        -- int
                             @MAKHACHHANG = 2,  -- int
                             @HANMUC = 10000000,       -- int
                             @SODUNO = 19249,       -- int
                             @TRANGTHAITHE = 'Khoa' -- varchar(100)


EXECUTE dbo.insertTheTinDung @SOTHE = 3,        -- int
                             @MAKHACHHANG = 3,  -- int
                             @HANMUC = 4000000,       -- int
                             @SODUNO = 30000,       -- int
                             @TRANGTHAITHE = 'Het han' -- varchar(100)

--Bai 3
GO

CREATE FUNCTION TinhTongSoTien(
	@MaGiaoDich INT,
	@SoTaiKhoan INT
)
RETURNS DECIMAL(10,2)
AS
BEGIN
    DECLARE @TongSoTien DECIMAL(10,2);

	

	IF NOT EXISTS (SELECT gd.MAGIAODICH, gd.SOTAIKHOAN FROM dbo.GIAODICH gd WHERE @MaGiaoDich = gd.MAGIAODICH AND @SoTaiKhoan = gd.SOTAIKHOAN)
	BEGIN
	    RETURN 0;
	END
	
	SELECT @TongSoTien = SUM(gd.SOTIEN) FROM dbo.GIAODICH gd
	WHERE @MaGiaoDich = gd.MAGIAODICH AND @SoTaiKhoan = gd.SOTAIKHOAN

	RETURN @TongSoTien
END

GO
DROP FUNCTION dbo.TinhTongSoTien
SELECT * FROM dbo.GIAODICH


SELECT dbo.TinhTongSoTien(1,1) AS TongSoTien

--Bai4 - VIEW
GO
CREATE VIEW Top2KhachHang
AS
SELECT TOP 2 tknh.MAKHACHHANG, kh.HOTEN, tknh.SOTAIKHOAN, tknh.SODU FROM dbo.TAIKHOANNGANHANG tknh
JOIN dbo.KHACHHANG kh ON kh.MAKHACHHANG = tknh.MAKHACHHANG
ORDER BY tknh.SODU DESC

GO
SELECT * FROM dbo.Top2KhachHang

--Bai5

--Bai5 - Stored Procedure TRANSACTION
GO
CREATE PROCEDURE KiemTraDieuKienGiaoDich(
	@MAGIAODICH INT,
	@SOTAIKHOAN INT,
	@NGAYGIAODICH DATE,
	@LOAIGIAODICH VARCHAR(100),
	@SOTIEN INT,
	@MOTA VARCHAR(100)
)
AS
BEGIN
BEGIN TRY
    BEGIN TRANSACTION
	DECLARE @SoDu DECIMAL(10,2);

	SELECT @MAGIAODICH = gd.MAGIAODICH, @SOTAIKHOAN = gd.SOTAIKHOAN, @NGAYGIAODICH = gd.NGAYGIAODICH, @LOAIGIAODICH = gd.LOAIGIAODICH, @SOTIEN = gd.SOTIEN, @MOTA = gd.MOTA FROM dbo.GIAODICH gd

	IF EXISTS (SELECT tknh.SODU FROM dbo.TAIKHOANNGANHANG tknh WHERE tknh.SODU>0)
	BEGIN
	    RAISERROR('So du tai khoan phai du de chuyen khoan', 16,1);
		ROLLBACK TRANSACTION;
		RETURN;
	END

	IF EXISTS (SELECT gd.SOTIEN FROM dbo.GIAODICH gd WHERE @SOTIEN<10000)
	BEGIN
	    RAISERROR('So tien chuyen toi thieu la 10.000', 16,1);
		ROLLBACK TRANSACTION;
		RETURN;
	END

	IF EXISTS (SELECT  FROM dbo.GIAODICH gd JOIN dbo.TAIKHOANNGANHANG tknh ON tknh.SOTAIKHOAN = gd.SOTAIKHOAN )
	BEGIN
	    RAISERROR('So du tai khoan phai con lai it nhat 50.000', 16,1);
		ROLLBACK TRANSACTION;
		RETURN;
	END

        INSERT INTO dbo.GIAODICH
        (
            SOTAIKHOAN,
            NGAYGIAODICH,
            LOAIGIAODICH,
            SOTIEN,
            MOTA
        )
        VALUES
        (   @SOTAIKHOAN,      -- SOTAIKHOAN - int
            @NGAYGIAODICH, -- NGAYGIAODICH - date
            @LOAIGIAODICH,        -- LOAIGIAODICH - varchar(100)
            @SOTIEN,         -- SOTIEN - int
            @MOTA       -- MOTA - varchar(100)
            )
			COMMIT TRANSACTION;

END TRY
BEGIN CATCH
		ROLLBACK TRANSACTION
		DECLARE @error VARCHAR(2000)
		SET @error = ERROR_MESSAGE();
		PRINT 'Loi cua ban la: ' + @error
END CATCH
END



