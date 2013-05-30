#!/bin/bash

date

if [ $# -ne 1 ] ; then
	echo "Usage: compileCv.sh <.tex file>"
	exit
fi

DIR=`dirname $1`
FILEPATH=$DIR/`basename $1 .tex`
echo "file: $FILEPATH"

if [ -f $1 ] ; then
	# Compile, clean
	pdflatex -halt-on-error -output-directory $DIR $1
	RETVAL=$?
	if [ $RETVAL == 0 ] ; then
		echo "compiled!"
		rm -f $FILEPATH.aux
		rm -f $FILEPATH.tex
		rm -f $FILEPATH.log
	else
		echo "Nepodarilo sa skompilovat: return code: $?"
	fi
else
	echo "File '$XMLPATH' not foud"
fi
