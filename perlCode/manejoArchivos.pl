#!/usr/bin/perl 

# ManejoArchivos.pl

# Hoy: Funciones basicas de manejo de archivos.
# Desde perl siempre es posible llamar al sistema para
# operar con archivos.
# Por ejemplo, para borrar, puedo hacer `rm archivo`...
# Pero lo mismo es posible hacerlodesde nuestro programa perl.
# Las ventanas se hacen evidentes cuando no se quiere borrar 1
# archivo, sino cuando se quieren borrar muchos.
# Hacer rm *. algo crea n procesos si es que hay que borrar
# n archivos.
# Usando funciones de perl, puede con 1 solo proceso.
# Mucho mas rapido y eficiente.
# Unix: nombre + inodo
# El equivalente de rm, en Perl es unlink
#			mv rename

use strict;

my $cuantos = unlink 'a.out' , 'b.out' , 'c.out';
print "Se borraron $cuantos archivos \n";


# Puedo emitir warning si no funciona el borrador..

unlink 'lala.txt' or warn "No se pudo borrar el archivo : $! \n";

# Ojo! unlink borra archivos normales...
# para borrar directorio usa rmdir.

# unlink se puede combinar con glob para borrar con un filtro.
# para borrar todos los archivos php:
unlink glob "*.php";

# El proceso inverso a unlink, es crear un link...:
link "nombre_viejo", "nombre_nuevo";

# el ultimo.. el mover.. o sea, renombrar...
rename "nombre_viejo", "nombre_nuevo";

# No hay ejercicio pero deben usarlo para la tarea!!
# Una nota antes de terminar por hoy:
# COmo cortar un archivo?
# split. -> rtfm -> man split 10M
# Como volver a pegarlo?
# cat.
# Otra forma? fijense en la opcion -F de unzip
