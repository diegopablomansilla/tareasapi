USE [VetaroRep]
GO

-- ====================================================================================
-- TABLA: TAREAS
-- ====================================================================================

ALTER TABLE [dbo].[TareaPersonaAdmin] DROP CONSTRAINT [fk_TareaPersonaAdmin_a]
GO


DROP TABLE [dbo].[TareaPersonaAdmin]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[TareaPersonaAdmin](	
	
	[persona] [smallint] NOT NULL
	
 CONSTRAINT [PorTareaPersonaAdmin] PRIMARY KEY CLUSTERED 
(
	
	[persona] DESC

)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


ALTER TABLE [dbo].[TareaPersonaAdmin]  WITH CHECK ADD  CONSTRAINT [fk_TareaPersonaAdmin_a] FOREIGN KEY([persona])
REFERENCES [dbo].[Personal] ([PERSONAL])
GO

ALTER TABLE [dbo].[TareaPersonaAdmin] CHECK CONSTRAINT [fk_TareaPersonaAdmin_a]
GO

SELECT * FROM [dbo].[TareaPersonaAdmin]
GO


-- DELETE FROM [dbo].[TareaPersonaAdmin]

-- INSERT INTO  [dbo].[TareaPersonaAdmin] VALUES (1);
-- INSERT INTO  [dbo].[TareaPersonaAdmin] VALUES (14);

