-- Tạo cơ sở dữ liệu FINAL_SOF203
CREATE DATABASE FINAL_SOF203;
GO

-- Sử dụng cơ sở dữ liệu FINAL_SOF203
USE FINAL_SOF203;
GO

-- Tạo bảng Động vật
CREATE TABLE DongVat (
    Ma INT PRIMARY KEY,
    Ten NVARCHAR(50),
    TrangThai NVARCHAR(50),
    ThucAn INT
);

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

-- Tạo bảng Đơn hàng
CREATE TABLE DonHang (
    Ma INT PRIMARY KEY,
    Ten NVARCHAR(50),
    TrangThai NVARCHAR(50),
    SoLuong INT
);

-- Tạo bảng Nhân viên
CREATE TABLE NhanVien (
    Ma INT PRIMARY KEY,
    Ten NVARCHAR(50),
    TrangThai NVARCHAR(50),
    Luong INT
);

-- Tạo bảng Phòng ban
CREATE TABLE PhongBan (
    Ma INT PRIMARY KEY,
    Ten NVARCHAR(50),
    TrangThai NVARCHAR(50),
    SoNhanVien INT
);

-- Chèn 10 bản ghi vào bảng Động vật
INSERT INTO DongVat (Ma, Ten, TrangThai, ThucAn) VALUES
(1, N'Chó', N'Hoạt động', 5),
(2, N'Mèo', N'Ngủ', 3),
(3, N'Hổ', N'Săn mồi', 7),
(4, N'Vịt', N'Ăn', 2),
(5, N'Ngựa', N'Chạy', 4),
(6, N'Lợn', N'Ăn', 8),
(7, N'Khỉ', N'Nghịch ngợm', 6),
(8, N'Cá', N'Bơi', 1),
(9, N'Gà', N'Kiếm ăn', 3),
(10, N'Dê', N'Ăn cỏ', 2);

-- Chèn dữ liệu vào bảng Sản phẩm
INSERT INTO SanPham (Ma, Ten, TrangThai, GiaBan) VALUES
(1, N'Tivi', N'Có sẵn', 5000),
(2, N'Tủ lạnh', N'Hết hàng', 8000),
(3, N'Máy giặt', N'Có sẵn', 7000),
(4, N'Lò vi sóng', N'Ngừng sản xuất', 3000),
(5, N'Diều hòa', N'Có sẵn', 12000),
(6, N'Máy tính', N'Hết hàng', 15000),
(7, N'Điện thoại', N'Có sẵn', 20000),
(8, N'Tai nghe', N'Có sẵn', 1000),
(9, N'Máy ảnh', N'Hết hàng', 18000),
(10, N'Quạt điện', N'Có sẵn', 2000);

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

-- Chèn dữ liệu vào bảng Đơn hàng
INSERT INTO DonHang (Ma, Ten, TrangThai, SoLuong) VALUES
(1, N'Đơn hàng #001', N'Đã giao', 5),
(2, N'Đơn hàng #002', N'Đang giao', 10),
(3, N'Đơn hàng #003', N'Hủy', 2),
(4, N'Đơn hàng #004', N'Đang xử lý', 8),
(5, N'Đơn hàng #005', N'Đã giao', 6),
(6, N'Đơn hàng #006', N'Hủy', 1),
(7, N'Đơn hàng #007', N'Đang xử lý', 12),
(8, N'Đơn hàng #008', N'Đã giao', 15),
(9, N'Đơn hàng #009', N'Hủy', 3),
(10, N'Đơn hàng #010', N'Đang xử lý', 7);

-- Chèn dữ liệu vào bảng Nhân viên
INSERT INTO NhanVien (Ma, Ten, TrangThai, Luong) VALUES
(1, N'Nguyễn Văn A', N'Làm việc', 15000),
(2, N'Trần Thị B', N'Nghỉ việc', 12000),
(3, N'Lê Văn C', N'Làm việc', 18000),
(4, N'Hoàng Văn D', N'Làm việc', 20000),
(5, N'Phạm Thị E', N'Nghỉ việc', 11000),
(6, N'Ngô Văn F', N'Làm việc', 17000),
(7, N'Vũ Thị G', N'Làm việc', 14000),
(8, N'Bùi Văn H', N'Nghỉ việc', 13000),
(9, N'Phan Văn I', N'Làm việc', 16000),
(10, N'Doãn Thị J', N'Làm việc', 19000);

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
