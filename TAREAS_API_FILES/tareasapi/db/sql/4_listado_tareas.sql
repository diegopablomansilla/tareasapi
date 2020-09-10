USE [VetaroRep]
GO

-- SELECT GETDATE()  
-- SELECT * FROM Person.Contact WITH (NOLOCK) WHERE ContactID < 20

-- ===========================================================================================


DROP VIEW [dbo].[VTarea]
GO


CREATE VIEW [dbo].[VTarea] AS  

SELECT 
	  	Tarea.id		
      , Tarea.nombre
      , Tarea.detalle	  
	  , CASE WHEN Tarea.ordenFabricacion IS NULL THEN NULL ELSE CONCAT('(', Tarea.ordenFabricacion, ') ', IndOrdenesDeFabricacion.DENOMINACION) END AS ordenFabricacion	  
      , CONCAT('(', Tarea.seccion, ') ', Secciones.NOMBRE) AS seccion	  
	  , CASE WHEN Tarea.puesto IS NULL THEN NULL ELSE CONCAT('(', Tarea.puesto, ') ', Puestos.NOMBRE) END AS puesto      
      , CASE WHEN Tarea.cerrada = 1 THEN 'CERRADA' ELSE 'ABIERTA' END AS estado	  
	  , CASE WHEN Tarea.horas IS NULL THEN 0.0 ELSE Tarea.horas END AS horas   	  
      , Tarea.adjunto
	  , FORMAT (Tarea.fecha, 'dd/MM/yy HH:mm') AS fecha	  
	  , (
			
			SELECT	COUNT(*) AS CANT		    
			FROM	TareaPersona
			WITH (NOLOCK)
			WHERE	TareaPersona.tarea = Tarea.id			
	  ) AS cantidadVecesTomada	  
	  , (
			
			SELECT	 STR((SUM(DATEDIFF(mi , TareaPersona.tomaTarea, TareaPersona.sueltaTarea)) / 60.0 ), 12, 3) AS HS		    
			FROM	TareaPersona
			WITH (NOLOCK)
			WHERE	TareaPersona.tarea = Tarea.id
					AND TareaPersona.sueltaTarea IS NOT NULL			
	  ) AS horasCalculadas	  
	  , (
			
			SELECT STUFF((
					SELECT distinct '; ' + CONCAT( LTRIM(RTRIM(Personal.APELLIDO)), ' ', LTRIM(RTRIM(Personal.NOMBRE)))
					FROM TareaPersona
					WITH (NOLOCK)
					JOIN  Personal ON Personal.PERSONAL = TareaPersona.persona
					WHERE	TareaPersona.tarea = Tarea.id
			FOR XML PATH('')) ,1,1,'') AS PERSONAS			
	  ) AS personas	  
	   , (
			
			SELECT	COUNT(*) AS CANT		    
			FROM	TareaPersona
			WITH (NOLOCK)
			WHERE	TareaPersona.tarea = Tarea.id	
					AND TareaPersona.sueltaTarea IS NULL		
	  ) AS cantidadEnEjecucion	  
	   , (
			
			SELECT STUFF((
					SELECT distinct '; ' + CONCAT( LTRIM(RTRIM(Personal.APELLIDO)), ' ', LTRIM(RTRIM(Personal.NOMBRE)), ' ( desde ' , FORMAT (TareaPersona.tomaTarea, 'dd/MM/yy HH:mm'), ')')
					FROM TareaPersona
					WITH (NOLOCK)
					JOIN  Personal ON Personal.PERSONAL = TareaPersona.persona
					WHERE	TareaPersona.tarea = Tarea.id
							AND TareaPersona.sueltaTarea IS NULL		
			FOR XML PATH('')) ,1,1,'') AS PERSONAS			
	  ) AS personasEnEjecucion	  
	  ,	Tarea.ordenFabricacion AS ordenFabricacionId	  
	  , Tarea.seccion AS seccionId
	  , Tarea.puesto AS puestoId   
	  , CASE WHEN Tarea.cerrada = 1 THEN 'true' ELSE 'false' END AS cerrada   	     
	  , Tarea.cerrada AS cerradaNumber   	     
	  
	  
FROM Tarea
WITH (NOLOCK)		
JOIN Secciones ON Secciones.SECCION = Tarea.seccion
LEFT JOIN Puestos ON Puestos.PUESTO = Tarea.puesto
LEFT JOIN IndOrdenesDeFabricacion ON IndOrdenesDeFabricacion.ORDENFABRICACION = Tarea.ordenFabricacion


/*

SELECT * 
FROM   VTarea
WITH (NOLOCK)
WHERE  fecha > DATEADD(MM, -1, fecha)  
	   -- AND ordenFabricacion = 1	   	
	   -- AND cerrada = 1
ORDER BY cerrada, fecha DESC, id



SELECT * 
FROM   Secciones
WITH (NOLOCK)
ORDER BY SECCION

SELECT * 
FROM   Puestos
WITH (NOLOCK)
ORDER BY PUESTO

SELECT MAX(NUMERO+1) FROM   IndOrdenesDeFabricacionTareas WITH (NOLOCK) WHERE ORDENFABRICACION = 1

INSERT INTO IndOrdenesDeFabricacionTareas (CERRADA, NOMBRE, DETALLE, SECCION, PUESTO, NUMERO, ORDENFABRICACION) VALUES (0, 'AAA', 'BBBB', 8, 1, (SELECT MAX(NUMERO+1) FROM IndOrdenesDeFabricacionTareas WITH (NOLOCK) WHERE ORDENFABRICACION = 1), 1)

TAREA POSTMAN, TAREA POSTMAN !!!, 8, 5, 1, 1


*/
/*
SELECT id FROM Tarea ORDER BY cerrada, fecha DESC, id
SELECT COUNT(*), GETDATE(), MONTH(GETDATE()), YEAR(GETDATE())  FROM Tarea 
*/
-- (SELECT CONCAT(COUNT(*) + 1, '.', MONTH(GETDATE()), '.', YEAR(GETDATE()))  FROM Tarea WITH (NOLOCK) WHERE MONTH (fecha) = MONTH(GETDATE()) AND YEAR(fecha) =  YEAR(GETDATE()))
 
