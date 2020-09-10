USE [VetaroRep]
GO


NO EJECUTAR !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

-- SELECT GETDATE()  
-- SELECT * FROM Person.Contact WITH (NOLOCK) WHERE ContactID < 20

-- DROP VIEW [dbo].[VIndOrdenesDeFabricacionTareas]


CREATE VIEW [dbo].[VIndOrdenesDeFabricacionTareas] AS  

SELECT CONCAT (  CAST(IndOrdenesDeFabricacionTareas.NUMERO AS VARCHAR), '-', CAST(IndOrdenesDeFabricacionTareas.ORDENFABRICACION AS VARCHAR) )	AS id			  
	  ,	IndOrdenesDeFabricacionTareas.NUMERO
      , IndOrdenesDeFabricacionTareas.NOMBRE
      , IndOrdenesDeFabricacionTareas.DETALLE      
      , CONCAT('(', IndOrdenesDeFabricacionTareas.SECCION, ') ', Secciones.NOMBRE) AS SECCION
	  , CONCAT('(', IndOrdenesDeFabricacionTareas.PUESTO, ') ', Puestos.NOMBRE) AS PUESTO      
      , CASE WHEN IndOrdenesDeFabricacionTareas.CERRADA = 1 THEN 'CERRADA' ELSE 'ABIERTA' END AS ESTADO
	  , CASE WHEN IndOrdenesDeFabricacionTareas.HORAS IS NULL THEN 0.0 ELSE IndOrdenesDeFabricacionTareas.HORAS END AS HORAS       
      , IndOrdenesDeFabricacionTareas.ADJUNTO
	  , (
			
			SELECT	COUNT(*) AS CANT		    
			FROM	IndOrdenesDeFabricacionTareasPersona
			WHERE	IndOrdenesDeFabricacionTareasPersona.ORDENFABRICACION = IndOrdenesDeFabricacionTareas.ORDENFABRICACION
					AND IndOrdenesDeFabricacionTareasPersona.NUMERO = IndOrdenesDeFabricacionTareas.NUMERO			
	  ) AS CANTIDAD_VECES_TOMADA	 
	  , (
			
			SELECT	SUM(DATEDIFF(hh , IndOrdenesDeFabricacionTareasPersona.TOMATAREA, IndOrdenesDeFabricacionTareasPersona.SUELTATAREA)) AS HS		    
			FROM	IndOrdenesDeFabricacionTareasPersona
			WHERE	IndOrdenesDeFabricacionTareasPersona.ORDENFABRICACION = IndOrdenesDeFabricacionTareas.ORDENFABRICACION
					AND IndOrdenesDeFabricacionTareasPersona.NUMERO = IndOrdenesDeFabricacionTareas.NUMERO
					AND IndOrdenesDeFabricacionTareasPersona.SUELTATAREA IS NOT NULL			
	  ) AS HORAS_CALCULADAS
	  , (
			
			SELECT STUFF((
					SELECT '; ' + CONCAT( LTRIM(RTRIM(Personal.APELLIDO)), ' ', LTRIM(RTRIM(Personal.NOMBRE)))
					FROM IndOrdenesDeFabricacionTareasPersona
					JOIN  Personal ON Personal.PERSONAL = IndOrdenesDeFabricacionTareasPersona.PERSONAL
					WHERE	IndOrdenesDeFabricacionTareasPersona.ORDENFABRICACION = IndOrdenesDeFabricacionTareas.ORDENFABRICACION
							AND IndOrdenesDeFabricacionTareasPersona.NUMERO = IndOrdenesDeFabricacionTareas.NUMERO
			FOR XML PATH('')) ,1,1,'') AS PERSONAS			
	  ) AS PERSONAS
	   , (
			
			SELECT	COUNT(*) AS CANT		    
			FROM	IndOrdenesDeFabricacionTareasPersona
			WHERE	IndOrdenesDeFabricacionTareasPersona.ORDENFABRICACION = IndOrdenesDeFabricacionTareas.ORDENFABRICACION
					AND IndOrdenesDeFabricacionTareasPersona.NUMERO = IndOrdenesDeFabricacionTareas.NUMERO	
					AND IndOrdenesDeFabricacionTareasPersona.SUELTATAREA IS NULL		
	  ) AS CANTIDAD_EN_EJECUCION
	   , (
			
			SELECT STUFF((
					SELECT '; ' + CONCAT( LTRIM(RTRIM(Personal.APELLIDO)), ' ', LTRIM(RTRIM(Personal.NOMBRE)), ' ( desde ' , FORMAT (IndOrdenesDeFabricacionTareasPersona.TOMATAREA, 'dd/MM/yy HH:mm'), ')')
					FROM IndOrdenesDeFabricacionTareasPersona
					JOIN  Personal ON Personal.PERSONAL = IndOrdenesDeFabricacionTareasPersona.PERSONAL
					WHERE	IndOrdenesDeFabricacionTareasPersona.ORDENFABRICACION = IndOrdenesDeFabricacionTareas.ORDENFABRICACION
							AND IndOrdenesDeFabricacionTareasPersona.NUMERO = IndOrdenesDeFabricacionTareas.NUMERO
							AND IndOrdenesDeFabricacionTareasPersona.SUELTATAREA IS NULL		
			FOR XML PATH('')) ,1,1,'') AS PERSONAS			
	  ) AS PERSONAS_EN_EJECUCION
	  ,	IndOrdenesDeFabricacionTareas.ORDENFABRICACION
	  , IndOrdenesDeFabricacionTareas.SECCION AS SECCION_ID
	  , IndOrdenesDeFabricacionTareas.PUESTO AS PUESTO_ID      

FROM IndOrdenesDeFabricacionTareas
WITH (NOLOCK)		
JOIN Secciones ON Secciones.SECCION = IndOrdenesDeFabricacionTareas.SECCION
LEFT JOIN Puestos ON Puestos.PUESTO = IndOrdenesDeFabricacionTareas.PUESTO
;

/*

SELECT * 
FROM   VIndOrdenesDeFabricacionTareas
WITH (NOLOCK)
WHERE ORDENFABRICACION = 1
ORDER BY NUMERO



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





