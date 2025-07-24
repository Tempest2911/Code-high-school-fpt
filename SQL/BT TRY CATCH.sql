CREATE DATABASE ThanhToanHocPhi
GO
USE ThanhToanHocPhi

CREATE TABLE SinhVien (
    MaSinhVien INT PRIMARY KEY,
    HoTen NVARCHAR(100),
    SoDienThoai VARCHAR(15),
    Email VARCHAR(100)
);

CREATE TABLE HocPhi (
    MaHocPhi INT PRIMARY KEY,
    MaSinhVien INT,
    HocKy NVARCHAR(10),
    TongTien DECIMAL(10, 2),
    DaThanhToan DECIMAL(10, 2),
    CONSTRAINT FK_HocPhi_SinhVien FOREIGN KEY (MaSinhVien) REFERENCES SinhVien(MaSinhVien),
    CONSTRAINT CK_DaThanhToan CHECK (DaThanhToan <= TongTien)
);

CREATE TABLE ThanhToan (
    MaThanhToan INT PRIMARY KEY,
    MaHocPhi INT,
    NgayThanhToan DATE,
    SoTien DECIMAL(10, 2),
    CONSTRAINT FK_ThanhToan_HocPhi FOREIGN KEY (MaHocPhi) REFERENCES HocPhi(MaHocPhi)
);

-- Chèn dữ liệu vào bảng SinhVien
INSERT INTO SinhVien (MaSinhVien, HoTen, SoDienThoai, Email) VALUES
(1, 'Nguyen Van A', '0123456789', 'nguyenvana@example.com'),
(2, 'Tran Thi B', '0987654321', 'tranthib@example.com'),
(3, 'Le Van C', '0912345678', 'levanc@example.com');

-- Chèn dữ liệu vào bảng HocPhi
INSERT INTO HocPhi (MaHocPhi, MaSinhVien, HocKy, TongTien, DaThanhToan) VALUES
(1, 1, '2024-1', 10000000, 5000000),
(2, 2, '2024-1', 12000000, 12000000),
(3, 3, '2024-1', 15000000, 10000000);

-- Chèn dữ liệu vào bảng ThanhToan
INSERT INTO ThanhToan (MaThanhToan, MaHocPhi, NgayThanhToan, SoTien) VALUES
(1, 1, '2024-01-15', 2000000),
(2, 1, '2024-02-15', 3000000),
(3, 3, '2024-03-01', 10000000);


--Bai 1
go
CREATE PROCEDURE A1
@MaSinhVien int,
@HoTen  varchar(100),
@SoDienThoai varchar(20),
@Email varchar(100)
AS
BEGIN
    IF @MaSinhVien IS NULL OR @HoTen IS NULL OR @SoDienThoai IS NULL OR @Email IS NULL 
	BEGIN
	    PRINT'Ko dc truyen vao tham so'
	END
		ELSE
    BEGIN
         INSERT INTO dbo.SinhVien
         (
             MaSinhVien,
             HoTen,
             SoDienThoai,
             Email
         )
         VALUES
         (   @MaSinhVien,    -- MaSinhVien - int
             @HoTen, -- HoTen - nvarchar(100)
             @SoDienThoai, -- SoDienThoai - varchar(15)
             @Email  -- Email - varchar(100)
             )
    END
END

CREATE PROCEDURE A2
@MaHocPhi INT,
@MaSinhVien VARCHAR(100),
@HocKy VARCHAR(20),
@TongTien DECIMAL(10, 2),
@DaThanhToan DECIMAL(10, 2)
AS
BEGIN
    IF @MaHocPhi IS NULL OR @MaSinhVien IS NULL OR @HocKy IS NULL OR @TongTien IS NULL OR @DaThanhToan IS NULL
	BEGIN
	    PRINT'Ko dc truyen vao tham so'
	END
		ELSE
    BEGIN
         INSERT INTO dbo.HocPhi
         (
             MaHocPhi,
             MaSinhVien,
             HocKy,
             TongTien,
             DaThanhToan
         )
         VALUES
         (   @MaHocPhi,    -- MaHocPhi - int
             @MaSinhVien, -- MaSinhVien - int
             @HocKy, -- HocKy - nvarchar(10)
             @TongTien, -- TongTien - decimal(10, 2)
             @DaThanhToan  -- DaThanhToan - decimal(10, 2)
             )
    END
END

CREATE PROCEDURE A3
@MaThanhToan  int,
@MaHocPhi   int,
@NgayThanhToan  date,
@SoTien DECIMAL(10,2)
AS
BEGIN
    IF @MaThanhToan IS NULL OR @MaHocPhi IS NULL OR @NgayThanhToan IS NULL OR @SoTien IS NULL 
	BEGIN
	    PRINT'Ko dc truyen vao tham so'
	END
		ELSE
    BEGIN
         INSERT INTO dbo.ThanhToan
         (
             MaThanhToan,
             MaHocPhi,
             NgayThanhToan,
             SoTien
         )
         VALUES
         (   @MaThanhToan,    -- MaThanhToan - int
             @MaHocPhi, -- MaHocPhi - int
             @NgayThanhToan, -- NgayThanhToan - date
             @SoTien  -- SoTien - decimal(10, 2)
             )
    END
END

GO


--Bai2
               
CREATE PROCEDURE GetThongTinThanhToan
    @MaSinhVien INT
AS
BEGIN
    BEGIN TRY
        SELECT sv.MaSinhVien, sv.HoTen, hp.HocKy, tt.SoTien
        FROM SinhVien sv
        JOIN HocPhi hp ON sv.MaSinhVien = hp.MaSinhVien
        JOIN ThanhToan tt ON hp.MaHocPhi = tt.MaHocPhi
        WHERE sv.MaSinhVien = @MaSinhVien;
    END TRY
    BEGIN CATCH
        PRINT 'Lỗi: Không thể lấy thông tin thanh toán';
    END CATCH
END;

EXEC dbo.GetThongTinThanhToan @MaSinhVien =  -- int


go;

--Bai3
CREATE PROCEDURE InsertThanhToan
    @MaHocPhi INT,
    @NgayThanhToan DATE,
    @SoTien DECIMAL(10, 2)
AS
BEGIN
    DECLARE @TongTien DECIMAL(10, 2);

    BEGIN TRANSACTION;

    BEGIN TRY
        SELECT @TongTien = TongTien FROM HocPhi WHERE MaHocPhi = @MaHocPhi;

        IF @SoTien > @TongTien
        BEGIN
            PRINT 'So tien ko dc lon hon tong tien';
            ROLLBACK TRANSACTION;
            RETURN;
        END

        INSERT INTO ThanhToan (MaHocPhi, NgayThanhToan, SoTien)
        VALUES (@MaHocPhi, @NgayThanhToan, @SoTien);

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        PRINT 'Ko the insert vao bang';
    END CATCH
END;

DROP PROCEDURE InsertThanhToan


GO;

EXECUTE InsertThanhToan @MaHocPhi = 1, @NgayThanhToan = '2024-10-03', @SoTien = 123456;


SELECT * FROM dbo.ThanhToan


						




	


