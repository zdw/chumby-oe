inherit native

DESCRIPTION = "Chumby config block manager"
HOMEPAGE = "http://www.chumby.com/"
AUTHOR = "Henry Groover"
LICENSE = "GPLv2"
PR = "r0"

SRC_URI = "http://files.chumby.com/source/openembedded/utils-57557.tar.gz"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/src"

do_compile() {
    ${CXX} ${CFLAGS} ${LDFLAGS} config_util.cpp -o config_util
}

# Empty stage_native.
# native.bbclass will try to call this and run oe_runmake.  That doesn't work
# for our packages, so just make it a NO-OP.
do_stage_native() {
    true
}

do_install() {
    install -d ${bindir}
    install -m 0755 ${S}/config_util ${bindir}/config_util
}

SRC_URI[md5sum] = "30c28653b7a6bbaa3f83779f9edcc880"
SRC_URI[sha256sum] = "6903dc40c68b17ecb524c1ed14915e748fa1bb0e8ebda1a727886f1a369aa345"
