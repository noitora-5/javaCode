#!/usr/bin/perl

# recorrerDir.pl

# En este programa vamos a ver como recorrer un directorio,
# y ver de que tipo son los archivos.

use strict;

my $dir_elegido = "/usr/bin";

# $! es el mensaje de error del sistema...
# chdir es.. chdir! change directory
chdir $dir_elegido or die "Algo salio mal $! \n";

opendir DIR , $dir_elegido or die "No se puede abrir el dir: $! \n";

my @archivos = readdir DIR;
foreach my $arch (@archivos){
	if(-d $arch){
		print "$arch es un directorio \n";
		next;
	}	
	if(-l $arch){
		print "$arch es un enlace simbolico \n";
		next;
	}
	if(-f $arch){
		print "$arch es un archivo normal \n";
		next;
	}
	print "Si llegue aqui, es porque $arch es algo raro \n";			
}
