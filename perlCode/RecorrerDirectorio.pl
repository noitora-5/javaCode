#!/usr/bin/perl

# Vamos a usar un modulo biblioteca, llamado Cwd
# Esto hace lo mismo que el comando pwd...

use Cwd;

# Imprime todos los nombres de archivos de este directorio,
# incluyendo los subdirectorios!
sub recorrerDirectorio {
	my $dirTrabajo = shift @_;
	my $dirDePartida = cwd;

	chdir $dirTrabajo or die "No pude entrar a $dirTrabajo $! \n";
	# Ojo, punto es el directorio actual, y como nos acabamos de cambiar a $dirTrabajo, estamos abriendo $dirTrabajo!
	opendir my $DIR , '.' or die "No pude abrir $dirTrabajo $! \n";
	my @nombres_archivo = readdir $DIR or die "No pude leer el dir \n";
	closedir $DIR;

	foreach my $archivo (@nombres_archivo){
		next if ($archivo eq '.' );
		next if ($archivo eq '..' );
		if (-f $archivo ){
			print "archivo : $archivo \n";
			next;
		}
		if (-d $archivo ){
			print "-------directorio : $archivo -----\n";
		recorrerDirectorio($archivo);
		next;
		}
	}
	# regresamos al direcotrio de donde partimos...
	chdir $dirDePartida or die "No pude volver a $dirDePartida $! \n";
}

recorrerDirectorio '.';
print "\n";
