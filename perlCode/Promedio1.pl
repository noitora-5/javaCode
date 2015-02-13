#!/usr/bin/perl

# Promedio1.pl
# Primer programa que contiene funciones perl..
# En este caso, vamos a calcular el promedio de un arreglo de numeros.
# Para obtener los numeros para llenar el arreglo
# vamos a hacer lo que haciamos en IPF1501, preguntarselos
# al usuario, o sea, lo vamos a hacer en la forma convencional.

# En perl las funciones no se llaman funciones....
# Se llaman sub (de subrutina).
# Habitualmente, las subrutinas no declaran sus parametros...
sub promedio {
	# En perl, siempre puedo acceder a los valores de los 		argumentos
	# usando el arreglo por defecto: @_ (arroba under score)
	$suma = 0;
	for ($i = 0; $i < @_ ; $i++){
		$suma += $_[$i] ;
	}
	return $suma / @_;
}

# Vamos a pedirle al usuario que ingrese una lista de numeros

print "Ingrese una lista de numeros \n";
print "Para terminar ingrese ctrl + D (=EOF ) \n";
@numeros_a_promediar;
while ($numero = <STDIN>){
	# agregamos el elemento arreglo
	push (@numeros_a_promediar, $numero);
}

$promedio = promedio(@numeros_a_promediar);

print "El promedio es : $promedio \n";



# http://startupchile.org/supweek/
