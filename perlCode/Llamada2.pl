#!/usr/bin/perl

#Llamada2.pl
# En este archivo vamos a obtener la salida estandar del comando,
# no es el resultado de ejecucion.
# Hay dos formas equivalentes de hacer esto:
# backtick y qx

use strict;

# my $info = `uptime`;
my $info = qx/uptime/;
print "Uptime : $info \n";

my $usuarios = `who`;
foreach my $usuario ($usuarios){
	print "$usuario \n";
}
