DESCRIPTION = "C-based ap_scan replacement"
HOMEPAGE = "http://www.chumby.com"
AUTHOR = "Sean Cross"
LICENSE = "GPL"
PR = "r0"

SRC_URI = "http://files.chumby.com/source/openembedded/wifi-58300.tar.gz"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/src/ap_scan/"

do_compile() {
    ${CC} ap_scan.c -o ap_scan ${CFLAGS} ${LDFLAGS} -lm
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ap_scan ${D}/${bindir}
}

SRC_URI[md5sum] = "4b70c7cf32f44f66c25180c4b00fc5a2"
SRC_URI[sha256sum] = "d5070581755b7f99bb75947a5db3540fa42f25e291a9df85cc30c24095a8b2b0"
