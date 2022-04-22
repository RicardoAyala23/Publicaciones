CREATE SCHEMA "sistemas_blog_spring_boot";

Colocar estas sentencias en la Tabla Publicaciones

INSERT INTO `sistemas_blog_spring_boot`.`publicaciones` (`contenido`, `descripcion`, `titulo`) VALUES ('Contiene todo relacionado con microservicios', 'Esta publicacion contiene informacion sobre Spring-boot', 'Spring-Boot');
INSERT INTO `sistemas_blog_spring_boot`.`publicaciones` (`contenido`, `descripcion`, `titulo`) VALUES ('JPA,JSP,JDBC', 'Esta publicacion contiene informacion relacionada con algunos temas de Java', 'Java');
INSERT INTO `sistemas_blog_spring_boot`.`publicaciones` (`contenido`, `descripcion`, `titulo`) VALUES ('@Autowired,@Bean,@Config', 'Esta publicacion contiene informacion sobre algunas JavaAnotations, sobre el como utilizar la Anotacion @Configuration', 'Spring');


Colocar estas sentencias en la tabla de comentarios

INSERT INTO `sistemas_blog_spring_boot`.`comentarios` (`cuerpo`, `email`, `nombre`, `publicacion_id`) VALUES ('Me gusto mucho tu publicacion, me gustaria ver mas videos sobre Spring-Boot a un futuro', 'Jorge@gmail.com', 'Jorge Hernandez', '1');
INSERT INTO `sistemas_blog_spring_boot`.`comentarios` (`cuerpo`, `email`, `nombre`, `publicacion_id`) VALUES ('Una pregunta, subiras el curso completo sobre Spring-boot ?', 'maria@hotmail.com', 'Maria Hernandez', '1');
INSERT INTO `sistemas_blog_spring_boot`.`comentarios` (`cuerpo`, `email`, `nombre`, `publicacion_id`) VALUES ('Despues de este curso, subiras uno con el framwork spring ?', 'ricardo@gamil.com', 'Ricardo Perez', '2');
INSERT INTO `sistemas_blog_spring_boot`.`comentarios` (`cuerpo`, `email`, `nombre`, `publicacion_id`) VALUES ('Me gusto mucho esta publicacion, espero puedas sefuir subiendo contenido', 'pedro@Yahoo.net', 'Pedro Carmona', '2');
INSERT INTO `sistemas_blog_spring_boot`.`comentarios` (`cuerpo`, `email`, `nombre`, `publicacion_id`) VALUES ('Disculpa me podrias decir cual seria la diferencia sobre usar el XML y la clase con la anotacion @Confi ?', 'omar@gmail.com', 'omar Hernandez', '3');
