USE [VetaroRep]
GO


-- DROP VIEW [dbo].[VIndOrdenesDeFabricacion]

CREATE VIEW [dbo].[VIndOrdenesDeFabricacion] AS  

SELECT	IndOrdenesDeFabricacion.ORDENFABRICACION
	  ,	IndOrdenesDeFabricacion.DENOMINACION  	  	  
FROM IndOrdenesDeFabricacion
WITH (NOLOCK)		
WHERE IndOrdenesDeFabricacion.ESTADO = 1

;

/*
SELECT	IndOrdenesDeFabricacion.ORDENFABRICACION
	  ,	IndOrdenesDeFabricacion.DENOMINACION      
	  , (
			
			SELECT	COUNT(*) AS CANT		    
			FROM	IndOrdenesDeFabricacionTareas
			WHERE	IndOrdenesDeFabricacionTareas.ORDENFABRICACION = IndOrdenesDeFabricacion.ORDENFABRICACION					
	  ) AS CANTIDAD_TAREAS	 
	  , (
			
			SELECT	COUNT(*) AS CANT		    
			FROM	IndOrdenesDeFabricacionTareas
			WHERE	IndOrdenesDeFabricacionTareas.ORDENFABRICACION = IndOrdenesDeFabricacion.ORDENFABRICACION					
					AND IndOrdenesDeFabricacionTareas.CERRADA = 1
	  ) AS CANTIDAD_TAREAS_CERRADAS
	  , (
			
			SELECT	CONCAT(
					( 
					   
					   STR((
					   CAST(COUNT(*) AS FLOAT)/
					   (
			
							SELECT	CAST(COUNT(*) AS FLOAT) AS CANT		    
							FROM	IndOrdenesDeFabricacionTareas
							WHERE	IndOrdenesDeFabricacionTareas.ORDENFABRICACION = IndOrdenesDeFabricacion.ORDENFABRICACION														
					  )
					  ), 12, 2)
					  						
					) 
					
					, ' %')AS PORC		    
			FROM	IndOrdenesDeFabricacionTareas
			WHERE	IndOrdenesDeFabricacionTareas.ORDENFABRICACION = IndOrdenesDeFabricacion.ORDENFABRICACION					
					AND IndOrdenesDeFabricacionTareas.CERRADA = 1
	  ) AS CANTIDAD_TAREAS_CERRADAS_PORCENTAJE
	  , (
			
			SELECT	COUNT(HORAS) AS CANT		    
			FROM	IndOrdenesDeFabricacionTareas
			WHERE	IndOrdenesDeFabricacionTareas.ORDENFABRICACION = IndOrdenesDeFabricacion.ORDENFABRICACION					
					AND IndOrdenesDeFabricacionTareas.CERRADA = 1
	  ) AS CANTIDAD_HORAS
	  , (
			
			SELECT	SUM(DATEDIFF(hh , IndOrdenesDeFabricacionTareasPersona.TOMATAREA, IndOrdenesDeFabricacionTareasPersona.SUELTATAREA)) AS HS		    
			FROM	IndOrdenesDeFabricacionTareas
					JOIN IndOrdenesDeFabricacionTareasPersona 
						ON IndOrdenesDeFabricacionTareasPersona.NUMERO = IndOrdenesDeFabricacionTareas.NUMERO
						AND IndOrdenesDeFabricacionTareasPersona.ORDENFABRICACION = IndOrdenesDeFabricacionTareas.ORDENFABRICACION
						AND IndOrdenesDeFabricacionTareas.ORDENFABRICACION = IndOrdenesDeFabricacion.ORDENFABRICACION
			WHERE	IndOrdenesDeFabricacionTareas.ORDENFABRICACION = IndOrdenesDeFabricacionTareas.ORDENFABRICACION
					AND IndOrdenesDeFabricacionTareas.NUMERO = IndOrdenesDeFabricacionTareas.NUMERO
					AND IndOrdenesDeFabricacionTareasPersona.SUELTATAREA IS NOT NULL			
	  ) AS HORAS_CALCULADAS
	 
	   , (
			
			SELECT	COUNT(*) AS CANT		    
			FROM	IndOrdenesDeFabricacionTareas
					JOIN IndOrdenesDeFabricacionTareasPersona 
						ON IndOrdenesDeFabricacionTareasPersona.NUMERO = IndOrdenesDeFabricacionTareas.NUMERO
						AND IndOrdenesDeFabricacionTareasPersona.ORDENFABRICACION = IndOrdenesDeFabricacionTareas.ORDENFABRICACION
						AND IndOrdenesDeFabricacionTareas.ORDENFABRICACION = IndOrdenesDeFabricacion.ORDENFABRICACION
			WHERE	IndOrdenesDeFabricacionTareas.ORDENFABRICACION = IndOrdenesDeFabricacion.ORDENFABRICACION					
					AND IndOrdenesDeFabricacionTareasPersona.SUELTATAREA IS NULL			
	  ) AS CANTIDAD_TAREAS_TOMADAS_EN_EJECUCION
	  		

FROM IndOrdenesDeFabricacion
WITH (NOLOCK)		
;
*/
/*


SELECT * 
FROM   VIndOrdenesDeFabricacion
-- WHERE ORDENFABRICACION = 1
WITH (NOLOCK)
ORDER BY ORDENFABRICACION
------------------------------------------


			SELECT	COUNT(*) AS CANT		    
			FROM	IndOrdenesDeFabricacionTareas
			WHERE	IndOrdenesDeFabricacionTareas.ORDENFABRICACION = 1
					AND IndOrdenesDeFabricacionTareas.CERRADA = 1		

*/


-- UPDATE IndOrdenesDeFabricacion SET ESTADO = 1
	
					 
			