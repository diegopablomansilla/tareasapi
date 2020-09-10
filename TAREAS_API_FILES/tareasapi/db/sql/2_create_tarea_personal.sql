USE [VetaroRep]
GO

-- ====================================================================================
-- TABLA: TAREAS
-- ====================================================================================

ALTER TABLE [dbo].[TareaPersona] DROP CONSTRAINT [fk_TareaPersona_a]
GO

ALTER TABLE [dbo].[TareaPersona] DROP CONSTRAINT [fk_TareaPersona_b]
GO

DROP TABLE [dbo].[TareaPersona]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[TareaPersona](	

	[tomaTarea] [datetime] NOT NULL,
	[sueltaTarea] [datetime] NULL,
	[comentarios] [varchar](255) NULL,
	[tarea] [varchar](36) NOT NULL,
	[persona] [smallint] NOT NULL
	
 CONSTRAINT [PorTareaPersona] PRIMARY KEY CLUSTERED 
(
	[tarea] DESC,
	[persona] DESC,
	[tomaTarea] DESC

)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[TareaPersona]  WITH CHECK ADD  CONSTRAINT [fk_TareaPersona_a] FOREIGN KEY([tarea])
REFERENCES [dbo].[Tarea] ([id])
GO

ALTER TABLE [dbo].[TareaPersona] CHECK CONSTRAINT [fk_TareaPersona_a]
GO

ALTER TABLE [dbo].[TareaPersona]  WITH CHECK ADD  CONSTRAINT [fk_TareaPersona_b] FOREIGN KEY([persona])
REFERENCES [dbo].[Personal] ([PERSONAL])
GO

ALTER TABLE [dbo].[TareaPersona] CHECK CONSTRAINT [fk_TareaPersona_b]
GO

SELECT * FROM [dbo].[TareaPersona]
GO


-- DELETE FROM [dbo].[TareaPersona]

