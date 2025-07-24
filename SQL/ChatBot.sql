CREATE DATABASE ChatBotDB
USE ChatBotDB
CREATE TABLE ChatHistory (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_input NVARCHAR(MAX),
    bot_response NVARCHAR(MAX),
    created_at DATETIME DEFAULT GETDATE()
);

SELECT * FROM dbo.ChatHistory

DROP TABLE dbo.ChatHistory
