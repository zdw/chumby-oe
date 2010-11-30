inherit autotools

DESCRIPTION = "Chumby patched wget code"
HOMEPAGE = "http://www.chumby.com/"
AUTHOR = "Duane Maxwell"
LICENSE = "GPLv2"
PR = "r0"

PROVIDES = "wget"

# Automake is broken on this project.  Needs to be looked into.

SRC_URI = "http://files.chumby.com/source/openembedded/utils-57557.tar.gz"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/src/wget"

autotools_do_configure() {
    oe_runconf $@
}

SRC_URI[md5sum] = "30c28653b7a6bbaa3f83779f9edcc880"
SRC_URI[sha256sum] = "6903dc40c68b17ecb524c1ed14915e748fa1bb0e8ebda1a727886f1a369aa345"
