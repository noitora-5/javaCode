#!/usr/bin/perl

# llamadas.pl
# Como hacer llamadas al sistema
# Esto sirve para poder llamar a otros programas desde un programa perl.

# En perl hay varias formas de llamar a otro programa...
# La primera, la funcion exec
# Exec llama a otro programa, y no vuele al programa en perl original

exec ("vi", "HolaMundo.txt");
# print "Esto no va a aparecer \n";

# Segunda Forma
# Es similar a la anterior, pero el control vuelve a perl
# al terminar la ejecucion del programa llamado

use strict;
my $resultado = system("vi", "HolaMundo.txt");
# Resultado obtiene un valor que viene desde el sistema
# operativo, para obtener un codigo util hay que hacer trampa
# y dividir por 256 o shiftearlo a 8 bits
# En unix, los programas le cuentan al sistema operativo
# porque terminaron su ejecucion.
# El codigo 0 es para terminar correctamente.
# Cualquier otro valor es eror
my $resultado = ($resultado >>=8);
print "Esto si va a aparecer \n";
if ($resultado != 0){
	print "Terminamos con un error \n";
}
else{
	print "El programa termino correctamente \n";
}
