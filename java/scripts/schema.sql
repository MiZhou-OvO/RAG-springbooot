IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='chat_session' AND xtype='U')
CREATE TABLE chat_session (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    title NVARCHAR(255) DEFAULT N'',
    create_time DATETIME2 DEFAULT GETDATE(),
    update_time DATETIME2 DEFAULT GETDATE()
);

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='chat_message' AND xtype='U')
CREATE TABLE chat_message (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    session_id BIGINT NOT NULL,
    role NVARCHAR(20) NOT NULL,
    content NVARCHAR(MAX),
    create_time DATETIME2 DEFAULT GETDATE()
);
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name='idx_session_id')
CREATE INDEX idx_session_id ON chat_message (session_id);
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name='idx_session_time')
CREATE INDEX idx_session_time ON chat_message (session_id, create_time);

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='kb_document' AND xtype='U')
CREATE TABLE kb_document (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    title NVARCHAR(255) NOT NULL,
    content NVARCHAR(MAX) NOT NULL,
    category NVARCHAR(100) DEFAULT N'',
    create_time DATETIME2 DEFAULT GETDATE()
);
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name='idx_category')
CREATE INDEX idx_category ON kb_document (category);
