USE [VetaroRep]
GO


DROP VIEW [dbo].[VMiTarea]
GO


CREATE VIEW [dbo].[VMiTarea] AS  

SELECT 
	  	Tarea.id		
      , Tarea.nombre
      , Tarea.detalle	  
	  , CASE WHEN Tarea.ordenFabricacion IS NULL THEN NULL ELSE CONCAT('(', Tarea.ordenFabricacion, ') ', IndOrdenesDeFabricacion.DENOMINACION) END AS ordenFabricacion	  
      , CONCAT('(', Tarea.seccion, ') ', Secciones.NOMBRE) AS seccion	  
	  , CASE WHEN Tarea.puesto IS NULL THEN NULL ELSE CONCAT('(', Tarea.puesto, ') ', Puestos.NOMBRE) END AS puesto            	  
      , Tarea.adjunto
	  , FORMAT (Tarea.fecha, 'dd/MM/yy HH:mm') AS fecha	  		  	  		  	  
	  ,	Tarea.ordenFabricacion AS ordenFabricacionId	  
	  , Tarea.seccion AS seccionId
	  , Tarea.puesto AS puestoId   	  	 
	  
FROM Tarea
WITH (NOLOCK)		
JOIN Secciones ON Secciones.SECCION = Tarea.seccion
LEFT JOIN Puestos ON Puestos.PUESTO = Tarea.puesto
LEFT JOIN IndOrdenesDeFabricacion ON IndOrdenesDeFabricacion.ORDENFABRICACION = Tarea.ordenFabricacion
WHERE cerrada = 0
;

/*
SELECT	* 
FROM   VMiTarea
WITH (NOLOCK)
WHERE	seccionId = 9
		AND (puestoId IS NULL OR puestoId = 6)
ORDER BY fecha DESC, id
*/

SELECT *
FROM	VMiTarea
WITH (NOLOCK)
WHERE seccionId = 9 
ORDER BY fecha DESC, id