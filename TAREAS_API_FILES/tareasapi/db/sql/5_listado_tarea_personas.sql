USE [VetaroRep]
GO


DROP VIEW [dbo].[VTareaPersona]
GO

CREATE VIEW [dbo].[VTareaPersona] AS  

SELECT  CONCAT (  CAST(TareaPersona.persona AS VARCHAR), '-', CAST(TareaPersona.tarea AS VARCHAR), '-', CAST(TareaPersona.tomaTarea AS VARCHAR) )	AS id		
	  , Personal.apellido
	  , Personal.nombre
	  , FORMAT (TareaPersona.tomaTarea, 'dd/MM/yy HH:mm') AS tomaTarea
	  , FORMAT (TareaPersona.sueltaTarea, 'dd/MM/yy HH:mm') AS sueltaTarea	  
      , TareaPersona.comentarios
      
	  , TareaPersona.tarea AS tareaId      
      , TareaPersona.persona AS personaId
  FROM TareaPersona
  WITH (NOLOCK)					  
  JOIN  Personal ON Personal.PERSONAL = TareaPersona.persona
  ;

  
  
  
 /* 

  SELECT * FROM VTareaPersona  
  WITH (NOLOCK)
  WHERE tareaId = '9994.7.2020'
  ORDER BY sueltaTarea DESC, tomaTarea DESC, apellido, nombre

  SELECT * FROM VTareaPersona  
  WITH (NOLOCK)
  WHERE personaId = 1
  ORDER BY sueltaTarea DESC, tomaTarea DESC, apellido, nombre


  -- Todas las tareas que tengo en ejecucion

  SELECT * FROM VTareaPersona  
  WITH (NOLOCK)
  WHERE personaId = 1
		AND sueltaTarea IS NULL
  ORDER BY sueltaTarea DESC, tomaTarea DESC, apellido, nombre


  -- TOMAR TAREA
  
  1.1) UPDATE TareaPersona SET sueltaTarea = GETDATE() WHERE sueltaTarea IS NULL AND persona = ?    
  1.2) INSERT INTO TareaPersona (tomaTarea, tarea, persona) VALUES (GETDATE(), ?, ?)
  2) UPDATE TareaPersona SET sueltaTarea = GETDATE(), comentario = ? WHERE tarea = ? AND persona = ?


*/


/*
SELECT PERSONAL, NOMBRE, APELLIDO, SECCION, PUESTO, CASE WHEN TareaPersonaAdmin.persona IS NOT NULL THEN 'A' ELSE 'O' END AS ROL 
FROM PERSONAL 
LEFT JOIN TareaPersonaAdmin ON PERSONAL.PERSONAL = TareaPersonaAdmin.persona
WHERE CUIL = '20122750877' AND CAST(PERSONAL AS VARCHAR) = '14' 
*/

-- SELECT DNI, PERSONAL, NOMBRE, APELLIDO, SECCION, PUESTO, CUIL FROM PERSONAL
-- SELECT * FROM PERSONAL

--  1 3463 20241193463








