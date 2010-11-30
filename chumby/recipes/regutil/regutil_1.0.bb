DESCRIPTION = "Chumby register reader and writer"
HOMEPAGE = "http://www.chumby.com/"
AUTHOR = "Sean Cross"
LICENSE = "BSD"
PR = "r0"

SRC_URI = "http://files.chumby.com/source/openembedded/utils-57557.tar.gz"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/src"
CNPLATFORM = "${@bb.data.getVar('MACHINE', d, 1).replace('chumby-', '')}"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} regutil.c -o regutil -DCNPLATFORM_${CNPLATFORM}
}

do_install() {
    install -d ${D}${sbindir}
    install -m 0755 regutil ${D}${sbindir}
}

SRC_URI[md5sum] = "30c28653b7a6bbaa3f83779f9edcc880"
SRC_URI[sha256sum] = "6903dc40c68b17ecb524c1ed14915e748fa1bb0e8ebda1a727886f1a369aa345"
