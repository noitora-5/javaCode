#!/usr/bin/perl

# Procesos.pl
# Como mirar los procesos: ps -fea
#		ps -e para todos los procesos

# Opcion 1: Usar ps y ver que viene

use strict;
my @resultado = `ps -e`;
shift @resultado; #sacar la primera linea, los titulos
# Procesar el resto...
# La dificultad esta en parsear el resultado!!
# Pues depende de la version de Unix que estes usando :(
foreach my $linea (@resultado){
	my ($pid, $tty, $time, $cmd)= split(/\s/, $linea);
	print " $pid $cmd\n";
}
