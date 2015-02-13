#!/usr/bin/perl 

# Nombre Completo: Francisco Andres Martinez Gutierrez
# Rut: 17.305.265-0
# Secion: 002D

use Cwd; 

%estructura;

sub buscarArchivo {
	my $dirOriginal = cwd;
	my $dirTrabajo = shift;
	chdir $dirTrabajo or die "No pude cambiarme al directorio $dirTrabajo : $! \n";
	opendir $DIR , '.' or die "No pude abrir el directorio :$! \n";
	my @hijos = readdir $DIR or die "No pude leer el directorio : $! \n";
	closedir $DIR;
	my $suma = 0;
	foreach $hijo (@hijos) {
		next if ($hijo eq '.');
		next if ($hijo eq '..');
		next if (-l $hijo);
		if (-f $hijo){
			my @privilegio = `ls -la $hijo`;
			$opcion1 = '-rwxrwxrwx';
			$opcion2 = '-rwxrw-rw-';
			$opcion3 = '-rw-rw-rw-';
			if ($privilegio[0] eq $opcion1)
			{
				my $ruta = cwd;
				my $dueno = stat($hijo);
				push(@{$estructura{$hijo}}, $ruta, $dueno);
			}
			if ($privilegio[0] eq $opcion2)
			{
				my $ruta = cwd;
				my $dueno = stat($hijo);
				push(@{$estructura{$hijo}}, $ruta, $dueno);
			}
			if ($privilegio[0] eq $opcion3)
			{
				my $ruta = cwd;
				my $dueno = stat($hijo);
				push(@{$estructura{$hijo}}, $ruta, $dueno);
			}
		}
		if (-d $hijo){
			buscarArchivo ($hijo); 
		}	
	}
	chdir $dirOriginal or die "No pude volver al directorio anterior : $! \n";
	
}


my $num_argumentos = $#ARGV + 1;
if ($num_argumentos == 0){
	buscarArchivo '.';
}
if ($num_argumentos == 1){
	my $directorio = shift @ARGV;
	buscarArchivo $directorio;
}
if ($num_argumentos > 1){
	print "uso : listaRepetidos.pl directorio\n";
	exit;
}

foreach my $nombre_archivo (keys %estructura) {
    
	@arreglo_rutas = @{$estructura{$nombre_archivo}};
	if (@arreglo_rutas > 1){
		print "El archivo $nombre_archivo esta en: \n";
    		foreach $ruta(@arreglo_rutas) {
        		print "\t$ruta su dueño es: \n";
    		}
		foreach $dueno(@arreglo_rutas) {
			print "\t$dueno revisar situacion. \n";
		}
		print "\tNumero de Repeticiones ". @arreglo_rutas. "\n";
	}
	
}



