#!/usr/bin/perl
# MandarMail.pl

# OBS! existen modulos para mandar mail, que hacen esto
# aun mas simple. Pero no estan instalados!
# Este programan asume que el servidor de correo
# sendmail esta instalado en la maquia en la ruta: /usr/sbin/sendmail.
# (esta en la conf en el duoc!)

use strict;

# Abrimos un canal de comunicacion con sendmail ....
# Como sendmail es un programa y no un archivo, colocamos
# un pipe ( | )
open my $SENDMAIL , '|-' , '/usr/sbin/sendmail -oi -t ' or die "No me pude comunicar con sendmail $! \n";
# Mandarle cosas a sendmail es como escribir a un archivo!

my $emisor = 'yo@localhost'; # Comillas simples!!!! @ -> arreglo
my $receptor = 'root@localhost';
# imprimimos !
print $SENDMAIL "From: el emisor <$emisor>\n";
print $SENDMAIL "To: el root <$receptor>\n";
print $SENDMAIL "Subject: Mandar mail desde un programa\n";
print $SENDMAIL "El cuerpo puede tener n lineas\n";
print $SENDMAIL "El cuerpo puede tener n lineas\n";
print $SENDMAIL "El cuerpo puede tener n lineas\n";
print $SENDMAIL "El cuerpo puede tener n lineas\n";
print $SENDMAIL "El cuerpo puede tener n lineas\n";
print $SENDMAIL "El cuerpo puede tener n lineas\n";
print "Terminar de mandar \n";
close(SENDMAIL) or warn "Fin?\n";
