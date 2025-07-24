-- Tạo cơ sở dữ liệu FINAL_SOF203
CREATE DATABASE FINAL_SOF203_FA24;
GO

-- Sử dụng cơ sở dữ liệu FINAL_SOF203
USE FINAL_SOF203_FA24;
GO


-- Tạo bảng Sản phẩm
CREATE TABLE SanPham (
    Ma INT PRIMARY KEY,
    Ten NVARCHAR(50),
    TrangThai NVARCHAR(50),
    GiaBan INT
);

-- Tạo bảng Khách hàng
CREATE TABLE KhachHang (
    Ma INT PRIMARY KEY,
    Ten NVARCHAR(50),
    TrangThai NVARCHAR(50),
    DoTuoi INT
);


-- Tạo bảng Phòng ban
CREATE TABLE PhongBan (
    Ma INT PRIMARY KEY,
    Ten NVARCHAR(50),
    TrangThai NVARCHAR(50),
    SoNhanVien INT
);

go

-- Chèn dữ liệu vào bảng Sản phẩm
INSERT INTO SanPham (Ma, Ten, TrangThai, GiaBan) VALUES
(1, N'Tivi', N'Còn hàng', 5000),
(2, N'Tủ lạnh', N'Hết hàng', 8000),
(3, N'Máy giặt', N'Còn hàng', 7000),
(4, N'Lò vi sóng', N'Còn hàng', 3000),
(5, N'Diều hòa', N'Còn hàng', 12000),
(6, N'Máy tính', N'Hết hàng', 15000),
(7, N'Điện thoại', N'Còn hàng', 20000),
(8, N'Tai nghe', N'Còn hàng', 1000),
(9, N'Máy ảnh', N'Hết hàng', 18000),
(10, N'Quạt điện', N'Còn hàng', 2000);

-- Chèn dữ liệu vào bảng Khách hàng
INSERT INTO KhachHang (Ma, Ten, TrangThai, DoTuoi) VALUES
(1, N'Nguyễn Văn A', N'Hoạt động', 25),
(2, N'Trần Thị B', N'Đã khóa', 30),
(3, N'Lê Văn C', N'Hoạt động', 35),
(4, N'Hoàng Văn D', N'Hoạt động', 28),
(5, N'Phạm Thị E', N'Đã khóa', 40),
(6, N'Ngô Văn F', N'Hoạt động', 45),
(7, N'Vũ Thị G', N'Hoạt động', 50),
(8, N'Bùi Văn H', N'Hoạt động', 23),
(9, N'Phan Văn I', N'Đã khóa', 27),
(10, N'Doãn Thị J', N'Hoạt động', 31);



-- Chèn dữ liệu vào bảng Phòng ban
INSERT INTO PhongBan (Ma, Ten, TrangThai, SoNhanVien) VALUES
(1, N'Phòng Kỹ thuật', N'Hoạt động', 15),
(2, N'Phòng Kinh doanh', N'Hoạt động', 20),
(3, N'Phòng Nhân sự', N'Đã giải tán', 0),
(4, N'Phòng Marketing', N'Hoạt động', 10),
(5, N'Phòng Sản xuất', N'Hoạt động', 25),
(6, N'Phòng Bảo trì', N'Hoạt động', 8),
(7, N'Phòng Kế toán', N'Hoạt động', 12),
(8, N'Phòng Thiết kế', N'Hoạt động', 5),
(9, N'Phòng IT', N'Hoạt động', 18),
(10, N'Phòng Hậu cần', N'Hoạt động', 22);

GO
