CREATE DATABASE NhanVienChungNhan;

USE NhanVienChungNhan;

CREATE TABLE NhanVien (
    MaNhanVien INT PRIMARY KEY,
    TenNhanVien NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NOT NULL,
    SoDienThoai NVARCHAR(15) NOT NULL
);

CREATE TABLE ChungNhan (
    MaChungNhan INT PRIMARY KEY,
    TenChungNhan NVARCHAR(100) NOT NULL,
    ThoiHan DATE NOT NULL, -- s? n?m
    CoQuanCap NVARCHAR(100) NOT NULL
);


CREATE TABLE LichSuChungNhan (
    MaNhanVien INT,
    MaChungNhan INT,
    NgayCap DATE NOT NULL,
    GhiChu NVARCHAR(255),
    PRIMARY KEY (MaNhanVien, MaChungNhan),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
    FOREIGN KEY (MaChungNhan) REFERENCES ChungNhan(MaChungNhan)
);

DROP TABLE dbo.LichSuChungNhan

--Bai 1
GO
CREATE PROCEDURE InsertNhanVien(
	@MaNhanVien INT,
    @TenNhanVien NVARCHAR(100),
    @Email NVARCHAR(100),
    @SoDienThoai NVARCHAR(15)
)
AS
BEGIN
    INSERT INTO dbo.NhanVien
    (
        MaNhanVien,
        TenNhanVien,
        Email,
        SoDienThoai
    )
    VALUES
    (   @MaNhanVien,   -- MaNhanVien - int
        @TenNhanVien, -- TenNhanVien - nvarchar(100)
        @Email, -- Email - nvarchar(100)
        @SoDienThoai  -- SoDienThoai - nvarchar(15)
        )
END
GO
EXEC dbo.InsertNhanVien @MaNhanVien = 1,    -- int
                        @TenNhanVien = N'Phong', -- nvarchar(100)
                        @Email = N'Phong@gmail.com',       -- nvarchar(100)
                        @SoDienThoai = N'0988958930'  -- nvarchar(15)

EXEC dbo.InsertNhanVien @MaNhanVien = 2,    -- int
                        @TenNhanVien = N'Quyet', -- nvarchar(100)
                        @Email = N'Quyet@gmail.com',       -- nvarchar(100)
                        @SoDienThoai = N'0824278424'  -- nvarchar(15)

EXEC dbo.InsertNhanVien @MaNhanVien = 3,    -- int
                        @TenNhanVien = N'Hung', -- nvarchar(100)
                        @Email = N'Hung@gmail.com',       -- nvarchar(100)
                        @SoDienThoai = N'0937427644'  -- nvarchar(15)
GO

CREATE PROCEDURE InsertChungNhan(
	 @MaChungNhan INT,
    @TenChungNhan NVARCHAR(100),
    @ThoiHan DATE, -- s? n?m
    @CoQuanCap NVARCHAR(100)
)
AS
BEGIN
    INSERT INTO dbo.ChungNhan
    (
        MaChungNhan,
        TenChungNhan,
        ThoiHan,
        CoQuanCap
    )
    VALUES
    (   @MaChungNhan,   -- MaChungNhan - int
        @TenChungNhan, -- TenChungNhan - nvarchar(100)
        @ThoiHan,   -- ThoiHan - int
        @CoQuanCap  -- CoQuanCap - nvarchar(100)
        )
END
GO


EXECUTE dbo.InsertChungNhan @MaChungNhan = 1,    -- int
                            @TenChungNhan = N'SIGMA', -- nvarchar(100)
                            @ThoiHan = '9999-9-9',        -- int
                            @CoQuanCap = N'SKIBIDI'     -- nvarchar(100)

EXECUTE dbo.InsertChungNhan @MaChungNhan = 2,    -- int
                            @TenChungNhan = N'GYAT', -- nvarchar(100)
                            @ThoiHan = '2050-5-7',        -- int
                            @CoQuanCap = N'BINH'     -- nvarchar(100)

EXECUTE dbo.InsertChungNhan @MaChungNhan = 3,    -- int
                            @TenChungNhan = N'WUKONG', -- nvarchar(100)
                            @ThoiHan = '2040-8-4',        -- int
                            @CoQuanCap = N'PICOLO'     -- nvarchar(100)

GO
CREATE PROCEDURE InsertLichSuChungNhan(
	@MaNhanVien INT,
    @MaChungNhan INT,
    @NgayCap DATE,
    @GhiChu NVARCHAR(255)
)
AS
BEGIN
    INSERT INTO dbo.LichSuChungNhan
    (
        MaNhanVien,
        MaChungNhan,
        NgayCap,
        GhiChu
    )
    VALUES
    (   @MaNhanVien,         -- MaNhanVien - int
        @MaChungNhan,         -- MaChungNhan - int
        @NgayCap, -- NgayCap - date
        @GhiChu       -- GhiChu - nvarchar(255)
        )
END
GO
EXECUTE dbo.InsertLichSuChungNhan @MaNhanVien = 1,         -- int
                                  @MaChungNhan = 1,        -- int
                                  @NgayCap = '2024-10-16', -- date
                                  @GhiChu = N'MMB'            -- nvarchar(255)

EXECUTE dbo.InsertLichSuChungNhan @MaNhanVien = 2,         -- int
                                  @MaChungNhan = 2,        -- int
                                  @NgayCap = '2024-10-16', -- date
                                  @GhiChu = N'CMG'            -- nvarchar(255)

EXECUTE dbo.InsertLichSuChungNhan @MaNhanVien = 3,         -- int
                                  @MaChungNhan = 3,        -- int
                                  @NgayCap = '2024-10-16', -- date
                                  @GhiChu = N'CCC'            -- nvarchar(255)

EXECUTE dbo.InsertLichSuChungNhan @MaNhanVien = 1,         -- int
                                  @MaChungNhan = 2,        -- int
                                  @NgayCap = '2024-10-16', -- date
                                  @GhiChu = N'LALALA'            -- nvarchar(255)

--Bai 2
GO
CREATE VIEW V_ChungNhan
AS
SELECT cn.MaChungNhan, cn.TenChungNhan, cn.CoQuanCap, (YEAR(cn.ThoiHan) - YEAR(ls.NgayCap)) AS ThoiGianHieuLuc FROM dbo.ChungNhan cn
JOIN dbo.LichSuChungNhan ls ON ls.MaChungNhan = cn.MaChungNhan 
GO
SELECT * FROM dbo.V_ChungNhan
--Bai 3
GO
CREATE VIEW V_TopNhanVien
AS
SELECT ls.MaNhanVien, COUNT(ls.MaChungNhan) AS SoChungNhan FROM dbo.LichSuChungNhan ls
GROUP BY ls.MaNhanVien
GO
SELECT * FROM dbo.V_TopNhanVien

--Bai 4
GO
CREATE FUNCTION TraVeNgayHetHan(
	@SoNam INT
)
RETURNS DATE
as
BEGIN
    DECLARE @NgayHetHan DATE;
	SELECT @NgayHetHan =  DATEADD(YEAR, @SoNam, GETDATE()) FROM dbo.ChungNhan 

	RETURN @NgayHetHan
END

GO
SELECT dbo.TraVeNgayHetHan(1) AS 'Ngay Het Han'
go
--Bai 5
GO
CREATE PROCEDURE XoaChungNhan(
	@MaNhanVien INT
)
AS
BEGIN
BEGIN try
    BEGIN TRANSACTION
	IF EXISTS(SELECT ls.MaNhanVien FROM dbo.LichSuChungNhan ls WHERE @MaNhanVien = ls.MaNhanVien)
	BEGIN
	    DELETE FROM dbo.LichSuChungNhan 
		WHERE @MaNhanVien = MaNhanVien
		COMMIT TRANSACTION
	END

END TRY
BEGIN CATCH
        ROLLBACK TRANSACTION
		DECLARE @error VARCHAR(2000)
		SET @error = ERROR_MESSAGE();
		PRINT 'Loi cua ban la: ' + @error
END CATCH
END

DROP PROCEDURE dbo.XoaChungNhan
GO
EXEC dbo.XoaChungNhan @MaNhanVien = 3 -- int
GO
--Bai 6
CREATE FUNCTION InRaEmail(
	@Email VARCHAR(100)
)
RETURNS VARCHAR(100)
AS
BEGIN
	SET @Email = SUBSTRING(@Email,CHARINDEX('@', @Email),LEN(@Email))
	RETURN @Email
END
DROP FUNCTION dbo.InRaEmail
GO
SELECT dbo.InRaEmail('aaaa@gmail.com') AS DuoiGmail

--Bai 7
GO
CREATE VIEW V_NhanVienMoi
AS
SELECT ls.MaNhanVien, ls.MaChungNhan, ls.NgayCap, ls.GhiChu FROM dbo.LichSuChungNhan ls
WHERE YEAR(ls.NgayCap) = 2024
GO
SELECT * FROM dbo.V_NhanVienMoi






