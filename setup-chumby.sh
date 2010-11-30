#!/bin/bash

TOPDIR=${HOME}/oe


export BBPATH=${TOPDIR}/chumby:${TOPDIR}/openembedded
MACHINE=
while [ "x${MACHINE}" = "x" ]
do
    echo "Chose a platform for OE:"
    echo "    1) Silvermoon"
    echo "    2) Falconwing"
    echo -n "-> "
    read -n 1 T
    echo ""

    if [ "x${T}" = "x1" ]
    then
        MACHINE=chumby-silvermoon
    elif [ "x${T}" = "x2" ]
    then
        MACHINE=chumby-falconwing
    fi
done
BB_ENV_EXTRAWHITE="MACHINE DISTRO TOPDIR IMAGETYPE"
export MACHINE BB_ENV_EXTRAWHITE TOPDIR IMAGETYPE
#unalias bitbake 2> /dev/null
#alias bitbake='MACHINE=${MACHINE} bitbake'
echo "Environment set up to build for ${MACHINE}"
