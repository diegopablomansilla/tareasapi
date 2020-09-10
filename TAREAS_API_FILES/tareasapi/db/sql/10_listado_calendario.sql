USE [VetaroRep]
GO


DROP VIEW [dbo].[VCalendario]
GO

CREATE VIEW [dbo].[VCalendario] AS  

SELECT	id
		, CAST(dia AS VARCHAR) AS dia
		, FORMAT (fecha, 'dd/MM/yy') AS fecha
		, FORMAT (horaInicio, N'hh\:mm') AS horaInicio
		, FORMAT (horaCierre, N'hh\:mm') AS horaCierre
		, CASE	WHEN dia IS NOT NULL AND dia = 1 THEN 'Lunes'
				WHEN dia IS NOT NULL AND dia = 2 THEN 'Martes'
				WHEN dia IS NOT NULL AND dia = 3 THEN 'Miércoles'
				WHEN dia IS NOT NULL AND dia = 4 THEN 'Jueves'
				WHEN dia IS NOT NULL AND dia = 5 THEN 'Viernes'
				WHEN dia IS NOT NULL AND dia = 6 THEN 'Sábado'
				WHEN dia IS NOT NULL AND dia = 7 THEN 'Domingo'
				WHEN fecha IS NOT NULL  THEN  FORMAT (fecha, 'dd/MM/yy')
		END AS etiqueta
		, CASE	WHEN dia IS NOT NULL THEN 0 ELSE 1 END AS o
		, fecha AS fechaDate
		, dia AS diaInt
FROM	TareaCalendario;
GO

-- SELECT id, dia, fecha, horaInicio, horaCierre, etiqueta FROM VCalendario WITH (NOLOCK) ORDER BY o, dia, fecha desc;
-- GO



-- SELECT * FROM VCalendario WITH (NOLOCK) ORDER BY o, diaInt, fechaDate desc

-- SELECT * FROM VCalendario WITH (NOLOCK) WHERE fechaDate IS NULL OR fechaDate >= CAST(GETDATE() AS DATE) ORDER BY o, diaInt, fechaDate desc

