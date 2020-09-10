USE [VetaroRep]
GO


-- ====================================================================================
-- TABLA: TAREAS
-- ====================================================================================

ALTER TABLE [dbo].[Tarea] DROP CONSTRAINT [fk_tarea_a]
GO

ALTER TABLE [dbo].[Tarea] DROP CONSTRAINT [fk_tarea_b]
GO

ALTER TABLE [dbo].[Tarea] DROP CONSTRAINT [fk_tarea_c]
GO

DROP TABLE [dbo].[Tarea]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Tarea](	

	[id] [varchar](36) NOT NULL,	
	[nombre] [varchar](150) NOT NULL,
	[detalle] [varchar](255) NULL,
	[ordenFabricacion] [smallint] NULL,
	[seccion] [smallint] NOT NULL,
	[puesto] [smallint] NULL,
	[cerrada] [tinyint] NOT NULL,
	[horas] [float] NULL,
	[adjunto] [varchar](255) NULL,
	[fecha] [datetime] NULL

	CONSTRAINT [UTarea] UNIQUE([id]),   	
 CONSTRAINT [PorTarea] PRIMARY KEY CLUSTERED 
(
	[id] DESC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[Tarea]  WITH CHECK ADD  CONSTRAINT [fk_tarea_a] FOREIGN KEY([ordenFabricacion])
REFERENCES [dbo].[IndOrdenesDeFabricacion] ([ORDENFABRICACION])
GO

ALTER TABLE [dbo].[Tarea] CHECK CONSTRAINT [fk_tarea_a]
GO

ALTER TABLE [dbo].[Tarea]  WITH CHECK ADD  CONSTRAINT [fk_tarea_b] FOREIGN KEY([seccion])
REFERENCES [dbo].[Secciones] ([SECCION])
GO

ALTER TABLE [dbo].[Tarea] CHECK CONSTRAINT [fk_tarea_b]
GO

ALTER TABLE [dbo].[Tarea]  WITH CHECK ADD  CONSTRAINT [fk_tarea_c] FOREIGN KEY([puesto])
REFERENCES [dbo].[Puestos] ([PUESTO])
GO

ALTER TABLE [dbo].[Tarea] CHECK CONSTRAINT [fk_tarea_c]
GO

SELECT * FROM [dbo].[Tarea]
GO

-- SELECT * FROM [dbo].[Tarea] WHERE id = '9778.7.2020'




-- DELETE FROM [dbo].[Tarea]


--update [Tarea] set cerrada = 0
