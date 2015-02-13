#!/usr/bin/perl

# Promedio1.pl
# Lo mismo que en el anterior pero al estilo Unix!
# Esto es: no vamos a pedir que ingresen los numeros
# por STDIN, sino que vamos a esperar que lleguen en 
# la linea de comandos.

sub promedio {
	# En perl, siempre puedo acceder a los valores de los 		argumentos
	# usando el arreglo por defecto: @_ (arroba under score)
	$suma = 0;
	for ($i = 0; $i < @_ ; $i++){
		$suma += $_[$i] ;
	}
	return $suma / @_;
}

# Los argumentos que el usuario ingresa al programa quedan en
# un arreglo especial llamado @ARGV.

if (@ARGV > 0){
	print promedio(@ARGV)."\n";
}
else{
	print "Uso : promedio2.pl <lista de numeros> \n";
}
