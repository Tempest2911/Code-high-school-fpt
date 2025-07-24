CREATE DATABASE QUANLYGIANGVIEN
GO
USE QUANLYGIANGVIEN
GO
CREATE TABLE GIANGVIEN(
	MAGIANGVIEN INT NOT NULL PRIMARY KEY,
	TENGIANGVIEN VARCHAR(100) NOT NULL,
	BOMON VARCHAR(100),
	EMAIL VARCHAR(100),
	NAMSINH INT NOT NULL
);

CREATE TABLE HOIDONGDATN(
	MAHOIDONG INT NOT NULL PRIMARY KEY,
	TENHOIDONG VARCHAR(100),
	CHUYENNGANH VARCHAR(100) NOT NULL,
	HOCKY VARCHAR(100)
);

CREATE TABLE DANHSACHHOIDONG(
	MAGIANGVIEN INT,
	MAHOIDONG INT,
	PRIMARY KEY(MAGIANGVIEN,MAHOIDONG),
	VAITRO VARCHAR(100) NOT NULL,
	GHICHU VARCHAR(100)
	FOREIGN KEY(MAGIANGVIEN) REFERENCES dbo.GIANGVIEN(MAGIANGVIEN),
	FOREIGN KEY(MAHOIDONG) REFERENCES dbo.HOIDONGDATN(MAHOIDONG)
);

--Bai1
GO
CREATE PROCEDURE INSERTGIANGVIEN(
	@MAGIANGVIEN INT,
	@TENGIANGVIEN VARCHAR(100),
	@BOMON VARCHAR(100),
	@EMAIL VARCHAR(100),
	@NAMSINH INT
	)
AS
BEGIN
    INSERT INTO dbo.GIANGVIEN
    (
        MAGIANGVIEN,
        TENGIANGVIEN,
        BOMON,
        EMAIL,
        NAMSINH
    )
    VALUES
    (   @MAGIANGVIEN,    -- MAGIANGVIEN - int
        @TENGIANGVIEN,   -- TENGIANGVIEN - varchar(100)
        @BOMON, -- BOMON - varchar(100)
        @EMAIL, -- EMAIL - varchar(100)
        @NAMSINH     -- NAMSINH - int
        )
END

EXEC dbo.INSERTGIANGVIEN @MAGIANGVIEN = 1,   -- int
                         @TENGIANGVIEN = 'Phong', -- varchar(100)
                         @BOMON = 'Tin Hoc',        -- varchar(100)
                         @EMAIL = 'Phong@gmail.com',        -- varchar(100)
                         @NAMSINH = 2007        -- int
EXEC dbo.INSERTGIANGVIEN @MAGIANGVIEN = 2,   -- int
                         @TENGIANGVIEN = 'Quyet', -- varchar(100)
                         @BOMON = 'Van Hoc',        -- varchar(100)
                         @EMAIL = 'Quyet@gmail.com',        -- varchar(100)
                         @NAMSINH = 2000        -- int
EXEC dbo.INSERTGIANGVIEN @MAGIANGVIEN = 3,   -- int
                         @TENGIANGVIEN = 'Hung', -- varchar(100)
                         @BOMON = 'Toan Hoc',        -- varchar(100)
                         @EMAIL = 'Hung@gmail.com',        -- varchar(100)
                         @NAMSINH = 1999        -- int

GO
CREATE PROCEDURE INSERTHOIDONG(
	@MAHOIDONG INT,
	@TENHOIDONG VARCHAR(100),
	@CHUYENNGANH VARCHAR(100),
	@HOCKY VARCHAR(100)
	)
AS
BEGIN
    INSERT INTO dbo.HOIDONGDATN
    (
        MAHOIDONG,
        TENHOIDONG,
        CHUYENNGANH,
        HOCKY
    )
    VALUES
    (   @MAHOIDONG,    -- MAHOIDONG - int
        @TENHOIDONG, -- TENHOIDONG - varchar(100)
        @CHUYENNGANH,   -- CHUYENNGANH - varchar(100)
        @HOCKY  -- HOCKY - varchar(100)
        )
END

EXEC dbo.INSERTHOIDONG @MAHOIDONG = 1,    -- int
                       @TENHOIDONG = 'Cham Thi',  -- varchar(100)
                       @CHUYENNGANH = 'TinHoc', -- varchar(100)
                       @HOCKY = '1'        -- varchar(100)

EXEC dbo.INSERTHOIDONG @MAHOIDONG = 2,    -- int
                       @TENHOIDONG = 'Xet Tuyen',  -- varchar(100)
                       @CHUYENNGANH = 'Toan Hoc', -- varchar(100)
                       @HOCKY = '2'        -- varchar(100)

EXEC dbo.INSERTHOIDONG @MAHOIDONG = 3,    -- int
                       @TENHOIDONG = 'Dam nhau',  -- varchar(100)
                       @CHUYENNGANH = 'Van Hoc', -- varchar(100)
                       @HOCKY = '3'        -- varchar(100)

GO
CREATE PROCEDURE INSERDANHSACHHOIDONG(
	@MAGIANGVIEN INT,
	@MAHOIDONG INT,
	@VAITRO VARCHAR(100),
	@GHICHU VARCHAR(100)
	)
AS
BEGIN
	INSERT INTO dbo.DANHSACHHOIDONG
	(
	    MAGIANGVIEN,
	    MAHOIDONG,
	    VAITRO,
	    GHICHU
	)
	VALUES
	(   @MAGIANGVIEN,   -- MAGIANGVIEN - int
	    @MAHOIDONG,   -- MAHOIDONG - int
	    @VAITRO,  -- VAITRO - varchar(100)
	    @GHICHU -- GHICHU - varchar(100)
	    )
END

EXEC dbo.INSERDANHSACHHOIDONG @MAGIANGVIEN = 1, -- int
                              @MAHOIDONG = 1,   -- int
                              @VAITRO = 'NIGGA',     -- varchar(100)
                              @GHICHU = 'LALA'      -- varchar(100)

EXEC dbo.INSERDANHSACHHOIDONG @MAGIANGVIEN = 2, -- int
                              @MAHOIDONG = 2,   -- int
                              @VAITRO = 'FREAKY',     -- varchar(100)
                              @GHICHU = 'LULU'      -- varchar(100)

EXEC dbo.INSERDANHSACHHOIDONG @MAGIANGVIEN = 3, -- int
                              @MAHOIDONG = 3,   -- int
                              @VAITRO = 'ALPHA',     -- varchar(100)
                              @GHICHU = 'LELE'      -- varchar(100)

EXEC dbo.INSERDANHSACHHOIDONG @MAGIANGVIEN = 1, -- int
                              @MAHOIDONG = 2,   -- int
                              @VAITRO = 'SIGMA',     -- varchar(100)
                              @GHICHU = 'LOLO'      -- varchar(100)

GO
--BAI 3
CREATE VIEW V_GIANGVIEN
AS
SELECT DS.MAGIANGVIEN, DS.MAHOIDONG, GV.BOMON, (2024-GV.NAMSINH) AS TUOI FROM dbo.DANHSACHHOIDONG DS
JOIN dbo.GIANGVIEN GV ON GV.MAGIANGVIEN = DS.MAGIANGVIEN 

GO
SELECT * FROM dbo.V_GIANGVIEN
GO
--BAI 4
CREATE VIEW V_TOPCHAMTHI
AS
SELECT TOP 2 DS.MAGIANGVIEN, COUNT(DS.MAHOIDONG) AS SOLANTHAMGIA FROM dbo.DANHSACHHOIDONG DS
GROUP BY DS.MAGIANGVIEN
ORDER BY COUNT(DS.MAHOIDONG) DESC
GO
SELECT * FROM V_TOPCHAMTHI
--BAI5
-- INPUT LA 1 CHUOI KY TU -> OUTPUT : 1 DOAN KY TU TRONG CHUOI
--SUBSTRING(INPUT,VI TRI BAT DAU,7)
--HUONGTTM   SUBSTRING(NAME,1,5)
-- CHARINDEX - TIM VI TRI CUA MOT CHUOI CON TRONG CHUOI LON -> TRA VE VI TRI -- CHARINDEX(KY TU MUON TIM,CHUOI BO,1)
-- NGUYENVV6@GMAIL.COM -> 10
-- HAHQ11@FE.EDU.VN ->7
GO
CREATE FUNCTION DBO.TIMEMAIL(
@EMAIL VARCHAR(50)
)
RETURNS VARCHAR(50)
AS 
BEGIN
	DECLARE @TENTAIKHOAN VARCHAR(20);
	SET @TENTAIKHOAN = SUBSTRING(@EMAIL,1,CHARINDEX('@', @EMAIL));
RETURN @TENTAIKHOAN ;
END









