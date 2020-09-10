USE [VetaroRep]
GO

-- ====================================================================================
-- TABLA: TAREAS CALENDARIO
-- ====================================================================================

DROP TABLE [dbo].[TareaCalendario]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[TareaCalendario](	
	
	[id] [varchar](36) NOT NULL,	
	[dia] [smallint] NULL,	
	[fecha] [date] NULL,			
	[horaInicio] [time] NULL,
	[horaCierre] [time] NULL	
	
 CONSTRAINT [PorTareaCalendario] PRIMARY KEY CLUSTERED 
(	
	[id] DESC

)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO





-- DELETE FROM [dbo].[TareaCalendario]

SELECT * FROM [dbo].[TareaCalendario] ORDER BY dia
GO


INSERT INTO [dbo].[TareaCalendario] VALUES ('1', 1, null, '8:00', '18:00');
INSERT INTO [dbo].[TareaCalendario] VALUES ('2', 2, null, '8:00', '18:00');
INSERT INTO [dbo].[TareaCalendario] VALUES ('3', 3, null, '8:00', '18:00');
INSERT INTO [dbo].[TareaCalendario] VALUES ('4', 4, null, '8:00', '18:00');
INSERT INTO [dbo].[TareaCalendario] VALUES ('5', 5, null, '8:00', '14:00');
INSERT INTO [dbo].[TareaCalendario] VALUES ('6', 6, null, '8:00', '14:00');
INSERT INTO [dbo].[TareaCalendario] VALUES ('7', 7, null, '8:00', '14:00');


-- es un ejemplo
-- INSERT INTO [dbo].[TareaCalendario] VALUES (FORMAT (getdate(), 'yyyy-MM-dd '), null, GETDATE(), '8:00', '13:30');



/*

SELECT DATEPART(weekday, GETDATE()) as theDay    
SELECT DATEPART(weekday, cast('2020-07-20' as date)) -- 1 Lunes
SELECT DATEPART(weekday, cast('2020-07-21' as date)) -- 2 Martes
SELECT DATEPART(weekday, cast('2020-07-22' as date)) -- 3 Miercoles
SELECT DATEPART(weekday, cast('2020-07-23' as date)) -- 4 Jueves
SELECT DATEPART(weekday, cast('2020-07-24' as date)) -- 5 Viernes
SELECT DATEPART(weekday, cast('2020-07-25' as date)) -- 6 Sabado
SELECT DATEPART(weekday, cast('2020-07-26' as date)) -- 7 Domingo

*/

/*

SELECT	TOP 1 CASE WHEN   CAST( GETDATE() AS TIME) >=  horaCierre THEN 1 ELSE 0 END AS soltar
FROM	TareaCalendario
WHERE	fecha =	CAST (GETDATE() AS DATE) OR dia = DATEPART(weekday, GETDATE())
ORDER BY fecha DESC

SELECT	TOP 1 CONVERT(DATETIME, CONCAT ( CAST(GETDATE() AS DATE), ' ',  FORMAT(horaCierre, N'hh\:mm\:ss')  ) , 102)
FROM	TareaCalendario
WHERE	fecha =	CAST (GETDATE() AS DATE) OR dia = DATEPART(weekday, GETDATE())
ORDER BY fecha DESC 

*/

-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

-- SELECT * FROM TareaPersona
-- UPDATE TareaPersona SET sueltaTarea = null
-- ----------------------------------------------------------------------------------------------------------------------
UPDATE TareaPersona 
			SET sueltaTarea = 
						(
							-- SELECT	TOP 1 CONVERT(DATETIME, CONCAT ( CAST(tomaTarea AS DATE), ' ',  FORMAT(horaCierre, N'hh\:mm\:ss')  ) , 102)
							SELECT	TOP 1 CONVERT(DATETIME, CONCAT ( CAST(GETDATE() AS DATE), ' ',  FORMAT(horaCierre, N'hh\:mm\:ss')  ) , 102)
							FROM	TareaCalendario
							WHERE	fecha =	CAST (GETDATE() AS DATE) OR dia = DATEPART(weekday, GETDATE())
							ORDER BY fecha DESC 
						)
WHERE	sueltaTarea IS NULL
		-- AND controlar q la fecha de hoy es igual a la fecha de toma de tarea
		AND 1 = 		(
							SELECT	TOP 1 CASE WHEN   CAST( GETDATE() AS TIME) >=  horaCierre THEN 1 ELSE 0 END AS soltar
							FROM	TareaCalendario
							WHERE	fecha =	CAST (GETDATE() AS DATE) OR dia = DATEPART(weekday, GETDATE())
							ORDER BY fecha DESC
	
						)
-- ----------------------------------------------------------------------------------------------------------------------










